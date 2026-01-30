package app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitService implements CommandLineRunner {

    private final UserRepo userRepo;
    private final ReqRepo reqRepo;
    private final ReqService reqService;

    public DataInitService(UserRepo userRepo, ReqRepo reqRepo, ReqService reqService) {
        this.userRepo = userRepo;
        this.reqRepo = reqRepo;
        this.reqService = reqService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize sample users if database is empty
        if (userRepo.count() == 0) {
            initializeSampleData();
            createSampleRequests();
        }
    }

    private void initializeSampleData() {
        // Students for each branch
        userRepo.save(new User("student1", "pass", "STUDENT", "CSE", "Rahul Kumar"));
        userRepo.save(new User("student2", "pass", "STUDENT", "ECE", "Priya Sharma"));
        userRepo.save(new User("student3", "pass", "STUDENT", "MECH", "Amit Singh"));
        userRepo.save(new User("student4", "pass", "STUDENT", "CIVIL", "Sneha Patel"));
        userRepo.save(new User("student5", "pass", "STUDENT", "EEE", "Arjun Reddy"));
        userRepo.save(new User("student6", "pass", "STUDENT", "IT", "Kavya Nair"));

        // Class Coordinators for each branch
        userRepo.save(new User("coordinator_cse", "pass", "COORDINATOR", "CSE", "Prof. Anita Desai"));
        userRepo.save(new User("coordinator_ece", "pass", "COORDINATOR", "ECE", "Prof. Ramesh Chandra"));
        userRepo.save(new User("coordinator_mech", "pass", "COORDINATOR", "MECH", "Prof. Deepak Agarwal"));
        userRepo.save(new User("coordinator_civil", "pass", "COORDINATOR", "CIVIL", "Prof. Sushma Rao"));
        userRepo.save(new User("coordinator_eee", "pass", "COORDINATOR", "EEE", "Prof. Manoj Kumar"));
        userRepo.save(new User("coordinator_it", "pass", "COORDINATOR", "IT", "Prof. Neha Jain"));

        // HODs for each branch
        userRepo.save(new User("hod_cse", "pass", "HOD", "CSE", "Dr. Suresh Kumar"));
        userRepo.save(new User("hod_ece", "pass", "HOD", "ECE", "Dr. Kavita Singh"));
        userRepo.save(new User("hod_mech", "pass", "HOD", "MECH", "Dr. Ravi Sharma"));
        userRepo.save(new User("hod_civil", "pass", "HOD", "CIVIL", "Dr. Anjali Gupta"));
        userRepo.save(new User("hod_eee", "pass", "HOD", "EEE", "Dr. Satish Reddy"));
        userRepo.save(new User("hod_it", "pass", "HOD", "IT", "Dr. Pradeep Joshi"));

        // Director
        userRepo.save(new User("director", "pass", "DIRECTOR", "ADMIN", "Dr. Ashok Mehta"));

        // Admin
        userRepo.save(new User("admin", "pass", "ADMIN", "ADMIN", "System Administrator"));

        System.out.println("Sample data initialized successfully!");
    }

    private void createSampleRequests() {
        // Create some sample requests for testing
        System.out.println("Creating sample requests for testing...");
        
        // Only create sample requests if none exist
        if (reqRepo.count() == 0) {
            // Sample request 1 - Simple request
            Req req1 = new Req();
            req1.type = "Leave Request";
            req1.department = "CSE";
            req1.priority = "MEDIUM";
            req1.details = "I want college leave for attending my sister's wedding ceremony.";
            req1.byUser = "student1";
            reqService.save(req1);
            
            // Sample request 2 - Medium request
            Req req2 = new Req();
            req2.type = "Fee Concession";
            req2.department = "ECE";
            req2.priority = "HIGH";
            req2.details = "Requesting fee concession due to financial difficulties. Family income has reduced significantly.";
            req2.byUser = "student2";
            reqService.save(req2);
            
            // Sample request 3 - Complex request
            Req req3 = new Req();
            req3.type = "Project Approval";
            req3.department = "CSE";
            req3.priority = "HIGH";
            req3.details = "Requesting approval for final year project on AI-based traffic management system.";
            req3.byUser = "student1";
            reqService.save(req3);
            
            System.out.println("Sample requests created successfully!");
        }
    }
}