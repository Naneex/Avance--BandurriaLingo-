<template>
  <div class="profile-info-container">
    <div class="left-section">
      <div class="imagen-perfil">
        <img :src="usuarioPerfil.imagen" alt="Foto de Perfil" />
      </div>
      <div class="info-header">
        <button v-if="usuarioPerfil.id === usuarioLogueado.id" class="edit-profile" @click="editarPerfil" >
          {{ modoEdicion = 'Guardar Cambios' }}
        </button>
        <br>
        <button v-if="usuarioPerfil.id === usuarioLogueado.id" class="edit-profile" @click="cancelarCambios" >
          {{ modoEdicion = 'Cancelar Cambios' }}
        </button>
        <input v-if="modoEdicion" type="text" v-model="usuarioPerfil.imagen" placeholder="Pega el enlace de la imagen" class="profile-url">
        <hr class="linea-bajo-perfil" />
      </div>
      <div class="badges">
        <ProfileBadges :idiomaDominado="usuarioPerfil.idiomaDominado" />
      </div>
    </div>
    <div class="info">  
      <div class="informacion">
        <p class="lower">Nombre</p>
        <input v-model="usuarioPerfil.usuario" class="usuario-name" />
      </div>

      <div class="informacion">
        <p class="lower">Nivel</p>
        <h2>{{ usuarioPerfil.nivel }}</h2> 
      </div>

      <div class="informacion">
        <p class="lower">Rango</p>
        <h2>{{ usuarioPerfil.rango }}</h2>
      </div>
    </div>
  </div>
</template>

<script>
import ProfileBadges from './ProfileBadges.vue';
import axios from 'axios';




export default {
  data() {
    return {
      usuarioPerfil: {},  
      usuarioLogueado: {},  
      imagenUrl: ''
    };
  },
  created() {
    this.obtenerUsuarioLogueado().then(() => {
    this.obtenerPerfilUsuario();
    });
  },
  methods: {
    async obtenerUsuarioLogueado() {
      try {
        const response = await axios.get("http://localhost:8080/profile/me", {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
        });
        this.usuarioLogueado = response.data;
        console.log("Usuario logueado:", this.usuarioLogueado);
      } catch (error) {
        console.error("Error al obtener el usuario logueado:", error.response || error);
        alert("No se pudo obtener el usuario logueado. Verifica tu autenticación.");
      }
    },
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
    },
    editarPerfil() {
      if (this.modoEdicion) {
        this.actualizarUsuario();
      } else {
        this.modoEdicion = true; 
      }
    },
    async actualizarUsuario() {
      try {
      const imagenPerfil = this.usuarioPerfil.imagen?.trim() || '';
      const imagenLogueado = this.usuarioLogueado.imagen?.trim() || '';

      if (!this.usuarioPerfil.usuario?.trim()) {
        alert("El nombre de usuario no puede estar vacío.");
      } else if (
        this.usuarioPerfil.usuario === this.usuarioLogueado.usuario &&
        imagenPerfil === imagenLogueado
      ) {
        alert("Tienes que hacer al menos un cambio para guardar.");
        return;
      }
      const id = this.usuarioPerfil.id;

      if (this.imagenUrl) {
      this.usuarioPerfil.imagen = this.imagenUrl; 
      }

      const response = await axios.put(
        `http://localhost:8080/profile/me`,
        { ...this.usuarioPerfil }, 
        {
          headers: { 
            Authorization: `Bearer ${localStorage.getItem("token")}`, 
            "Content-Type": "application/json"
          }
        }
      );

      const newToken = response.data.token;
      localStorage.setItem("token", newToken);

      alert("Cambios guardados con éxito");
      this.$router.push(`/profile/${id}`); 
      } catch (error) {
        console.error("Error al actualizar el perfil", error);
        alert("Hubo un problema al guardar los cambios");
      }
    },
    async cancelarCambios() {
      this.modoEdicion = false;
      const id = this.usuarioPerfil.id;
      this.$router.push(`/profile/${id}`);
    }
  },
  components: {
    ProfileBadges
  }
};
</script>

<style scoped>
.profile-info-container {
  display: flex;
  height: 100%;
  width: 100%;
}

.imagen-perfil {
  display: flex;
  margin-top: 15vh;
  justify-content: center;
}

.imagen-perfil img {
  display: flex;
  width: 10vw;
  height: 20vh;
  background-color: #FFFFFF;
  border: 1px solid black;
  box-shadow: 10px 10px 4px rgba(0, 0, 0, 0.3);
  justify-content: center;
  margin-left: 0px;
}

.left-section {
  display: flex;
  flex-direction: column;
  width: 20vw;
}

.info {
  display: flex;
  flex-direction: column;
  margin-top: 15vh; 
  height: auto;
}

.edit-profile {
  background-color: #ffffff;
  color: rgb(0, 0, 0);
  padding: 10px 20px;
  border: none;
  border-radius: 30px;
  cursor: pointer;
  width: 15vw;
  height: 5vh;
}

.info-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 3vh;
  min-height: 10vh;
}

.linea-bajo-perfil {
  border: none;
  border-top: 1px solid #000000; 
  margin-top: 30px;
  width: 15vw;
}

.informacion {
  margin: 5px 0;
}

.informacion h2 {
  font-size: 1.5rem;
  color: black;
}

.lower {
  font-size: 1.8rem;
  color: gray;
}

.badges {
  margin-top: 4vw;
  margin-left: 2vw;
}

.usuario-name {
  width: 15vw;
  height: 5vh;
  margin-top: 1vh;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.profile-url {
  width: 15vw;
  height: 5vh;
  margin-top: 1vh;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>
