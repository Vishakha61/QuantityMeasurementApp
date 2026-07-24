import { useEffect, useRef, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useTheme } from "../context/ThemeContext";
import "./Navbar.css";

const NAV_ITEMS = [
    { to: "/dashboard", label: "Dashboard" },
    { to: "/calculator", label: "Calculator" },
    { to: "/history", label: "History" },
];

export default function Navbar() {
    const { user, logout } = useAuth();
    const { theme, toggleTheme } = useTheme();
    const navigate = useNavigate();

    const [menuOpen, setMenuOpen] = useState(false);
    const menuRef = useRef(null);

    useEffect(() => {
        function handleClickOutside(e) {
            if (menuRef.current && !menuRef.current.contains(e.target)) {
                setMenuOpen(false);
            }
        }
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

    const handleLogout = () => {
        setMenuOpen(false);
        logout();
        navigate("/");
    };

    const initial = user?.email?.[0]?.toUpperCase() || "?";
    const isDark = theme === "dark";

    return (
        <header className="topnav">
            <div className="topnav__brand">
        <span className="topnav__mark" aria-hidden="true">
          ⟟
        </span>
                <div className="topnav__brand-text">
                    <span className="topnav__brand-name">Quantity Measurement</span>
                    <span className="topnav__brand-sub">
            Spring Boot • REST API • React
          </span>
                </div>
            </div>

            <nav className="topnav__pills">
                {NAV_ITEMS.map((item) => (
                    <NavLink
                        key={item.to}
                        to={item.to}
                        className={({ isActive }) =>
                            `topnav__pill ${isActive ? "topnav__pill--active" : ""}`
                        }
                    >
                        {item.label}
                    </NavLink>
                ))}
            </nav>

            <div className="topnav__right">
                <button
                    type="button"
                    className="topnav__theme-toggle"
                    onClick={toggleTheme}
                    aria-label="Toggle dark mode"
                >
                    {isDark ? <SunIcon /> : <MoonIcon />}
                    <span>{isDark ? "Light" : "Dark"}</span>
                </button>

                <div className="topnav__profile" ref={menuRef}>
                    <button
                        type="button"
                        className="topnav__avatar-btn"
                        onClick={() => setMenuOpen((open) => !open)}
                        aria-haspopup="true"
                        aria-expanded={menuOpen}
                    >
                        <span className="topnav__avatar">{initial}</span>
                    </button>

                    {menuOpen && (
                        <div className="topnav__menu">
                            <div className="topnav__menu-header">
                <span className="topnav__avatar topnav__avatar--lg">
                  {initial}
                </span>
                                <div>
                                    <span className="topnav__menu-name">Signed in as</span>
                                    <span className="topnav__menu-email" title={user?.email}>
                    {user?.email || "Unknown"}
                  </span>
                                </div>
                            </div>

                            <button
                                type="button"
                                className="topnav__menu-item"
                                onClick={() => {
                                    setMenuOpen(false);
                                    navigate("/profile");
                                }}
                            >
                                <ProfileIcon />
                                View Profile
                            </button>

                            <button
                                type="button"
                                className="topnav__menu-item topnav__menu-item--danger"
                                onClick={handleLogout}
                            >
                                <LogoutIcon />
                                Logout
                            </button>
                        </div>
                    )}
                </div>
            </div>
        </header>
    );
}

function SunIcon() {
    return (
        <svg width="15" height="15" viewBox="0 0 24 24" fill="none" aria-hidden="true">
            <circle cx="12" cy="12" r="4.5" stroke="currentColor" strokeWidth="1.8" />
            <path
                stroke="currentColor"
                strokeWidth="1.8"
                strokeLinecap="round"
                d="M12 2.5v2M12 19.5v2M4.2 4.2l1.4 1.4M18.4 18.4l1.4 1.4M2.5 12h2M19.5 12h2M4.2 19.8l1.4-1.4M18.4 5.6l1.4-1.4"
            />
        </svg>
    );
}

function MoonIcon() {
    return (
        <svg width="15" height="15" viewBox="0 0 24 24" fill="none" aria-hidden="true">
            <path
                fill="currentColor"
                d="M20.5 14.5A8.5 8.5 0 0 1 9.5 3.5a8.5 8.5 0 1 0 11 11z"
            />
        </svg>
    );
}

function ProfileIcon() {
    return (
        <svg width="15" height="15" viewBox="0 0 24 24" fill="none" aria-hidden="true">
            <circle cx="12" cy="8" r="3.4" stroke="currentColor" strokeWidth="1.7" />
            <path
                stroke="currentColor"
                strokeWidth="1.7"
                strokeLinecap="round"
                d="M4.8 20c1.2-3.6 4-5.6 7.2-5.6s6 2 7.2 5.6"
            />
        </svg>
    );
}

function LogoutIcon() {
    return (
        <svg width="15" height="15" viewBox="0 0 24 24" fill="none" aria-hidden="true">
            <path
                stroke="currentColor"
                strokeWidth="1.7"
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M9 4H6a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h3M16 16l4-4-4-4M20 12H9"
            />
        </svg>
    );
}