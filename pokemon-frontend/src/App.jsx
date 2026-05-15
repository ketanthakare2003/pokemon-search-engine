import { useState } from "react";
import axios from "axios";

function App() {

  const [pokemonName, setPokemonName] = useState("");
  const [pokemonData, setPokemonData] = useState(null);
  const [error, setError] = useState("");

  const searchPokemon = async () => {

    if (!pokemonName) return;

    try {

      setError("");

      const response = await axios.get(
        `http://127.0.0.1:8080/pokemon/${pokemonName.toLowerCase()}`
      );

      setPokemonData(response.data);

    } catch (err) {

      setPokemonData(null);

      setError("Pokemon not found");
    }
  };

  return (

    <div style={styles.container}>

      <h1 style={styles.heading}>
        Pokemon Search Engine
      </h1>

      <div style={styles.searchContainer}>

        <input
          type="text"
          placeholder="Enter Pokemon Name"
          value={pokemonName}
          onChange={(e) => setPokemonName(e.target.value)}
          style={styles.input}
        />

        <button
          onClick={searchPokemon}
          style={styles.button}
        >
          Search
        </button>

      </div>

      {error && (
        <p style={styles.error}>
          {error}
        </p>
      )}

      {pokemonData && (

        <div style={styles.card}>

          <img
            src={pokemonData.image}
            alt={pokemonData.name}
            style={styles.image}
          />

          <h2 style={styles.name}>
            {pokemonData.name.toUpperCase()}
          </h2>

          <div style={styles.infoBox}>
            <p><strong>Height:</strong> {pokemonData.height}</p>
            <p><strong>Weight:</strong> {pokemonData.weight}</p>
          </div>

          <div>

            <h3>Abilities</h3>

            <div style={styles.tagContainer}>
              {pokemonData.abilities.map((ability, index) => (
                <span key={index} style={styles.tag}>
                  {ability}
                </span>
              ))}
            </div>

          </div>

          <div>

            <h3>Types</h3>

            <div style={styles.tagContainer}>
              {pokemonData.types.map((type, index) => (
                <span key={index} style={styles.typeTag}>
                  {type}
                </span>
              ))}
            </div>

          </div>

        </div>
      )}

    </div>
  );
}

const styles = {

  container: {
    minHeight: "100vh",
    background: "#f4f6f9",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    paddingTop: "50px",
    fontFamily: "Arial"
  },

  heading: {
    marginBottom: "30px",
    color: "#333"
  },

  searchContainer: {
    display: "flex",
    gap: "10px",
    marginBottom: "30px"
  },

  input: {
    padding: "12px",
    width: "250px",
    borderRadius: "8px",
    border: "1px solid #ccc",
    fontSize: "16px",
    outline: "none"
  },

  button: {
    padding: "12px 20px",
    border: "none",
    borderRadius: "8px",
    backgroundColor: "#007bff",
    color: "white",
    cursor: "pointer",
    fontSize: "16px"
  },

  card: {
    width: "320px",
    background: "white",
    borderRadius: "15px",
    padding: "25px",
    boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
    textAlign: "center"
  },

  image: {
    width: "150px"
  },

  name: {
    color: "#222"
  },

  infoBox: {
    marginBottom: "20px"
  },

  tagContainer: {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "center",
    gap: "10px",
    marginBottom: "15px"
  },

  tag: {
    background: "#e3f2fd",
    padding: "8px 12px",
    borderRadius: "20px",
    fontSize: "14px"
  },

  typeTag: {
    background: "#ffe082",
    padding: "8px 12px",
    borderRadius: "20px",
    fontSize: "14px"
  },

  error: {
    color: "red",
    fontWeight: "bold"
  }
};

export default App;