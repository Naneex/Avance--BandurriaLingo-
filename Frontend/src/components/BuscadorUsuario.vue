<template>
    <div class="bg-white w-11/12 h-10 rounded-xl flex items-center px-2 shadow-md focus:border focus:border-slate-900"> 
        <div class="mr-3"> 
            <img :src="lupaIcon" alt="Buscar" class="w-4 h-4" id="lupa-icon"> 
        </div>
        <input 
        type="text" 
        v-model="terminoBusqueda"
        @input="emitirBusqueda"
        class="bg-white border-0 outline-none w-full h-full placeholder-gray-600 font-arial" placeholder="Buscar">
    </div>
</template>

<script setup>
import lupaIcon from "../assets/simbolo-de-la-interfaz-de-busqueda.png"
import { defineEmits } from "vue";

const emit = defineEmits(['buscarUsuario']);

function emitirBusqueda(event) {

    const terminoBusqueda  = event.target.value;
    console.log(terminoBusqueda);
    emit('buscarUsuario', terminoBusqueda);
}

</script>