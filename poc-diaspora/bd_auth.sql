-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 04 mai 2022 à 16:47
-- Version du serveur : 10.4.19-MariaDB
-- Version de PHP : 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd_auth`
--

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(19);

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

CREATE TABLE `profil` (
  `id` bigint(20) NOT NULL,
  `nom_profil` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `profil`
--

INSERT INTO `profil` (`id`, `nom_profil`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `td_otpconfiguration`
--

CREATE TABLE `td_otpconfiguration` (
  `id_otp` bigint(20) NOT NULL,
  `val_duree` bigint(20) DEFAULT NULL,
  `valcode` varchar(255) DEFAULT NULL,
  `dejavalide` bit(1) NOT NULL,
  `otp_val_date_creation` datetime DEFAULT NULL,
  `parametre_otp` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `td_otpconfiguration`
--

INSERT INTO `td_otpconfiguration` (`id_otp`, `val_duree`, `valcode`, `dejavalide`, `otp_val_date_creation`, `parametre_otp`) VALUES
(1, 5, '810550', b'0', '2022-04-19 14:01:46', 1),
(3, 25, '729606', b'1', '2022-04-19 15:29:45', 1),
(4, 25, '899122', b'1', '2022-04-19 16:00:58', 1),
(5, 5, '207833', b'0', '2022-04-20 09:44:26', 1),
(6, 5, '847238', b'0', '2022-04-20 10:27:02', 1),
(7, 30, '809210', b'0', '2022-04-20 10:28:31', 1),
(8, 5, '311661', b'1', '2022-04-20 11:09:25', 1),
(9, 5, '298839', b'1', '2022-04-20 11:13:31', 1),
(10, 5, '705936', b'0', '2022-04-20 11:48:41', 1),
(11, 5, '872430', b'1', '2022-04-20 12:18:14', 1),
(12, 5, '413589', b'1', '2022-04-20 12:19:45', 1),
(13, 5, '110065', b'1', '2022-04-20 12:24:08', 1),
(14, 5, '778102', b'0', '2022-04-20 12:41:59', 1),
(15, 5, '189321', b'1', '2022-04-20 12:50:20', 1),
(16, 5, '178049', b'0', '2022-04-20 12:45:22', 1),
(17, 5, '980438', b'0', '2022-04-20 12:45:40', 1),
(18, 5, '984490', b'0', '2022-04-20 12:45:59', 1);

-- --------------------------------------------------------

--
-- Structure de la table `td_parametreotp`
--

CREATE TABLE `td_parametreotp` (
  `id_parametre_otp` bigint(20) NOT NULL,
  `opt_caractere` varchar(255) DEFAULT NULL,
  `opt_duree` double DEFAULT NULL,
  `opt_dureee` varchar(50) DEFAULT NULL,
  `opt_evenement` varchar(255) DEFAULT NULL,
  `opt_libelle` varchar(255) DEFAULT NULL,
  `otp_type` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `td_parametreotp`
--

INSERT INTO `td_parametreotp` (`id_parametre_otp`, `opt_caractere`, `opt_duree`, `opt_dureee`, `opt_evenement`, `opt_libelle`, `otp_type`) VALUES
(1, 'chiffre', 5, 'minute', 'validation-inscription', 'validation-inscription', 'Email'),
(2, 'chiffre', 5, 'minute', 'validation-inscription', 'validation-inscription-sms', 'SMS');

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `application` varchar(255) DEFAULT NULL,
  `date_transaction` datetime DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `transaction`
--

INSERT INTO `transaction` (`id`, `action`, `application`, `date_transaction`, `user`) VALUES
(1, 'liste', 'fra', NULL, 17),
(2, 'consultationliste', 'fra-service', NULL, NULL),
(3, 'consultationliste', 'fra-service', NULL, NULL),
(4, 'liste', 'fra', '2022-04-21 12:43:29', NULL),
(7, 'Consultation Liste fra', 'Fra-service', '2022-04-21 14:34:01', 17),
(8, 'Suivi d\'une demande Fra', 'Service demande Fra', '2022-04-21 15:16:12', 17),
(9, 'Suivi d\'une demande Fra', 'Service demande Fra', '2022-04-21 15:16:12', 17),
(15, 'Suivi d\'une demande Fra', 'Service demande Fra', '2022-04-21 15:21:05', 17),
(16, 'Liste des permis', 'Service demande de permis', '2022-04-26 10:00:54', 1),
(25, 'Demande de permis', 'Service demande de permis', '2022-04-28 09:53:05', 17),
(26, 'Demande de permis', 'Service demande de permis', '2022-04-28 09:53:40', 17),
(27, 'Demande de permis', 'Service demande de permis', '2022-04-28 09:54:24', 17),
(28, 'Suivi d\'une demande Fra', 'Service demande Fra', '2022-04-28 10:24:37', 17),
(29, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:25:46', 17),
(30, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:26:08', 17),
(31, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:26:08', 17),
(32, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:30:33', 17),
(33, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:30:33', 17),
(34, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:30:42', 17),
(35, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:35:46', 17),
(36, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:36:23', 17),
(37, 'Suivi d\'une demande Fra', 'Service demande Fra', '2022-04-28 10:43:11', 17),
(38, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:47:39', 17),
(39, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:57:24', 17),
(40, 'Demande de permis', 'Service demande de permis', '2022-04-28 10:59:18', 17),
(41, 'Demande de permis', 'Service demande de permis', '2022-04-28 11:03:41', 17),
(42, 'Demande de permis', 'Service demande de permis', '2022-04-28 11:08:51', 17),
(43, 'Demande de permis', 'Service demande de permis', '2022-04-28 11:10:24', 17),
(44, 'Demande de permis', 'Service demande de permis', '2022-04-28 11:10:51', 17),
(45, 'Demande de permis', 'Service demande de permis', '2022-04-28 11:12:40', 17),
(46, 'Demande de permis', 'Service demande de permis', '2022-04-28 11:58:42', 17),
(47, 'Demande de permis', 'Service demande de permis', '2022-04-28 11:59:17', 17),
(48, 'Prise de rendez-vous', 'RVMedical-service', '2022-04-28 12:02:07', 17),
(49, 'Demande de permis', 'Service demande de permis', '2022-04-28 12:02:59', 17),
(50, 'Demande de permis', 'Service demande de permis', '2022-04-28 12:05:47', 17),
(51, 'Demande de permis', 'Service demande de permis', '2022-04-28 12:48:43', 17),
(52, 'Demande de permis', 'Service demande de permis', '2022-04-28 12:48:45', 17),
(53, 'Demande de permis', 'Service demande de permis', '2022-04-28 12:48:46', 17),
(54, 'Demande de permis', 'Service demande de permis', '2022-04-28 12:54:48', 17),
(55, 'Demande de permis', 'Service demande de permis', '2022-04-28 12:54:54', 17),
(56, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:36:23', 17),
(57, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:37:04', 17),
(58, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:37:49', 17),
(59, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:40:43', 17),
(60, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:41:35', 17),
(61, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:42:58', 17),
(62, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:43:57', 17),
(63, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:47:47', 17),
(64, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:48:35', 17),
(65, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:54:39', 17),
(66, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:55:13', 17),
(67, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:58:29', 17),
(68, 'Demande de permis', 'Service demande de permis', '2022-04-28 13:59:42', 17),
(69, 'Demande de permis', 'Service demande de permis', '2022-04-28 14:02:32', 17),
(70, 'Demande de permis', 'Service demande de permis', '2022-04-28 14:08:59', 17),
(71, 'Prise de rendez-vous', 'RVMedical-service', '2022-04-28 14:35:38', 17),
(72, 'Prise de rendez-vous', 'RVMedical-service', '2022-04-28 14:49:59', 17),
(73, 'Prise de rendez-vous', 'RVMedical-service', '2022-04-28 14:50:03', 17),
(74, 'Prise de rendez-vous', 'RVMedical-service', '2022-04-28 14:58:50', 17),
(75, 'Prise de rendez-vous', 'RVMedical-service', '2022-04-28 15:01:03', 17),
(76, 'Prise de rendez-vous', 'RVMedical-service', '2022-04-28 15:02:31', 17),
(77, 'Demande de permis', 'Service demande de permis', '2022-04-28 15:09:51', 17),
(78, 'Demande de permis', 'Service demande de permis', '2022-04-28 15:10:03', 17),
(79, 'Suivi d\'une demande Fra', 'Service demande Fra', '2022-04-28 15:10:55', 17);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `profil_id` bigint(20) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `email`, `nom`, `password`, `prenom`, `telephone`, `username`, `profil_id`, `user_status`) VALUES
(1, 'aseye@gmail.com', 'seye', '$2a$10$/PzXKtlNNfq8qsF6/AjKDekGH1DZ0j1QFEiCnqaKaas9KG477CW6C', 'adama', '778094925', 'adam123', 1, 1),
(2, 'babacar@gmail.com', 'beye', '$2a$10$ddF8D5Kx4t8Vq3/nBn02Z.sTaDygLMKsWI.NyUPy/eQcQXXBx4CAS', 'babacar', NULL, 'babacar', 1, NULL),
(3, 'abdiallo@gainde2000.sn', 'Diallo', '$2a$10$rPNM8xgr98BsIkQGPhhaouFUNU4ahjG31FhC5Xe8oFnpzX/PJ8ZFK', 'Bassirou', NULL, 'koula', 1, NULL),
(4, 'kniang@diaspora.sn', 'Niang', '$2a$10$Mt523HsLmgY4DtZmWRgqmeoyWj23Hr0LF/ErnDotXqRS5k8GAbOY2', 'Khady', NULL, 'kniang', 1, 1),
(7, 'babacar12@gmail.com', 'gueye', '$2a$10$afbXezgSXM0MWfdrw5UAHO.rSd6w0rEl17o1uFRrVkam0AvXT6/rW', 'babacar', '779495959', 'babs', 1, 0),
(16, 'a.seye3777@zig.univ.sn', 'Seye', '$2a$10$B7s0WnAKAEwToncDcpjUi.UMew2lOE4hvciq5KhLfPkh1HZfc./NO', 'Adama', '778563468', 'aseye1', 1, 1),
(17, 'moubass.diallo@gmail.com', 'Diallo', '$2a$10$/PzXKtlNNfq8qsF6/AjKDekGH1DZ0j1QFEiCnqaKaas9KG477CW6C', 'Bassirou', '77655888', 'bass', 1, 1),
(18, 'modouxdiop@gmail.com', 'Diop', '$2a$10$JkCqiAgMU4kzyu1PshEY4Ovdi40Es6QhbpIcZvTuKsp.LUZdBQyye', 'Modou', '7777777777', 'modoux', 1, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `profil`
--
ALTER TABLE `profil`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `td_otpconfiguration`
--
ALTER TABLE `td_otpconfiguration`
  ADD PRIMARY KEY (`id_otp`),
  ADD KEY `FKh2ubyhrhpwcpr3xgyh4a3xu3m` (`parametre_otp`);

--
-- Index pour la table `td_parametreotp`
--
ALTER TABLE `td_parametreotp`
  ADD PRIMARY KEY (`id_parametre_otp`),
  ADD UNIQUE KEY `UK_fkbx2pj1k7kh46mi71pgypn07` (`opt_libelle`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjolu7lm52cuxqhmgny4i8qkdk` (`user`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6ldvumu3hqvnmmxy1b6lsxwqy` (`email`),
  ADD UNIQUE KEY `UK_6wo7b684svg7hmrpxnkbf4uun` (`telephone`),
  ADD UNIQUE KEY `UK_l8ivthvih63lgxwa4uqwmbqj9` (`username`),
  ADD KEY `FKo6jvc68160vdsntqxcbdslmls` (`profil_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `profil`
--
ALTER TABLE `profil`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `td_parametreotp`
--
ALTER TABLE `td_parametreotp`
  MODIFY `id_parametre_otp` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `td_otpconfiguration`
--
ALTER TABLE `td_otpconfiguration`
  ADD CONSTRAINT `FKh2ubyhrhpwcpr3xgyh4a3xu3m` FOREIGN KEY (`parametre_otp`) REFERENCES `td_parametreotp` (`id_parametre_otp`);

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FKjolu7lm52cuxqhmgny4i8qkdk` FOREIGN KEY (`user`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD CONSTRAINT `FKo6jvc68160vdsntqxcbdslmls` FOREIGN KEY (`profil_id`) REFERENCES `profil` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
