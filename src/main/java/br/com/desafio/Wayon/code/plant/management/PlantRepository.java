package br.com.desafio.Wayon.code.plant.management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    boolean existsByCode(Long code);
}
