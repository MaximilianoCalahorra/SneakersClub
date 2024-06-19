USE `dbstockgrupo7`;

/* Users */
INSERT INTO `dbstockgrupo7`.`user`
(`created_at`, `enabled`, `password`, `updated_at`, `username`)
VALUES
('2024-06-18', b'1', '$2a$10$ZnRLwIr0ncC8fg5bgsRB5.ccjfyYp39c4yQiVNubLsf2qu4n8kJd.', '2024-06-18', 'ghsicilianouser'),
('2024-06-18', b'1', '$2a$10$pSHjCAc1mR43Lc.ZcIym9.A3TbpL8pr4S5Sxevp5.Xoe8E75M9RgK', '2024-06-18', 'ocruinauser'),
('2024-06-18', b'1', '$2a$10$5DOzhT.j8jcGh2D82p2nBubeGiqXf2IRLG9D5604xij/D974Ps3km', '2024-06-18', 'ghsicilianoadmin'),
('2024-06-18', b'1', '$2a$10$egFLTkgpkppOWvQ66EohJe0W8S593GAnAwj4Fa27wnyFE8TePzjLG', '2024-06-18', 'ocruinaadmin');

/* Users Roles */
INSERT INTO `dbstockgrupo7`.`user_role`
(`created_at`, `role`, `updated_at`, `user_id`)
VALUES
('2024-06-18', 'ROLE_USER', '2024-06-18', 1), /* Rol para el usuario con username 'ghsicilianouser' */
('2024-06-18', 'ROLE_USER', '2024-06-18', 2), /* Rol para el usuario con username 'ocruinauser' */
('2024-06-18', 'ROLE_ADMIN', '2024-06-18', 3), /* Rol para el usuario con username 'ghsicilianoadmin' */
('2024-06-18', 'ROLE_ADMIN', '2024-06-18', 4); /* Rol para el usuario con username 'ocruinaadmin' */

/* Products */
INSERT INTO `dbstockgrupo7`.`product`
(`brand`, `code`, `cost`, `description`, `enabled`, `name`, `sale_price`)
VALUES
('Nike', '355', 150, "Dunk Low 'Black White'", b'1', 'Dunk Low', 200), /* Product con el ID 1 */
('Nike', '460', 120, "Futura Laboratories X Dunk Low SB 'Bleached Aqua'", b'1', 'Dunk Low SB', 180), /* Product con el ID 2 */
('Nike', '395', 120, "Dunk Low 'Michigan State'", b'1', 'Dunk Low', 180), /* Product con el ID 3 */
('Nike', '220', 180, "Air Vapormax Plus 'Triple Black'", b'1', 'Vapormax', 220), /* Product con el ID 4 */
('Nike', '147', 200, "Air Foamposite One 'Eggplant' 2024", b'1', 'Air Foamposite', 250), /* Product con el ID 5 */
('Jordan', '85', 150, "Jordan 4 Retro 'Bred Reimagined'", b'1', "Jordan 4 Retro", 180), /* Product con el ID 6 */
('Jordan', '360', 200, "Jordan 1 Retro High OG 'Chicago Lost and Found'", b'1', 'Jordan 1 Retro High OG', 250), /* Product con el ID 7 */
('Jordan', '690', 200, "Jordan 1 Retro High OG 'Latte'", b'1', 'Jordan 1 Retro High OG', 250), /* Product con el ID 8 */
('Jordan', '752', 250, "Jordan 9 Retro 'Powder Blue' 2024", b'1', 'Jordan 9 Retro', 300), /* Product con el ID 9 */
('Jordan', '874', 300, "Travis Scott X Jordan 1 Retro Low OG SP 'Black Panthom'", b'1', 'Jordan 1 Retro Low OG SP', 350), /* Product con el ID 10 */
('Adidas', '45', 150, "Yezzy Boost 350 V2 'ONYX'", b'1', 'Yezzy', 190), /* Product con el ID 11 */
('Adidas', '356', 140, "Bad Bunny X Campus 'The Last Campus'", b'1', 'Campus 00', 200), /* Product con el ID 12 */
('Adidas', '160', 120, "Samba OG 'Collegiate Green Gum'", b'1', 'Samba OG', 250), /* Product con el ID 13 */
('Adidas', '79', 180, "AE 1 'Velocity Blue'", b'1', 'AE 1', 250), /* Product con el ID 14 */
('Adidas', '74', 150, "Yezzy Foam Runner 'Clay Taupe'", b'1', 'Yezzy Foam Runner', 200); /* Product con el ID 15 */

/* Stocks */
INSERT INTO `dbstockgrupo7`.`stock`
(`actual_amount`, `desirable_amount`, `minimum_amount`, `product_id`)
VALUES
(20, 30, 10, 1), /* Stock del Product con el ID 1 */
(25, 35, 15, 2), /* Stock del Product con el ID 2 */
(29, 50, 20, 3), /* Stock del Product con el ID 3 */
(14, 40, 15, 4), /* Stock del Product con el ID 4 */
(19, 45, 15, 5), /* Stock del Product con el ID 5 */
(17, 35, 10, 6), /* Stock del Product con el ID 6 */
(27, 50, 20, 7), /* Stock del Product con el ID 7 */
(22, 45, 15, 8), /* Stock del Product con el ID 8 */
(35, 60, 25, 9), /* Stock del Product con el ID 9 */
(31, 40, 15, 10), /* Stock del Product con el ID 10 */
(32, 35, 10, 11), /* Stock del Product con el ID 11 */
(7, 20, 5, 12), /* Stock del Product con el ID 12 */
(23, 40, 20, 13), /* Stock del Product con el ID 13 */
(30, 50, 15, 14), /* Stock del Product con el ID 14 */
(10, 25, 8, 15); /* Stock del Product con el ID 15 */

