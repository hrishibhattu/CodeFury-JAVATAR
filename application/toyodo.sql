-- -------------------------------------------------------------
-- TablePlus 4.2.0(388)
--
-- https://tableplus.com/
--
-- Database: toyodo
-- Generation Time: 2021-09-26 14:38:07.7700
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `company_name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `gst_number` varchar(15) NOT NULL,
  PRIMARY KEY (`company_name`,`gst_number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gst_number` varchar(15) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `pincode` int NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `customer_login_credential`;
CREATE TABLE `customer_login_credential` (
  `customer_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `customer_order_invoice`;
CREATE TABLE `customer_order_invoice` (
  `customer_id` varchar(50) NOT NULL,
  `order_id` int NOT NULL,
  `invoice_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `invoice_id` int NOT NULL AUTO_INCREMENT,
  `invoice_date` date NOT NULL,
  `order_datetime` datetime NOT NULL,
  `customer_id` varchar(50) NOT NULL,
  `gst` double NOT NULL,
  `type_of_gst` varchar(11) NOT NULL,
  `total_gst_amount` double NOT NULL,
  `total_invoice_value` double NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`invoice_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11022027 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `last_login_datetime`;
CREATE TABLE `last_login_datetime` (
  `login_id` int NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `last_login_details`;
CREATE TABLE `last_login_details` (
  `login_id` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `logintime` datetime NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_date` date NOT NULL,
  `order_datetime` datetime NOT NULL,
  `customer_id` varchar(50) CHARACTER SET latin1 NOT NULL,
  `total_order_value` double NOT NULL,
  `shipping_cost` double NOT NULL,
  `shipping_agency` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=35800016 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `order_product_util`;
CREATE TABLE `order_product_util` (
  `order_id` int DEFAULT NULL,
  `product_id` varchar(10) CHARACTER SET utf8mb4 DEFAULT NULL,
  `quantity` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `product_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `category` varchar(50) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `quote`;
CREATE TABLE `quote` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;

INSERT INTO `company` (`company_name`, `address`, `city`, `gst_number`) VALUES
('Amazon Seller Services Pvt. Ltd.', 'Sri Kanteshwara Nagar, Mahalakshmi Layout', 'Bengaluru', '29AAICA3918J1ZE'),
('Blue Dart Express', 'Opp Passport Office, Mahmoorganj', 'Varanasi', '27AAACB0446L2ZR');

INSERT INTO `customer` (`customer_id`, `name`, `gst_number`, `address`, `city`, `email`, `phone`, `pincode`) VALUES
('C00101', 'Raya Chawla', '22AAAAA0000A1Z5', '64, Janpath', 'Delhi', 'raya.chawla@gmail.com', '7878777701', 110001),
('C00102', 'Shally Roy', '07FFFFF0000B2Z9', '2nd Floor, Cosmos Avenue', 'Mumbai', 'roy.shally@gmail.com', '9967026046', 400601),
('C00103', 'Aryan Singh', '07AAECR2970C1Z8', 'DB-18, Salt Lake', 'Kolkata', 'aryan.singh@gmail.com', '6012224783', 700064);

INSERT INTO `customer_login_credential` (`customer_id`, `name`, `password`) VALUES
('C00101', 'Raya Chawla', 'qwert'),
('C00102', 'Shally Roy', 'asdfg'),
('C00103', 'Aryan Singh', 'zxcvb');

INSERT INTO `customer_order_invoice` (`customer_id`, `order_id`, `invoice_id`) VALUES
('C00101', 35800001, 11022002),
('C00101', 35800005, 11022018),
('C00101', 35800007, 11022021),
('C00101', 35800008, 11022024),
('C00102', 35800002, 11022001),
('C00102', 35800003, 11022019),
('C00102', 35800004, 11022020),
('C00102', 35800006, 11022022),
('C00102', 35800009, 11022023);

INSERT INTO `employee` (`employee_id`, `name`, `password`) VALUES
('E00201', 'Reema Shetty', 'mnbvc'),
('E00202', 'Mayank Roy', 'lkjhg');

INSERT INTO `invoice` (`invoice_id`, `invoice_date`, `order_datetime`, `customer_id`, `gst`, `type_of_gst`, `total_gst_amount`, `total_invoice_value`, `status`) VALUES
(11022001, '2021-09-23', '2021-09-23 01:00:00', 'C00102', 32, 'inter-state', 2424, 43443, 'Pending'),
(11022002, '2021-09-24', '2021-09-23 12:14:08', 'C00101', 12.9, 'inter-state', 5412.9, 5641.9, 'Pending'),
(11022018, '2021-09-24', '2021-09-23 22:53:40', 'C00101', 1.29, 'inter-state', 25.98, 191.38, 'Pending'),
(11022019, '2021-09-24', '2021-09-23 22:53:43', 'C00102', 1.29, 'inter-state', 25.98, 191.38, 'Pending'),
(11022020, '2021-09-24', '2021-09-22 23:06:40', 'C00102', 1.29, 'inter-state', 25.98, 191.38, 'Approved'),
(11022021, '2021-09-22', '2021-09-21 20:18:19', 'C00101', 18.2, 'inter-state', 44.18, 1900.5800000000002, 'Approved'),
(11022022, '2021-09-25', '2021-09-24 22:38:25', 'C00102', 1.29, 'inter-state', 25.98, 191.38, 'Pending'),
(11022023, '2021-09-25', '2021-09-24 22:39:18', 'C00102', 1.29, 'inter-state', 954.98, 1120.38, 'Pending'),
(11022024, '2021-09-26', '2021-09-25 12:01:46', 'C00101', 1.29, 'inter-state', 25.98, 191.38, 'Pending'),
(11022025, '2021-09-26', '2021-09-25 23:30:35', 'C00101', 24.69, 'inter-state', 564.69, 3033.69, 'Pending'),
(11022026, '2021-09-26', '2021-09-25 23:41:31', 'C00101', 24.69, 'inter-state', 564.69, 3033.69, 'Pending');

INSERT INTO `last_login_details` (`login_id`, `logintime`) VALUES
('E00201', '2021-09-26 13:31:44');

INSERT INTO `order` (`order_id`, `order_date`, `order_datetime`, `customer_id`, `total_order_value`, `shipping_cost`, `shipping_agency`, `status`) VALUES
(35800001, '2021-09-22', '2021-09-22 00:00:11', 'C00101', 52000, 150, 'fsgt', 'Pending'),
(35800002, '2021-09-22', '2021-09-23 03:18:00', 'C00102', 542000, 150, 'wryhert', 'Approved'),
(35800003, '2021-09-24', '2021-09-23 22:53:43', 'C00102', 2892.4, 36.4, 'Blue Dart', 'Pending'),
(35800004, '2021-09-23', '2021-09-22 23:06:40', 'C00102', 7572.4, 36.4, 'fvtg', 'Approved'),
(35800005, '2021-09-21', '2021-09-21 20:18:19', 'C00101', 6923.4, 36.4, 'Amazon', 'Approved'),
(35800006, '2021-09-24', '2021-09-24 22:38:25', 'C00102', 2763.4, 36.4, 'AMazon', 'Pending'),
(35800007, '2021-09-24', '2021-09-24 22:39:18', 'C00101', 98003.4, 36.4, 'Amazon', 'Pending'),
(35800008, '2021-09-24', '2021-09-25 12:01:46', 'C00101', 2763.4, 36.4, 'asdf', 'Pending'),
(35800009, '2021-09-24', '2021-09-25 12:01:46', 'C00102', 2763.4, 36.4, 'asdf', 'Pending'),
(35800010, '2021-09-23', '2021-09-25 22:36:31', 'C00101', 934802, 0, 'Blue Dart', 'Pending'),
(35800011, '2021-09-06', '2021-09-25 23:27:17', 'C00101', 233283, 0, 'fewgtaw', 'Pending');

INSERT INTO `order_product_util` (`order_id`, `product_id`, `quantity`) VALUES
(35800001, 'P00110', 2),
(35800002, 'P00110', 3),
(35800003, 'P00110', 4),
(35800004, 'P00110', 5),
(35800005, 'P00110', 6),
(35800006, 'P00110', 4),
(35800007, 'P00110', 44),
(35800008, 'P00110', 3),
(35800009, 'P00110', 5),
(35800010, 'P00110', 6),
(35800011, 'P00110', 6),
(35800001, 'P00105', 4);

INSERT INTO `products` (`product_id`, `name`, `price`, `category`) VALUES
('P00101', 'Sony MHC V82D', 54000, 'Level 3'),
('P00102', 'Apple 13\'\' MacBook', 92900, 'Level 3'),
('P00103', 'Baseball Tricot Track Suit', 2469, 'Level 2'),
('P00104', 'Popcorn Kernels', 129, 'Level 1'),
('P00105', 'Allen Solly Navy Suit', 6900, 'Level 2'),
('P00106', 'Denim Coat', 1820, 'Level 2'),
('P00108', 'Bottle', 400, 'Level 2'),
('P00110', 'Mouse', 800, 'Level 2'),
('P00111', 'HDMI Monitor', 12000, 'Level 3'),
('P00112', 'Speaker system', 12000, 'Level 3'),
('P00113', '20L water', 400, 'Level 1'),
('P00114', 'Bitter Gourd - 1Pc', 100, 'Level 1');



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;