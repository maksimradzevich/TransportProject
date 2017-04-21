

function addTable(index) {

    console.log("CALL addTable with index: " + index);

    var timetable = document.getElementById("t" + index);
    var stringJson = timetable.innerHTML;
    var json = JSON.parse(stringJson);
    var tableOfTimetable = createTableOfTimetable(json);
    tableOfTimetable.className = "table table-bordered";
    document.getElementById("timetable" + index).appendChild(tableOfTimetable);
}

// var timetable = document.getElementById("t");
// var stringJson = timetable.innerHTML;
// var json = JSON.parse(stringJson);
// var tableOfTimetable = createTableOfTimetable(json);
// tableOfTimetable.className = "table table-bordered";
// document.getElementById("timetable").appendChild(tableOfTimetable);

//
// for (var i = 0; i < numberOfInner; i++) {
//     var tableRow = document.createElement("TR");
//
//     for (var prop in json[i]) {
//
//         var newVar = json[i][prop];
//         console.log(newVar);
//         var text = document.createTextNode(newVar);
//         var tableCell = document.createElement("TD");
//         tableCell.appendChild(text);
//         tableRow.appendChild(tableCell);
//         // array.push(objectArray[i][prop])
//     }
//     document.getElementById("timetable").appendChild(tableRow);
// }

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

