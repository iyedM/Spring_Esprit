package tn.esprit.iyed_mohamed_artic10.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.iyed_mohamed_artic10.Services.ICallsServices;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

import java.util.List;

@RestController
@RequestMapping("/api/calls")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CallsRestController {

    private final ICallsServices callsServices;

    @PostMapping
    public ResponseEntity<Calls> addCall(@RequestBody Calls call) {
        Calls savedCall = callsServices.addCalls(call);
        return new ResponseEntity<>(savedCall, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Calls> updateCall(@RequestBody Calls call) {
        Calls updatedCall = callsServices.UpdateCalls(call);
        return ResponseEntity.ok(updatedCall);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calls> getCallById(@PathVariable Long id) {
        Calls call = callsServices.getById(id);
        return ResponseEntity.ok(call);
    }

    @GetMapping
    public ResponseEntity<List<Calls>> getAllCalls() {
        List<Calls> calls = callsServices.getAll();
        return ResponseEntity.ok(calls);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCall(@PathVariable Long id) {
        callsServices.deleteCalls(id);
        return ResponseEntity.noContent().build();
    }
}

