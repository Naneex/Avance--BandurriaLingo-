<template>
    <div class="flex space-x-2">
      <ContenedorLetra 
        v-for="(letra, index) in resultado" 
        :key="index" 
        :disabled="!isCurrent" 
        :letra="word[index]" 
        :tipo="resultado[index]"
        @update:letra="updateLetra(index, $event)" 
      />
    </div>
  </template>
  
  <script setup>
  import { ref, defineEmits, defineProps } from 'vue';
  import ContenedorLetra from './ContenedorLetra.vue';
  
  const word = ref(["", "", "", "", ""]);
  const emit = defineEmits(['mostrar:word']);
  const props = defineProps({
    isCurrent: Boolean,
    resultado: Array
  });
  
  function updateLetra(index, letra) {
    word.value[index] = letra;
    console.log(word.value.join(""));
    emit('mostrar:word', word.value.join(""));
  }
  </script>
  