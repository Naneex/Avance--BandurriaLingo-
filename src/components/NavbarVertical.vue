<template>
    <nav class="w-16 min-w-16 p-1 bg-white text-white  flex flex-col justify-between shadow-2xl rounded-xl items-center pb-2 pt-2">
        <div>
            <ul>
                <RouterLink :to="{name: 'Profile', params: { id: usuarioId}}">
                <IconoComponente
                :src="userIcon"
                alt="Perfil del usuario"
                title="Perfil"
                />
                </RouterLink>
                <IconoComponente
                :src="notificacionesIcon"
                alt="Notificaciones"
                title="Notificaciones"
                />
            </ul>
        </div>

        <div>
            <ul class="">
                <RouterLink to="/retodiario">
                <IconoComponente
                :src="juegoDiarioIcon"
                alt="Juego diario"
                title="Juego diario"
                />
                </RouterLink>
                <RouterLink to="/ranking">
                <IconoComponente
                :src="clasificatoriaIcon"
                alt="Clasificacion"
                title="Clasificacion"
                />
                </RouterLink>
                <IconoComponente
                :src="foroIcon"
                alt="Foro"
                title="Foro"
                />
                <RouterLink to="/pares">
                <IconoComponente
                :src="juegoParesIcon"
                alt="Juego de pares"
                title="Juego de pares"
                />
                </RouterLink>
                <RouterLink to="/flashcards">
                <IconoComponente
                :src="flashCardIcon"
                alt="FlashCards"
                title="FlashCards"
                />
                </RouterLink>
            </ul>
        </div>

        <div>
            <ul>
                <RouterLink to="/">
                    <IconoComponente
                    :src="menuIcon"
                    alt="Menu"
                    title="Menu"
                    />
                </RouterLink>
            </ul>
        </div>
</nav>
</template>

<script setup>
import IconoComponente from './IconoComponente.vue';
import userIcon from '../assets/usuario-de-perfil.png';
import notificacionesIcon from '../assets/boton-de-notificaciones.png';
import juegoDiarioIcon from '../assets/flama-de-fuego.png';
import clasificatoriaIcon from '../assets/clasificacion.png';
import foroIcon from '../assets/charla.png';
import juegoParesIcon from '../assets/juego-de-poquer.png';
import menuIcon from '../assets/casa.png';
import flashCardIcon from '../assets/card.png'
import { RouterLink } from 'vue-router';

const usuarioLogueado = JSON.parse(localStorage.getItem('usuarioLogueado'));
const usuarioId =usuarioLogueado ? usuarioLogueado.id : null;

</script>