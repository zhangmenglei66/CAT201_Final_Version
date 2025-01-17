/*
 Navicat Premium Dump SQL

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.39)
 Source Host           : localhost:3306
 Source Schema         : shoping

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 11/01/2025 13:35:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收件人电话',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收件人地址',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收件人姓名',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '收货地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '13422222222', 'Shanghai', 'zhangsan', 1);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', 'admin');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `product_id` int NULL DEFAULT NULL COMMENT '商品id',
  `cart_num` int NULL DEFAULT NULL COMMENT '数量',
  `status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '状态',
  `order_id` int NULL DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (13, 1, 22, 2, '0', 0);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `cate_name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '类型名称',
  `cate_parent_id` int NULL DEFAULT NULL COMMENT '父类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Kitchen Supplies', 0);
INSERT INTO `category` VALUES (2, 'Clothing', 0);
INSERT INTO `category` VALUES (3, 'Office Supplies', 0);
INSERT INTO `category` VALUES (4, 'Home Goods', 0);
INSERT INTO `category` VALUES (5, 'Digital Technology', 0);
INSERT INTO `category` VALUES (6, 'Tableware', 1);
INSERT INTO `category` VALUES (7, 'Cookware', 1);
INSERT INTO `category` VALUES (8, 'Processed Foods', 1);
INSERT INTO `category` VALUES (9, 'Men\'s Clothing', 2);
INSERT INTO `category` VALUES (10, 'Women\'s Clothing', 2);
INSERT INTO `category` VALUES (11, 'Children\'s Clothing', 2);
INSERT INTO `category` VALUES (12, 'Senior Clothing', 2);
INSERT INTO `category` VALUES (13, 'Writing Stationery', 3);
INSERT INTO `category` VALUES (14, 'Office Equipment', 3);
INSERT INTO `category` VALUES (15, 'Bedding', 4);
INSERT INTO `category` VALUES (16, 'Furniture', 4);
INSERT INTO `category` VALUES (17, 'Appliances', 4);
INSERT INTO `category` VALUES (18, 'Computer Products', 5);
INSERT INTO `category` VALUES (19, 'Mobile Accessories', 5);
INSERT INTO `category` VALUES (20, 'Photography Equipment', 5);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单Id',
  `order_no` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `product_id` int NULL DEFAULT NULL COMMENT '产品Id',
  `user_id` int NULL DEFAULT 0 COMMENT '用户Id',
  `num` int NULL DEFAULT NULL COMMENT '产品数量',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '产品总价',
  `create_time` datetime NULL DEFAULT NULL COMMENT '订单创建时间',
  `status` int NULL DEFAULT NULL COMMENT '订单状态',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收件人电话',
  `realname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收件人姓名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (6, 'ORDE522661778698', 22, 1, 2, 10.00, '2024-11-29 09:31:06', 1, 'Shanghai', '13422222222', 'Zhangsan1');
INSERT INTO `orders` VALUES (7, 'ORDE980916767260', 5, 1, 1, 24.00, '2024-11-29 16:58:41', 0, 'Shanghai', '13422222222', 'Zhangsan1');
INSERT INTO `orders` VALUES (8, 'ORDE450159031201', 33, 1, 1, 5200.00, '2025-01-08 16:48:25', 0, 'Shanghai', '13422222222', 'Zhangsan1');
INSERT INTO `orders` VALUES (9, 'ORDE354709649141', 24, 1, 1, 35.00, '2025-01-08 16:52:08', 0, 'Shanghai', '13422222222', 'Zhangsan1');
INSERT INTO `orders` VALUES (10, 'ORDE199170709011', 34, 1, 1, 3699.00, '2025-01-10 14:43:39', 0, 'Shanghai', '13422222222', 'zhangsan');
INSERT INTO `orders` VALUES (11, 'ORDE945183752539', 22, 1, 2, 10.00, '2025-01-10 14:43:39', 0, 'Shanghai', '13422222222', 'zhangsan');
INSERT INTO `orders` VALUES (12, 'ORDE384774036038', 23, 1, 1, 6.00, '2025-01-10 14:43:39', 0, 'Shanghai', '13422222222', 'zhangsan');
INSERT INTO `orders` VALUES (13, 'ORDE423151704766', 25, 1, 1, 5000.00, '2025-01-10 14:54:07', 0, 'Shanghai', '13422222222', 'zhangsan');
INSERT INTO `orders` VALUES (14, 'ORDE377731882025', 25, 1, 1, 5000.00, '2025-01-10 14:57:30', 0, 'Shanghai', '13422222222', 'zhangsan');
INSERT INTO `orders` VALUES (15, 'ORDE795157264563', 25, 1, 1, 5000.00, '2025-01-10 15:06:29', 0, 'Shanghai', '13422222222', 'zhangsan');
INSERT INTO `orders` VALUES (16, 'ORDE891481035962', 5, 1, 1, 24.00, '2025-01-10 15:19:07', 0, 'Shanghai', '13422222222', 'zhangsan');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '商品名称',
  `product_description` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '产品简介',
  `product_price` decimal(10, 2) NOT NULL COMMENT '价格',
  `product_stock` int NULL DEFAULT NULL COMMENT '库存',
  `product_fid` int NULL DEFAULT NULL COMMENT '父分类id',
  `product_cid` int NULL DEFAULT NULL COMMENT '分类id',
  `product_image` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '商品图片',
  `product_status` int NOT NULL DEFAULT 1 COMMENT '商品状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '商品' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (4, 'Chinese Chopsticks', 'Chopsticks are comfortable and convenient to use.', 20.00, 120, 1, 6, 'ftamyldqrbdeggfw.png', 1);
INSERT INTO `product` VALUES (5, 'White Ceramic Bowl', 'Good quality, easy to use.', 24.00, 198, 1, 6, 'brzetjeaznrttpky.png', 1);
INSERT INTO `product` VALUES (6, 'Round Plate', 'Round plates are common household tableware.', 22.00, 300, 1, 6, 'pixrsdqrgwuohjij.png', 1);
INSERT INTO `product` VALUES (7, 'Gas Stove', 'Vatti gas stove, high quality.', 3600.00, 50, 1, 7, 'altrmdruwugljsma.png', 1);
INSERT INTO `product` VALUES (8, 'Rice Cooker', 'Cooking rice is fragrant and delicious.', 600.00, 120, 1, 7, 'nwbxtwhqbtrdrztk.png', 1);
INSERT INTO `product` VALUES (9, 'Luhua Peanut Oil', 'Cold-pressed, highly fragrant and pure.', 99.00, 500, 1, 8, 'jhdoqnevdzywjndg.png', 1);
INSERT INTO `product` VALUES (10, 'Haitian Oyster Sauce', 'Enhances flavor when cooking.', 10.00, 450, 1, 8, 'crddqwmkihpfvcrv.png', 1);
INSERT INTO `product` VALUES (11, 'Men\'s Tops', 'H&M, reliable quality.', 120.00, 200, 2, 9, 'arjciaclnepjifjn.png', 1);
INSERT INTO `product` VALUES (12, 'Men\'s Jackets', 'Benneton men\'s jacket, warm and reliable.', 250.00, 400, 2, 9, 'keaarifjqcshgfyh.png', 1);
INSERT INTO `product` VALUES (13, 'Men\'s Jeans', 'Affordable price, good quality.', 150.00, 400, 2, 9, 'bxyxfvoxcbgypkmw.png', 1);
INSERT INTO `product` VALUES (14, 'Dress', 'Clean, neat, and cool and comfortable for summer.', 250.00, 330, 2, 10, 'mrobroiogrwoqmfi.png', 1);
INSERT INTO `product` VALUES (15, 'Women\'s Winter Wear', 'Warm and good quality.', 500.00, 160, 2, 10, 'xoufcuwcikzfolfo.png', 1);
INSERT INTO `product` VALUES (16, 'Qipao', 'Elegant temperament.', 350.00, 200, 2, 10, 'yzwrgkbgducnnzqa.png', 1);
INSERT INTO `product` VALUES (17, 'Children\'s Thermal Shirt', 'Good-looking and durable.', 160.00, 300, 2, 11, 'zkgccxzktqfycwrz.png', 1);
INSERT INTO `product` VALUES (18, 'Children\'s Hat', 'Attractive and beautiful.', 50.00, 420, 2, 11, 'lfbemoyarkvxynsv.png', 1);
INSERT INTO `product` VALUES (19, 'Senior Tops', 'Noble and elegant.', 200.00, 350, 2, 12, 'fkblybgseppbuxds.png', 1);
INSERT INTO `product` VALUES (20, 'Senior Cotton Coat', 'Warm and comfortable for winter.', 450.00, 230, 2, 12, 'kxjvyhuywbxgdaqr.png', 1);
INSERT INTO `product` VALUES (21, 'Ballpoint Pen', 'Aesthetically pleasing, easy to write, and durable.', 10.00, 500, 3, 13, 'zzxqbpvcuacartsm.png', 1);
INSERT INTO `product` VALUES (22, 'Sticky Notes', 'Environmentally friendly, convenient, and tidy.', 5.00, 598, 3, 13, 'rdhytspsonvgnpds.png', 1);
INSERT INTO `product` VALUES (23, 'Stationery Notebook', 'A great helper for work meetings.', 6.00, 499, 3, 13, 'egwwjgldemctexhf.png', 1);
INSERT INTO `product` VALUES (24, 'Stapler', 'Sturdy, reliable, and durable.', 35.00, 599, 3, 14, 'veucwdtffchmjtei.png', 1);
INSERT INTO `product` VALUES (25, 'Photocopier', 'Large office equipment.', 5000.00, 57, 3, 14, 'uhfwbzqfcgtwbbgf.png', 1);
INSERT INTO `product` VALUES (26, 'Quilt', 'Warm and comfortable.', 300.00, 600, 4, 15, 'zrkzdotufsufthzq.png', 1);
INSERT INTO `product` VALUES (27, 'Children\'s Mosquito Net', 'Clean, neat, and mosquito-proof.', 268.00, 300, 4, 15, 'rztbuewhulbuyfvy.png', 1);
INSERT INTO `product` VALUES (28, 'Computer Desk', 'Strong, solid, and durable.', 260.00, 250, 4, 16, 'rolzeksrbnwcrvcr.png', 1);
INSERT INTO `product` VALUES (29, 'Rectangular Coffee Table', 'Convenient to use, simple and easy to operate.', 220.00, 400, 4, 16, 'xfqkqnfihrkawryk.png', 1);
INSERT INTO `product` VALUES (30, 'Chair', 'Comfortable to sit on, aesthetically pleasing, and durable.', 60.00, 500, 4, 16, 'pwyhwbgzxqfnzvag.png', 1);
INSERT INTO `product` VALUES (31, 'Haier Air Conditioner', 'Energy-saving, environmentally friendly, and guaranteed quality.', 2500.00, 300, 4, 17, 'pljrvhvvxyodjamt.png', 1);
INSERT INTO `product` VALUES (32, 'Haier Washing Machine', 'Washes clothes cleanly, saves electricity.', 5000.00, 260, 4, 17, 'jhiaiizqofxlryhw.png', 1);
INSERT INTO `product` VALUES (33, 'Huawei Laptop', 'High performance and excellent quality.', 5200.00, 359, 5, 18, 'ivnozwaaojyswdjd.png', 1);
INSERT INTO `product` VALUES (34, 'Xiaomi Laptop', 'Great value for money, easy to operate.', 3699.00, 499, 5, 18, 'kwtyvlmfbnvgjovj.png', 1);
INSERT INTO `product` VALUES (35, 'Huawei Mate 60', 'Good quality, superior craftsmanship.', 6000.00, 600, 5, 19, 'dkmgngxlpavzcbvd.png', 1);
INSERT INTO `product` VALUES (36, 'Xiaomi 10 Smartphone', 'Great value for money, full features.', 2999.00, 600, 5, 19, 'yxymumsepcntjyjm.png', 1);
INSERT INTO `product` VALUES (37, 'Sony Camera', 'A trusted old brand.', 2850.00, 300, 5, 20, 'xdzyenlflfseggry.png', 1);
INSERT INTO `product` VALUES (38, 'Projector', 'A must-have tool for meetings.', 1500.00, 500, 3, 14, 'ydykhbdjundhfhxc.png', 1);
INSERT INTO `product` VALUES (39, 'Selfie Stick', 'Easy to use, simple to learn.', 60.00, 500, 5, 20, 'nxrvgrxuvjmlavzt.png', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  `balance` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号余额',
  `sex` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user', '123', '0.0', 'Man', '13458586969', '123@123', NULL);

SET FOREIGN_KEY_CHECKS = 1;
