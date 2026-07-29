import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      // Auth microservice (Google OAuth2 login + JWT issuing) - port 8080
      "/api/auth": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
      "/oauth2": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
      "/login": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
      // Quantity microservice (business logic + JWT validation) - port 8081
      "/api/quantity": {
        target: "http://localhost:8081",
        changeOrigin: true,
      },
    },
  },
});