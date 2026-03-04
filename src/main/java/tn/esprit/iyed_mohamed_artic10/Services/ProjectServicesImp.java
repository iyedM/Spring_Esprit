package tn.esprit.iyed_mohamed_artic10.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Repositories.IProjects;
import tn.esprit.iyed_mohamed_artic10.entities.Agents;
import tn.esprit.iyed_mohamed_artic10.entities.Projects;

import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class ProjectServicesImp implements IProjectServices {

    private final IProjects projectRepository;

    @Override
    public Projects addProject(Projects p) {
        return projectRepository.save(p);
    }

    @Override
    public Projects updateProject(Projects p) {
        return projectRepository.save(p);
    }

    @Override
    public Projects getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    @Override
    public List<Projects> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Agents> getAgent(Long IdProject) {
        Projects project = projectRepository.findById(IdProject)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return project.getAgents().stream().toList();
    }
}
