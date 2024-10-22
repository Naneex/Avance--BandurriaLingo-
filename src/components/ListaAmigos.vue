<template>
  <div class="lista-amigos">
    <div class="encabezado-perfil">
      <img :src="require('@/assets/Profile.png')" alt="Ícono Perfil">
      <h4>Proyecto Aplicación</h4>
    </div>
    <div class="barra-busqueda2">
      <input type="text" placeholder="Buscar amigos..." v-model="busqueda">
    </div>

    <h5>Amigos</h5>

    <div class="seccion-amigos">
      <p>En línea</p>
      <div v-for="amigo in amigosEnLinea" :key="amigo.id" class="amigo-item">
        <div class="info-amigo">
          <img :src="require('@/assets/Friend.png')" alt="Ícono Usuario" class="icono-amigo">
          <span class="nombre-amigo">{{ amigo.nombre }}</span>
          <span class="estado-amigo"></span>
        </div>
      </div>
    </div>

    <div class="seccion-amigos">
      <p>Desconectados ({{ amigosDesconectados.length }})</p>
      <div v-for="amigo in amigosDesconectados" :key="amigo.id" class="amigo-item">
        <div class="info-amigo">
          <img :src="require('@/assets/Friend.png')" alt="Ícono Usuario" class="icono-amigo">
          <span class="nombre-amigo">{{ amigo.nombre }}</span>
          <span class="estado-amigo desconectado"></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      amigos: [],
      busqueda: ''
    };
  },
  computed: {
    amigosEnLinea() {
      return this.amigos.filter(amigo => amigo.online && amigo.nombre.toLowerCase().includes(this.busqueda.toLowerCase()));
    },
    amigosDesconectados() {
      return this.amigos.filter(amigo => !amigo.online && amigo.nombre.toLowerCase().includes(this.busqueda.toLowerCase()));
    }
  },
  mounted() {
    fetch('/db.json')
      .then(response => response.json())
      .then(data => {
        this.amigos = data.amigos;
      })
      .catch(error => {
        console.error('Error fetching amigos:', error);
      });
  }
};
</script>

<style scoped>
.lista-amigos {
  position: fixed;
  top: 110px;
  right: 20px;
  width: 300px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

.encabezado-perfil {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.encabezado-perfil img {
  width: 45px;
  height: 45px;
  margin-right: 15px;
  border-radius: 50%;
}

.barra-busqueda2 {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.barra-busqueda2 input {
  width: 100%;
  padding: 10px;
  border-radius: 8px;
  border: 1px solid #ccc;
  font-size: 15px;
}

.lista-amigos h4 {
  font-size: 20px;
  font-weight: bold;
}

.lista-amigos h5 {
  font-size: 16px;
  margin-bottom: 15px;
  font-weight: bold;
}

.seccion-amigos {
  margin-bottom: 20px;
}

.seccion-amigos p {
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.amigo-item {
  display: flex;
  align-items: center;
  padding: 8px 0;
  border-bottom: 1px solid #ddd;
}

.info-amigo {
  display: flex;
  align-items: center;
}

.icono-amigo {
  width: 35px;
  height: 35px;
  margin-right: 10px;
  border-radius: 50%;
}

.nombre-amigo {
  font-size: 14px;
  font-weight: normal;
  color: #333;
}

.estado-amigo {
  display: inline-block;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: green;
  margin-left: 8px;
}

.estado-amigo.desconectado {
  background-color: gray;
}
</style>

  
  