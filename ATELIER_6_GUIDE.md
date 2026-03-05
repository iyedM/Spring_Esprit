# **Atelier 6 : Spring Data JPA - Affectations**
## Guide de test complet

---

## **Q.1 - Assigner un appel à un agent avec contraintes**

### Endpoint
```
PUT /api/calls/assignToAgent/{callId}/{agentId}
```

### Règles implémentées
- ✅ L'agent doit être disponible
- ✅ L'appel passe à IN_PROGRESS
- ✅ L'agent devient indisponible

### Exemple de test Postman
```
PUT http://localhost:8085/api/calls/assignToAgent/1/1
```

### Cas d'erreur possibles
- **Agent non disponible** → `IllegalStateException`
- **Agent ou appel non trouvé** → `EntityNotFoundException`

---

## **Q.2 - Assigner un appel à un système IA**

### Endpoint
```
PUT /api/calls/assignToAISystem/{callId}/{aiSystemId}
```

### Règles implémentées
- ✅ L'IA doit être disponible
- ✅ Une IA ne peut gérer que 2 appels maximum
- ✅ L'appel passe à IN_PROGRESS

### Exemple de test Postman
```
PUT http://localhost:8085/api/calls/assignToAISystem/1/1
```

### Cas d'erreur possibles
- **Système IA non disponible** → `IllegalStateException`
- **Limite de 2 appels atteinte** → `IllegalStateException`

---

## **Q.3 - Vérifier si un appel nécessite un agent humain**

### Endpoint
```
GET /api/calls/requiresHumanAgent/{callId}
```

### Règle
- **Si l'appel contient `Technical_support`** → retourne `true` (agent requis)
- **Sinon** → retourne `false` (système IA peut gérer)

### Exemple de test Postman
```
GET http://localhost:8085/api/calls/requiresHumanAgent/1
```

### Réponse
```json
{
  "result": true
}
```

---

## **Q.4 - Affectation automatique d'appels aux agents**

### Endpoint
```
POST /api/calls/autoAssignCallsToAgents
```

### Algorithme
1. Pour chaque appel → vérifier s'il nécessite un agent (Q.3)
2. Si oui → trouver un agent disponible avec compétence requise
3. Assigner l'appel et mettre à jour le statut à IN_PROGRESS

### Exemple de test Postman
```
POST http://localhost:8085/api/calls/autoAssignCallsToAgents
Content-Type: application/json

[1, 2, 3]
```

---

## **Q.5 - Affectation intelligente d'appels**

### Endpoint
```
POST /api/calls/assignCallsToAgents
```

### Algorithme
- **Si appel nécessite agent** → affecter à un agent disponible/compétent
- **Si appel ne nécessite pas agent** → PAS d'affectation (peut être géré par IA)
- **Tous les appels de la liste peuvent ne pas être assignés**

### Exemple de test Postman
```
POST http://localhost:8085/api/calls/assignCallsToAgents
Content-Type: application/json

[1, 2, 3, 4]
```

---

## **Préparation des données de test**

### 1️⃣ Créer des agents
```bash
POST http://localhost:8085/api/agents/add
{
  "name": "Agent Technique",
  "skills": ["Technical_support", "Sales"],
  "available": true
}

POST http://localhost:8085/api/agents/add
{
  "name": "Agent Support Client",
  "skills": ["Customer_service", "Billing"],
  "available": true
}
```

### 2️⃣ Créer des systèmes IA
```bash
POST http://localhost:8085/api/aisystems/add
{
  "type": "ChatBot Support",
  "available": true,
  "skills": ["Customer_service", "Billing"]
}
```

### 3️⃣ Créer des appels de test

**Appel 1** (nécessite technique) :
```bash
POST http://localhost:8085/api/calls/add
{
  "phoneNumber": "+21655111111",
  "requiredSkills": ["Technical_support"]
}
```

**Appel 2** (ne nécessite pas technique) :
```bash
POST http://localhost:8085/api/calls/add
{
  "phoneNumber": "+21655222222",
  "requiredSkills": ["Customer_service", "Billing"]
}
```

**Appel 3** (nécessite technique) :
```bash
POST http://localhost:8085/api/calls/add
{
  "phoneNumber": "+21655333333",
  "requiredSkills": ["Technical_support", "Sales"]
}
```

**Appel 4** (ne nécessite pas technique) :
```bash
POST http://localhost:8085/api/calls/add
{
  "phoneNumber": "+21655444444",
  "requiredSkills": ["Billing"]
}
```

---

## **Scénario de test complet**

### Étape 1 : Créer les ressources
```
✓ POST agents/add → Agent Technique (ID=1)
✓ POST agents/add → Agent Support (ID=2)
✓ POST aisystems/add → ChatBot (ID=1)
✓ POST calls/add → Call 1 (Technical_support) (ID=1)
✓ POST calls/add → Call 2 (Customer_service) (ID=2)
✓ POST calls/add → Call 3 (Technical_support) (ID=3)
✓ POST calls/add → Call 4 (Billing) (ID=4)
```

### Étape 2 : Tester Q.3 (vérification des appels)
```
GET /calls/requiresHumanAgent/1 → true (Technical_support)
GET /calls/requiresHumanAgent/2 → false (Customer_service)
GET /calls/requiresHumanAgent/3 → true (Technical_support)
GET /calls/requiresHumanAgent/4 → false (Billing)
```

### Étape 3 : Test Q.4 (affectation automatique)
```
POST /calls/autoAssignCallsToAgents
Body: [1, 2, 3, 4]

Résultats attendus :
- Call 1 → Agent Technique (ID=1)
- Call 2 → PAS d'affectation (ne nécessite pas agent)
- Call 3 → Agent Support (ID=2)
- Call 4 → PAS d'affectation (ne nécessite pas agent)
```

### Étape 4 : Vérifier les statuts
```
GET /calls/getAll

Appels affectés doivent avoir :
- callStatus = In_progress
- assignedAgent ≠ null
- Statut des agents : available = false
```

---

## **Résumé des endpoints**

| Question | Méthode | Endpoint | Description |
|----------|---------|----------|-------------|
| Q.1 | PUT | `/calls/assignToAgent/{callId}/{agentId}` | Assigner appel à agent |
| Q.2 | PUT | `/calls/assignToAISystem/{callId}/{aiSystemId}` | Assigner appel à IA |
| Q.3 | GET | `/calls/requiresHumanAgent/{callId}` | Vérifier si agent requis |
| Q.4 | POST | `/calls/autoAssignCallsToAgents` | Affectation automatique |
| Q.5 | POST | `/calls/assignCallsToAgents` | Affectation intelligente |

---

## **Notes importantes**

1. **Compétences de l'enum `CallSkills`**
   - `Billing`
   - `Sales`
   - `Customer_service`
   - `Technical_support`

2. **Statuts de l'enum `CallStatus`**
   - `On_Hold` (défaut créé)
   - `In_progress` (affecté)
   - `TRANSFERRED`
   - `RESOLVED`
   - `ABANDONED`

3. **Lors de l'ajout d'un appel** :
   - La date/heure est automatiquement définie (`LocalDateTime.now()`)
   - Le statut passe automatiquement à `On_Hold`

4. **Les affectations automatiques** :
   - Parcourent les appels fournis
   - Ignorent ceux sans besoin d'agent
   - Cherchent le premier agent disponible avec compétences
   - Mettent à jour la disponibilité

---
