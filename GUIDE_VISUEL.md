# 🎨 GUIDE VISUEL - Relations et Architecture

## 🔗 1. DIAGRAMME DES RELATIONS ENTRE ENTITÉS

```
                    ┌─────────────────────────────────────────┐
                    │         SYSTÈME ARCTIC10                │
                    │    (Gestion d'Appels et Projets)        │
                    └─────────────────────────────────────────┘
                                        │
                ┌───────────────────────┼───────────────────────┐
                │                       │                       │
                ▼                       ▼                       ▼
        ┌──────────────┐        ┌──────────────┐      ┌──────────────┐
        │   AGENTS     │        │  AI_SYSTEMS  │      │   PROJECTS   │
        │ (Employés)   │        │  (IA)        │      │  (Projets)   │
        └──────┬───────┘        └──────┬───────┘      └──────┬───────┘
               │                       │                      │
               │ @ManyToMany          │                      │ @OneToOne
               ├───────────────────────┼──────────────────────┤
               │                       │                      │
               │     @ManyToOne        │ @ManyToOne           ▼
               │          ↓            │     ↓         ┌──────────────┐
               │     ┌────────────────────────┐        │PROJECT_DETAILS
               └────►│      CALLS             │        │              │
                     │   (Appels clients)     │        │ budget       │
                     └────────────────────────┘        │ client       │
                                                       └──────────────┘
```

## 📊 2. DÉTAILS DES RELATIONS

### 🔵 Relation 1 : AGENTS ↔ CALLS (One-to-Many)

```
┌───────────────────┐                    ┌───────────────────┐
│     AGENTS        │ 1              M   │      CALLS        │
│                   │◄───────────────────│                   │
│ agentsId          │                    │ callsId           │
│ name: "Pierre"    │                    │ phoneNumber       │
│ available: true   │                    │ assignedAgent ────┤
│ skills: [Sales]   │                    │ callStatus        │
└───────────────────┘                    └───────────────────┘

📝 Signification:
   • Un agent peut gérer plusieurs appels (One-to-Many)
   • Un appel est assigné à UN SEUL agent (Many-to-One)
   • Relation bidirectionnelle

💡 Exemple:
   Agent "Pierre" → [Appel1, Appel2, Appel3]
   Appel1 → Agent "Pierre"
```

### 🟢 Relation 2 : AI_SYSTEMS ↔ CALLS (One-to-Many)

```
┌───────────────────┐                    ┌───────────────────┐
│   AI_SYSTEMS      │ 1              M   │      CALLS        │
│                   │◄───────────────────│                   │
│ aiSystemsId       │                    │ callsId           │
│ type: "GPT-4"     │                    │ phoneNumber       │
│ available: true   │                    │ assignedAiSystem ─┤
│ skills: [Support] │                    │ callStatus        │
└───────────────────┘                    └───────────────────┘

📝 Signification:
   • Un système IA peut gérer plusieurs appels
   • Un appel peut être assigné à UNE SEULE IA
   
💡 Exemple:
   IA "GPT-4" → [Appel5, Appel6, Appel7]
   Appel5 → IA "GPT-4"
```

### 🟡 Relation 3 : AGENTS ↔ PROJECTS (Many-to-Many)

```
┌───────────────────┐                    ┌───────────────────┐
│     AGENTS        │ M              M   │     PROJECTS      │
│                   │◄──────────────────►│                   │
│ agentsId          │                    │ projectsid        │
│ name: "Marie"     │                    │ libelle: "App X"  │
│ myProjects ───────┤                    │ agents ───────────┤
└───────────────────┘                    └───────────────────┘
            │                                      │
            └──────────────┬───────────────────────┘
                           ▼
                ┌────────────────────────┐
                │  PROJECTS_AGENTS       │
                │  (Table de liaison)    │
                │                        │
                │ projects_projectsid    │
                │ agents_agents_id       │
                └────────────────────────┘

📝 Signification:
   • Un agent peut travailler sur plusieurs projets
   • Un projet peut avoir plusieurs agents
   • Table intermédiaire créée automatiquement

💡 Exemple:
   Agent "Marie" → [Projet1, Projet2]
   Projet1 → [Marie, Pierre, Sophie]
```

### 🔴 Relation 4 : PROJECTS ↔ PROJECT_DETAILS (One-to-One)

