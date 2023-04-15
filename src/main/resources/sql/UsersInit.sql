DROP DATABASE IF EXISTS users;

CREATE DATABASE users;

CREATE TABLE Users(
	username VARCHAR(100) PRIMARY KEY,
    pw VARCHAR(100) NOT NULL
);

SELECT users;
INSERT INTO Users (username, pw) VALUES ("user", "admin");