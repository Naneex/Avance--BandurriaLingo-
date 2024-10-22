import { createRouter, createWebHistory } from 'vue-router';
import ForoBandurria from '../components/ForoBandurria.vue';
import ForoEjemplo from '../components/ForoEjemplo.vue';
import QuizPrincipal from '@/components/QuizPrincipal.vue';
import QuizCorrect from '@/components/QuizCorrect.vue';
import QuizIncorrect from '@/components/QuizIncorrect.vue';

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
  },
  {
    path: '/quiz/principal',
    name: 'QuizPrincipal',
    component: QuizPrincipal,
  },
  {
    path: '/quiz/correct',
    name: 'QuizCorrect',
    component: QuizCorrect,
  },
  {
    path: '/quiz/incorrect',
    name: 'QuizIncorrect',
    component: QuizIncorrect,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
