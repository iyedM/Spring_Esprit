package tn.esprit.mohamed_ali_baaroun_artic10.entities;

import jakarta.persistence.*;
import tn.esprit.mohamed_ali_baaroun_artic10.Enum.CallSkills;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Agents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long agentsId;
    private String name ;
    private Set<CallSkills>  skills ;
    private Boolean available;
    @OneToMany(mappedBy = "Calls")
    private Collection<Calls> Calls = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "agent_projects",
            joinColumns = @JoinColumn(name = "agent_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Projects> projects = new HashSet<>();
}
