-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-10-2015 a las 00:16:04
-- Versión del servidor: 5.5.31
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `examen`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--
CREATE DATABASE  examen;
USE examen;
CREATE TABLE IF NOT EXISTS `persona` (
  `Nombre` varchar(60) NOT NULL,
  `Apellido_Paterno` varchar(60) NOT NULL,
  `Apellido_Materno` varchar(60) NOT NULL,
  `Nombre_de_Usuario` varchar(12) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `Contrasenia` varchar(15) NOT NULL,
  `Numero_Telefonico` varchar(10) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `direccion` varchar(40) DEFAULT NULL,
  `Estatus` char(1) NOT NULL,
  PRIMARY KEY (`Nombre_de_Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`Nombre`, `Apellido_Paterno`, `Apellido_Materno`, `Nombre_de_Usuario`, `Contrasenia`, `Numero_Telefonico`, `Email`, `direccion`, `Estatus`) VALUES
('ADMIN', 'ADMIN', 'ADMIN', 'Admin', '123456789', '7121345029', 'uno@hotmail.com', 's/n', '1');
