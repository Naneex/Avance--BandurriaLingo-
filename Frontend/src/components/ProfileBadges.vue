<template>
  <div class="badge-container">
    
    <img v-if="usuarioPerfil.idiomaDominado === 'Español'" src="../assets/Imagenes/banderaespaña.png" alt="Bandera de España" />
    <img v-else-if="usuarioPerfil.idiomaDominado === 'Inglés'" src="../assets/Imagenes/USA.png" alt="Bandera de Estados Unidos" />
    <br />

    <div class="fire-badge" v-if="usuarioPerfil.racha_diaria > 0">
      <img v-if="usuarioPerfil.racha_diaria >= 1" src="../assets/Imagenes/Fuego.png" alt="" />
      <span class="racha-number">{{ usuarioPerfil.racha_diaria }}</span>
    </div>

    <div class="fire-badge" v-else>
      <img class="apagado" src="../assets/Imagenes/FuegoApagado.png" alt="" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  props: {
    idiomaDominado: String,
    
  },
    data() {
        return {
        usuarioPerfil: {},
        };
    },
    created() {
      this.obtenerPerfilUsuario();
    },
    

  methods: {
    async obtenerPerfilUsuario() {
      const id = this.$route.params.id; 
      try {
        const response = await axios.get(`http://localhost:8080/profile/${id}`, {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
        });
        this.usuarioPerfil = response.data; 
        console.log("Perfil del usuario:", this.usuarioPerfil);
      } catch (error) {
        console.error("Error al obtener el perfil del usuario:", error.response || error);
        alert("No se pudo obtener el perfil del usuario.");
      }
    }
}
}

</script>

<style scoped>

.body{
    margin: 0;
    padding: 0;
    height: 100%;
    width: 100%;
    background-color: #D9E2EC;
    font-family: 'Roboto', sans-serif;
    color: black;
}

.badge-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

}

.badge-container img {
  width: 100px;
  height: 100px;
  margin-left: -35px;
}

.fire-badge {
  position: relative;
  display: inline-block;
    margin-top: 20px;

}

.fire-badge img {
  width: 100px;
  height: 100px;
  margin-left: -15px;
}

.racha-number {
  position: absolute;
  top: 75%;
  left: 33%;
  transform: translate(-50%, -50%);
  font-size: 1.5rem;
  color: rgb(255, 255, 255);
  font-weight: bold;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.fire-badge img.apagado {
  width: 200px;
  height: 200px;
}
</style>
