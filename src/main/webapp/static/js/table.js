function addTable(index) {

    let timetable = document.getElementById("t" + index);
    let stringJson = timetable.innerHTML;
    let tableOfTimetable;
    try {
        let json = JSON.parse(stringJson);
        tableOfTimetable = createTableOfTimetable(json);
        tableOfTimetable.className = "table table-bordered";
        document.getElementById("timetable" + index).appendChild(tableOfTimetable);
    } catch (e){
        tableOfTimetable = new DOMParser().parseFromString(stringJson, "text/xml");
        let tableFromTableLol = createTableFromTableLol(tableOfTimetable.documentElement);
        tableFromTableLol.className = "table table-bordered";
        document.getElementById("timetable" + index).appendChild(tableFromTableLol);
    }
}

function createTableFromTableLol(tableWrong) {
    let table = document.createElement("TABLE");
    let tableBody = document.createElement("TBODY");
    table.appendChild(tableBody);

    let tbodyNode = tableWrong.childNodes[0];

    for (let trNode of tbodyNode.childNodes) {
        let tableRow = document.createElement("TR");
        for (let tdNode of trNode.childNodes) {
            let text = document.createTextNode(tdNode.innerHTML);
            let tableCell = document.createElement("TD");
            tableCell.appendChild(text);
            tableRow.appendChild(tableCell);
        }
        tableBody.appendChild(tableRow);
    }
    return table;
}

function createTableOfTimetable(json) {
    var table = document.createElement("TABLE");
    var tableBody = document.createElement("TBODY");
    table.appendChild(tableBody);

    var lengthOfJson = json.length;

    for (var i = 0; i < lengthOfJson; i++) {
        var tableRow = document.createElement("TR");

        for (var prop in json[i]) {
            var newVar = json[i][prop];
            var text = document.createTextNode(newVar);
            var tableCell = document.createElement("TD");
            tableCell.appendChild(text);
            tableRow.appendChild(tableCell);
        }

        tableBody.appendChild(tableRow);
    }
    return table;
}

