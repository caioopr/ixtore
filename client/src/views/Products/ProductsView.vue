<script setup>
import ProductItem from '@/components/ProductItem.vue';
import { useProductsStore } from '@/store/ProductsStore';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';

const isLoading = ref(false);
const error = ref(null);

const productsStore = useProductsStore();
const {products} = storeToRefs(productsStore);

loadProducts();



async function loadProducts(){

  error.value = null;
  isLoading.value = true;
  try {
    await productsStore.fetchProductsList();
  } catch(err){
    console.log(err)
    error.value = err || 'Failed to fetch products';
  }
  isLoading.value = false;
}

</script>

<template>
  <div class="flex md:w-3/5 lg:w-2/5 min-h-full flex-1 flex-col justify-center mx-0 md:mx-auto mt-10 px-3 py-2">
    <div class="flex flex-row justify-between items-center pb-4">
      <h2 class="text-lg md:text-2xl font-bold leading-9 tracking-tight text-gray-900">Products</h2>
      <button>refresh</button>
    </div>
    <div class="flex flex-row justify-between items-center gap-2 pb-4">
      <button>filter</button>
      <button>add +</button>
    </div>
    <section>
      <div v-if="isLoading === true">
          Loading...
      </div>
      <div v-else-if="products.length > 0">
        <product-item  v-for="product in products" :code="product.code" :name="product.name"></product-item>
      </div>
      <div v-else>
        <p>No products found. Click in the "add" button to register a new one.</p> 
      </div>
    </section>
  </div>
</template>

<style scoped></style>