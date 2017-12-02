-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cineevolution
-- ------------------------------------------------------
-- Server version	5.5.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cartelera`
--

DROP TABLE IF EXISTS `cartelera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartelera` (
  `idcartelera` int(11) NOT NULL AUTO_INCREMENT,
  `fechaestreno` date NOT NULL,
  `fechafinal` date NOT NULL,
  PRIMARY KEY (`idcartelera`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartelera`
--

LOCK TABLES `cartelera` WRITE;
/*!40000 ALTER TABLE `cartelera` DISABLE KEYS */;
INSERT INTO `cartelera` VALUES (5,'2017-12-20','2017-12-30'),(6,'2017-11-20','2017-01-01'),(7,'2018-01-02','2017-12-08'),(8,'2017-12-01','2017-12-13');
/*!40000 ALTER TABLE `cartelera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartelera_pelicula`
--

DROP TABLE IF EXISTS `cartelera_pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartelera_pelicula` (
  `idcartelera` int(11) NOT NULL,
  `idpelicula` int(11) NOT NULL,
  PRIMARY KEY (`idcartelera`,`idpelicula`),
  KEY `fk_cartelera_has_pelicula_pelicula1_idx` (`idpelicula`),
  KEY `fk_cartelera_has_pelicula_cartelera1_idx` (`idcartelera`),
  CONSTRAINT `fk_cartelera_has_pelicula_cartelera1` FOREIGN KEY (`idcartelera`) REFERENCES `cartelera` (`idcartelera`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cartelera_has_pelicula_pelicula1` FOREIGN KEY (`idpelicula`) REFERENCES `pelicula` (`idpelicula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartelera_pelicula`
--

LOCK TABLES `cartelera_pelicula` WRITE;
/*!40000 ALTER TABLE `cartelera_pelicula` DISABLE KEYS */;
INSERT INTO `cartelera_pelicula` VALUES (5,1),(6,1),(8,1);
/*!40000 ALTER TABLE `cartelera_pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clasificacion`
--

DROP TABLE IF EXISTS `clasificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clasificacion` (
  `idclasificacion` int(11) NOT NULL AUTO_INCREMENT,
  `clasificacion` char(10) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idclasificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificacion`
--

LOCK TABLES `clasificacion` WRITE;
/*!40000 ALTER TABLE `clasificacion` DISABLE KEYS */;
INSERT INTO `clasificacion` VALUES (1,'PG13');
/*!40000 ALTER TABLE `clasificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `codempleado` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `nombreempleado` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `apellidosempleado` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `direccion` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `telefono` char(9) COLLATE latin1_spanish_ci DEFAULT NULL,
  `correo` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`codempleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('0606-1998-01001','Marcio','Ochoa','Col. Monte Carlos','9687-3048','marciomartinez500@gmail.com'),('0607-1998-01136','Steven','Lezama','B° Suyapa','9547-5259','slezamac@hotmail.com');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `idfactura` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `cantidadboleto` int(11) NOT NULL,
  `precioboleto` decimal(18,2) NOT NULL,
  `idpelicula` int(11) NOT NULL,
  `idtecnologia` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idfactura`),
  KEY `fk_factura_pelicula1_idx` (`idpelicula`),
  KEY `fk_factura_tecnologia1_idx` (`idtecnologia`),
  KEY `fk_factura_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_factura_pelicula1` FOREIGN KEY (`idpelicula`) REFERENCES `pelicula` (`idpelicula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_tecnologia1` FOREIGN KEY (`idtecnologia`) REFERENCES `tecnologia` (`idtecnologia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (2,'2017-12-02',7,3.00,1,1,12),(3,'2017-12-02',6,100.00,1,3,12),(4,'2017-12-02',9,5.00,1,3,12),(5,'2017-12-02',6,7.00,1,3,12),(6,'2017-12-02',7,104.00,1,3,12);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genero` (
  `idgenero` int(11) NOT NULL AUTO_INCREMENT,
  `generopelicula` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idgenero`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'Miedo'),(2,'Comedia');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horario` (
  `idhorario` int(11) NOT NULL AUTO_INCREMENT,
  `horariopelicula` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `idsala` int(11) NOT NULL,
  PRIMARY KEY (`idhorario`),
  KEY `fk_horario_sala1_idx` (`idsala`),
  CONSTRAINT `fk_horario_sala1` FOREIGN KEY (`idsala`) REFERENCES `sala` (`idsala`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` VALUES (1,'5',1),(2,'2:00',1);
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logauditoria`
--

DROP TABLE IF EXISTS `logauditoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logauditoria` (
  `idlogauditoria` int(11) NOT NULL AUTO_INCREMENT,
  `accion` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `logauditoriacol` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idlogauditoria`),
  KEY `fk_logauditoria_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_logauditoria_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logauditoria`
--

LOCK TABLES `logauditoria` WRITE;
/*!40000 ALTER TABLE `logauditoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `logauditoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pelicula` (
  `idpelicula` int(11) NOT NULL AUTO_INCREMENT,
  `nombrepelicula` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `sinopsis` varchar(400) COLLATE latin1_spanish_ci NOT NULL,
  `lanzamiento` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `duracion` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `imagenpelicula` blob,
  `url` varchar(300) COLLATE latin1_spanish_ci DEFAULT NULL,
  `idgenero` int(11) NOT NULL,
  `idclasificacion` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idpelicula`),
  KEY `fk_pelicula_genero1_idx` (`idgenero`),
  KEY `fk_pelicula_clasificacion1_idx` (`idclasificacion`),
  KEY `fk_pelicula_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_pelicula_genero1` FOREIGN KEY (`idgenero`) REFERENCES `genero` (`idgenero`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pelicula_clasificacion1` FOREIGN KEY (`idclasificacion`) REFERENCES `clasificacion` (`idclasificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pelicula_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula`
--

LOCK TABLES `pelicula` WRITE;
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
INSERT INTO `pelicula` VALUES (1,'Fast and Furious 9','dsad','das','3',NULL,'sads',1,1,12);
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula_horario`
--

DROP TABLE IF EXISTS `pelicula_horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pelicula_horario` (
  `idpelicula` int(11) NOT NULL,
  `idhorario` int(11) NOT NULL,
  PRIMARY KEY (`idpelicula`,`idhorario`),
  KEY `fk_pelicula_has_horario_horario1_idx` (`idhorario`),
  KEY `fk_pelicula_has_horario_pelicula1_idx` (`idpelicula`),
  CONSTRAINT `fk_pelicula_has_horario_pelicula1` FOREIGN KEY (`idpelicula`) REFERENCES `pelicula` (`idpelicula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pelicula_has_horario_horario1` FOREIGN KEY (`idhorario`) REFERENCES `horario` (`idhorario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula_horario`
--

LOCK TABLES `pelicula_horario` WRITE;
/*!40000 ALTER TABLE `pelicula_horario` DISABLE KEYS */;
INSERT INTO `pelicula_horario` VALUES (1,2);
/*!40000 ALTER TABLE `pelicula_horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula_tecnologia`
--

DROP TABLE IF EXISTS `pelicula_tecnologia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pelicula_tecnologia` (
  `idpelicula` int(11) NOT NULL,
  `idtecnologia` int(11) NOT NULL,
  `precio` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`idpelicula`,`idtecnologia`),
  KEY `fk_pelicula_has_tecnologia_tecnologia1_idx` (`idtecnologia`),
  KEY `fk_pelicula_has_tecnologia_pelicula1_idx` (`idpelicula`),
  CONSTRAINT `fk_pelicula_has_tecnologia_pelicula1` FOREIGN KEY (`idpelicula`) REFERENCES `pelicula` (`idpelicula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pelicula_has_tecnologia_tecnologia1` FOREIGN KEY (`idtecnologia`) REFERENCES `tecnologia` (`idtecnologia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula_tecnologia`
--

LOCK TABLES `pelicula_tecnologia` WRITE;
/*!40000 ALTER TABLE `pelicula_tecnologia` DISABLE KEYS */;
INSERT INTO `pelicula_tecnologia` VALUES (1,1,140.00),(1,3,150.00);
/*!40000 ALTER TABLE `pelicula_tecnologia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sala` (
  `idsala` int(11) NOT NULL AUTO_INCREMENT,
  `descripcionsala` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `capacidadasientos` char(4) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idsala`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (1,'Sala 1','50');
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnologia`
--

DROP TABLE IF EXISTS `tecnologia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tecnologia` (
  `idtecnologia` int(11) NOT NULL AUTO_INCREMENT,
  `tipotecnologia` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idtecnologia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnologia`
--

LOCK TABLES `tecnologia` WRITE;
/*!40000 ALTER TABLE `tecnologia` DISABLE KEYS */;
INSERT INTO `tecnologia` VALUES (1,'2D'),(3,'3D');
/*!40000 ALTER TABLE `tecnologia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipousuario` (
  `idtipousuario` int(11) NOT NULL AUTO_INCREMENT,
  `tipousuario` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idtipousuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` VALUES (1,'Administrador'),(2,'Usuario');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreusuario` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `contrasena` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `idtipousuario` int(11) NOT NULL,
  `codempleado` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_usuario_tipousuario_idx` (`idtipousuario`),
  KEY `fk_usuario_empleado1_idx` (`codempleado`),
  CONSTRAINT `fk_usuario_tipousuario` FOREIGN KEY (`idtipousuario`) REFERENCES `tipousuario` (`idtipousuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_empleado1` FOREIGN KEY (`codempleado`) REFERENCES `empleado` (`codempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (12,'MarcioM98','hola',1,'0606-1998-01001');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-02  6:37:03
