-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 10-Nov-2017 às 00:51
-- Versão do servidor: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdt3`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`pep`@`10.%` PROCEDURE `cPessoa` (IN `idPessoa` VARCHAR(11))  NO SQL
    DETERMINISTIC
SELECT       

             Pessoa.codPessoa

            ,Pessoa.nome

            ,Pessoa.cidade

            ,Pessoa.codTipoPessoa

            ,tipo.utilitario as tipoPessoa

			,Pessoa.codProfissao

            ,profissao.utilitario as profissão

            ,Pessoa.cpf

            ,Pessoa.Ativa

            ,Usuarios.codUsuarios

            ,Usuarios.status



        FROM Pessoa

            LEFT JOIN Utilitarios AS tipo ON
                Pessoa.codTipoPessoa = tipo.codUtilitario

            LEFT JOIN Utilitarios AS profissao ON

                Pessoa.codProfissao = profissao.codUtilitario


            LEFT JOIN Usuarios ON

                Pessoa.codPessoa = Usuarios.codPessoa

WHERE Pessoa.codPessoa = filtro(Pessoa.codPessoa, idPessoa)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `cUtilitarios` (IN `codTipoUtilitarios` INT(11) UNSIGNED, IN `codSubGrupoUtilitarios` INT(11) UNSIGNED)  NO SQL
    DETERMINISTIC
SELECT 

		Utilitarios.codUtilitario
		,Utilitarios.utilitario
		,Utilitarios.codTipoUtilirario
		,Utilitarios.Obs
	
		,Utilitarios.favorita
		,TipoUtilitarios.descTipoUtilitario
		,Utilitarios.codSubGrupo
		,sub.utilitario AS subGrupo

	FROM Utilitarios
		LEFT JOIN TipoUtilitarios ON
			Utilitarios.codTipoUtilirario = TipoUtilitarios.codTipoUtilitario

		LEFT JOIN Utilitarios as sub ON
			Utilitarios.codSubGrupo = sub.codUtilitario

		WHERE Utilitarios.codTipoUtilirario = filtro(Utilitarios.codTipoUtilirario, codTipoUtilitarios)
			 AND Utilitarios.codSubGrupo = filtro(Utilitarios.codSubGrupo,codSubGrupoUtilitarios)

ORDER BY Utilitarios.utilitario, TipoUtilitarios.descTipoUtilitario ASC$$

--
-- Functions
--
CREATE DEFINER=`pep`@`10.%` FUNCTION `filtro` (`valorOrigem` VARCHAR(200), `valorFiltro` VARCHAR(200)) RETURNS VARCHAR(10) CHARSET latin1 BEGIN
    DECLARE f varchar(200);
 
    IF valorFiltro > '0' THEN
        SET f = valorFiltro;

    ELSE 
        SET f = valorOrigem;
    END IF;
 
 RETURN (f);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

CREATE TABLE `pessoa` (
  `codPessoa` smallint(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `codTipoPessoa` int(11) NOT NULL COMMENT 'Tipo Utilitário id:3',
  `codProfissao` int(11) NOT NULL COMMENT 'Tipo Utilitário id:6',
  `cpf` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `Ativa` varchar(1) NOT NULL COMMENT 'S= Sim, N=Não',
  `cref` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=REDUNDANT;

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`codPessoa`, `nome`, `cidade`, `codTipoPessoa`, `codProfissao`, `cpf`, `email`, `Ativa`, `cref`) VALUES
(12, 'angela', 'encantado', 2, 5, '1216511321', 'dasdsa@fddd', 's', ''),
(13, 'neimar', 'relvado', 1, 7, '156511565', 'adsd@ddd', 's', ''),
(14, 'jorjao', 'xaixim', 3, 6, '465132', 'fdsfsd@ff', 's', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `codProduto` smallint(11) NOT NULL,
  `descProduto` varchar(50) NOT NULL,
  `codMarca` int(11) NOT NULL,
  `valorNotacao` float NOT NULL,
  `codNotacao` int(11) NOT NULL,
  `preco` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipoutilitarios`
--

CREATE TABLE `tipoutilitarios` (
  `codTipoUtilitario` smallint(20) UNSIGNED NOT NULL,
  `descTipoUtilitario` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipoutilitarios`
--

INSERT INTO `tipoutilitarios` (`codTipoUtilitario`, `descTipoUtilitario`) VALUES
(3, 'Tipo pessoa'),
(6, 'Profissões'),
(13, 'Notação'),
(14, 'Marca'),
(15, 'Situação fisica');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` smallint(6) NOT NULL,
  `codUsuarios` varchar(20) NOT NULL,
  `codPessoa` int(11) DEFAULT NULL,
  `senha` varchar(8) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `codUsuarios`, `codPessoa`, `senha`, `status`) VALUES
(1, 'neimar', 2, '1234', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `utilitarios`
--

CREATE TABLE `utilitarios` (
  `codUtilitario` smallint(11) NOT NULL,
  `utilitario` varchar(255) NOT NULL,
  `codTipoUtilirario` int(11) NOT NULL,
  `Obs` text NOT NULL,
  `codSubGrupo` int(11) NOT NULL,
  `favorita` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `utilitarios`
--

INSERT INTO `utilitarios` (`codUtilitario`, `utilitario`, `codTipoUtilirario`, `Obs`, `codSubGrupo`, `favorita`) VALUES
(1, 'Cliente', 3, '', 0, 0),
(2, 'Forncedor', 3, '', 0, 0),
(3, 'Cliente fornecedor', 3, '', 0, 0),
(4, 'Engenheiro', 6, '', 0, 0),
(5, 'Professor', 6, '', 0, 0),
(6, 'Engenheiro', 6, '', 0, 0),
(7, 'Atendente', 6, '', 0, 0),
(8, 'Sansung', 14, '', 0, 0),
(9, 'Nokia', 14, '', 0, 0),
(10, 'HP', 14, '', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`codPessoa`);

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`codProduto`);

--
-- Indexes for table `tipoutilitarios`
--
ALTER TABLE `tipoutilitarios`
  ADD PRIMARY KEY (`codTipoUtilitario`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilitarios`
--
ALTER TABLE `utilitarios`
  ADD PRIMARY KEY (`codUtilitario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `codPessoa` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `codProduto` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tipoutilitarios`
--
ALTER TABLE `tipoutilitarios`
  MODIFY `codTipoUtilitario` smallint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `utilitarios`
--
ALTER TABLE `utilitarios`
  MODIFY `codUtilitario` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
