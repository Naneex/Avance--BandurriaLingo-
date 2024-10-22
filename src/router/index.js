import { createRouter, createWebHistory } from 'vue-router'
import ChatWeb from "../views/ChatWeb.vue";

const routes = [
  { path: '/chat', component: ChatWeb},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router