<template>
    <div class="h-screen flex flex-col bg-slate-400">
      <NavbarHorizontal :page="'FlashCards'"/>
  
      <div class="flex-grow flex my-32 shadow-2xl mx-56 bg-white rounded-xl">
            <div class="flex flex-col w-full">
                <div class="flex px-4 py-4 text-2xl justify-between shadow-sm">
                <span>Categoria</span>
                <span>Cards</span>
                <span class="mr-5 invisible">espaciado</span>
                </div>
            <div class="w-full">
                <CategoriaCards 
                :categoria="'Frases'" 
                :numberCards="countFrases" 
                class="shadow-sm"
                @estudiar="manejarEstudiar('Frases')"
                @crear="manejarCrear('frases')"
                />
                <CategoriaCards 
                :categoria="'Palabras'" 
                :numberCards="countPalabras" 
                class="shadow-sm"
                @estudiar="manejarEstudiar('Palabras')"
                @crear="manejarCrear('palabras')"
                />
                <CategoriaCards 
                :categoria="'Gramatica'" 
                :numberCards="countGramatica" 
                class="shadow-sm"
                @estudiar="manejarEstudiar('Gramatica')"
                @crear="manejarCrear('gramatica')"
                />
            </div>
            </div>
      </div>
    </div>
</template>
  
<script setup>
import { computed, ref } from 'vue';
import NavbarHorizontal from '../components/NavbarHorizontal.vue';
import CategoriaCards from '../components/CategoriaCards.vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const url = 'http://localhost:3000/usuario'
const router = useRouter();
const cards = ref([]);
const usuarioLogueado = JSON.parse(localStorage.getItem('usuarioLogueado'));
const usuarioId =usuarioLogueado ? usuarioLogueado.id : null;
console.log("Usuario:", usuarioLogueado.id);

const countFrases = computed(() => {
   return cards.value.filter(card => card.categoria === 'frases').length;
})
const countGramatica = computed(() => {
   return cards.value.filter(card => card.categoria === 'gramatica').length;
})
const countPalabras = computed(() => {
   return cards.value.filter(card => card.categoria === 'palabras').length;
})

async function obetenerFlashcards() {
    try {
    console.log(usuarioLogueado.flashcards);
    
    const response = await axios.get(`${url}/${usuarioId}`); 
    console.log('Usuario:', response.data);
    const flashcards = response.data.flashcards; 
    console.log(flashcards);
    
    if (flashcards) {
      cards.value = flashcards; 
    } 
  } catch (error) {
    console.error('Error:', error);
  }
}
obetenerFlashcards();

function manejarEstudiar(categoria) {
    console.log(categoria);
    
    router.push({
        name: 'Estudiar',
        params: { 
            categoria: categoria.toLowerCase(), 
            usuarioId: usuarioId
        }
    });
}
function manejarCrear(categoria) {
    console.log(categoria);
    
    router.push({
        name: 'Crear',
        params: { 
            categoria: categoria.toLowerCase()
        }
    });
}
</script>
  