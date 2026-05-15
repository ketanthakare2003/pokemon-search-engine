package pokemon_backend.dto;

import java.util.List;

public class PokemonResponse {

    private String name;

    private int height;

    private int weight;

    private String image;

    private List<String> abilities;

    private List<String> types;

    public PokemonResponse() {
    }

    public PokemonResponse(String name,
                           int height,
                           int weight,
                           String image,
                           List<String> abilities,
                           List<String> types) {

        this.name = name;
        this.height = height;
        this.weight = weight;
        this.image = image;
        this.abilities = abilities;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}