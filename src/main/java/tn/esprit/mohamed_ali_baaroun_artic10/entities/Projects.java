package tn.esprit.mohamed_ali_baaroun_artic10.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectsId ;
    private String libelle;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToMany(mappedBy = "projects")
    private Set<Agents> Agents = new HashSet<>();
    @OneToOne
    private ProjectDetails projectDetails ;
}
