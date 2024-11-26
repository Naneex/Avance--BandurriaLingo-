<template>
  <HeaderComponent nombre_pag="Encuentra los pares" />
  <div class="pares-body">
    <div class="pares-container">
      <div class="game">
        <ItemPares
          v-for="(word, index) in shuf_palabras"
          :key="index"
          :word="word"
          @itemClicked="handleItemClick"
          :isOpen="isOpen(word)"
        />
      </div>
      <button class="reset" @click="resetGame">Reiniciar</button>
      <div v-if="mensajeCompletado" class="mensaje-completado">
        {{ mensajeCompletado }}
      </div>
    </div>
  </div>
</template>

<script>
import ItemPares from './ItemPares.vue';
import HeaderComponent from './HeaderComponent.vue';
import axios from 'axios';


export default {
  components: {
    ItemPares,
    HeaderComponent,
  },
  data() {
    return {
      paresPalabras: [],   
      shuf_palabras: [],   
      openedWords: [], 
      openedPair: [],  
      lockBoard: false, 
      traducciones: {}, 
      mensajeCompletado: "",
      usuarioLogueado: {},
    };
  },
  mounted() {
    this.obtenerSetsDePalabras(); 
  },
  methods: {
    async obtenerSetsDePalabras() {
      try {
        const response = await fetch('http://localhost:8080/parespalabras', {
          headers: {
            'Authorization': `Bearer ${localStorage.getItem('token')}`, 
            'Content-Type': 'application/json'
          }
        });

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        this.paresPalabras = data;  
        this.shuffleWords(); 
      } catch (error) {
        console.error('Error al obtener las palabras:', error);
      }
    },

    shuffleWords() {
      console.log("paresPalabras:", this.paresPalabras);

      if (this.paresPalabras.length === 0) {
        console.error("No hay datos en paresPalabras");
        return;
      }

      const randomSet = this.paresPalabras[Math.floor(Math.random() * this.paresPalabras.length)];
      console.log("randomSet seleccionado:", randomSet);

      if (!randomSet || !randomSet.palabras) {
        console.error("El conjunto seleccionado no tiene palabras o está mal formado:", randomSet);
        return;
      }

      this.shuf_palabras = randomSet.palabras
        .map(pair => [pair.palabraEs, pair.palabraEn]) 
        .flat() 
        .sort(() => Math.random() - 0.5); 

      console.log("shuf_palabras:", this.shuf_palabras);

      this.crearTraducciones(randomSet.palabras);
    },

    crearTraducciones(palabras) {
      console.log("Creando traducciones con:", palabras);

      this.traducciones = {};
      palabras.forEach(p => {
        if (p.palabraEs && p.palabraEn) {
          this.traducciones[p.palabraEs] = p.palabraEn;
          this.traducciones[p.palabraEn] = p.palabraEs;
        } else {
          console.error("Par de palabras inválido:", p);
        }
      });
    },

    handleItemClick(word) {
      if (this.lockBoard) return;
      if (!this.openedWords.includes(word) && this.openedPair.length < 2) {
        this.openedPair.push(word);
        this.openedWords.push(word);
        
        if (this.openedPair.length === 2) {
          this.checkForMatch();
        }

        if (this.openedWords.length === this.shuf_palabras.length) {
          this.completeGame();
        }
      }
    },

    checkForMatch() {
      const [firstWord, secondWord] = this.openedPair;

      if (!this.isMatch(firstWord, secondWord)) {
        this.lockBoard = true;
        setTimeout(() => {
          this.openedWords = this.openedWords.filter(word => !this.openedPair.includes(word));
          this.openedPair = [];
          this.lockBoard = false;
        }, 1000);
      } else {
        this.openedPair = [];
      }
    },

    isMatch(word1, word2) {
      return this.traducciones[word1] === word2;
    },

    isOpen(word) {
      return this.openedWords.includes(word);
    },

    resetGame() {
      this.openedWords = [];
      this.openedPair = [];
      this.lockBoard = false;
      this.shuffleWords();
      this.mensajeCompletado = "";
    },

    async completeGame() {
      const response = await axios.get("http://localhost:8080/profile/me", {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
        });
      this.usuarioLogueado = response.data;


      if (this.usuarioLogueado) {
        this.usuarioLogueado.exp += 10;  
        if (this.usuarioLogueado.exp >= 30) {  
          this.usuarioLogueado.nivel += 1; 
          this.usuarioLogueado.exp = 0;  
        }
        this.updateUserInServer(this.usuarioLogueado);
        this.mensajeCompletado = `Juego completado! +10 EXP, eres nivel ${this.usuarioLogueado.nivel} con ${this.usuarioLogueado.exp} EXP, te faltan ${30 - this.usuarioLogueado.exp} de exp para subir al nivel ${this.usuarioLogueado.nivel + 1}`;
      }
    },

    async updateUserInServer(usuario) {
      try {
        const token = localStorage.getItem("token");

        if (!token) {
          console.error("No se encontró el token en localStorage.");
          return;
        }

       
        const response = await fetch(`http://localhost:8080/usuario/${this.usuarioLogueado.id}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("token")}`, 
          },
          body: JSON.stringify({
            exp: this.usuarioLogueado.exp,
            nivel: this.usuarioLogueado.nivel,
          }),
        });

        if (response.ok) {
          console.log("Datos actualizados correctamente en el servidor");
          localStorage.setItem("usuario", JSON.stringify(this.usuarioLogueado)); 
        } else {
          console.error(
            "Error al actualizar los datos en el servidor:",
            response.status
          );
        }
      } catch (error) {
        console.error("Error de red al intentar actualizar el usuario:", error);
      }
    }
  },
};
</script>

<style scoped>
.pares-body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #D9E2EC;
  width: 100vw;
}

.pares-container {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 30px;
  background-color: #D9E2EC;
  padding: 40px 60px;
}

.pares-body h2 {
  font-size: 2em;
  color: #000000;
  text-transform: uppercase;
  letter-spacing: 0.1em;
}

.reset {
  padding: 5px 10px;
  color: #2b64a5;
  background-color: #ffffff;
  border: none;
  font-size: 1.5em;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  cursor: pointer;
  font-weight: 600;
}

.reset:focus {
  color: #ffffff;
  background-color: #2b64a5;
}

.game {
  display: grid;
  grid-template-columns: repeat(6, 1fr); 
  grid-template-rows: repeat(3, 1fr); 
  gap: 4vh;
  justify-items: center; 
  align-items: center; 
}

.mensaje-completado {
  font-size: 1.5em;
  color: #000000;
  font-weight: bold;
  text-align: center;
  animation: fadeIn 1s ease-in-out;
}

</style>
