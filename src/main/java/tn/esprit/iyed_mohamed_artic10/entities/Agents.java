package tn.esprit.iyed_mohamed_artic10.entities;

import jakarta.persistence.*;
import tn.esprit.iyed_mohamed_artic10.Enum.CallSkills;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Agents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long agentsId;
    private String name;
    @ElementCollection
    @Enumerated(EnumType.STRING)

    private Set<CallSkills> skills;
    private boolean available;
    //unidirectionnele genere  une table associative
    @OneToMany(mappedBy = "assignedAgent")

    Set<Calls> myCalls;

    @ManyToMany(mappedBy = "agents")
    Set<Projects> myProjects;
}
