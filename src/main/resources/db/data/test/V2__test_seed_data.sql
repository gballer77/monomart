INSERT INTO Catalogs (id, display_name)
VALUES ('electronics', 'Electronics');

INSERT INTO Products (id, catalog_id, name, image_src, image_alt, description, price, quantity)
VALUES ('d5d6d7d8-9c8b-4d3c-9f2e-1d5c5d2c5d2a', 'electronics', 'Dell Laptop', 'https://picsum.photos/id/2/200/300', 'Products Alt Text', 'This is a description of Dell Laptop', '10.99', 10);