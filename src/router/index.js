import { createRouter, createWebHistory } from 'vue-router'
import LoginContainer from '../components/LoginContainer.vue'
import RegisterContainer from '../components/RegisterContainer.vue'
import MainMenu from '../components/MainMenu.vue'
import RankingContainer from '../components/RankingContainer.vue'
import ParesGame from '../components/ParesGame.vue'
import ProfileContainer from '../components/ProfileContainer.vue'
import RetoDiario from "../views/RetoDiario.vue";
import FlashCardMenu from "../views/FlashCardMenu.vue";
import ChatWeb from "../views/ChatWeb.vue";
import StudyFlashCard from "../views/StudyFlashCard.vue";
import ForoBandurria from '../components/ForoBandurria.vue';
import ForoEjemplo from '../components/ForoEjemplo.vue';
import QuizPrincipal from '../components/QuizPrincipal.vue';
import QuizCorrect from '../components/QuizCorrect.vue';
import QuizIncorrect from '../components/QuizIncorrect.vue';


const routes = [
  {path: '/login', name: 'LoginContainer', component: LoginContainer},
  {path : '/register', name: 'RegisterContainer', component: RegisterContainer},
  {path: '/main', name: 'MainMenu', component: MainMenu},
  {path: '/ranking', name: 'RankingContainer', component: RankingContainer},
  {path: '/pares', name: 'ParesGame', component: ParesGame},
  {path: '/profile/:id', name: 'ProfileContainer', component: ProfileContainer},
  {path: '/chat', component: ChatWeb},
  {path: '/retodiario', component: RetoDiario},
  {path: '/flashcards', component: FlashCardMenu},
  {path: '/flashcards/estudiar/:categoria', name: 'Estudiar', component: () => import('../views/StudyFlashCard.vue')},
  {path: '/flashcards/crear/:categoria', name: 'Crear', component: () => import('../views/CrearFlashCard.vue')},
  {path: '/chat/:id_usuario', component: ChatWeb, props: true },
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
]

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
