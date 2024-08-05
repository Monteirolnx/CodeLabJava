package Gatio.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/healthcheck")
public class HealthCheckController {

    private static final String BASE_URL = "https://localhost:7082/api/healthcheck";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<String> verificarSaude() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(BASE_URL, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Erro ao verificar saúde do sistema");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao conectar: " + ex.getMessage());
        }
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Endpoint de teste funcionando!");
    }
}
