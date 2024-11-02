<template>
    <HeaderComponent nombre_pag="Perfil" />
  <div class="profile-container">
    <ProfileEdit/>
    <Comms />
  </div>
</template>

<script>
import Comms from './Comms.vue';
import HeaderComponent from './HeaderComponent.vue';
import ProfileBadges from './ProfileBadges.vue';
import ProfileEdit from './ProfileEdit.vue';

export default {
 
  components: {
    ProfileBadges,
    ProfileEdit,
    Comms,
    HeaderComponent
  }
};
</script>

<style scoped>
.profile-container {
  display: flex;
}
</style>