```
┌───────────────────┐                    ┌───────────────────┐
│     PROJECTS      │ 1              1   │ PROJECT_DETAILS   │
│                   │◄──────────────────►│                   │
│ projectsid: 5     │                    │ detailsId: 10     │
│ libelle: "App Y"  │                    │ budget: 50000€    │
│ details ──────────┤                    │ client: "ESPRIT"  │
│ startDate         │                    │                   │
│ endDate           │                    │                   │
└───────────────────┘                    └───────────────────┘

📝 Signification:
   • Un projet a UN SEUL détail
   • Un détail appartient à UN SEUL projet
   • Relation 1:1 bidirectionnelle

💡 Exemple:
   Projet "App Y" ↔ Détails (50000€, Client: ESPRIT)
```

## 🎯 3. EXEMPLE CONCRET D'UTILISATION

### Scénario : Centre d'appels Arctic10

```
┌─────────────────────────────────────────────────────────────┐
│                    JOUR TYPIQUE                              │
└─────────────────────────────────────────────────────────────┘

📞 APPEL 1 arrive à 9h00
├─ Numéro: 0612345678
├─ Compétence requise: Billing
├─ Statut: On_Hold
└─ Système recherche un agent disponible avec compétence "Billing"
   └─► Agent "Marie" (Billing, Sales) est disponible
       └─► Appel assigné à Marie
           └─► Statut change: In_progress
               └─► Marie résout le problème
                   └─► Statut change: RESOLVED

📞 APPEL 2 arrive à 9h15
├─ Numéro: 0698765432
├─ Compétence requise: Technical_support
├─ Statut: On_Hold
└─ Système recherche un agent disponible
   └─► Aucun agent disponible !
       └─► IA "GPT-4" (Technical_support) prend l'appel
           └─► Statut change: In_progress
               └─► IA ne peut pas résoudre
                   └─► Statut change: TRANSFERRED
                       └─► Agent "Pierre" devient disponible
                           └─► Appel transféré à Pierre
                               └─► Statut: RESOLVED

👥 PROJET "Application Mobile"
├─ Début: 01/02/2026
├─ Fin: 30/06/2026
├─ Budget: 100,000€
├─ Client: "ESPRIT University"
└─ Équipe assignée:
   ├─► Agent "Marie" (Sales, Billing)
   ├─► Agent "Pierre" (Technical_support)
   └─► Agent "Sophie" (Customer_service)
```

## 🔄 4. CYCLE DE VIE D'UN APPEL

```
┌───────────┐
│  CLIENT   │
│  Appelle  │
└─────┬─────┘
      │
      ▼
┌─────────────────┐
│   On_Hold       │ ← Appel en attente
│   (En attente)  │
└────────┬────────┘
         │
         ▼
    [Recherche Agent/IA disponible]
         │
    ┌────┴─────┐
    │          │
    ▼          ▼
[Agent]     [IA]
    │          │
    └────┬─────┘
         │
         ▼
┌─────────────────┐
│  In_progress    │ ← Appel traité
│  (En cours)     │
└────────┬────────┘
         │
    ┌────┴─────┐
    │          │
    ▼          ▼
[Résolu]  [Transféré]
    │          │
    ▼          │
┌─────────┐   │
│RESOLVED │   │
└─────────┘   │
              ▼
         [Autre Agent]
              │
              ▼
         ┌─────────┐
         │RESOLVED │
         └─────────┘
```

## 📦 5. ARCHITECTURE EN COUCHES (DÉTAILLÉE)

