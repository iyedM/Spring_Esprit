package tn.esprit.iyed_mohamed_artic10.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.iyed_mohamed_artic10.Services.IProjectDetailsServices;
import tn.esprit.iyed_mohamed_artic10.entities.ProjectDetails;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projectdetails")
public class ProjectDetailsRestController {

    private final IProjectDetailsServices projectDetailsServices;

    // Créer un détail de projet - POST /api/projectdetails/add
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ProjectDetails addProjectDetails(@RequestBody ProjectDetails projectDetails) {
        return projectDetailsServices.addProjectDetails(projectDetails);
    }

    // Mettre à jour un détail de projet - PUT /api/projectdetails/update
    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ProjectDetails updateProjectDetails(@RequestBody ProjectDetails projectDetails) {
        return projectDetailsServices.updateProjectDetails(projectDetails);
    }

    // Récupérer un détail de projet par ID - GET /api/projectdetails/get/{id}
    @GetMapping("/get/{id}")
    public ProjectDetails getProjectDetailsById(@PathVariable Long id) {
        return projectDetailsServices.getById(id);
    }

    // Récupérer tous les détails de projets - GET /api/projectdetails/getAll
    @GetMapping("/getAll")
    public List<ProjectDetails> getAllProjectDetails() {
        return projectDetailsServices.getAll();
    }

    // Supprimer un détail de projet - DELETE /api/projectdetails/delete/{id}
    @DeleteMapping("/delete/{id}")
    public void deleteProjectDetails(@PathVariable Long id) {
        projectDetailsServices.deleteProjectDetails(id);
    }
}
