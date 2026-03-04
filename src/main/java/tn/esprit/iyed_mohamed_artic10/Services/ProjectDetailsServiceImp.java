package tn.esprit.iyed_mohamed_artic10.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Repositories.IProjectDetails;
import tn.esprit.iyed_mohamed_artic10.entities.ProjectDetails;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectDetailsServiceImp implements IProjectDetailsServices {

    private final IProjectDetails projectDetailsRepository;

    @Override
    public ProjectDetails addProjectDetails(ProjectDetails projectDetails) {
        return projectDetailsRepository.save(projectDetails);
    }

    @Override
    public ProjectDetails updateProjectDetails(ProjectDetails projectDetails) {
        return projectDetailsRepository.save(projectDetails);
    }

    @Override
    public ProjectDetails getById(Long id) {
        return projectDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProjectDetails> getAll() {
        return projectDetailsRepository.findAll();
    }

    @Override
    public void deleteProjectDetails(Long id) {
        projectDetailsRepository.deleteById(id);
    }
}
