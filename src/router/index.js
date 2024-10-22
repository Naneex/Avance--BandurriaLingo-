import { createRouter, createWebHistory } from 'vue-router'
import ChatWeb from "../views/ChatWeb.vue";

const routes = [
  { path: '/chat', component: ChatWeb},
  { path: '/retodiario', component: RetoDiario},
  { path: '/flashcards', component: FlashCardMenu},
  { path: '/chat', component: ChatWeb},
  { path: '/flashcards/estudiar/:categoria', name: 'Estudiar', component: () => import('../views/StudyFlashCard.vue')},
  { path: '/flashcards/crear/:categoria', name: 'Crear', component: () => import('../views/CrearFlashCard.vue')},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router