<template>
    <div class="login-container">
      <div class="texto-ajustado">
        <h1>Registrate</h1>
        <form @submit.prevent="registerUser" class="boxes">
          <input v-model="usuario" placeholder="Usuario" />
          <input v-model="contraseña" type="password" placeholder="Contraseña" />
          <div class="box">
          <select v-model="idiomaDominado">
            <option value="" disabled selected>Idioma que dominas</option>
            <option>Español</option>
            <option>Inglés</option>
          </select>
          </div>

          <div class="box-2">
          <select v-model="idiomaAprender">
            <option value="" disabled selected>Idioma a aprender</option>
            <option>Español</option>
            <option>Inglés</option>
          </select>
          </div>
          <button type="submit" class="boton-click">Registrate</button>
          <hr class="linea" />
          <p class="p-login">
              ¿Ya tienes una cuenta? <a @click.prevent="redirectToLogin" class="a-class">Logueate</a>
          </p>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        usuario: '',
        contraseña: '',
        idiomaDominado: '',
        idiomaAprender: '',
        xp: 0,
        nivel:0,
        imagen: '',
        rango: '',
        flashcards: [],
        rachaDiaria: 0,

      };
    },
    methods: {
        async registerUser() {

        if (this.usuario === '' || this.contraseña === '' || this.idiomaDominado === '' || this.idiomaAprender === '') {
            alert('Para registrarse hay que llenar todos los campos');
            return;
        }

        try {
        const response = await fetch(`http://localhost:3000/usuario?usuario=${this.usuario}`);
        const usuarios = await response.json();
        
        if (usuarios.length > 0) {
            alert('Este usuario ya existe');
            return; 
        }

        const newUser = { 
            usuario: this.usuario, 
            contraseña: this.contraseña,
            idiomaDominado: this.idiomaDominado,
            idiomaAprender: this.idiomaAprender,
            xp: this.xp,
            nivel: this.nivel,
            imagen: "https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.webp",
            rango:  "Novato",
            flashcards: this.flashcards,
            rachaDiaria: this.rachaDiaria

        };
        
        await fetch('http://localhost:3000/usuario', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newUser)
        });

        this.$router.push('/login');
        } catch (error) {
        console.error('Error registering user:', error);
        }
    },
  
      redirectToLogin() {
        this.$router.push('/login'); 
      }
    }
  };
  </script>
  
  <style scoped>
  .login-container {
    display: flex;
    justify-content: center; 
    align-items: center; 
    height: 100vh; 
    width: 50vw;
    padding-right: 20px; 
  }
  
  .texto-ajustado {
    max-width: 400px;
    text-align: center;
  }

  .texto-ajustado h1 {
    font-size: 2vw; 
    margin-bottom: 5vh; 
  }
  
  .boxes {
    margin-top: 1vh; 
    display: flex;
    flex-direction: column;
    gap: 5vh; 
    width: 100%;
    color: #3E3E3E;
  }
  
  input {
    padding: 1.1vh; 
    font-size: 2vw; 
    width: 100%; 
    background-color: #ffffff; 
    border: 1px solid #ADADAD; 
    opacity: 0.7; 
  }
  
  .boton-click {
    background-color: #ffffff; 
    border: none; 
    border-radius: 5vh; 
    padding: 3vh 2vh; 
    font-size: 1.5vw; 
    cursor: pointer; 
    width: 100%; 
    text-align: center; 
    margin-top: 1vh; 
  }
  
  .boton-click:hover {
    background-color: #bfbfbf;
  }
  
  .linea {
    border: none; 
    border-top: 1px solid #000000; 
    margin-top: 2vh; 
    width: 100%; 
  }
  
  .p-login {
    margin-top: -5vh; 
    font-size: 1.2vw;
  }
  
  .a-class {
    text-decoration: dashed;
    color: #909090;
    font-size: 1.5vw; 
    font-family: 'Roboto', sans-serif; 
  }

  select {
  padding: 1.5vh; 
  font-size: 2vw; 
  width: 100%; 
  background-color: #ffffff; 
  border: 1px solid #ADADAD; 
  opacity: 0.7; 
}
  
  
  </style>
  