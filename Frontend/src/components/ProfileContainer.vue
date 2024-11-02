<template>
    <HeaderComponent nombre_pag="Perfil" />
  <div class="profile-container">
    <ProfileInfo/>
    <Comms />
  </div>
</template>

<script>
import Comms from './Comms.vue';
import HeaderComponent from './HeaderComponent.vue';
import ProfileBadges from './ProfileBadges.vue';
import ProfileInfo from './ProfileInfo.vue';

export default {
 
  components: {
    ProfileBadges,
    ProfileInfo,
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
