import "./Button.css";
import Loader from "./Loader";

export default function Button({
                                   children,
                                   variant = "primary",
                                   size = "md",
                                   loading = false,
                                   disabled = false,
                                   type = "button",
                                   onClick,
                                   fullWidth = false,
                                   icon = null,
                                   ...rest
                               }) {
    return (
        <button
            type={type}
            className={`btn btn--${variant} btn--${size} ${fullWidth ? "btn--full" : ""}`}
            disabled={disabled || loading}
            onClick={onClick}
            {...rest}
        >
            {loading ? (
                <Loader size="sm" label="" />
            ) : (
                <>
                    {icon && <span className="btn__icon">{icon}</span>}
                    {children}
                </>
            )}
        </button>
    );
}