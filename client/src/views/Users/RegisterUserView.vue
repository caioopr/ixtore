<script setup>
import { useAuthStore } from '@/store/AuthStore';
import { ref } from 'vue';
import router from '@/router';

const authStore = useAuthStore();
const username = ref('');
const email = ref('');
const password = ref('');
const formIsValid = ref(true);
const isLoading = ref(false);
const error = ref(null);

async function submitForm() {
  error.value = null;
  validateForm()
  if (!formIsValid) {
    return;
  }

  isLoading.value = true;

  const payload = {
    name: username.value,
    email: email.value,
    password: password.value
  }

  try {
    await authStore.register(payload);
    router.replace({ name: 'signin' });
  } catch (err) {
    error.value = err.message || 'Failed to create account';
  }

  isLoading.value = false;

}

function validateForm() {
  formIsValid.value = true;
  if (
    username.value === '' || email.value === '' ||
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
      <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Create an account</h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <form @submit.prevent="submitForm" method="POST" class="space-y-6">
        <div>
          <label for="username" class="block text-sm font-medium leading-6 text-gray-900">Username</label>
          <div class="mt-1.5">
            <input v-model.trim="username" id="username" name="username" type="text" autocomplete="username"
              required="true" />
          </div>
        </div>
        <div>
          <label for="email" class="block text-sm font-medium leading-6 text-gray-900">Email address</label>
          <div class="mt-1.5">
            <input v-model.trim="email" id="email" name="email" type="email" autocomplete="email" required="true" />
          </div>
        </div>

        <div>
          <div class="flex items-center justify-between">
            <label for="password" class="block text-sm font-medium leading-6 text-gray-900">Password</label>
          </div>
          <div class="mt-1.5">
            <input v-model.trim="password" id="password" name="password" type="password" autocomplete="current-password"
              required="true" />
          </div>
        </div>

        <div>
          <button type="submit"
            class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Register</button>
        </div>
      </form>

      <p class="mt-6 md:mt-10 text-center text-sm text-gray-500">
        Alredy have an account?
        {{ ' ' }}
        <router-link to="/signin" class="font-semibold leading-6 text-indigo-600 hover:text-indigo-500">Sign
          in</router-link>
      </p>
    </div>
  </div>
</template>

<style scoped>
input {
  @apply block w-full rounded-md border-0 py-1.5 px-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
}
</style>