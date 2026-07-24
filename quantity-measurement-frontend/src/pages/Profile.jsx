import { useAuth } from "../context/AuthContext";
import Card from "../components/Card";
import "./Profile.css";

// The backend doesn't expose a /api/auth/me or user-profile endpoint — the
// JWT only carries { sub: email, iat, exp } (see OAuth2LoginSuccessHandler /
// JwtUtil). Rather than invent a backend call that doesn't exist, this page
// surfaces exactly that: the identity and session info already available
// client-side from the token, presented as a real profile view.
export default function Profile() {
    const { user } = useAuth();

    const initial = user?.email?.[0]?.toUpperCase() || "?";

    return (
        <div className="profile">
            <header>
                <span className="eyebrow">Profile</span>
                <h1 className="profile__title">Your Profile</h1>
                <p className="profile__subtitle">
                    Account details available from your current session.
                </p>
            </header>

            <Card className="profile__card">
                <div className="profile__avatar">{initial}</div>
                <div>
                    <h2 className="profile__email">{user?.email || "Unknown"}</h2>
                    <p className="profile__hint">Signed in with Google</p>
                </div>
            </Card>

            <Card className="profile__details">
                <h3 className="profile__details-title">Session</h3>
                <dl className="profile__list">
                    <div className="profile__row">
                        <dt>Email</dt>
                        <dd className="mono">{user?.email || "—"}</dd>
                    </div>
                    <div className="profile__row">
                        <dt>Session started</dt>
                        <dd className="mono">
                            {user?.issuedAt ? user.issuedAt.toLocaleString() : "—"}
                        </dd>
                    </div>
                    <div className="profile__row">
                        <dt>Session expires</dt>
                        <dd className="mono">
                            {user?.expiresAt ? user.expiresAt.toLocaleString() : "—"}
                        </dd>
                    </div>
                    <div className="profile__row">
                        <dt>Auth provider</dt>
                        <dd>Google OAuth 2.0</dd>
                    </div>
                </dl>
            </Card>

            <p className="profile__note">
                The backend doesn't currently expose a detailed user-profile API
                (name, avatar, saved preferences), so this view only shows what's
                available from your sign-in session. It'll grow automatically if
                that endpoint is added later.
            </p>
        </div>
    );
}