package tn.esprit.iyed_mohamed_artic10.Services;

import tn.esprit.iyed_mohamed_artic10.entities.ProjectDetails;

import java.util.List;

public interface IProjectDetailsServices {
    ProjectDetails addProjectDetails(ProjectDetails projectDetails);
    ProjectDetails updateProjectDetails(ProjectDetails projectDetails);
    ProjectDetails getById(Long id);
    List<ProjectDetails> getAll();
    void deleteProjectDetails(Long id);
}
