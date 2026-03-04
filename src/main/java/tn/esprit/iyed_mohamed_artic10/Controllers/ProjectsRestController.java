package tn.esprit.iyed_mohamed_artic10.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.iyed_mohamed_artic10.Services.IProjectServices;
import tn.esprit.iyed_mohamed_artic10.entities.Agents;
import tn.esprit.iyed_mohamed_artic10.entities.Projects;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectsRestController {

    private final IProjectServices projectServices;

    // Créer un projet - POST /api/projects/add
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Projects addProject(@RequestBody Projects project) {
        return projectServices.addProject(project);
    }

    // Mettre à jour un projet - PUT /api/projects/update
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Projects updateProject(@RequestBody Projects project) {
        return projectServices.updateProject(project);
    }

    // Supprimer un projet - DELETE /api/projects/delete/{id}
    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectServices.deleteProject(id);
    }

    // Récupérer un projet par ID - GET /api/projects/get/{id}
    @GetMapping("/get/{id}")
    public Projects getById(@PathVariable Long id) {
        return projectServices.getById(id);
    }

    // Récupérer tous les projets - GET /api/projects/getAll
    @GetMapping("/getAll")
    public List<Projects> getAll() {
        return projectServices.getAll();
    }

    // Récupérer les agents d'un projet - GET /api/projects/getAgents/{id}
    @GetMapping("/getAgents/{id}")
    public List<Agents> getAgentsByProject(@PathVariable Long id) {
        return projectServices.getAgent(id);
    }
}
