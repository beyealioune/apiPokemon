package com.example.demo.repositori;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modele.Veterinaire;

public interface VeterinaireRepository extends JpaRepository<Veterinaire, Long> {

    Optional<Veterinaire> findByName(String name);
}
