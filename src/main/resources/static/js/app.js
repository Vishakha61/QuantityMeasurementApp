const measurementCards = document.querySelectorAll(".type-card");

const unit1 = document.getElementById("unit1");
const unit2 = document.getElementById("unit2");

const targetUnit = document.getElementById("targetUnit");
const resultUnit = document.getElementById("resultUnit");

const targetUnitSection =
    document.getElementById("targetUnitSection");

const secondQuantitySection =
    document.getElementById("secondQuantitySection");

const resultUnitSection =
    document.getElementById("resultUnitSection");

const compareBtn =
    document.getElementById("compareBtn");

const convertBtn =
    document.getElementById("convertBtn");

const addBtn =
    document.getElementById("addBtn");

const subtractBtn =
    document.getElementById("subtractBtn");

const divideBtn =
    document.getElementById("divideBtn");

const executeBtn =
    document.getElementById("executeBtn");

const value1 =
    document.getElementById("value1");

const value2 =
    document.getElementById("value2");

const result =
    document.getElementById("result");

const API_URL =
    "http://localhost:8080/api/quantity";

let currentType = "LENGTH";

let currentOperation = "compare";

/*
    Stores latest backend result.

    Used when Result Unit changes.
*/
let lastResult = null;

const units = {

    LENGTH:[
        "FEET",
        "INCHES",
        "YARDS",
        "CENTIMETERS"
    ],

    WEIGHT:[
        "GRAM",
        "KILOGRAM",
        "POUND"
    ],

    TEMPERATURE:[
        "CELSIUS",
        "FAHRENHEIT",
        "KELVIN"
    ],

    VOLUME:[
        "LITRE",
        "MILLILITRE",
        "GALLON"
    ]

};

function loadUnits(type){

    unit1.innerHTML="";
    unit2.innerHTML="";
    targetUnit.innerHTML="";
    resultUnit.innerHTML="";

    units[type].forEach(unit=>{

        unit1.innerHTML+=
            `<option value="${unit}">${unit}</option>`;

        unit2.innerHTML+=
            `<option value="${unit}">${unit}</option>`;

        targetUnit.innerHTML+=
            `<option value="${unit}">${unit}</option>`;

        resultUnit.innerHTML+=
            `<option value="${unit}">${unit}</option>`;

    });

}

function activateButton(button){

    document
        .querySelectorAll(".action-buttons button")
        .forEach(btn=>btn.classList.remove("active"));

    button.classList.add("active");

}

function updateOperationUI(){

    resultUnitSection.style.display="none";

    if(currentOperation==="compare"){

        targetUnitSection.style.display="none";

        secondQuantitySection.style.display="flex";

    }

    else if(currentOperation==="convert"){

        targetUnitSection.style.display="flex";

        secondQuantitySection.style.display="none";

    }

    else{

        targetUnitSection.style.display="none";

        secondQuantitySection.style.display="flex";

    }

}

loadUnits(currentType);

updateOperationUI();

measurementCards.forEach(card=>{

    card.onclick=()=>{

        measurementCards.forEach(c=>
            c.classList.remove("active"));

        card.classList.add("active");

        currentType=card.dataset.type;

        loadUnits(currentType);

        lastResult=null;

        result.innerHTML="Waiting for operation...";

        resultUnitSection.style.display="none";

        if(currentType==="TEMPERATURE"){

            addBtn.style.display="none";
            subtractBtn.style.display="none";
            divideBtn.style.display="none";

            compareBtn.style.display="inline-block";
            convertBtn.style.display="inline-block";

            activateButton(compareBtn);

            currentOperation="compare";

        }

        else{

            addBtn.style.display="inline-block";
            subtractBtn.style.display="inline-block";
            divideBtn.style.display="inline-block";

        }

        updateOperationUI();

    };

});

compareBtn.onclick=()=>{

    activateButton(compareBtn);

    currentOperation="compare";

    updateOperationUI();

};

convertBtn.onclick=()=>{

    activateButton(convertBtn);

    currentOperation="convert";

    updateOperationUI();

};

addBtn.onclick=()=>{

    activateButton(addBtn);

    currentOperation="add";

    updateOperationUI();

};

subtractBtn.onclick=()=>{

    activateButton(subtractBtn);

    currentOperation="subtract";

    updateOperationUI();

};

divideBtn.onclick=()=>{

    activateButton(divideBtn);

    currentOperation="divide";

    updateOperationUI();

};

