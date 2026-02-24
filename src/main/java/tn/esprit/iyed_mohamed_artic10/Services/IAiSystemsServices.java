package tn.esprit.iyed_mohamed_artic10.Services;

import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.entities.AiSystems;

import java.util.List;

@Service
public interface IAiSystemsServices {
    IAiSystemsServices addIaiSystems (AiSystems systems) ;
    IAiSystemsServices UpdateIaiSystems (AiSystems systems) ;
    IAiSystemsServices getById (Long systems) ;
    List<IAiSystemsServices> getAll () ;
    void deleteIaiSystems (Long systems);
}
