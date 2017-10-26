-- phpMyAdmin SQL Dump
-- version 4.3.7
-- http://www.phpmyadmin.net
--
-- Host: mysql01-farm62.kinghost.net
-- Tempo de geração: 22/10/2017 às 17:11
-- Versão do servidor: 5.7.18-log
-- Versão do PHP: 5.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `pep`
--

DELIMITER $$
--
-- Procedimentos
--
CREATE DEFINER=`pep`@`10.%` PROCEDURE `cExercicios`(IN `idModalidade` INT(11) UNSIGNED ZEROFILL, IN `idNivel` INT(11) UNSIGNED ZEROFILL, IN `idTipo` INT(11) UNSIGNED ZEROFILL)
    NO SQL
    DETERMINISTIC
SELECT 
	Exercicios.codExercicio
	,Exercicios.nome	

	,Exercicios.codModalidade	
	,modalidade.utilitario As modalidade

	,Exercicios.codNivel
	,nivel.utilitario AS nivel
	
	,Exercicios.codTipo
	,tipo.utilitario AS tipo
	
	,Exercicios.descContraIndicacoes
	,Exercicios.descRecomendacoes


FROM Exercicios
	
	LEFT JOIN Utilitarios AS modalidade ON
		Exercicios.codModalidade = modalidade.codUtilitario
	
	LEFT JOIN Utilitarios AS nivel ON
		Exercicios.codNivel = nivel.codUtilitario

	LEFT JOIN Utilitarios AS tipo ON
		Exercicios.codTipo = tipo.codUtilitario

WHERE Exercicios.codModalidade = filtro(Exercicios.codModalidade,idModalidade)
	and Exercicios.codNivel = filtro(Exercicios.codNivel,idNivel)
	and Exercicios.codTipo = filtro(Exercicios.codTipo,idTipo)

ORDER by Exercicios.nome asc$$

CREATE DEFINER=`pep`@`10.%` PROCEDURE `cGraficoResultados`(IN `codAluno` INT)
    NO SQL
    DETERMINISTIC
SELECT	
	PlanoAtividadePessoal.codAluno
	,Treinos.codProfessor
	
	,ResultadosPeriodicos.pesoObjetivo - ResultadosPeriodicos.pesoAtual AS Meta
	
	,ResultadosPeriodicos.pesoSuporte - ResultadosPeriodicos.pesoAtual AS Suporte

from ResultadosPeriodicos
	
	LEFT JOIN PlanoAtividadePessoal ON
		ResultadosPeriodicos.codPlanoAtividadePessoal = PlanoAtividadePessoal.codPlanoAtividadePessoal

		LEFT JOIN Treinos ON
			PlanoAtividadePessoal.codTreino = Treinos.codTreinos$$

CREATE DEFINER=`pep`@`10.%` PROCEDURE `cHabilitacaoPessoa`(IN `idPessoa` INT)
    NO SQL
    DETERMINISTIC
SELECT                 
			
			HabilitacaoPessoa.codHabilitacaoPessoa

            ,Pessoa.nome
		 			
			,Pessoa.codPessoa

            ,Pessoa.cidade

            ,Pessoa.codTipoPessoa
  
            ,tipo.utilitario as tipoPessoa

			,Pessoa.codProfissao

            ,profissao.utilitario as profissão

            ,Pessoa.cpf

            ,Pessoa.Ativa

            ,Usuarios.codUsuarios

            ,Usuarios.status
			
			,tipoHabilitacaoPessoa.utilitario AS habilitacaoPessoa



        FROM Pessoa

            LEFT JOIN Utilitarios AS tipo ON

                Pessoa.codTipoPessoa = tipo.codUtilitario


            LEFT JOIN Utilitarios AS profissao ON

                Pessoa.codProfissao = profissao.codUtilitario
			

            LEFT JOIN Usuarios ON

                Pessoa.codPessoa = Usuarios.codPessoa


			LEFT JOIN HabilitacaoPessoa ON
				
				Pessoa.codPessoa = HabilitacaoPessoa.codPessoa


				LEFT JOIN Utilitarios AS tipoHabilitacaoPessoa ON

					HabilitacaoPessoa.codTipoHabilitacao = tipoHabilitacaoPessoa.codUtilitario


WHERE Pessoa.codPessoa = filtro(Pessoa.codPessoa, idPessoa)

ORDER by Pessoa.nome ASC$$

CREATE DEFINER=`pep`@`10.%` PROCEDURE `cPessoa`(IN `idPessoa` VARCHAR(1))
    NO SQL
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

CREATE DEFINER=`pep`@`10.%` PROCEDURE `cTreinos`(IN `idTreino` INT(11) UNSIGNED ZEROFILL, IN `idProfessor` INT(11) UNSIGNED ZEROFILL, IN `idTipoTreinos` INT(11) UNSIGNED ZEROFILL)
    NO SQL
    DETERMINISTIC
SELECT 
	
	AtividadesTreino.codAtividadesTreino
	,Treinos.codTreinos
	,Treinos.codProfessor
	,Pessoa.nome AS professor
	,Treinos.codTipoTreino
	,tipoTreino.utilitario AS tipoTreino
	,Treinos.descObjetivo
	,Treinos.qtDiasDuracao
	,Treinos.qtDiasAvaliacao
	,Treinos.ativo
	,AtividadesTreino.codExercicio
	,Exercicios.nome AS exercicios
	,AtividadesTreino.codNotacao
	,notacao.utilitario as descNotacao
	,AtividadesTreino.valorNotacao
	,AtividadesTreino.qtCalorias


