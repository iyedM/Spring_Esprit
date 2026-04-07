package tn.esprit.iyed_mohamed_artic10.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.iyed_mohamed_artic10.Enum.CallSkills;
import tn.esprit.iyed_mohamed_artic10.Enum.CallStatus;
import tn.esprit.iyed_mohamed_artic10.Services.ICallsServices;
import tn.esprit.iyed_mohamed_artic10.dto.HumanAgentRequiredResponse;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

import java.util.List;
import java.util.Set;

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

    // ============ Q.1 - Assigner un appel à un agent ============
    @PutMapping("/assignToAgent/{callId}/{agentId}")
    public void assignCallToAgent(@PathVariable Long callId, @PathVariable Long agentId) {
        callsServices.assignCallToAgent(callId, agentId);
    }

    // ============ Q.2 - Assigner un appel à un système IA ============
    @PutMapping("/assignToAISystem/{callId}/{aiSystemId}")
    public void assignCallToAISystem(@PathVariable Long callId, @PathVariable Long aiSystemId) {
        callsServices.assignCallToAISystem(callId, aiSystemId);
    }

    // ============ Q.3 - Vérifier si un appel nécessite un agent humain ============
    @GetMapping("/requiresHumanAgent/{callId}")
    public HumanAgentRequiredResponse callRequiresHumanAgent(@PathVariable Long callId) {
        Calls call = callsServices.getById(callId);
        boolean requires = callsServices.callRequiresHumanAgent(call);
        String message = requires ? 
            "Cet appel nécessite l'intervention d'un agent humain (Technical_support)" :
            "Cet appel peut être géré par un système IA";
        return new HumanAgentRequiredResponse(callId, requires, message);
    }

    // ============ Q.4 - Affectation automatique d'appels aux agents ============
    @PostMapping("/autoAssignCallsToAgents")
    public void autoAssignCallsToAgents(@RequestBody Set<Long> callIds) {
        callsServices.autoAssignCallsToAgents(callIds);
    }

    // ============ Q.5 - Affectation intelligente d'appels (agent ou IA) ============
    @PostMapping("/assignCallsToAgents")
    public void assignCallsToAgents(@RequestBody Set<Long> callIds) {
        callsServices.assignCallsToAgents(callIds);
    }

    @GetMapping("/findByStatusAndAgentId/{status}/{agentId}")
    public List<Calls> findByStatusAndAssignedAgent_AgentsId(@PathVariable CallStatus status, @PathVariable long agentId) {
        return callsServices.findByStatusAndAssignedAgent_AgentsId(status, agentId);
    }

    @GetMapping("/findByStatus/{status}")
    public List<Calls> findByStatus(@PathVariable CallStatus status) {
        return callsServices.findByStatus(status);
    }

    @GetMapping("/findUnassigned")
    public List<Calls> findByAssignedAgentIsNull() {
        return callsServices.findByAssignedAgentIsNull();
    }

    @GetMapping("/findByRequiredSkills/{skill}")
    public List<Calls> findByRequiredSkillsContains(@PathVariable CallSkills skill) {
        return callsServices.findByRequiredSkillsContains(skill);
    }

    @GetMapping("/getTop5ByCallsDateTimeAndRequiredSkillsIn/{skill}")
    public List<Calls> findTop5ByOrderByCallDateTimeAscAndRequiredSkillsIn(@PathVariable CallSkills skill) {
        return callsServices.findTop5ByOrderByCallDateTimeAscAndRequiredSkillsIn(skill);
    }

    @GetMapping("/existsByPhoneNumber/{phoneNumber}")
    public boolean existsByPhoneNumber(@PathVariable String phoneNumber) {
        return callsServices.existsByPhoneNumber(phoneNumber);
    }

    @GetMapping("/countByStatus/{status}")
    public long countByStatus(@PathVariable CallStatus status) {
        return callsServices.countByStatus(status);
    }
}