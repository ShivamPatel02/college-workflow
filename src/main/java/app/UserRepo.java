package app;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByRole(String role);
    List<User> findByDepartment(String department);
    List<User> findByRoleAndDepartment(String role, String department);
}
