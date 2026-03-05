package tn.esprit.iyed_mohamed_artic10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HumanAgentRequiredResponse {
    private Long callId;
    private boolean requiresHumanAgent;
    private String message;
}
