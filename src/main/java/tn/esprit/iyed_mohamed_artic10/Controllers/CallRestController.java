package tn.esprit.iyed_mohamed_artic10.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.iyed_mohamed_artic10.Services.ICallsServices;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calls")
public class CallRestController {

    private final ICallsServices callsServices;

    // Créer un call - POST /api/calls/add
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Calls addCall(@RequestBody Calls call) {
        return callsServices.addCalls(call);
    }

    // Mettre à jour un call - PUT /api/calls/update
    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public Calls updateCall(@RequestBody Calls call) {
        return callsServices.UpdateCalls(call);
    }

    // Récupérer un call par ID - GET /api/calls/get/{id}
    @GetMapping("/get/{id}")
    public Calls getCallById(@PathVariable Long id) {
        return callsServices.getById(id);
    }

    // Récupérer tous les calls - GET /api/calls/getAll
    @GetMapping("/getAll")
    public List<Calls> getAllCalls() {
        return callsServices.getAll();
    }

    // Supprimer un call - DELETE /api/calls/delete/{id}
    @DeleteMapping("/delete/{id}")
    public void deleteCall(@PathVariable Long id) {
        callsServices.deleteCalls(id);
    }

    // Assigner un call à un agent
    @PutMapping("/assign/{callId}/{agentId}")
    public Calls assignCallToAgent(@PathVariable Long callId, @PathVariable Long agentId) {
        return callsServices.assignToAgent(callId, agentId);
    }
}