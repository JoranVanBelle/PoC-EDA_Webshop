DROP DATABASE IF EXISTS inventory;

CREATE DATABASE inventory;

CREATE TABLE Inventory(
	productid INT PRIMARY KEY,
    productname VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    timeUpserted BIGINT NOT NULL
);