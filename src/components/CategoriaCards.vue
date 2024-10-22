<template>
    <div class="flex justify-between space-x-10 p-2 px-4 text-lg py-5 items-center">
        <div class="w-6/12 justify-between flex text-xl">
            <span>{{ categoria }}</span>
            <span> {{ numberCards }}</span>
        </div>
        <div class="space-x-3">
            <button 
            class="border-green-200 border-2 px-3 hover:bg-green-200 transition duration-700 rounded-2xl"
            @click="emitirEstudiar"
            >
            Estudiar
        </button>
        <button 
        class="border-2 border-blue-200 px-5 hover:bg-blue-200 transition duration-700 rounded-2xl"
        @click="emitirCrear">
            Crear
        </button>
        </div>
    </div>
</template>

<script setup>
    const props = defineProps({
        categoria: String,
        numberCards: Number
    })
    const emits = defineEmits(['estudiar', 'crear']);

function emitirEstudiar(){
    emits('estudiar');
}

function emitirCrear(){
    emits('crear')
}
</script>