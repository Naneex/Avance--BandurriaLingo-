import { createRouter, createWebHistory } from 'vue-router';
import ForoBandurria from '../components/ForoBandurria.vue';
import ForoEjemplo from '../components/ForoEjemplo.vue';

const routes = [
  {
    path: '/Foro',
    name: 'ForoBandurria',
    component: ForoBandurria
  },
  {
    path: '/foroejemplo',
    name: 'ForoEjemplo',
    component: ForoEjemplo
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
