DROP DATABASE IF EXISTS inventory;

CREATE DATABASE inventory;

CREATE TABLE Inventory(
	productid INT PRIMARY KEY,
    productname VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    timeUpserted BIGINT NOT NULL
);

INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (1, 'Football', 1000000, 1);
INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (2, 'Volleyball', 1000000, 1);
INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (3, 'Basketball', 1000000, 1);
INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (4, 'Chair', 1000000, 1);
INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (5, 'Laptop', 1000000, 1);
INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (6, 'Desk', 1000000, 1);
INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (7, 'Table', 1000000, 1);
INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (8, 'Cup', 1000000, 1);
INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (9, 'Mouse', 1000000, 1);
INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES (10, 'Monitor', 1000000, 1);