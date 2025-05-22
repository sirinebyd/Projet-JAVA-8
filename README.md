# Projet Java 8 - CY-SIAO

**Application de gestion d’hébergement social** développée en JavaFX avec une base de données SQLite.  
Ce projet permet de gérer les chambres, lits, personnes hébergées et leurs affectations, avec interface graphique.

---

## Fonctionnalités principales

- Gestion des **personnes** (nom, prénom, date de naissance, etc.)
- Gestion des **chambres** (capacités, restrictions d'âge ou de genre)
- Gestion des **lits** liés aux chambres
- Création d'**occupations** (assignation de personnes à des lits)
- Interface JavaFX dynamique avec chargement des données
- Filtres pour déterminer les chambres disponibles selon des critères (âge, genre, etc.)

---

## Technologies utilisées

| Outil          | Description                         |
|----------------|-------------------------------------|
| Java 17+       | Langage principal                   |
| JavaFX         | Interface utilisateur graphique     |
| FXML           | Description des vues JavaFX         |
| SQLite         | Base de données locale              |
| JDBC           | Connexion base ↔ Java               |
| Maven          | Gestion des dépendances             |

---

## Structure du projet
```text
Projet-JAVA-8/
├── src/
│   └── main/
│       ├── java/
│       │   └── org.house.projetjava8/
│       │       ├── model/       # Entités : Person, Room, Bed, etc.
│       │       ├── dao/         # Accès base de données (DAO)
│       │       ├── service/     # Logique métier (filtrage, validation)
│       │       ├── ui/          # Contrôleurs JavaFX (FXML)
│       │       └── db/          # Connexion à SQLite (DataManager)
│       └── resources/
|           └── db
|               └── database.db  # Base de données SQLite
|           └── img
│           └── org.house.projetjava8.ui/
│               └── *.fxml       # Interfaces graphiques (FXML)
|              
├── pom.xml                      # Configuration Maven
└── README.md                    # Présentation du projet
```
