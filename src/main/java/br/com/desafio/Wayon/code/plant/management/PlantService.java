package br.com.desafio.Wayon.code.plant.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

public class PlantService {

    @Autowired
    private PlantRepository repository;

    public Plant creatPlant(PlantDTO dto) {
        if (repository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Codigo já existente");
        }

        Plant plant = new Plant();
        plant.setId(dto.getCode());
        plant.setDescription(dto.getDescription());
        return repository.save(plant);
    }

    public Plant updatePlant(Long code, PlantDTO dto) {
        Plant plant = repository.findById(dto.getCode())
                .orElseThrow(() -> new RuntimeException("Planta não encontrada"));

        plant.setDescription(dto.getDescription());
        return repository.save(plant);
    }


    public void deletePlant(Long code, User user) throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        if (!isAdmin) {
            throw new AccessDeniedException("Apenas administradores podem excluir.");
        }

        System.out.println("Usuário " + username + " excluiu planta com código " + code);
        repository.deleteById(code);
    }

    public List<Plant> search(String term) {
        return repository.findAll().stream()
                .filter(p -> p.getId().toString().contains(term) ||
                        (p.getDescription() != null && p.getDescription().contains(term)))
                .collect(Collectors.toList());
    }
}
