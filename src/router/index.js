import { createRouter, createWebHistory } from 'vue-router'
import LoginContainer from '../components/LoginContainer.vue'

const routes = [
  {path: '/', name: 'LoginContainer', component: LoginContainer}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router