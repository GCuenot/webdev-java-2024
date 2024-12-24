-- ticketapp2024.ticket definition
 
CREATE TABLE `ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(100) DEFAULT NULL,
  `description` text,
  `impact` smallint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;