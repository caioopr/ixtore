<script setup>
import {useAuthStore} from  '@/store/AuthStore';
import { storeToRefs } from 'pinia';


const authStore = useAuthStore();

const {user} = storeToRefs(authStore);
// TODO: add a proper router-link and dividers

</script>



<template>
<div class="flex min-h-full flex-1 flex-col justify-center px-6 py-4 md:py-12 lg:px-8">
  <div class="sm:mx-auto sm:w-full sm:max-w-sm">
    <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Profile</h2>
  </div>

  <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
    <div class="divide-y">
        <div>
          <p class="title">Username</p>
          <p>{{ user.name }}</p>
        </div>
      <div>
        <p class="title">Email</p>
        <p>{{  user.email }}</p>
      </div>
      <div>
        <p class="title">Role</p>
        <p class="capitalize">{{ user.role }}</p>
      </div>
    </div>
    <div class="mt-4 divide-y">
      <div>
        <router-link to="/">Update info</router-link>
      </div>
      <!-- add a divider like --or-- -->
      <div>
        <router-link to="/">Change password</router-link> 
      </div>
    </div>
  </div>
</div>
</template>

<style scoped>
  a {
    @apply flex w-full justify-center rounded-md bg-indigo-600 my-1.5 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600
  }

  .title {
    color: rgb(156 163 175);
  }
</style>