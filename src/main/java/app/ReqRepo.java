package app;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReqRepo extends JpaRepository<Req, Long> {
    List<Req> findByStatus(String status);
    List<Req> findByByUser(String byUser);
    List<Req> findByDepartment(String department);
    List<Req> findByCurrentApprover(String currentApprover);
    List<Req> findByDepartmentAndStatus(String department, String status);
    List<Req> findByStatusOrderByPriorityDescCreatedAtAsc(String status);
}
