package tn.esprit.iyed_mohamed_artic10.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.esprit.iyed_mohamed_artic10.Repositories.IAgents;
import tn.esprit.iyed_mohamed_artic10.Repositories.ICallsRepository;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CallsServicesImp implements ICallsServices{

    private final ICallsRepository callsRepository;

    @Override
    public Calls addCalls(Calls call) {
        return callsRepository.save(call);
    }

    @Override
    public Calls UpdateCalls(Calls call) {
        return callsRepository.save(call);
    }

    @Override
    public Calls getById(Long call) {
//        return callsRepository.findById(call).orElse(null);
        return callsRepository.findById(call).orElseThrow(() -> new EntityNotFoundException("not found"));
    }

    @Override
    public List<Calls> getAll() {
        return callsRepository.findAll();
    }

    @Override
    public void deleteCalls(Long Id) {
        callsRepository.deleteById(Id);
    }
}
