import axios from "axios";

// In dev, Vite proxies /api to http://localhost:8080 (see vite.config.js).
// In prod, point VITE_API_BASE_URL at wherever the Spring Boot app is deployed.
const baseURL = import.meta.env.VITE_API_BASE_URL || "";

const api = axios.create({
    baseURL,
    headers: {
        "Content-Type": "application/json",
    },
});

// Attach the JWT (stored under "jwt", exactly as the backend's
// OAuth2LoginSuccessHandler writes it to localStorage) to every request.
api.interceptors.request.use((config) => {
    const token = localStorage.getItem("jwt");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

// Centralize error shaping so every page gets a friendly, consistent message
// without changing anything about what the backend actually returns.
api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (!error.response) {
            error.friendlyMessage =
                "Can't reach the server. Check your connection and try again.";
            return Promise.reject(error);
        }

        const { status, data } = error.response;

        if (status === 401) {
            localStorage.removeItem("jwt");
            error.friendlyMessage = "Your session has expired. Please log in again.";
            if (window.location.pathname !== "/") {
                window.location.href = "/";
            }
        } else if (status === 403) {
            error.friendlyMessage = "You don't have permission to do that.";
        } else if (status === 404) {
            error.friendlyMessage = "We couldn't find what you were looking for.";
        } else if (status >= 500) {
            error.friendlyMessage = "Something went wrong on our end. Please try again.";
        } else {
            error.friendlyMessage =
                (typeof data === "string" && data) ||
                data?.message ||
                data?.error ||
                "That request couldn't be completed. Please check your input.";
        }

        return Promise.reject(error);
    }
);

export default api;