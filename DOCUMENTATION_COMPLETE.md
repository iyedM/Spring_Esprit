# 📚 DOCUMENTATION COMPLÈTE DU PROJET - Arctic10 (Iyed Mohamed)

## 🎯 VUE D'ENSEMBLE DU PROJET

**Nom du projet :** iyed_mohamed_artic10  
**Type :** Application Spring Boot (API REST)  
**Version :** 0.0.1-SNAPSHOT  
**Framework :** Spring Boot 3.5.10  
**Langage :** Java 17  
**Base de données :** MySQL  
**Port serveur :** 8085  

### 🔍 Objectif du Projet
Ce projet est un système de gestion d'appels et de projets avec des agents humains et des systèmes IA. Il permet de :
- Gérer des agents (employés)
- Gérer des systèmes IA
- Gérer des appels clients
- Gérer des projets et leurs détails
- Assigner des agents à des projets
- Assigner des agents ou IA aux appels

---

## 📁 STRUCTURE COMPLÈTE DU PROJET

```
iyed_mohamed_artic10/
│
├── .git/                          # Dossier Git (contrôle de version)
├── .gitattributes                 # Configuration Git
├── .gitignore                     # Fichiers ignorés par Git
├── .idea/                         # Configuration IntelliJ IDEA
├── .mvn/                          # Configuration Maven Wrapper
│
├── HELP.md                        # Documentation d'aide Spring Boot
├── mvnw                           # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                       # Maven Wrapper (Windows)
├── pom.xml                        # Configuration Maven (dépendances)
│
├── src/                           # Code source
│   ├── main/
│   │   ├── java/
│   │   │   └── tn/esprit/iyed_mohamed_artic10/
│   │   │       │
│   │   │       ├── IyedMohamedArtic10Application.java   # Classe principale
│   │   │       │
│   │   │       ├── entities/                            # Entités JPA (Modèles)
│   │   │       │   ├── Agents.java
│   │   │       │   ├── AiSystems.java
│   │   │       │   ├── Calls.java
│   │   │       │   ├── Projects.java
│   │   │       │   └── ProjectDetails.java
│   │   │       │
│   │   │       ├── Enum/                                # Énumérations
│   │   │       │   ├── CallSkills.java
│   │   │       │   └── CallStatus.java
│   │   │       │
│   │   │       ├── Repositories/                         # Repositories (Accès BD)
│   │   │       │   ├── IAgents.java
│   │   │       │   ├── IAiSystems.java
│   │   │       │   ├── ICallsRepository.java
│   │   │       │   ├── IProjects.java
│   │   │       │   └── IProjectDetails.java
│   │   │       │
│   │   │       ├── Services/                            # Services (Logique métier)
│   │   │       │   ├── IAgentsService.java              # Interface
│   │   │       │   ├── AgentsServicesImp.java           # Implémentation
│   │   │       │   ├── IAiSystemsServices.java
│   │   │       │   ├── AiSystemsServicesImp.java
│   │   │       │   ├── ICallsServices.java
│   │   │       │   ├── CallsServicesImp.java
│   │   │       │   ├── IProjectServices.java
│   │   │       │   ├── ProjectServicesImp.java
│   │   │       │   ├── IProjectDetailsServices.java
│   │   │       │   └── ProjectDetailsServiceImp.java
│   │   │       │
│   │   │       └── Controllers/                         # Contrôleurs REST
│   │   │           ├── HomeController.java
│   │   │           ├── CallsRestController.java
│   │   │           └── ProjectsRestController.java
│   │   │
│   │   └── resources/
│   │       └── application.properties                   # Configuration Spring
│   │
│   └── test/                                            # Tests unitaires
│       └── java/
│           └── tn/esprit/iyed_mohamed_artic10/
│               └── IyedMohamedArtic10ApplicationTests.java
│
└── target/                                              # Fichiers compilés (.class)
    ├── classes/                                         # Classes compilées
    ├── generated-sources/
    ├── generated-test-sources/
    ├── maven-status/
    └── test-classes/
```

---

## 🗄️ ARCHITECTURE DU PROJET (Pattern MVC)

Le projet suit l'architecture **MVC (Model-View-Controller)** adaptée pour les API REST :

