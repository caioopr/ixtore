import { defineStore } from 'pinia';

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
      console.log(payload);
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ',
        },
        referrerPolicy: 'strict-origin-when-cross-origin',
        body: JSON.stringify(payload),
      });

      console.log(response);
      const responseData = await response.json();

      if (!response.ok) {
        console.log(response);
        const error = new Error(
          responseData.message ||
            'Failed to authenticate. Check your login data.'
        );
        throw error;
      }

      console.log(responseData);
      const { userInfo, token } = responseData;

      this.user.id = userInfo.user_uuid;
      this.user.name = userInfo.name;
      this.user.email = userInfo.email;
      this.user.role = userInfo.role;
      this.token = token;
    },

    async register(payload) {
      const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        body: JSON.stringify({
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
