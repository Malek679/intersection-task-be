package michal.malek.avsystemtask.service;

import lombok.RequiredArgsConstructor;
import michal.malek.avsystemtask.model.log.LogEntity;
import michal.malek.avsystemtask.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public void log(String message, int step) {
        LogEntity logEntity = new LogEntity(message, step);
        save(logEntity);
    }

    public List<LogEntity> findAll() {
        return logRepository.findTop50ByOrderByTimestampDesc();
    }

    private void save(final LogEntity log) {
        logRepository.saveAndFlush(log);
    }
}
