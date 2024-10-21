<template>
    <div class="main-menu">
        <HeaderComponent nombre_pag="Menu Principal" />
        <div class="contenedor-centro">
        <div class="secciones">
            <div class="secciones-contenido">

                <div class="secciones-arriba-izq">
                    <img src="../assets/Imagenes/Foro.jpg" alt="">
                    <h2><router-link to="/Foro">Foro</router-link></h2>
                </div>
                <div class="secciones-arriba-der">
                    <img src="../assets/Imagenes/flashca.jpg" alt="">
                    <h2><router-link to="/flashcards">FlashCards</router-link></h2>
                </div>

            </div>
    
            <div class="cuadritos-container">
                <div class="cuadrito">
                    <img src="../assets/Imagenes/Reto diario.png" alt="">
                    <h2><router-link to="/retodiario">Reto Diario</router-link></h2>
                </div>
                <div class="cuadrito">
                    <img src="../assets/Imagenes/lvltest.png" alt="">
                    <h2><router-link to="/quiz/principal">Test de nivel</router-link></h2>
                </div>
                <div class="cuadrito">
                    <img src="../assets/Imagenes/Rank.png" alt="">
                    <h2><router-link to="/Ranking">Ranking</router-link></h2>
                </div>

                <div class="cuadrito">
                    <img src="../assets/Imagenes/juego-memoria.png" alt="">
                    <h2><router-link to="/Pares">Juego Memoria</router-link></h2>
                </div>
            </div>
            
        </div>
     </div>
    </div>
</template>

<script>
import HeaderComponent from './HeaderComponent.vue';

export default {
  name: 'MainMenu',
  components: {
    HeaderComponent,
  },
};



</script>

<style scoped>
body, html {
    margin: 0;
    padding: 0;
    height: 100%;
    background-color: white;
    font-family: 'Roboto', sans-serif;
}

.contenedor-centro {
    display: flex;
    position: relative;
    justify-content: center;
    align-items: center;
    width: 100vw;
    height: 90vh;
}

.secciones {
    display: block;
    background-color: #D9E2EC;
    width: 90vw;
    height: 90vh;
    justify-content: center;
    text-align: center;
    border-radius: 3rem;
}

.secciones-contenido {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    margin-bottom: 5vh;
    gap: 5vw;
}

.secciones-arriba-izq, .secciones-arriba-der {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #FFFFFF;
    width: 35vw;
    height: 30vh;
    border-radius: 2rem;
    margin-top: 15vh;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
}

.secciones-arriba-izq img {
    display: flex;
    width: 50%;
    height: 50%;
    border-radius: 1rem;
    margin-top: 1rem;
}

.secciones-arriba-der img {
    width: 40%;
    height: 50%;
    border-radius: 1rem;
    margin-top: 1rem;
}

.secciones h2 {
    font-size: 2rem;
    margin-top: 0.5rem;
    color: black;
}

.cuadritos-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 5vw;
    margin-top: 5vh;
}

.cuadrito {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #FFFFFF;
    width: 15vw;
    height: 30vh;
    border-radius: 2rem;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
}

.cuadrito img {
    width: 50%;
    height: 50%;
    border-radius: 1rem;
    margin-top: 1rem;
}

.cuadritos-container h2 {
    font-size: 1.5rem;
    margin-top: 0.5rem;
    color: black;
}

.cuadrito h2 a {
    color: black;
    text-decoration: none;
}

.cuadrito h2 a:hover {
    color: black;
    text-decoration: none;
    background-color: #DCDCDC;
    border-radius: 10px;
}

.secciones-contenido a {
    text-decoration: none;
    color: black;
}

.secciones-contenido a:hover {
    text-decoration: none;
    color: black;
    background-color: #DCDCDC;
    border-radius: 10px;
}


</style>