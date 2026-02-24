package tn.esprit.iyed_mohamed_artic10.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Repositories.IAgents;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentsServicesImp implements IAgentsService{
    @Override
    public IAgents addIAgents(IAgents iAgents) {
        return null;
    }

    @Override
    public IAgents UpdateIAgents(IAgents iAgents) {
        return null;
    }

    @Override
    public IAgents getById(Long iAgents) {
        return null;
    }

    @Override
    public List<IAgents> getAll() {
        return List.of();
    }

    @Override
    public void deleteIAgents(Long Id) {

    }
}
