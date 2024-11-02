<template>
    <div class="flex flex-col h-screen bg-slate-400">
        <NavbarHorizontal :page="'Reto Diario'"/>
  
        <div class="relative flex items-center justify-end w-9/12">
          <div class="flex justify-center items-center">
              <img :src="fuegoIcon" alt="Fuego" class="absolute object-cover mt-20 opacity-80 h-16 w-16">
              <span class="absolute mt-24 text-4xl text-gray-700 font-bold shadow-2xl">{{ rachaReto }}</span>
          </div>
        </div>
  
        <div
        @keyup.enter="changeRow"
        class="flex flex-col justify-center flex-grow items-center space-y-2 "
        >
          <FilaLetras 
          v-for="(resultado, index) in resultadosFilas"
          :key="index"
          :isCurrent="rowCurrent === index"
          :resultado="resultado"
          @mostrar:word="actualizarWord"
          class="hover:scale-105 transition duration-500"
        />
      </div>
  </div>
  </template>
  <script setup>
  import { computed, ref } from 'vue';
  import FilaLetras from '../components/FilaLetras.vue';
  import NavbarHorizontal from '../components/NavbarHorizontal.vue';
  import fuegoIcon from '../assets/fuego.png';
  import axios from 'axios';
  
  const word = ref("");
  const rowCurrent = ref(0);
  const wordDaily = "write";
  const rachaReto = ref(0);
  const ganador = ref(false);
  const nuevaRacha = computed(() => {
    return rachaReto.value + 1;
  });
  const countFilaCurrent = ref(0);
  const resultadosFilas = ref([
    Array(5).fill(5),  // Fila 1
    Array(5).fill(5),  // Fila 2
    Array(5).fill(5),  // Fila 3
    Array(5).fill(5),  // Fila 4
    Array(5).fill(5),  // Fila 5
    Array(5).fill(5)   // Fila 6
  ])
  
  console.log(resultadosFilas.value[0]);
  
  const usuarioLogueado = JSON.parse(localStorage.getItem('usuarioLogueado'));
  const usuarioId =usuarioLogueado ? usuarioLogueado.id : null;
  const retoDiarioJugado = ref(false);
  
  async function changeRow() {    
    if (word.value.length < 5) {
        console.log(usuarioLogueado.jugarRetoDiario);
        
        alert("Complete todas las casillas");
    } else if (word.value === wordDaily) {
        ganador.value = true;
        revisarCasillas();
        await actualizarRachaDiaria();
        alert("Felicidades");
    } else {
        console.log(resultadosFilas.value[countFilaCurrent]);
        revisarCasillas();
        rowCurrent.value = rowCurrent.value + 1;
    }
  }
  
  function revisarCasillas() {
      for (let i = 0; i < 5; i++) {
      const letra = word.value[i];
      if (letra === wordDaily[i]) {
        resultadosFilas.value[countFilaCurrent.value][i] = 1;
      } else if (wordDaily.includes(letra)) {
        resultadosFilas.value[countFilaCurrent.value][i] = 0; 
      } else {
        resultadosFilas.value[countFilaCurrent.value][i] = -1; 
      }
    }
  
    countFilaCurrent.value = countFilaCurrent.value + 1;
  }
  
  function actualizarWord(wordActualizado) {
    console.log("permiso", retoDiarioJugado.value);
    console.log(resultadosFilas.value[0]);
    word.value = wordActualizado;
    console.log(word.value.length);
    console.log(word.value);
  }
  
  const url = 'http://localhost:3000/usuario/';
  
  async function obtenerDatos() {
    try {
        const response = await axios.get(url);
        const usuario = response.data.find(u => u.id === usuarioId);
  
        console.log(usuario);
        rachaReto.value = usuario.rachaDiaria;
        retoDiarioJugado.value = usuario.retoDiarioJugado;
        console.log(rachaReto.value);
    } catch (error) {
        console.error("Error al obtener datos:", error);
    }
  }
  
  async function actualizarRachaDiaria() {
    if (retoDiarioJugado.value) {
      alert("Ya ha sido jugado el dia de hoy");
      return;
    }
      try {
          const response = await fetch(`http://localhost:3000/usuario/${usuarioId}`);
          if (!response.ok) {
              throw new Error('Error al obtener datos del usuario');
          }
  
          const usuario = await response.json(); 
  
          const updatedData = {
              ...usuario, 
              rachaDiaria: nuevaRacha.value, 
              retoDiarioJugado: true
          };
  
          const updateResponse = await fetch(`http://localhost:3000/usuario/${usuarioId}`, {
              method: 'PUT',
              headers: {
                  'Content-Type': 'application/json',
              },
              body: JSON.stringify(updatedData),
          });
  
          if (!updateResponse.ok) {
              throw new Error('Error al actualizar la racha diaria');
          }
  
          const responseData = await updateResponse.json();
          console.log("Racha diaria actualizada:", responseData);
      } catch (error) {
          console.error("Error al actualizar la racha diaria:", error);
      }
  }
  
  obtenerDatos();
  </script>
  