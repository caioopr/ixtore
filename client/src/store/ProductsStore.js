import { defineStore } from 'pinia';
import { useAuthStore } from './AuthStore';

export const useProductsStore = defineStore('products', {
  state: () => {
    return {
      products: [],
    };
  },
  getters: {
    getProductByCode: (state) => {
      return (productCode) =>
        state.products.find((product) => product.code === productCode);
    },
  },
  actions: {
    async registerProduct(payload) {
      const authStore = useAuthStore();

      const fetchString = `http://localhost:8080/api/products`;
      const bearerToken = `Bearer ${authStore.token}`;

      payload.fk_user_uuid = authStore.user.id;

      const response = await fetch(fetchString, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: bearerToken,
        },
        //referrerPolicy: 'strict-origin-when-cross-origin',
        body: JSON.stringify(payload),
      });

      const responseData = await response.json();

      if (!response.ok) {
        const error = new Error(
          responseData.message || 'Failed to register item.'
        );
        throw error;
      }

      this.products.push(responseData);
    },
    async fetchProductsList() {
      const authStore = useAuthStore();

      const fetchString = `http://localhost:8080/api/products?user_id=${authStore.user.id}`;
      const bearerToken = `Bearer ${authStore.token}`;

      const response = await fetch(fetchString, {
        method: 'GET',
        headers: {
          Authorization: bearerToken,
        },
      });
      const responseData = await response.json();
      if (!response.ok) {
        throw new Error(
          responseData.message || 'Failed to fetch products list.'
        );
      }
      this.products = responseData;
    },
  },
});