FROM Treinos 

	LEFT JOIN AtividadesTreino ON
		Treinos.codTreinos = AtividadesTreino.codTreino

		LEFT JOIN Exercicios ON	
			AtividadesTreino.codExercicio = Exercicios.codExercicio

		
		LEFT JOIN Utilitarios AS notacao ON
			AtividadesTreino.codNotacao = notacao.codUtilitario
		


	LEFT JOIN HabilitacaoPessoa ON
    	Treinos.codProfessor = HabilitacaoPessoa.codHabilitacaoPessoa

    LEFT JOIN Pessoa ON
   		HabilitacaoPessoa.codPessoa = Pessoa.codPessoa
	

	LEFT JOIN Utilitarios tipoTreino ON 

		Treinos.codTipoTreino = tipoTreino.codUtilitario


WHERE 
	Treinos.codTreinos = filtro(Treinos.codTreinos, idTreino)
	AND Treinos.codProfessor = filtro(Treinos.codProfessor, idProfessor)
	AND Treinos.codTipoTreino = filtro(Treinos.codTipoTreino, idTipoTreinos)$$

CREATE DEFINER=`pep`@`10.%` PROCEDURE `cUtilitarios`(IN `codTipoUtilitarios` INT(13) UNSIGNED, IN `codSubGrupoUtilitarios` INT UNSIGNED)
    NO SQL
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
-- Funções
--
CREATE DEFINER=`pep`@`10.%` FUNCTION `filtro`(`valorOrigem` VARCHAR(200), `valorFiltro` VARCHAR(200)) RETURNS varchar(10) CHARSET latin1
    DETERMINISTIC
BEGIN
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
-- Estrutura para tabela `AgendaTreinamentos`
--

