package tn.esprit.iyed_mohamed_artic10.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectsid;

    private String libelle;

    private Date startDate;
    private Date endDate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    ProjectDetails details;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Agents> agents;
}
