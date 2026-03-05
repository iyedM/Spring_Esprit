package tn.esprit.iyed_mohamed_artic10.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Enum.CallSkills;
import tn.esprit.iyed_mohamed_artic10.Enum.CallStatus;
import tn.esprit.iyed_mohamed_artic10.Repositories.IAiSystems;
import tn.esprit.iyed_mohamed_artic10.Repositories.IAgents;
import tn.esprit.iyed_mohamed_artic10.Repositories.ICallsRepository;
import tn.esprit.iyed_mohamed_artic10.entities.Agents;
import tn.esprit.iyed_mohamed_artic10.entities.AiSystems;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Primary
@RequiredArgsConstructor
@Service
public class CallsServicesImp implements ICallsServices {

    private final ICallsRepository callsRepository;
    private final IAgents agentsRepository;
    private final IAiSystems aiSystemsRepository;

    @Override
    public Calls addCalls(Calls call) {
        call.setCallDateTime(LocalDateTime.now());
        call.setCallStatus(CallStatus.On_Hold);
        return callsRepository.save(call);
    }

    @Override
    public Calls UpdateCalls(Calls call) {
        return callsRepository.save(call);
    }

    @Override
    public Calls getById(Long call) {
        return callsRepository.findById(call).orElseThrow(() -> new EntityNotFoundException("not found"));
    }

    @Override
    public List<Calls> getAll() {
        return callsRepository.findAll();
    }

    @Override
    public void deleteCalls(Long Id) {
        callsRepository.deleteById(Id);
    }

    // ============ Q.1 - Assign call to agent with constraints ============
    @Override
    public void assignCallToAgent(Long callId, Long agentId) {
        Calls call = callsRepository.findById(callId)
                .orElseThrow(() -> new EntityNotFoundException("Appel non trouvé"));
        Agents agent = agentsRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent non trouvé"));

        // Règle 1 : L'agent doit être disponible
        if (!agent.isAvailable()) {
            throw new IllegalStateException("L'agent n'est pas disponible");
        }

        // Assigner l'appel à l'agent
        call.setAssignedAgent(agent);
        
        // Règle 2 : L'appel passe à IN_PROGRESS
        call.setCallStatus(CallStatus.In_progress);
        
        // Règle 3 : L'agent devient indisponible
        agent.setAvailable(false);
        
        callsRepository.save(call);
        agentsRepository.save(agent);
    }

    // ============ Q.2 - Assign call to AI system with max 2 calls constraint ============
    @Override
    public void assignCallToAISystem(Long callId, Long aiSystemId) {
        Calls call = callsRepository.findById(callId)
                .orElseThrow(() -> new EntityNotFoundException("Appel non trouvé"));
        AiSystems aiSystem = aiSystemsRepository.findById(aiSystemId)
                .orElseThrow(() -> new EntityNotFoundException("Système IA non trouvé"));

        // Règle 1 : L'IA doit être disponible
        if (!aiSystem.isAvailable()) {
            throw new IllegalStateException("Le système IA n'est pas disponible");
        }

        // Règle 2 : Une IA ne peut gérer que 2 appels à la fois
        if (aiSystem.getMyCalls() != null && aiSystem.getMyCalls().size() >= 2) {
            throw new IllegalStateException("Le système IA gère déjà 2 appels (limite atteinte)");
        }

        call.setAssignedAiSystem(aiSystem);
        call.setCallStatus(CallStatus.In_progress);
        callsRepository.save(call);
    }

    // ============ Q.3 - Check if call requires human agent based on skills ============
    @Override
    public boolean callRequiresHumanAgent(Calls call) {
        // Si l'appel nécessite TECHNICAL_SUPPORT → agent humain requis
        return call.getRequiredSkills() != null && 
               call.getRequiredSkills().contains(CallSkills.Technical_support);
    }

    // ============ Q.4 - Auto assign calls to competent available agents ============
    @Override
    public void autoAssignCallsToAgents(Set<Long> callIds) {
        for (Long callId : callIds) {
            Calls call = callsRepository.findById(callId)
                    .orElseThrow(() -> new EntityNotFoundException("Appel non trouvé"));

            // Vérifier si l'appel nécessite un agent (Q.3)
            if (callRequiresHumanAgent(call)) {
                // Trouver un agent disponible avec au moins une compétence requise
                List<Agents> agents = agentsRepository.findAll();
                
                for (Agents agent : agents) {
                    if (agent.isAvailable() && hasMatchingSkills(agent, call)) {
                        // Affecter l'appel à cet agent
                        call.setAssignedAgent(agent);
                        call.setCallStatus(CallStatus.In_progress);
                        agent.setAvailable(false);
                        
                        callsRepository.save(call);
                        agentsRepository.save(agent);
                        break;
                    }
                }
            }
        }
    }

    // ============ Q.5 - Intelligent assignment (agent or AI based on needs) ============
    @Override
    public void assignCallsToAgents(Set<Long> callIds) {
        for (Long callId : callIds) {
            Calls call = callsRepository.findById(callId)
                    .orElseThrow(() -> new EntityNotFoundException("Appel non trouvé"));

            if (callRequiresHumanAgent(call)) {
                // Chercher un agent disponible et compétent
                List<Agents> agents = agentsRepository.findAll();
                
                for (Agents agent : agents) {
                    if (agent.isAvailable() && hasMatchingSkills(agent, call)) {
                        call.setAssignedAgent(agent);
                        call.setCallStatus(CallStatus.In_progress);
                        agent.setAvailable(false);
                        
                        callsRepository.save(call);
                        agentsRepository.save(agent);
                        break;
                    }
                }
            }
            // Si l'appel ne nécessite pas une intervention humaine → pas d'affectation
        }
    }

    // Méthode helper pour vérifier si un agent a les compétences requises
    private boolean hasMatchingSkills(Agents agent, Calls call) {
        if (agent.getSkills() == null || call.getRequiredSkills() == null) {
            return false;
        }
        
        // Vérifier s'il y a au moins une compétence commune
        for (CallSkills skill : call.getRequiredSkills()) {
            if (agent.getSkills().contains(skill)) {
                return true;
            }
        }
        return false;
    }
}
