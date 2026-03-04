package tn.esprit.iyed_mohamed_artic10.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.iyed_mohamed_artic10.Services.IAgentsService;
import tn.esprit.iyed_mohamed_artic10.entities.Agents;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agents")
public class AgentRestController {

    private final IAgentsService agentsService;

    // Créer un agent - POST /api/agents/add
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Agents addAgent(@RequestBody Agents agent) {
        return agentsService.addAgent(agent);
    }

    // Mettre à jour un agent - PUT /api/agents/update
    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public Agents updateAgent(@RequestBody Agents agent) {
        return agentsService.updateAgent(agent);
    }

    // Récupérer un agent par ID - GET /api/agents/get/{id}
    @GetMapping("/get/{id}")
    public Agents getAgentById(@PathVariable Long id) {
        return agentsService.getById(id);
    }

    // Récupérer tous les agents - GET /api/agents/getAll
    @GetMapping("/getAll")
    public List<Agents> getAllAgents() {
        return agentsService.getAll();
    }

    // Supprimer un agent - DELETE /api/agents/delete/{id}
    @DeleteMapping("/delete/{id}")
    public void deleteAgent(@PathVariable Long id) {
        agentsService.deleteAgent(id);
    }
}
