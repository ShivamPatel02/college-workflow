package app;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LogRepo extends JpaRepository<Log, Long> {
    List<Log> findByReqId(Long reqId);
    List<Log> findByUser(String user);
}
