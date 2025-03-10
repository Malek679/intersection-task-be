package michal.malek.avsystemtask.repository;

import michal.malek.avsystemtask.model.log.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findTop50ByOrderByTimestampDesc();
}
