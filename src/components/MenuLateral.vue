<template>
    <div class="menu-lateral">
      <ul>
        <li>
          <img :src="require('@/assets/Inicioj4j4.png')" alt="Icono Inicio">
          <span><router-link to="/main">Inicio</router-link></span>
        </li>
        <li>
          <img :src="require('@/assets/reto diario.png')" alt="Icono Reto Diario">
          <span><router-link to="/retodiario">Reto Diario</router-link></span>
        </li>
        <li>
          <img :src="require('@/assets/fLashcards.png')" alt="Icono Flashcards">
          <span><router-link to="/flashcards">Flashcards</router-link></span>
        </li>
        <li>
          <img :src="require('@/assets/Profile.png')" alt="Icono Perfil">
          <span><router-link to="/profile/:id">Perfil</router-link></span>
        </li> 
        <li>
          <img :src="require('@/assets/Foropngj4j4.png')" alt="Icono Foros">
          <span><router-link to="/foro">Foro</router-link></span>
        </li>
        <li>
          <img :src="require('@/assets/test nivel.png')" alt="Icono Test Nivel">
          <span><router-link to="/quiz/principal">Quiz</router-link></span>
        </li>
        <li>
          <img :src="require('@/assets/ranking.jpg')" alt="Icono Ranking">
          <span><router-link to="/ranking">Ranking</router-link></span>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name: 'MenuLateral',
  };
  </script>
  
  <style scoped>
  .menu-lateral {
    position: fixed;
    top: 120px; 
    left: 20px;
    width: 200px;
    background-color: #ffffff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  }
  
  .menu-lateral ul {
    list-style-type: none;
  }
  
  .menu-lateral li {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }
  
  .menu-lateral li img {
    width: 40px;
    height: 40px;
    margin-right: 15px;
  }
  
  .menu-lateral li span {
    font-size: 18px;
    font-weight: bold;
  }
  </style>
  