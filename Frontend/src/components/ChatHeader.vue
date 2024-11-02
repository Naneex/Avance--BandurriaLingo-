<template>
    <div class="flex items-center py-2 px-5 justify-between shadow-sm bg-white rounded-xl ">
        <a href="">
          <router-link :to="`/profile/${props.usuarioHablarId}`">
          <img :src="imagen" alt="" class="w-14 h-14 rounded-full">
          </router-link>
        </a>
        <h2 class="font-semibold">{{nombre}}</h2>
        <a href="">
          <img src="../assets/informacion.png" alt="" class="w-8">
        </a>
      </div>
    
</template>

<script setup>

const props = defineProps ({ 
  nombre: String,
  imagen: String,
  usuarioHablarId: String
});
</script>