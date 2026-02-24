# 🛠️ GUIDE PRATIQUE - Exemples et Tutoriels

## 📝 1. EXEMPLES DE REQUÊTES API (Postman/curl)

### 1.1 Page d'accueil

**Requête:**
```bash
curl -X GET http://localhost:8085/
```

**Réponse:**
```json
{
  "message": "Bienvenue sur l'API Arctic10 - Iyed Mohamed",
  "status": "Application is running successfully!",
  "endpoints": {
    "calls": "/api/calls",
    "projects": "/api/projects"
  }
}
```

---

### 1.2 Créer un appel

**Requête:**
```bash
curl -X POST http://localhost:8085/api/calls \
  -H "Content-Type: application/json" \
  -d '{
    "phoneNumber": "0612345678",
    "callStatus": "On_Hold",
    "requiredSkills": ["Sales", "Customer_service"]
  }'
```

**Réponse (201 Created):**
```json
{
  "callsId": 1,
  "phoneNumber": "0612345678",
  "callStatus": "On_Hold",
  "requiredSkills": ["Sales", "Customer_service"],
  "assignedAgent": null,
  "assignedAiSystem": null
}
```

---

### 1.3 Récupérer tous les appels

**Requête:**
```bash
curl -X GET http://localhost:8085/api/calls
```

**Réponse (200 OK):**
```json
[
  {
    "callsId": 1,
    "phoneNumber": "0612345678",
    "callStatus": "On_Hold",
    "requiredSkills": ["Sales", "Customer_service"],
    "assignedAgent": null,
    "assignedAiSystem": null
  },
  {
    "callsId": 2,
    "phoneNumber": "0698765432",
    "callStatus": "In_progress",
    "requiredSkills": ["Technical_support"],
    "assignedAgent": {
      "agentsId": 5,
      "name": "Pierre"
    },
    "assignedAiSystem": null
  }
]
```

---

### 1.4 Récupérer un appel par ID

**Requête:**
```bash
curl -X GET http://localhost:8085/api/calls/1
```

**Réponse (200 OK):**
```json
{
  "callsId": 1,
  "phoneNumber": "0612345678",
  "callStatus": "On_Hold",
  "requiredSkills": ["Sales", "Customer_service"],
  "assignedAgent": null,
  "assignedAiSystem": null
}
```

**En cas d'erreur (404 Not Found):**
```json
{
  "timestamp": "2026-02-24T02:45:00",
  "status": 404,
  "error": "Not Found",
  "message": "Entity not found"
}
```

---

### 1.5 Modifier un appel

**Requête:**
```bash
curl -X PUT http://localhost:8085/api/calls \
  -H "Content-Type: application/json" \
  -d '{
    "callsId": 1,
    "phoneNumber": "0612345678",
    "callStatus": "RESOLVED",
    "requiredSkills": ["Sales"]
  }'
```

**Réponse (200 OK):**
```json
{
  "callsId": 1,
  "phoneNumber": "0612345678",
  "callStatus": "RESOLVED",
  "requiredSkills": ["Sales"],
  "assignedAgent": {
    "agentsId": 3,
    "name": "Marie"
  },
  "assignedAiSystem": null
}
```

---

### 1.6 Supprimer un appel

**Requête:**
```bash
curl -X DELETE http://localhost:8085/api/calls/1
```

**Réponse (204 No Content):**
```
(Pas de corps de réponse)
```

---

### 1.7 Récupérer les agents d'un projet

**Requête:**
```bash
curl -X GET http://localhost:8085/api/projects/5/agents
```

**Réponse (200 OK):**
```json
[
  {
    "agentsId": 1,
    "name": "Marie",
    "skills": ["Sales", "Billing"],
    "available": true
  },
  {
    "agentsId": 2,
    "name": "Pierre",
    "skills": ["Technical_support"],
    "available": false
  },
  {
    "agentsId": 3,
    "name": "Sophie",
    "skills": ["Customer_service"],
    "available": true
  }
]
```

---

## 🧪 2. TESTER AVEC POSTMAN

### Configuration Postman

**1. Créer une nouvelle collection "Arctic10 API"**

**2. Variables d'environnement:**
```
base_url = http://localhost:8085
```

