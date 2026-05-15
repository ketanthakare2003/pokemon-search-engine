package pokemon_backend.cache;

import org.springframework.stereotype.Component;
import pokemon_backend.dto.PokemonResponse;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class PokemonCache {

    private final ConcurrentHashMap<String, PokemonResponse> cache =
            new ConcurrentHashMap<>();

    public PokemonResponse get(String name) {
        return cache.get(name);
    }

    public void put(String name, PokemonResponse response) {
        cache.put(name, response);
    }

    public boolean contains(String name) {
        return cache.containsKey(name);
    }
}