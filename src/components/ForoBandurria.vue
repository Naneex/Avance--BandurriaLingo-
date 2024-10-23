<template>
  <div class="contenedor">
    <header>
      <div class="logo">
        <h1 @click="goToMain" style="cursor: pointer;">Bandurria</h1>
        <p @click="goToMain" style="cursor: pointer;">Lingo </p>
      </div>
      <div class="titulo-foro">
        <h2>Foro</h2>
      </div>
      <div class="notificacion">
          <img src="../assets/Notification.png" alt="Notificaciones" @click="abrirModalNotificacion"> 
        </div>
    </header>

    <main>
      <section class="categorias">
        <h3>Categorías</h3>
        <div class="buscador-crear">
          <input type="text" placeholder="Buscar categoría...">
          <button class="boton-crear" @click="abrirModal">+ Crear</button>
        </div>
        <div class="columnas-categorias">
          <div class="categoria">
            <h4>Cultura</h4>
            <div class="caja-categoria">
              <h3>Participantes</h3>
              <p>10+ Más</p>
              <h5>Cultural Insights from Native Speakers</h5>
              <p>Explore cultural nuances with native speakers from around the world.</p>
              <p><strong>Autor: SarahJ</strong></p>
              <button class="boton-unirme" @click="goToForoEjemplo">+ Unirme</button>
            </div>
            <div class="caja-categoria">
              <h3>Participantes</h3>
              <p>10+ Más</p>
              <h5>Understanding Global Etiquette</h5>
              <p>Discuss the various customs and etiquettes around the world that shape our interactions.</p>
              <p><strong>Autor: CultureGOD</strong></p>
              <button class="boton-unirme">+ Unirme</button>
            </div>
          </div>

          <div class="categoria">
            <h4>Gramática</h4>
            <div class="caja-categoria">
              <h3>Participantes</h3>
              <p>10+ Más</p>
              <h5>Grammar Tips and Tricks</h5>
              <p>Share your best grammar tips to improve your English writing.</p>
              <p><strong>Autor: Guru</strong></p>
              <button class="boton-unirme">+ Unirme</button>
            </div>
            <div class="caja-categoria">
              <h3>Participantes</h3>
              <p>10+ Más</p>
              <h5>Common Grammar Pitfalls</h5>
              <p>Identify and avoid common grammar mistakes that even native speakers make.</p>
              <p><strong>Autor: Pepito12</strong></p>
              <button class="boton-unirme">+ Unirme</button>
            </div>
          </div>

          <div class="categoria">
            <h4>Vocabulario</h4>
            <div class="caja-categoria">
              <h3>Participantes</h3>
              <p>10+ Más</p>
              <h5>Expanding Your Vocabulary</h5>
              <p>Learn new words and see how they can enhance your daily conversations.</p>
              <p><strong>Autor: WordMRS</strong></p>
              <button class="boton-unirme">+ Unirme</button>
            </div>
            <div class="caja-categoria">
              <h5>Participantes</h5>
              <p>10+ Más</p>
              <h5>Daily Word Challenge</h5>
              <p>Challenge yourself with a new word every day and incorporate it into your conversations.</p>
              <p><strong>Autor: Varshot</strong></p>
              <button class="boton-unirme">+ Unirme</button>
            </div>
          </div>
        </div>
      </section>

      <div class="lista-amigos">
        <div class="encabezado-perfil">
          <img src="../assets/Profile.png" alt="Ícono Perfil">
          <h4>Proyecto Aplicación</h4>
        </div>

        <div class="barra-busqueda2">
          <input type="text" placeholder="Buscar amigos...">
        </div>

        <h5>Amigos</h5>

        <div class="seccion-amigos">
          <p>En línea</p>
          <div class="amigo-item" v-for="amigo in amigosEnLinea" :key="amigo.nombre">
            <div class="info-amigo">
              <img src="../assets/Friend.png" alt="Ícono Usuario">
              <span class="nombre-amigo">{{ amigo.nombre }}</span>
              <span class="estado-amigo"></span>
            </div>
            <div class="icono-chat"></div>
          </div>
        </div>

        <div class="seccion-amigos">
          <p>Desconectados (4)</p>
          <div class="amigo-item" v-for="amigo in amigosDesconectados" :key="amigo.nombre">
            <div class="info-amigo">
              <img src="../assets/Friend.png" alt="Ícono Usuario">
              <span class="nombre-amigo">{{ amigo.nombre }}</span>
              <span class="estado-amigo desconectado"></span>
            </div>
            <div class="icono-chat"></div>
          </div>
        </div>
      </div>

      <div id="modal" class="modal">
        <div class="modal-contenido">
          <span class="cerrar" @click="cerrarModal">&times;</span>
          <h2>Crear Nueva Categoría</h2>
          <form @submit.prevent="crearCategoria">
            <div class="form-group">
              <label for="nombreCategoria">Titulo*:</label>
              <input type="text" id="nombreCategoria" v-model="nuevaCategoria.nombre" required>
            </div>
            <div class="form-group">
              <label for="descripcionCategoria">Descripción*:</label>
              <textarea id="descripcionCategoria" v-model="nuevaCategoria.descripcion" required></textarea>
            </div>
            <button type="submit" class="boton-crear">Crear</button>
          </form>
        </div>
      </div>
      <div id="modalNotificacion" class="modal">
        <div class="modal-contenido">
          <span class="cerrar" @click="cerrarModalNotificacion">&times;</span>
          <h2>No hay notificaciones pendientes</h2>
        </div>
      </div>

      <div id="fondoOscuro" class="fondo-oscuro"></div>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      amigosEnLinea: [
        { nombre: 'Martin Zúñiga' },
        { nombre: 'Leo Messi' },
        { nombre: 'Bob Esponja' },
      ],
      amigosDesconectados: [
        { nombre: 'Juan Manuel' },
        { nombre: 'Esteban Ortiz' },
        { nombre: 'Sebastián Piñera' },
        { nombre: 'Orlando Caullan' },
      ],
      nuevaCategoria: {
        nombre: '',
        descripcion: ''
      }
    };
  },
  methods: {
    goToMain() {
      this.$router.push('/main');  
    },
    
    abrirModal() {
      document.getElementById('modal').style.display = 'block';
      document.getElementById('fondoOscuro').style.display = 'block';
    },
    cerrarModal() {
      document.getElementById('modal').style.display = 'none';
      document.getElementById('fondoOscuro').style.display = 'none';
    },

    abrirModalNotificacion() {
      document.getElementById('modalNotificacion').style.display = 'block';
      document.getElementById('fondoOscuro').style.display = 'block';
    },
    cerrarModalNotificacion() {
      document.getElementById('modalNotificacion').style.display = 'none';
      document.getElementById('fondoOscuro').style.display = 'none';
    },

    crearCategoria() {
      if (this.nuevaCategoria.nombre && this.nuevaCategoria.descripcion) {
        console.log('Nueva categoría creada:', this.nuevaCategoria);

        this.cerrarModal();

        this.nuevaCategoria.nombre = '';
        this.nuevaCategoria.descripcion = '';
      }
    },
    goToForoEjemplo() {
      this.$router.push('/foroejemplo');
    }
  }
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: Arial, sans-serif;
  background-color: #f0f4f7;
  margin: 0;
  padding: 0;
}

