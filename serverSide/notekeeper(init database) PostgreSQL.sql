CREATE TABLE note
(
  head           VARCHAR(255),
  body           TEXT,
  creationDate   TIMESTAMP ,
  lastChangeDate TIMESTAMP ,
  noteId         BIGINT UNIQUE PRIMARY KEY NOT NULL,
  userId         BIGINT                    NOT NULL
);
CREATE TABLE users
(
  userId   BIGINT PRIMARY KEY NOT NULL,
  email    VARCHAR(40) UNIQUE    NOT NULL,
  login    VARCHAR(40) UNIQUE    NOT NULL,
  password VARCHAR(40)           NOT NULL
);
CREATE TABLE monitoring
(
  id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  note_id BIGINT,
  log_time TIMESTAMP  NOT NULL,
  log_data TEXT NOT NULL,
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