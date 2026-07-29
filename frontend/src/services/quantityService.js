import api from "./api";

// Every function here maps 1:1 to an existing backend endpoint.
// Request/response shapes are untouched — see QuantityInputDTO / QuantityDTO / QuantityMeasurementDTO.

function buildInput({ thisQuantity, thatQuantity, targetUnit }) {
    return {
        thisQuantity,
        thatQuantity,
        targetUnit,
    };
}

const quantityService = {
    compare: (input) => api.post("/api/quantity/compare", buildInput(input)),
    convert: (input) => api.post("/api/quantity/convert", buildInput(input)),
    add: (input) => api.post("/api/quantity/add", buildInput(input)),
    subtract: (input) => api.post("/api/quantity/subtract", buildInput(input)),
    divide: (input) => api.post("/api/quantity/divide", buildInput(input)),

    getHistory: () => api.get("/api/quantity/history"),
    getHistoryByOperation: (operation) =>
        api.get(`/api/quantity/history/${operation}`),
    getHistoryByMeasurementType: (measurementType) =>
        api.get(`/api/quantity/measurement/${measurementType}`),
    getErroredOperations: () => api.get("/api/quantity/errors"),
    getOperationCount: (operation) =>
        api.get(`/api/quantity/count/${operation}`),
};

export default quantityService;