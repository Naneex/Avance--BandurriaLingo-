<template>

    <div 
        class="flex items-center p-2 hover:px-3 cursor-pointer space-x-4 transition-all duration-400"
        @click = "abrirChat"
        >
        <img :src= "imagen" alt="usuario" class="w-11 h-11 rounded-full">
        <h5 class="font-semibold"> {{ nombre }} </h5>
    </div>

</template>

<script setup>

const props = defineProps({
    nombre: String,
    imagen: String
})

const emit = defineEmits(['abrirChat']);

function abrirChat(){
    emit('abrirChat');
}

</script>