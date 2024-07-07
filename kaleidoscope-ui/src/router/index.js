import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Layout from "@/views/Layout.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'layout',
    component: Layout,
    redirect: '/',
    children: [
      {
        path: '/',
        name: 'home',
        component: HomeView
      },
      {
        path: '/generator',
        name: 'home',
        component: () => import(/* webpackChunkName: "about" */ '../views/Generator.vue')
      },
    ]
  }
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
