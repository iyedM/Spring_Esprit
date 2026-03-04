package tn.esprit.iyed_mohamed_artic10.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.iyed_mohamed_artic10.Enum.CallSkills;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
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
    @JsonIgnore
    Set<Calls> myCalls;

    @ManyToMany(mappedBy = "agents")
    @JsonIgnore
    Set<Projects> myProjects;
}
