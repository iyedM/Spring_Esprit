package tn.esprit.iyed_mohamed_artic10.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long detailsId;
    private double budget;
    private String client;

    //@OneToOne(mappedBy = "details")
    //mappedby toujours dans le child
    // Projects projects;
}
