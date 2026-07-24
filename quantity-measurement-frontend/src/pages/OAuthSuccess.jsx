import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function OAuthSuccess() {
    const navigate = useNavigate();

    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        const token = params.get("token");

        if (token) {
            localStorage.setItem("jwt", token);

            navigate("/dashboard");
        } else {
            navigate("/");
        }
    }, [navigate]);

    return (
        <div style={{ textAlign: "center", marginTop: "100px" }}>
            <h2>Signing you in...</h2>
        </div>
    );
}

export default OAuthSuccess;