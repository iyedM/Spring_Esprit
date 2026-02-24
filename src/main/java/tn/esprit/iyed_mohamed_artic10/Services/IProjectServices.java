package tn.esprit.iyed_mohamed_artic10.Services;

import tn.esprit.iyed_mohamed_artic10.entities.Agents;

import java.util.List;

public interface IProjectServices  {
    List<Agents> getAgent(Long IdProject);
}
