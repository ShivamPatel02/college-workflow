package app;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReqService {

    private final ReqRepo reqRepo;
    private final LogRepo logRepo;
    private final UserRepo userRepo;

    public ReqService(ReqRepo reqRepo, LogRepo logRepo, UserRepo userRepo) {
        this.reqRepo = reqRepo;
        this.logRepo = logRepo;
        this.userRepo = userRepo;
    }

    public void save(Req req) {
        req.status = "PENDING";
        req.createdAt = LocalDateTime.now();
        req.updatedAt = LocalDateTime.now();
        
        // Determine approval chain based on request type
        setApprovalChain(req);
        
        reqRepo.save(req);
        
        // Log creation
        logRepo.save(new Log(req.getId(), "CREATED", req.byUser, "Request created and submitted"));
    }

    private void setApprovalChain(Req req) {
        // Define approval hierarchy based on request type (Teachers completely removed)
        switch (req.type) {
            case "Leave Request":
            case "Assignment Extension":
            case "Lab Access":
            case "Library Book Extension":
                // Simple: Student -> Coordinator
                req.approvalLevel = "COORDINATOR";
                req.currentApprover = findCoordinatorInDepartment(req.department);
                req.approvalChain = "COORDINATOR";
                break;
                
            case "Fee Concession":
            case "Transfer Certificate":
            case "Scholarship Application":
            case "Course Change":
            case "Exam Re-evaluation":
                // Medium: Student -> Coordinator -> HOD
                req.approvalLevel = "COORDINATOR";
                req.currentApprover = findCoordinatorInDepartment(req.department);
                req.approvalChain = "COORDINATOR->HOD";
                break;
                
            case "Project Approval":
            case "Equipment Purchase":
            case "Infrastructure Request":
            case "Industry Collaboration":
            case "Research Funding":
                // Complex: Student -> Coordinator -> HOD -> Director
                req.approvalLevel = "COORDINATOR";
                req.currentApprover = findCoordinatorInDepartment(req.department);
                req.approvalChain = "COORDINATOR->HOD->DIRECTOR";
                break;
                
            default:
                req.approvalLevel = "COORDINATOR";
                req.currentApprover = findCoordinatorInDepartment(req.department);
                req.approvalChain = "COORDINATOR";
        }
    }

    private String findCoordinatorInDepartment(String department) {
        List<User> coordinators = userRepo.findByRoleAndDepartment("COORDINATOR", department);
        return coordinators.isEmpty() ? "coordinator_" + department.toLowerCase() : coordinators.get(0).username;
    }

    private String findHODInDepartment(String department) {
        List<User> hods = userRepo.findByRoleAndDepartment("HOD", department);
        return hods.isEmpty() ? "hod_" + department.toLowerCase() : hods.get(0).username;
    }

    private String findDirector() {
        List<User> directors = userRepo.findByRole("DIRECTOR");
        return directors.isEmpty() ? "director" : directors.get(0).username;
    }

    public void approve(Long id, String approver, String comments) {
        Req req = reqRepo.findById(id).orElse(null);
        if (req == null) return;

        User approverUser = userRepo.findByUsername(approver);
        if (approverUser == null) return;

        // Log the approval
        logRepo.save(new Log(id, "APPROVED", approver, comments));

        // Check if this is the final approval or needs to move to next level
        if (needsNextLevelApproval(req, approverUser.role)) {
            moveToNextApprovalLevel(req, approverUser.role);
            req.status = "IN_PROGRESS";
        } else {
            req.status = "APPROVED";
            req.currentApprover = null;
        }

        reqRepo.save(req);
    }

    public void reject(Long id, String approver, String comments) {
        Req req = reqRepo.findById(id).orElse(null);
        if (req == null) return;

        req.status = "REJECTED";
        req.currentApprover = null;
        reqRepo.save(req);

        logRepo.save(new Log(id, "REJECTED", approver, comments));
    }

    private boolean needsNextLevelApproval(Req req, String currentRole) {
        if (req.approvalChain == null) return false;
        
        String[] chain = req.approvalChain.split("->");
        for (int i = 0; i < chain.length; i++) {
            if (chain[i].equals(currentRole) && i < chain.length - 1) {
                return true;
            }
        }
        return false;
    }

    private void moveToNextApprovalLevel(Req req, String currentRole) {
        String[] chain = req.approvalChain.split("->");
        for (int i = 0; i < chain.length; i++) {
            if (chain[i].equals(currentRole) && i < chain.length - 1) {
                String nextRole = chain[i + 1];
                req.approvalLevel = nextRole;
                
                switch (nextRole) {
                    case "COORDINATOR":
                        req.currentApprover = findCoordinatorInDepartment(req.department);
                        break;
                    case "HOD":
                        req.currentApprover = findHODInDepartment(req.department);
                        break;
                    case "DIRECTOR":
                        req.currentApprover = findDirector();
                        break;
                }
                
                logRepo.save(new Log(req.getId(), "FORWARDED", req.currentApprover, 
                    currentRole, nextRole));
                break;
            }
        }
    }

    // Legacy methods for compatibility
    public void ok(Long id, String user) {
        approve(id, user, "Approved");
    }

    public void no(Long id, String user) {
        reject(id, user, "Rejected");
    }
}
