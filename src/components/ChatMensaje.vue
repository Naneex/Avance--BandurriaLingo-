<template>
    <div 
      :class="{'self-end': propioMensaje, 'self-start': !propioMensaje}" 
      class="w-auto p-2 mt-2 mx-2 rounded-lg shadow-sm max-w-4xl" 
      :style="mensajeColor">
      <p class="text-gray-800">{{ mensajeMostrar }}</p>
    </div>
    <button 
      v-if="!propioMensaje"
      class="text-sm text-black hover:text-blue-700 self-start ml-3" 
      @click="alternarTraduccion"> 
      {{ estado }}
    </button>
  </template>
  
  <script setup>
  import { computed, ref, defineProps } from 'vue';
  import axios from 'axios';
  
  const props = defineProps({
    mensaje: String,
    propioMensaje: Boolean
  });
  
  const isTraducido = ref(false);
  const traduccion = ref(''); 
  
  const traducirMensaje = () => {
    const apiKey = 'a154a095-7af9-4bf3-b75d-cbe89e90c6b0:fx';
    const url = 'https://api-free.deepl.com/v2/translate';
    const parametros = new URLSearchParams({
      auth_key: apiKey,
      text: props.mensaje,
      target_lang: 'ES'
    });
  
    axios.post(url, parametros)
      .then(response => {
        traduccion.value = response.data.translations[0].text;
        isTraducido.value = true;
      })
      .catch(error => {
        console.error('Error en la traducción:', error);
      });
  };
  
  const estado = computed(() => {
    return isTraducido.value ? "Original" : "Traducir";
  });
  
  const mensajeMostrar = computed(() => {
    return isTraducido.value ? traduccion.value : props.mensaje;
  });
  
  const alternarTraduccion = () => {
    if (!isTraducido.value) {
      traducirMensaje();
    } else {
      isTraducido.value = false;
    }
  };
  
  const mensajeColor = computed(() => {
    return {
      backgroundColor: props.propioMensaje ? '#F5F5DC' : '#F8F8FF'
    };
  });
  
  </script>
  