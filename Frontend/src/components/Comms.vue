<template>
    <div class="mt-[50vh] h-[400px] w-[300vw] max-w-[1300px] bg-gray-200 border border-gray-300 p-5 overflow-y-auto mr-auto rounded-3xl fixed left-[20%]">
      <h2 class="text-xl font-bold mb-4">Comentarios</h2>
      <textarea
        v-model="nuevoComentario"
        placeholder="Añadir un comentario..."
        class="w-[65.5vw] h-[4vw] p-2 mb-3 border border-gray-400 rounded resize-none"
      ></textarea>
      <button @click="enviarComentario" class="bg-white text-black py-2 px-4 rounded-lg cursor-pointer hover:bg-gray-300">Enviar</button>
  
      <ul class="list-none p-0 max-w-full">
        <li v-for="(comentario, index) in comentarios" :key="index" class="mt-2 bg-gray-200 p-2 rounded border border-gray-300 shadow max-w-full break-words">
          <strong>{{ comentario.usuario }}:</strong> <br />
          <span>{{ comentario.mensaje }}</span>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  export default {
    data() {
      return {
        nuevoComentario: '',
        comentarios: [],
        usuarioPerfil: {},
        usuarioLogueado: {}
      };
    },
    created() {
      this.obtenerPerfilUsuario();
      this.obtenerUsuarioLogueado();
    },
    methods: {
      async obtenerPerfilUsuario() {
      const id = this.$route.params.id; 
      try {
        const response = await axios.get(`http://localhost:8080/profile/${id}`, {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
        });
        this.usuarioPerfil = response.data; 
        console.log("Perfil del usuario:", this.usuarioPerfil);
      } catch (error) {
        console.error("Error al obtener el perfil del usuario:", error.response || error);
        alert("No se pudo obtener el perfil del usuario.");
      }
    },
  
      async obtenerUsuarioLogueado() {
      try {
        const response = await axios.get("http://localhost:8080/profile/me", {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
        });
        this.usuarioLogueado = response.data;
        console.log("Usuario logueado:", this.usuarioLogueado);
      } catch (error) {
        console.error("Error al obtener el usuario logueado:", error.response || error);
        alert("No se pudo obtener el usuario logueado. Verifica tu autenticación.");
      }
    },
  
      async enviarComentario() {
        if (this.nuevoComentario.trim() === '') {
          alert('El comentario no puede estar vacío');
          return;
        }
  
        const nuevoComentarioObj = {
          usuario: this.usuarioLogueado.usuario,  
          mensaje: this.nuevoComentario
        };
  
        this.comentarios.push(nuevoComentarioObj);
        this.usuarioPerfil.comentarios = this.comentarios;
  
        try {
          await axios.put(`http://localhost:3000/usuario/${this.usuarioPerfil.id}`, {
            ...this.usuarioPerfil 
          });
          this.nuevoComentario = '';
        } catch (error) {
          console.error('Error al enviar el comentario:', error);
        }
      }
    }
  };
  </script>
  
  