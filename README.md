# Smart Call Center - Projet Iyed Mohamed

Backend Spring Boot pour la gestion d’un call center avec agents, systèmes IA, projets, détails de projet et appels.

## Stack

- Spring Boot 3.5.10
- Java 17
- Spring Data JPA
- MySQL
- Lombok
- Swagger / OpenAPI

## Lancement

```bash
mvn clean install
mvn spring-boot:run
```

L’application démarre sur `http://localhost:8082/api`.

## API principales

- `GET /api/calls/get`
- `POST /api/calls/add`
- `PUT /api/calls/update`
- `DELETE /api/calls/delete/{id}`
- `GET /api/project/get`
- `POST /api/project/add`

## Structure

- `src/main/java/tn/esprit/iyed_mohamed_artic10/controllers`
- `src/main/java/tn/esprit/iyed_mohamed_artic10/services`
- `src/main/java/tn/esprit/iyed_mohamed_artic10/repositories`
- `src/main/java/tn/esprit/iyed_mohamed_artic10/entities`
- `src/main/java/tn/esprit/iyed_mohamed_artic10/Enum`

## Configuration

- Base de données MySQL locale
- Contexte serveur `/api`
- Port `8082`

## Auteur

Iyed Mohamed
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

