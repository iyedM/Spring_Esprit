# 🎯 Arctic10 - Système de Gestion de Call Center

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![MySQL](https://img.shields.io/badge/MySQL-5.5+-blue)
![Status](https://img.shields.io/badge/Status-Running-success)

## 📋 Description

**Arctic10** est un système de gestion de call center moderne qui combine des agents humains et des systèmes d'intelligence artificielle pour traiter efficacement les appels clients. Le système permet de gérer des projets, d'assigner des agents et de suivre l'état des appels en temps réel.

---

## ✨ Fonctionnalités

- 📞 **Gestion des appels** : Créer, modifier, suivre les appels clients
- 👥 **Gestion des agents** : Gérer les employés et leurs compétences
- 🤖 **Systèmes IA** : Intégration d'assistants virtuels intelligents
- 📊 **Gestion de projets** : Organiser les équipes et les projets
- 🔄 **Assignation automatique** : Attribution des appels selon les compétences
- 📈 **Suivi en temps réel** : Statuts des appels et disponibilité des agents

---

## 🚀 Démarrage Rapide

### Prérequis
- Java 17+
- Maven 3.6+
- MySQL 5.5+

### Installation

```bash
# Cloner le projet
git clone <repository-url>
cd iyed_mohamed_artic10

# Configurer MySQL
mysql -u root -p
CREATE DATABASE my_db;
CREATE USER 'iyed'@'localhost' IDENTIFIED BY 'iyed';
GRANT ALL PRIVILEGES ON my_db.* TO 'iyed'@'localhost';

# Compiler et lancer
mvn clean install
mvn spring-boot:run
```

L'application sera accessible sur : **http://localhost:8085**

---

## 🌐 API Endpoints

### Accueil
```
GET  /              → Page d'accueil
GET  /health        → État de l'application
```

### Appels
```
GET    /api/calls           → Liste tous les appels
GET    /api/calls/{id}      → Récupère un appel
POST   /api/calls           → Crée un appel
PUT    /api/calls           → Modifie un appel
DELETE /api/calls/{id}      → Supprime un appel
```

### Projets
```
GET  /api/projects/{id}/agents  → Liste les agents d'un projet
```

---

## 📊 Modèle de Données

### Entités Principales

- **Agents** : Employés du call center avec leurs compétences
- **AiSystems** : Systèmes d'IA disponibles
- **Calls** : Appels clients avec statut et historique
- **Projects** : Projets avec équipes assignées
- **ProjectDetails** : Détails budgétaires et clients

### Relations

```
Agents ←→ Calls      (One-to-Many)
Agents ←→ Projects   (Many-to-Many)
AiSystems ←→ Calls   (One-to-Many)
Projects ←→ Details  (One-to-One)
```

---

## 🛠️ Technologies

| Technologie | Version | Usage |
|------------|---------|-------|
| Spring Boot | 3.5.10 | Framework backend |
| Spring Data JPA | 3.5.10 | ORM et persistence |
| MySQL | 5.5+ | Base de données |
| Hibernate | 6.6.41 | Implémentation JPA |
| Lombok | - | Réduction de code |
| Maven | - | Gestion de projet |

---

## 📖 Documentation

Le projet contient **3 guides complets** :

1. **📄 DOCUMENTATION_COMPLETE.md**
   - Vue d'ensemble du projet
   - Architecture détaillée
   - Explication de tous les composants

2. **🎨 GUIDE_VISUEL.md**
   - Diagrammes et schémas
   - Relations entre entités
   - Flux de données

3. **🛠️ GUIDE_PRATIQUE.md**
   - Exemples de requêtes API
   - Scripts SQL de test
   - Intégration frontend

---

## 🧪 Exemples d'Utilisation

### Créer un appel
```bash
curl -X POST http://localhost:8085/api/calls \
  -H "Content-Type: application/json" \
  -d '{
    "phoneNumber": "0612345678",
    "callStatus": "On_Hold",
    "requiredSkills": ["Sales"]
  }'
```

### Récupérer tous les appels
```bash
curl http://localhost:8085/api/calls
```

### Insérer des données de test
```sql
INSERT INTO agents (name, available) VALUES ('Marie', true);
INSERT INTO agents_skills (agents_agents_id, skills) VALUES (1, 'Sales');
```

---

## 📁 Structure du Projet

```
iyed_mohamed_artic10/
├── src/
│   ├── main/
│   │   ├── java/tn/esprit/iyed_mohamed_artic10/
│   │   │   ├── IyedMohamedArtic10Application.java
│   │   │   ├── entities/          # Modèles JPA
│   │   │   ├── Enum/              # Énumérations
│   │   │   ├── Repositories/       # Repositories
│   │   │   ├── Services/          # Logique métier
│   │   │   └── Controllers/       # API REST
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── DOCUMENTATION_COMPLETE.md
├── GUIDE_VISUEL.md
└── GUIDE_PRATIQUE.md
```

---

## ⚙️ Configuration

### application.properties
```properties
# Base de données
spring.datasource.url=jdbc:mysql://localhost:3306/my_db
spring.datasource.username=iyed
spring.datasource.password=iyed

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Serveur
server.port=8085
```

---

## 🧩 Enums

### CallSkills
- `Billing` : Facturation
- `Sales` : Ventes
- `Customer_service` : Service client
- `Technical_support` : Support technique

### CallStatus
- `On_Hold` : En attente
- `In_progress` : En cours
- `TRANSFERRED` : Transféré
- `RESOLVED` : Résolu
- `ABANDONED` : Abandonné

---

## 🔮 Roadmap

### Version Actuelle (v0.0.1)
- [x] API REST de base
- [x] Gestion des appels
- [x] Architecture MVC
- [x] Documentation complète

### Version Future (v0.1.0)
- [ ] Services complets pour toutes les entités
- [ ] Validations des données
- [ ] Gestion d'erreurs globale
- [ ] Pagination et filtres

### Version Future (v0.2.0)
- [ ] Authentification JWT
- [ ] Rôles et permissions
- [ ] Documentation Swagger
- [ ] Tests unitaires

---

## 👨‍💻 Auteur

**Iyed Mohamed**  
Projet : Arctic10  
École : ESPRIT

---

## 📄 Licence

Ce projet est développé à des fins éducatives dans le cadre du programme Arctic10.

---

## 🤝 Contribution

1. Fork le projet
2. Créer une branche (`git checkout -b feature/AmazingFeature`)
3. Commit les changements (`git commit -m 'Add AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

---

## 📞 Support

Pour toute question ou problème :
- Consulter la **DOCUMENTATION_COMPLETE.md**
- Lire le **GUIDE_PRATIQUE.md**
- Voir les diagrammes dans **GUIDE_VISUEL.md**

---

## ✅ Status

- ✅ Application fonctionnelle
- ✅ Base de données connectée
- ✅ API REST opérationnelle
- ✅ Documentation complète

---

**🚀 Projet prêt pour le développement et l'extension !**

*Dernière mise à jour : Février 2026*

