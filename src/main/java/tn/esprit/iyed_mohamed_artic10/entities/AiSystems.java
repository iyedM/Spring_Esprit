package tn.esprit.iyed_mohamed_artic10.entities;

import jakarta.persistence.*;
import tn.esprit.iyed_mohamed_artic10.Enum.CallSkills;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
public class AiSystems {
    @Id
    private long aiSystemsId;
    private String type;
    private boolean available ;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<CallSkills> skills;

    @OneToMany(mappedBy = "assignedAiSystem")
    Set<Calls> myCalls;
}
