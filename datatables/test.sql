-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.6.4-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table swappee.item: ~10 rows (approximately)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
REPLACE INTO `item` (`id`, `user_id`, `status`, `name`, `description`, `brand`, `new`, `category`, `strict`, `likes`, `version`, `created_date`, `created_by`, `last_modified_date`, `last_modified_by`, `deleted`) VALUES
	(1, 5, 'OPEN', 'Chemistry O level notes', 'Done by me, giving it for free', 'No brand', b'0', 'School', b'1', 2, 2, '2022-05-11 11:59:38', 'tayyantay', '2022-05-11 22:19:09', 'lyuzher', b'0'),
	(2, 5, 'OPEN', 'Samsung Galaxy S7 Tablet', 'BNIB, looking for Ipad Pro', 'Samsung', b'0', 'Mobiles', b'1', 3, 3, '2022-05-11 14:01:00', 'tayyantay', '2022-05-11 22:18:56', 'lyuzher', b'0'),
	(3, 3, 'OPEN', 'Pedigree Dog Food', 'Mistaken my cat for a dog', 'Pedigree', b'0', 'Pets', b'1', 0, 0, '2022-05-11 22:05:35', 'master_marc99', '2022-05-11 22:05:35', 'master_marc99', b'0'),
	(4, 4, 'OPEN', 'Protein Powder', 'Stopped gyming, no longer need it. Looking for fast food coupons', 'Whey', b'0', 'Health', b'1', 2, 2, '2022-05-11 22:10:06', 'tauple', '2022-05-11 22:21:01', 'agoh', b'0'),
	(5, 4, 'OPEN', 'Nike Flex Experience Run 10', 'Shoe size EU 10, stopped running. Looking for clothing', 'Nike', b'0', 'Shoes', b'0', 0, 0, '2022-05-11 23:13:39', 'tauple', '2022-05-11 22:13:39', 'tauple', b'0'),
	(6, 3, 'OPEN', 'Biscuits', 'Stockpiled for COVID outbreak and no longer need. Not accepting toilet paper', 'No brand', b'0', 'Food', b'0', 1, 1, '2022-05-11 22:25:30', 'master_marc99', '2022-05-11 22:25:24', 'agoh', b'0'),
	(7, 9, 'OPEN', 'Elden Ring account', 'Looking for better games', 'No brand', b'0', 'Games', b'0', 0, 0, '2022-05-11 22:25:20', 'lyuzher', '2022-05-12 22:18:39', 'lyuzher', b'0'),
	(8, 10, 'OPEN', 'Chemistry A levels TYS', 'Offer anything!', 'No brand', b'0', 'School', b'0', 0, 0, '2022-05-11 22:25:16', 'agoh', '2022-05-13 22:22:34', 'agoh', b'0'),
	(9, 10, 'OPEN', 'Physics A levels TYS', 'Offer anything!', 'No brand', b'0', 'School', b'0', 0, 0, '2022-05-11 22:25:08', 'agoh', '2022-05-13 22:28:31', 'agoh', b'0'),
	(10, 5, 'OPEN', 'CS2109 Notes', 'Looking for NUS CS module notes', 'No brand', b'0', 'School', b'1', 0, 0, '2022-05-12 20:15:42', 'tayyantay', '2022-05-12 20:15:42', 'tayyantay', b'0');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

-- Dumping data for table swappee.item_history: ~0 rows (approximately)
/*!40000 ALTER TABLE `item_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_history` ENABLE KEYS */;

-- Dumping data for table swappee.likes: ~8 rows (approximately)
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
REPLACE INTO `likes` (`id`, `item_id`, `user_id`, `created_date`, `item_deleted`) VALUES
	(1, 1, 3, '2022-05-11 22:06:19', b'0'),
	(2, 2, 3, '2022-05-11 22:06:25', b'0'),
	(3, 2, 4, '2022-05-11 22:10:32', b'0'),
	(4, 2, 9, '2022-05-11 22:18:56', b'0'),
	(5, 4, 9, '2022-05-11 22:19:01', b'0'),
	(6, 1, 9, '2022-05-11 22:19:09', b'0'),
	(7, 6, 10, '2022-05-11 22:20:51', b'0'),
	(8, 4, 10, '2022-05-11 22:21:01', b'0');
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;

