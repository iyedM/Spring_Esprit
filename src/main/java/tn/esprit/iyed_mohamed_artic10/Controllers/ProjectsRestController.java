package tn.esprit.iyed_mohamed_artic10.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.iyed_mohamed_artic10.Services.IProjectServices;
import tn.esprit.iyed_mohamed_artic10.entities.Agents;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProjectsRestController {

    private final IProjectServices projectServices;

    @GetMapping("/{id}/agents")
    public ResponseEntity<List<Agents>> getProjectAgents(@PathVariable Long id) {
        List<Agents> agents = projectServices.getAgent(id);
        return ResponseEntity.ok(agents);
    }
}

