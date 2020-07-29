-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: dbnw
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `aSeqno` int(11) NOT NULL AUTO_INCREMENT,
  `aName` varchar(45) DEFAULT NULL,
  `aImage` varchar(45) DEFAULT NULL,
  `aPNum` varchar(45) DEFAULT NULL,
  `aEmail` varchar(45) DEFAULT NULL,
  `aMemo` varchar(45) DEFAULT NULL,
  `aTag` varchar(45) DEFAULT '"0"',
  `aValidation` int(11) DEFAULT '1',
  `user_uId` varchar(45) NOT NULL,
  `user_uSeqno` int(11) DEFAULT NULL,
  PRIMARY KEY (`aSeqno`),
  KEY `fk_user_Id_idx` (`user_uId`)
) ENGINE=InnoDB AUTO_INCREMENT=601 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (523,'조세호','zo.jpg','01123446959','조세호@google.com','momo 조세호','3',1,'',2),(524,'임요한','im.jpg','01023446959','임요한@daum.net','momo 임요한','2',1,'',1),(525,'효정','hozung.jpg','01119593949','효정@jtbc.co.kr','momo 효정','5,7',1,'',1),(526,'유산슬','yu.jpg','01023446959','유산슬@google.com','momo 유산슬','3',1,'',1),(527,'아이린','Irean.jpg','01620394050','아이린@hanmail.com','momo 아이린','3,4,6',1,'',1),(528,'혜리','hery.jpg','01119593949','혜리@mbc.co.kr','momo 혜리','4,6',1,'',1),(529,'은비','eunbi.jpg','01619593949','은비@jtbc.co.kr','momo 은비','5,7',1,'',1),(530,'혜리','hery.jpg','01119593949','혜리@mbc.co.kr','momo 혜리','4,6',1,'',1),(531,'아이유','I.jpg','01123446959','아이유@mbc.co.kr','momo 아이유','4',1,'',1),(532,'혜리','hery.jpg','01119593949','혜리@mbc.co.kr','momo 혜리','4,6',1,'',1),(533,'문별','moon.jpg','01620394050','문별@hanmail.com','momo 문별','3,4,6',1,'',1),(534,'혜리','hery.jpg','01119593949','혜리@mbc.co.kr','momo 혜리','4,6',1,'',1),(535,'문별','moon.jpg','01620394050','문별@hanmail.com','momo 문별','3,4,6',1,'',1),(536,'은비','eunbi.jpg','01619593949','은비@jtbc.co.kr','momo 은비','5,7',1,'',1),(537,'홍진호','hong.jpg','01620394050','홍진호@hanmail.com','momo 홍진호','3,4,6',1,'',1),(538,'단비','danbi.jpg','01619593949','단비@jtbc.co.kr','momo 단비','5,7',1,'',1),(539,'홍진호','hong.jpg','01620394050','홍진호@hanmail.com','momo 홍진호','3,4,6',1,'',1),(540,'단비','danbi.jpg','01619593949','단비@jtbc.co.kr','momo 단비','5,7',1,'',1),(541,'이수근','e.jpg','01012346034','이수근@naver.com','momo 이수근','1',1,'',1),(542,'문별','moon.jpg','01620394050','문별@hanmail.com','momo 문별','3,4,6',1,'',1),(543,'유산슬','yu.jpg','01023446959','유산슬@google.com','momo 유산슬','3',1,'',1),(544,'조세호','zo.jpg','01123446959','조세호@google.com','momo 조세호','3',1,'',3),(545,'효정','hozung.jpg','01119593949','효정@jtbc.co.kr','momo 효정','5,7',1,'',3),(546,'이수근','e.jpg','01012346034','이수근@daum.net','momo 이수근','2',1,'',3),(547,'효정','hozung.jpg','01119593949','효정@jtbc.co.kr','momo 효정','5,7',1,'',3),(548,'단비','danbi.jpg','01119593949','단비@jtbc.co.kr','momo 단비','5,7',1,'',3),(549,'아이린','Irean.jpg','01620394050','아이린@sbs.co.kr','momo 아이린','3,4,6',1,'',3),(550,'유산슬','yu.jpg','01023446959','유산슬@google.com','momo 유산슬','3',1,'',3),(551,'아이유','I.jpg','01123446959','아이유@mbc.co.kr','momo 아이유','4',1,'',3),(552,'박정석','pack.jpg','01012346034','박정석@daum.net','momo 박정석','2',1,'',3),(553,'유산슬','yu.jpg','01123446959','유산슬@google.com','momo 유산슬','3',1,'',3),(554,'강호동','gang.jpg','01012346034','강호동@naver.com','momo 강호동','1',1,'',3),(555,'박정석','pack.jpg','01012346034','박정석@daum.net','momo 박정석','2',1,'',7),(556,'혜리','hery.jpg','01119593949','혜리@mbc.co.kr','momo 혜리','4,6',1,'',7),(557,'이수근','e.jpg','01012346034','이수근@daum.net','momo 이수근','2',1,'',7),(558,'유산슬','yu.jpg','01123446959','유산슬@google.com','momo 유산슬','3',1,'',7),(559,'조세호','zo.jpg','01123446959','조세호@mbc.co.kr','momo 조세호','4',1,'',7),(560,'유재석','yo.jpg','01012346034','유재석@naver.com','momo 유재석','1',1,'',7),(561,'은하','eunha.jpg','01620394050','은하@sbs.co.kr','momo 은하','3,4,6',1,'',7),(562,'유재석','yo.jpg','01012346034','유재석@naver.com','momo 유재석','1',1,'',7),(563,'조세호','zo.jpg','01123446959','조세호@google.com','momo 조세호','3',1,'',7),(564,'이수근','e.jpg','01012346034','이수근@daum.net','momo 이수근','2',1,'',7),(565,'홍진호','hong.jpg','01620394050','홍진호@hanmail.com','momo 홍진호','3,4,6',1,'',7),(566,'은비','eunbi.jpg','01619593949','은비@jtbc.co.kr','momo 은비','5,7',1,'',7),(567,'아이유','I.jpg','01123446959','아이유@mbc.co.kr','momo 아이유','4',1,'',7),(568,'문별','moon.jpg','01620394050','문별@hanmail.com','momo 문별','3,4,6',1,'',7),(569,'강호동','gang.jpg','01012346034','강호동@naver.com','momo 강호동','1',1,'',7),(570,'혜리','hery.jpg','01119593949','혜리@mbc.co.kr','momo 혜리','4,6',1,'',7),(571,'효정','hozung.jpg','01119593949','효정@jtbc.co.kr','momo 효정','5,7',1,'',7),(572,'혜리','hery.jpg','01119593949','혜리@mbc.co.kr','momo 혜리','4,6',1,'',7),(573,'효정','hozung.jpg','01119593949','효정@jtbc.co.kr','momo 효정','5,7',1,'',7),(574,'박정석','pack.jpg','01012346034','박정석@daum.net','momo 박정석','2',1,'',7),(575,'혜리','hery.jpg','01119593949','혜리@mbc.co.kr','momo 혜리','4,6',1,'',7),(576,'임요한','im.jpg','01023446959','임요한@daum.net','momo 임요한','2',1,'',7),(577,'이수근','e.jpg','01012346034','이수근@naver.com','momo 이수근','1',1,'',7),(578,'별','moon.jpg','10123123','99999','','1,2',1,'',7),(579,'별','moon.jpg','10123123','99999','','1,2',1,'zzeung@gmail.com',3),(580,'aae','2020-07-10_16:57:45.jpg','123','aa@a.com','helllow','2,4',1,'zzeung@gmail.com',3),(581,'린다G','lindag.jpg','01082828282','lindag@gmail.com','싹쓰리 실질적 리더','6',1,'zzeung@gmail.com',3),(582,'비룡','bryong.jpg','01056565656','bryong@naver.com','싹쓰리 막내','6',1,'zzeung@gmail.com',3),(583,'수발놈','subalom.jpg','01098989898','subal@naver.com','싹쓰리 막내','6',1,'zzeung@gmail.com',3),(584,'멈멍이','2020-07-10_12:05:46.jpg','8888','','','5',1,'zzeung@gmail.com',3),(587,'부엌ㅋㅋ','2020-07-10_12:11:41.jpg','789789','','','1,2',1,'zzeung@gmail.com',3),(588,'조정석','2020-07-10_15:43:48.jpg','01012345678','jo@naver.com','과외선생님','2,5',1,'user@naver.com',8),(589,'조정석','2020-07-10_15:44:08.jpg','01012345678','jo@naver.com','과외선생님','2,5',1,'user@naver.com',8),(590,'야너두','2020-07-10_15:45:29.jpg','01034567890','ya@naver.com','과외선생님','2,4,5',1,'user@naver.com',8),(591,'조정식','2020-07-10_15:51:41.jpg','01012341234','jojo@naver.com','야너두','1,5',1,'user@naver.com',8),(592,'강정식','2020-07-10_15:52:41.jpg','01045674567','jj@naver.com','야너두','1,5,6',1,'user@naver.com',8),(594,'asddb','2020-07-10_16:02:54.jpg','23','dsafas@df.go.kr','sadfasf','3,4',1,'zzeung@naver.com',6),(595,'asddb','2020-07-10_16:03:14.jpg','23','dsafas@df.go.kr','sadfasf','3,4',1,'zzeung@naver.com',6),(596,'abcde','2020-07-10_16:18:39.jpg','01012341234','fff@ggg.kk.kr','hijklmn','0',1,'zzeung@gmail.com',3),(597,'de','2020-07-10_16:20:59.jpg','3','dd','d','5,6',1,'user@naver.com',8),(598,'조정석','2020-07-10_16:37:22.jpg','01045678912','jo@naver.com','야너두','2,4,5',1,'userid@naver.com',9),(599,'조정석','2020-07-10_16:43:20.jpg','01012345678','jo@naver.com','야너두','1,4,5',1,'usertest@naver.com',10),(600,'d','2020-07-10_16:45:41.jpg','3','d','d','1,2,5',1,'usertest@naver.com',10);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `tSeqno` int(11) NOT NULL AUTO_INCREMENT,
  `tName` varchar(45) DEFAULT NULL,
  `tNumber` int(11) DEFAULT NULL,
  `tValidation` int(11) DEFAULT '1',
  `user_uId` varchar(45) NOT NULL,
  PRIMARY KEY (`tSeqno`),
  KEY `user_uId_foreignKey_idx` (`user_uId`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (15,'친구',1,1,'jong@naver.com'),(16,'연인',2,1,'jong@naver.com'),(17,'동료',3,1,'jong@naver.com'),(18,'회장님',4,1,'jong@naver.com'),(19,'가족',5,1,'jong@naver.com'),(20,'돈받아야할사람',6,1,'jong@naver.com'),(21,'돈내는사람',7,1,'jong@naver.com'),(58,'테란11',1,1,'ooo@naver.com'),(59,'ossssss',2,1,'ooo@naver.com'),(60,'나나33333',3,1,'ooo@naver.com'),(61,'뚜비뚜비',4,1,'ooo@naver.com'),(62,'파랑',5,1,'ooo@naver.com'),(63,'보라돌이',6,1,'ooo@naver.com'),(64,'쥐돌이',7,1,'ooo@naver.com'),(72,'아아아',1,1,'zzeung@gmail.com'),(73,'친구',2,1,'zzeung@gmail.com'),(74,'직장',3,1,'zzeung@gmail.com'),(75,'구독1',4,1,'zzeung@gmail.com'),(76,'파란색2',5,1,'zzeung@gmail.com'),(77,'놀면뭐하니',6,1,'zzeung@gmail.com'),(78,'런닝맨',7,1,'zzeung@gmail.com'),(79,'빨',1,1,'jjj@naver.com'),(80,'주',2,1,'jjj@naver.com'),(81,'노',3,1,'jjj@naver.com'),(82,'초',4,1,'jjj@naver.com'),(83,'파',5,1,'jjj@naver.com'),(84,'보',6,1,'jjj@naver.com'),(85,'회색',7,1,'jjj@naver.com'),(86,'빨간색',1,1,'shsh@daum.net'),(87,'주황색',2,1,'shsh@daum.net'),(88,'노란색',3,1,'shsh@daum.net'),(89,'초록색',4,1,'shsh@daum.net'),(90,'파란색',5,1,'shsh@daum.net'),(91,'보라색',6,1,'shsh@daum.net'),(92,'회색',7,1,'shsh@daum.net'),(93,'빨간색',1,1,'zzeung@naver.com'),(94,'주황색',2,1,'zzeung@naver.com'),(95,'노란색',3,1,'zzeung@naver.com'),(96,'초록색',4,1,'zzeung@naver.com'),(97,'파란색',5,1,'zzeung@naver.com'),(98,'보라색',6,1,'zzeung@naver.com'),(99,'회색',7,1,'zzeung@naver.com'),(100,'빨간색',1,1,'test@naver.com'),(101,'주황색',2,1,'test@naver.com'),(102,'노란색',3,1,'test@naver.com'),(103,'초록색',4,1,'test@naver.com'),(104,'파란색',5,1,'test@naver.com'),(105,'보라색',6,1,'test@naver.com'),(106,'회색',7,1,'test@naver.com'),(107,'사랑하는가족',1,1,'user@naver.com'),(108,'주황색',2,1,'user@naver.com'),(109,'직장동료',3,1,'user@naver.com'),(110,'동네친구',4,1,'user@naver.com'),(111,'술친구',5,1,'user@naver.com'),(112,'동아리',6,1,'user@naver.com'),(113,'회색',7,1,'user@naver.com'),(114,'사랑하는 가족',1,1,'userid@naver.com'),(115,'절친',2,1,'userid@naver.com'),(116,'동네친구',3,1,'userid@naver.com'),(117,'술친구',4,1,'userid@naver.com'),(118,'동아리',5,1,'userid@naver.com'),(119,'직장동료',6,1,'userid@naver.com'),(120,'회색',7,1,'userid@naver.com'),(121,'사랑하는가족',1,1,'usertest@naver.com'),(122,'절친들',2,1,'usertest@naver.com'),(123,'술친구',3,1,'usertest@naver.com'),(124,'동아리',4,1,'usertest@naver.com'),(125,'파란색',5,1,'usertest@naver.com'),(126,'보라색',6,1,'usertest@naver.com'),(127,'회색',7,1,'usertest@naver.com');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uSeqno` int(11) NOT NULL AUTO_INCREMENT,
  `uId` varchar(45) NOT NULL,
  `uPw` varchar(45) DEFAULT NULL,
  `uDate` datetime DEFAULT NULL,
  `uValidation` int(11) DEFAULT '1',
  PRIMARY KEY (`uSeqno`,`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'jong@naver.com','123456',NULL,0),(2,'ooo@naver.com','123123','2020-07-09 16:08:38',1),(3,'zzeung@gmail.com','123123','2020-07-09 17:14:00',1),(4,'jjj@naver.com','123123','2020-07-09 17:58:08',1),(5,'shsh@daum.net','123123','2020-07-09 18:10:26',1),(6,'zzeung@naver.com','123123','2020-07-09 18:12:04',1),(7,'test@naver.com','123456','2020-07-10 15:26:21',1),(8,'user@naver.com','123456','2020-07-10 15:41:33',1),(9,'userid@naver.com','123456','2020-07-10 16:35:23',1),(10,'usertest@naver.com','123456','2020-07-10 16:41:52',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-10 17:05:38