.contenedor {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  margin-top: 100px;
}

header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #ffffff;
  padding: 15px;
  border-bottom: 1px solid #ddd;
  position: fixed;
  top: 0px;
  width: 100%;
  z-index: 1000;
}

.logo {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.logo h1 {
  font-size: 30px; 
  font-weight: bold;
  margin: 0;
  text-align: center;
}

.logo p {
  font-size: 30px; 
  font-weight: bold;
  margin: 0;
  text-align: center;
  line-height: 1; 
}

.titulo-foro h2 {
  font-size: 40px;
  margin: 0 auto;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

.notificacion img {
  width: 50px;
  height: 50px;
}

.buscador-crear {
   display: flex;
  gap: 10px;
  align-items: center;
  flex-grow: 1;
  margin-right: 10px;
  margin-top: -20px;
}

.buscador-crear input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.boton-crear {
  padding: 8px 12px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.boton-crear:hover {
  background-color: #218838;
}

main {
  display: flex;
  width: 100%;
  max-width: 1200px;
  margin-top: 20px;
}

.categorias {
  flex: 3;
}

.categorias h3 {
  margin-right: 20px;
  font-size: 24px;
  margin-bottom: 20px;
}

.columnas-categorias {
  display: flex;
  justify-content: space-between;
}

.categoria {
  flex: 1;
  margin-right: 20px;
}

.categoria h4 {
  font-size: 18px;
  margin-bottom: 10px;
}

.caja-categoria {
  background-color: #ffffff;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.caja-categoria h5 {
  font-size: 16px;
  margin-bottom: 8px;
}

.caja-categoria p {
  font-size: 14px;
  color: #666;
  margin-bottom: 10px;
}

.caja-categoria button {
  padding: 8px 12px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.caja-categoria button:hover {
  background-color: #0056b3;
}

.lista-amigos {
  position: fixed;
  top: 150px;
  right: 20px;
  width: 300px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.encabezado-perfil {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.encabezado-perfil img {
  width: 40px;
  height: 40px;
  margin-right: 10px;
  border-radius: 50%;
}

.barra-busqueda2 {
  display: flex;
  justify-content: center;
  margin-bottom: 15px;
}

.barra-busqueda2 input {
  width: 100%;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 14px;
}

.lista-amigos h4, .encabezado-perfil h4 {
  font-size: 18px;
  margin-bottom: 10px;
  font-weight: bold;
}

.seccion-amigos {
  display: flex;
  flex-direction: column;
  padding: 10px 0;
  border-top: 1px solid #ddd;
}

.seccion-amigos:first-child {
  border-top: none;
}

.seccion-amigos p {
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 5px;
}

.amigo-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
  padding: 8px;
  border-bottom: 1px solid #ddd;
}

.amigo-item:last-child {
  border-bottom: none;
}

.info-amigo {
  display: flex;
  align-items: center;
}

.info-amigo img {
  width: 24px;
  height: 24px;
  margin-right: 10px;
  border-radius: 50%;
}

.nombre-amigo {
  font-size: 14px;
}

.estado-amigo {
  display: inline-block;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: green;
  margin-left: 5px;
}

.estado-amigo.desconectado {
  background-color: gray;
}

.icono-chat {
  width: 18px;
  height: 18px;
  background-size: cover;
}

.fondo-oscuro {
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 999;
}

.modal {
  display: none;
  position: fixed;
  z-index: 1000;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
}

.modal-contenido {
  text-align: center;
}

.cerrar {
  position: absolute;
  top: 10px;
  right: 20px;
  font-size: 24px;
  cursor: pointer;
  color: #333;
}

.modal-contenido form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.modal-contenido .form-group {
  display: flex;
  flex-direction: column;
}

.modal-contenido .form-group label {
  font-weight: bold;
  margin-bottom: 5px;
}

.modal-contenido .form-group input,
.modal-contenido .form-group textarea {
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ddd;
  font-size: 14px;
}

.modal-contenido button {
  padding: 10px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.modal-contenido button:hover {
  background-color: #218838;
}

</style>
