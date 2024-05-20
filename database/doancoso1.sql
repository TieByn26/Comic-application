-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2024 at 05:48 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doancoso1`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `idUser` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(255) NOT NULL,
  `codeAccess` int(11) NOT NULL,
  `authority` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categorycomics`
--

CREATE TABLE `categorycomics` (
  `idCategory` varchar(10) NOT NULL,
  `categoryInformation` varchar(255) NOT NULL,
  `descriptCategory` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chapter`
--

CREATE TABLE `chapter` (
  `idComics` varchar(10) NOT NULL,
  `chapter` int(11) NOT NULL,
  `linkImages` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `comicsinformation`
--

CREATE TABLE `comicsinformation` (
  `nameComics` varchar(255) NOT NULL,
  `idComics` varchar(10) NOT NULL,
  `author` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `avatarComcis` text NOT NULL,
  `numberOfChapter` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `historyreadcomic`
--

CREATE TABLE `historyreadcomic` (
  `idUser` int(11) NOT NULL,
  `idComics` varchar(10) NOT NULL,
  `chapter` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `managercategorycomics`
--

CREATE TABLE `managercategorycomics` (
  `idComics` varchar(10) NOT NULL,
  `idCategory` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `statistics`
--

CREATE TABLE `statistics` (
  `idComcis` varchar(10) NOT NULL,
  `allViews` int(11) NOT NULL,
  `latestUpdateDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `fullName` varchar(255) NOT NULL,
  `avatar` text NOT NULL,
  `experience` int(11) NOT NULL,
  `level` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`idUser`);

--
-- Indexes for table `categorycomics`
--
ALTER TABLE `categorycomics`
  ADD PRIMARY KEY (`idCategory`);

--
-- Indexes for table `chapter`
--
ALTER TABLE `chapter`
  ADD PRIMARY KEY (`idComics`,`chapter`);

--
-- Indexes for table `comicsinformation`
--
ALTER TABLE `comicsinformation`
  ADD PRIMARY KEY (`idComics`),
  ADD UNIQUE KEY `nameComics` (`nameComics`);

--
-- Indexes for table `historyreadcomic`
--
ALTER TABLE `historyreadcomic`
  ADD PRIMARY KEY (`idUser`,`idComics`),
  ADD KEY `idComics` (`idComics`);

--
-- Indexes for table `managercategorycomics`
--
ALTER TABLE `managercategorycomics`
  ADD PRIMARY KEY (`idComics`,`idCategory`),
  ADD KEY `idCategory` (`idCategory`);

--
-- Indexes for table `statistics`
--
ALTER TABLE `statistics`
  ADD PRIMARY KEY (`idComcis`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chapter`
--
ALTER TABLE `chapter`
  ADD CONSTRAINT `chapter_ibfk_1` FOREIGN KEY (`idComics`) REFERENCES `comicsinformation` (`idComics`);

--
-- Constraints for table `historyreadcomic`
--
ALTER TABLE `historyreadcomic`
  ADD CONSTRAINT `historyreadcomic_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
  ADD CONSTRAINT `historyreadcomic_ibfk_2` FOREIGN KEY (`idComics`) REFERENCES `comicsinformation` (`idComics`);

--
-- Constraints for table `managercategorycomics`
--
ALTER TABLE `managercategorycomics`
  ADD CONSTRAINT `managercategorycomics_ibfk_1` FOREIGN KEY (`idComics`) REFERENCES `comicsinformation` (`idComics`),
  ADD CONSTRAINT `managercategorycomics_ibfk_2` FOREIGN KEY (`idCategory`) REFERENCES `categorycomics` (`idCategory`);

--
-- Constraints for table `statistics`
--
ALTER TABLE `statistics`
  ADD CONSTRAINT `statistics_ibfk_1` FOREIGN KEY (`idComcis`) REFERENCES `comicsinformation` (`idComics`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `account` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
