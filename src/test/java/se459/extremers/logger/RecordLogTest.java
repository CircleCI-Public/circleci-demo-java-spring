package se459.extremers.logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testing RecordLog")
class RecordLogTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RecordLogRepository repository;

    private RecordLog createTestLog(String action, String logType, String surface, String node, String message,
            LocalDateTime dateTime) {
        RecordLog newTestLog = new RecordLog();
        newTestLog.setAction(action);
        newTestLog.setLogType(logType);
        newTestLog.setSurface(surface);
        newTestLog.setNode(node);
        newTestLog.setMessage(message);
        newTestLog.setCreatedOn(dateTime);
        entityManager.persistAndFlush(newTestLog);
        return newTestLog;
    }

    @DisplayName("Can create, persist, and retrieve new Record Log")
    @Test
    void canCreateRecordLog() {
        String testAction = "SWEEP";
        String testLogType = "LOG";
        String testSurface = "LOW_PILE_CARPET";
        String testNode = "NODE2";
        String testMessage = "Swept 5 units from node 2 of type low pile carpet";
        LocalDateTime testCreatedOn = LocalDateTime.parse("2021-07-23T19:20:21");

        RecordLog newRecord = createTestLog(testAction, testLogType, testSurface, testNode, testMessage, testCreatedOn);

        RecordLog dbRecord = repository.findById(newRecord.getId()).get();
        assertEquals(dbRecord.getAction(), testAction);
        assertEquals(dbRecord.getLogType(), testLogType);
        assertEquals(dbRecord.getSurface(), testSurface);
        assertEquals(dbRecord.getNode(), testNode);
        assertEquals(dbRecord.getMessage(), testMessage);
        assertEquals(dbRecord.getCreatedOn(), testCreatedOn);
    }
}