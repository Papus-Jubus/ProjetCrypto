# ProjetCrypto
Projet Cryptographie avec JavaFX
Ce projet est une application de cryptographie développée en Java avec JavaFX pour l'interface graphique. L'objectif est de fournir une interface utilisateur intuitive permettant de générer des clés, d'effectuer des opérations cryptographiques (symétriques et asymétriques), de gérer des utilisateurs et de stocker les clés générées de manière sécurisée. L'application repose sur les algorithmes de chiffrement couramment utilisés et propose une gestion flexible des clés selon le type de chiffrement sélectionné.

Fonctionnalités principales
Génération de clés : Génération de clés pour les algorithmes de chiffrement symétriques et asymétriques avec la possibilité de sélectionner l'algorithme, la taille de la clé, et le fournisseur.
Chiffrement/Déchiffrement :
Chiffrement symétrique (AES, DES, 3DES)
Chiffrement asymétrique (RSA, DSA)
Partage de clé Diffie-Hellman : Implémentation du protocole Diffie-Hellman pour l'échange sécurisé de clés.
Hashage : Calcul de hachages avec ou sans clé (HMAC, SHA, etc.).
Signature numérique : Signature des messages et vérification des signatures à l'aide de clés privées et publiques.
Stockage sécurisé : Enregistrement et gestion des clés générées dans une base de données sécurisée.
Interface utilisateur intuitive : Une interface graphique avec JavaFX, permettant de sélectionner les paramètres de chiffrement via des menus déroulants (ComboBox).
Structure du projet
Le projet suit une architecture MVC (Model-View-Controller) avec la séparation des responsabilités entre les différentes couches.

Model : Contient la logique métier, y compris les algorithmes de chiffrement, la génération et le stockage des clés.
View : Basé sur JavaFX et conçu à l'aide de SceneBuilder, fournit une interface utilisateur intuitive pour interagir avec l'application.
Controller : Gère les interactions entre la vue et le modèle. Les contrôleurs capturent les événements utilisateur (comme la sélection de l'algorithme, la génération de clé) et exécutent les actions appropriées.
Technologies utilisées
Java : Langage principal pour le développement.
JavaFX : Framework pour l'interface graphique.
SceneBuilder : Outil pour concevoir visuellement les interfaces JavaFX.
JCE (Java Cryptography Extension) : Pour les opérations cryptographiques.
JDBC : Pour le stockage sécurisé des clés dans une base de données.
FXML : Pour la description de l'interface utilisateur.
Prérequis
JDK 11+ avec support pour JavaFX 11+
JavaFX SDK : Utilisé pour l'interface graphique.
Une base de données relationnelle (MySQL, PostgreSQL) pour le stockage des clés (si configurée).


