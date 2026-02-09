package tn.esprit.mohamed_ali_baaroun_artic10.entities;

import jakarta.persistence.*;
import tn.esprit.mohamed_ali_baaroun_artic10.Enum.CallSkills;
import tn.esprit.mohamed_ali_baaroun_artic10.Enum.CallStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Calls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long callsId;
    private String phoneNumber;
    private LocalDateTime callsDateTime;
    @Enumerated(EnumType.STRING)
    private CallStatus status;
    @Enumerated(EnumType.STRING)
    // for new update
    //@ElementCollection
    private Set<CallSkills> requiredSkills;

    @ManyToOne
    @JoinColumn(name = "ai_system_id", nullable = false)
    private AiSystems aiSystems;

    @ManyToOne
    @JoinColumn(name = "Agents", nullable = false)
    private Agents Agents ;
}
