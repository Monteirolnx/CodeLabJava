package Gatio.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import Gatio.dtos.ComandaDto;
import Gatio.models.Comanda;

@RestController
@RequestMapping("/api/comanda")
public class ComandaController {
    private static final String BASE_URL = "https://localhost:7082/api/comanda";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/abrir")
    public ResponseEntity<Comanda> abrirComanda(@RequestBody ComandaDto comandaDto) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<ComandaDto> request = new HttpEntity<>(comandaDto, headers);
            ResponseEntity<Comanda> response = restTemplate.postForEntity(BASE_URL + "/abrir", request, Comanda.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                throw new IllegalArgumentException("A comanda com o número " + comandaDto.getNumero() + " já está aberta.");
            }
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Endpoint de teste funcionando!");
    }
}
