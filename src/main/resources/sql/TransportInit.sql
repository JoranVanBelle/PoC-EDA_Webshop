DROP DATABASE IF EXISTS transport;

CREATE DATABASE transport;

CREATE TABLE Transport(
	transportid INT PRIMARY KEY,
    transportname VARCHAR(100) NOT NULL,
    timeUpserted BIGINT NOT NULL
);