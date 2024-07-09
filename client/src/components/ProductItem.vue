<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';

const props = defineProps({ code: String, name: String });

const route = useRoute();

const productDetailsLink = computed(()=>{
    return route.path + '/' + props.code; // /products/09830238402
})

</script>

<template>
  <div class="flex flex-row justify-between items-center">
        <div>
          <p class="text-gray-400">{{ props.code }}</p>
          <h2>{{ props.name }}</h2>
        </div>

        <router-link :to="productDetailsLink">Details ></router-link>
      <!-- <div>
        <p>Price</p>
        <p>Weight</p>
        <p>Supplier</p>
        <p>Created at</p>
        <p>Description</p>
      </div> -->
  </div>
</template>

<style scoped>

</style>