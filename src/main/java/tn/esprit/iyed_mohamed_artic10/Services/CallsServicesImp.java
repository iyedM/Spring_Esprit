package tn.esprit.iyed_mohamed_artic10.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Repositories.IAgents;
import tn.esprit.iyed_mohamed_artic10.Repositories.ICallsRepository;
import tn.esprit.iyed_mohamed_artic10.entities.Agents;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

import java.util.List;

//primary hethy nesta3mlouha wa9t aandna deux srvice classes , donc on utilise @Primary pour indiquer a spring que c'est la classe a utiliser par defaut
@Primary
@RequiredArgsConstructor
@Service
public class CallsServicesImp implements ICallsServices{

    private final ICallsRepository callsRepository;
    private final IAgents agentsRepository;

    @Override
    public Calls addCalls(Calls call) {
        return callsRepository.save(call);
    }

    @Override
    public Calls UpdateCalls(Calls call) {
        return callsRepository.save(call);
    }

    @Override
    public Calls getById(Long call) {
//        return callsRepository.findById(call).orElse(null);
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

    @Override
    public Calls assignToAgent(Long CallsId, Long AgentId) {
        Calls call = callsRepository.findById(CallsId).orElseThrow(() -> new EntityNotFoundException("not found"));
        Agents Agent = agentsRepository.findById(AgentId).orElse(null);
        call.setAssignedAgent(Agent);

        return callsRepository.save(call);
    }


}
