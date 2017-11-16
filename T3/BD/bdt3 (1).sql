-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 16-Nov-2017 às 13:46
-- Versão do servidor: 10.1.19-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `cPessoa` (IN `idPessoa` VARCHAR(11))  NO SQL
    DETERMINISTIC
SELECT       

             Pessoa.codPessoa

            ,Pessoa.nome

            ,Pessoa.cidade

            ,Pessoa.codTipoPessoa

            ,tipo.utilitario as tipoPessoa

			,Pessoa.codProfissao

            ,profissao.utilitario as profissao

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `cProdutos` (IN `idProduto` INT(11) UNSIGNED ZEROFILL)  NO SQL
select 
	produtos.codProduto
    ,produtos.descProduto
    ,produtos.codMarca
    ,marca.utilitario as marca
    ,produtos.valorNotacao
    ,notacao.utilitario as notacao
    ,produtos.preco
	
from produtos
	LEFT JOIN utilitarios as marca ON
    	produtos.codMarca = marca.codUtilitario
    
    LEFT JOIN utilitarios AS notacao ON
    	produtos.codNotacao = notacao.codUtilitario

where produtos.codProduto = filtro(produtos.codProduto, idProduto)$$

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
CREATE DEFINER=`root`@`localhost` FUNCTION `filtro` (`valorOrigem` VARCHAR(200), `valorFiltro` VARCHAR(200)) RETURNS VARCHAR(10) CHARSET latin1 BEGIN
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
-- Estrutura da tabela `conta`
--

CREATE TABLE `conta` (
  `codConta` smallint(6) NOT NULL,
  `operacao` int(11) NOT NULL,
  `nroBanco` int(11) NOT NULL,
  `nroConta` varchar(11) NOT NULL,
  `agencia` varchar(11) NOT NULL,
  `saldoAtual` float(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `movimentacaoconta`
--

CREATE TABLE `movimentacaoconta` (
  `codMoviConta` smallint(11) NOT NULL,
  `codConta` int(11) NOT NULL,
  `codTipoMov` int(11) NOT NULL,
  `valor` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(17, 'Neimar Moises', 'Porto alegra', 1, 3, '1336515488-45', '125@ddfd.com', 's', ''),
(18, 'Jorge carlos limera', 'Limeira ', 1, 4, '45112.5.656-8', 'sfd@ds.com.br', 's', ''),
(19, 'Janete de vargas', 'Santo Anto', 1, 7, '46516548-55', 'asdasd@ds.com.br', 's', '');

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

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`codProduto`, `descProduto`, `codMarca`, `valorNotacao`, `codNotacao`, `preco`) VALUES
(11, 'Celular nokia', 5, 1, 12, 500.12),
(12, 'Geladeira frosfri', 13, 1, 12, 200.15),
(13, 'Celular k10 ', 10, 1, 12, 1550.05),
(14, 'Notebook i7 ', 9, 1, 12, 3250.14);

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
(15, 'Situação fisica'),
(16, 'Tipo Movimentação conta');

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
(10, 'HP', 14, '', 0, 0),
(12, 'Unidade', 13, '', 0, 0),
(13, 'Consul', 14, '', 0, 0),
(14, 'Saque', 16, '', 0, 0),
(15, 'Depósito', 16, '', 0, 0),
(16, 'Transferencia', 16, '', 0, 0),
(17, 'Rendimento', 16, '', 0, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendaintens`
--

CREATE TABLE `vendaintens` (
  `codVendaIntens` smallint(11) NOT NULL,
  `codVendaPedido` int(11) NOT NULL,
  `codProduto` int(11) NOT NULL,
  `qtVenda` int(11) NOT NULL,
  `desconto` double(10,2) NOT NULL,
  `acrescimo` double(10,2) NOT NULL,
  `totalValorBruto` double(10,2) NOT NULL,
  `valorTotal` double(10,2) NOT NULL,
  `totalValorLiquido` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vendaintens`
--

INSERT INTO `vendaintens` (`codVendaIntens`, `codVendaPedido`, `codProduto`, `qtVenda`, `desconto`, `acrescimo`, `totalValorBruto`, `valorTotal`, `totalValorLiquido`) VALUES
(27, 20, 11, 2, 20.11, 0.00, 1000.24, 980.13, 980.13),
(28, 20, 12, 5, 25.00, 0.00, 1000.75, 975.75, 975.75),
(29, 20, 13, 2, 152.00, 0.00, 3100.10, 2948.10, 2948.10),
(30, 20, 14, 1, 215.00, 0.00, 3250.14, 3035.14, 3035.14),
(31, 21, 13, 1, 0.00, 0.00, 1550.05, 1550.05, 1550.05),
(32, 21, 11, 1, 0.00, 0.00, 500.12, 500.12, 500.12),
(33, 22, 14, 1, 1.00, 1.00, 3250.14, 3249.14, 3250.14),
(34, 23, 12, 2, 0.00, 1.00, 400.30, 400.30, 401.30),
(35, 24, 5, 1, 1.00, 1.00, 0.00, -1.00, 0.00),
(36, 24, 11, 1, 0.00, 0.00, 500.12, 500.12, 500.12),
(37, 24, 12, 1, 0.00, 0.00, 200.15, 200.15, 200.15);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendapedido`
--

CREATE TABLE `vendapedido` (
  `codVendaPedido` smallint(11) NOT NULL,
  `codCliente` int(11) NOT NULL,
  `dataVenda` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vendapedido`
--

INSERT INTO `vendapedido` (`codVendaPedido`, `codCliente`, `dataVenda`) VALUES
(20, 17, '2017-02-17'),
(21, 18, '2014-02-15'),
(22, 18, '2014-02-15'),
(23, 17, '2017-02-18'),
(24, 17, '2017-02-17');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `conta`
--
ALTER TABLE `conta`
  ADD PRIMARY KEY (`codConta`);

--
-- Indexes for table `movimentacaoconta`
--
ALTER TABLE `movimentacaoconta`
  ADD PRIMARY KEY (`codMoviConta`);

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
-- Indexes for table `vendaintens`
--
ALTER TABLE `vendaintens`
  ADD PRIMARY KEY (`codVendaIntens`),
  ADD KEY `codVendaPedido` (`codVendaPedido`);

--
-- Indexes for table `vendapedido`
--
ALTER TABLE `vendapedido`
  ADD PRIMARY KEY (`codVendaPedido`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `conta`
--
ALTER TABLE `conta`
  MODIFY `codConta` smallint(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `movimentacaoconta`
--
ALTER TABLE `movimentacaoconta`
  MODIFY `codMoviConta` smallint(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `codPessoa` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `codProduto` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `tipoutilitarios`
--
ALTER TABLE `tipoutilitarios`
  MODIFY `codTipoUtilitario` smallint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `utilitarios`
--
ALTER TABLE `utilitarios`
  MODIFY `codUtilitario` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `vendaintens`
--
ALTER TABLE `vendaintens`
  MODIFY `codVendaIntens` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `vendapedido`
--
ALTER TABLE `vendapedido`
  MODIFY `codVendaPedido` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
