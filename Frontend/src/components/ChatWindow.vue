<template>
  <div class="bg-slate-300 w-full rounded-2xl">
    <ChatHeader :nombre="usuarioHablar.usuario" :imagen="usuarioHablar.imagen" :usuarioHablarId="usuarioHablar.id"/>

    <div class="h-[calc(100vh-125px)] overflow-hidden bg-slate-00">
      <div class="h-full overflow-auto">
        <div v-for="(mensaje, index) in mensajes" :key="index" class="flex flex-col items-center">
          <ChatMensaje
            :mensaje="mensaje.content"
            :propioMensaje="mensaje.sender === usuarioActual.usuario"
          />
        </div>
      </div>
    </div>

    <MensajeInput @enviarMensaje="manejarMensaje"/>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import ChatMensaje from './ChatMensaje.vue';
import MensajeInput from './MensajeInput.vue';
import ChatHeader from './ChatHeader.vue';
import axios from 'axios';
import stompService from '../service/stompService.js';

const props = defineProps({
  usuarioHablar: Object,
  usuarioActual: Object
});
const chatId = ref(0);
const mensajes = ref([]);

async function obtenerMensajes() {
  try {
    const response = await axios.get(`http://localhost:8080/chats/between`, {
      params: {
        id1: props.usuarioActual.id,
        id2: props.usuarioHablar.id 
      }
    }); 

    console.log('Respuesta del servidor:', response.data);
    
    chatId.value = response.data.id;
    console.log("Chat ID: ", chatId.value);
    
    mensajes.value = response.data.messages;
    console.log("mensajes: " , mensajes.value); 
  } catch (error) {
    console.error('Error:', error);
  }
}

obtenerMensajes();

function manejarMensaje(mensaje) {
  console.log(`/topic/chat/${chatId.value}`);
  console.log("To Chat ID: ", (chatId.value));
  console.log("Message: ", mensaje);

  const newMensaje = {
    content: mensaje,
    sender: props.usuarioActual.usuario,
    chat_id: chatId.value
  }
  console.log(newMensaje);
  stompService.send(chatId.value, newMensaje);
}

onMounted(async () => {
  try {
    await stompService.connect();

    stompService.subscribe(`/topic/chat/${chatId.value}`, (msg) => {
      console.log("Mensaje recibido del servidor:", msg);

      const receivedMessage = {
        content: msg.content || msg.content,
        sender: msg.sender || msg.sender,
      };
      mensajes.value.push(receivedMessage);
    });
  } catch (error) {
    console.error("Error al conectar con STOMP:", error);
  }
});
</script>
