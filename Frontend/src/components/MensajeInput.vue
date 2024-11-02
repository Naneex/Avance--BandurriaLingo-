<template>
    <div class="rounded-2xl flex items-center border mt-2 bg-slate-300 border-slate-500">
        <input 
        type="text" 
        class="rounded-2xl outline-none w-full h-10 px-4 placeholder-gray-600 bg-slate-300" 
        placeholder="Escribe tu mensaje..." 
        v-model="mensaje"
        @keyup.enter="enviarMensaje"
        /> 
        
        <button 
            class="rounded-lg px-5 h-10 text-black hover:text-blue-700"
            @click = "enviarMensaje"
        >Enviar</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';


const emit = defineEmits(['enviarMensaje']);
const mensaje = ref("");

function enviarMensaje() {
    if (!mensaje.value == "") {
        emit('enviarMensaje', mensaje.value);
        mensaje.value = "";
    }
}
</script>