**3. Requêtes à créer:**

#### Collection: Calls
```
GET    {{base_url}}/api/calls          → Tous les appels
GET    {{base_url}}/api/calls/1        → Appel par ID
POST   {{base_url}}/api/calls          → Créer appel
PUT    {{base_url}}/api/calls          → Modifier appel
DELETE {{base_url}}/api/calls/1        → Supprimer appel
```

#### Collection: Projects
```
GET    {{base_url}}/api/projects/1/agents  → Agents du projet
```

#### Collection: Health
```
GET    {{base_url}}/                   → Page d'accueil
GET    {{base_url}}/health             → Santé
```

---

## 💾 3. INSERTION DE DONNÉES DE TEST (MySQL)

### Ouvrir MySQL Workbench ou terminal:

```bash
mysql -u iyed -p
# Entrer le mot de passe: iyed
USE my_db;
```

### Insérer des agents:

```sql
-- Agent 1: Marie (Sales, Billing)
INSERT INTO agents (name, available) VALUES ('Marie', true);
INSERT INTO agents_skills (agents_agents_id, skills) VALUES 
  (1, 'Sales'),
  (1, 'Billing');

-- Agent 2: Pierre (Technical_support)
INSERT INTO agents (name, available) VALUES ('Pierre', true);
INSERT INTO agents_skills (agents_agents_id, skills) VALUES 
  (2, 'Technical_support');

-- Agent 3: Sophie (Customer_service)
INSERT INTO agents (name, available) VALUES ('Sophie', true);
INSERT INTO agents_skills (agents_agents_id, skills) VALUES 
  (3, 'Customer_service');
```

### Insérer des systèmes IA:

```sql
-- IA 1: GPT-4 (Technical_support, Customer_service)
INSERT INTO ai_systems (ai_systems_id, type, available) VALUES (1, 'GPT-4', true);
INSERT INTO ai_systems_skills (ai_systems_ai_systems_id, skills) VALUES 
  (1, 'Technical_support'),
  (1, 'Customer_service');

-- IA 2: Claude (Sales, Billing)
INSERT INTO ai_systems (ai_systems_id, type, available) VALUES (2, 'Claude', true);
INSERT INTO ai_systems_skills (ai_systems_ai_systems_id, skills) VALUES 
  (2, 'Sales'),
  (2, 'Billing');
```

### Insérer des appels:

```sql
-- Appel 1: En attente, besoin de Sales
INSERT INTO calls (phone_number, call_status, assigned_agent_agents_id) 
VALUES ('0612345678', 'On_Hold', NULL);
INSERT INTO calls_required_skills (calls_calls_id, required_skills) 
VALUES (1, 'Sales');

-- Appel 2: En cours avec Pierre
INSERT INTO calls (phone_number, call_status, assigned_agent_agents_id) 
VALUES ('0698765432', 'In_progress', 2);
INSERT INTO calls_required_skills (calls_calls_id, required_skills) 
VALUES (2, 'Technical_support');

-- Appel 3: Résolu par IA GPT-4
INSERT INTO calls (phone_number, call_status, assigned_ai_system_ai_systems_id) 
VALUES ('0655443322', 'RESOLVED', 1);
INSERT INTO calls_required_skills (calls_calls_id, required_skills) 
VALUES (3, 'Customer_service');
```

### Insérer des projets:

```sql
-- Détails du projet
INSERT INTO project_details (budget, client) 
VALUES (100000, 'ESPRIT University');

-- Projet
INSERT INTO projects (libelle, start_date, end_date, details_details_id) 
VALUES ('Application Mobile E-Learning', '2026-02-01', '2026-06-30', 1);

-- Assigner des agents au projet
INSERT INTO projects_agents (projects_projectsid, agents_agents_id) VALUES 
  (1, 1),  -- Marie
  (1, 2),  -- Pierre
  (1, 3);  -- Sophie
```

---

## 📊 4. REQUÊTES SQL UTILES

### Voir tous les agents avec leurs compétences:

```sql
SELECT a.agents_id, a.name, a.available, GROUP_CONCAT(s.skills) as skills
FROM agents a
LEFT JOIN agents_skills s ON a.agents_id = s.agents_agents_id
GROUP BY a.agents_id;
```

