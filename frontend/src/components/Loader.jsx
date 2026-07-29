import "./Loader.css";

export default function Loader({ label = "Loading...", fullScreen = false, size = "md" }) {
    return (
        <div className={`loader-wrap ${fullScreen ? "loader-wrap--full" : ""}`}>
            <span className={`loader loader--${size}`} aria-hidden="true" />
            <span className="loader-label">{label}</span>
        </div>
    );
}