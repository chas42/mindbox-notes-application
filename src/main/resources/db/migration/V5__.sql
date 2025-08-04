CREATE TABLE IF NOT EXISTS note_history
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    title      VARCHAR(255) NOT NULL,
    content    VARCHAR(10000) NOT NULL,
    user_id    BIGINT NOT NULL,
    changed_at datetime NOT NULL,
    note_id    BIGINT NOT NULL,
    version    INT NOT NULL,
    CONSTRAINT pk_note_history PRIMARY KEY (id)
);

ALTER TABLE note_history
    ADD CONSTRAINT FK_NOTE_HISTORY_ON_NOTE FOREIGN KEY (note_id) REFERENCES note (id);

ALTER TABLE note_history
    ADD CONSTRAINT FK_NOTE_HISTORY_ON_USER FOREIGN KEY (user_id) REFERENCES user_note (id);