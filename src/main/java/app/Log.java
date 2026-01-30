package app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long reqId;
    public String action; // CREATED, APPROVED, REJECTED, FORWARDED, COMMENTED
    @Column(name = "username")
    public String user;
    public String fromRole;
    public String toRole;
    public String comments;
    public LocalDateTime time;

    public Log() {}

    public Log(Long reqId, String action, String user, String fromRole, String toRole) {
        this.reqId = reqId;
        this.action = action;
        this.user = user;
        this.fromRole = fromRole;
        this.toRole = toRole;
        this.time = LocalDateTime.now();
    }

    public Log(Long reqId, String action, String user, String comments) {
        this.reqId = reqId;
        this.action = action;
        this.user = user;
        this.comments = comments;
        this.time = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getReqId() { return reqId; }
    public void setReqId(Long reqId) { this.reqId = reqId; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getFromRole() { return fromRole; }
    public void setFromRole(String fromRole) { this.fromRole = fromRole; }
    public String getToRole() { return toRole; }
    public void setToRole(String toRole) { this.toRole = toRole; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }
}
