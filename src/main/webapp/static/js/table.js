"use strict";

function addTable(index) {

    var timetable = document.getElementById("t" + index);
    var stringJson = timetable.innerHTML;
    var tableOfTimetable = void 0;
    try {
        var json = JSON.parse(stringJson);
        tableOfTimetable = createTableOfTimetable(json);
        tableOfTimetable.className = "table table-bordered";
        document.getElementById("timetable" + index).appendChild(tableOfTimetable);
    } catch (e) {
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

    var _iteratorNormalCompletion = true;
    var _didIteratorError = false;
    var _iteratorError = undefined;

    try {
        for (var _iterator = tbodyNode.childNodes[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
            var trNode = _step.value;

            var tableRow = document.createElement("TR");
            var _iteratorNormalCompletion2 = true;
            var _didIteratorError2 = false;
            var _iteratorError2 = undefined;

            try {
                for (var _iterator2 = trNode.childNodes[Symbol.iterator](), _step2; !(_iteratorNormalCompletion2 = (_step2 = _iterator2.next()).done); _iteratorNormalCompletion2 = true) {
                    var tdNode = _step2.value;

                    var text = document.createTextNode(tdNode.innerHTML);
                    var tableCell = document.createElement("TD");
                    tableCell.appendChild(text);
                    tableRow.appendChild(tableCell);
                }
            } catch (err) {
                _didIteratorError2 = true;
                _iteratorError2 = err;
            } finally {
                try {
                    if (!_iteratorNormalCompletion2 && _iterator2.return) {
                        _iterator2.return();
                    }
                } finally {
                    if (_didIteratorError2) {
                        throw _iteratorError2;
                    }
                }
            }

            tableBody.appendChild(tableRow);
        }
    } catch (err) {
        _didIteratorError = true;
        _iteratorError = err;
    } finally {
        try {
            if (!_iteratorNormalCompletion && _iterator.return) {
                _iterator.return();
            }
        } finally {
            if (_didIteratorError) {
                throw _iteratorError;
            }
        }
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