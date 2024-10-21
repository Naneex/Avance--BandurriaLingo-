import { createRouter, createWebHistory } from 'vue-router'
import LoginContainer from '../components/LoginContainer.vue'
import RegisterContainer from '../components/RegisterContainer.vue'
import MainMenu from '../components/MainMenu.vue'

const routes = [
  {path: '/login', name: 'LoginContainer', component: LoginContainer},
  {path : '/register', name: 'RegisterContainer', component: RegisterContainer},
  {path: '/main', name: 'MainMenu', component: MainMenu}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router