CREATE TABLE IF NOT EXISTS `AgendaTreinamentos` (
  `codAgendaTreinamentos` smallint(6) NOT NULL,
  `codPlanoAtividadePessoal` int(11) NOT NULL,
  `codAtividadesTreino` int(11) NOT NULL,
  `codOrdem` int(11) NOT NULL,
  `valorNotacaoExecucao` float NOT NULL,
  `codNotacaoQtExecucao` int(11) NOT NULL COMMENT 'Tipo Utilitário 13',
  `qtRepeticoes` int(11) NOT NULL,
  `peso` float NOT NULL,
  `valorTempoExecucao` float NOT NULL,
  `codNotacoTempoExecucao` int(11) NOT NULL COMMENT 'Tipo Utilitário 13',
  `valorNotacaoMetaAtividade` float NOT NULL,
  `codNotacaoMetaAtividade` int(11) NOT NULL COMMENT 'Tipo Utilitário 13',
  `realizado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `AtividadesTreino`
--

CREATE TABLE IF NOT EXISTS `AtividadesTreino` (
  `codAtividadesTreino` smallint(6) NOT NULL,
  `codTreino` int(11) NOT NULL,
  `codExercicio` int(11) NOT NULL,
  `codNotacao` int(11) NOT NULL COMMENT 'Tipo Utilitário 13',
  `valorNotacao` int(11) NOT NULL,
  `qtCalorias` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `AtividadesTreino`
--

INSERT INTO `AtividadesTreino` (`codAtividadesTreino`, `codTreino`, `codExercicio`, `codNotacao`, `valorNotacao`, `qtCalorias`) VALUES
(1, 1, 1, 54, 2, 200),
(2, 1, 2, 54, 2, 200);

-- --------------------------------------------------------

--
-- Estrutura para tabela `AvaliacaoAluno`
--

CREATE TABLE IF NOT EXISTS `AvaliacaoAluno` (
  `codAvaliacaoAluno` smallint(6) NOT NULL,
  `codSolicitacaoAvaliacao` int(11) NOT NULL,
  `codProfessor` int(11) NOT NULL COMMENT 'Tabela habilitação pessoa',
  `codAluno` int(11) NOT NULL,
  `codSituacao` int(11) NOT NULL COMMENT 'Tipo utilitarios: 15',
  `dataAvaliacao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `ChekinPessoal`
--

CREATE TABLE IF NOT EXISTS `ChekinPessoal` (
  `codChekeinPessoal` smallint(6) NOT NULL,
  `codPessoa` int(11) NOT NULL,
  `dataNacimento` date NOT NULL,
  `pesoAtual` int(11) NOT NULL,
  `codSituacaoFisica` int(11) NOT NULL COMMENT 'Tipo Utilitário id: 4',
  `codObjetivo` int(11) NOT NULL COMMENT 'Tipo Utilitário 10',
  `pesoDesejado` int(11) NOT NULL,
  `nroMeses` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `ChekinPessoal`
--

INSERT INTO `ChekinPessoal` (`codChekeinPessoal`, `codPessoa`, `dataNacimento`, `pesoAtual`, `codSituacaoFisica`, `codObjetivo`, `pesoDesejado`, `nroMeses`) VALUES
(1, 1, '2000-06-05', 0, 5, 43, 80, 10),
(2, 1, '1990-09-21', 100, 3, 41, 80, 6),
(3, 2, '2001-09-11', 90, 3, 42, 70, 12);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Exercicios`
--

CREATE TABLE IF NOT EXISTS `Exercicios` (
  `codExercicio` smallint(11) NOT NULL,
  `codModalidade` int(11) NOT NULL COMMENT 'Tipo Utilitário id:18',
  `nome` varchar(255) NOT NULL,
  `codNivel` int(11) NOT NULL COMMENT 'Tipo Utilitário id:14',
  `codTipo` int(11) NOT NULL COMMENT 'Tipo Utilitário id:19',
  `descContraIndicacoes` text NOT NULL,
  `descRecomendacoes` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Exercicios`
--

INSERT INTO `Exercicios` (`codExercicio`, `codModalidade`, `nome`, `codNivel`, `codTipo`, `descContraIndicacoes`, `descRecomendacoes`) VALUES
(1, 75, 'Caminhada', 65, 79, '', ''),
(2, 75, 'Esteira', 64, 78, '', '');

-- --------------------------------------------------------

--
-- Estrutura para tabela `exercicios`
--

CREATE TABLE IF NOT EXISTS `exercicios` (
  `id` int(10) unsigned NOT NULL,
  `nome` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ordem` tinyint(3) unsigned DEFAULT NULL,
  `carga` double(8,2) DEFAULT NULL,
  `series` tinyint(3) unsigned DEFAULT NULL,
  `repeticoes` tinyint(3) unsigned DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Fazendo dump de dados para tabela `exercicios`
--

INSERT INTO `exercicios` (`id`, `nome`, `ordem`, `carga`, `series`, `repeticoes`, `created_at`, `updated_at`) VALUES
(1, 'Leg Pres', 4, 40.00, 3, 10, '2017-10-22 09:29:37', '2017-10-22 09:29:37'),
(2, 'Extensão', 5, 2.00, 3, 10, '2017-10-22 09:32:30', '2017-10-22 09:32:30'),
(3, 'Flexão', 2, 7.00, 3, 10, '2017-10-22 09:34:34', '2017-10-22 09:34:34'),
(4, 'Adução', 9, 6.00, 3, 10, '2017-10-22 09:35:10', '2017-10-22 09:35:10'),
(5, 'Abdução', 8, 6.00, 3, 10, '2017-10-22 09:35:56', '2017-10-22 09:35:56'),
(14, 'Rosca Roldana', 10, 10.00, 3, 10, '2017-10-22 13:00:42', '2017-10-22 13:00:42'),
(15, 'Rosca Tríceps', 0, 0.00, 0, 0, '2017-10-22 13:01:10', '2017-10-22 13:01:10'),
(16, 'Testa', 0, 0.00, 0, 0, '2017-10-22 13:01:32', '2017-10-22 13:01:32'),
(17, 'Rosca Direta', 3, 8.00, 3, 10, '2017-10-22 13:02:28', '2017-10-22 13:02:28'),
(18, 'Rosca Alternada', 0, 0.00, 0, 0, '2017-10-22 13:03:10', '2017-10-22 13:03:10'),
(19, 'Supino Reto', 0, 0.00, 0, 0, '2017-10-22 13:03:24', '2017-10-22 13:03:24'),
(20, 'Supino Inclinado', 0, 0.00, 0, 0, '2017-10-22 13:03:37', '2017-10-22 13:03:37');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Feedback`
--

CREATE TABLE IF NOT EXISTS `Feedback` (
  `codFeedback` smallint(11) NOT NULL,
  `cod` int(11) NOT NULL COMMENT 'Cod de varias tabelas ',
  `tabela` varchar(40) NOT NULL COMMENT 'Nome da tabela origem a linha',
  `notaAvalicao` int(11) NOT NULL,
  `codNotacao` int(11) NOT NULL COMMENT 'Tipo Utilitário id:13',
  `tempoAtividade` int(11) NOT NULL,
  `dataAvalicao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Fornecedores`
--

CREATE TABLE IF NOT EXISTS `Fornecedores` (
  `codForncedores` smallint(6) NOT NULL,
  `nomeFantazia` varchar(50) NOT NULL,
  `codPessoa` int(11) NOT NULL,
  `cnpj` varchar(20) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cep` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `grupo_musculars`
--

CREATE TABLE IF NOT EXISTS `grupo_musculars` (
  `id` int(10) unsigned NOT NULL,
  `nome` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Fazendo dump de dados para tabela `grupo_musculars`
--

INSERT INTO `grupo_musculars` (`id`, `nome`, `tipo`, `created_at`, `updated_at`) VALUES
(1, 'Tríceps', 'A', '2017-10-22 18:53:18', '2017-10-22 19:07:18'),
(2, 'Membros Inferiores', 'A', '2017-10-22 19:02:35', '2017-10-22 19:02:35'),
(3, 'Bíceps', 'A', '2017-10-22 19:39:00', '2017-10-22 19:39:00'),
(4, 'Peitoral', 'B', '2017-10-22 20:08:48', '2017-10-22 20:08:48'),
(5, 'Dorsais', 'B', '2017-10-22 20:09:04', '2017-10-22 20:09:04'),
(6, 'Deltóides', 'B', '2017-10-22 20:09:34', '2017-10-22 20:09:34'),
(7, 'Trapézio', 'B', '2017-10-22 20:09:52', '2017-10-22 20:09:52'),
(8, 'Abdominais', 'C', '2017-10-22 20:10:29', '2017-10-22 20:10:29'),
(9, 'Lombar', 'C', '2017-10-22 20:10:47', '2017-10-22 20:10:47');

-- --------------------------------------------------------

--
-- Estrutura para tabela `HabilitacaoPessoa`
--

CREATE TABLE IF NOT EXISTS `HabilitacaoPessoa` (
  `codHabilitacaoPessoa` smallint(6) NOT NULL,
  `codPessoa` int(11) NOT NULL,
  `codTipoHabilitacao` int(11) NOT NULL COMMENT 'Tipo Utilitário id:8',
  `Ativo` tinyint(1) NOT NULL,
  `codLocalidade` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `HabilitacaoPessoa`
--

INSERT INTO `HabilitacaoPessoa` (`codHabilitacaoPessoa`, `codPessoa`, `codTipoHabilitacao`, `Ativo`, `codLocalidade`) VALUES
(1, 1, 16, 1, 0),
(2, 2, 17, 1, 0),
(3, 3, 17, 1, 0),
(4, 4, 16, 1, 0),
(5, 5, 17, 1, 0),
(6, 6, 17, 1, 0),
(7, 7, 17, 1, 0),
(8, 1, 17, 1, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `migrations`
--

CREATE TABLE IF NOT EXISTS `migrations` (
  `id` int(10) unsigned NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Fazendo dump de dados para tabela `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(5, '2017_10_22_032003_create_exercicios_table', 2),
(7, '2017_10_22_161356_create_grupo_musculars_table', 3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `password_resets`
--

CREATE TABLE IF NOT EXISTS `password_resets` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Fazendo dump de dados para tabela `password_resets`
--

INSERT INTO `password_resets` (`email`, `token`, `created_at`) VALUES
('danieldiasrosa2016@gmail.com', '$2y$10$PnSFLvZRnmQIdPnE5fDoWudWeU47Pa1kTr2WvybYt3dMkAyqH4WR.', '2017-10-19 06:54:30'),
('ramprofissional@gmail.com', '$2y$10$rjH7QvBOXV.krSdZoWLbOuzsq1np1BV5k.LfMNVH4gqpRfopS7DZS', '2017-10-20 02:31:44');

-- --------------------------------------------------------

--
-- Estrutura para tabela `PerfisUsuario`
--

CREATE TABLE IF NOT EXISTS `PerfisUsuario` (
  `codPerfisUsuarios` smallint(6) NOT NULL,
  `Objeto` varchar(50) NOT NULL,
  `criar` tinyint(1) NOT NULL,
  `editar` tinyint(1) NOT NULL,
  `deletar` tinyint(1) NOT NULL,
  `consultar` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `PermisoesUsuarios`
--

CREATE TABLE IF NOT EXISTS `PermisoesUsuarios` (
  `codPermisoesUsuarios` smallint(6) NOT NULL,
  `idPerfisUsuario` int(11) NOT NULL,
  `codUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Pessoa`
--

CREATE TABLE IF NOT EXISTS `Pessoa` (
  `codPessoa` smallint(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `codTipoPessoa` int(11) NOT NULL COMMENT 'Tipo Utilitário id:3',
  `codProfissao` int(11) NOT NULL COMMENT 'Tipo Utilitário id:6',
  `cpf` varchar(20) NOT NULL,
  `Ativa` tinyint(1) NOT NULL,
  `email` varchar(50) NOT NULL,
  `cref` varchar(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 ROW_FORMAT=REDUNDANT;

--
-- Fazendo dump de dados para tabela `Pessoa`
--

INSERT INTO `Pessoa` (`codPessoa`, `nome`, `cidade`, `codTipoPessoa`, `codProfissao`, `cpf`, `Ativa`, `email`, `cref`) VALUES
(1, 'Jorge maia', 'Porto alegre', 23, 29, '223332123312', 1, '', ''),
(2, 'Neiamar gobbi', 'Porto alegre', 23, 26, '223332123312', 1, '', ''),
(3, 'Mariana', 'Porto alegre', 23, 26, '126565612767', 1, '', ''),
(4, 'Castor solverio', 'Porto alegre', 23, 29, '126565617788', 1, '', ''),
(5, 'Rodrigo avelar', 'Porto alegre', 23, 26, '165652128768', 1, '', ''),
(6, 'Daniel dias', 'Porto alegre', 23, 27, '125454128787', 1, '', ''),
(7, 'Aurelio santana', 'Porto alegre', 23, 28, '121545428787', 1, '', '');

-- --------------------------------------------------------

--
-- Estrutura para tabela `PlanoAtividadePessoal`
--

CREATE TABLE IF NOT EXISTS `PlanoAtividadePessoal` (
  `codPlanoAtividadePessoal` int(11) NOT NULL,
  `codTreino` int(11) NOT NULL,
  `codAluno` int(11) NOT NULL,
  `codTipoTreino` int(11) NOT NULL COMMENT 'Tipo Utilitário id:9',
  `codAvaliacaoAlunos` int(11) NOT NULL,
  `situacao` tinyint(1) NOT NULL,
  `dataIniciao` date NOT NULL,
  `dataFim` int(11) NOT NULL,
  `codObjeitvo` int(11) NOT NULL COMMENT 'Tipo Utilitário id:10',
  `valorMeta` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `ResultadosPeriodicos`
--

CREATE TABLE IF NOT EXISTS `ResultadosPeriodicos` (
  `codResultadosPeriodicos` smallint(6) NOT NULL,
  `codPlanoAtividadePessoal` int(11) NOT NULL,
  `pesoObjetivo` float NOT NULL COMMENT 'Peso objetivo de para emagrecimento',
  `pesoAtual` float NOT NULL COMMENT 'Peso no dia da avaliação ',
  `pesoSuporte` float NOT NULL COMMENT 'Peso referência em casos das atividades físicas estiverem surtindo efeito contrario (Engordando) ',
  `nroDias` int(11) NOT NULL,
  `dataAvalicao` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `ResultadosPeriodicos`
--

INSERT INTO `ResultadosPeriodicos` (`codResultadosPeriodicos`, `codPlanoAtividadePessoal`, `pesoObjetivo`, `pesoAtual`, `pesoSuporte`, `nroDias`, `dataAvalicao`) VALUES
(1, 1, 90, 99, 102, 90, '2017-10-09'),
(2, 2, 90, 95, 98, 90, '2018-02-09'),
(3, 3, 90, 92, 95, 90, '2018-05-09'),
(4, 4, 85, 85, 92, 90, '2018-09-09');

-- --------------------------------------------------------

--
-- Estrutura para tabela `SolicitacaoAvalicao`
--

CREATE TABLE IF NOT EXISTS `SolicitacaoAvalicao` (
  `codResultadosPeriodicos` smallint(6) NOT NULL,
  `codAluno` int(11) NOT NULL,
  `codProfessor` int(11) NOT NULL,
  `codSitucao` int(11) NOT NULL COMMENT 'Tipo Utilitário id:4',
  `dataAvalicação` date DEFAULT NULL,
  `hora` varchar(5) NOT NULL,
  `codLocalidade` int(11) NOT NULL,
  `codStatusSolicitacao` int(11) NOT NULL COMMENT 'Tipo Utilitário id:15'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `SolicitacaoAvalicao`
--

INSERT INTO `SolicitacaoAvalicao` (`codResultadosPeriodicos`, `codAluno`, `codProfessor`, `codSitucao`, `dataAvalicação`, `hora`, `codLocalidade`, `codStatusSolicitacao`) VALUES
(1, 1, 2, 3, NULL, '', 0, 61);

-- --------------------------------------------------------

--
-- Estrutura para tabela `TipoUtilitarios`
--

CREATE TABLE IF NOT EXISTS `TipoUtilitarios` (
  `codTipoUtilitario` smallint(20) unsigned NOT NULL,
  `descTipoUtilitario` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `TipoUtilitarios`
--

INSERT INTO `TipoUtilitarios` (`codTipoUtilitario`, `descTipoUtilitario`) VALUES
(3, 'Tipo pessoa'),
(4, 'Situação fisica'),
(5, 'Doenças'),
(6, 'Profissoções'),
(7, 'Tipo Exercícios'),
(8, 'Tipo habilitação pessoa'),
(9, 'Tipo de treinos'),
(10, 'Objetivo checkim'),
(12, 'Tipo execução atividade trieno'),
(13, 'Notação'),
(14, 'Nível de execução'),
(15, 'Status avaliação '),
(16, 'Tipo exercicio'),
(17, 'Ordem execução atividades'),
(18, 'Modalidade exercicios'),
(19, 'Tipo exercicios');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Treinos`
--

CREATE TABLE IF NOT EXISTS `Treinos` (
  `codTreinos` smallint(11) NOT NULL,
  `codProfessor` int(11) NOT NULL,
  `codTipoTreino` int(11) NOT NULL COMMENT 'Tipo Utilitário id:9',
  `descObjetivo` text NOT NULL,
  `qtDiasDuracao` int(11) NOT NULL,
  `qtDiasAvaliacao` int(11) NOT NULL,
  `ativo` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Treinos`
--

INSERT INTO `Treinos` (`codTreinos`, `codProfessor`, `codTipoTreino`, `descObjetivo`, `qtDiasDuracao`, `qtDiasAvaliacao`, `ativo`) VALUES
(1, 2, 19, 'Treino para iniciantes sem contra indicações', 90, 90, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Fazendo dump de dados para tabela `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'Rodrigo Avellar', 'ramprofissional@gmail.com', '$2y$10$Ud7OQFZR/sCxQGBTOeafG.dSGIDh1XDW0KwRp3UPzvXegL2f7yGxe', 'NbsEUnERxqM136GRE3NCpYv1IMiC82jC8vVE7UdtaqRnQK2uWNWLkSeYHw1p', '2017-10-08 13:08:13', '2017-10-08 13:39:15'),
(2, 'Daniel Dias', 'danieldiasrosa2016@gmail.com', '698dc19d489c4e4db73e28a713eab07b', 'FzihGiBBz3KQSpDckGNcwPHJ2EINfADekqJzlP1qaybc7rpZsuFUyjw26LmY', '2017-10-08 13:10:54', '2017-10-08 13:28:06'),
(3, 'Mariana Biancini', 'marianabianchini18@gmail.com', '$2y$10$ZdIls0mIuYfC8YFHAYRQb.k0Sq/7wup.rhDPUxREmyt0wvV3yIZXS', NULL, NULL, '2017-10-08 13:29:00'),
(4, 'Neimar Gobbi', 'neimarg@gmail.com', '$2y$10$mdz5Ab9G7W1YvxNXWw4Sxuy8D1B8lZPKlUHhtYmuR2uJynvIAAfgG', NULL, NULL, '2017-10-08 13:29:32'),
(5, 'Aurélio', 'aumasa2000@gmail.com', '$2y$10$rz5kM1djard2E7kXcglE8O81YQ9UIwx1ooaFnFs4ha6eZkvMyg0Ry', NULL, NULL, '2017-10-08 13:59:15'),
(11, 'Catharine Farrell', 'alisha.klein@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'um9wIlQAgv', '2017-10-22 19:27:26', '2017-10-22 19:27:26'),
(12, 'Isaac Hoppe', 'lou47@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'sTEqDJUrLp', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(13, 'Jordane Larson', 'weissnat.rebeka@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '8HZsdlmnzL', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(14, 'Dennis Graham', 'zkuphal@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '2U1kTEqa5t', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(15, 'Jillian Steuber', 'gprice@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '75RP9h3nlF', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(16, 'Adelbert Dickens', 'vada.luettgen@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'kAcLcki7bd', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(17, 'Ms. Kyla Towne', 'thea.orn@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'jWXPokwJbm', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(18, 'Kylie Ward II', 'dale56@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'aMpcKRpYjb', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(19, 'Prof. Felicity Konopelski IV', 'dillon56@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '9GnOH81ldv', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(20, 'Tressie Orn PhD', 'gzboncak@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'Ksy6NEWyrN', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(21, 'Dr. Eli Gorczany MD', 'hhagenes@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '664fmtoRqt', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(22, 'Mr. Jarvis Pollich', 'wisozk.kristopher@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'D6nCI3vZ0L', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(23, 'Baron Bayer', 'harber.merl@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'i9FlGWGEch', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(24, 'Magdalen Roob', 'pearl16@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'XQucNBglso', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(25, 'Pedro Ruecker', 'piper73@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'DuP8tLluRa', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(26, 'Dr. Dorris DuBuque PhD', 'flossie.miller@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'BvNVBko5QW', '2017-10-22 19:27:27', '2017-10-22 19:27:27'),
(27, 'Dr. Luna Wehner DDS', 'ansel.nolan@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'QRaJ97T2IK', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(28, 'Jillian Dach', 'poreilly@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'yRXm4bv4cL', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(29, 'Albertha Hansen', 'fatima.abbott@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'HzrWEPMTFY', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(30, 'Dillan Bode', 'emard.madalyn@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '7ruS59RYwX', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(31, 'Waldo Bergnaum II', 'fernser@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '0ahBLeIr6W', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(32, 'Mr. Fredy Lubowitz', 'ppfannerstill@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'tcithGycaD', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(33, 'Casimir Nienow', 'utremblay@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'jvKmFfsaCj', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(34, 'Jessyca West', 'pblick@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '8JV4Q64f1T', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(35, 'Miss Deborah Hegmann II', 'tre.walker@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '65LT5VX3b4', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(36, 'Max Mitchell', 'schoen.isabella@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'FYHeinwufD', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(37, 'Kitty Trantow', 'wyatt25@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'zobjTel9Ec', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(38, 'Sierra Schuppe', 'alivia.raynor@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'msr6QMXIkW', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(39, 'Colt Nolan', 'lbradtke@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '1hc7Znr59n', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(40, 'Ms. Asa McCullough V', 'santiago.kemmer@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'siAdVLO3J9', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(41, 'Brando Bosco', 'aidan53@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'VXqP6RbRfp', '2017-10-22 19:27:28', '2017-10-22 19:27:28'),
(42, 'Lavina Stehr IV', 'monte.kreiger@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'ibVGxVmqR8', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(43, 'Elna Hermann', 'marcus.hartmann@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'EnFNe6yUZr', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(44, 'Tillman Becker V', 'denesik.ludwig@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'KJl7jyFxzT', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(45, 'Bella Feest', 'thiel.jimmie@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', '3NNfwFmxlC', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(46, 'Bridget Hirthe Jr.', 'agustina.hamill@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'DAdEHpRQkm', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(47, 'Mr. Jeramie Rice', 'gregory.lesch@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'g3Dt4dUY2S', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(48, 'Mr. Blake Ratke', 'frederic.mckenzie@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'OOWMGXOEhl', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(49, 'Dr. Melody Lemke MD', 'jovani.stamm@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'DtXuNoQrE8', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(50, 'Marion Gutmann', 'carlie50@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'DRryhXXHFb', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(51, 'Domenick McDermott', 'reba.dooley@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'GIF5c8wvgi', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(52, 'Jonathon Kreiger PhD', 'stamm.willard@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 't944yJBSbM', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(53, 'Kaleigh Brown', 'rhoda43@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'aCiN86zA6Q', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(54, 'Ms. Katlyn Hartmann', 'jgusikowski@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'ITE3GxCjXu', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(55, 'Susana Balistreri', 'hickle.providenci@example.net', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'CHHEjiXKIJ', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(56, 'Renee Littel', 'xankunding@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'bS2dIRtcuR', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(57, 'Dr. Nathaniel Leuschke V', 'cole05@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'AN09qyQah8', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(58, 'Katharina Berge', 'leola20@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'vPqqlYRU1S', '2017-10-22 19:27:29', '2017-10-22 19:27:29'),
(59, 'Trystan Halvorson', 'marks.aylin@example.org', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'cQLJjcsus0', '2017-10-22 19:27:30', '2017-10-22 19:27:30'),
(60, 'Charlie Huels', 'carli44@example.com', '$2y$10$YrIq5lHYm39CTHF8jBnereL4wfNHf2.rJqs8ySdbxYbenNU80Ooee', 'qvXgBj4kc2', '2017-10-22 19:27:30', '2017-10-22 19:27:30');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Usuarios`
--

CREATE TABLE IF NOT EXISTS `Usuarios` (
  `id` smallint(6) NOT NULL,
  `codUsuarios` varchar(20) NOT NULL,
  `codPessoa` int(11) DEFAULT NULL,
  `senha` varchar(8) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Usuarios`
--

INSERT INTO `Usuarios` (`id`, `codUsuarios`, `codPessoa`, `senha`, `status`) VALUES
(1, 'neimar', 2, '1234', 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Utilitarios`
--

CREATE TABLE IF NOT EXISTS `Utilitarios` (
  `codUtilitario` smallint(11) NOT NULL,
  `utilitario` varchar(255) NOT NULL,
  `codTipoUtilirario` int(11) NOT NULL,
  `Obs` text NOT NULL,
  `codSubGrupo` int(11) NOT NULL,
  `favorita` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Utilitarios`
--

INSERT INTO `Utilitarios` (`codUtilitario`, `utilitario`, `codTipoUtilirario`, `Obs`, `codSubGrupo`, `favorita`) VALUES
(1, 'Não realizo atividades', 4, '', 0, 0),
(2, 'Realizo atividades', 4, '', 0, 0),
(3, 'Não realizo atividades', 4, '', 0, 0),
(4, 'Realizo atividades', 4, '', 0, 0),
(5, 'Sou atleta amador', 4, '', 0, 0),
(6, 'Ateleta profissional', 4, '', 0, 0),
(7, 'Cedentário', 4, '', 0, 0),
(8, 'Perna', 7, '', 0, 0),
(9, 'Lombar', 7, '', 0, 0),
(10, 'Hipertenção ', 5, '', 0, 0),
(11, 'Ostoporose', 5, '', 0, 0),
(12, 'Hipertenção ', 5, '', 0, 0),
(13, 'Ostoporose', 5, '', 0, 0),
(15, 'Administrador', 8, '', 0, 0),
(16, 'Personal treiner', 8, '', 0, 0),
(17, 'Aluno', 8, '', 0, 0),
(18, 'Intermediario', 9, '', 0, 0),
(19, 'Básico', 9, '', 0, 0),
(20, 'Intermidiario', 9, '', 0, 0),
(21, 'Avançado', 9, '', 0, 0),
(22, 'Profissional', 9, '', 0, 0),
(23, 'Fisica', 3, '', 0, 0),
(24, 'Juridica', 3, '', 0, 0),
(25, 'Ambos', 3, '', 0, 0),
(26, 'Engenheiro', 6, '', 0, 0),
(27, 'Estudante', 6, '', 0, 0),
(28, 'Programador', 6, '', 0, 0),
(29, 'Professor', 6, '', 0, 0),
(30, 'Faxineiro', 6, '', 0, 0),
(41, 'Perder peso', 10, '', 0, 0),
(42, 'Ganhar massa muscular', 10, '', 0, 0),
(43, 'Melhorar meu preparo fisico', 10, '', 0, 0),
(44, 'Treinamento profissional', 10, '', 0, 0),
(45, 'Treinamento para competir', 10, '', 0, 0),
(49, 'Diário', 12, '', 0, 0),
(50, 'Dia sim dia não', 12, '', 0, 0),
(51, 'Semanal', 12, '', 0, 0),
(52, 'Quinsenal', 12, '', 0, 0),
(53, 'Série', 13, '', 0, 0),
(54, 'Hs', 13, '', 0, 0),
(55, 'Dias', 13, '', 0, 0),
(56, 'Semanas', 13, '', 0, 0),
(57, 'Série', 13, '', 0, 0),
(58, 'Km', 13, '', 0, 0),
(61, 'Agendado', 15, '', 0, 0),
(62, 'Avaliado', 15, '', 0, 0),
(63, 'Pendente', 15, '', 0, 0),
(64, 'Leve', 14, '', 0, 0),
(65, 'Pesado ', 14, '', 0, 0),
(66, 'Profissional', 14, '', 0, 0),
(67, 'Peitoral', 16, '', 0, 0),
(68, 'Triceps', 16, '', 0, 0),
(70, 'A', 17, '', 0, 0),
(71, 'B', 17, '', 0, 0),
(72, 'C', 17, '', 0, 0),
(73, 'D', 17, '', 0, 0),
(74, '*', 17, '', 0, 0),
(75, 'Ginastica', 18, '', 0, 0),
(76, 'Hidroginastica', 18, '', 0, 0),
(77, 'Pilates', 18, '', 0, 0),
(78, 'Orientada', 19, '', 0, 0),
(79, 'Livre', 19, '', 0, 0),
(80, 'Membros Inferiores', 16, '', 0, 0),
(82, 'Dorsais', 16, '', 0, 0),
(83, 'Deltóides', 16, '', 0, 0),
(85, 'Abdominais', 16, '', 0, 0),
(86, 'Lombar', 16, '', 0, 0),
(88, 'Bíceps', 16, '', 0, 0),
(89, 'Bíceps', 16, '', 0, 0);

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `AgendaTreinamentos`
--
ALTER TABLE `AgendaTreinamentos`
  ADD PRIMARY KEY (`codAgendaTreinamentos`);

--
-- Índices de tabela `AtividadesTreino`
--
ALTER TABLE `AtividadesTreino`
  ADD PRIMARY KEY (`codAtividadesTreino`);

--
-- Índices de tabela `AvaliacaoAluno`
--
ALTER TABLE `AvaliacaoAluno`
  ADD PRIMARY KEY (`codAvaliacaoAluno`);

--
-- Índices de tabela `ChekinPessoal`
--
ALTER TABLE `ChekinPessoal`
  ADD PRIMARY KEY (`codChekeinPessoal`);

--
-- Índices de tabela `Exercicios`
--
ALTER TABLE `Exercicios`
  ADD PRIMARY KEY (`codExercicio`);

--
-- Índices de tabela `exercicios`
--
ALTER TABLE `exercicios`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `Feedback`
--
ALTER TABLE `Feedback`
  ADD PRIMARY KEY (`codFeedback`);

--
-- Índices de tabela `grupo_musculars`
--
ALTER TABLE `grupo_musculars`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `HabilitacaoPessoa`
--
ALTER TABLE `HabilitacaoPessoa`
  ADD PRIMARY KEY (`codHabilitacaoPessoa`);

--
-- Índices de tabela `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Índices de tabela `PerfisUsuario`
--
ALTER TABLE `PerfisUsuario`
  ADD PRIMARY KEY (`codPerfisUsuarios`);

--
-- Índices de tabela `PermisoesUsuarios`
--
ALTER TABLE `PermisoesUsuarios`
  ADD PRIMARY KEY (`codPermisoesUsuarios`);

--
-- Índices de tabela `Pessoa`
--
ALTER TABLE `Pessoa`
  ADD PRIMARY KEY (`codPessoa`);

--
-- Índices de tabela `ResultadosPeriodicos`
--
ALTER TABLE `ResultadosPeriodicos`
  ADD PRIMARY KEY (`codResultadosPeriodicos`);

--
-- Índices de tabela `SolicitacaoAvalicao`
--
ALTER TABLE `SolicitacaoAvalicao`
  ADD PRIMARY KEY (`codResultadosPeriodicos`);

--
-- Índices de tabela `TipoUtilitarios`
--
ALTER TABLE `TipoUtilitarios`
  ADD PRIMARY KEY (`codTipoUtilitario`), ADD UNIQUE KEY `codTipoUtilitario` (`codTipoUtilitario`);

--
-- Índices de tabela `Treinos`
--
ALTER TABLE `Treinos`
  ADD PRIMARY KEY (`codTreinos`);

--
-- Índices de tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- Índices de tabela `Usuarios`
--
ALTER TABLE `Usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `Utilitarios`
--
ALTER TABLE `Utilitarios`
  ADD PRIMARY KEY (`codUtilitario`), ADD UNIQUE KEY `codUtilitario` (`codUtilitario`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `AgendaTreinamentos`
--
ALTER TABLE `AgendaTreinamentos`
  MODIFY `codAgendaTreinamentos` smallint(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `AtividadesTreino`
--
ALTER TABLE `AtividadesTreino`
  MODIFY `codAtividadesTreino` smallint(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `AvaliacaoAluno`
--
ALTER TABLE `AvaliacaoAluno`
  MODIFY `codAvaliacaoAluno` smallint(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `ChekinPessoal`
--
ALTER TABLE `ChekinPessoal`
  MODIFY `codChekeinPessoal` smallint(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de tabela `Exercicios`
--
ALTER TABLE `Exercicios`
  MODIFY `codExercicio` smallint(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `exercicios`
--
ALTER TABLE `exercicios`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT de tabela `Feedback`
--
ALTER TABLE `Feedback`
  MODIFY `codFeedback` smallint(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `grupo_musculars`
--
ALTER TABLE `grupo_musculars`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de tabela `HabilitacaoPessoa`
--
ALTER TABLE `HabilitacaoPessoa`
  MODIFY `codHabilitacaoPessoa` smallint(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de tabela `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de tabela `PerfisUsuario`
--
ALTER TABLE `PerfisUsuario`
  MODIFY `codPerfisUsuarios` smallint(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `PermisoesUsuarios`
--
ALTER TABLE `PermisoesUsuarios`
  MODIFY `codPermisoesUsuarios` smallint(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `Pessoa`
--
ALTER TABLE `Pessoa`
  MODIFY `codPessoa` smallint(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de tabela `ResultadosPeriodicos`
--
ALTER TABLE `ResultadosPeriodicos`
  MODIFY `codResultadosPeriodicos` smallint(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de tabela `SolicitacaoAvalicao`
--
ALTER TABLE `SolicitacaoAvalicao`
  MODIFY `codResultadosPeriodicos` smallint(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `TipoUtilitarios`
--
ALTER TABLE `TipoUtilitarios`
  MODIFY `codTipoUtilitario` smallint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT de tabela `Treinos`
--
ALTER TABLE `Treinos`
  MODIFY `codTreinos` smallint(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=62;
--
-- AUTO_INCREMENT de tabela `Usuarios`
--
ALTER TABLE `Usuarios`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `Utilitarios`
--
ALTER TABLE `Utilitarios`
  MODIFY `codUtilitario` smallint(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=90;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
