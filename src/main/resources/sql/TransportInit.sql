DROP DATABASE IF EXISTS transport;

CREATE DATABASE transport;

CREATE TABLE Transport(
	transportid INT PRIMARY KEY,
    transportname VARCHAR(100) NOT NULL,
    timeUpserted BIGINT NOT NULL
);

INSERT INTO Transport(transportid, transportname, timeUpserted) VALUES (1, BPost, 1);
INSERT INTO Transport(transportid, transportname, timeUpserted) VALUES (2, PostNL, 1);
INSERT INTO Transport(transportid, transportname, timeUpserted) VALUES (3, FedEx, 1);
INSERT INTO Transport(transportid, transportname, timeUpserted) VALUES (4, DHL, 1);
INSERT INTO Transport(transportid, transportname, timeUpserted) VALUES (5, La Poste, 1);
