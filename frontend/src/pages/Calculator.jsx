import { useEffect, useMemo, useState } from "react";
import quantityService from "../services/quantityService";
import Card from "../components/Card";
import Button from "../components/Button";
import Input from "../components/Input";
import "./Calculator.css";

const UNITS = {
    LENGTH: ["FEET", "INCHES", "YARDS", "CENTIMETERS"],
    WEIGHT: ["GRAM", "KILOGRAM", "POUND"],
    TEMPERATURE: ["CELSIUS", "FAHRENHEIT", "KELVIN"],
    VOLUME: ["LITRE", "MILLILITRE", "GALLON"],
};

const TYPES = [
    { key: "LENGTH", label: "Length", icon: "↔" },
    { key: "WEIGHT", label: "Weight", icon: "⚖" },
    { key: "VOLUME", label: "Volume", icon: "◔" },
    { key: "TEMPERATURE", label: "Temperature", icon: "◐" },
];

const OPERATIONS = [
    { key: "compare", label: "Compare" },
    { key: "convert", label: "Convert" },
    { key: "add", label: "Add" },
    { key: "subtract", label: "Subtract" },
    { key: "divide", label: "Divide" },
];

export default function Calculator() {
    const [currentType, setCurrentType] = useState("LENGTH");
    const [currentOperation, setCurrentOperation] = useState("compare");

    const [value1, setValue1] = useState("");
    const [unit1, setUnit1] = useState(UNITS.LENGTH[0]);
    const [value2, setValue2] = useState("");
    const [unit2, setUnit2] = useState(UNITS.LENGTH[0]);
    const [targetUnit, setTargetUnit] = useState(UNITS.LENGTH[0]);
    const [resultUnit, setResultUnit] = useState(UNITS.LENGTH[0]);

    const [lastResult, setLastResult] = useState(null);
    const [resultDisplay, setResultDisplay] = useState("Waiting for operation...");
    const [resultTone, setResultTone] = useState("neutral");
    const [formError, setFormError] = useState("");
    const [loading, setLoading] = useState(false);

    const availableUnits = UNITS[currentType];
    const availableOperations = useMemo(
        () =>
            currentType === "TEMPERATURE"
                ? OPERATIONS.filter((op) => ["compare", "convert"].includes(op.key))
                : OPERATIONS,
        [currentType]
    );

    // Reset units/operation state whenever the measurement type changes,
    // mirroring the original app.js loadUnits() + card click behavior.
    useEffect(() => {
        setUnit1(availableUnits[0]);
        setUnit2(availableUnits[0]);
        setTargetUnit(availableUnits[0]);
        setResultUnit(availableUnits[0]);

        if (currentType === "TEMPERATURE" && !["compare", "convert"].includes(currentOperation)) {
            setCurrentOperation("compare");
        }

        resetResult();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [currentType]);

    useEffect(() => {
        resetResult();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [currentOperation]);

    function resetResult() {
        setLastResult(null);
        setResultDisplay("Waiting for operation...");
        setResultTone("neutral");
        setFormError("");
    }

    function displayResult(data) {
        if (typeof data === "boolean") {
            setResultTone(data ? "success" : "danger");
            setResultDisplay(data ? "✅ Quantities are Equal" : "❌ Quantities are Not Equal");
            return;
        }
        if (typeof data === "number") {
            setResultTone("neutral");
            setResultDisplay(String(data));
            return;
        }
        if (data && data.value !== undefined) {
            setResultTone("neutral");
            setResultDisplay(`${data.value} ${data.unit}`);
            return;
        }
        if (data && data.result !== undefined) {
            setResultTone("neutral");
            setResultDisplay(String(data.result));
            return;
        }
        setResultTone("neutral");
        setResultDisplay(JSON.stringify(data, null, 2));
    }

    async function executeOperation() {
        setFormError("");

        if (value1 === "") {
            setFormError("Please enter the first value.");
            return;
        }
        if (isNaN(Number(value1))) {
            setFormError("First value must be a number.");
            return;
        }
        if (currentOperation !== "convert") {
            if (value2 === "") {
                setFormError("Please enter the second value.");
                return;
            }
            if (isNaN(Number(value2))) {
                setFormError("Second value must be a number.");
                return;
            }
        }

        const request = {
            thisQuantity: {
                value: Number(value1),
                unit: unit1,
                measurementType: currentType,
            },
            thatQuantity: {
                value: currentOperation === "convert" ? 0 : Number(value2),
                unit: unit2,
                measurementType: currentType,
            },
            targetUnit: targetUnit,
        };

        setLoading(true);
        setResultDisplay("Loading...");

        try {
            const response = await quantityService[currentOperation](request);
            const data = response.data;

            if (currentOperation === "add" || currentOperation === "subtract") {
                setLastResult(data);
                setResultUnit(data.unit);
            } else {
                setLastResult(null);
            }

            displayResult(data);
        } catch (error) {
            setResultTone("danger");
            setResultDisplay(error.friendlyMessage || "Operation failed.");
        } finally {
            setLoading(false);
        }
    }

    async function handleResultUnitChange(newUnit) {
        setResultUnit(newUnit);
        if (lastResult == null) return;

        const request = {
            thisQuantity: {
                value: lastResult.value,
                unit: lastResult.unit,
                measurementType: lastResult.measurementType,
            },
            thatQuantity: {
                value: 0,
                unit: lastResult.unit,
                measurementType: lastResult.measurementType,
            },
            targetUnit: newUnit,
        };

        try {
            const response = await quantityService.convert(request);
            setLastResult(response.data);
            displayResult(response.data);
        } catch (error) {
            setResultTone("danger");
            setResultDisplay(error.friendlyMessage || "Conversion failed.");
        }
    }

    const showSecondQuantity = currentOperation !== "convert";
    const showTargetUnit = currentOperation === "convert";
    const showResultUnit =
        lastResult != null && (currentOperation === "add" || currentOperation === "subtract");

    return (
        <div className="calculator">
            <header className="calculator__header">
                <span className="eyebrow">Calculator</span>
                <h1 className="calculator__title">Quantity Calculator</h1>
                <p className="calculator__subtitle">
                    Select a measurement type and operation, then run the calculation.
                </p>
            </header>

            <section>
                <h2 className="calculator__section-label">Measurement type</h2>
                <div className="calculator__types">
                    {TYPES.map((type) => (
                        <button
                            key={type.key}
                            className={`type-pill ${currentType === type.key ? "type-pill--active" : ""}`}
                            onClick={() => setCurrentType(type.key)}
                            type="button"
                        >
                            <span>{type.icon}</span>
                            {type.label}
                        </button>
                    ))}
                </div>
            </section>

            <Card className="calculator__panel">
                <div className="calculator__operations">
                    {availableOperations.map((op) => (
                        <button
                            key={op.key}
                            type="button"
                            className={`op-tab ${currentOperation === op.key ? "op-tab--active" : ""}`}
                            onClick={() => setCurrentOperation(op.key)}
                        >
                            {op.label}
                        </button>
                    ))}
                </div>

                <div className="calculator__grid">
                    <div className="calculator__quantity">
                        <Input
                            label="First value"
                            id="value1"
                            type="number"
                            placeholder="e.g. 12"
                            value={value1}
                            onChange={(e) => setValue1(e.target.value)}
                        />
                        <Input label="Unit" id="unit1" as="select" value={unit1} onChange={(e) => setUnit1(e.target.value)}>
                            {availableUnits.map((u) => (
                                <option key={u} value={u}>
                                    {u}
                                </option>
                            ))}
                        </Input>
                    </div>

                    {showSecondQuantity && (
                        <div className="calculator__quantity">
                            <Input
                                label="Second value"
                                id="value2"
                                type="number"
                                placeholder="e.g. 5"
                                value={value2}
                                onChange={(e) => setValue2(e.target.value)}
                            />
                            <Input label="Unit" id="unit2" as="select" value={unit2} onChange={(e) => setUnit2(e.target.value)}>
                                {availableUnits.map((u) => (
                                    <option key={u} value={u}>
                                        {u}
                                    </option>
                                ))}
                            </Input>
                        </div>
                    )}

                    {showTargetUnit && (
                        <div className="calculator__quantity">
                            <Input
                                label="Target unit"
                                id="targetUnit"
                                as="select"
                                value={targetUnit}
                                onChange={(e) => setTargetUnit(e.target.value)}
                            >
                                {availableUnits.map((u) => (
                                    <option key={u} value={u}>
                                        {u}
                                    </option>
                                ))}
                            </Input>
                        </div>
                    )}
                </div>

                {formError && <p className="calculator__error">{formError}</p>}

                <Button
                    variant="primary"
                    size="lg"
                    fullWidth
                    loading={loading}
                    onClick={executeOperation}
                >
                    Execute Operation
                </Button>
            </Card>

            <Card className={`calculator__result calculator__result--${resultTone}`}>
                <span className="calculator__result-label">Result</span>
                <p className="calculator__result-value">{resultDisplay}</p>

                {showResultUnit && (
                    <div className="calculator__result-unit">
                        <Input
                            label="Show result in"
                            id="resultUnit"
                            as="select"
                            value={resultUnit}
                            onChange={(e) => handleResultUnitChange(e.target.value)}
                        >
                            {availableUnits.map((u) => (
                                <option key={u} value={u}>
                                    {u}
                                </option>
                            ))}
                        </Input>
                    </div>
                )}
            </Card>
        </div>
    );
}