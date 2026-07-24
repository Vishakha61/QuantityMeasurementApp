import { Link } from "react-router-dom";

export default function NotFound() {
    return (
        <div
            style={{
                minHeight: "100vh",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                gap: 12,
                textAlign: "center",
                padding: 24,
            }}
        >
            <h1 style={{ fontSize: 48, margin: 0 }}>404</h1>
            <p style={{ color: "var(--ink-soft)" }}>This page doesn't exist.</p>
            <Link to="/dashboard" style={{ color: "var(--teal)", fontWeight: 600 }}>
                Back to dashboard →
            </Link>
        </div>
    );
}