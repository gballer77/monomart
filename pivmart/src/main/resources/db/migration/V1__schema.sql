create table cart_items (id uuid not null, quantity integer, product_id uuid not null, primary key (id));
create table products (id uuid not null, catalog_id uuid, description varchar(255), image_alt varchar(255), image_src varchar(255), name varchar(255), price varchar(255), primary key (id));
alter table cart_items add constraint FK1re40cjegsfvw58xrkdp6bac6 foreign key (product_id) references products;
