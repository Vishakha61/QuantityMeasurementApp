// Minimal JWT payload decoder — no network/backend calls, no library needed.
// The backend only signs { sub: email, iat, exp }, so this is all we need
// to know who is logged in and whether the token has expired.

export function decodeJwt(token) {
    if (!token) return null;

    try {
        const payload = token.split(".")[1];
        const normalized = payload.replace(/-/g, "+").replace(/_/g, "/");
        const padded = normalized.padEnd(
            normalized.length + ((4 - (normalized.length % 4)) % 4),
            "="
        );
        const json = decodeURIComponent(
            atob(padded)
                .split("")
                .map((c) => "%" + c.charCodeAt(0).toString(16).padStart(2, "0"))
                .join("")
        );
        return JSON.parse(json);
    } catch (err) {
        return null;
    }
}

export function isTokenExpired(token) {
    const claims = decodeJwt(token);
    if (!claims || !claims.exp) return true;
    return Date.now() >= claims.exp * 1000;
}