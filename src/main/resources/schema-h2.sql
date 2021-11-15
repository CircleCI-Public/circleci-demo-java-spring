DROP TABLE IF EXISTS record_log;
DROP TABLE IF EXISTS logs;

CREATE SEQUENCE hibernate_sequence START WITH 100 INCREMENT BY 1;

CREATE TABLE record_log
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    action          VARCHAR(40),
    log_type        VARCHAR(40),
    surface         VARCHAR(40),
    node            VARCHAR(40),
    message         VARCHAR(100),
    created_on      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    primary key (id)
);