
INSERT INTO user (id, passwd, name, phone, email)
VALUES(1, '{bcrypt}$2a$10$uL5HGS1ZIKNXL3s723odk.p73jbKbcpZWCGOLDoqCtfLioLXhzKuS', 'jaehee', '5141', 'jaehee@gmail.com');

INSERT INTO roles (id, name) VALUES (1, 'USER');

INSERT INTO user_roles(user_id, role_id) VALUES (1,1);


insert into category(id, name, ordering) values(1, '운동화', 1);
insert into category(id, name, ordering) values(2, '구두', 2);

INSERT INTO product(product_id, product_name, price, category_id, user_id) VALUES(1, '상품1', 100, 1, 1);
INSERT INTO product(product_id, product_name, price, category_id, user_id) VALUES(2, '상품2', 100, 1, 1);
INSERT INTO product(product_id, product_name, price, category_id, user_id) VALUES(3, '상품3', 100, 1, 1);
INSERT INTO product(product_id, product_name, price, category_id, user_id) VALUES(4, '상품4', 100, 1, 1);
INSERT INTO product(product_id, product_name, price, category_id, user_id) VALUES(5, '상품5', 100, 2, 1);
INSERT INTO product(product_id, product_name, price, category_id, user_id) VALUES(6, '상품6', 100, 2, 1);
INSERT INTO product(product_id, product_name, price, category_id, user_id) VALUES(7, '상품7', 100, 2, 1);

