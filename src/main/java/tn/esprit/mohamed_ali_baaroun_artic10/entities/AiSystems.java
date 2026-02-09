package tn.esprit.mohamed_ali_baaroun_artic10.entities;

import jakarta.persistence.*;
import tn.esprit.mohamed_ali_baaroun_artic10.Enum.CallSkills;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
public class AiSystems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aiSystemId;
    private String type;
    private Boolean available;
    private Set<CallSkills> skills ;

    @OneToMany(mappedBy = "assignedAiSystem")
    private Collection<Calls> calls = new ArrayList<>();
}
