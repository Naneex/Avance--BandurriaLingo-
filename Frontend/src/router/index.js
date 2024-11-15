import { createRouter, createWebHistory } from 'vue-router';
import LoginContainer from '../components/LoginContainer.vue';
import RegisterContainer from '../components/RegisterContainer.vue';
import MainMenu from '../components/MainMenu.vue';
import RankingContainer from '../components/RankingContainer.vue';
import ParesGame from '../components/ParesGame.vue';
import ProfileContainer from '../components/ProfileContainer.vue';
import RetoDiario from "../views/RetoDiario.vue";
import FlashCardMenu from "../views/FlashCardMenu.vue";
import ChatWeb from "../views/ChatWeb.vue";
import StudyFlashCard from "../views/StudyFlashCard.vue";
import ForoBandurria from '../components/ForoBandurria.vue';
import ForoEjemplo from '../components/ForoEjemplo.vue';
import QuizPrincipal from '../components/QuizPrincipal.vue';
import QuizCorrect from '../components/QuizCorrect.vue';
import QuizIncorrect from '../components/QuizIncorrect.vue';
import ProfileEditContainer from '../components/ProfileEditContainer.vue';

const routes = [
  { path: '/login', name: 'LoginContainer', component: LoginContainer },
  { path: '/register', name: 'RegisterContainer', component: RegisterContainer },
  
  { path: '/main', name: 'MainMenu', component: MainMenu, meta: { requiresAuth: true } },
  { path: '/ranking', name: 'RankingContainer', component: RankingContainer, meta: { requiresAuth: true } },
  { path: '/pares', name: 'ParesGame', component: ParesGame, meta: { requiresAuth: true } },
  { path: '/profile/:id', name: 'ProfileContainer', component: ProfileContainer, meta: { requiresAuth: true } },
  { path: '/profile/:id/editar', component: ProfileEditContainer, meta: { requiresAuth: true } },
  { path: '/chat', component: ChatWeb, meta: { requiresAuth: true } },
  { path: '/retodiario', component: RetoDiario, meta: { requiresAuth: true } },
  { path: '/flashcards', component: FlashCardMenu, meta: { requiresAuth: true } },
  { path: '/flashcards/estudiar/:categoria', name: 'Estudiar', component: StudyFlashCard, meta: { requiresAuth: true } },
  { path: '/flashcards/crear/:categoria', name: 'Crear', component: () => import('../views/CrearFlashCard.vue'), meta: { requiresAuth: true } },
  { path: '/chat/:id_usuario', component: ChatWeb, props: true, meta: { requiresAuth: true } },
  { path: '/Foro', name: 'ForoBandurria', component: ForoBandurria, meta: { requiresAuth: true } },
  { path: '/foroejemplo', name: 'ForoEjemplo', component: ForoEjemplo, meta: { requiresAuth: true } },
  { path: '/quiz/principal', name: 'QuizPrincipal', component: QuizPrincipal, meta: { requiresAuth: true } },
  { path: '/quiz/correct', name: 'QuizCorrect', component: QuizCorrect, meta: { requiresAuth: true } },
  { path: '/quiz/incorrect', name: 'QuizIncorrect', component: QuizIncorrect, meta: { requiresAuth: true } },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  const loggedIn = !!localStorage.getItem('token'); 
  
  if (to.matched.some(record => record.meta.requiresAuth) && !loggedIn) {
    next('/login'); 
  } else {
    next();
  }
});

export default router;
