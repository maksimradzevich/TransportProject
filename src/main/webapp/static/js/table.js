"use strict";
function addTable(index) {

    var timetable = document.getElementById("t" + index);
    var stringJson = timetable.innerHTML;
    var tableOfTimetable;
    try {
        var json = JSON.parse(stringJson);
        tableOfTimetable = createTableOfTimetable(json);
        tableOfTimetable.className = "table table-bordered";
        document.getElementById("timetable" + index).appendChild(tableOfTimetable);
    } catch (e){
        tableOfTimetable = new DOMParser().parseFromString(stringJson, "text/xml");
        var tableFromTableLol = createTableFromTableLol(tableOfTimetable.documentElement);
        tableFromTableLol.className = "table table-bordered";
        document.getElementById("timetable" + index).appendChild(tableFromTableLol);
    }
}

function createTableFromTableLol(tableWrong) {
    var table = document.createElement("TABLE");
    var tableBody = document.createElement("TBODY");
    table.appendChild(tableBody);

    var tbodyNode = tableWrong.childNodes[0];

    for (var trNode of tbodyNode.childNodes) {
        var tableRow = document.createElement("TR");
        for (var tdNode of trNode.childNodes) {
            var text = document.createTextNode(tdNode.innerHTML);
            var tableCell = document.createElement("TD");
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

