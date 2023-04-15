DROP DATABASE IF EXISTS orders;

CREATE DATABASE orders;

CREATE TABLE orders(
	orderid INT PRIMARY KEY,
    orderprice DOUBLE NOT NULL,
    inventoryid INT NOT NULL,
    quantity INT NOT NULL,
    username VARCHAR(100) NOT NULL,
    transportid INT NOT NULL,
    timeUpserted BIGINT NOT NULL
);