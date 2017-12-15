-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 15-Dez-2017 às 15:09
-- Versão do servidor: 10.1.26-MariaDB
-- PHP Version: 7.1.9

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
    ,produtos.preco
    ,produtos.descProduto
    ,produtos.codMarca
    ,marca.utilitario as marca
    ,produtos.valorNotacao
    ,notacao.utilitario as notacao
    
	
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `cVendas` (IN `idPedidoVendas` INT(11) UNSIGNED ZEROFILL, IN `idPessoas` INT(11) UNSIGNED ZEROFILL)  NO SQL
SELECT
	vendapedido.codVendaPedido	
	,vendapedido.codCliente
    ,pessoa.nome
    ,pessoa.cpf
    ,vendapedido.dataVenda
    ,vendaintens.codProduto
	,produtos.descProduto
    ,vendaintens.qtVenda
    ,vendaintens.desconto
    ,vendaintens.acrescimo
    ,vendaintens.totalValorBruto
    ,vendaintens.valorTotal
    ,vendaintens.totalValorLiquido

FROM vendapedido
	LEFT JOIN vendaintens ON	
    	vendapedido.codVendaPedido = vendaintens.codVendaPedido
      
    	LEFT JOIN produtos ON
        	vendaintens.codProduto = produtos.codProduto
      
    LEFT JOIN pessoa ON
    	vendapedido.codCliente = pessoa.codPessoa
        
 WHERE vendapedido.codVendaPedido = filtro(vendapedido.codVendaPedido, idPedidoVendas)
 		AND vendapedido.codCliente = filtro(vendapedido.codCliente, idPessoas)$$

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
  `Banco` varchar(100) NOT NULL,
  `nroConta` varchar(11) NOT NULL,
  `agencia` varchar(11) NOT NULL,
  `saldoAtual` float(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `conta`
--

INSERT INTO `conta` (`codConta`, `operacao`, `Banco`, `nroConta`, `agencia`, `saldoAtual`) VALUES
(1, 11, 'BB', '123-125', '2356', 123.00),
(2, 5, 'CAIXA', '1235-56', '254', 500.00);

-- --------------------------------------------------------

--
-- Estrutura da tabela `menus`
--

CREATE TABLE `menus` (
  `codMenu` smallint(11) NOT NULL,
  `nomeMenu` varchar(20) NOT NULL,
  `codTipoMenu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `menus`
--

INSERT INTO `menus` (`codMenu`, `nomeMenu`, `codTipoMenu`) VALUES
(1, 'Clientes', 18),
(2, 'Produtos', 18),
(3, 'Vendas', 19);

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

--
-- Extraindo dados da tabela `movimentacaoconta`
--

INSERT INTO `movimentacaoconta` (`codMoviConta`, `codConta`, `codTipoMov`, `valor`) VALUES
(1, 126566, 0, 100.00),
(2, 2365211, 0, 200.00);

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
(19, 'Janete de vargas', 'Santo Anto', 1, 7, '46516548-55', 'asdasd@ds.com.br', 's', ''),
(20, 'neimar', 'poa', 5, 1, '15613', '122@dsds', 's', ''),
(21, 'dsdsdsd', 'null', 1, 1, 'CPF', 'dsds', 'S', ''),
(22, 'Neimar', 'Porto alegre', 3, 4, '132366565226', 'niemar@gfms.com', 'S', ''),
(23, 'neimar', 'porto alegre', 3, 4, '234566-55', 'nuwrrwrwerw@tt', 'S', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `codProduto` smallint(11) NOT NULL,
  `descProduto` varchar(50) NOT NULL,
  `codMarca` int(11) NOT NULL,
  `valorNotacao` float(10,2) NOT NULL,
  `codNotacao` int(11) NOT NULL,
  `preco` float(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`codProduto`, `descProduto`, `codMarca`, `valorNotacao`, `codNotacao`, `preco`) VALUES
(11, 'Celular nokia', 5, 1.00, 12, 500.12),
(12, 'Geladeira frosfri', 13, 1.00, 12, 200.15),
(13, 'Celular k10 ', 10, 1.00, 12, 1550.05),
(14, 'Notebook i7 ', 9, 1.00, 12, 3250.14),
(70, 'dsdsdsd', 9, 12.56, 18, 12.65),
(71, 'pc dell', 9, 34.56, 18, 34.45);

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
(16, 'Tipo Movimentação conta'),
(17, 'Tipo Conta'),
(18, 'Menu cadastro'),
(19, 'Menu Opercações'),
(20, 'Menu Relatorios');

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
(17, 'Rendimento', 16, '', 0, 0),
(18, 'metros', 13, '', 0, 0),
(19, 'kg ', 13, '', 0, 0),
(20, 'Corrente', 17, '', 0, 0),
(21, 'Poupança', 17, '', 0, 0),
(22, 'Transferencia', 16, '', 0, 0),
(23, 'Saque', 16, '', 0, 0),
(24, 'Deposito', 16, '', 0, 0),
(25, 'Clientes', 18, '', 0, 0),
(26, 'Produtos', 18, '', 0, 0),
(27, 'Vendas', 19, '', 0, 0),
(29, 'Perdido', 18, '', 0, 0);

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
(88, 68, 13, 5, 0.00, 0.00, 0.00, 0.00, 0.00),
(89, 68, 13, 5, 0.00, 0.00, 0.00, 0.00, 0.00),
(90, 69, 12, 1, 0.00, 0.00, 0.00, 0.00, 0.00),
(91, 70, 14, 1, 0.00, 0.00, 12.00, 12.00, 12.00),
(92, 70, 14, 34, 0.00, 0.00, 408.00, 408.00, 408.00),
(93, 70, 14, 34, 0.00, 100.00, 408.00, 408.00, 508.00),
(94, 70, 14, 34, 350.00, 100.00, 408.00, 58.00, 158.00),
(95, 71, 12, 4, 0.00, 0.00, 48.00, 48.00, 48.00),
(96, 72, 12, 45, 0.00, 0.00, 540.00, 540.00, 540.00),
(97, 72, 12, 45, 45.00, 0.00, 540.00, 495.00, 495.00),
(98, 72, 12, 45, 45.00, 56.00, 540.00, 495.00, 551.00),
(99, 77, 12, 3, 0.00, 0.00, 36.00, 36.00, 36.00),
(100, 77, 12, 3, 0.00, 0.00, 36.00, 36.00, 36.00),
(101, 77, 12, 3, 4.00, 0.00, 36.00, 32.00, 32.00),
(102, 77, 12, 3, 4.00, 55.00, 36.00, 32.00, 87.00),
(103, 78, 71, 4, 20.00, 30.00, 136.00, 116.00, 146.00);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendapedido`
--

CREATE TABLE `vendapedido` (
  `codVendaPedido` smallint(11) NOT NULL,
  `codCliente` int(11) NOT NULL,
  `dataVenda` date NOT NULL,
  `idConta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vendapedido`
--

INSERT INTO `vendapedido` (`codVendaPedido`, `codCliente`, `dataVenda`, `idConta`) VALUES
(68, 18, '2017-12-15', 1),
(69, 19, '2017-12-15', 1),
(70, 17, '2017-12-15', 1),
(71, 20, '2017-12-15', 1),
(72, 18, '2017-12-15', 1),
(73, 19, '2017-12-15', 1),
(74, 19, '2017-12-15', 1),
(75, 19, '2017-12-15', 1),
(76, 19, '2017-12-15', 1),
(77, 19, '2017-12-15', 1),
(78, 23, '2017-12-15', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `conta`
--
ALTER TABLE `conta`
  ADD PRIMARY KEY (`codConta`);

--
-- Indexes for table `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`codMenu`);

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
  MODIFY `codConta` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `menus`
--
ALTER TABLE `menus`
  MODIFY `codMenu` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `movimentacaoconta`
--
ALTER TABLE `movimentacaoconta`
  MODIFY `codMoviConta` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `codPessoa` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `codProduto` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `tipoutilitarios`
--
ALTER TABLE `tipoutilitarios`
  MODIFY `codTipoUtilitario` smallint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `utilitarios`
--
ALTER TABLE `utilitarios`
  MODIFY `codUtilitario` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `vendaintens`
--
ALTER TABLE `vendaintens`
  MODIFY `codVendaIntens` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT for table `vendapedido`
--
ALTER TABLE `vendapedido`
  MODIFY `codVendaPedido` smallint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
