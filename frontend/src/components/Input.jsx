import "./Input.css";

export default function Input({
                                  label,
                                  id,
                                  error,
                                  hint,
                                  as = "input",
                                  children,
                                  ...rest
                              }) {
    const Tag = as;
    return (
        <div className={`field ${error ? "field--error" : ""}`}>
            {label && (
                <label htmlFor={id} className="field__label">
                    {label}
                </label>
            )}
            <Tag id={id} className="field__control" {...rest}>
                {children}
            </Tag>
            {error ? (
                <span className="field__message field__message--error">{error}</span>
            ) : hint ? (
                <span className="field__message">{hint}</span>
            ) : null}
        </div>
    );
}