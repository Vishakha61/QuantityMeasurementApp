import { Navigate, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import Card from "../components/Card";
import Button from "../components/Button";
import "./Auth.css";

// The backend has no /api/auth/signup or password-registration endpoint —
// account creation happens implicitly the first time someone signs in with
// Google (see CustomOAuth2UserService). Rather than fabricate a signup API
// that doesn't exist, this page explains that and routes to the same
// Google flow, so the experience stays honest about what the backend does.
export default function Signup() {
    const { isAuthenticated, loginWithGoogle } = useAuth();
    const navigate = useNavigate();

    if (isAuthenticated) {
        return <Navigate to="/dashboard" replace />;
    }

    return (
        <div className="auth-screen">
            <div className="auth-screen__side">
                <div className="tick-rule">
                    {Array.from({ length: 24 }).map((_, i) => (
                        <span key={i} />
                    ))}
                </div>
                <h1 className="auth-screen__title">
                    One account.
                    <br />
                    Zero passwords.
                </h1>
                <p className="auth-screen__copy">
                    Quantify doesn't keep a password of its own — your Google account
                    is the account. First sign-in creates it, every sign-in after that
                    just confirms it's you.
                </p>
            </div>

            <div className="auth-screen__form">
                <Card className="auth-card">
                    <span className="auth-card__eyebrow">Create your account</span>
                    <h2 className="auth-card__title">Sign up with Google</h2>
                    <p className="auth-card__copy">
                        There's no separate registration form to fill out. Continue with
                        Google once, and Quantify sets up your account automatically —
                        you'll land straight on your dashboard.
                    </p>

                    <Button
                        variant="primary"
                        size="lg"
                        fullWidth
                        onClick={loginWithGoogle}
                    >
                        Continue with Google
                    </Button>

                    <button
                        type="button"
                        className="auth-card__link"
                        onClick={() => navigate("/")}
                    >
                        ← Back to log in
                    </button>
                </Card>
            </div>
        </div>
    );
}