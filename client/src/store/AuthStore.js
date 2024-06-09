import { defineStore } from 'pinia';
import { computed, ref } from 'vue';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: {
      id: null,
      name: null,
      email: null,
      role: null,
    },
    isAuthenticated: false,
    token: null,
  }),
  // actions can be async
  actions: {
    async signin(payload) {
      const response = await fetch('https://localhost:8080/api/users', {
        method: 'POST',
        body: JSON.stringify({
          email: payload.email,
          password: payload.password,
        }),
      });

      const responseData = await response.json();

      if (!response.ok) {
        console.log(responseData);
        const error = new Error(
          responseData.message ||
            'Failed to authenticate. Check your login data.'
        );
        throw error;
      }

      this.user.id = responseData.user_uuid;
      this.user.name = responseData.name;
      this.user.email = responseData.email;
      this.user.role = responseData.role;
      //this.token = responseData.token;
    },

    async register(payload) {
      const response = await fetch('https://localhost:8080/api/users', {
        method: 'POST',
        body: JSON.stringify({
          name: payload.name,
          email: payload.email,
          password: payload.password,
        }),
      });
    },

    logout() {
      this.$reset();
    },
  },
});
