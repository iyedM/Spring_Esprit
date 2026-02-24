package tn.esprit.iyed_mohamed_artic10.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.entities.AiSystems;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiSystemsServicesImp implements IAiSystemsServices{
    @Override
    public IAiSystemsServices addIaiSystems(AiSystems systems) {
        return null;
    }

    @Override
    public IAiSystemsServices UpdateIaiSystems(AiSystems systems) {
        return null;
    }

    @Override
    public IAiSystemsServices getById(Long systems) {
        return null;
    }

    @Override
    public List<IAiSystemsServices> getAll() {
        return List.of();
    }

    @Override
    public void deleteIaiSystems(Long systems) {

    }
}