**Résultat:**
```
+------------+--------+-----------+-----------------+
| agents_id  | name   | available | skills          |
+------------+--------+-----------+-----------------+
|          1 | Marie  |         1 | Sales,Billing   |
|          2 | Pierre |         1 | Technical_sup.. |
|          3 | Sophie |         1 | Customer_serv.. |
+------------+--------+-----------+-----------------+
```

### Voir tous les appels avec leur statut:

```sql
SELECT c.calls_id, c.phone_number, c.call_status, 
       a.name as agent_name, ai.type as ai_type
FROM calls c
LEFT JOIN agents a ON c.assigned_agent_agents_id = a.agents_id
LEFT JOIN ai_systems ai ON c.assigned_ai_system_ai_systems_id = ai.ai_systems_id;
```

**Résultat:**
```
+----------+--------------+-------------+------------+---------+
| calls_id | phone_number | call_status | agent_name | ai_type |
+----------+--------------+-------------+------------+---------+
|        1 | 0612345678   | On_Hold     | NULL       | NULL    |
|        2 | 0698765432   | In_progress | Pierre     | NULL    |
|        3 | 0655443322   | RESOLVED    | NULL       | GPT-4   |
+----------+--------------+-------------+------------+---------+
```

### Compter les appels par statut:

```sql
SELECT call_status, COUNT(*) as total
FROM calls
GROUP BY call_status;
```

**Résultat:**
```
+-------------+-------+
| call_status | total |
+-------------+-------+
| On_Hold     |     1 |
| In_progress |     1 |
| RESOLVED    |     1 |
+-------------+-------+
```

### Voir les projets avec leurs agents:

```sql
SELECT p.projectsid, p.libelle, GROUP_CONCAT(a.name) as team_members
FROM projects p
LEFT JOIN projects_agents pa ON p.projectsid = pa.projects_projectsid
LEFT JOIN agents a ON pa.agents_agents_id = a.agents_id
GROUP BY p.projectsid;
```

**Résultat:**
```
+------------+---------------------------+-----------------------+
| projectsid | libelle                   | team_members          |
+------------+---------------------------+-----------------------+
|          1 | Application Mobile...     | Marie,Pierre,Sophie   |
+------------+---------------------------+-----------------------+
```

---

## 🎯 5. SCÉNARIOS D'UTILISATION

### Scénario 1: Réception d'un nouvel appel

**Étapes:**

1. **Client appelle le centre**
```bash
curl -X POST http://localhost:8085/api/calls \
  -H "Content-Type: application/json" \
  -d '{
    "phoneNumber": "0612345678",
    "callStatus": "On_Hold",
    "requiredSkills": ["Technical_support"]
  }'
```

2. **Système recherche un agent disponible**
```sql
SELECT * FROM agents a
JOIN agents_skills s ON a.agents_id = s.agents_agents_id
WHERE a.available = true AND s.skills = 'Technical_support';
```

3. **Assigner l'agent trouvé**
```bash
curl -X PUT http://localhost:8085/api/calls \
  -H "Content-Type: application/json" \
  -d '{
    "callsId": 1,
    "phoneNumber": "0612345678",
    "callStatus": "In_progress",
    "assignedAgent": { "agentsId": 2 }
  }'
```

4. **Marquer l'agent comme occupé**
```sql
UPDATE agents SET available = false WHERE agents_id = 2;
```

5. **Résoudre l'appel**
```bash
curl -X PUT http://localhost:8085/api/calls \
  -H "Content-Type: application/json" \
  -d '{
    "callsId": 1,
    "callStatus": "RESOLVED"
  }'
```

6. **Libérer l'agent**
```sql
UPDATE agents SET available = true WHERE agents_id = 2;
```

---

### Scénario 2: Créer un nouveau projet

**Étapes:**

1. **Créer les détails du projet** (via SQL ou API à implémenter)
```sql
INSERT INTO project_details (budget, client) 
VALUES (50000, 'TechCorp');
```

2. **Créer le projet**
```sql
INSERT INTO projects (libelle, start_date, end_date, details_details_id) 
VALUES ('Site Web E-Commerce', '2026-03-01', '2026-05-31', 2);
```

