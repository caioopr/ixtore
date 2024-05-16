<script setup>
import { RouterView } from 'vue-router'
import TheHeader from './components/layout/TheHeader.vue';
</script>

<template>
  <the-header></the-header>
  <router-view></router-view>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap");

* {
  box-sizing: border-box;
}

html {
  font-family: "Roboto", sans-serif;
  height: 100%;
}

body {
  margin: 0;
  height: 100%;
}
</style>
