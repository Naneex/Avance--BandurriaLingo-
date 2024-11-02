<template>
  <div class="contenedor">
    <AuthImage />
    <LoginForm />
  </div>
</template>

<script>

import AuthImage from './AuthImage.vue';
import LoginForm from './LoginForm.vue';

export default {
  components: {
    AuthImage,
    LoginForm,
  },
};
</script>

<style>
.contenedor {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #D9E2EC;
}
</style>
