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
      mensajeCompletado: ""
    };
  },
  mounted() {
    this.obtenerSetsDePalabras(); 
  },
  methods: {
    async obtenerSetsDePalabras() {
      try {
        const response = await fetch('http://localhost:3000/parespalabras');
        const data = await response.json();
        this.paresPalabras = data;  
        this.shuffleWords(); 
      } catch (error) {
        console.error('Error al obtener las palabras:', error);
      }
    },

    shuffleWords() {
      if (this.paresPalabras.length === 0) return; 

      
      const randomSet = this.paresPalabras[Math.floor(Math.random() * this.paresPalabras.length)];
      this.shuf_palabras = randomSet.palabras
        .map(pair => [pair.es, pair.en]) 
        .flat()  
        .sort(() => Math.random() - 0.5);
      this.crearTraducciones(randomSet.palabras);
    },

    crearTraducciones(palabras) {
      this.traducciones = {};
      palabras.forEach(p => {
        this.traducciones[p.es] = p.en;
        this.traducciones[p.en] = p.es;
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

    completeGame() {
      const usuario = JSON.parse(localStorage.getItem('usuarioLogueado'));
      if (usuario) {
        usuario.xp += 10;  
        if (usuario.xp >= 30) {  
          usuario.nivel += 1; 
          usuario.xp = 0;  
        }
        this.updateUserInServer(usuario);
        this.mensajeCompletado = `Juego completado! +10 EXP, eres nivel ${usuario.nivel} con ${usuario.xp} EXP, te faltan ${30 - usuario.xp} de exp para subir al nivel ${usuario.nivel + 1}`;
      }
    },

    async updateUserInServer(usuario) {
      try {
        const response = await fetch(`http://localhost:3000/usuario/${usuario.id}`, {
          method: 'PATCH',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            xp: usuario.xp,
            nivel: usuario.nivel,
          }),
        });

        if (response.ok) {
          console.log('Datos actualizados correctamente en el servidor');
          localStorage.setItem('usuario', JSON.stringify(usuario));
        } 
      } catch (error) {
        console.error('Error de red al intentar actualizar el usuario:', error);
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
