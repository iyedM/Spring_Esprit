package tn.esprit.iyed_mohamed_artic10.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.iyed_mohamed_artic10.Services.IAiSystemsServices;
import tn.esprit.iyed_mohamed_artic10.entities.AiSystems;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/aisystems")
public class AiSystemsRestController {

    private final IAiSystemsServices aiSystemsServices;

    // Créer un système IA - POST /api/aisystems/add
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public AiSystems addAiSystem(@RequestBody AiSystems aiSystem) {
        return aiSystemsServices.addAiSystem(aiSystem);
    }

    // Mettre à jour un système IA - PUT /api/aisystems/update
    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public AiSystems updateAiSystem(@RequestBody AiSystems aiSystem) {
        return aiSystemsServices.updateAiSystem(aiSystem);
    }

    // Récupérer un système IA par ID - GET /api/aisystems/get/{id}
    @GetMapping("/get/{id}")
    public AiSystems getAiSystemById(@PathVariable Long id) {
        return aiSystemsServices.getById(id);
    }

    // Récupérer tous les systèmes IA - GET /api/aisystems/getAll
    @GetMapping("/getAll")
    public List<AiSystems> getAllAiSystems() {
        return aiSystemsServices.getAll();
    }

    // Supprimer un système IA - DELETE /api/aisystems/delete/{id}
    @DeleteMapping("/delete/{id}")
    public void deleteAiSystem(@PathVariable Long id) {
        aiSystemsServices.deleteAiSystem(id);
    }
}