```
┌─────────────────────────────────────────────────────────────┐
│                      COUCHE CLIENT                           │
│  (Browser, Postman, Application Mobile, etc.)               │
│                                                              │
│  📱 http://localhost:8085/api/calls                         │
└──────────────────────────┬──────────────────────────────────┘
                           │ HTTP Request (JSON)
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                  COUCHE PRÉSENTATION                         │
│                    (Controllers)                             │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │HomeController│  │CallsController│  │ProjectsCtrl  │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
│                                                              │
│  Annotations: @RestController, @GetMapping, @PostMapping    │
└──────────────────────────┬──────────────────────────────────┘
                           │ Appel de méthodes
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                   COUCHE MÉTIER                              │
│                    (Services)                                │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │CallsServices │  │AgentsServices│  │ProjectService│     │
│  │     Imp      │  │     Imp      │  │     Imp      │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
│                                                              │
│  Logique métier: Validation, Calculs, Transformations       │
└──────────────────────────┬──────────────────────────────────┘
                           │ Accès aux données
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                 COUCHE D'ACCÈS AUX DONNÉES                   │
│                    (Repositories)                            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │ICallsRepo    │  │IAgents       │  │IProjects     │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
│                                                              │
│  JpaRepository: save(), findById(), findAll(), delete()     │
└──────────────────────────┬──────────────────────────────────┘
                           │ SQL Queries
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                    COUCHE PERSISTANCE                        │
│                     (JPA/Hibernate)                          │
│                                                              │
│  Génère automatiquement:                                    │
│  • CREATE TABLE agents (...)                                │
│  • INSERT INTO calls VALUES (...)                           │
│  • SELECT * FROM projects WHERE id = ?                      │
└──────────────────────────┬──────────────────────────────────┘
                           │ JDBC
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                   BASE DE DONNÉES                            │
│                      MySQL (my_db)                           │
│                                                              │
│  Tables: agents, ai_systems, calls, projects, ...           │
└─────────────────────────────────────────────────────────────┘
```

## 🎨 6. FLUX DE DONNÉES : Créer un Agent

```
1️⃣ CLIENT envoie POST /api/agents
   Body: { "name": "Alice", "skills": ["Sales"], "available": true }
              │
              ▼
2️⃣ CONTROLLER (AgentsRestController)
   @PostMapping
   public ResponseEntity<Agents> createAgent(@RequestBody Agents agent)
              │
              ▼
3️⃣ SERVICE (AgentsServicesImp)
   public Agents addAgent(Agents agent) {
       // Validation
       if (agent.getName() == null) throw new Exception();
       // Sauvegarde
       return agentsRepository.save(agent);
   }
              │
              ▼
4️⃣ REPOSITORY (IAgents extends JpaRepository)
   save(agent) → Hibernate génère SQL
              │
              ▼
5️⃣ HIBERNATE génère:
   INSERT INTO agents (name, available) VALUES ('Alice', true);
   INSERT INTO agents_skills (agents_id, skills) VALUES (1, 'Sales');
              │
              ▼
6️⃣ MYSQL exécute les requêtes
   → Nouvelle ligne dans la table 'agents'
   → ID généré automatiquement = 1
              │
              ▼ (Retour)
7️⃣ CONTROLLER retourne:
   Status: 201 CREATED
   Body: { "agentsId": 1, "name": "Alice", "skills": ["Sales"], "available": true }
```

## 🔍 7. COMPARAISON SQL vs JPA

### ❌ Approche traditionnelle (SQL)

```java
// Code Java sans JPA
public Agents getAgentById(Long id) {
    String sql = "SELECT * FROM agents WHERE agents_id = ?";
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setLong(1, id);
    ResultSet rs = stmt.executeQuery();
    
    if (rs.next()) {
        Agents agent = new Agents();
        agent.setAgentsId(rs.getLong("agents_id"));
        agent.setName(rs.getString("name"));
        agent.setAvailable(rs.getBoolean("available"));
        // ... mapper tous les champs manuellement
        return agent;
    }
    return null;
}
```

### ✅ Approche JPA (Moderne)

```java
// Code Java avec JPA
public Agents getAgentById(Long id) {
    return agentsRepository.findById(id).orElse(null);
}
```

**Gain :**
- 90% de code en moins
- Pas d'erreurs SQL manuelles
- Gestion automatique des relations
- Indépendant de la base de données

## 🏗️ 8. PATTERNS DE CONCEPTION UTILISÉS

### 🎯 Pattern 1: MVC (Model-View-Controller)
```
Model      → Entities (Agents, Calls, Projects)
View       → JSON (pas de HTML, c'est une API)
Controller → RestControllers
```

### 🎯 Pattern 2: Repository Pattern
```
Interface → IAgents, ICallsRepository
Implémentation → JpaRepository (Spring Data)
```

### 🎯 Pattern 3: Dependency Injection (IoC)
```java
// Spring crée et injecte automatiquement
@RequiredArgsConstructor
public class CallsServicesImp {
    private final ICallsRepository repo;  // ← Injecté par Spring
}
```

