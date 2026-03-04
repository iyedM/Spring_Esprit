package tn.esprit.iyed_mohamed_artic10.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.esprit.iyed_mohamed_artic10.Enum.CallSkills;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AiSystems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aiSystemsId;
    private String type;
    private boolean available;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<CallSkills> skills;

    @OneToMany(mappedBy = "assignedAiSystem")
    @JsonIgnore
    Set<Calls> myCalls;
}
