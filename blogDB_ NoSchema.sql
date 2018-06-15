-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `descrizione` text,
  `immagine` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'sport','tutti gli sport del mondo',NULL),(2,'cucina','Tutti i piatti piu\' prelibati',NULL),(3,'flora e fauna','piante e animali dal mondo',NULL),(4,'moda','tutti alla moda',NULL),(5,'Skateboarding','',NULL),(6,'Ammaccabanane','Scrubs',NULL),(8,'Libri','Libri di ogni genere',NULL),(9,'Rose','Ma senza le spine!',NULL);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commento`
--

DROP TABLE IF EXISTS `commento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `commento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_utente` bigint(20) DEFAULT NULL,
  `data_inserimento` datetime NOT NULL,
  `testo` text NOT NULL,
  `data_risposta` datetime DEFAULT NULL,
  `risposta` text,
  `visibile` enum('true','false','unchecked') NOT NULL,
  `id_post` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_post` (`id_post`),
  KEY `id_utente` (`id_utente`),
  CONSTRAINT `commento_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `commento_ibfk_2` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commento`
--

LOCK TABLES `commento` WRITE;
/*!40000 ALTER TABLE `commento` DISABLE KEYS */;
INSERT INTO `commento` VALUES (1,1,'2018-05-01 09:00:00','il tofu e\' buonissimo!','2018-04-23 12:00:00','e\' proprio vero!','true',2),(2,1,'2018-05-02 05:00:23','Questo e\' un corommento al mentolo!',NULL,NULL,'true',3),(3,1,'2018-05-31 00:00:00','Delizioso Ramen!','2018-06-01 00:00:00','e\' proprio vero!','true',2),(4,1,'2018-05-31 00:00:00','Deliziose Tagliatelle!',NULL,NULL,'true',2),(5,1,'2018-06-02 00:00:00','testo di prova',NULL,NULL,'unchecked',2),(6,1,'2018-06-02 00:00:00','evviva il Tofu!',NULL,NULL,'unchecked',2),(10,46,'2018-06-07 00:00:00','Questo e\' un commento al lampone!',NULL,NULL,'true',2);
/*!40000 ALTER TABLE `commento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_categoria` int(11) DEFAULT NULL,
  `id_utente` bigint(20) DEFAULT NULL,
  `titolo` text NOT NULL,
  `descrizione` text NOT NULL,
  `data` datetime NOT NULL,
  `visibile` tinyint(1) NOT NULL,
  `visite` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_categoria` (`id_categoria`),
  KEY `id_utente` (`id_utente`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,1,1,'Lo scudetto va al Fidelis Andria','<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque pellentesque libero eu dictum posuere. Suspendisse dignissim est quis velit porttitor interdum. In non laoreet arcu, id maximus dui. Fusce est neque, posuere et arcu eu, aliquam accumsan augue. Morbi congue justo magna, ut malesuada arcu pretium ut. Suspendisse sed porttitor est. Sed ut orci semper, rutrum augue ut, auctor purus. Pellentesque erat mauris, malesuada at sodales blandit, sodales eget mi. Nunc eu risus at urna mattis aliquam. Ut viverra, massa non vehicula tincidunt, nisl purus egestas ex, eu ornare nisi lectus in quam. Fusce elementum et neque eu consequat. Aenean et justo ante. Pellentesque porttitor convallis nisi, id dictum ex consectetur id. Vestibulum eu semper ipsum, vitae porta augue.</p>','2017-05-07 00:00:00',0,125),(2,2,1,'tofu','<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque pellentesque libero eu dictum posuere. Suspendisse dignissim est quis velit porttitor interdum. In non laoreet arcu, id maximus dui. Fusce est neque, posuere et arcu eu, aliquam accumsan augue. Morbi congue justo magna, ut malesuada arcu pretium ut. Suspendisse sed porttitor est. Sed ut orci semper, rutrum augue ut, auctor purus. Pellentesque erat mauris, malesuada at sodales blandit, sodales eget mi. Nunc eu risus at urna mattis aliquam. Ut viverra, massa non vehicula tincidunt, nisl purus egestas ex, eu ornare nisi lectus in quam. Fusce elementum et neque eu consequat. Aenean et justo ante. Pellentesque porttitor convallis nisi, id dictum ex consectetur id. Vestibulum eu semper ipsum, vitae porta augue.</p>','2018-05-07 10:06:00',1,60),(3,3,1,'Le gerbere fioriscono','bellissime le gerbere fiorite','2018-05-07 06:10:00',1,4),(4,4,1,'I risvoltini','Risvoltini per tutti i gusti. Peggio degli involtini','2018-05-04 00:00:00',1,73),(5,1,1,'ciambella','Ciambelle giganti!','2018-05-20 00:00:00',1,125),(6,4,1,'titolo','post','2018-05-27 00:00:00',1,0),(13,1,1,'calcio','A sorpresa, l\'Italia vince i mondiali 2018!','2018-05-20 00:00:00',1,126),(14,1,1,'calcio','Francia vince i mondiali 2018!','2018-05-20 00:00:00',1,125),(16,1,1,'calcio','Spagna vince i mondiali 2018!','2018-05-20 00:00:00',1,125),(17,1,1,'calcio','Germania vince i mondiali 2018!','2018-05-20 00:00:00',1,125),(18,1,1,'calcio','Repubblica Ceca vince i mondiali 2018!','2018-05-20 00:00:00',1,125),(19,3,1,'Gerani','#gerani bellissimi gerani#inFiore#colorati #verdi e#blu','2018-05-30 00:00:00',1,0),(20,4,1,'hubs','#giacche stupende#giacche di tutti i #tipi e ancora #tipi','2018-05-30 00:00:00',1,0),(21,3,1,'I Pioppi','i #pioppi sono bellissimi in questa stagione dell\'#anno !','2018-06-02 00:00:00',1,0),(22,5,1,'Rodney Mullen','Best freestyle skater of all times!','2018-06-07 00:00:00',1,0),(23,1,1,'Italiano batte Djokovich','Incredibile impresa del campione nostrano!','2018-06-08 00:00:00',1,0),(24,1,1,'calcio','calcio e #calcio tag per  il #calcio','2018-06-08 00:00:00',1,0),(25,1,1,'calcio 2 la vendemmia','calcio e #calcio tag per  il #calcio','2018-06-08 00:00:00',1,0),(26,1,1,'calcio 3','calcio e #calcio tag per  il #calcio','2018-06-08 00:00:00',1,0),(27,1,1,'calcio 4 ebbasta!','calcio e #calcio tag per  il #calcio','2018-06-08 00:00:00',1,0),(28,8,1,'manuale Java','manuale di base per java','2018-06-12 21:42:00',1,3);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_tag`
--

DROP TABLE IF EXISTS `post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `post_tag` (
  `id_post` int(11) NOT NULL,
  `id_tag` int(11) NOT NULL,
  PRIMARY KEY (`id_tag`,`id_post`),
  KEY `id_post` (`id_post`),
  CONSTRAINT `post_tag_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_tag_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_tag`
--

LOCK TABLES `post_tag` WRITE;
/*!40000 ALTER TABLE `post_tag` DISABLE KEYS */;
INSERT INTO `post_tag` VALUES (2,2),(2,8),(3,1),(3,2),(4,4),(4,6),(13,4),(14,4),(17,19),(18,19),(19,20),(19,21),(19,22),(19,23),(19,24),(20,25),(20,26),(21,27),(21,28),(24,4),(25,4),(26,4),(27,4);
/*!40000 ALTER TABLE `post_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruolo`
--

DROP TABLE IF EXISTS `ruolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ruolo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruolo`
--

LOCK TABLES `ruolo` WRITE;
/*!40000 ALTER TABLE `ruolo` DISABLE KEYS */;
INSERT INTO `ruolo` VALUES (1,'administrator'),(2,'editor'),(3,'utente');
/*!40000 ALTER TABLE `ruolo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'vegetariano'),(2,'vegano'),(3,'running'),(4,'calcio'),(5,'nuoto'),(6,'tennis'),(7,'pioppo'),(8,'leopardo'),(9,'tulipano'),(10,'gerbere'),(11,'fashion'),(12,'fiera di milano'),(16,'Volley'),(17,'Karting'),(18,'calcetto'),(19,'Mondiali'),(20,'gerani'),(21,'inFiore'),(22,'colorati'),(23,'verdi'),(24,'blu'),(25,'giacche'),(26,'tipi'),(27,'pioppi'),(28,'anno');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `utente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `password` text,
  `is_active` tinyint(1) NOT NULL,
  `failed_access_attempts` int(11) NOT NULL,
  `is_banned` tinyint(1) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_last_access` datetime DEFAULT NULL,
  `id_ruolo` bigint(20) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `id_ruolo` (`id_ruolo`),
  CONSTRAINT `utente_ibfk_1` FOREIGN KEY (`id_ruolo`) REFERENCES `ruolo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'admin@admin.com','admin',1,0,0,'2018-05-01 09:25:00','2018-05-14 07:08:00',1,'/blog/profile-pictures/1_images.png'),(15,'user7@user.com','user',1,0,0,'2018-05-27 00:00:00','2018-05-27 00:00:00',3,NULL),(16,'user8@user.com','user',1,0,0,'2018-05-18 00:00:00','2018-05-18 00:00:00',3,NULL),(17,'user9@user.com','user',1,0,1,'2018-05-27 00:00:00','2018-05-27 00:00:00',3,NULL),(19,'user10@user.com','user',1,0,0,'2018-05-18 00:00:00','2018-05-18 00:00:00',3,NULL),(25,'user2@user.com','user',1,0,1,'2018-05-27 00:00:00','2018-05-27 00:00:00',3,NULL),(26,'user3@user.com','user',0,0,0,'2018-05-27 00:00:00','2018-05-27 00:00:00',3,NULL),(28,'user4@user.com','user',1,0,0,'2018-05-27 00:00:00','2018-05-27 00:00:00',3,NULL),(30,'user6@user.com','user',0,0,0,'2018-05-27 00:00:00','2018-05-27 00:00:00',3,NULL),(32,'user5@user.com','user',1,0,0,'2018-05-27 00:00:00','2018-05-27 00:00:00',3,NULL),(34,'user11@user.com','user',0,0,0,'2018-05-27 00:00:00','2018-05-27 00:00:00',3,NULL),(35,'user12@user.com','user',1,0,0,'2018-05-27 00:00:00','2018-05-27 00:00:00',3,NULL),(46,'denis.coccodi@gmail.com','user',1,3,0,'2018-06-04 00:00:00','2018-06-04 00:00:00',3,NULL),(47,'seyriu@hotmail.com','user',1,0,0,'2018-06-04 00:00:00','2018-06-04 00:00:00',3,NULL),(48,'user100@user.com','user',1,0,0,'2018-06-04 00:00:00','2018-06-04 00:00:00',3,NULL),(49,'user101@user.com','user',1,0,0,'2018-06-04 00:00:00','2018-06-04 00:00:00',3,NULL),(50,'user102@user.com','user',0,0,0,'2018-06-04 00:00:00','2018-06-04 00:00:00',3,NULL),(51,'userNoFacesConverter@user.com','user',0,0,0,'2018-05-18 00:00:00','2018-05-18 00:00:00',3,NULL),(52,'userDateTime@user.com','user',0,0,0,'2018-05-18 11:20:00','2018-05-18 06:02:25',3,NULL);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-15 13:17:43
