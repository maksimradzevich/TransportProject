"use strict";
function addTable(index) {
    var timetable = document.getElementById("t" + index);
    var stringJson = timetable.innerHTML;
    var tableOfTimetable;
    var tableBody;
    try {
        var json = JSON.parse(stringJson);
        tableOfTimetable = createTableOfTimetable(json);
        tableOfTimetable.className = "table table-bordered";
        document.getElementById("timetable" + index).appendChild(tableOfTimetable);
        tableBody = tableOfTimetable.childNodes[0];
    } catch (e) {
        tableOfTimetable = new DOMParser().parseFromString(stringJson, "text/xml");
        tableOfTimetable = createTableFromTableLol(tableOfTimetable.documentElement);
        tableOfTimetable.className = "table table-bordered";
        document.getElementById("timetable" + index).appendChild(tableOfTimetable);
        tableBody = tableOfTimetable.documentElement.childNodes[0];
    }
    runTimer(tableBody);
}

function isEmptyMinute(hourTd) {
    return hourTd == "" || hourTd == "null";
}
function createArrayOfTime(hoursRow, minutesRow) {
    var arrayOfTimes = [];
    for (var i = 1; i < hoursRow.length; i++) {
        var hourTd = hoursRow[i];
        if (!isEmptyMinute(hourTd)) {
            var hour = hourTd.innerHTML;
            var minutes = minutesRow[i].innerHTML.split(" ");
            for (var j = 1; j < minutes.length; j++) {
                arrayOfTimes.push({
                    hour: +hour,
                    minute: +minutes[j]
                });
            }
        }
    }
    return arrayOfTimes;
}

function isWeekDay() {
    var date = new Date();
    return date.getDay() != 0 && date.getDay() != 6;
}

function isHourExactOrNext(timeObject, temporaryHour, temporaryMinutes) {
    return timeObject.hour >= temporaryHour && timeObject.minute >= temporaryMinutes;
}
function midnightCase(timeObject, temporaryHour, temporaryMinutes) {
    return temporaryHour > timeObject.hour;
}
function isLeftToday(arrayOfWeekDayTime, temporaryHour, temporaryMinutes) {
    for (var i = 0; i < arrayOfWeekDayTime.length; i++) {
        var timeObject = arrayOfWeekDayTime[i];
        if (timeObject.hour > temporaryHour) {
            return true;
        } else if (timeObject.hour == temporaryHour && timeObject.minute >= temporaryMinutes) {
            return true;
        }
    }
    return false;
}
function computeNextTime(arrayOfWeekDayTime) {
    var date = new Date();
    var currentHour = date.getHours();
    var minutes = date.getMinutes();
    var temporaryHour = currentHour;
    var temporaryMinutes = minutes;
    var nextHour;
    var nextMinute;
    var nextDay = false;
    for (var i = 0; i < arrayOfWeekDayTime.length; i++) {
        var timeObject = arrayOfWeekDayTime[i];
        if (isHourExactOrNext(timeObject, temporaryHour, temporaryMinutes) ) {
            nextHour = timeObject.hour;
            nextMinute = timeObject.minute;
        } else if (midnightCase(timeObject, temporaryHour, temporaryMinutes) && !isLeftToday(arrayOfWeekDayTime, temporaryHour, temporaryMinutes)) {
            nextHour = timeObject.hour;
            nextMinute = timeObject.minute;
            nextDay = true;
        }
    }

}
function isFriday() {
    return new Date().getDay() == 5;
}
function isSunday() {
    return new Date().getDay() == 0;
}

function defineNextTime(firstTimeArray, currentHour, currentMinutes) {
    var nextTimeDate = undefined;
    for (var i = 0; i < firstTimeArray.length; i++) {
        var timeObject = firstTimeArray[i];
        if (currentHour == 23 && timeObject.hour < currentHour) {
            nextTimeDate = new Date();
            nextTimeDate.setDate(nextTimeDate.getDate() + 1);
            nextTimeDate.setHours(timeObject.hour, timeObject.minute);
            return nextTimeDate;
        } else if (timeObject.hour >= currentHour && timeObject.minute >= currentMinutes) {
            nextTimeDate = new Date();
            nextTimeDate.setHours(timeObject.hour, timeObject.minute);
            return nextTimeDate;
        }
    }
}
function findNextTimeInArrays(firstTimeArray, secondTimeArray) {
    var currentHour = new Date().getHours();
    var currentMinutes = new Date().getMinutes();
    var nextTimeDate;
    nextTimeDate = defineNextTime(firstTimeArray, currentHour, currentMinutes);
    if (nextTimeDate === undefined) {
        nextTimeDate = defineNextTime(secondTimeArray, currentHour, currentMinutes);
        nextTimeDate.setDate(nextTimeDate.getDate() + 1);
    }
}
function computeNextTime(arrayOfWeekdayTime, arrayOfWeekendTime) {
    if (isWeekDay()) {
        if (isFriday()) {
            return findNextTimeInArrays(arrayOfWeekdayTime, arrayOfWeekendTime);
        } else {
            return findNextTimeInArrays(arrayOfWeekdayTime, arrayOfWeekdayTime);
        }
    } else {
        if (isSunday()) {
            return findNextTimeInArrays(arrayOfWeekendTime, arrayOfWeekdayTime);
        } else {
            return findNextTimeInArrays(arrayOfWeekendTime, arrayOfWeekendTime);
        }
    }
}
function runTimer(table) {
    var hoursRow = table.childNodes[0].childNodes;
    var weekDayRow = table.childNodes[1].childNodes;
    var weekEndRow = table.childNodes[2].childNodes;
    var arrayOfWeekdayTime = createArrayOfTime(hoursRow, weekDayRow);
    var arrayOfWeekendTime = createArrayOfTime(hoursRow, weekEndRow);
    var arrayOfNextTime = computeNextTime(arrayOfWeekdayTime, arrayOfWeekendTime);
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

