function addTable(index) {

    console.log("CALL addTable with index: " + index);

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
        // tableOfTimetable.documentElement.className = "table table-bordered";
        document.getElementById("timetable" + index).appendChild(tableOfTimetable.documentElement);
        // tableOfTimetable = $.parseHTML(stringJson);
        // tableOfTimetable.className = "table table-bordered";
        // $("#timetable" + index).append(tableOfTimetable);
    }
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

