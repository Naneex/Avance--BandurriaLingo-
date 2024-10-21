import { createRouter, createWebHistory } from 'vue-router'
import LoginContainer from '../components/LoginContainer.vue'
import RegisterContainer from '../components/RegisterContainer.vue'

const routes = [
  {path: '/login', name: 'LoginContainer', component: LoginContainer},
  {path : '/register', name: 'RegisterContainer', component: RegisterContainer}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router