### 🎯 Pattern 4: DTO (Data Transfer Object) - À implémenter
```java
// Actuellement, on retourne les entités directement
// Mieux: Créer des DTOs
public class CallsDTO {
    private Long id;
    private String phoneNumber;
    private String status;
    // Sans les relations complexes
}
```

## 🔐 9. SÉCURITÉ (À implémenter)

### État actuel: ⚠️ Pas de sécurité
```
Tout le monde peut:
✓ Créer des appels
✓ Modifier des projets
✓ Supprimer des agents
✓ Accéder à toutes les données
```

### À implémenter: 🔒 Spring Security

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Ajouter authentification JWT
    // Ajouter rôles (ADMIN, USER, MANAGER)
    // Protéger les endpoints
}
```

## 📊 10. STATISTIQUES DU PROJET

```
📁 Fichiers Java:
   ├─ 5 Entités        (entities/)
   ├─ 2 Enums          (Enum/)
   ├─ 5 Repositories   (Repositories/)
   ├─ 10 Services      (Services/)
   └─ 3 Controllers    (Controllers/)
   ───────────────────────────────
   Total: 25 fichiers Java

📊 Lignes de code (approximatif):
   ├─ Entities:      ~200 lignes
   ├─ Services:      ~300 lignes
   ├─ Controllers:   ~150 lignes
   ├─ Repositories:  ~50 lignes
   └─ Config:        ~20 lignes
   ───────────────────────────────
   Total: ~720 lignes

🗄️ Tables générées: 11
   ├─ agents
   ├─ agents_skills
   ├─ ai_systems
   ├─ ai_systems_skills
   ├─ calls
   ├─ calls_required_skills
   ├─ project_details
   ├─ projects
   └─ projects_agents

🌐 Endpoints API: 8
   ├─ GET    /
   ├─ GET    /health
   ├─ GET    /api/calls
   ├─ GET    /api/calls/{id}
   ├─ POST   /api/calls
   ├─ PUT    /api/calls
   ├─ DELETE /api/calls/{id}
   └─ GET    /api/projects/{id}/agents
```

## 🎓 11. GLOSSAIRE TECHNIQUE

| Terme | Signification |
|-------|---------------|
| **JPA** | Java Persistence API - Standard pour mapper objets ↔ BD |
| **Hibernate** | Implémentation de JPA (fait le travail réel) |
| **ORM** | Object-Relational Mapping - Lie objets Java aux tables SQL |
| **Entity** | Classe Java qui représente une table de BD |
| **Repository** | Interface pour accéder aux données (CRUD) |
| **Service** | Couche métier avec la logique applicative |
| **Controller** | Gère les requêtes HTTP et retourne des réponses |
| **DTO** | Data Transfer Object - Objet pour transférer des données |
| **CRUD** | Create, Read, Update, Delete - Opérations de base |
| **REST** | Representational State Transfer - Architecture API |
| **JSON** | JavaScript Object Notation - Format de données |
| **Endpoint** | URL accessible de l'API (ex: /api/calls) |
| **IoC** | Inversion of Control - Spring gère les dépendances |
| **DI** | Dependency Injection - Injection automatique |
| **Bean** | Objet géré par Spring |
| **MVC** | Model-View-Controller - Pattern architectural |

## 🚀 12. AMÉLIORATIONS FUTURES

### Phase 1: Fonctionnalités de base
- [ ] Compléter tous les services (Agents, AiSystems, ProjectDetails)
- [ ] Ajouter validations (@Valid, @NotNull, @Size)
- [ ] Gérer les erreurs avec @ExceptionHandler
- [ ] Ajouter pagination et tri

### Phase 2: Fonctionnalités avancées
- [ ] Recherche avec critères (@Query)
- [ ] Export de données (CSV, Excel)
- [ ] Statistiques et rapports
- [ ] Notifications (Email, SMS)

### Phase 3: Sécurité et Performance
- [ ] Authentification JWT
- [ ] Gestion des rôles (RBAC)
- [ ] Cache avec Redis
- [ ] Optimisation des requêtes

### Phase 4: Documentation et Tests
- [ ] Documentation Swagger/OpenAPI
- [ ] Tests unitaires (JUnit)
- [ ] Tests d'intégration
- [ ] CI/CD avec GitHub Actions

---

*Document créé pour faciliter la compréhension du projet Arctic10*

