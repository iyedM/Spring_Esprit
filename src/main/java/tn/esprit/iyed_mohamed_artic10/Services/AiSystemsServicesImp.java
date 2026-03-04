package tn.esprit.iyed_mohamed_artic10.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Repositories.IAiSystems;
import tn.esprit.iyed_mohamed_artic10.entities.AiSystems;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiSystemsServicesImp implements IAiSystemsServices {

    private final IAiSystems aiSystemsRepository;

    @Override
    public AiSystems addAiSystem(AiSystems system) {
        return aiSystemsRepository.save(system);
    }

    @Override
    public AiSystems updateAiSystem(AiSystems system) {
        return aiSystemsRepository.save(system);
    }

    @Override
    public AiSystems getById(Long id) {
        return aiSystemsRepository.findById(id).orElse(null);
    }

    @Override
    public List<AiSystems> getAll() {
        return aiSystemsRepository.findAll();
    }

    @Override
    public void deleteAiSystem(Long id) {
        aiSystemsRepository.deleteById(id);
    }
}
