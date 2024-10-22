<template>
    <div class="bg-slate-400 w-full h-screen flex flex-col">
    <NavbarHorizontal/>

    <div class="flex-grow flex flex-col justify-center items-center">
        <span class="text-xl mb-2">
            Frente
        </span>
        <textarea 
            class="w-6/12 max-w-6/12 h-56 resize-none rounded-xl shadow-xl"
            placeholder="Escribe aquí"
            v-model="frente"
        ></textarea>

        <span class="text-xl mt-6 mb-2">
            Reverso
        </span>
        <textarea 
            class="w-6/12 max-w-6/12 h-56 resize-none rounded-xl shadow-xl"
            placeholder="Escribe aquí"
            v-model="reverso"
        ></textarea>

        <button 
        class="mt-5 border-2 p-1.5 rounded-full border-red-900 hover:bg-red-900 hover:text-white transition duration-500"
        @click="guardarFlashcard"
        >
            Guardar
        </button>
    </div>
</div>

</template>
<script setup>
import { ref } from 'vue';
import NavbarHorizontal from '../components/NavbarHorizontal.vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const route = useRoute();
const frente = ref('');
const reverso = ref('');
const categoria = route.params.categoria;
const usuarioLogueado = JSON.parse(localStorage.getItem('usuarioLogueado'));
const usuarioId = usuarioLogueado ? usuarioLogueado.id : null;

const url = `http://localhost:3000/usuario/${usuarioId}`;
console.log("usuario", usuarioId);

async function guardarFlashcard() {
  try {
    const response = await axios.get(url);
    const usuario = response.data;

    const nuevaFlashcard = {
      categoria: categoria,
      frente: frente.value,
      reverso: reverso.value,
    };

    usuario.flashcards.push(nuevaFlashcard);

    const updateResponse = await fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(usuario), 
    });

    if (updateResponse.ok) {
      const updatedUsuario = await updateResponse.json();
      console.log('Flashcard guardada exitosamente:', updatedUsuario);

      frente.value = '';
      reverso.value = '';
    } else {
      console.error('Error al actualizar las flashcards:', updateResponse.statusText);
    }
  } catch (error) {
    console.error('Error al guardar la flashcard:', error);
  }
}

</script>