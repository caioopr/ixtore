import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/sales',
    },
    {
      // TODO: turn /signin and /register into /auth
      path: '/signin',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/Users/SigninUserView.vue'),
    },
    {
      path: '/register',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/Users/RegisterUserView.vue'),
    },
    {
      path: '/current_user',
      component: () => import('../views/Users/CurrentUserView.vue'),
    },
    {
      path: '/products',
      component: null, //() => import('../views/AboutView.vue'),
    },
    {
      path: '/products/:id',
      component: null, //() => import('../views/AboutView.vue'),
    },
    {
      path: '/customers',
      component: null, //() => import('../views/AboutView.vue'),
    },
    {
      path: '/customers/:id',
      component: null, //() => import('../views/AboutView.vue'),
    },
    {
      path: '/sales',
      component: null, //() => import('../views/AboutView.vue'),
    },
    {
      path: '/sales/:id',
      component: null, //() => import('../views/AboutView.vue'),
    },
    {
      path: '/dashboard',
      component: null, //() => import('../views/AboutView.vue'),
    },
    {
      path: '/:notFound(.*)',
      component: null, //() => import('../views/AboutView.vue'),
    },
  ],
});

export default router;