```
┌─────────────────────────────────────────────────────┐
│                   CLIENT (Browser)                   │
│              http://localhost:8085/                  │
└───────────────────────┬─────────────────────────────┘
                        │ HTTP Request
                        ↓
┌─────────────────────────────────────────────────────┐
│              CONTROLLERS (RestControllers)           │
│  HomeController, CallsRestController, ...           │
│  - Reçoit les requêtes HTTP                         │
│  - Retourne les réponses JSON                       │
└───────────────────────┬─────────────────────────────┘
                        │
                        ↓
┌─────────────────────────────────────────────────────┐
│                   SERVICES                           │
│  CallsServicesImp, ProjectServicesImp, ...          │
│  - Logique métier                                   │
│  - Traitement des données                           │
└───────────────────────┬─────────────────────────────┘
                        │
                        ↓
┌─────────────────────────────────────────────────────┐
│                  REPOSITORIES                        │
│  ICallsRepository, IProjects, ...                   │
│  - Accès à la base de données                       │
│  - Opérations CRUD                                  │
└───────────────────────┬─────────────────────────────┘
                        │
                        ↓
┌─────────────────────────────────────────────────────┐
│                   ENTITIES (Models)                  │
│  Agents, AiSystems, Calls, Projects, ...           │
│  - Représentation des tables de la BD               │
└───────────────────────┬─────────────────────────────┘
                        │
                        ↓
┌─────────────────────────────────────────────────────┐
│              BASE DE DONNÉES MYSQL                   │
│                   (my_db)                            │
└─────────────────────────────────────────────────────┘
```

---

## 📦 1. FICHIERS DE CONFIGURATION

### 1.1 pom.xml (Project Object Model)
**Rôle :** Configuration Maven - gère toutes les dépendances et la compilation

**Dépendances principales :**
```xml
✓ Spring Boot Starter Web       → Pour créer des API REST
✓ Spring Boot Starter Data JPA  → Pour gérer la base de données
✓ MySQL Connector               → Pour se connecter à MySQL
✓ Lombok                        → Pour réduire le code (getters/setters auto)
✓ Spring Boot Starter Test      → Pour les tests unitaires
✓ Kotlin                        → Support Kotlin (optionnel)
```

**Plugins :**
- `spring-boot-maven-plugin` : Permet de lancer l'app avec `mvn spring-boot:run`
- `kotlin-maven-plugin` : Compile le code Kotlin
- `maven-compiler-plugin` : Compile le code Java

### 1.2 application.properties
**Rôle :** Configuration de l'application Spring Boot

```properties
# Nom de l'application
spring.application.name=iyed_mohamed_artic10

# Configuration de la base de données MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/my_db
spring.datasource.username=iyed
spring.datasource.password=iyed
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuration JPA/Hibernate
spring.jpa.show-sql=true                     # Affiche les requêtes SQL dans la console
spring.jpa.hibernate.ddl-auto=update         # Crée/met à jour automatiquement les tables
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true  # Formate les requêtes SQL

# Configuration du serveur
server.port=8085                             # L'application écoute sur le port 8085
```

**Explication de `ddl-auto=update` :**
- `create` : Supprime et recrée les tables à chaque démarrage
- `update` : Met à jour le schéma sans perdre les données
- `validate` : Vérifie que le schéma correspond aux entités
- `none` : Ne fait rien

---

## 🗃️ 2. COUCHE ENTITIES (Modèles de données)

Les entités représentent les **tables de la base de données**. Chaque entité Java = 1 table MySQL.

### 2.1 Agents.java
**Rôle :** Représente un agent (employé) du call center

```java
@Entity  // Indique que c'est une table de la BD
public class Agents {
    @Id  // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incrémente
    private long agentsId;
    
    private String name;                    // Nom de l'agent
    
    @ElementCollection                      // Collection stockée dans une table séparée
    @Enumerated(EnumType.STRING)           // Stocke l'enum comme String (pas nombre)
    private Set<CallSkills> skills;        // Compétences de l'agent (Billing, Sales, etc.)
    
    private boolean available;              // Agent disponible ou occupé
    
    @OneToMany(mappedBy = "assignedAgent") // Un agent peut avoir plusieurs appels
    Set<Calls> myCalls;
    
    @ManyToMany(mappedBy = "agents")       // Un agent peut être dans plusieurs projets
    Set<Projects> myProjects;
}
```

