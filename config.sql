CREATE DATABASE desafio_hotel;

USE desafio_hotel;

CREATE TABLE `reservas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_entrada` date DEFAULT NULL,
  `data_saida` date DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `forma_pagamento` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `hospedes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) DEFAULT NULL,
  `sobrenome` varchar(30) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `nacionalidade` varchar(30) DEFAULT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `id_reserva` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_reserva` (`id_reserva`),
  CONSTRAINT `hospedes_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id`)
);

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(30) DEFAULT NULL,
  `senha` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `usuarios` VALUES (1,'admin','admin'),(2,'Julyanna','12345'),(3,'pitura','qwert');
