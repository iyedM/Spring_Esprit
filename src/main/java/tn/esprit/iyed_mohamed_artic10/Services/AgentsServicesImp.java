package tn.esprit.iyed_mohamed_artic10.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Repositories.IAgents;
import tn.esprit.iyed_mohamed_artic10.entities.Agents;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentsServicesImp implements IAgentsService {

    private final IAgents agentsRepository;

    @Override
    public Agents addAgent(Agents agent) {
        return agentsRepository.save(agent);
    }

    @Override
    public Agents updateAgent(Agents agent) {
        return agentsRepository.save(agent);
    }

    @Override
    public Agents getById(Long id) {
        return agentsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Agents> getAll() {
        return agentsRepository.findAll();
    }

    @Override
    public void deleteAgent(Long id) {
        agentsRepository.deleteById(id);
    }
}