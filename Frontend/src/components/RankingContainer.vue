<template>
  <div class="ranking-container">
    <HeaderComponent nombre_pag="Ranking" />
    <TopRanking />
    <RankingList />
  </div>
</template>

<script>
import TopRanking from './TopRanking.vue';
import RankingList from './RankingList.vue';
import HeaderComponent from './HeaderComponent.vue';

export default {
  name: 'RankingContainer',
  components: {
    TopRanking,
    RankingList,
    HeaderComponent,
  },
};
</script>

<style scoped>
* {
    font-family: monospace;
}

.ranking-container {
  display: flex;
}

</style>
