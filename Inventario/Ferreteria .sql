
DROP SCHEMA IF EXISTS `Ferreteria`;
CREATE SCHEMA `Ferreteria` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;

USE `Ferreteria`;
CREATE TABLE `Ferreteria`.`herramientas` (
  `codigo` INTEGER NOT NULL,
  `nombreProducto` VARCHAR(45) NOT NULL,
  `precio` DOUBLE NOT NULL,
  `cantidad` INTEGER NOT NULL,
  `capasidad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`));
  
  
  USE `Ferreteria`;
CREATE TABLE `Ferreteria`.`materiales` (
  `codigo` INTEGER NOT NULL,
  `nombreProducto` VARCHAR(45) NOT NULL,
  `precio` DOUBLE NOT NULL,
  `cantidad` INTEGER NOT NULL,
  `tamanio` DOUBLE NOT NULL,
  `medida` DOUBLE NOT NULL,
  PRIMARY KEY (`codigo`));
