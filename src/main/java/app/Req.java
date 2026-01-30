package app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
public class Req {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String type;
    public String details;
    public String status; // PENDING, APPROVED, REJECTED, IN_PROGRESS
    public String byUser;
    public String department; // Target department
    public String priority; // LOW, MEDIUM, HIGH, URGENT
    public String currentApprover; // Current person who needs to approve
    public String approvalLevel; // COORDINATOR, HOD, DIRECTOR
    public String approvalChain; // JSON string of approval hierarchy
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public Req() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Req(String type, String details, String department) {
        this.type = type;
        this.details = details;
        this.department = department;
        this.status = "PENDING";
        this.priority = "MEDIUM";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; this.updatedAt = LocalDateTime.now(); }
    public String getByUser() { return byUser; }
    public void setByUser(String byUser) { this.byUser = byUser; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getCurrentApprover() { return currentApprover; }
    public void setCurrentApprover(String currentApprover) { this.currentApprover = currentApprover; }
    public String getApprovalLevel() { return approvalLevel; }
    public void setApprovalLevel(String approvalLevel) { this.approvalLevel = approvalLevel; }
    public String getApprovalChain() { return approvalChain; }
    public void setApprovalChain(String approvalChain) { this.approvalChain = approvalChain; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