**Table générée dans MySQL :**
```
agents (table principale)
├── agents_id (PK)
├── name
└── available

agents_skills (table de compétences)
├── agents_id (FK)
└── skills (enum)
```

### 2.2 AiSystems.java
**Rôle :** Représente un système d'intelligence artificielle

```java
@Entity
public class AiSystems {
    @Id
    private long aiSystemsId;
    
    private String type;                    // Type de système IA (GPT, Claude, etc.)
    private boolean available;              // IA disponible ou occupée
    
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<CallSkills> skills;         // Compétences de l'IA
    
    @OneToMany(mappedBy = "assignedAiSystem")
    Set<Calls> myCalls;                     // Appels gérés par cette IA
}
```

### 2.3 Calls.java
**Rôle :** Représente un appel client

```java
@Entity
public class Calls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long callsId;
    
    private String phoneNumber;             // Numéro de téléphone du client
    
    @Enumerated(EnumType.STRING)
    private CallStatus callStatus;          // Statut : On_Hold, In_progress, RESOLVED, etc.
    
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<CallSkills> requiredSkills; // Compétences requises pour l'appel
    
    @ManyToOne                              // Plusieurs appels peuvent être gérés par 1 agent
    Agents assignedAgent;                   // Agent assigné à cet appel
    
    @ManyToOne                              // Plusieurs appels peuvent être gérés par 1 IA
    AiSystems assignedAiSystem;             // IA assignée à cet appel
}
```

**Table générée :**
```
calls
├── calls_id (PK)
├── phone_number
├── call_status
├── assigned_agent_id (FK → agents)
└── assigned_ai_system_id (FK → ai_systems)

calls_required_skills
├── calls_id (FK)
└── required_skills
```

### 2.4 Projects.java
**Rôle :** Représente un projet

```java
@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectsid;
    
    private String libelle;                 // Nom/titre du projet
    private Date startDate;                 // Date de début
    private Date endDate;                   // Date de fin
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    ProjectDetails details;                 // Détails du projet (relation 1-1)
    
    @ManyToMany(fetch = FetchType.EAGER)   // Un projet a plusieurs agents
    Set<Agents> agents;                     // Liste des agents du projet
    
    // Getters et Setters...
}
```

**Relations :**
- **OneToOne** avec `ProjectDetails` : Un projet a un seul détail
- **ManyToMany** avec `Agents` : Un projet peut avoir plusieurs agents, un agent peut être dans plusieurs projets

### 2.5 ProjectDetails.java
**Rôle :** Détails supplémentaires d'un projet

```java
@Entity
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long detailsId;
    
    private double budget;                  // Budget du projet
    private String Client;                  // Nom du client
}
```

---

## 🎭 3. COUCHE ENUM (Énumérations)

Les énumérations définissent des **valeurs fixes et limitées**.

### 3.1 CallSkills.java
**Rôle :** Compétences disponibles pour les agents et IA

```java
public enum CallSkills {
    Billing,             // Facturation
    Sales,               // Ventes
    Customer_service,    // Service client
    Technical_support    // Support technique
}
```

### 3.2 CallStatus.java
**Rôle :** États possibles d'un appel

```java
public enum CallStatus {
    On_Hold,        // En attente
    In_progress,    // En cours
    TRANSFERRED,    // Transféré
    RESOLVED,       // Résolu
    ABANDONED       // Abandonné
}
```

---

## 🗂️ 4. COUCHE REPOSITORIES (Accès aux données)

Les repositories permettent d'**interagir avec la base de données** sans écrire de SQL.

### 4.1 IAgents.java
```java
public interface IAgents extends JpaRepository<Agents, Long> {
    // Hérite automatiquement de :
    // - save(agent)          : Créer/Modifier
    // - findById(id)         : Trouver par ID
    // - findAll()            : Trouver tous
    // - deleteById(id)       : Supprimer
    // - count()              : Compter
}
```

**Repositories disponibles :**
- `IAgents` → Pour la table `agents`
- `IAiSystems` → Pour la table `ai_systems`
- `ICallsRepository` → Pour la table `calls`
- `IProjects` → Pour la table `projects`
- `IProjectDetails` → Pour la table `project_details`

