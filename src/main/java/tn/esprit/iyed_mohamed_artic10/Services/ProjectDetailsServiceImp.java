package tn.esprit.iyed_mohamed_artic10.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.entities.ProjectDetails;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectDetailsServiceImp implements IProjectDetailsServices{
    @Override
    public ProjectDetails addProjectDetails(ProjectDetails projectDetails) {
        return null;
    }

    @Override
    public ProjectDetails UpdateProjectDetails(ProjectDetails projectDetails) {
        return null;
    }

    @Override
    public ProjectDetails getById(Long projectDetails) {
        return null;
    }

    @Override
    public List<ProjectDetails> getAll() {
        return List.of();
    }

    @Override
    public void deleteProjectDetails(Long Id) {

    }
}
