create database ONLINE_SHOP;

use ONLINE_SHOP;


-- drop database ONLINE_SHOP;



create table category(
                         category_id int primary key auto_increment,
                         name varchar(255),
                         image_name varchar(255));


create table product(
                        id int primary key auto_increment,
                        description varchar(255),
                        name varchar(255),
                        price double,
                        weight double,
                        category_id int,
                        image_name varchar(255),
                        foreign key(category_id) references category(category_id));


create table actual_product(
                               id int primary key auto_increment,
                               description varchar(255),
                               name varchar(255),
                               price double,
                               weight double,
                               category_id int,
                               image_name varchar(255),
                               foreign key(category_id) references category(category_id));

-- drop table actual_product;

create table roles(
                      id int primary key auto_increment,
                      name varchar(255));


create table users(
                      id int primary key auto_increment,
                      email varchar(255),
                      first_name varchar(255),
                      last_name varchar(255),
                      password varchar(255),
                      reset_password_token varchar(45));



create table user_role(
                          user_id int,
                          role_id int,
                          foreign key(user_id) references users(id),
                          foreign key(role_id) references roles(id));




create table contact_form(
                             id int primary key auto_increment,
                             name varchar(50),
                             email varchar(50),
                             message varchar(1000));



-- drop table contact_form;


insert into users values(1, 'name@email.com', 'name', 'first name', 'last name', 'name');
insert into users values(4, 'c@c.com', 'c', 'c', 'c', 'c');
insert into users(email, first_name, last_name, password) values('ex@email.com', 'example1', 'first name', 'last name', 'password');
insert into users(email, first_name, last_name, password) values('a@a.com', 'a', 'a', 'a');
insert into users(email, first_name, last_name, password) values('b@b.com', 'b', 'b', 'b');


INSERT INTO  category (name, image_name) VALUES
                                             ('IPhone', 'iphones.jpg'),
                                             ('Mac', 'macbooks.jpg'),
                                             ('IPad', 'ipads.jpg'),
                                             ('Smart Watch', 'smart-watches.jpg');

INSERT INTO  product(description, image_name, name, price, weight, category_id) VALUES
                                                                                    ('description of iphone 2', 'iphone1.jpg', 'Apple iPhone 13 Pro Max 128GB Sierra', '900', '300', '1'),
                                                                                    ('description of iphone 3', 'iphone2.jpg', 'Apple iPhone 13 256GB', '1100', '350', '1'),
                                                                                    ('description of iphone 4', 'iphone3.jpg', 'Apple iPhone 13 mini 256GB', '1200', '300', '1'),
                                                                                    ('description of iphone 5', 'iphone4.jpg', 'iPhone 13 mini 128GB (PRODUCT)', '700', '250', '1'),
                                                                                    ('description of iphone 6', 'iphone5.jpg', 'Apple iPhone 13 Pro 512GB', '800', '330', '1');

INSERT INTO  actual_product(description, image_name, name, price, weight, category_id) VALUES
                                                                                           ('description of iphone 1', 'iphone1.jpg', 'iPhone 12 Pro, 128GB', '999', '1290', '1'),
                                                                                           ('description of iphone 2', 'iphone2.jpg', 'Apple iPhone 13 128GB', '999', '1290', '1'),
                                                                                           ('description of iphone 3', 'iphone3.jpg', 'Apple Iphone 13 128 GB', '999', '1290', '1'),
                                                                                           ('description of ipad 1','ipad1.jpg', 'Apple iPad 9 (2021) 10.2" 256GB', '999', '1290', '3'),
                                                                                           ('description of ipad 3','ipad3.jpg', 'Apple iPad 10 (2022) 10.2" 512GB', '999', '1290', '3'),
                                                                                           ('description of macbook pro 1','mac-book-pro1.jpg', 'Laptop Apple MacBook Pro 13', '999', '1290', '2'),
                                                                                           ('description of smart watches 1','sw1.jpg', 'Smart watch Apple Watch Series 3', '999', '1290', '4'),
                                                                                           ('description of smart waches 2','sw2.jpg', 'Smart watch Apple Watch Series 4', '999', '1290', '4');



INSERT INTO  product(description, image_name, name, price, weight, category_id) VALUES
                                                                                    ('description of macbook 1', 'mac-book-air1.jpg', 'MacBook Air 500 GB', '1500', '1200', '2'),
                                                                                    ('description of macbook 2', 'mac-book-pro1.jpg', 'MacBook Pro 1000 GB', '2500', '2000', '2');


