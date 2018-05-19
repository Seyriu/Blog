-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2018 at 05:23 PM
-- Server version: 5.7.21-log
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blog`
--
CREATE DATABASE IF NOT EXISTS `blog` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `blog`;

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `descrizione` text,
  `immagine` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`id`, `nome`, `descrizione`, `immagine`) VALUES
(1, 'sport', 'tutti gli sport del mondo', NULL),
(2, 'cucina', 'Tutti i piatti piu\' prelibati', NULL),
(3, 'flora e fauna', 'piante e animali dal mondo', NULL),
(4, 'moda', 'tutti alla moda', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `commento`
--

DROP TABLE IF EXISTS `commento`;
CREATE TABLE `commento` (
  `id` int(11) NOT NULL,
  `id_utente` bigint(20) NOT NULL,
  `data_inserimento` datetime NOT NULL,
  `testo` text NOT NULL,
  `data_risposta` datetime DEFAULT NULL,
  `risposta` text,
  `visibile` tinyint(1) NOT NULL,
  `id_post` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commento`
--

INSERT INTO `commento` (`id`, `id_utente`, `data_inserimento`, `testo`, `data_risposta`, `risposta`, `visibile`, `id_post`) VALUES
(1, 1, '2018-05-01 09:00:00', 'il tofu e\' buonissimo!', '2018-04-23 12:00:00', 'e\' proprio vero!', 1, 2),
(2, 1, '2018-05-02 05:00:23', 'Questo e\' un corommento al mentolo!', NULL, NULL, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `id_utente` bigint(20) DEFAULT NULL,
  `titolo` text NOT NULL,
  `descrizione` text NOT NULL,
  `data` datetime NOT NULL,
  `visibile` tinyint(1) NOT NULL,
  `visite` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `id_categoria`, `id_utente`, `titolo`, `descrizione`, `data`, `visibile`, `visite`) VALUES
(1, 1, 1, 'Lo scudetto va al Fidelis Andria', 'Annata speciale per il Fidelis Andria!', '2017-05-07 00:00:00', 0, 125),
(2, 2, 1, 'tofu', 'tofu... per tutti i piatti', '2018-05-07 11:15:19', 0, 43),
(3, 3, 1, 'Le gerbere fioriscono', 'bellissime le gerbere fiorite', '2018-05-07 10:29:37', 0, 0),
(4, 4, 1, 'I risvoltini', 'Risvoltini per tutti i gusti. Peggio degli involtini', '2018-05-04 17:12:00', 1, 72);

-- --------------------------------------------------------

--
-- Table structure for table `post_tag`
--

DROP TABLE IF EXISTS `post_tag`;
CREATE TABLE `post_tag` (
  `id_post` int(11) NOT NULL,
  `id_tag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post_tag`
--

INSERT INTO `post_tag` (`id_post`, `id_tag`) VALUES
(1, 4),
(2, 2),
(2, 8),
(3, 7),
(3, 10),
(3, 11),
(4, 11),
(4, 12);

-- --------------------------------------------------------

--
-- Table structure for table `ruolo`
--

DROP TABLE IF EXISTS `ruolo`;
CREATE TABLE `ruolo` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ruolo`
--

INSERT INTO `ruolo` (`id`, `nome`) VALUES
(1, 'administrators');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`id`, `nome`) VALUES
(1, 'vegetariano'),
(2, 'vegano'),
(3, 'running'),
(4, 'calcio'),
(5, 'nuoto'),
(6, 'tennis'),
(7, 'pioppo'),
(8, 'leopardo'),
(9, 'tulipano'),
(10, 'gerbere'),
(11, 'fashion'),
(12, 'fiera di milano'),
(16, 'Volley');

-- --------------------------------------------------------

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
CREATE TABLE `utente` (
  `id` bigint(20) NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` text NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `failed_access_attempts` int(11) NOT NULL,
  `is_banned` tinyint(1) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_last_access` datetime NOT NULL,
  `id_ruolo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utente`
--

INSERT INTO `utente` (`id`, `email`, `password`, `is_active`, `failed_access_attempts`, `is_banned`, `date_creation`, `date_last_access`, `id_ruolo`) VALUES
(1, 'admin@admin.com', 'admin', 1, 0, 0, '2018-05-01 09:25:00', '2018-05-14 07:08:00', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `commento`
--
ALTER TABLE `commento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_post` (`id_post`),
  ADD KEY `id_utente` (`id_utente`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`),
  ADD KEY `id_utente` (`id_utente`);

--
-- Indexes for table `post_tag`
--
ALTER TABLE `post_tag`
  ADD PRIMARY KEY (`id_tag`,`id_post`),
  ADD KEY `id_post` (`id_post`);

--
-- Indexes for table `ruolo`
--
ALTER TABLE `ruolo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_ruolo` (`id_ruolo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `commento`
--
ALTER TABLE `commento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `ruolo`
--
ALTER TABLE `ruolo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `utente`
--
ALTER TABLE `utente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commento`
--
ALTER TABLE `commento`
  ADD CONSTRAINT `commento_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `commento_ibfk_2` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `post_ibfk_2` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id`) ON DELETE NO ACTION;

--
-- Constraints for table `post_tag`
--
ALTER TABLE `post_tag`
  ADD CONSTRAINT `post_tag_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `post_tag_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `utente`
--
ALTER TABLE `utente`
  ADD CONSTRAINT `utente_ibfk_1` FOREIGN KEY (`id_ruolo`) REFERENCES `ruolo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
