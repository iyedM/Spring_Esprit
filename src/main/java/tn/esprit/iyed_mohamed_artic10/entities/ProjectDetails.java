package tn.esprit.iyed_mohamed_artic10.entities;

import jakarta.persistence.*;

@Entity
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long detailsId;
    private double budget;
    private String Client;
    //@OneToOne(mappedBy = "details")
    //mappedby toujours dans le child

    // Projects projects;


};
