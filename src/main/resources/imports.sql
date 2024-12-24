INSERT INTO ticket (id, titre, description, date_creation, date_mise_a_jour, impact, etat, utilisateur_createur, poste_informatique, type_demande) VALUES
('1', 'Probleme de connexion reseau', 'Utilisateur Patrick ne peut pas se connecter au reseau local depuis son poste informatique', '2024-12-12 09:00:00', '2024-12-12 11:30:00', 'Bloquant', 'En cours', 'Patrick', '1', 'Probleme reseau'),
('2', 'Mise a jour de logiciels', 'Installation de la derniere version de Microsoft Office sur le poste de Marylise.', '2024-12-11 14:15:00', '2024-12-11 16:45:00', 'Mineur', 'Resolu', 'Marylise', '2', 'Mise a jour logicielle'),
('3', 'Demande de materiel', 'Commande d une nouvelle souris ergonomique pour Jean.', '2024-12-10 10:30:00', '2024-12-10 12:00:00', 'Mineur', 'Ferme', 'Jean', '3', 'Demande de materiel');

INSERT INTO user (id, nom, email, mot_de_passe, date_derniere_connexion, statut, role) VALUES
('1', 'Patrick', 'patrick@example.com', 'password123', '2024-12-12 08:30:00', 'Oui', 'utilisateur'),
('2', 'Marylise', 'marylise@example.com', 'securepass456', '2024-12-11 15:00:00', 'Oui', 'intervenant'),
('3', 'Jean', 'jean@example.com', 'mypassword789', '2024-12-10 13:45:00', 'Non', 'utilisateur');

INSERT INTO device (id, utilisateur_affecte, etat, configuration) VALUES
('1', 'Patrick', 'En fonction', 'Processeur: Intel Core i7-11700, RAM: 16 Go DDR4, Stockage: SSD 512 Go, Carte graphique: NVIDIA GeForce GTX 1660, Système d exploitation: Windows 11 Pro'),
('2', 'Marylise', 'En maintenance', 'Processeur: AMD Ryzen 5 3600, RAM: 8 Go DDR4, Stockage: HDD 1 To, Carte graphique: AMD Radeon RX 550, Système d exploitation: Windows 10 Pro'),
('3', 'Jean', 'En commande', 'Processeur: Intel Core i5-10400, RAM: 8 Go DDR4, Stockage: SSD 256 Go, Carte graphique: Intégrée (Intel UHD 630), Système d exploitation: Ubuntu 20.04');