**Méthodes automatiques fournies par JpaRepository :**
```java
save(entity)           // Créer ou mettre à jour
findById(id)           // Trouver par ID
findAll()              // Trouver tous
deleteById(id)         // Supprimer par ID
count()                // Compter le nombre d'entités
existsById(id)         // Vérifier l'existence
```

---

## ⚙️ 5. COUCHE SERVICES (Logique métier)

Les services contiennent la **logique métier** de l'application.

### 5.1 Interface : ICallsServices.java
```java
@Service
public interface ICallsServices {
    Calls addCalls(Calls c);           // Ajouter un appel
    Calls UpdateCalls(Calls c);        // Modifier un appel
    Calls getById(Long c);             // Récupérer un appel par ID
    List<Calls> getAll();              // Récupérer tous les appels
    void deleteCalls(Long Id);         // Supprimer un appel
}
```

### 5.2 Implémentation : CallsServicesImp.java
```java
@RequiredArgsConstructor  // Lombok : crée un constructeur avec les champs final
@Service
public class CallsServicesImp implements ICallsServices {
    
    private final ICallsRepository callsRepository;  // Injection de dépendance
    
    @Override
    public Calls addCalls(Calls call) {
        return callsRepository.save(call);  // Sauvegarde dans la BD
    }
    
    @Override
    public Calls UpdateCalls(Calls call) {
        return callsRepository.save(call);  // Save = create ou update
    }
    
    @Override
    public Calls getById(Long call) {
        return callsRepository.findById(call)
            .orElseThrow(() -> new EntityNotFoundException("not found"));
    }
    
    @Override
    public List<Calls> getAll() {
        return callsRepository.findAll();
    }
    
    @Override
    public void deleteCalls(Long Id) {
        callsRepository.deleteById(Id);
    }
}
```

### 5.3 ProjectServicesImp.java
**Fonctionnalité spéciale :** Récupérer les agents d'un projet

```java
@RequiredArgsConstructor
@Service
public class ProjectServicesImp implements IProjectServices {
    
    public final IProjects iProjects;
    
    @Override
    public List<Agents> getAgent(Long IdProject) {
        // Trouve le projet par ID
        Projects projects = iProjects.findById(IdProject)
            .orElseThrow(() -> new EntityNotFoundException("not found"));
        
        // Retourne la liste des agents du projet
        return projects.getAgents().stream().toList();
    }
}
```

---

## 🎮 6. COUCHE CONTROLLERS (API REST)

Les contrôleurs exposent des **endpoints HTTP** pour les clients.

### 6.1 HomeController.java
**Rôle :** Page d'accueil de l'API

```java
@RestController
@RequestMapping("/")
public class HomeController {
    
    @GetMapping  // GET http://localhost:8085/
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bienvenue sur l'API Arctic10 - Iyed Mohamed");
        response.put("status", "Application is running successfully!");
        response.put("endpoints", Map.of(
            "calls", "/api/calls",
            "projects", "/api/projects"
        ));
        return response;  // Retourné en JSON
    }
    
    @GetMapping("/health")  // GET http://localhost:8085/health
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Application is healthy");
        return response;
    }
}
```

### 6.2 CallsRestController.java
**Rôle :** API CRUD pour les appels

```java
@RestController
@RequestMapping("/api/calls")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // Permet les requêtes depuis n'importe quel domaine
public class CallsRestController {
    
    private final ICallsServices callsServices;
    
    // POST http://localhost:8085/api/calls
    @PostMapping
    public ResponseEntity<Calls> addCall(@RequestBody Calls call) {
        Calls savedCall = callsServices.addCalls(call);
        return new ResponseEntity<>(savedCall, HttpStatus.CREATED);  // 201
    }
    
    // PUT http://localhost:8085/api/calls
    @PutMapping
    public ResponseEntity<Calls> updateCall(@RequestBody Calls call) {
        Calls updatedCall = callsServices.UpdateCalls(call);
        return ResponseEntity.ok(updatedCall);  // 200
    }
    
    // GET http://localhost:8085/api/calls/1
    @GetMapping("/{id}")
    public ResponseEntity<Calls> getCallById(@PathVariable Long id) {
        Calls call = callsServices.getById(id);
        return ResponseEntity.ok(call);
    }
    
    // GET http://localhost:8085/api/calls
    @GetMapping
    public ResponseEntity<List<Calls>> getAllCalls() {
        List<Calls> calls = callsServices.getAll();
        return ResponseEntity.ok(calls);
    }
    
    // DELETE http://localhost:8085/api/calls/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCall(@PathVariable Long id) {
        callsServices.deleteCalls(id);
        return ResponseEntity.noContent().build();  // 204
    }
}
```

