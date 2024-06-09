<script>
const example = {
  name: "John Doe",
  email:"doe@email.com",
  role: "admin"
}
</script>

<template>
<div class="flex min-h-full flex-1 flex-col justify-center px-6 py-4 md:py-12 lg:px-8">
  <div class="sm:mx-auto sm:w-full sm:max-w-sm">
    <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Profile</h2>
  </div>

  <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
    <ul role="list">
      <li>
        name
      </li>
      <li>
        email
      </li>
      <li>
        role
      </li>
    </ul>
    <p>name email role</p>
    <router-link>update</router-link>
    <router-link>change password</router-link>
  </div>
</div>
</template>

<style scoped>
</style>