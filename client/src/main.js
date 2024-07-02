import './assets/style.css';

import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';
import { useAuthStore } from './store/AuthStore';

const pinia = createPinia();
const app = createApp(App);

app.use(router);
app.use(pinia);

const authStore = useAuthStore();

router.beforeEach(function (to, _, next) {
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next('/signin');
  }
  //else if (to.meta.requiresUnauth && !authStore.isAuthenticated) {
  //  next();
  //}
  else {
    next();
  }
});

app.mount('#app');
