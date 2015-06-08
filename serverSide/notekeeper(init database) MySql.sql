CREATE SCHEMA notekeeper;
CREATE TABLE note
(
  head           CHAR(255),
  body           LONGTEXT,
  creationDate   DATETIME,
  lastChangeDate DATETIME,
  noteId         BIGINT UNIQUE PRIMARY KEY NOT NULL,
  userId         BIGINT                    NOT NULL
);
CREATE TABLE users
(
  userId   BIGINT PRIMARY KEY NOT NULL,
  email    CHAR(40) UNIQUE    NOT NULL,
  login    CHAR(40) UNIQUE    NOT NULL,
  password CHAR(40)           NOT NULL
);
CREATE TABLE monitoring
(
  id       BIGINT   NOT NULL AUTO_INCREMENT,
  user_id  BIGINT   NOT NULL,
  note_id  BIGINT,
  log_time DATETIME NOT NULL,
  log_data LONGTEXT NOT NULL,
  PRIMARY KEY (id)
);
ALTER TABLE note ADD CONSTRAINT note_ibfk_1 FOREIGN KEY (userId)
REFERENCES users (userId)
  ON DELETE CASCADE;
CREATE INDEX R_3 ON note (userId);
ALTER TABLE monitoring ADD CONSTRAINT user_FK FOREIGN KEY (user_id)
REFERENCES users (userId)
  ON DELETE CASCADE;
ALTER TABLE monitoring ADD CONSTRAINT note_FK FOREIGN KEY (note_id)
REFERENCES note (noteId)
  ON DELETE CASCADE;