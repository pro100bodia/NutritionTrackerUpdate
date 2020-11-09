DROP DATABASE nutrition;
CREATE DATABASE nutrition character SET UTF8; 

USE nutrition;

-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 20, 2019 at 04:07 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `nutrition`
--

-- --------------------------------------------------------

--
-- Table structure for table `coefs`
--

CREATE TABLE IF NOT EXISTS `coefs` (
  `id` int(11) NOT NULL,
  `men` double NOT NULL,
  `women` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `coefs`
--

INSERT INTO `coefs` (`id`, `men`, `women`) VALUES
(0, 88.3962, 447.593),
(1, 13.397, 9.247),
(2, 4.799, 3.098),
(3, 5.677, 4.33);

-- --------------------------------------------------------

--
-- Table structure for table `deflection_record`
--

CREATE TABLE IF NOT EXISTS `deflection_record` (
  `id` bigint(20) NOT NULL,
  `deflection_records` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `calories` double NOT NULL,
  `protein` double NOT NULL,
  `fats` double NOT NULL,
  `carbohydrates` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqxll0c8k08rvn3tmd1a1pkain` (`deflection_records`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE `deflection_record`:
--   `deflection_records`
--       `user` -> `id`
--

--
-- Dumping data for table `deflection_record`
--

INSERT INTO `deflection_record` (`id`, `deflection_records`, `date`, `calories`, `protein`, `fats`, `carbohydrates`) VALUES
(1, 1, '2019-06-15', -200, -200, -200, -200),
(2, 1, '2019-06-14', -100, -100, -100, -100),
(3, 1, '2019-06-13', 0, 0, 0, 0),
(4, 2, '2019-06-15', -200, -300, -400, -500),
(5, 2, '2019-06-14', -100, -100, -100, -100),
(6, 2, '2019-06-13', -54, -17, -380, -40),
(7, 3, '2019-06-15', 200, 500, -450, -230),
(8, 3, '2019-06-14', 200, -600, 300, 120),
(9, 3, '2019-06-13', 90, 340, -900, -480),
(10, 4, '2019-06-15', 250, 900, -20, -120),
(11, 4, '2019-06-14', -120, 500, 500, -130),
(12, 4, '2019-06-13', 120, 250, 320, 670),
(13, 5, '2019-06-13', 450, 880, -340, -190),
(14, 5, '2019-06-14', 150, 130, -120, -100),
(15, 5, '2019-06-15', -260, 250, -200, -420);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE IF NOT EXISTS `food` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` bigint(20) NOT NULL,
  `calories` bigint(20) NOT NULL,
  `protein` bigint(20) NOT NULL,
  `fats` bigint(20) NOT NULL,
  `carbohydrates` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `name`, `number`, `calories`, `protein`, `fats`, `carbohydrates`) VALUES
(0, 'pear', 100, 42, 0, 0, 11),
(1, 'cherry', 100, 52, 1, 1, 11),
(2, 'bird-cherry', 100, 50, 1, 0, 12),
(3, 'apple', 100, 45, 0, 0, 12),
(4, 'water melon', 100, 38, 1, 0, 11),
(5, 'banana', 100, 89, 2, 0, 22),
(6, 'sour cream 10%', 100, 115, 3, 10, 3),
(7, 'kefir not greasy', 100, 30, 3, 0, 4),
(8, 'mocarella', 100, 264, 21, 21, 1),
(9, 'cow`s milk', 100, 64, 3, 4, 5),
(10, 'cottage cheese', 100, 88, 18, 1, 2),
(11, 'holland chese', 100, 352, 26, 27, 1),
(12, 'gercules', 100, 305, 11, 6, 66),
(13, 'wheat bran', 100, 191, 15, 4, 34),
(14, 'wheat', 100, 301, 13, 3, 67),
(15, 'oatmeal', 100, 303, 11, 6, 65),
(16, 'semolina porridge', 100, 328, 10, 1, 73),
(17, 'rice', 100, 330, 7, 1, 77),
(18, 'buckwheat', 100, 335, 13, 3, 68),
(19, 'boiled chicken egg', 100, 144, 13, 11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `food_history`
--

CREATE TABLE IF NOT EXISTS `food_history` (
  `id` bigint(20) NOT NULL,
  `food_records` bigint(20) DEFAULT NULL,
  `foodId` bigint(20) DEFAULT NULL,
  `amount` double NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3908jhdq6gah71rlfobgj3cyq` (`food_records`),
  KEY `foodId` (`foodId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE `food_history`:
--   `foodId`
--       `food` -> `food_id`
--   `food_records`
--       `user` -> `id`
--

--
-- Dumping data for table `food_history`
--

INSERT INTO `food_history` (`id`, `food_records`, `foodId`, `amount`, `date`) VALUES
(1, 1, 19, 100, '2019-06-10'),
(2, 1, 10, 200, '2019-06-10'),
(3, 1, 11, 300, '2019-06-10'),
(4, 1, 12, 400, '2019-06-10'),
(5, 2, 18, 100, '2019-06-10'),
(6, 2, 15, 200, '2019-06-10'),
(7, 2, 12, 230, '2019-06-10'),
(8, 2, 8, 400, '2019-06-10'),
(9, 3, 5, 400, '2019-06-10'),
(10, 3, 17, 400, '2019-06-10'),
(11, 3, 11, 400, '2019-06-10'),
(12, 3, 12, 400, '2019-06-10'),
(13, 4, 7, 320, '2019-06-10'),
(14, 4, 2, 200, '2019-06-10'),
(15, 4, 7, 450, '2019-06-10'),
(16, 4, 14, 100, '2019-06-10'),
(17, 5, 10, 400, '2019-06-10'),
(18, 5, 7, 240, '2019-06-10'),
(19, 5, 5, 340, '2019-06-10'),
(20, 5, 2, 140, '2019-06-10');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(5),
(5),
(5);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `height` double NOT NULL,
  `weight` double NOT NULL,
  `life_style` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `role`, `birthdate`, `gender`, `height`, `weight`, `life_style`) VALUES
(1, 'user', 'password', 'USER', '2019-08-15', 'MALE', 24, 237, 'M'),
(2, 'doctor', 'doctor', 'DOCTOR', '2019-08-22', 'MALE', 23, 346, 'A'),
(3, 'bohdan', '1234', 'USER', '2000-12-04', 'MALE', 46, 236, 'M'),
(4, 'ohrip', 'pirho', 'USER', '2019-08-14', 'MALE', 236, 748, 'M'),
(5, 'not sample name', 'not sample password', 'USER', '1970-01-01', 'MALE', 111, 66.6, 'M'),
(6, 'Alexandra', '158,962,555,217,826,360,000', 'USER', '2000-09-21', 'FEMALE', 185.5, 64.3, 'H'),
(7, 'Dima', 'ooo|vosmiklasssnica|', 'USER', '2000-09-21', 'MALE', 185.5, 64.3, 'M'),
(8, 'Nick', 'lukin4you', 'USER', '2000-09-12', 'MALE', 180, 68.8, 'E'),
(9, 'Sasha', '$n`t2ch', 'USER', '2000-08-24', 'MALE', 178.9, 60.6, 'L'),
(10, 'Dr.House', 'URshipskinEd', 'DOCTOR', '2000-05-17', 'MALE', 189.9, 75, 'E'),
(11, 'Zaitseva', '1111', 'DOCTOR', '1997-05-14', 'FEMALE', 164.3, 45.5, 'A');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `deflection_record`
--
ALTER TABLE `deflection_record`
  ADD CONSTRAINT `FKqxll0c8k08rvn3tmd1a1pkain` FOREIGN KEY (`deflection_records`) REFERENCES `user` (`id`);

--
-- Constraints for table `food_history`
--
ALTER TABLE `food_history`
  ADD CONSTRAINT `food_history_ibfk_1` FOREIGN KEY (`foodId`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `FK3908jhdq6gah71rlfobgj3cyq` FOREIGN KEY (`food_records`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;