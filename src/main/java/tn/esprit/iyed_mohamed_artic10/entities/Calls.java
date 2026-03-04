package tn.esprit.iyed_mohamed_artic10.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.iyed_mohamed_artic10.Enum.CallSkills;
import tn.esprit.iyed_mohamed_artic10.Enum.CallStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long callsId ;

    private LocalDateTime callDateTime;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private CallStatus callStatus;

    @Enumerated(EnumType.STRING)

    @ElementCollection
    private Set<CallSkills> requiredSkills;

    @ManyToOne
    Agents assignedAgent;

    @ManyToOne
    AiSystems assignedAiSystem;


//    @PrePersist
//    protected void onCreate() {
//        this.callDateTime = LocalDateTime.now();
//    }
}
