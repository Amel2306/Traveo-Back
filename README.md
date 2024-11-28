# Traveo-Back

## 🌟 Lancer l'Application : Back  🌟

Bienvenue dans ce projet ! Ce guide vous aidera à configurer et lancer l'application en suivant quelques étapes simples. 🚀

---

### 📂 Pré-requis

Avant de commencer, assurez-vous d'avoir installé les outils suivants :

Docker (et Docker Compose)
Java (JDK 17 recommandé)
Un IDE tel qu'IntelliJ IDEA ou Eclipse
Git (pour cloner le projet)
Gradle

---

### ⚙️ Étapes pour lancer le back-end

##### 1. Cloner le projet
Clonez le projet depuis le dépôt Git en utilisant la commande suivante dans votre terminal :

```git clone https://github.com/Amel2306/Traveo-Back.git```

---

##### 2 Ouvrir les microservices dans l'IDE
Chaque microservice correspond à un projet distinct dans ce dépôt.
Ouvrez chaque microservice dans un IDE comme IntelliJ IDEA ou Eclipse.

---

##### 3. Construire la base de données avec Docker Compose
Dans le répertoire contenant le fichier docker-compose.yml, exécutez la commande suivante :

```docker-compose up -d```


Cette commande :
Instanciera les conteneurs nécessaires (ex ici. : PostgreSQL).
Assurera la connexion entre les bases de données et les microservices.

---

##### 4. Mettre à jour l'adresse IP dans les fichiers `*ServiceClient`
Pour chaque microservice, localisez les classes qui se terminent par `*ServiceClient` et mettez à jour l'adresse IP des autres microservices en fonction des conteneurs Docker ou de votre configuration réseau.

Exemple dans UserServiceClient.java :

```private static final String USER_SERVICE_URL = "http://<IP_ADDRESS>:8080/api/users/";```

Remplacez `<IP_ADDRESS>` par l'IP correcte.

---

##### 5. Lancer les microservices
Dans votre IDE :

Pour Gradle : Cliquez sur Run ou utilisez la commande suivante dans le terminal de chaque microservice :
```gradle bootRun```


> 💡 Astuce : Vous pouvez configurer un run configuration dans IntelliJ IDEA pour lancer tous les microservices en un clic.

---

### 🚀 Vérification

Une fois tous les microservices lancés testez les endpoints api souhaité 

### 📊 Tableaux des Endpoints API

| **Service**   | **Méthode** | **Endpoint**                              | **Description**                                       |
|:--------------|:------------|:------------------------------------------|:-----------------------------------------------------|
| **User**      | GET         | /api/users                                | Récupère tous les utilisateurs                       |
|               | GET         | /api/users/{id}                           | Récupère un utilisateur par son ID                  |
|               | POST        | /api/users/register                       | Enregistre un nouvel utilisateur                    |
|               | POST        | /api/users/login                          | Connecte un utilisateur                             |
|               | PUT         | /api/users/{id}                           | Met à jour un utilisateur                           |
|               | DELETE      | /api/users/{id}                           | Supprime un utilisateur                             |
| **Thème**     | GET         | /api/themes                               | Récupère tous les thèmes                            |
|               | GET         | /api/themes/{id}                          | Récupère un thème par son ID                        |
|               | POST        | /api/themes                               | Crée un nouveau thème                               |
|               | PUT         | /api/themes/{id}                          | Met à jour un thème                                 |
|               | DELETE      | /api/themes/{id}                          | Supprime un thème                                   |
| **Activité**  | GET         | /api/activities                           | Récupère toutes les activités                       |
|               | GET         | /api/activities/user/{userId}             | Récupère les activités associées à un utilisateur   |
|               | GET         | /api/activities/{id}                      | Récupère une activité par son ID                    |
|               | GET         | /api/activities/theme/{themeId}           | Récupère les activités liées à un thème             |
|               | POST        | /api/activities                           | Crée une nouvelle activité                          |
|               | PUT         | /api/activities/{id}                      | Met à jour une activité                             |
|               | DELETE      | /api/activities/{id}                      | Supprime une activité                               |
| **Réservation**| GET        | /api/reservations                         | Récupère toutes les réservations                    |
|               | GET         | /api/reservations/user/{userId}           | Récupère les réservations d’un utilisateur          |
|               | GET         | /api/reservations/activity/{activityId}   | Récupère les réservations associées à une activité  |
|               | GET         | /api/reservations/{id}                    | Récupère une réservation par son ID                 |
|               | POST        | /api/reservations                         | Crée une nouvelle réservation                       |
|               | PUT         | /api/reservations/{id}                    | Met à jour une réservation                          |
|               | DELETE      | /api/reservations/{id}                    | Supprime une réservation                            |
| **Avis**      | GET         | /api/avis                                 | Récupère tous les avis                              |
|               | GET         | /api/avis/{id}                            | Récupère un avis par son ID                         |
|               | GET         | /api/avis/activite/{idActivite}           | Récupère les avis associés à une activité           |
|               | GET         | /api/avis/user/{userId}                   | Récupère les avis laissés par un utilisateur        |
|               | GET         | /api/avis/host/{userId}                   | Récupère les avis concernant un hôte                |
|               | POST        | /api/avis                                 | Crée un nouvel avis                                 |
|               | PUT         | /api/avis/{id}                            | Met à jour un avis                                  |
|               | DELETE      | /api/avis/{id}                            | Supprime un avis                                    |


