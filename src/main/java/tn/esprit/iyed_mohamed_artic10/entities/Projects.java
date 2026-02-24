package tn.esprit.iyed_mohamed_artic10.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectsid;

    private String libelle;

    private Date startDate;
    private Date endDate;

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.REMOVE } )
    ProjectDetails details;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Agents> agents;

    public long getProjectsid() {
        return projectsid;
    }

    public void setProjectsid(long projectsid) {
        this.projectsid = projectsid;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ProjectDetails getDetails() {
        return details;
    }

    public void setDetails(ProjectDetails details) {
        this.details = details;
    }

    public Set<Agents> getAgents() {
        return agents;
    }

    public void setAgents(Set<Agents> agents) {
        this.agents = agents;
    }
}
