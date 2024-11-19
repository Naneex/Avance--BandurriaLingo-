<template>
  <div class="container-ranking">
    <div class="ranking-header">
      <p id="ranking-rank">Rank</p>
      <p id="ranking-foto">Foto</p>
      <p id="ranking-nombre">Nombre</p>
      <p id="ranking-nivel">Nivel</p>
    </div>

    <hr class="container-ranking-linea" />

    <div v-for="(usuario, index) in filteredUsuarios" :key="usuario.id" class="ranking-item">
      <p class="ranking-numero">{{ index + 4 }}</p>
      <div class="ranking-foto">
        <img :src="usuario.imagen" alt="Foto de Perfil" />
      </div>
      <p class="ranking-nombre">{{ usuario.usuario }}</p>
      <p class="ranking-nivel">{{ usuario.nivel }}</p>
    </div>

    <hr class="container-ranking-linea" />
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'RankingList',
  data() {
    return {
      usuarios: [],
    };
  },
  computed: {
    filteredUsuarios() {
    return this.usuarios
      .sort((a, b) => b.nivel - a.nivel)
      .slice(3, 10);
  }
  },
  mounted() {
    this.fetchUsuarios();
  },
  methods: {
    async fetchUsuarios() {
      try {
        const token = localStorage.getItem('token'); 
        const response = await axios.get('http://localhost:8080/ranking/list', {
          headers: {
            Authorization: `Bearer ${token}`, 
          },
        });
        this.usuarios = response.data;
      } catch (error) {
        console.error('Error al obtener el top ranking:', error);
      }
    },
  },
};
</script>

<style scoped>
.container-ranking {
  background-color: #FFFFFF;
  border-radius: 1vw;
  text-align: center;
  margin-top: 20vh;
  height: 68vh;
  width: 40vw;
  margin-left: 7vw;
  box-shadow: 1vw 1vw 1vw rgba(0, 0, 0, 0.3);
  display: block;
  padding-bottom: 1vh;
  overflow-y: auto; 
}

.ranking-header {
  display: flex;
  justify-content: space-around;
  padding: 2vh;
  font-weight: bold;
}

.ranking-item {
  display: flex;
  align-items: center;
  padding: 0.3vh;
  font-size: 2.7vh;
  margin-top: 1.5vh;
}

.ranking-item img {
  width: 6vh;
  height: 6vh;
  border-radius: 50%;
}

.ranking-numero {
  margin-left: 7%;
  width: 10%;
}

.ranking-foto {
  margin-left: 14.3%;
  width: 10%;
}

.ranking-nombre {
  width: 40%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.ranking-nivel {
  margin-left: 1%;
  width: 10%;
}

.ranking-header p {
  font-size: 3vh;
}

.container-ranking-linea {
  border: none;
  border-top: 0.1vh solid #000000;
  width: 95%;
  margin-left: 2.5%;
}
</style>
