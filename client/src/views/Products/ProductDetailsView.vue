<script setup>
import { useProductsStore } from '@/store/ProductsStore';
import { storeToRefs } from 'pinia';
import { computed } from 'vue';
import { useRoute } from 'vue-router';

  
  const route = useRoute();

  const productsStore = useProductsStore();
  const product = productsStore.getProductByCode(route.params.id);

</script>
<template>
  <section>
    <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-4 md:py-12 lg:px-8">
      <div class="sm:mx-auto sm:w-full sm:max-w-sm">
        <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Details</h2>
      </div>

      <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <div class="divide-y">
            <div>
                <p class="title">Code</p>
                <p>{{ product.code }}</p>
            </div>
            <div>
              <p class="title">Name</p>
              <p>{{ product.name }}</p>
            </div>
          <div>
            <p class="title">Price</p>
            <p>{{ product.price }}</p>
          </div>
          <div>
            <p class="title">Description</p>
            <p>{{ product.description }}</p>
          </div>
          <div>
            <p class="title">Weight</p>
            <p>{{ product.weight }}</p>
          </div>
        </div>
        <div class="mt-4 divide-y">
          <div>
            <router-link to="/">Update</router-link>
          </div>
        <!-- add a divider like --or-- -->
          <div>
            <button>Delete</button> 
          </div>
      </div>
    </div>
  </div>
  </section>
</template>