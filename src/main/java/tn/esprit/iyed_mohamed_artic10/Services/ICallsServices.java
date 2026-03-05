package tn.esprit.iyed_mohamed_artic10.Services;

import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

import java.util.List;
import java.util.Set;

@Service
public interface ICallsServices {
    Calls addCalls (Calls c) ;
    Calls UpdateCalls (Calls c) ;
    Calls getById (Long c) ;
    List<Calls> getAll () ;
    void deleteCalls (Long Id);

    // Q.1 - Assign call to agent with constraints
    void assignCallToAgent(Long callId, Long agentId);

    // Q.2 - Assign call to AI system with max 2 calls constraint
    void assignCallToAISystem(Long callId, Long aiSystemId);

    // Q.3 - Check if call requires human agent based on skills
    boolean callRequiresHumanAgent(Calls call);

    // Q.4 - Auto assign calls to competent available agents
    void autoAssignCallsToAgents(Set<Long> callIds);

    // Q.5 - Intelligent assignment (agent or AI based on needs)
    void assignCallsToAgents(Set<Long> callIds);
}
