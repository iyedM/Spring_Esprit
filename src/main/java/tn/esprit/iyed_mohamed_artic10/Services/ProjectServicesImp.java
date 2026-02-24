package tn.esprit.iyed_mohamed_artic10.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Repositories.IAgents;
import tn.esprit.iyed_mohamed_artic10.Repositories.IProjects;
import tn.esprit.iyed_mohamed_artic10.entities.Agents;
import tn.esprit.iyed_mohamed_artic10.entities.Projects;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServicesImp implements IProjectServices{

    public final IProjects iProjects;

    @Override
    public List<Agents> getAgent(Long IdProject) {
        Projects projects = iProjects.findById(IdProject).orElseThrow(() -> new EntityNotFoundException("not found"));
        return projects.getAgents().stream().toList();
    }
}
