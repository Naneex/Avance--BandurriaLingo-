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
import axios from 'axios';

export default {
  data() {
    return {
      usuario: '',
      contraseña: '',
      idiomaDominado: '',
      idiomaAprender: '',
      alert: ''
    };
  },
  methods: {
    async registerUser() {
      if (this.usuario === '' || this.contraseña === '' || this.idiomaDominado === '' || this.idiomaAprender === '') {
        this.alert = 'Para registrarse hay que llenar todos los campos';
        return;
      }

      try {
        const response = await axios.post('http://localhost:8080/auth/register', {
          usuario: this.usuario,
          contrasena: this.contraseña,
          idiomaDominado: this.idiomaDominado,
          idiomaAprender: this.idiomaAprender
        });
        
        const token = response.data.token;
        if (token) {
          this.$router.push('/login');
        }
      } catch (error) {
        console.error('Error al intentar registrar usuario:', error);
        this.alert = 'Hubo un problema al registrar el usuario. Inténtalo nuevamente';
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
  