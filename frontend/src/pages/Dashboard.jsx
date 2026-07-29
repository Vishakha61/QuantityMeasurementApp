import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import quantityService from "../services/quantityService";
import Card from "../components/Card";
import "./Dashboard.css";

const QUICK_LINKS = [
    {
        to: "/calculator",
        icon: "±",
        title: "Quantity Calculator",
        copy: "Compare, convert, add, subtract, or divide across any supported unit.",
    },
    {
        to: "/history",
        icon: "☰",
        title: "Calculation History",
        copy: "Browse, search, and filter every calculation you've run.",
    },
    {
        to: "/profile",
        icon: "◍",
        title: "Profile",
        copy: "View your account details and session information.",
    },
];

export default function Dashboard() {
    const { user } = useAuth();
    const navigate = useNavigate();
    const [now, setNow] = useState(new Date());
    const [stats, setStats] = useState({ total: null, errors: null });

    useEffect(() => {
        const timer = setInterval(() => setNow(new Date()), 1000);
        return () => clearInterval(timer);
    }, []);

    useEffect(() => {
        let cancelled = false;

        Promise.allSettled([
            quantityService.getHistory(),
            quantityService.getErroredOperations(),
        ]).then(([historyRes, errorsRes]) => {
            if (cancelled) return;

            setStats({
                total:
                    historyRes.status === "fulfilled"
                        ? historyRes.value.data.length
                        : null,
                errors:
                    errorsRes.status === "fulfilled"
                        ? errorsRes.value.data.length
                        : null,
            });
        });

        return () => {
            cancelled = true;
        };
    }, []);

    const dateLabel = now.toLocaleDateString(undefined, {
        weekday: "long",
        year: "numeric",
        month: "long",
        day: "numeric",
    });

    const timeLabel = now.toLocaleTimeString(undefined, {
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
    });

    const displayName = user?.email
        ? user.email.split("@")[0]
        : "there";

    return (
        <div className="dashboard">
            <header className="dashboard__header">
                <div>
                    <span className="eyebrow">Dashboard</span>
                    <h1 className="dashboard__title">
                        Welcome back, {displayName}
                    </h1>
                </div>

                <Card className="dashboard__clock">
                    <span className="dashboard__clock-time">
                        {timeLabel}
                    </span>

                    <span className="dashboard__clock-date">
                        {dateLabel}
                    </span>
                </Card>
            </header>

            <section className="dashboard__stats">
                <Card className="dashboard__stat">
                    <span className="dashboard__stat-label">
                        Total calculations
                    </span>

                    <span className="dashboard__stat-value">
                        {stats.total ?? "—"}
                    </span>
                </Card>

                <Card className="dashboard__stat">
                    <span className="dashboard__stat-label">
                        Errored operations
                    </span>

                    <span className="dashboard__stat-value">
                        {stats.errors ?? "—"}
                    </span>
                </Card>

                <Card className="dashboard__stat">
                    <span className="dashboard__stat-label">
                        Session status
                    </span>

                    <span className="dashboard__stat-value dashboard__stat-value--ok">
                        Active
                    </span>
                </Card>
            </section>

            <section>
                <h2 className="dashboard__section-title">
                    Quick navigation
                </h2>

                <div className="dashboard__grid">
                    {QUICK_LINKS.map((link) => (
                        <Card
                            key={link.to}
                            as="button"
                            interactive
                            className="dashboard__link-card"
                            onClick={() => navigate(link.to)}
                        >
                            <span className="dashboard__link-icon">
                                {link.icon}
                            </span>

                            <span className="dashboard__link-title">
                                {link.title}
                            </span>

                            <span className="dashboard__link-copy">
                                {link.copy}
                            </span>
                        </Card>
                    ))}
                </div>
            </section>
        </div>
    );
}