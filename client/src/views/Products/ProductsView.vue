<script setup>
import ProductItem from '@/components/ProductItem.vue';
import { useProductsStore } from '@/store/ProductsStore';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';

const isRegisterFormOpen = ref(false);
const formIsValid = ref(true);
const isLoading = ref(false);
const error = ref(null);

const code = ref('');
const name = ref('');
const price = ref(0.0000);
const description = ref('');
const weight = ref(0.0);
const supplier = ref('');

const productsStore = useProductsStore();
const { products } = storeToRefs(productsStore);

loadProducts();

function registerForm() {
  isRegisterFormOpen.value = !(isRegisterFormOpen.value);
}

async function submitForm() {
  error.value = null;
  validateForm();

  if (!formIsValid) {
    return;
  }

  isLoading.value = true

  const payload = {
    code: code.value,
    name: name.value,
    price: price.value,
    description: description.value,
    weight: weight.value,
    fk_user_uuid: "",
    supplier: supplier.value
  }

  try {
    await productsStore.registerProduct(payload);
  } catch (err) {
    error.value = err.message || 'Failed to register product';
  }

  isLoading.value = false
}

async function loadProducts() {

  error.value = null;
  isLoading.value = true;
  try {
    await productsStore.fetchProductsList();
  } catch (err) {
    console.log(err)
    error.value = err || 'Failed to fetch products';
  }
  isLoading.value = false;
}

function validateForm() {
  formIsValid.value = true;
  if (
    code.value === '' || name.value === '' || supplier.value === ''
  ) {
    formIsValid.value = false;
    return;
  }
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
      <button @click="registerForm">add +</button>
    </div>
    <section v-if="isRegisterFormOpen">
      <div>
        <form @submit.prevent="submitForm" method="POST" class="space-y-6">
          <div class="grid sm:grid-cols-1 md:grid-cols-2">
            <div>
              <label for="code" class="block text-sm font-medium leading-6 text-gray-900">Code *</label>
              <div class="mt-1.5">
                <input v-model.trim="code" id="code" name="code" type="text" required="true" />
              </div>
            </div>
            <div>
              <label for="name" class="block text-sm font-medium leading-6 text-gray-900">Name *</label>
              <div class="mt-1.5">
                <input v-model.trim="name" id="name" name="name" type="text" required="true" />
              </div>
            </div>
            <div>
              <label for="price" class="block text-sm font-medium leading-6 text-gray-900">Price</label>
              <div class="mt-1.5">
                <input v-model.trim="price" id="price" name="price" type="number" required="false" />
              </div>
            </div>
            <div>
              <label for="description" class="block text-sm font-medium leading-6 text-gray-900">Description</label>
              <div class="mt-1.5">
                <input v-model.trim="description" id="description" name="description" type="text" required="false" />
              </div>
            </div>
            <div>
              <label for="weight" class="block text-sm font-medium leading-6 text-gray-900">Weight</label>
              <div class="mt-1.5">
                <input v-model.trim="weight" id="weight" name="weight" type="number" required="false" />
              </div>
            </div>
            <div>
              <label for="supplier" class="block text-sm font-medium leading-6 text-gray-900">Supplier *</label>
              <div class="mt-1.5">
                <input v-model.trim="supplier" id="supplier" name="supplier" type="text" required="true" />
              </div>
            </div>
          </div>

          <button type="submit"
            class="flex w-1/4 justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Confirm</button>
        </form>
      </div>
    </section>
    <section class="mt-5">
      <div v-if="isLoading === true">
        Loading...
      </div>
      <div v-else-if="products.length > 0">
        <product-item v-for="product in products" :code="product.code" :name="product.name"></product-item>
      </div>
      <div v-else>
        <p>No products found. Click in the "add" button to register a new one.</p>
      </div>
    </section>
  </div>
</template>

<style scoped>
input {
  border: none;
  border-bottom: 2px solid blue;
}

input {
  @apply w-full md:w-4/5
}
</style>