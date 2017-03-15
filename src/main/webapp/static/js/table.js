var timetable = document.getElementById("t");

var stringJson = timetable.innerHTML;
console.log(stringJson);
var json = JSON.parse(stringJson);

var numberOfInner = json.length;
console.log(numberOfInner);


for (var i = 0; i < numberOfInner; i++) {
    var tableRow = document.createElement("TR");

    for (var prop in json[i]) {

        var newVar = json[i][prop];
        console.log(newVar);
        var text = document.createTextNode(newVar);
        var tableCell = document.createElement("TD");
        tableCell.appendChild(text);
        tableRow.appendChild(tableCell);
        // array.push(objectArray[i][prop])
    }
    document.getElementById("timetable").appendChild(tableRow);
}

function createTableOfTimetable(json) {
    var table = document.createElement("")
}

