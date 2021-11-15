package se459.extremers.logger;

import lombok.Data;
import java.time.LocalDateTime;

import javax.persistence.*;

// @Table(name = "logs")

@Data
@Entity
public class RecordLog {
    @Id
    @GeneratedValue
    private long id;
    
    private String action;
    private String logType;
    private String surface;
    private String node;
    private String message;
    private LocalDateTime createdOn;
}
