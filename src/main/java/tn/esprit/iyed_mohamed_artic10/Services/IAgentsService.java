package tn.esprit.iyed_mohamed_artic10.Services;

import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Repositories.IAgents;

import java.util.List;

@Service
public interface IAgentsService {
    IAgents addIAgents (IAgents iAgents) ;
    IAgents UpdateIAgents (IAgents iAgents) ;
    IAgents getById (Long iAgents) ;
    List<IAgents> getAll () ;
    void deleteIAgents (Long Id);
}
