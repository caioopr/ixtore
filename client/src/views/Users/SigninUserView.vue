<script setup>
import router from '@/router';
import { useAuthStore } from '@/store/AuthStore';
import { ref } from 'vue';

const authStore = useAuthStore();
const email = ref('');
const password = ref('');
const formIsValid = ref(true);
const isLoading = ref(false);
const error = ref(null);

// TODO: make a hook
async function submitForm(){
  error.value = null;
  validateForm()
  if (!formIsValid){
    return;
  }

  isLoading.value = true;

  const payload = {
    email: email.value,
    password: password.value
  }

  try {
    await authStore.signin(payload);
    router.replace({ name: 'products' });
  } catch(err){
    error.value = err.message || 'Failed to sign in.';
  }

  isLoading.value = false;

}

function validateForm(){
  formIsValid.value = true;
  if (
    email.value === '' ||
    !email.value.includes('@') || 
    password.value.length < 8
  ) {
    formIsValid.value = false;
    return;
  }
} 

</script>

<template>
  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-4 md:py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">
      <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Sign in to your account</h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <form @submit.prevent="submitForm" class="space-y-6">
        <div>
          <label for="email" class="block text-sm font-medium leading-6 text-gray-900">Email address</label>
          <div class="mt-1.5">
            <input v-model.trim="email" id="email" name="email" type="email" autocomplete="email" required="true"/>
          </div>
        </div>

        <div>
            <label for="password" class="block text-sm font-medium leading-6 text-gray-900">Password</label>
          <div class="my-1.5">
            <input v-model="password" id="password" name="password" type="password" autocomplete="current-password" required="true"/>
          </div>
          <div class="text-sm">
            <a href="#" class="font-semibold text-indigo-600 hover:text-indigo-500">Forgot password?</a>
          </div>
        </div>

        <div>
          <button type="submit"
            class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Sign in</button>
        </div>
      </form>
      <p v-if="error != null" class="mt-6 md:mt-6 text-center text-sm text-red-500">{{ error }}</p>

      <p class="mt-6 md:mt-10 text-center text-sm text-gray-500">
        New here?
        {{ ' ' }}
        <router-link to="/register" class="font-semibold leading-6 text-indigo-600 hover:text-indigo-500">Create an account</router-link>
    </p>
  </div>
</div>
</template>


<style scoped>
input {
  @apply block w-full rounded-md border-0 py-1.5 px-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
}
</style>