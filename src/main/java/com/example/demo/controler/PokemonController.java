package com.example.demo.controler;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modele.Pokemon;
import com.example.demo.modele.Veterinaire;
import com.example.demo.repositori.PokemonRepository;
import com.example.demo.repositori.VeterinaireRepository;

import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/poke")
@AllArgsConstructor
public class PokemonController {
    


    private final PokemonRepository pokemonRepository;

    @PostMapping("/create")
    public Pokemon create(@RequestBody Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    

    @GetMapping("/read")
    public List<Pokemon> read(){
        return pokemonRepository.findAll();

    }

    @GetMapping("/read/{name}")
    public Pokemon readName(@PathVariable String name){
        return pokemonRepository.findByName(name);

    }


    @PutMapping("/update/{name}") //modifier
    public Pokemon update(@PathVariable String name,@RequestBody Pokemon pokemon){
        Pokemon updatePokemon = pokemonRepository.findByName(name);
                updatePokemon.setName(pokemon.getName());
        return pokemonRepository.save(updatePokemon);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        
         pokemonRepository.deleteById(id);

         return "pokemon supprimé";
    }


  
}
