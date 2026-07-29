import { useEffect, useState } from "react";
import { Navigate, useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import Card from "../components/Card";
import Button from "../components/Button";
import "./Auth.css";

export default function Login() {
    const { isAuthenticated, loginWithGoogle, refresh } = useAuth();
    const location = useLocation();
    const navigate = useNavigate();
    const [redirecting, setRedirecting] = useState(false);

    // After the backend's OAuth2LoginSuccessHandler writes the JWT to
    // localStorage and redirects to /index.html, the app picks it up here
    // and moves straight to the dashboard — no extra API calls needed.
    useEffect(() => {
        refresh();
    }, [refresh]);

    if (isAuthenticated) {
        const from = location.state?.from?.pathname || "/dashboard";
        return <Navigate to={from} replace />;
    }

    const handleGoogleLogin = () => {
        setRedirecting(true);
        loginWithGoogle();
    };

    return (
        <div className="auth-screen">
            <div className="auth-screen__side">
                <div className="tick-rule">
                    {Array.from({ length: 24 }).map((_, i) => (
                        <span key={i} />
                    ))}
                </div>
                <h1 className="auth-screen__title">
                    Precise conversions.
                    <br />
                    Every unit, every time.
                </h1>
                <p className="auth-screen__copy">
                    Compare, convert, add, subtract, and divide across length, weight,
                    volume, and temperature — backed by a calculation history you can
                    always trace back through.
                </p>
            </div>

            <div className="auth-screen__form">
                <Card className="auth-card">
                    <span className="auth-card__eyebrow">Welcome back</span>
                    <h2 className="auth-card__title">Log in to Quantify</h2>
                    <p className="auth-card__copy">
                        This app signs you in with your Google account — the backend
                        issues a secure session token as soon as Google confirms who you
                        are, no separate password to remember.
                    </p>

                    <Button
                        variant="primary"
                        size="lg"
                        fullWidth
                        loading={redirecting}
                        onClick={handleGoogleLogin}
                        icon={<GoogleIcon />}
                    >
                        Continue with Google
                    </Button>

                    <p className="auth-card__footnote">
                        New here? Continuing with Google creates your account
                        automatically — there's nothing separate to sign up for.
                    </p>

                    <button
                        type="button"
                        className="auth-card__link"
                        onClick={() => navigate("/signup")}
                    >
                        Learn how sign up works →
                    </button>
                </Card>
            </div>
        </div>
    );
}

function GoogleIcon() {
    return (
        <svg width="16" height="16" viewBox="0 0 18 18" aria-hidden="true">
            <path
                fill="#4285F4"
                d="M17.64 9.2c0-.64-.06-1.25-.16-1.84H9v3.48h4.84a4.14 4.14 0 0 1-1.8 2.72v2.26h2.9c1.7-1.57 2.7-3.87 2.7-6.62z"
            />
            <path
                fill="#34A853"
                d="M9 18c2.43 0 4.47-.8 5.96-2.18l-2.9-2.26c-.8.54-1.84.86-3.06.86-2.35 0-4.34-1.59-5.05-3.72H.96v2.33A9 9 0 0 0 9 18z"
            />
            <path
                fill="#FBBC05"
                d="M3.95 10.7A5.4 5.4 0 0 1 3.67 9c0-.59.1-1.17.28-1.7V4.97H.96A9 9 0 0 0 0 9c0 1.45.35 2.83.96 4.03l2.99-2.33z"
            />
            <path
                fill="#EA4335"
                d="M9 3.58c1.32 0 2.5.46 3.44 1.35l2.58-2.58C13.46.89 11.43 0 9 0A9 9 0 0 0 .96 4.97l2.99 2.33C4.66 5.17 6.65 3.58 9 3.58z"
            />
        </svg>
    );
}