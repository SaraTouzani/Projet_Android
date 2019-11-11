-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2018 at 11:25 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appels`
--

-- --------------------------------------------------------

--
-- Table structure for table `appel`
--

CREATE TABLE `appel` (
  `id` int(11) NOT NULL,
  `num` varchar(40) COLLATE utf8_bin NOT NULL,
  `duree` int(11) NOT NULL,
  `statut` int(11) NOT NULL,
  `date` varchar(40) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `appel`
--

INSERT INTO `appel` (`id`, `num`, `duree`, `statut`, `date`) VALUES
(10, '0522222', 0, 1, ' 2018-05-13 Ã  01:59:20 PM'),
(11, '0522222', 0, 1, ' 2018-05-13 Ã  02:02:54 PM'),
(12, '06123456', 0, 1, ' 2018-05-13 Ã  07:59:33 PM'),
(13, '06789123', 0, 1, ' 2018-05-14 Ã  09:24:32 PM');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appel`
--
ALTER TABLE `appel`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appel`
--
ALTER TABLE `appel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
