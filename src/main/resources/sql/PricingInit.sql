DROP DATABASE IF EXISTS pricing;

CREATE DATABASE pricing;

CREATE TABLE Pricing(
	id INT PRIMARY KEY AUTO_INCREMENT,
	productid INT PRIMARY KEY,
    productname VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    timeUpserted BIGINT NOT NULL
);

INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (1, 'Football', 20.00, 1);
INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (2, 'Volleyball', 20.00, 1);
INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (3, 'Basketball', 20.00, 1);
INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (4, 'Chair', 50.00, 1);
INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (5, 'Laptop', 150.00, 1);
INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (6, 'Desk', 150.00, 1);
INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (7, 'Table', 150.00, 1);
INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (8, 'Cup', 15.00, 1);
INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (9, 'Mouse', 30.00, 1);
INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES (10, 'Monitor', 100.00, 1);