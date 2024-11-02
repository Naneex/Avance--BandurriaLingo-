<template>
    <div class="bg-slate-400 h-screen w-full flex flex-col">
    <NavbarHorizontal/>

    <div class="flex-grow flex flex-col justify-center items-center">
        <div class="bg-white w-6/12 max-w-6/12 h-96 max-h-96 flex justify-center items-center p-3 rounded-3xl shadow-xl">
            <span class="font-semibold text-2xl">{{card}}</span>
        </div>

        <button 
        v-if="tipo === 0"
        class="mt-5 border-2 border-green-500 rounded-full p-1.5 hover:bg-green-500 transition-all duration-500 text-lg shadow-lg hover:text-white"
        @click="cambiarToReverse"
        >
            Reverso
        </button>

        <button 
        v-if="tipo === 1"
        class="mt-5 border-2 border-blue-500 rounded-full p-1.5 hover:bg-blue-500 transition-all duration-500 text-lg shadow-lg hover:text-white"
        @click="cambiarDeCard"
        >
            Siguiente
        </button>
        
    </div>
</div>

</template>

<script setup>
import { computed, ref } from 'vue';
import NavbarHorizontal from '../components/NavbarHorizontal.vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const route = useRoute();
const categoria = route.params.categoria;
const usuarioLogueado = JSON.parse(localStorage.getItem('usuarioLogueado'));
const usuarioId = usuarioLogueado ? usuarioLogueado.id : null;
console.log("UsuarioId:", usuarioId);

const url = 'http://localhost:3000/usuario'
const cards = ref([]);
const posicionCard = ref(0);
const card = computed(() => {
    return determinarCard(tipo.value);
}) ;
const tipo = ref(0);

async function obetenerFlashcards() {
    try {
    const response = await axios.get(`${url}/${usuarioId}`); 
    console.log('Usuario:', usuarioId);
    const flashcards = response.data.flashcards.filter(flashcard => flashcard.categoria === categoria); 
    console.log(flashcards);

    if (flashcards) {
      cards.value = flashcards; 
    } 
  } catch (error) {
    console.error('Error:', error);
  }
}
obetenerFlashcards();

function determinarCard(tipo) {
    if (cards.value.length === 0 || !cards.value[posicionCard.value]) {
        return '';
    }
    if (tipo === 0) {
        return cards.value[posicionCard.value].frente;
    } else {
        return cards.value[posicionCard.value].reverso;
    }
}

function cambiarToReverse() {
    if(tipo.value === 0) {
        tipo.value = 1;
    } else {
        tipo.value = 0;
    }
}

function cambiarDeCard(){
    console.log("cards:", cards.value.length);
    console.log("posicion:",posicionCard.value);
    
    if (posicionCard.value > cards.value.length - 2) {
        alert("No hay más cards que estudiar")
    } else {
        posicionCard.value = posicionCard.value + 1; 
        tipo.value = 0;
    }
}

</script>