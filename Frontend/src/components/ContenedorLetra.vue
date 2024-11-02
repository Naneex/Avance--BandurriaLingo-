<template>
    <input 
      type="text" 
      maxlength="1"
      v-model="letra"
      @input="emitirLetra"
      :disabled="disabled"
      :class="getClass()"
    >
  </template>
  
  <script setup>
  import { ref, defineProps, defineEmits } from 'vue';
  
  const emit = defineEmits(['update:letra']);
  const props = defineProps({
    letra: String,
    disabled: Boolean,
    estado: Number, 
    tipo: Number   
  })
 
  const letra = ref(props.letra);
  
 
  function emitirLetra() {
    emit('update:letra', letra.value);
  }
  
  
  function getClass() {
    // 1 = Posicion correcta
    // 0 = Posicion presente
    // -1 = Posicion incorrecta
    switch (props.tipo) {
      case 1: 
        return 'w-20 h-20 text-3xl font-bold flex text-center uppercase border-2 outline-none focus:border-green-300 rounded-xl border-green-400 bg-green-400';
      case 0: 
        return 'w-20 h-20 text-3xl font-bold flex text-center uppercase border-2 outline-none focus:border-yellow-300 rounded-xl border-yellow-300 bg-yellow-300';
      case -1:
        return 'w-20 h-20 text-3xl font-bold flex text-center uppercase border-2 outline-none focus:border-slate-300 rounded-xl border-slate-500 bg-slate-500 text-white';
      default: 
        return 'w-20 h-20 text-3xl font-bold flex text-center uppercase border-2 outline-none focus:border-red-300 rounded-xl border-slate-500';
    }
  }
  </script>
  