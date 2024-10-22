<template>
    <div class="flex">
      <nav class="w-full px-3 py-2 bg-white text-gray-800 flex justify-between shadow-md rounded-lg items-center">
        <ul>
          <IconoComponente
            :src="tresLineas"
            alt=""
            @click="toggleSidebar"
            class="cursor-pointer"
          />
        </ul>
        
        <span class="font-bold text-2xl">{{ page }}</span>
  
        <ul class="flex space-x-2">
          <IconoComponente
            :src="notificacionesIcon"
            alt="Notificaciones"
          />
          <RouterLink :to="{name: 'Profile', params: { id: usuarioId}}">
            <IconoComponente
            :src="userIcon"
            alt="Perfil del usuario"
            />
          </RouterLink>
        </ul>
      </nav>
  
      <aside
        :class="{
          'translate-x-0': isSidebarOpen,
          '-translate-x-full': !isSidebarOpen,
        }"
        class="flex flex-col justify-between fixed inset-y-0 left-0 w-64 h-screen p-2 bg-white text-gray-800 shadow-2xl rounded-lg transition-transform duration-500 z-30">
         
        <ul>
          <li class="flex justify-end rounded-2xl transition duration-500">
            <IconoComponente
              :src="equis"
              title="Cerrar"
              @click="toggleSidebar"
            />
          </li>
        </ul>
  
        <ul class="flex flex-col space-y-2 justify-center">
          <RouterLink to="/chat">
          <div class="flex items-center hover:bg-gray-200 hover:rounded-2xl cursor-pointer rounded-2xl transition duration-500">
            <li>
              <IconoComponente
                :src="mensajesIcon"
                alt="Mensajes"
                title="Mensajes"
              />
            </li>
            <span class="ml-8">Mensajes</span>
          </div>
          </RouterLink>
          <RouterLink to="/retodiario">
          <div class="flex items-center hover:bg-gray-200 hover:rounded-2xl cursor-pointer rounded-2xl transition duration-500">
            <li>
              <IconoComponente
                :src="juegoDiarioIcon"
                alt="Juego diario"
                title="Juego diario"
              />
            </li>
            <span class="ml-8">Juego Diario</span>
          </div>
          </RouterLink>
  
          <div class="flex items-center hover:bg-gray-200 hover:rounded-2xl cursor-pointer rounded-2xl transition duration-500">
            <li>
              <IconoComponente
                :src="foroIcon"
                alt="Foros"
                title="Foros"
              />
            </li>
            <span class="ml-8">Foros</span>
          </div>
          <RouterLink to="/flashcards">
          <div class="flex items-center hover:bg-gray-200 hover:rounded-2xl cursor-pointer rounded-2xl transition duration-500">
            <li>
                <IconoComponente
                  :src="flashCardIcon"
                  alt="FlashCards"
                  title="FlashCards"
                />
            </li>
            <span class="ml-8">FlashCards</span>
          </div>
          </RouterLink>
          <RouterLink to="/pares">
          <div class="flex items-center hover:bg-gray-200 hover:rounded-2xl cursor-pointer rounded-2xl transition duration-500">
            <li>
              <IconoComponente
                :src="juegoParesIcon"
                alt="Juego Pares"
                title="Juego Pares"
              />
            </li>
            <span class="ml-8">Juego Pares</span>
          </div>
        </RouterLink>
        <RouterLink to="/ranking">
          <div class="flex items-center hover:bg-gray-200 hover:rounded-2xl cursor-pointer rounded-2xl transition duration-500">
            <li>
              <IconoComponente
                :src="clasificatoriaIcon"
                alt="Clasificacion"
                title="Clasificacion"
              />
            </li>
            <span class="ml-8">Clasificacion</span>
          </div>
        </RouterLink>
        </ul>
        <ul>
          <li class="flex justify-center">
            <RouterLink to="/main">
              <IconoComponente
              :src="menuIcon"
              alt="Principal"
              title="Principal"
            />
            </RouterLink>
          </li>
        </ul>
      </aside>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import IconoComponente from './IconoComponente.vue';
  import userIcon from '../assets/usuario-de-perfil.png';
  import notificacionesIcon from '../assets/boton-de-notificaciones.png';
  import juegoDiarioIcon from '../assets/flama-de-fuego.png';
  import clasificatoriaIcon from '../assets/clasificacion.png';
  import foroIcon from '../assets/charla.png';
  import juegoParesIcon from '../assets/juego-de-poquer.png';
  import menuIcon from '../assets/casa.png';
  import tresLineas from '../assets/tresLineas.png';
  import equis from '../assets/x.png'
  import flashCardIcon from '../assets/card.png'
  import mensajesIcon from '../assets/email1.png'
  import { RouterLink } from 'vue-router';
  
const isSidebarOpen = ref(false);
const usuarioLogueado = JSON.parse(localStorage.getItem('usuarioLogueado'));
const usuarioId =usuarioLogueado ? usuarioLogueado.id : null;

  function toggleSidebar() {
    isSidebarOpen.value = !isSidebarOpen.value;
  }
  
  const props = defineProps({
    page: String
  })
  </script>