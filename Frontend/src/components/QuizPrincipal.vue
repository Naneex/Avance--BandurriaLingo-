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
  
      <div class="contenedor2">
        <div class="question-box">
          <p class="question">Question</p>
          <h1>Which sentence is correct?</h1>
        </div>
  
        <div class="opciones">
          <button class="opcion" @click="checkAnswer('incorrect')">A. She don't like apples.</button>
          <button class="opcion" @click="checkAnswer('incorrect')">B. He doesn't likes apples.</button>
          <button class="opcion" @click="checkAnswer('incorrect')">C. They doesn't like apples.</button>
          <button class="opcion" @click="checkAnswer('correct')">D. She doesn't like apples.</button>
        </div>
  
        <div class="mensaje">
          <p>You can do it!</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      checkAnswer(answer) {
        if (answer === 'correct') {
          this.$router.push('/quiz/correct');  
        } else {
          this.$router.push('/quiz/incorrect'); 
        }
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

.contenedor2 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 40px;
  margin: 100px auto 0 auto; 
  width: 100%;
  max-width: 800px;
  background-color: #ffffff;
  border-radius: 15px;
  box-shadow: 0px 8px 30px rgba(0, 0, 0, 0.1);
}

.question-box {
  background-color: #ffe6e6;
  padding: 20px;
  border-radius: 10px;
  width: 100%;
  text-align: center;
  margin-bottom: 30px;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
}

.question {
  color: #B02747;
  font-size: 26px;
  font-weight: bold;
}

.opciones {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.opciones {
  margin: 20px 0;
}

.opcion {
  display: block;
  width: 100%;
  max-width: 500px;
  margin: 10px 0;
  padding: 15px;
  font-size: 18px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f7f7f7;
  cursor: pointer;
  transition: background-color 0.3s ease, box-shadow 0.3s ease;
  text-align: left;
}

.opcion:hover {
  background-color: #ececec;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
}

.mensaje {
  margin-top: 20px;
  font-size: 18px;
  color: #007bff;
  text-align: center;
}

.mensaje p {
  font-weight: bold;
}
  </style>