-- Dumping data for table swappee.picture: ~16 rows (approximately)
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
REPLACE INTO `picture` (`id`, `item_id`, `pic_order`, `file_data`, `file_name`, `content_type`, `content_length`, `description`) VALUES
	(1, 1, 0, _binary '', 'Chemistry O level notes_0.jpeg', 'image/jpeg', 0, 'postman'),
	(2, 1, 1, _binary '', 'Chemistry O level notes_1.jpeg', 'image/jpeg', 0, 'postman2'),
	(3, 2, 0, _binary '', 'Samsung Galaxy S7 Tablet_0.jpeg', 'image/jpeg', 0, 'postman'),
	(4, 2, 1, _binary '', 'Samsung Galaxy S7 Tablet_1.jpeg', 'image/jpeg', 0, 'postman2'),
	(5, 3, 0, _binary '', 'Pedigree Dog Food_0.jpeg', 'image/jpeg', 0, 'postman'),
	(6, 3, 1, _binary '', 'Pedigree Dog Food_1.jpeg', 'image/jpeg', 0, 'postman2'),
	(7, 4, 0, _binary '', 'Protein Powder_0.jpeg', 'image/jpeg', 0, 'postman'),
	(8, 4, 1, _binary '', 'Protein Powder_1.jpeg', 'image/jpeg', 0, 'postman2'),
	(9, 5, 0, _binary '', 'Nike Flex Experience Run 10_0.jpeg', 'image/jpeg', 0, 'postman'),
	(10, 5, 1, _binary '', 'Nike Flex Experience Run 10_1.jpeg', 'image/jpeg', 0, 'postman2'),
	(11, 6, 0, _binary '', 'Biscuits_0.jpeg', 'image/jpeg', 0, 'postman'),
	(12, 6, 1, _binary '', 'Biscuits_1.jpeg', 'image/jpeg', 0, 'postman2'),
	(13, 7, 0, _binary '', 'Elden Ring account_0.jpeg', 'image/jpeg', 0, 'postman'),
	(14, 7, 1, _binary '', 'Elden Ring account_1.jpeg', 'image/jpeg', 0, 'postman2'),
	(15, 8, 0, _binary '', 'Chemistry A levels TYS_0.jpeg', 'image/jpeg', 0, 'postman'),
	(16, 8, 1, _binary '', 'Chemistry A levels TYS_1.jpeg', 'image/jpeg', 0, 'postman2'),
	(17, 10, 0, _binary '', 'CS2109 Notes_0.jpeg', 'image/jpeg', 0, 'postman'),
	(18, 10, 1, _binary '', 'CS2109 Notes_1.jpeg', 'image/jpeg', 0, 'postman2');
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;

-- Dumping data for table swappee.preferred_cat: ~11 rows (approximately)
/*!40000 ALTER TABLE `preferred_cat` DISABLE KEYS */;
REPLACE INTO `preferred_cat` (`item_id`, `category`) VALUES
	(1, 'School'),
	(2, 'School'),
	(2, 'Mobile'),
	(2, 'Tablets'),
	(3, 'Pets'),
	(3, 'Clothing'),
	(4, 'Food'),
	(5, 'Clothing'),
	(6, 'School'),
	(7, 'Games'),
	(8, 'School'),
	(10, 'School');
/*!40000 ALTER TABLE `preferred_cat` ENABLE KEYS */;

-- Dumping data for table swappee.preferred_item: ~16 rows (approximately)
/*!40000 ALTER TABLE `preferred_item` DISABLE KEYS */;
REPLACE INTO `preferred_item` (`item_id`, `name`, `category`, `brand`, `new`) VALUES
	(1, '1', '1', '1', b'1'),
	(1, '2', '2', '2', b'1'),
	(2, '1', '1', '1', b'1'),
	(2, '2', '2', '2', b'1'),
	(3, '1', '1', '1', b'1'),
	(3, '2', '2', '2', b'1'),
	(4, '1', '1', '1', b'1'),
	(4, '2', '2', '2', b'1'),
	(5, '1', '1', '1', b'1'),
	(5, '2', '2', '2', b'1'),
	(6, '1', '1', '1', b'1'),
	(6, '2', '2', '2', b'1'),
	(7, '1', '1', '1', b'1'),
	(7, '2', '2', '2', b'1'),
	(8, '1', '1', '1', b'1'),
	(8, '2', '2', '2', b'1'),
	(10, '1', '1', '1', b'1'),
	(10, '2', '2', '2', b'1');
/*!40000 ALTER TABLE `preferred_item` ENABLE KEYS */;

-- Dumping data for table swappee.request: ~0 rows (approximately)
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;

-- Dumping data for table swappee.review: ~0 rows (approximately)
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;

-- Dumping data for table swappee.user: ~5 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `avatar`, `phone`, `email_only`, `role`, `score`, `total_traded`, `version`, `created_date`, `last_modified_date`, `deleted`) VALUES
	(3, 'Marcus', 'Tang', 'master_marc99', '$2a$10$oSJJSBcrmJ3wHytaeIYkie0x9US0HtCKnRR7aoJTNYvy5C4wdSCqK', 'master_marc99@gmail.com', _binary '', 92321426, b'0', 'USER', 0, 0, 0, '2022-05-11 21:48:23', '2022-05-11 21:48:23', b'0'),
	(4, 'Taufiq', 'Bin Abdul Rahman', 'tauple', '$2a$10$2mi2u4.Y47g9ypDOYzU7g.kPCZhB60rCoyEmw.tZoYSViZPG931Nq', 'tauple@gmail.com', _binary '', 86453211, b'0', 'USER', 0, 0, 0, '2022-05-11 21:49:54', '2022-05-11 21:49:54', b'0'),
	(5, 'Yan Han', 'Tay', 'tayyantay', '$2a$10$c8oHhqsXuBxfAzR5vlvPsuO0mdHTBqeD4VT2LJqbxspEbUgvV4igC', 'tayyantay@gmail.com', _binary '', 91234526, b'0', 'USER', 0, 0, 0, '2022-05-11 21:54:34', '2022-05-11 21:54:34', b'0'),
	(9, 'Yu Zher', 'Lim', 'lyuzher', '$2a$10$O/Mv9ab41ALcp194hXOgQu7cUPqoqh9Sh9m2KeUCx/xqDDb5/juq2', 'lyuzher@gmail.com', _binary '', 93234526, b'0', 'USER', 0, 0, 0, '2022-05-11 21:55:50', '2022-05-11 21:55:21', b'0'),
	(10, 'Amanda', 'Goh', 'agoh', '$2a$10$PVa3OGMxNfP3XRjuJImdhe4gobTZK9WjJ1/2CeuGQO9a/SqilSWFe', 'agoh@gmail.com', _binary '', 91356781, b'0', 'USER', 0, 0, 0, '2022-05-11 21:58:24', '2022-05-11 21:58:24', b'0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
