import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import ConfirmMail from '../views/ConfirmMail.vue'
import ChangePassword from '../views/ChangePassword.vue'
import UserProfile from '@/views/UserProfile.vue'
import ForoUsuarioLogeado from '@/views/ForoUsuarioLogeado.vue'

const routes = [
  { 
    path: '/',
    name: 'home',
    component: () => import('../views/ForoUsuarioDefault.vue')
  },
  {
    name: 'login',
    path: '/login',
    component: LoginView
  },
  {
    name: 'register',
    path: '/register',
    component: RegisterView
  },
  {
    name: 'confirmMail',
    path: '/confirm-mail',
    component: ConfirmMail
  },
  {
    name: 'changePassword',
    path: '/change-password',
    component: ChangePassword
  },
  {
    name: 'foro',
    path: '/foro',
    component: ForoUsuarioLogeado
  },
  {
    name: 'profile',
    path: '/profile/:id',
    component: UserProfile
  },
  { 
    path: '/ForoDefault',
    name: 'foroDefault',
    component: () => import('../views/ForoUsuarioDefault.vue')
  },
  { 
    path: '/verPublicacion/:postId',
    name: 'verPublicacion',
    component: () => import('../views/VerPublicacion.vue'),
    props: true,
  },
  { 
    path: '/popular',
    name: 'popularPublicacion',
    component: () => import('../views/PublicacionesPopulares.vue'),
    props: true,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