### ℹ️ Notes importantes :
1. **Paramètres dynamiques** : Les `{id}`, `{userId}`, `{activityId}`, etc., doivent être remplacés par des valeurs réelles lors des requêtes.
2. **Structure RESTful** : Tous les endpoints suivent une convention REST standard pour une meilleure maintenabilité et extensibilité.
3. **Retour JSON** : Chaque endpoint retourne une réponse JSON standard.

### ⌨️ Structure de code

Voici une représentation et une explication de l'architecture d'un microservice du backend pour le projet **Traveo_Back**. Cette structure est applicable à tous les microservices (activités, utilisateurs, thèmes, réservations, avis).

```
📁 Traveo_Back/
├── 📂 activité/
│   ├── 📁 .gradle/
│   ├── 📁 build/
│   ├── 📁 gradle/
│   ├── 📁 src/
│   │   ├── 📂 main/
│   │   │   ├── 📂 java/
│   │   │   │   └── 📂 com.ig5.iwa.activite/
│   │   │   │       ├── 📂 config/
│   │   │   │       │   └── 🛠️ RestTemplateConfig.java    # Configuration des clients REST pour communiquer avec d'autres microservices
│   │   │   │       ├── 📂 controller/
│   │   │   │       │   └── 🌐 ActiviteController.java    # Gère les endpoints pour les opérations CRUD
│   │   │   │       ├── 📂 model/
│   │   │   │       │   └── 📄 Activite.java              # Représente l'entité Activite en base de données
│   │   │   │       ├── 📂 repository/
│   │   │   │       │   └── 📦 ActiviteRepository.java    # Interface pour les opérations CRUD avec la base de données
│   │   │   │       ├── 📂 service/
│   │   │   │       │   ├── 🛠️ ActiviteService.java       # Contient la logique métier pour les activités
│   │   │   │       │   ├── 🌐 ThemeServiceClient.java    # Communication avec le service de thèmes
│   │   │   │       │   └── 🌐 UserServiceClient.java     # Communication avec le service utilisateur
│   │   │   │       └── 🚀 ActiviteApplication.java       # Classe principale de démarrage du microservice
│   │   │   ├── 📂 resources/
│   │   │   │   ├── ⚙️ application.properties            # Fichier de configuration (ports, accès DB, etc.)
│   │   │   │   └── 📂 templates
│   │   ├── 📂 test/                                      # Dossier pour les tests unitaires
│   ├── ⚙️ build.gradle.kts                               # Configuration Gradle du projet
│   ├── 🐳 docker-compose.yml                             # Configuration Docker Compose pour démarrer les microservices et la base de données
│   ├── 🐋 Dockerfile                                     # Dockerfile pour construire une image du microservice
│   ├── ⚙️ settings.gradle.kts                            # Configuration globale des dépendances Gradle
│   ├── 🔧 gradlew                                        # Wrapper Gradle pour exécuter Gradle sans l'installer globalement
│   ├── 🔧 gradlew.bat                                    # Wrapper Gradle pour Windows
```

---

### ℹ️ Explications des fichiers et dossiers principaux

#### **src/main/java/**
- **`controller/`** : Contient les classes qui définissent les endpoints REST, par exemple `ActiviteController` pour gérer les activités.
- **`model/`** : Contient les entités utilisées dans la base de données (ex. : `Activite`).
- **`repository/`** : Interface pour les opérations CRUD avec la base de données via Spring Data JPA.
- **`service/`** : Contient la logique métier, y compris les communications avec d'autres microservices via `RestTemplate`.
- **`config/`** : Classe pour configurer les composants communs comme les clients REST.

#### **src/main/resources/**
- **`application.properties`** : Configure les ports, la connexion à la base de données et d'autres paramètres spécifiques au microservice.

#### **Fichiers Docker**
- **`Dockerfile`** : Permet de construire une image Docker pour exécuter le microservice. Il contient généralement des instructions pour copier le code, installer les dépendances et démarrer l'application.
- **`docker-compose.yml`** : Fichier de configuration pour lancer plusieurs services en même temps, y compris les bases de données et les microservices, dans des conteneurs Docker.

---

### Notes générales
- Chaque microservice (user, theme, reservation, avis) suit la même structure, avec des contrôleurs, services, modèles et repository spécifiques à leurs domaines.
- La communication entre les microservices se fait via des clients REST (`RestTemplate` ou `FeignClient` dans certains cas).

Si tu as des questions ou si tu veux des précisions sur une partie spécifique, n'hésite pas !

### 🎉 Félicitations !

Vous êtes maintenant prêt à utiliser l'application ! Si vous rencontrez des problèmes ou des questions, consultez les logs des services ou contactez l'équipe technique (Amel, Ines et Nouhaila : un mail suffira :)).

Bon développement ! ✨
