<template>
    <div class="contenedor">
      <AuthImage />
      <RegisterForm />
    </div>
</template>
  
<script>
  
  import AuthImage from './AuthImage.vue';
    import RegisterForm from './RegisterForm.vue';
  
  export default {
    components: {
      AuthImage,
      RegisterForm,
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
  