<template>
    <div class="contenedor">
      <header>
        <div class="logo">
          <h1>Bandurria</h1>
          <p>Lingo</p>
        </div>
        <div class="quiz-titulo">
          <h2>Test de Nivel</h2>
        </div>
        <div class="notificaciones">
          <img src="../assets/Notification.png" alt="Notificaciones">
        </div>
      </header>
  
      <div class="contenedor">
        <img src="../assets/RankMaster.png" alt="Rango Obtenido">
        <button @click="$router.push('/quiz/principal')">Back to menu</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'QuizCorrect',
  };
  </script>
  
  <style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  display: flex;
  flex-direction: column;
}

.contenedor {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  flex-grow: 1; 
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

.logo h1, .logo p {
  font-size: 40px;
  font-weight: bold;
  margin: 0;
  text-align: center;
}

header .quiz-titulo h2 {
  font-size: 30px;
  text-align: center;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  margin: 0;
}

header .notificaciones img {
  width: 40px;
  height: 40px;
}
  
.contenedor img {
    width: 900px;
    max-width: 100%;
    height: auto;
    margin-bottom: 20px;
  }
  
.contenedor button {
    padding: 10px 20px;
    font-size: 18px;
    background-color: #18dd53;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
.contenedor button:hover {
    background-color: #225c0b;
  }
  </style>