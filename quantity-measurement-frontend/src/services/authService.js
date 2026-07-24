import { decodeJwt, isTokenExpired } from "../utils/jwt";

const TOKEN_KEY = "jwt";

// The backend only exposes ONE auth entry point: GET /api/auth/login, which
// 302-redirects the browser to /oauth2/authorization/google. There is no
// password-based login/signup API and no separate "signup" endpoint — Google
// sign-in creates/reuses the account implicitly on the backend. So both
// "Log in" and "Sign up" in this app send the user through the same redirect;
// that mirrors the backend exactly rather than inventing endpoints it doesn't have.
function startGoogleLogin() {
    window.location.href = "/api/auth/login";
}

function getToken() {
    return localStorage.getItem(TOKEN_KEY);
}

function isAuthenticated() {
    const token = getToken();
    return Boolean(token) && !isTokenExpired(token);
}

// The backend's JWT only carries { sub: email, iat, exp } — no name/picture —
// so this is the full identity we can surface without touching the backend.
function getCurrentUser() {
    const token = getToken();
    if (!token) return null;

    const claims = decodeJwt(token);
    if (!claims) return null;

    return {
        email: claims.sub,
        issuedAt: claims.iat ? new Date(claims.iat * 1000) : null,
        expiresAt: claims.exp ? new Date(claims.exp * 1000) : null,
    };
}

function logout() {
    localStorage.removeItem(TOKEN_KEY);
}

const authService = {
    startGoogleLogin,
    getToken,
    isAuthenticated,
    getCurrentUser,
    logout,
};

export default authService;