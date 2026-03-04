package tn.esprit.iyed_mohamed_artic10.Services;

import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.entities.Agents;
import java.util.List;

public interface IAgentsService {

    Agents addAgent(Agents agent);
    Agents updateAgent(Agents agent);
    Agents getById(Long id);
    List<Agents> getAll();
    void deleteAgent(Long id);
}