INSERT INTO  product(description, image_name, name, price, weight, category_id) VALUES
                                                                                    ('description of IPad 1', 'ipad1.jpg', 'Apple iPad mini 2021 Wi-Fi 256GB', '1300', '535', '3'),
                                                                                    ('description of IPad 2', 'ipad2.jpg', 'Apple iPad mini 5 Wi-Fi 64Gb Space Gray', '800', '433', '3'),
                                                                                    ('description of IPad 3', 'ipad3.jpg', 'Apple iPad Pro 12.9" M1 Wi-Fi + Cellular 2TB Silver', '900', '602', '3'),
                                                                                    ('description of IPad 4', 'ipad4.jpg', 'Apple iPad Pro 12.9" M1 Wi-Fi 512GB Space Gray', '1200', '254', '3'),
                                                                                    ('description of IPad 5', 'ipad5.jpg', 'Apple iPad mini 2021 Wi-Fi + Cellular 64GB Space Gray', '1500', '457', '3');




INSERT INTO  product(description, image_name, name, price, weight, category_id) VALUES
                                                                                    ('description of smart watch 1', 'sw1.jpg', 'Apple Watch Series 3 GPS 38mm Silver Aluminium', '350', '324', '4'),
                                                                                    ('description of smart watch 2', 'sw2.jpg', 'Apple Watch SE GPS 40mm Gold Aluminium', '250', '353', '4'),
                                                                                    ('description of smart watch 3', 'sw3.jpg', 'Apple Watch SE Nike GPS 44mm Space Gray Aluminum', '412', '300', '4'),
                                                                                    ('description of smart watch 4', 'sw4.jpg', 'Apple Watch Series 7 GPS 45mm Blue Aluminium', '200', '252', '4'),
                                                                                    ('description of smart watch 5', 'sw5.jpg', 'Apple Watch SE GPS 44mm Space Grey Aluminium', '400', '124', '4'),
                                                                                    ('description of smart watch 6', 'sw6.jpg', 'Apple Watch Series 7 Nike GPS 41mm Starlight Aluminium', '250', '335', '4'),
                                                                                    ('description of smart watch 7', 'sw7.jpg', 'Apple Watch Series 5 Nike+ GPS 40mm Silver Aluminium', '450', '223', '4'),
                                                                                    ('The first fitness service powered by Apple Watch.', 'exclusive.png', 'Apple Watch Series 7', '399.00', '150', '4');

INSERT INTO  product(description, image_name, name, price, weight, category_id) VALUES ('The first fitness service powered by Apple Watch.', 'exclusive.png', 'Apple Watch Series 7', '399.00', '150', '4');



INSERT INTO  roles (name) VALUES
                              ('ROLE_ADMIN'),
                              ('ROLE_USER');




INSERT INTO product (description, image_name, name, price, weight, category_id) VALUES ('Our thinnest, lightest notebook, completely transformed by the Apple M1 chip. CPU speeds up to 3.5x faster. GPU speeds up to 5x faster. ', 'macbook-air-space-gray.jpg', 'MacBook Air', '999', '1290', '1');
INSERT INTO product (description, image_name, name, price, weight, category_id) VALUES ('Liquid Retina display', 'ipad-pro-12-select-202003.png', 'IPad Pro 12.9 inch', '535', '641', '2');
INSERT INTO product (description, image_name, name, price, weight, category_id) VALUES ('', 'iphone-12-mini-select-2020.jpg', 'IPhone 12 mini', '699', '133', '3');
INSERT INTO product (description, image_name, name, price, weight, category_id) VALUES ('Iphone 11', 'iphone11.png', 'IPhone 11 64Gb', '599', '194', '3');
INSERT INTO product (description, image_name, name, price, weight, category_id) VALUES ('iPhone SE packs our powerful A13 Bionic chip into our most popular size at our most affordable price. It’s just what you’ve been waiting for.', 'iphone-se-family-select-2020.jpg', 'IPhone SE 64Gb', '399', '148', '3');
INSERT INTO product (description, image_name, name, price, weight, category_id) VALUES ('this is macbook', 'mbp16touch-space-select-201911.jpg', 'MacBook Pro 16\"', '2399', '2000', '3');





select *from users;
select *from roles;
select *from product;
select *from category;
select *from user_role;
select *from contact_form;







drop table roles;
