INSERT INTO public.product (id, name, price) VALUES (1, 'Hot Dog', 10.99);
INSERT INTO public.product (id, name, price) VALUES (2, 'Pizza', 9.99);
INSERT INTO public.product (id, name, price) VALUES (3, 'Apple', 4.76);
INSERT INTO public.product (id, name, price) VALUES (4, 'Banana', 7.65);
INSERT INTO public.product (id, name, price) VALUES (5, 'Onion', 1.23);
INSERT INTO public.product (id, name, price) VALUES (6, 'Garlic', 3.24);
INSERT INTO public.product (id, name, price) VALUES (7, 'Pineapple', 5.45);
INSERT INTO public.product (id, name, price) VALUES (8, 'Avocado', 6.45);
INSERT INTO public.product (id, name, price) VALUES (9, 'Tomatoes', 6.78);
INSERT INTO public.product (id, name, price) VALUES (10, 'Broccoli', 8.69);

INSERT INTO public.seller (id, name) VALUES (11, 'Albert Carl');
INSERT INTO public.seller (id, name) VALUES (12, 'Carl Doe');
INSERT INTO public.seller (id, name) VALUES (13, 'Doe Esteban');
INSERT INTO public.seller (id, name) VALUES (14, 'Esteban Fali');
INSERT INTO public.seller (id, name) VALUES (15, 'Fali Gordon');
INSERT INTO public.seller (id, name) VALUES (16, 'Gordon Humus');

INSERT INTO public.sale (id, total_amount, seller_id) VALUES (18, 33.39, 11);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (19, 25.740000000000002, 11);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (20, 13.64, 11);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (21, 13.64, 12);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (22, 12.41, 12);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (23, 14.43, 12);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (24, 15.469999999999999, 12);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (25, 9.690000000000001, 13);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (26, 25.440000000000005, 13);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (27, 22.200000000000003, 14);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (28, 66.60000000000001, 14);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (29, 44.4, 14);
INSERT INTO public.sale (id, total_amount, seller_id) VALUES (30, 35.839999999999996, 15);

INSERT INTO public.sale_products (sale_id, product_id) VALUES (18, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (18, 2);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (18, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (18, 4);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (19, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (19, 2);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (19, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (20, 5);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (20, 4);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (20, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (21, 5);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (21, 4);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (21, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (22, 4);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (22, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (23, 4);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (23, 9);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (24, 10);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (24, 9);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (25, 8);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (25, 6);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (26, 8);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (26, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (26, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (26, 6);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (27, 8);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (27, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (27, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (28, 8);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (28, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (28, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (28, 8);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (28, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (28, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (28, 8);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (28, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (28, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (29, 8);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (29, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (29, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (29, 8);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (29, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (29, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (30, 3);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (30, 1);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (30, 4);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (30, 8);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (30, 5);
INSERT INTO public.sale_products (sale_id, product_id) VALUES (30, 3);
