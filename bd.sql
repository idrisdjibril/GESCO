-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 14 août 2024 à 01:11
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd`
--

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `idClasse` int(11) NOT NULL,
  `idEns` int(11) NOT NULL,
  `niveauClasse` varchar(254) NOT NULL,
  `NomClasse` varchar(254) NOT NULL,
  `secteurClasse` varchar(254) NOT NULL,
  `effectif` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`idClasse`, `idEns`, `niveauClasse`, `NomClasse`, `secteurClasse`, `effectif`) VALUES
(1, 1, '1', '6e1', 'francophone', 60),
(2, 2, '1', '6e2', 'francophone', 60);

-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

CREATE TABLE `discipline` (
  `idDiscipline` int(11) NOT NULL,
  `idEl` int(11) NOT NULL,
  `nomSurveillant` varchar(254) DEFAULT NULL,
  `date` varchar(254) NOT NULL,
  `absJus` int(11) DEFAULT NULL,
  `absNonJus` int(11) DEFAULT NULL,
  `totalAbs` int(11) DEFAULT NULL,
  `conduite` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

CREATE TABLE `eleve` (
  `idEl` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL,
  `matEl` varchar(254) NOT NULL,
  `nomEleve` varchar(254) NOT NULL,
  `prenomEl` varchar(254) NOT NULL,
  `dateNaissance` varchar(50) NOT NULL,
  `lieuNaissance` varchar(254) NOT NULL,
  `sexe` varchar(254) NOT NULL,
  `email` varchar(254) NOT NULL,
  `phone` varchar(254) NOT NULL,
  `nomPereEleve` varchar(254) NOT NULL,
  `nomMereEleve` varchar(254) NOT NULL,
  `residence` varchar(254) NOT NULL,
  `image` varchar(254) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `eleve`
--

INSERT INTO `eleve` (`idEl`, `idClasse`, `matEl`, `nomEleve`, `prenomEl`, `dateNaissance`, `lieuNaissance`, `sexe`, `email`, `phone`, `nomPereEleve`, `nomMereEleve`, `residence`, `image`) VALUES
(1, 1, '2024_GESCO_1001', 'ABA\'A ONDOA', 'Fabrice', '2010-01-04', 'yaounde', 'Masc', 'gesco@fabrice.com', '699887766', 'ONDOA Paul', 'MESSA Jeanne', 'awae', 'C:\\Users\\LENOVO\\projet-java\\Ecole\\src\\images\\eka.png');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `idEns` int(11) NOT NULL,
  `matEns` varchar(254) NOT NULL,
  `nomEns` varchar(254) NOT NULL,
  `prenomEns` varchar(254) NOT NULL,
  `sexe` varchar(254) NOT NULL,
  `emailEns` varchar(254) NOT NULL,
  `phonEns` varchar(254) NOT NULL,
  `specialEns` varchar(254) NOT NULL,
  `matierEns` varchar(254) NOT NULL,
  `gradEns` varchar(254) NOT NULL,
  `residence` varchar(254) NOT NULL,
  `photoEns` varchar(254) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`idEns`, `matEns`, `nomEns`, `prenomEns`, `sexe`, `emailEns`, `phonEns`, `specialEns`, `matierEns`, `gradEns`, `residence`, `photoEns`) VALUES
(1, '2024_GESCO_p100', 'user', 'second', 'Masc', 'user@gmail.com', '699881708', 'Langues', 'mathematiques', 'Dipes2', 'yaounde', 'C:\\Users\\LENOVO\\Pictures\\images Ecoles\\tongcos.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `frais`
--

CREATE TABLE `frais` (
  `idPaie` int(11) NOT NULL,
  `idEl` int(11) NOT NULL,
  `motif` varchar(254) NOT NULL,
  `montant` double NOT NULL,
  `datPaie` varchar(254) NOT NULL,
  `annScolaire` varchar(254) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `frais`
--

INSERT INTO `frais` (`idPaie`, `idEl`, `motif`, `montant`, `datPaie`, `annScolaire`) VALUES
(1, 1, 'Inscription', 12000, '2024-08-09', '2024-2025'),
(2, 1, 'Tranche 1', 20000, '2024-08-05', '2024-2025'),
(3, 1, 'Tranche 2', 300, '2024-08-07', '2024-2025');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `idMat` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL,
  `idEns` int(11) NOT NULL,
  `nomMat` varchar(254) NOT NULL,
  `coefMat` int(11) NOT NULL,
  `groupMat` varchar(254) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `idNote` int(11) NOT NULL,
  `idEl` int(11) NOT NULL,
  `idEns` int(11) DEFAULT NULL,
  `idMat` int(11) DEFAULT NULL,
  `note` double DEFAULT NULL,
  `date` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `presence`
--

CREATE TABLE `presence` (
  `idPre` int(11) NOT NULL,
  `idEl` int(11) NOT NULL,
  `datPre` varchar(255) NOT NULL,
  `StatutPres` varchar(254) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `statut` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `username`, `password`, `role`, `statut`) VALUES
(1, 'user', 'user123', 'admin', '1'),
(2, 'idris', 'idris', 'administrateur', '1'),
(3, 'user1', 'user1', 'enseignant', '0'),
(4, 'user2', 'user2', 'enseignant', '0');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`idClasse`),
  ADD UNIQUE KEY `idEns` (`idEns`);

--
-- Index pour la table `discipline`
--
ALTER TABLE `discipline`
  ADD PRIMARY KEY (`idDiscipline`),
  ADD UNIQUE KEY `DISCIPLINE_ELEVE_PK` (`idDiscipline`),
  ADD UNIQUE KEY `idEl` (`idEl`),
  ADD UNIQUE KEY `nomSurveillant` (`nomSurveillant`),
  ADD KEY `ASSOCIATION_12_FK` (`nomSurveillant`);

--
-- Index pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD PRIMARY KEY (`matEl`),
  ADD UNIQUE KEY `idEl` (`idEl`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`matEns`),
  ADD UNIQUE KEY `ENSEIGNANT_PK` (`idEns`);

--
-- Index pour la table `frais`
--
ALTER TABLE `frais`
  ADD PRIMARY KEY (`idPaie`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`idMat`),
  ADD UNIQUE KEY `idClasse` (`idClasse`),
  ADD UNIQUE KEY `idEns` (`idEns`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`idNote`),
  ADD UNIQUE KEY `idEl` (`idEl`,`idEns`,`idMat`),
  ADD KEY `APPARTENIR_FK` (`idEl`);

--
-- Index pour la table `presence`
--
ALTER TABLE `presence`
  ADD PRIMARY KEY (`idPre`),
  ADD UNIQUE KEY `idEl` (`idEl`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `idClasse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `discipline`
--
ALTER TABLE `discipline`
  MODIFY `idDiscipline` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `eleve`
--
ALTER TABLE `eleve`
  MODIFY `idEl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `enseignant`
--
ALTER TABLE `enseignant`
  MODIFY `idEns` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `frais`
--
ALTER TABLE `frais`
  MODIFY `idPaie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `idMat` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `idNote` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `presence`
--
ALTER TABLE `presence`
  MODIFY `idPre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
