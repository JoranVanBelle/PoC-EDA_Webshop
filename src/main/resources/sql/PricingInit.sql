DROP DATABASE IF EXISTS pricing;

CREATE DATABASE pricing;

CREATE TABLE Pricing(
	productid INT PRIMARY KEY,
    productname VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    timeUpserted BIGINT NOT NULL
);