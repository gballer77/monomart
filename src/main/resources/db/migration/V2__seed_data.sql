INSERT INTO Catalogs (id, display_name)
VALUES ('home', 'Home Goods');

INSERT INTO Catalogs (id, display_name)
VALUES ('sporting', 'Sporting Goods');

INSERT INTO Catalogs (id, display_name)
VALUES ('electronics', 'Electronics');

INSERT INTO Catalogs (id, display_name)
VALUES ('clothes', 'Clothing');

INSERT INTO Products (id, catalog_id, name, image_src, image_alt, description, price)
VALUES ('d5f1c3f9-7b2e-4b6d-8a3c-9f3d3d9c7c6f', 'home', 'Coffee Mug', 'https://picsum.photos/id/100/200/300', 'Products Alt Text', 'This is a description of Coffee Mug', '10.99');

INSERT INTO Products (id, catalog_id, name, image_src, image_alt, description, price)
VALUES ('d1d8f6d9-9f2b-4d8b-9d7c-9b7f5a9c8d5f', 'home', 'Toothpicks', 'https://picsum.photos/id/200/200/300', 'Products Alt Text', 'This is a description of Toothpicks', '10.99');

INSERT INTO Products (id, catalog_id, name, image_src, image_alt, description, price)
VALUES ('d8d9d0a9-3c1e-4d7c-9f0d-2d5c5d2c5d5d', 'home', 'Umbrella', 'https://picsum.photos/id/300/200/300', 'Products Alt Text', 'This is a description of Umbrella', '10.99');

INSERT INTO Products (id, catalog_id, name, image_src, image_alt, description, price)
VALUES ('d5d6d7d8-9c8b-4d3c-9f2e-1d5c5d2c5d5d', 'sporting', 'Basketball', 'https://picsum.photos/id/400/200/300', 'Products Alt Text', 'This is a description of Basketball', '10.99');

INSERT INTO Products (id, catalog_id, name, image_src, image_alt, description, price)
VALUES ('d5d6d7d8-9c8b-4d3c-9f2e-1d5c5d2c5d5e', 'sporting', 'Balaclava', 'https://picsum.photos/id/567/200/300', 'Products Alt Text', 'This is a description of Balaclava', '10.99');

INSERT INTO Products (id, catalog_id, name, image_src, image_alt, description, price)
VALUES ('d5d6d7d8-9c8b-4d3c-9f2e-1d5c5d2c5d5f', 'electronics', 'Apple iPad', 'https://picsum.photos/id/1/200/300', 'Products Alt Text', 'This is a description of Apple iPad', '10.99');

INSERT INTO Products (id, catalog_id, name, image_src, image_alt, description, price)
VALUES ('d5d6d7d8-9c8b-4d3c-9f2e-1d5c5d2c5d2a', 'electronics', 'Dell Laptop', 'https://picsum.photos/id/2/200/300', 'Products Alt Text', 'This is a description of Dell Laptop', '10.99');

INSERT INTO Products (id, catalog_id, name, image_src, image_alt, description, price)
VALUES ('d5d6d7d8-9c8b-4d3c-9f2e-1d5c5d2c5d5b', 'clothes', 'Jorts', 'https://picsum.photos/id/634/200/300', 'Products Alt Text', 'This is a description of Jorts', '10.99');
