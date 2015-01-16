#NoteKeeper
Note service for keeping yours notes in the cloud.
This repository contains two application.
##Java Backend
####Goal
Purpose of this application - provide REST service with security using as storage DataBase(MySql).
####Used for the development of:
* JavaEE 7 
* Spring Framework
* Persistence API
* MySql
* Junit
* Log4j
* H2 in memory database

##Frontend

####Goal
WebUI support.

####Used for the development of:

* Angular.JS
* BootStrap
* jQuery

#NoteKeeper
Java based Simple Note Service. This repository contains two application.
##Java Backend
####Goal
Purpose of this application - provide REST service with security using as storage DataBase(MySql).
####Used for the development of:
* JavaEE 7 
* Spring Framework
* Persistence API
* MySql
* Junit
* Log4j
* H2 in memory database

##Frontend

####Goal
WebUI support.

####Used for the development of:

* Angular.JS
* BootStrap
* jQuery

##Installation guide 
####Requirements

1. DataBase, your choice is not critical, tested on [MySql](http://dev.mysql.com/).
2. Application server, tested on [Tomcat](http://tomcat.apache.org/download-80.cgi) and [GlassFish](https://glassfish.java.net/).

####Steps

1. Create schema, tables and keys in your DataBase.

```SQL
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
CREATE TABLE user
(
  userId   BIGINT PRIMARY KEY NOT NULL,
  email    CHAR(40) UNIQUE    NOT NULL,
  login    CHAR(40) UNIQUE    NOT NULL,
  password CHAR(40)           NOT NULL
);
ALTER TABLE note ADD CONSTRAINT note_ibfk_1 FOREIGN KEY (userId)
REFERENCES user (userId)
  ON DELETE CASCADE;
CREATE INDEX R_3 ON note (userId);
```

2. [Clone git repository](https://github.com/BioQwer/NoteKeeper).

  ```
  git clone https://github.com/BioQwer/NoteKeeper.git
  ```
  
3. Edit configs for DataBase `app.properties`, if you use not [MySql](http://dev.mysql.com/) you need to add [Maven dependency](http://mvnrepository.com/) for your JDBC.

  ```
  cd serverSide/src/main/resources/app.properties
  ```

4. Compile project.

  ```
  mvn clean package
  ```

5. Deploy to Application server.
6. Enjoy.
