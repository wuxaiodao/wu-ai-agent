import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import LoveApp from '../views/LoveApp.vue'
import SuperAgent from '../views/SuperAgent.vue'

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/love', name: 'LoveApp', component: LoveApp },
  { path: '/agent', name: 'SuperAgent', component: SuperAgent }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