3. **Assigner des agents**
```sql
INSERT INTO projects_agents (projects_projectsid, agents_agents_id) VALUES 
  (2, 1),  -- Marie
  (2, 3);  -- Sophie
```

4. **Vérifier l'équipe du projet**
```bash
curl -X GET http://localhost:8085/api/projects/2/agents
```

---

### Scénario 3: Transférer un appel

**Étapes:**

1. **IA ne peut pas résoudre**
```bash
curl -X PUT http://localhost:8085/api/calls \
  -H "Content-Type: application/json" \
  -d '{
    "callsId": 5,
    "callStatus": "TRANSFERRED",
    "assignedAiSystem": null
  }'
```

2. **Trouver un agent disponible**
```sql
SELECT * FROM agents WHERE available = true LIMIT 1;
```

3. **Assigner à l'agent**
```bash
curl -X PUT http://localhost:8085/api/calls \
  -H "Content-Type: application/json" \
  -d '{
    "callsId": 5,
    "callStatus": "In_progress",
    "assignedAgent": { "agentsId": 1 }
  }'
```

---

## 🔧 6. DÉBOGAGE ET LOGS

### Activer les logs SQL dans application.properties:

```properties
# Afficher les requêtes SQL
spring.jpa.show-sql=true

# Formater les requêtes SQL
spring.jpa.properties.hibernate.format_sql=true

# Afficher les valeurs des paramètres
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# Logs Spring
logging.level.org.springframework.web=DEBUG
logging.level.tn.esprit.iyed_mohamed_artic10=DEBUG
```

### Voir les logs dans la console:

```
Hibernate: 
    insert 
    into
        calls
        (call_status, phone_number, assigned_agent_agents_id) 
    values
        (?, ?, ?)
        
2026-02-24 02:45:00.123 TRACE --- BasicBinder : binding parameter [1] as [VARCHAR] - [On_Hold]
2026-02-24 02:45:00.124 TRACE --- BasicBinder : binding parameter [2] as [VARCHAR] - [0612345678]
2026-02-24 02:45:00.125 TRACE --- BasicBinder : binding parameter [3] as [BIGINT] - [null]
```

---

## 📱 7. INTÉGRATION FRONTEND (Exemple JavaScript)

### Récupérer tous les appels:

```javascript
// Vanilla JavaScript
fetch('http://localhost:8085/api/calls')
  .then(response => response.json())
  .then(calls => {
    console.log('Appels reçus:', calls);
    calls.forEach(call => {
      console.log(`Appel ${call.callsId}: ${call.phoneNumber} - ${call.callStatus}`);
    });
  })
  .catch(error => console.error('Erreur:', error));
```

### Créer un appel:

```javascript
const newCall = {
  phoneNumber: '0612345678',
  callStatus: 'On_Hold',
  requiredSkills: ['Sales']
};

fetch('http://localhost:8085/api/calls', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(newCall)
})
  .then(response => response.json())
  .then(call => {
    console.log('Appel créé:', call);
    alert(`Appel créé avec l'ID: ${call.callsId}`);
  })
  .catch(error => console.error('Erreur:', error));
```

### Exemple avec React:

```jsx
import React, { useState, useEffect } from 'react';

