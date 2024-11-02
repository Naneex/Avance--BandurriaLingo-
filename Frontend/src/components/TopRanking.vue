<template>
  <div class="top-container">
    <div class="top-2" v-if="usuarios.length >= 2">
      <img :src="usuarios[1].imagen" alt="Foto de Perfil" />
      <p>{{ usuarios[1].usuario }}</p>
      <div class="top-2-info">
        <img src="../assets/Imagenes/segundolugar.png" alt="Segundo Lugar" />
        <p>Nivel</p>
        <h2>{{ usuarios[1].nivel }}</h2>
      </div>
    </div>

    <div class="top-1" v-if="usuarios.length >= 1">
      <img :src="usuarios[0].imagen" alt="Foto de Perfil" />
      <p>{{ usuarios[0].usuario }}</p>
      <div class="top-1-info">
        <img src="../assets/Imagenes/primerlugar.png" alt="Primer Lugar" />
        <p>Nivel</p>
        <h2>{{ usuarios[0].nivel }}</h2>
      </div>
    </div>

    <div class="top-3" v-if="usuarios.length >= 3">
      <img :src="usuarios[2].imagen" alt="Foto de Perfil" />
      <p>{{ usuarios[2].usuario }}</p>
      <div class="top-3-info">
        <img src="../assets/Imagenes/tercerlugar.png" alt="Tercer Lugar" />
        <p>Nivel</p>
        <h2>{{ usuarios[2].nivel }}</h2>
    </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'TopRanking',
  data() {
    return {
      usuarios: [],
    };
  },
  mounted() {
    this.fetchTopUsuarios();
  },
  methods: {
    async fetchTopUsuarios() {
    try {
      const response = await axios.get('http://localhost:3000/usuario?_sort=nivel&_order=desc');
      console.log(response.data); 
      this.usuarios = response.data.sort((a, b) => b.nivel - a.nivel).slice(0, 3);
    } catch (error) {
      console.error('Error error:', error);
    }
  }
  },
};
</script>

<style scoped>
.top-container {
  display: flex;
  position: relative;
  text-align: center;
}

.top-2,
.top-1,
.top-3 {
  background-color: white;
  border-radius: 1vw;
  padding: 1vh;
  box-shadow: 0.5vw 0.5vw 1vh rgba(0, 0, 0, 0.1);
  width: 15vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.top-2 {
  height: 60vh;
  margin-left: 3vw;
  margin-top: 30vh;
}

.top-1 {
  height: 75vh;
  margin-left: 2vw;
  margin-top: 15vh;
}

.top-3 {
  height: 60vh;
  margin-left: 2vw;
  margin-top: 30vh;
}

.top-1 img,
.top-2 img,
.top-3 img {
  width: 16vh;
  height: 16vh;
  border-radius: 1vw;
  background-color: #ffffff;
  outline: 0.1vw solid black;
}

.top-1 p,
.top-2 p,
.top-3 p {
  font-size: 3vh;
  margin-top: 3vh;
}

.top-1-info,
.top-2-info,
.top-3-info {
  display: flex;
  flex-direction: column;
  background-color: #A3BAD5;
  width: 90%;
  color: white;
  height: 60%;
  border-radius: 1vw;
  justify-content: center;
  align-items: center;
  margin-top: 5vh;
}

.top-1-info img,
.top-2-info img,
.top-3-info img {
  width: 30%;
  height: 30%;
  background-color: #A3BAD4;
  outline: none;
}

.top-1-info p,
.top-2-info p,
.top-3-info p {
  margin-top: 3%;
  font-size: 3vh;
}

.top-1-info h2,
.top-2-info h2,
.top-3-info h2 {
  font-size: 8vh;
  margin-top: 1vh;
}
</style>