executeBtn.onclick=()=>{

    executeOperation();

};
async function executeOperation(){

    if(value1.value === ""){

        result.innerHTML = "Please enter the first value.";

        return;

    }

    if(currentOperation !== "convert" &&
        value2.value === ""){

        result.innerHTML =
            "Please enter the second value.";

        return;

    }

    const token = localStorage.getItem("jwt");

    if(!token){

        result.innerHTML =
            "Please login with Google first.";

        return;

    }

    const request = {

        thisQuantity:{

            value:Number(value1.value),

            unit:unit1.value,

            measurementType:currentType

        },

        thatQuantity:{

            value:
                currentOperation==="convert"
                    ? 0
                    : Number(value2.value),

            unit:unit2.value,

            measurementType:currentType

        },

        targetUnit:
        targetUnit.value

    };

    executeBtn.disabled = true;

    executeBtn.innerHTML = "Processing...";

    result.innerHTML = "Loading...";

    try{

        const response = await fetch(

            API_URL + "/" + currentOperation,

            {

                method:"POST",

                headers:{

                    "Content-Type":"application/json",

                    "Authorization":
                        "Bearer " + token

                },

                body:JSON.stringify(request)

            }

        );

        if(!response.ok){

            const error =
                await response.text();

            throw new Error(

                error ||

                "Operation Failed"

            );

        }

        const data =
            await response.json();

        /*
            Save arithmetic result.

            Used when Result Unit changes.
        */

        if(

            currentOperation==="add" ||

            currentOperation==="subtract"

        ){

            lastResult = data;

            resultUnit.value = data.unit;

            resultUnitSection.style.display = "flex";

        }

        else{

            lastResult = null;

            resultUnitSection.style.display = "none";

        }

        displayResult(data);

    }

    catch(error){

        result.innerHTML = error.message;

    }

    finally{

        executeBtn.disabled = false;

        executeBtn.innerHTML =

            "Execute Operation";

    }

}
function displayResult(data){

    if(typeof data === "boolean"){

        result.innerHTML = data
            ? "✅ Quantities are Equal"
            : "❌ Quantities are Not Equal";

        return;

    }

    if(typeof data === "number"){

        result.innerHTML = data;

        return;

    }

    if(data.value !== undefined){

        result.innerHTML =
            `${data.value} ${data.unit}`;

        return;

    }

    if(data.result !== undefined){

        result.innerHTML = data.result;

        return;

    }

    result.innerHTML =
        JSON.stringify(data,null,2);

}

/*
==================================
Automatic Result Conversion
==================================
*/

resultUnit.onchange = async ()=>{

    if(lastResult == null){

        return;

    }

    const token =
        localStorage.getItem("jwt");

    if(!token){

        return;

    }

    const request = {

        thisQuantity:{

            value:lastResult.value,

            unit:lastResult.unit,

            measurementType:lastResult.measurementType

        },

        thatQuantity:{

            value:0,

            unit:lastResult.unit,

            measurementType:lastResult.measurementType

        },

        targetUnit:resultUnit.value

    };

    try{

        const response =
            await fetch(

                API_URL + "/convert",

                {

                    method:"POST",

                    headers:{

                        "Content-Type":"application/json",

                        "Authorization":
                            "Bearer " + token

                    },

                    body:
                        JSON.stringify(request)

                }

            );

        if(!response.ok){

            throw new Error(
                "Conversion Failed"
            );

        }

        const converted =
            await response.json();

        lastResult = converted;

        displayResult(converted);

    }

    catch(error){

        result.innerHTML =
            error.message;

    }

};
/*
==========================================
Reset UI when operation changes
==========================================
*/

function resetResult(){

    lastResult = null;

    result.innerHTML = "Waiting for operation...";

    resultUnitSection.style.display = "none";

}

compareBtn.addEventListener("click", resetResult);

convertBtn.addEventListener("click", resetResult);

addBtn.addEventListener("click", resetResult);

subtractBtn.addEventListener("click", resetResult);

divideBtn.addEventListener("click", resetResult);

/*
==========================================
Reset UI when measurement type changes
==========================================
*/

measurementCards.forEach(card=>{

    card.addEventListener("click",()=>{

        resetResult();

    });

});

/*
==========================================
Initialize
==========================================
*/

activateButton(compareBtn);

loadUnits(currentType);

updateOperationUI();

resultUnitSection.style.display = "none";