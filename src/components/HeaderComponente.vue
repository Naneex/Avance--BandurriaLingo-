<template>
  <header>
    <div class="logo">
      <h1>Bandurria</h1>
      <p>Lingo</p>
    </div>
    <div class="titulo-foro">
      <h2>Cultural Insights from Native Speakers</h2>
    </div>
    <div class="notificacion">
      <img src="../assets/Notification.png" alt="Notificaciones">
    </div>
  </header>
</template>

<script>
export default {
  name: 'HeaderComponente',
};
</script>

<style scoped>
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #ffffff;
  padding: 15px;
  border-bottom: 1px solid #ddd;
}

.logo {
  display: flex;
  flex-direction: column;
  align-items: center; 
}

.logo h1, .logo p {
  font-size: 30px;
  font-weight: bold;
  margin: 0;
  text-align: center;
}

.titulo-foro h2 {
  font-size: 40px;
  text-align: center;
}

.notificacion img {
  width: 50px;
  height: 50px;
}
</style>
