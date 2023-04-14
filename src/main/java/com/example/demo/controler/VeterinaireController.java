package com.example.demo.controler;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modele.Pokemon;
import com.example.demo.modele.Veterinaire;
import com.example.demo.repositori.PokemonRepository;
import com.example.demo.repositori.VeterinaireRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/veto")
@AllArgsConstructor
public class VeterinaireController {
    
    @Autowired
    private final VeterinaireRepository veterinaireRepository;
    @Autowired
    private final PokemonRepository pokemonRepository;

    @PostMapping("/create")
    public Veterinaire create(@RequestBody Veterinaire veterinaire){
        return veterinaireRepository.save(veterinaire);
    }


    @GetMapping("/read")
    public List<Veterinaire> read(){
        return veterinaireRepository.findAll();
    }

    @DeleteMapping("/{veterinaire}/pokemon/{nom}")
public ResponseEntity<String> supprimerPokemon(@PathVariable Long veterinaire, @PathVariable String nom) {
    List<Pokemon> pokemons = pokemonRepository.findByNameContaining(nom); // chercher les Pokémons par nom dans la HashMap
    if (pokemons != null && !pokemons.isEmpty()) {
        Pokemon pokemonASupprimer = null;
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getVeterinaire().getId().equals(veterinaire)) { // vérifier que le vétérinaire est le propriétaire du Pokémon
                pokemonASupprimer = pokemon;
                break;
            }
        }
        if (pokemonASupprimer != null) {
            pokemonRepository.delete(pokemonASupprimer); // supprimer le Pokémon
            pokemons.remove(pokemonASupprimer);
            return ResponseEntity.ok("Le Pokemon " + nom + " a été supprimé avec succès.");
        }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le Pokemon " + nom + " n'a pas été trouvé.");
}




@DeleteMapping("/veterinaire/{nom}")
public ResponseEntity<String> supprimerVeto(@PathVariable String nom) {
    Optional<Veterinaire> veterinaire = veterinaireRepository.findByName(nom); // 
    if (veterinaire.isPresent() == false) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (veterinaire.isPresent() == true) {
            Veterinaire veto = veterinaire.get();// récupére le veterinaire optional 
            List<Pokemon> pokemons = veto.getPokemons();  //récupére la liste de ses pokémons 
            for ( Pokemon pokemon : pokemons){
                pokemon.setVeterinaire(null);
                pokemonRepository.save(pokemon);
            }

            veterinaireRepository.delete(veto);
        }
    
    return ResponseEntity.status(HttpStatus.OK).body("Le veterinaire " + nom + " à été delete.");
}


}
