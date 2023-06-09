package com.example.demo.repositori;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modele.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    Pokemon findByName(String name);
    // Pokemon findById(Long id);

    
    List<Pokemon> findByNameContaining(String name);




   
}
