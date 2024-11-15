<template>
  <div class="login-container">
    <div class="texto-ajustado">
      <h1>Bienvenido</h1>
      <form @submit.prevent="loginUser" class="boxes">
        <input v-model="usuario" placeholder="Usuario" />
        <input v-model="contraseña" type="password" placeholder="Contraseña" />
        <button type="submit" class="boton-click">Iniciar Sesión</button>
        <h1>{{ alert }}</h1>
        <hr class="linea" />
        <p class="p-login">
            ¿No tienes una cuenta? <a @click.prevent="redirectToRegister" class="a-class">Regístrate</a>
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
      alert: ''
    };
  },
  methods: {
    async loginUser() {
      if (this.usuario === '' || this.contraseña === '') {
        this.alert = 'Para iniciar sesión hay que llenar todos los campos';
        return;
      }
      try {
        const response = await axios.post('http://localhost:8080/auth/login', {
          usuario: this.usuario,
          contrasena: this.contraseña
        });
        const token = response.data.token;

        if (token) {
          localStorage.setItem('token', token);
          this.$router.push('/Main'); 
        }
      } catch (error) {
        console.error('Error al intentar hacer login:', error);
        this.alert = 'Usuario o contraseña incorrectos, vuelva a intentarlo';
      }
    },

    redirectToRegister() {
      this.$router.push('/register'); 
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
  color: #3E3E3E;
}

.boxes {
  margin-top: 5vh; 
  display: flex;
  flex-direction: column;
  gap: 5vh; 
  width: 100%;
  color: #3E3E3E;
}

input {
  padding: 1.5vh; 
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
  margin-top: 5vh; 
}

.boton-click:hover {
  background-color: #bfbfbf;
}

.linea {
  border: none; 
  border-top: 1px solid #000000; 
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


</style>
