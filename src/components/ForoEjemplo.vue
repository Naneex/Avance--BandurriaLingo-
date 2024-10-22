<template>
  <div class="foro-ejemplo-container">
    <HeaderComponente />
    <div class="content-wrapper">
      <MenuLateral />
      <div class="main-content">
        <div class="contenido-principal">
          <div class="seccion-imagen">
            <img src="../assets/Foro Perfil Rank.png" alt="Maestro de Rango" class="imagen-rango">
          </div>
    
          <div class="barra-busqueda">
            <input type="text" placeholder="Buscar...">
            <img src="../assets/settings.png" alt="Icono Ajustes" class="icono-ajustes">
          </div>
    
          <div class="seccion-comentarios">
            <div v-for="comentario in comentarios" :key="comentario.id" class="caja-comentario">
              <img src= "../assets/Profile.png" alt="Avatar de Usuario" class="avatar">
              <div class="contenido-comentario">
                <p>{{ comentario.usuario }}</p>
                <p>{{ comentario.mensaje }}</p>
                <p>{{ comentario.hashtags }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <ListaAmigos />
    </div>
  </div>
</template>

<script>
import HeaderComponente from './HeaderComponente.vue';
import MenuLateral from './MenuLateral.vue';
import ListaAmigos from './ListaAmigos.vue';
 
export default {
  data() {
    return {
      comentarios: []
    };
  },
  mounted() {
    fetch('http://localhost:3000/comentarios')
      .then(response => response.json())
      .then(data => {
        this.comentarios = data;
      })
      .catch(error => {
        console.error('Error fetching comentarios:', error);
      });
  },
  components: {
    HeaderComponente,
    MenuLateral,
    ListaAmigos
  }
};
</script>

<style scoped>
.foro-ejemplo-container {
  display: flex;
  flex-direction: column;
}

.content-wrapper {
  display: flex;
  justify-content: space-between;
}

.main-content {
  flex-grow: 1;
  padding: 20px;
}

.contenido-principal {
  width: 75%; 
  max-width: 1200px; 
  margin: 20px auto; 
  padding: 20px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.seccion-imagen {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.imagen-rango {
  width: 100%; 
  max-width: 600px; 
}

.barra-busqueda {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.barra-busqueda input[type="text"] {
  width: 70%;
  padding: 10px;
  border-radius: 20px;
  border: 1px solid #ccc;
}

.icono-ajustes {
  width: 30px;
  height: 30px;
  margin-left: 10px;
  cursor: pointer;
}

.seccion-comentarios {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.caja-comentario {
  display: flex;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  padding: 15px;
}

.avatar {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 15px;
}

.contenido-comentario {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.contenido-comentario p {
  margin: 0;
  padding: 2px 0;
}

.contenido-comentario p:nth-child(1) {
  font-weight: bold;
  font-size: 14px;
  color: #333;
}

.contenido-comentario p:nth-child(2) {
  font-size: 16px;
  color: #555;
}

.contenido-comentario p:nth-child(3) {
  font-size: 14px;
  color: #999;
}
</style>