### 6.3 ProjectsRestController.java
**Rôle :** API pour les projets

```java
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProjectsRestController {
    
    private final IProjectServices projectServices;
    
    // GET http://localhost:8085/api/projects/1/agents
    @GetMapping("/{id}/agents")
    public ResponseEntity<List<Agents>> getProjectAgents(@PathVariable Long id) {
        List<Agents> agents = projectServices.getAgent(id);
        return ResponseEntity.ok(agents);
    }
}
```

---

## 🌐 7. ENDPOINTS API DISPONIBLES

### 📍 Endpoints actuels :

| Méthode | URL | Description | Exemple |
|---------|-----|-------------|---------|
| GET | `/` | Page d'accueil | `curl http://localhost:8085/` |
| GET | `/health` | État de l'application | `curl http://localhost:8085/health` |
| GET | `/api/calls` | Liste tous les appels | `curl http://localhost:8085/api/calls` |
| GET | `/api/calls/{id}` | Récupère un appel | `curl http://localhost:8085/api/calls/1` |
| POST | `/api/calls` | Crée un appel | `curl -X POST -H "Content-Type: application/json" -d '{"phoneNumber":"123456"}' http://localhost:8085/api/calls` |
| PUT | `/api/calls` | Modifie un appel | `curl -X PUT -H "Content-Type: application/json" -d '{"callsId":1,"phoneNumber":"789"}' http://localhost:8085/api/calls` |
| DELETE | `/api/calls/{id}` | Supprime un appel | `curl -X DELETE http://localhost:8085/api/calls/1` |
| GET | `/api/projects/{id}/agents` | Liste agents d'un projet | `curl http://localhost:8085/api/projects/1/agents` |

---

## 🔄 8. FLUX DE DONNÉES (Exemple d'un appel)

### Scénario : Créer un nouvel appel

```
┌─────────────────────────────────────────────────────────┐
│ 1. CLIENT envoie une requête HTTP POST                 │
│    POST http://localhost:8085/api/calls                 │
│    Body: {"phoneNumber": "0612345678", ...}             │
└────────────────────┬────────────────────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────────────────────┐
│ 2. CONTROLLER (CallsRestController)                     │
│    @PostMapping                                          │
│    → Reçoit l'objet Calls depuis le JSON                │
│    → Appelle callsServices.addCalls(call)                │
└────────────────────┬────────────────────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────────────────────┐
│ 3. SERVICE (CallsServicesImp)                           │
│    → Traite la logique métier (validation, etc.)        │
│    → Appelle callsRepository.save(call)                  │
└────────────────────┬────────────────────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────────────────────┐
│ 4. REPOSITORY (ICallsRepository)                        │
│    → Utilise JPA/Hibernate                              │
│    → Génère automatiquement le SQL INSERT               │
│    → Exécute : INSERT INTO calls (...) VALUES (...)     │
└────────────────────┬────────────────────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────────────────────┐
│ 5. BASE DE DONNÉES MySQL                                │
│    → Insère la ligne dans la table 'calls'              │
│    → Retourne l'ID généré                               │
└────────────────────┬────────────────────────────────────┘
                     │
                     ↓ (Retour)
┌─────────────────────────────────────────────────────────┐
│ 6. CONTROLLER retourne ResponseEntity                   │
│    → Status: 201 CREATED                                │
│    → Body: {"callsId": 5, "phoneNumber": "0612345678"}  │
└─────────────────────────────────────────────────────────┘
```

---

## 🗄️ 9. BASE DE DONNÉES (Schéma MySQL)

### Tables créées automatiquement par Hibernate :