function CallsList() {
  const [calls, setCalls] = useState([]);
  
  useEffect(() => {
    fetch('http://localhost:8085/api/calls')
      .then(res => res.json())
      .then(data => setCalls(data))
      .catch(err => console.error(err));
  }, []);
  
  return (
    <div>
      <h1>Liste des appels</h1>
      <ul>
        {calls.map(call => (
          <li key={call.callsId}>
            {call.phoneNumber} - {call.callStatus}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default CallsList;
```

---

## 🎨 8. EXEMPLE DE PAGE HTML (Test simple)

Créez un fichier `test.html`:

```html
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Arctic10 API - Test</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        button { padding: 10px 20px; margin: 5px; cursor: pointer; }
        #result { background: #f0f0f0; padding: 15px; margin-top: 20px; }
        pre { background: #fff; padding: 10px; border: 1px solid #ccc; }
    </style>
</head>
<body>
    <h1>🎯 Arctic10 API - Test Interface</h1>
    
    <h2>Appels (Calls)</h2>
    <button onclick="getAllCalls()">Tous les appels</button>
    <button onclick="getCallById(1)">Appel ID 1</button>
    <button onclick="createCall()">Créer un appel</button>
    
    <h2>Projets (Projects)</h2>
    <button onclick="getProjectAgents(1)">Agents du projet 1</button>
    
    <h2>Système</h2>
    <button onclick="checkHealth()">Vérifier santé</button>
    
    <div id="result">
        <h3>Résultat:</h3>
        <pre id="output">Cliquez sur un bouton pour tester l'API...</pre>
    </div>

    <script>
        const BASE_URL = 'http://localhost:8085';
        
        function displayResult(data) {
            document.getElementById('output').textContent = 
                JSON.stringify(data, null, 2);
        }
        
        async function getAllCalls() {
            try {
                const response = await fetch(`${BASE_URL}/api/calls`);
                const data = await response.json();
                displayResult(data);
            } catch (error) {
                displayResult({ error: error.message });
            }
        }
        
        async function getCallById(id) {
            try {
                const response = await fetch(`${BASE_URL}/api/calls/${id}`);
                const data = await response.json();
                displayResult(data);
            } catch (error) {
                displayResult({ error: error.message });
            }
        }
        
        async function createCall() {
            const newCall = {
                phoneNumber: '0612345678',
                callStatus: 'On_Hold',
                requiredSkills: ['Sales', 'Customer_service']
            };
            
            try {
                const response = await fetch(`${BASE_URL}/api/calls`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(newCall)
                });
                const data = await response.json();
                displayResult(data);
            } catch (error) {
                displayResult({ error: error.message });
            }
        }
        
        async function getProjectAgents(id) {
            try {
                const response = await fetch(`${BASE_URL}/api/projects/${id}/agents`);
                const data = await response.json();
                displayResult(data);
            } catch (error) {
                displayResult({ error: error.message });
            }
        }
        
        async function checkHealth() {
            try {
                const response = await fetch(`${BASE_URL}/health`);
                const data = await response.json();
                displayResult(data);
            } catch (error) {
                displayResult({ error: error.message });
            }
        }
    </script>
</body>
</html>
```

**Ouvrir dans un navigateur:** `file:///chemin/vers/test.html`

---

## 🔍 9. CODES HTTP ET LEUR SIGNIFICATION

| Code | Statut | Signification | Quand ? |
|------|--------|---------------|---------|
| 200 | OK | Succès | GET, PUT réussi |
| 201 | Created | Créé avec succès | POST réussi |
| 204 | No Content | Supprimé avec succès | DELETE réussi |
| 400 | Bad Request | Données invalides | JSON mal formé |
| 404 | Not Found | Ressource introuvable | ID inexistant |
| 500 | Internal Error | Erreur serveur | Bug dans le code |

---

## 📝 10. CHECKLIST DE TEST

### ✅ Tests à effectuer:

#### Appels (Calls)
- [ ] Créer un appel avec POST /api/calls
- [ ] Récupérer tous les appels avec GET /api/calls
- [ ] Récupérer un appel par ID avec GET /api/calls/{id}
- [ ] Modifier un appel avec PUT /api/calls
- [ ] Supprimer un appel avec DELETE /api/calls/{id}
- [ ] Tester avec un ID inexistant (doit retourner 404)
- [ ] Tester avec des données invalides

#### Projets (Projects)
- [ ] Récupérer les agents d'un projet avec GET /api/projects/{id}/agents
- [ ] Tester avec un projet sans agents
- [ ] Tester avec un ID de projet inexistant

#### Système
- [ ] Vérifier la page d'accueil GET /
- [ ] Vérifier l'état de santé GET /health
- [ ] Vérifier que l'application écoute sur le port 8085

#### Base de données
- [ ] Vérifier que les tables sont créées automatiquement
- [ ] Insérer des données de test
- [ ] Vérifier les relations entre tables
- [ ] Tester les cascades (delete, update)

---

**Ce guide pratique est maintenant complet ! Vous pouvez tester toutes les fonctionnalités de l'API Arctic10.** 🚀