/* Supply Orders */
INSERT INTO `dbstockgrupo7`.`supply_order`
(`amount`, `state`, `supplier`, `product_id`)
VALUES
(10, 'Delivered', 'NikeSupplier', 1), /* Supply Order del Product con el ID 1 */
(15, 'Delivered', 'NikeSupplier', 1), /* Supply Order del Product con el ID 1 */
(15, 'Delivered', 'NikeSupplier', 2), /* Supply Order del Product con el ID 2 */
(20, 'Delivered', 'NikeSupplier', 3), /* Supply Order del Product con el ID 3 */
(10, 'Delivered', 'NikeSupplier', 3), /* Supply Order del Product con el ID 3 */
(10, 'Delivered', 'NikeSupplier', 4), /* Supply Order del Product con el ID 4 */
(15, 'Delivered', 'NikeSupplier', 5), /* Supply Order del Product con el ID 5 */
(15, 'Delivered', 'NikeSupplier', 5), /* Supply Order del Product con el ID 5 */
(20, 'Delivered', 'JordanSupplier', 6), /* Supply Order del Product con el ID 6 */
(10, 'Delivered', 'JordanSupplier', 7), /* Supply Order del Product con el ID 7 */
(10, 'Delivered', 'JordanSupplier', 7), /* Supply Order del Product con el ID 7 */
(15, 'Delivered', 'JordanSupplier', 8), /* Supply Order del Product con el ID 8 */
(15, 'Delivered', 'JordanSupplier', 9), /* Supply Order del Product con el ID 9 */
(20, 'Delivered', 'JordanSupplier', 9), /* Supply Order del Product con el ID 9 */
(10, 'Delivered', 'JordanSupplier', 10), /* Supply Order del Product con el ID 10 */
(10, 'Delivered', 'AdidasSupplier', 11), /* Supply Order del Product con el ID 11 */
(15, 'Delivered', 'AdidasSupplier', 11), /* Supply Order del Product con el ID 11 */
(15, 'Delivered', 'AdidasSupplier', 12), /* Supply Order del Product con el ID 12 */
(20, 'Delivered', 'AdidasSupplier', 13), /* Supply Order del Product con el ID 13 */
(10, 'Delivered', 'AdidasSupplier', 13), /* Supply Order del Product con el ID 13 */
(10, 'Delivered', 'AdidasSupplier', 14), /* Supply Order del Product con el ID 14 */
(15, 'Delivered', 'AdidasSupplier', 15), /* Supply Order del Product con el ID 15 */
(15, 'Delivered', 'AdidasSupplier', 15), /* Supply Order del Product con el ID 15 */
(15, 'In process', 'NikeSupplier', 1), /* Supply Order del Product con el ID 1 sin Lot asociado */
(10, 'In process', 'JordanSupplier', 6), /* Supply Order del Product con el ID 6 sin Lot asociado */
(20, 'In process', 'AdidasSupplier', 11), /* Supply Order del Product con el ID 11 sin Lot asociado */
(25, 'Delivered', 'NikeSupplier', 2), /* Supply Order del Product con el ID 2 sin Lot asociado */
(20, 'Delivered', 'JordanSupplier', 7), /* Supply Order del Product con el ID 7 sin Lot asociado */
(10, 'Delivered', 'AdidasSupplier', 12); /* Supply Order del Product con el ID 12 sin Lot asociado */

/* Lots */
INSERT INTO `dbstockgrupo7`.`lot`
(`existing_amount`, `initial_amount`, `purchase_price`, `reception_date`, `stock_id`, `supply_order_id`)
VALUES
(12, 15, 3000, '2024-05-30', 1, 1),
(8, 20, 2000, '2024-05-30', 1, 2),
(25, 30, 5400, '2024-05-31', 2, 3),
(12, 15, 2700, '2024-05-31', 3, 4),
(17, 20, 3600, '2024-06-01', 3, 5),
(14, 30, 6600, '2024-06-03', 4, 6),
(5, 15, 3750, '2024-06-04', 5, 7),
(14, 50, 12500, '2024-06-04', 5, 8),
(17, 30, 5400, '2024-06-06', 6, 9),
(10, 20, 2500, '2024-06-07', 7, 10),
(17, 25, 4250, '2024-06-08', 7, 11),
(22, 25, 6250, '2024-06-09', 8, 12),
(5, 40, 12000, '2024-06-10', 9, 13),
(30, 35, 10500, '2024-06-11', 9, 14),
(31, 40, 14350, '2024-06-12', 10, 15),
(10, 10, 1900, '2024-06-13', 11, 16),
(22, 25, 4750, '2024-06-13', 11, 17),
(7, 10, 2000, '2024-06-15', 12, 18),
(3, 5, 1250, '2024-06-15', 13, 19),
(20, 25, 6250, '2024-06-16', 13, 20),
(30, 30, 7500, '2024-06-17', 14, 21),
(2, 10, 2000, '2024-06-17', 15, 22),
(8, 10, 2000, '2024-06-18', 15, 23);

/* Purchases */
INSERT INTO `dbstockgrupo7`.`purchase`
(`amount`, `date_time`, `method_of_pay`, `purchase_price`, `product_id`)
VALUES
(5, '2024-06-16', 'Debit', 1000, 1),
(3, '2024-06-17', 'Credit', 880, 4),
(8, '2024-06-17', 'Debit', 2000, 7),
(4, '2024-06-18', 'Credit', 1400, 10),
(10, '2024-06-18', 'Debit', 2500, 13);

