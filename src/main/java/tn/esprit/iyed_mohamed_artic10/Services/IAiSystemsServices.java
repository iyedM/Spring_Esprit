package tn.esprit.iyed_mohamed_artic10.Services;

import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.entities.AiSystems;

import java.util.List;

public interface IAiSystemsServices {
    AiSystems addAiSystem(AiSystems system);
    AiSystems updateAiSystem(AiSystems system);
    AiSystems getById(Long id);
    List<AiSystems> getAll();
    void deleteAiSystem(Long id);
}
