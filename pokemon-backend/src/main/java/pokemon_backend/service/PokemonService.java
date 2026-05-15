package pokemon_backend.service;
import pokemon_backend.exception.PokemonNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pokemon_backend.cache.PokemonCache;
import pokemon_backend.dto.PokemonResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PokemonService {

    private final PokemonCache pokemonCache;

    public PokemonService(PokemonCache pokemonCache) {
        this.pokemonCache = pokemonCache;
    }

    public PokemonResponse getPokemon(String name) {

        String pokemonNameLower = name.toLowerCase();

        // CHECK CACHE
        if (pokemonCache.contains(pokemonNameLower)) {

            System.out.println("Data from CACHE");

            return pokemonCache.get(pokemonNameLower);
        }

        System.out.println("Data from PokeAPI");

        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonNameLower;

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> response;

        try {

            response = restTemplate.getForObject(url, Map.class);

        } catch (Exception e) {

            throw new PokemonNotFoundException(
                    "Pokemon not found: " + name
            );
        }

        // BASIC DETAILS
        String pokemonName = (String) response.get("name");

        int height = (Integer) response.get("height");

        int weight = (Integer) response.get("weight");

        // IMAGE
        Map<String, Object> sprites =
                (Map<String, Object>) response.get("sprites");

        String image =
                (String) sprites.get("front_default");

        // ABILITIES
        List<Map<String, Object>> abilitiesData =
                (List<Map<String, Object>>) response.get("abilities");

        List<String> abilities = new ArrayList<>();

        for (Map<String, Object> abilityItem : abilitiesData) {

            Map<String, Object> ability =
                    (Map<String, Object>) abilityItem.get("ability");

            abilities.add((String) ability.get("name"));
        }

        // TYPES
        List<Map<String, Object>> typesData =
                (List<Map<String, Object>>) response.get("types");

        List<String> types = new ArrayList<>();

        for (Map<String, Object> typeItem : typesData) {

            Map<String, Object> type =
                    (Map<String, Object>) typeItem.get("type");

            types.add((String) type.get("name"));
        }

        PokemonResponse pokemonResponse =
                new PokemonResponse(
                        pokemonName,
                        height,
                        weight,
                        image,
                        abilities,
                        types
                );

        // SAVE CACHE
        pokemonCache.put(pokemonNameLower, pokemonResponse);

        return pokemonResponse;
    }
}