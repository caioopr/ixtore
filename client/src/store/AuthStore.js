import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => {
    return {
      user: {
        id: null,
        name: null,
        email: null,
        role: null,
      },
      isAuthenticated: false,
      token: null,
    };
  },
  // actions can be async
  actions: {
    async signin(payload) {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ',
        },
        referrerPolicy: 'strict-origin-when-cross-origin',
        body: JSON.stringify(payload),
      });

      const responseData = await response.json();

      if (!response.ok) {
        const error = new Error(
          responseData.message ||
            'Failed to authenticate. Check your login data.'
        );
        throw error;
      }

      // const {userInfo, token}: {userInfo:{...}, token: String} = responseData;
      const { userInfo, token } = responseData;

      this.user.id = userInfo.user_uuid;
      this.user.name = userInfo.name;
      this.user.email = userInfo.email;
      this.user.role = userInfo.role;
      this.token = token;
      this.isAuthenticated = true;
    },

    async register(payload) {
      const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ',
        },
        body: JSON.stringify({
          name: payload.name,
          email: payload.email,
          password: payload.password,
          role: 'USER',
        }),
      });

      if (!response.ok) {
        throw new Error('Failed to create account.');
      }
    },

    logout() {
      this.$reset();
    },
  },
});
