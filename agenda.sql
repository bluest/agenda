-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 10-Set-2016 às 03:20
-- Versão do servidor: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agenda`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `contato`
--

CREATE TABLE `contato` (
  `codigo` int(10) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  `celular` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tipo` int(10) NOT NULL,
  `observacao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `contato`
--

INSERT INTO `contato` (`codigo`, `nome`, `endereco`, `telefone`, `celular`, `email`, `tipo`, `observacao`) VALUES
(1, 'Bruna', 'Minha Rua', '3434-3434', '99999-9999', 'bruna@bruna', 1, 'não ligar de manhã'),
(4, 'Mãe', 'Rua 10', '3421-3421', '91919-9999', 'mae@mae', 1, 'aniversário: 17/01'),
(5, 'Chefe', 'Rua X', '3423-3421', '97919-9898', 'chefe@chefe', 2, 'não recusar ligações'),
(6, 'Michel', 'Rua 15', '3432-3231', '90999-9122', 'michel@michel', 1, 'aniversário: 12/09'),
(7, 'Funcionário', 'Rua 9', '3322-3232', '97999-9432', 'func@func', 2, 'não atender fora de horário'),
(8, 'João', 'Rua 8', '3131-3234', '99449-4444', 'joao@joao', 2, 'cabelereiro');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo`
--

CREATE TABLE `tipo` (
  `codigo` int(10) NOT NULL,
  `descricao` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipo`
--

INSERT INTO `tipo` (`codigo`, `descricao`) VALUES
(1, 'Pessoal'),
(2, 'Profissional');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contato`
--
ALTER TABLE `contato`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contato`
--
ALTER TABLE `contato`
  MODIFY `codigo` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
