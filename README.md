# Quantity Measurement — Microservices

This project has been split from the original monolith into **3 independent pieces**,
with no change to any backend business logic or frontend UI logic:

```
├── auth-service/        Spring Boot, port 8080  — Google OAuth2 login + JWT issuing
├── quantity-service/     Spring Boot, port 8081  — Quantity measurement business logic + JWT validation
└── frontend/              React + Vite, port 5173 — unchanged UI
```

## 1. auth-service (port 8080)

Contains exactly what used to live in the monolith's auth-related classes:
- `AuthController` → `GET /api/auth/login` (redirects to `/oauth2/authorization/google`)
- `CustomOAuth2UserService`, `OAuth2LoginSuccessHandler` — Google OAuth2 flow
- `JwtUtil` — issues the JWT after successful Google login
- `SecurityConfig` — permits `/api/auth/**`, `/oauth2/**`, `/login/**`, swagger; everything else needs a valid JWT

Your Google Cloud Console OAuth credentials were already registered for
`http://localhost:8080/login/oauth2/code/google`, and since this service keeps
running on **8080**, nothing needs to change there.

Run it:
```bash
cd auth-service
./mvnw spring-boot:run
```

## 2. quantity-service (port 8081)

Contains exactly what used to live in the monolith's quantity-measurement classes:
- `QuantityMeasurementController` (`/api/quantity/**`) — compare, convert, add, subtract, divide, history, errors, counts
- `service`, `unit`, `quantity`, `entity`, `repository`, `model`, `exception` packages — copied byte-for-byte, no logic touched
- `JwtUtil` + `JwtAuthenticationFilter` — validates the JWT issued by auth-service
- `SecurityConfig` — every `/api/quantity/**` request requires a valid `Authorization: Bearer <jwt>` header
- Its own in-memory H2 database (`quantitydb`), same schema as before

**Important:** `jwt.secret` (and `jwt.expiration`) in `quantity-service/src/main/resources/application.properties`
is intentionally identical to auth-service's, so a token minted by auth-service validates here.

Run it:
```bash
cd quantity-service
./mvnw spring-boot:run
```

## 3. frontend (port 5173)

Completely unchanged UI/logic. `node_modules` and `dist` were stripped from this
zip to keep it small — reinstall before running:

```bash
cd frontend
npm install
npm run dev
```

The **only** file touched here is `vite.config.js`, and only its dev-proxy table
— this is required so requests are routed to the right service now that there
are two backends instead of one:
- `/api/auth`, `/oauth2`, `/login` → `http://localhost:8080` (auth-service)
- `/api/quantity` → `http://localhost:8081` (quantity-service)

Nothing else in the frontend (components, pages, services, contexts) was changed.

## Login flow across services

1. Browser hits `GET /api/auth/login` on the frontend → proxied to auth-service (8080).
2. auth-service redirects to Google, handles the OAuth2 callback, and issues a JWT.
3. auth-service redirects the browser to `http://localhost:5173/oauth-success?token=...`.
4. Frontend stores the JWT in `localStorage` (unchanged behavior).
5. Every call to `/api/quantity/**` is proxied to quantity-service (8081), which
   independently validates that same JWT (shared secret) and extracts the user's
   email from it — exactly like the monolith did, just now in a separate process.

## One thing to be aware of

`QuantityMeasurementController` still carries
`@CrossOrigin(origins = "http://localhost:8080")` exactly as it was in the
monolith — untouched, per "don't change backend logic". In local dev this is
harmless because the Vite proxy makes requests look same-origin to the browser.
If you ever call quantity-service (8081) directly from a browser without the
Vite proxy in front of it, you may want to update that origin to match wherever
the frontend is actually served from.
