package br.com.desafio.Wayon.code.plant.management;


import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private PlantService service;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PlantDTO dto) {
        return ResponseEntity.ok(service.creatPlant(dto));
    }
    @PutMapping("/{code}")
    public ResponseEntity<?> update(@PathVariable Long code, @RequestBody @Valid PlantDTO dto) {
        return ResponseEntity.ok(service.updatePlant(code, dto));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> delete(@PathVariable Long code, Authentication authentication) throws AccessDeniedException {
        User user = customUserDetailsService.getAuthenticatedUser();

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            throw new AccessDeniedException("Apenas administradores podem excluir.");
        }

        service.deletePlant(code, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String term) {
        return ResponseEntity.ok(service.search(term));
    }

}
