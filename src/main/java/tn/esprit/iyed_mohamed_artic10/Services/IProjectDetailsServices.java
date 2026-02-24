package tn.esprit.iyed_mohamed_artic10.Services;



import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.entities.ProjectDetails;

import java.util.List;

@Service
public interface IProjectDetailsServices {
    ProjectDetails addProjectDetails (ProjectDetails projectDetails) ;
    ProjectDetails UpdateProjectDetails (ProjectDetails projectDetails) ;
    ProjectDetails getById (Long projectDetails) ;
    List<ProjectDetails> getAll () ;
    void deleteProjectDetails (Long Id);
}
