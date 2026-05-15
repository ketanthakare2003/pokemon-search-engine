package pokemon_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pokemon_backend.dto.PokemonResponse;
import pokemon_backend.service.PokemonService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/")
    public String home() {
        return "Pokemon Backend Running Successfully!";
    }

    @GetMapping("/pokemon/{name}")
    public PokemonResponse getPokemon(@PathVariable String name) {
        return pokemonService.getPokemon(name);
    }
}