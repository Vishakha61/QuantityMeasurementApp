import { useEffect, useMemo, useState } from "react";
import quantityService from "../services/quantityService";
import Card from "../components/Card";
import Input from "../components/Input";
import Loader from "../components/Loader";
import Button from "../components/Button";
import "./History.css";

const OPERATION_FILTERS = ["ALL", "COMPARE", "CONVERT", "ADD", "SUBTRACT", "DIVIDE"];
const TYPE_FILTERS = ["ALL", "LENGTH", "WEIGHT", "VOLUME", "TEMPERATURE"];

function formatQuantity(q) {
    if (!q) return "—";
    return `${q.value} ${q.unit}`;
}

function formatResult(entry) {
    if (entry.error) return entry.errorMessage || "Error";
    const result = entry.result;
    if (result == null) return "—";
    if (typeof result === "boolean") return result ? "Equal" : "Not equal";
    if (typeof result === "object") return `${result.value} ${result.unit}`;
    return String(result);
}

export default function History() {
    const [entries, setEntries] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [search, setSearch] = useState("");
    const [operationFilter, setOperationFilter] = useState("ALL");
    const [typeFilter, setTypeFilter] = useState("ALL");

    useEffect(() => {
        loadHistory();
    }, []);

    async function loadHistory() {
        setLoading(true);
        setError("");
        try {
            const response = await quantityService.getHistory();
            setEntries(response.data || []);
        } catch (err) {
            setError(err.friendlyMessage || "Couldn't load history.");
        } finally {
            setLoading(false);
        }
    }

    const filtered = useMemo(() => {
        const term = search.trim().toLowerCase();

        return entries.filter((entry) => {
            if (operationFilter !== "ALL" && entry.operation?.toUpperCase() !== operationFilter) {
                return false;
            }
            if (
                typeFilter !== "ALL" &&
                entry.firstQuantity?.measurementType?.toUpperCase() !== typeFilter
            ) {
                return false;
            }
            if (!term) return true;

            const haystack = [
                entry.operation,
                formatQuantity(entry.firstQuantity),
                formatQuantity(entry.secondQuantity),
                formatResult(entry),
                entry.firstQuantity?.measurementType,
            ]
                .join(" ")
                .toLowerCase();

            return haystack.includes(term);
        });
    }, [entries, search, operationFilter, typeFilter]);

    return (
        <div className="history">
            <header className="history__header">
                <div>
                    <span className="eyebrow">History</span>
                    <h1 className="history__title">Calculation History</h1>
                    <p className="history__subtitle">
                        {entries.length} calculation{entries.length === 1 ? "" : "s"} on record
                    </p>
                </div>
                <Button variant="secondary" onClick={loadHistory} loading={loading}>
                    Refresh
                </Button>
            </header>

            <Card className="history__filters">
                <Input
                    id="search"
                    placeholder="Search by operation, unit, or result..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
                <Input
                    id="operationFilter"
                    as="select"
                    value={operationFilter}
                    onChange={(e) => setOperationFilter(e.target.value)}
                >
                    {OPERATION_FILTERS.map((op) => (
                        <option key={op} value={op}>
                            {op === "ALL" ? "All operations" : op}
                        </option>
                    ))}
                </Input>
                <Input
                    id="typeFilter"
                    as="select"
                    value={typeFilter}
                    onChange={(e) => setTypeFilter(e.target.value)}
                >
                    {TYPE_FILTERS.map((t) => (
                        <option key={t} value={t}>
                            {t === "ALL" ? "All types" : t}
                        </option>
                    ))}
                </Input>
            </Card>

            <Card className="history__table-card">
                {loading ? (
                    <div className="history__state">
                        <Loader label="Loading history..." />
                    </div>
                ) : error ? (
                    <div className="history__state history__state--error">{error}</div>
                ) : filtered.length === 0 ? (
                    <div className="history__state">
                        {entries.length === 0
                            ? "No calculations yet — run something in the Calculator to see it here."
                            : "No calculations match your filters."}
                    </div>
                ) : (
                    <div className="history__table-wrap">
                        <table className="history__table">
                            <thead>
                            <tr>
                                <th>Operation</th>
                                <th>First quantity</th>
                                <th>Second quantity</th>
                                <th>Result</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            {filtered.map((entry) => (
                                <tr key={entry.id}>
                                    <td>
                                        <span className="history__op-badge">{entry.operation}</span>
                                    </td>
                                    <td className="mono">{formatQuantity(entry.firstQuantity)}</td>
                                    <td className="mono">{formatQuantity(entry.secondQuantity)}</td>
                                    <td className="mono">{formatResult(entry)}</td>
                                    <td>
                      <span
                          className={`history__status ${
                              entry.error ? "history__status--error" : "history__status--ok"
                          }`}
                      >
                        {entry.error ? "Error" : "Success"}
                      </span>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </Card>
        </div>
    );
}