package app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Arrays;

@Controller
public class Web {

    private final ReqRepo reqRepo;
    private final ReqService reqService;
    private final UserRepo userRepo;
    private final LogRepo logRepo;

    public Web(ReqRepo reqRepo, ReqService reqService, UserRepo userRepo, LogRepo logRepo) {
        this.reqRepo = reqRepo;
        this.reqService = reqService;
        this.userRepo = userRepo;
        this.logRepo = logRepo;
    }

    @GetMapping("/")
    public String dashboard(HttpSession session, Model model) {
        User currentUser = getCurrentUser(session);
        model.addAttribute("currentUser", currentUser);
        
        // Add dashboard statistics with null safety
        if ("STUDENT".equals(currentUser.role)) {
            List<Req> myRequests = reqRepo.findByByUser(currentUser.username);
            model.addAttribute("totalRequests", myRequests.size());
            long pendingCount = myRequests.stream()
                .filter(r -> "PENDING".equals(r.status) || "IN_PROGRESS".equals(r.status))
                .count();
            model.addAttribute("pendingRequests", pendingCount);
        } else {
            List<Req> pendingForMe = reqRepo.findByCurrentApprover(currentUser.username);
            model.addAttribute("pendingForApproval", pendingForMe.size());
            // Set default values for student stats
            model.addAttribute("totalRequests", 0);
            model.addAttribute("pendingRequests", 0);
        }
        
        return "dashboard";
    }

    @GetMapping("/new")
    public String newRequestForm(HttpSession session, Model model) {
        User currentUser = getCurrentUser(session);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("req", new Req());
        
        // Add departments list
        List<String> departments = Arrays.asList("CSE", "ECE", "MECH", "CIVIL", "EEE", "IT");
        model.addAttribute("departments", departments);
        
        return "new-request";
    }

    @PostMapping("/save")
    public String saveRequest(@ModelAttribute Req req, HttpSession session) {
        User currentUser = getCurrentUser(session);
        req.byUser = currentUser.username;
        reqService.save(req);
        return "redirect:/my-requests?success=true";
    }

    @GetMapping("/my-requests")
    public String myRequests(HttpSession session, Model model) {
        User currentUser = getCurrentUser(session);
        List<Req> requests = reqRepo.findByByUser(currentUser.username);
        model.addAttribute("requests", requests);
        model.addAttribute("currentUser", currentUser);
        return "my-requests";
    }

    @GetMapping("/pending-approvals")
    public String pendingApprovals(HttpSession session, Model model) {
        User currentUser = getCurrentUser(session);
        List<Req> pendingRequests = reqRepo.findByCurrentApprover(currentUser.username);
        model.addAttribute("requests", pendingRequests);
        model.addAttribute("currentUser", currentUser);
        return "pending-approvals";
    }

    @GetMapping("/department-requests")
    public String departmentRequests(HttpSession session, Model model) {
        User currentUser = getCurrentUser(session);
        List<Req> deptRequests = reqRepo.findByDepartment(currentUser.department);
        model.addAttribute("requests", deptRequests);
        model.addAttribute("currentUser", currentUser);
        return "department-requests";
    }

    @GetMapping("/request/{id}")
    public String viewRequest(@PathVariable Long id, HttpSession session, Model model) {
        User currentUser = getCurrentUser(session);
        Req request = reqRepo.findById(id).orElse(null);
        
        if (request == null) {
            return "redirect:/my-requests?error=notfound";
        }
        
        // Get logs for this request
        List<Log> logs = logRepo.findByReqId(id);
        
        model.addAttribute("request", request);
        model.addAttribute("logs", logs);
        model.addAttribute("currentUser", currentUser);
        
        // Check if current user can approve this request
        boolean canApprove = request.currentApprover != null && 
                           request.currentApprover.equals(currentUser.username) &&
                           ("PENDING".equals(request.status) || "IN_PROGRESS".equals(request.status));
        model.addAttribute("canApprove", canApprove);
        
        return "request-details";
    }

    @PostMapping("/approve/{id}")
    public String approveRequest(@PathVariable Long id, @RequestParam String comments, HttpSession session) {
        User currentUser = getCurrentUser(session);
        reqService.approve(id, currentUser.username, comments);
        return "redirect:/pending-approvals?approved=true";
    }

    @PostMapping("/reject/{id}")
    public String rejectRequest(@PathVariable Long id, @RequestParam String comments, HttpSession session) {
        User currentUser = getCurrentUser(session);
        reqService.reject(id, currentUser.username, comments);
        return "redirect:/pending-approvals?rejected=true";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, 
                       HttpSession session, Model model) {
        try {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                boolean passwordValid = false;
                
                // Check if it's a plain text password (for demo users)
                if (PasswordUtil.isPlainTextPassword(user.password)) {
                    passwordValid = password.equals(user.password);
                } else {
                    passwordValid = PasswordUtil.verifyPassword(password, user.password);
                }
                
                if (passwordValid) {
                    session.setAttribute("currentUser", user);
                    return "redirect:/";
                }
            }
            
            model.addAttribute("error", "Invalid username or password");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Login failed. Please try again.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        if (session != null) {
            session.invalidate();
        }
        model.addAttribute("message", "You have been successfully logged out.");
        return "login";
    }

    // Removed switch-user functionality for security reasons
    // Users must log out and log in with different credentials

    private User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            throw new RuntimeException("User not authenticated");
        }
        return user;
    }

    // Legacy endpoints for compatibility
    @GetMapping("/approve")
    public String legacyApprove(HttpSession session, Model model) {
        return pendingApprovals(session, model);
    }

    @GetMapping("/yes/{id}")
    public String legacyYes(@PathVariable Long id, HttpSession session) {
        User currentUser = getCurrentUser(session);
        reqService.ok(id, currentUser.username);
        return "redirect:/pending-approvals";
    }

    @GetMapping("/no/{id}")
    public String legacyNo(@PathVariable Long id, HttpSession session) {
        User currentUser = getCurrentUser(session);
        reqService.no(id, currentUser.username);
        return "redirect:/pending-approvals";
    }
}