```sql
-- Table des agents
CREATE TABLE agents (
    agents_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    available BOOLEAN
);

-- Table des compétences des agents
CREATE TABLE agents_skills (
    agents_agents_id BIGINT,
    skills VARCHAR(50),
    FOREIGN KEY (agents_agents_id) REFERENCES agents(agents_id)
);

-- Table des systèmes IA
CREATE TABLE ai_systems (
    ai_systems_id BIGINT PRIMARY KEY,
    type VARCHAR(255),
    available BOOLEAN
);

-- Table des compétences des IA
CREATE TABLE ai_systems_skills (
    ai_systems_ai_systems_id BIGINT,
    skills VARCHAR(50),
    FOREIGN KEY (ai_systems_ai_systems_id) REFERENCES ai_systems(ai_systems_id)
);

-- Table des appels
CREATE TABLE calls (
    calls_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    phone_number VARCHAR(255),
    call_status VARCHAR(50),
    assigned_agent_agents_id BIGINT,
    assigned_ai_system_ai_systems_id BIGINT,
    FOREIGN KEY (assigned_agent_agents_id) REFERENCES agents(agents_id),
    FOREIGN KEY (assigned_ai_system_ai_systems_id) REFERENCES ai_systems(ai_systems_id)
);

-- Table des compétences requises pour les appels
CREATE TABLE calls_required_skills (
    calls_calls_id BIGINT,
    required_skills VARCHAR(50),
    FOREIGN KEY (calls_calls_id) REFERENCES calls(calls_id)
);

-- Table des détails de projet
CREATE TABLE project_details (
    details_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    budget DOUBLE,
    client VARCHAR(255)
);

-- Table des projets
CREATE TABLE projects (
    projectsid BIGINT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255),
    start_date DATE,
    end_date DATE,
    details_details_id BIGINT,
    FOREIGN KEY (details_details_id) REFERENCES project_details(details_id)
);

-- Table association Projets-Agents (ManyToMany)
CREATE TABLE projects_agents (
    projects_projectsid BIGINT,
    agents_agents_id BIGINT,
    PRIMARY KEY (projects_projectsid, agents_agents_id),
    FOREIGN KEY (projects_projectsid) REFERENCES projects(projectsid),
    FOREIGN KEY (agents_agents_id) REFERENCES agents(agents_id)
);
```

### Diagramme relationnel :

```
┌──────────────┐         ┌──────────────┐
│   AGENTS     │◄───────►│   PROJECTS   │
│              │ M     M  │              │
│ agents_id PK │         │ projectsid PK│
│ name         │         │ libelle      │
│ available    │         │ start_date   │
└──────┬───────┘         │ end_date     │
       │                  └──────┬───────┘
       │ 1                       │ 1
       │                         │
       │ M                       │ 1
┌──────▼───────┐         ┌──────▼────────┐
│    CALLS     │         │PROJECT_DETAILS│
│              │         │               │
│ calls_id  PK │         │ details_id PK │
│ phone_number │         │ budget        │
│ call_status  │         │ client        │
└──────┬───────┘         └───────────────┘
       │
       │ M
       │
       │ 1
┌──────▼───────┐
│  AI_SYSTEMS  │
│              │
│ ai_systems_id│
│ type         │
│ available    │
└──────────────┘
```

---

## 🚀 10. COMMANDES UTILES

### Compilation et exécution :

```bash
# Compiler le projet
mvn clean compile

# Lancer l'application
mvn spring-boot:run

# Compiler et créer un fichier JAR
mvn clean package

# Lancer le JAR compilé
java -jar target/iyed_mohamed_artic10-0.0.1-SNAPSHOT.jar

# Nettoyer le projet
mvn clean
```

### Tests avec curl :

```bash
# Page d'accueil
curl http://localhost:8085/

# Santé de l'application
curl http://localhost:8085/health

# Lister tous les appels
curl http://localhost:8085/api/calls

# Créer un appel
curl -X POST http://localhost:8085/api/calls \
  -H "Content-Type: application/json" \
  -d '{"phoneNumber":"0612345678","callStatus":"On_Hold"}'

# Récupérer un appel
curl http://localhost:8085/api/calls/1

# Supprimer un appel
curl -X DELETE http://localhost:8085/api/calls/1
```

---

## 📝 11. ANNOTATIONS SPRING IMPORTANTES

