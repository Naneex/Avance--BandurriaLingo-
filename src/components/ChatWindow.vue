<template>
    <div class="bg-slate-300 w-full rounded-2xl">
      <ChatHeader :nombre="usuarioHablar.usuario" :imagen="usuarioHablar.imagen"/>
  
      <div class="h-[calc(100vh-125px)] overflow-hidden bg-slate-00">
        <div class="h-full overflow-auto">
          <div v-for="(mensaje, index) in mensajes" :key="index" class="flex flex-col items-center">
            <ChatMensaje
              :mensaje="mensaje.texto"
              :propioMensaje="mensaje.remitenteId === props.usuarioActual.id"
            />
          </div>
        </div>
      </div>
  
      <MensajeInput @enviarMensaje="manejarMensaje"/>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import ChatMensaje from './ChatMensaje.vue';
  import MensajeInput from './MensajeInput.vue';
  import ChatHeader from './ChatHeader.vue';
  import axios from 'axios';
  
  const nuevoMensaje = ref("");
  
  const props = defineProps({
    usuarioHablar: Object,
    usuarioActual: Object
  })
  
  const mensajes = ref([]);
  
  const url = 'http://localhost:3000/chats/';
  
  async function obtenerMensajes() {
    try {
      const response = await axios.get(url); 
      console.log('Respuesta del servidor:', response.data);
      const chats = response.data;
      const chat = response.data.find(c => (c.integrantes.includes(props.usuarioHablar.id) && c.integrantes.includes(props.usuarioActual.id))
      ); 
      
      console.log(chat);
      
      if (chat) {
        mensajes.value = chat.mensajes; 
      } else {
        const newChat = {
          id: String(chats.length + 1),
          integrantes: [props.usuarioActual.id, props.usuarioHablar.id],
          mensajes: []
        }
  
         const updateResponse = await fetch (`http://localhost:3000/chats/`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(newChat),
         });
  
         if (updateResponse.ok) {
          const addChat = await updateResponse.json();
          console.log('Chat agregado:', addChat);
          } else {
            console.error('Error', updateResponse.statusText);
          }
         console.log(newChat);
      }
  
      console.log(mensajes.value); 
    } catch (error) {
      console.error('Error:', error);
    }
  }
  
  obtenerMensajes();
  
  async function manejarMensaje(mensaje) {
    nuevoMensaje.value = mensaje;
    mensajes.value.push({ texto: mensaje, remitenteId: props.usuarioActual.id });
    console.log({ texto: mensaje, remitenteId: props.usuarioActual.id });
  
    try {
      const response = await axios.get(url);
      const chat = response.data.find(c =>
        (c.integrantes[0] === props.usuarioHablar.id && c.integrantes[1] === props.usuarioActual.id) ||
        (c.integrantes[0] === props.usuarioActual.id && c.integrantes[1] === props.usuarioHablar.id)
      );
  
      if (chat) {
        const newMessage = {
          id: chat.mensajes.length + 1, 
          texto: mensaje,
          remitenteId: props.usuarioActual.id
        };
  
        chat.mensajes.push(newMessage);
  
        const updateResponse = await fetch(`http://localhost:3000/chats/${chat.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(chat), 
        });
  
        if (updateResponse.ok) {
          const updatedChat = await updateResponse.json();
          console.log('Chat actualizado:', updatedChat);
        } else {
          console.error('Error al actualizar el chat:', updateResponse.statusText);
        }
      }
    } catch (error) {
      console.error('Error al manejar el mensaje:', error);
    }
  }
  </script>
  