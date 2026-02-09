package tn.esprit.mohamed_ali_baaroun_artic10.entities;

import jakarta.persistence.*;

@Entity
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailsId ;
    private Double budget;
    private String client;

    @OneToOne
    private Projects project ;
};