| Annotation | Rôle | Exemple |
|------------|------|---------|
| `@SpringBootApplication` | Point d'entrée de l'app | Classe principale |
| `@Entity` | Définit une table de BD | Classes dans entities/ |
| `@Id` | Clé primaire | `private long id;` |
| `@GeneratedValue` | Auto-incrémentation | ID généré automatiquement |
| `@RestController` | Contrôleur REST | Retourne du JSON |
| `@RequestMapping` | URL de base | `/api/calls` |
| `@GetMapping` | Requête GET | Lire des données |
| `@PostMapping` | Requête POST | Créer des données |
| `@PutMapping` | Requête PUT | Modifier des données |
| `@DeleteMapping` | Requête DELETE | Supprimer des données |
| `@PathVariable` | Variable dans l'URL | `/{id}` |
| `@RequestBody` | Corps de la requête JSON | Données envoyées |
| `@Service` | Classe de service | Logique métier |
| `@Repository` | Classe repository | Accès BD |
| `@Autowired` | Injection de dépendance | Injecte automatiquement |
| `@RequiredArgsConstructor` | (Lombok) Constructeur auto | Remplace @Autowired |
| `@OneToMany` | Relation 1-N | 1 agent → N appels |
| `@ManyToOne` | Relation N-1 | N appels → 1 agent |
| `@ManyToMany` | Relation N-N | Agents ↔ Projects |
| `@OneToOne` | Relation 1-1 | Project ↔ Details |

---

## 🎓 12. CONCEPTS CLÉS À COMPRENDRE

### 12.1 Injection de Dépendances (DI)
Au lieu de créer manuellement les objets :
```java
// ❌ Mauvais
ICallsRepository repo = new CallsRepository();

// ✅ Bon (Spring injecte automatiquement)
@RequiredArgsConstructor
public class CallsServicesImp {
    private final ICallsRepository callsRepository;  // Spring l'injecte
}
```

### 12.2 JPA/Hibernate
- **JPA** = Java Persistence API (Standard)
- **Hibernate** = Implémentation de JPA (Fait le travail)
- Convertit automatiquement les objets Java ↔ Tables SQL

### 12.3 REST (Representational State Transfer)
- Architecture pour les API web
- Utilise HTTP (GET, POST, PUT, DELETE)
- Échange des données en JSON

### 12.4 Maven
- Outil de gestion de projet Java
- Gère les dépendances (télécharge les librairies)
- Compile et package l'application

### 12.5 Lombok
- Librairie qui génère du code automatiquement
- `@RequiredArgsConstructor` → Crée le constructeur
- `@Data` → Crée getters, setters, toString, etc.

---

## 🔍 13. PROBLÈMES RÉSOLUS

### Problème initial : Erreur 404
**Cause :** Pas de contrôleurs REST → Aucun endpoint défini  
**Solution :** Création de 3 contrôleurs (Home, Calls, Projects)

### État actuel :
✅ Application démarre correctement  
✅ Connexion à MySQL réussie  
✅ Hibernate crée les tables automatiquement  
✅ API REST fonctionnelle  
✅ Endpoints accessibles  

---

## 📚 14. RESSOURCES ET DOCUMENTATION

- **Spring Boot :** https://spring.io/projects/spring-boot
- **Spring Data JPA :** https://spring.io/projects/spring-data-jpa
- **Hibernate :** https://hibernate.org/
- **Maven :** https://maven.apache.org/
- **Lombok :** https://projectlombok.org/

---

## 🎯 15. PROCHAINES ÉTAPES POSSIBLES

Pour améliorer le projet :

1. ✅ **Contrôleurs REST** (FAIT)
2. 🔜 Compléter les services pour Agents, AiSystems, ProjectDetails
3. 🔜 Ajouter des validations (@Valid, @NotNull)
4. 🔜 Gérer les erreurs avec @ControllerAdvice
5. 🔜 Ajouter la pagination (Pageable)
6. 🔜 Créer des méthodes de recherche personnalisées
7. 🔜 Ajouter Spring Security (authentification)
8. 🔜 Documenter l'API avec Swagger/OpenAPI
9. 🔜 Créer des tests unitaires (JUnit, Mockito)
10. 🔜 Ajouter des logs (SLF4J)

---

## 📧 CONTACT

**Auteur :** Iyed Mohamed  
**Projet :** Arctic10  
**Framework :** Spring Boot 3.5.10  
**Date :** Février 2026

---

*Ce document a été généré automatiquement pour expliquer l'ensemble du projet Arctic10.*

