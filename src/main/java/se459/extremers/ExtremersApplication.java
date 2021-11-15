package se459.extremers;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import se459.extremers.logger.RecordLog;
import se459.extremers.logger.RecordLogRepository;

@SpringBootApplication
public class ExtremersApplication {
	private static final Logger log = LoggerFactory.getLogger(ExtremersApplication.class);

    @Bean
    public CommandLineRunner printLogs(RecordLogRepository logRepository) {
        log.info("--- START printLogs ---");
        return (args) -> {
            
            RecordLog recordLog = new RecordLog();
            recordLog.setAction("MOVE");
            recordLog.setLogType("ERROR");
            recordLog.setSurface("BARE_SURFACE");
            recordLog.setNode("NODE1");
            recordLog.setMessage("Got stuck moving to node1, cannot navigate anywere");
            recordLog.setCreatedOn(LocalDateTime.now());
            logRepository.save(recordLog);

            log.info(logRepository.findAll().toString());
            log.info("--- END printLogs ---");
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(ExtremersApplication.class, args);
	}

}
