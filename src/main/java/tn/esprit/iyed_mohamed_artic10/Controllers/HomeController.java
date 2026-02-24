package tn.esprit.iyed_mohamed_artic10.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bienvenue sur l'API Arctic10 - Iyed Mohamed");
        response.put("status", "Application is running successfully!");
        response.put("endpoints", Map.of(
                "calls", "/api/calls",
                "projects", "/api/projects"
        ));
        return response;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Application is healthy");
        return response;
    }
}

