CREATE TABLE note
(
  head           CHAR(255),
  body           LONGTEXT,
  creationDate   DATE,
  lastChangeDate DATE,
  noteId         BIGINT PRIMARY KEY NOT NULL,
  userId         BIGINT             NOT NULL
);
CREATE TABLE user
(
  userId   BIGINT PRIMARY KEY NOT NULL,
  email    CHAR(40),
  login    CHAR(40)           NOT NULL,
  password CHAR(40)
);
ALTER TABLE note ADD FOREIGN KEY (userId) REFERENCES user (userId);
CREATE INDEX R_3 ON note (userId);
