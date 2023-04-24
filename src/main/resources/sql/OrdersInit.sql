DROP DATABASE IF EXISTS orders;

CREATE DATABASE orders;

CREATE TABLE orders(
	id INT PRIMARY KEY AUTO_INCREMENT,
	orderid INT NOT NULL,
    orderprice DOUBLE NOT NULL,
    inventoryid INT NOT NULL,
    quantity INT NOT NULL,
    username VARCHAR(100) NOT NULL,
    transportid INT NOT NULL,
    timeUpserted BIGINT NOT NULL
);