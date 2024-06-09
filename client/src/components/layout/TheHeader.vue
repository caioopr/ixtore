<script setup>
import { computed } from 'vue';
import {useAuthStore} from '@/store/AuthStore';

const authStore = useAuthStore();

const isLoggedIn = computed(() => {
  return authStore.isAuthenticated;
})


</script>

<template>
  <header class="w-full h-16 mt-4 md:mt-0 bg-white md:border-b flex justify-center items-center">
    <nav class="w-11/12 m-auto flex flex-col justify-between items-center md:flex-row">
      <h1 class="black bg-transparent text-2xl m-0">
        <router-link to="/signin" >Ixtore</router-link>
      </h1>
      <ul class="list-none w-full md:w-max mt-4 md:m-0 p-0 flex flex-row justify-center items-center">
        <li v-if="isLoggedIn">
            <button @click="authStore.logout">Logout</button>
        </li>
        <li v-if="!isLoggedIn">
              <router-link to="/signin" class="link">Sign in</router-link>
        </li>
        <li v-if="!isLoggedIn">
                <router-link to="/register" class="link">Register</router-link>
          </li>
      </ul>
    </nav>
  </header>
</template>



<style scoped>

.link, button{
  @apply no-underline inline-block rounded-md py-1 px-2 md:py-3 md:px-6 text-white bg-indigo-600 hover:bg-indigo-500 focus-visible:outline  
}

.link:active,
.link.router-link-active {
  @apply bg-indigo-400 
}



li {
  margin: 0 0.5rem;
}
</style>