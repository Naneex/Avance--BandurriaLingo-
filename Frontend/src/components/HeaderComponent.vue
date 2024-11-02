<template>
 <header>
    <div class="left-section">
        <button class="menu-icon" @click="toggleMenu"><img src="../assets/Imagenes/Menu.png" alt=""></button>
    </div>
    <nav v-if="menuVisible" class="nav-menu">
      <ul>
        <li>
          <img src="../assets/Imagenes/Home.png" alt="">
          <router-link to="/Main">Inicio</router-link>
        </li>
        <li>
          <img src="../assets/Imagenes/ranking.png" alt="">
          <router-link to="/Ranking">Ranking</router-link>
        </li>
        <li>
          <img src="../assets/Imagenes/reto.png" alt="">
          <router-link to="/retodiario">Reto Diario</router-link>
        </li>
        <li>
          <a @click.prevent="redirectToProfile"><img src="../assets/usuario-de-perfil.png" alt="">Perfil</a>
        </li>
        <li>
          <img src="../assets/Imagenes/reto.png" alt="">
          <router-link to="/Pares">Pares</router-link>
        </li>
        <li>
          <img src="../assets/Imagenes/flashcards.png" alt="">
          <router-link to="/flashcards">FlashCards</router-link>
        </li>
        <li>
          <img src="../assets/Imagenes/test.png" alt="">
          <router-link to="/quiz/principal">Test de nivel</router-link>
        </li>
        <li>
          <img src="../assets/Imagenes/forum.png" alt="">
          <router-link to="/Foro">Foro</router-link>
        </li>
        <li>
          <img src="../assets/Imagenes/msg.png" alt="">
          <router-link to="/chat">Chats</router-link>
        </li>

      </ul>

    </nav>

    <div v-if="menuVisible" class="overlay" @click="toggleMenu"></div>

  
    <h1>{{ nombre_pag }}</h1>
    <div class="right-section">
      <div class="msg">
        <router-link to="/chat"><img src="../assets/Imagenes/msg.png" alt=""></router-link>
      </div>
      <div class="notifaction">
        <img src="../assets/Imagenes/Notificaciones.png" alt="">
      </div>
      <div class="profile">
        <a @click.prevent="redirectToProfile"><img src="../assets/usuario-de-perfil.png" alt=""></a>
      </div>
      <button class="desplegable-icon" @click="toggleDesplegable" ><img src="../assets/Imagenes/Desplegable.png" alt=""></button>
      <nav v-if="desplegableVisible" class="desplegable-menu">
        <ul>
          <li>
            <a @click.prevent="returnToLogin">Cerrar Sesion</a>
          </li>
        </ul>
      </nav>
    </div>
  </header>
</template>
  
<script>
  export default {
  name: 'HeaderComponent',

  data() {
    return {
      menuVisible: false,
      desplegableVisible: false,
    };
  },

  methods: {
    toggleMenu() {
      this.menuVisible = !this.menuVisible;
    },

    toggleDesplegable() {
      this.desplegableVisible = !this.desplegableVisible;
    },

    returnToLogin() {
      this.$router.push('/login');
    },

    redirectToProfile() {
      const usuario = localStorage.getItem('usuarioLogueado');
      if (usuario) {
        const usuarioLogueado = JSON.parse(usuario);
        this.$router.push(`/profile/${usuarioLogueado.id}`); 
      } else {
        console.error('No hay usuario logueado.');
      }
    }
  },

  props: {
    nombre_pag: {
      type: String,
      required: true,
    },
  },
};
</script>
  
<style scoped>

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body, #app, template{
  width: 100%;
  margin: 0;
  padding: 0;
  height: 100%;
}




header{
  z-index: 10;
  display: flex;
  align-items: center;
  background-color: #FFFFFF;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.3);
  padding: 1%;
  width: 100%;
  position: fixed;
  top: 0;
  height: 10%;
}

header h1{
  flex-grow: 1;
  margin-left: 1%;
  font-size: 2.0rem; 
  text-align: center;
  flex: 1;
}

.left-section {
  display: flex;
  align-items: center;
  width: 10%;
  height: auto; 
}
.left-section img{
  width: 25%; 
  height: auto; 
  margin-left: -20%; 
}

.right-section{
  display: flex;
  align-items: center;
  margin-left: auto;
  gap: 1.25rem; 
}
.right-section img{
  width: 2rem; 
  height: auto; 
}

.menu-icon{
  background: none;
  border: none;
  cursor: pointer;
  margin-left: 30px;
}

.desplegable-icon{
  background: none;
  border: none;
  cursor: pointer;
}

.icon-inicio{
  src: url(Imagenes/Perfil.png);
}

.nav-menu{
  background-color: #FFFFFF;
  position: absolute;
  top: 100%;
  left: 0;
  width: 15%;
  height: 90vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 1.5rem;
  box-shadow: 1px  4px rgba(0, 0, 0, 0.3);
  list-style: none;
  z-index: 2;

}

.nav-menu ul {
  padding: 0; 
  margin: 0; 
  list-style: none; 

}

.nav-menu li {
  list-style: none; 
  margin: 1.9rem;
  display: flex;
  gap: 0.1;
  align-items: center;
}

.nav-menu a {
  text-decoration: none;
  color: #000000;
  font-size: 1.5rem;
  font-size: 23px;
  display: flex;
}

.nav-menu img{
  width: 2rem; 
  height: 2rem; 
  margin-right: 1rem;
}

.overlay {
  position: fixed; 
  top: 10%;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1; 
}

.desplegable-menu{
  
  
}

.desplegable-menu a{
  color: #000000;
}
.desplegable-menu ul{
  padding: 0; 
  margin: 0; 
  list-style: none; 
}

.desplegable-menu li{
  list-style: none; 
}

.nav-menu a:hover{
  color: #000000;
  background-color: #DCDCDC;
  border-radius: 10px;
}

.msg a{
  text-decoration: none;
  color: #000000;
}

.msg a:hover{
  text-decoration: none;
  color: #000000;
  background-color: transparent;
  border-radius: 0px;
}

.profile a{
  text-decoration: none;
  color: #000000;
}

.profile a:hover{
  text-decoration: none;
  color: #000000;
  background-color: transparent;
  border-radius: 0px;
}

</style>
  