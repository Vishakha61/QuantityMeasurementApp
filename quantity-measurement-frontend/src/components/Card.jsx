import "./Card.css";

export default function Card({
                                 children,
                                 className = "",
                                 as = "div",
                                 interactive = false,
                                 ...rest
                             }) {
    const Tag = as;
    return (
        <Tag
            className={`card ${interactive ? "card--interactive" : ""} ${className}`}
            {...rest}
        >
            {children}
        </Tag>
    );
}