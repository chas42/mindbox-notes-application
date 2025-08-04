CREATE TABLE note
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    title      VARCHAR(255) NULL,
    content    VARCHAR(10000) NULL,
    user_id    VARCHAR(255) NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_note PRIMARY KEY (id)
);

CREATE TABLE note_tags
(
    note_id BIGINT NOT NULL,
    tags    VARCHAR(255) NULL
);

ALTER TABLE note_tags
    ADD CONSTRAINT fk_note_tags_on_note FOREIGN KEY (note_id) REFERENCES note (id);