<template>
    <div class="w-96 min-w-96 bg-white mx-1 rounded-xl flex flex-col items-center overflow-hidden">
      <div class="m-5">
        <h1 class="text-left font-bold text-2xl">Mensajes</h1>
      </div>
  
      <BuscadorUsuario @buscarUsuario="filtrarUsuarios" />
  
      <div class="w-full h-full bg-white mt-5 rounded-2xl flex flex-col-reverse shadow-lg overflow-auto">
        <div v-for="usuario in usuariosFiltrados" :key="usuario.id">
          <Chat
            v-if="usuario.id !== props.usuarioActualId"
            :nombre="usuario.usuario" 
            :imagen="usuario.imagen"
            @abrirChat="abrirChat(usuario)"
          />
        </div>
      </div>
    </div>
  
    <ChatWindow
      v-if="chatSeleccionado"
      :key="chatSeleccionado.id"
      :usuarioHablar="chatSeleccionado"
      :usuarioActual="usuarioActual"
    />
  </template>
  
  <script setup>
  import { ref, computed, onMounted } from 'vue';
  import axios from 'axios';
  import BuscadorUsuario from './BuscadorUsuario.vue';
  import Chat from './Chat.vue';
  import ChatWindow from './ChatWindow.vue';
  import { useRouter } from 'vue-router';
  
  const url = 'http://localhost:3000/usuario/'; 
  
  const usuarios = ref([]);  
  const usuarioActual = ref(null); 
  const chatSeleccionado = ref(null); 
  const router = useRouter();

  const props = defineProps({
    usuarioActualId: String
  });
  
  async function obtenerUsuariosList() {
    try {
      const response = await axios.get(url);
      const data = response.data;
  
      usuarios.value = data.filter(usuario => usuario.id !== props.usuarioActualId);
      usuarioActual.value = data.find(u => u.id === props.usuarioActualId);
  
    } catch (error) {
      console.error('Error al obtener la lista de usuarios:', error);
    }
  }
  
  onMounted(obtenerUsuariosList);
  
  const abrirChat = (usuario) => {
    chatSeleccionado.value = usuario;
    console.log('Chat seleccionado:', chatSeleccionado.value);

    router.push(`/chat/${usuario.id}`);
  };
  
  const terminoBusqueda = ref('');
  const usuariosFiltrados = computed(() => {
    return usuarios.value.filter(usuario =>
      usuario.usuario.toLowerCase().includes(terminoBusqueda.value.toLowerCase()) 
    );
  });
  
  const filtrarUsuarios = (termino) => {
    terminoBusqueda.value = termino;
  };
  </script>
  