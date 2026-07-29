import { createContext, useContext, useEffect, useState, useCallback } from "react";
import authService from "../services/authService";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
    const [user, setUser] = useState(() => authService.getCurrentUser());
    const [loading, setLoading] = useState(true);

    const refresh = useCallback(() => {
        setUser(authService.isAuthenticated() ? authService.getCurrentUser() : null);
    }, []);

    useEffect(() => {
        refresh();
        setLoading(false);

        // Pick up login/logout that happened in another tab.
        const onStorage = (e) => {
            if (e.key === "jwt") refresh();
        };
        window.addEventListener("storage", onStorage);
        return () => window.removeEventListener("storage", onStorage);
    }, [refresh]);

    const loginWithGoogle = () => {
        authService.startGoogleLogin();
    };

    const logout = () => {
        authService.logout();
        setUser(null);
    };

    const value = {
        user,
        isAuthenticated: Boolean(user),
        loading,
        loginWithGoogle,
        logout,
        refresh,
    };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
    const ctx = useContext(AuthContext);
    if (!ctx) throw new Error("useAuth must be used within an AuthProvider");
    return ctx;
}