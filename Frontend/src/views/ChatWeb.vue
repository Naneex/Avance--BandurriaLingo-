<template>
    <div class="bg-slate-300 h-screen flex">
      <NavbarVertical class="h-full"/>
      <ListaDeChats :usuarioActualId="usuarioId"/>
    </div>
</template>

<script setup>
import NavbarVertical from "../components/NavbarVertical.vue";
import ListaDeChats from "../components/ListaDeChats.vue";

const usuarioLogueado = JSON.parse(localStorage.getItem('usuarioLogueado'));
const usuarioId =usuarioLogueado ? usuarioLogueado.id : null;
</script>

