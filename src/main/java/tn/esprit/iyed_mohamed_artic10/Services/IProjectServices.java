package tn.esprit.iyed_mohamed_artic10.Services;

import tn.esprit.iyed_mohamed_artic10.entities.Agents;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;
import tn.esprit.iyed_mohamed_artic10.entities.Projects;
import java.util.List;

public interface IProjectServices {
    Projects addProject(Projects p);
    Projects updateProject(Projects p);
    Projects getById(Long id);
    List<Projects> getAll();
    void deleteProject(Long id);

    // Ta méthode spécifique
    List<Agents> getAgent(Long IdProject);

}
