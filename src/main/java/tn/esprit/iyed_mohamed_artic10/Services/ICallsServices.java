package tn.esprit.iyed_mohamed_artic10.Services;

import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

import java.util.List;

@Service
public interface ICallsServices {
    Calls addCalls (Calls c) ;
    Calls UpdateCalls (Calls c) ;
    Calls getById (Long c) ;
    List<Calls> getAll () ;
    void deleteCalls (Long Id);
}
