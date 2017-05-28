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
        tableBody = tableOfTimetable.childNodes[0];
    }

    tableBody = angular.copy(tableBody);
    runTimer(tableBody, index);
}

function runTimer(table, index) {
    var hoursRow = table.childNodes[0].childNodes;
    var weekDayRow = table.childNodes[1].childNodes;
    var weekEndRow = weekDayRow;
    var onlyWeekDay = false;
    if (table.childNodes[2] !== undefined) {
        weekEndRow = table.childNodes[2].childNodes;
    } else {
        onlyWeekDay = true;
    }
    var arrayOfWeekdayTime = createArrayOfTime(hoursRow, weekDayRow);
    var arrayOfWeekendTime = createArrayOfTime(hoursRow, weekEndRow);

    console.log("Будни: " + arrayOfWeekdayTime);
    console.log("Выходные: " + arrayOfWeekendTime);
    showTimer(arrayOfWeekdayTime, arrayOfWeekendTime, onlyWeekDay, index);
    var now = new Date();
    var date = new Date();
    date.setMinutes(date.getMinutes() + 1);
    date.setSeconds(0);
    date.setMilliseconds(0);
    setTimeout(function () {
        showTimer(arrayOfWeekdayTime, arrayOfWeekendTime, onlyWeekDay, index);
        setInterval(showTimer, 60000, arrayOfWeekdayTime, arrayOfWeekendTime, onlyWeekDay, index);
    }, date.getTime() - now.getTime());

}

function getDiffTimeString(nextTime, timer) {
    var date = new Date();
    console.dir(" -- Время сейчас " + date);
    console.dir(" -- Время прибытия" + nextTime);
    var millisecondsDiff = nextTime.getTime() - date.getTime();
    var minutes = Math.ceil(millisecondsDiff / 60000);
    console.dir(" -- Минуты с округлением " + minutes);

    var str;
    if (minutes < 60) {
        str = minutes + " мин.";
    } else if (minutes >= 60 && minutes < 1440) {
        var hours = minutes / 60 | 0;
        var minutesNew = minutes - hours * 60;
        str = hours + " ч. " + minutesNew + " мин.";
    } else {
        var days = minutes / 1440 | 0;
        var hours = (minutes - (days * 1440)) / 60 | 0;
        var minutesNew = (minutes - (days * 1440) - hours * 60);
        str = days + " дн. " + hours + " ч. " + minutesNew + " мин.";
    }

    timer.innerHTML = str;

    if (minutes < 10) {
        timer.className = "label label-danger";
    } else if (minutes >= 10 && minutes <= 20) {
        timer.className = "label label-warning";
    } else {
        timer.className = "label label-success";
    }

}
function showTimer(arrayOfWeekdayTime, arrayOfWeekendTime, onlyWeekDay, index) {
    var nextTime = computeNextTime(arrayOfWeekdayTime, arrayOfWeekendTime, onlyWeekDay);
    console.dir("Время следующего прибытия: " + nextTime);
    var timer = document.getElementById("time" + index);

    getDiffTimeString(nextTime, timer);
}

function isEmptyCell(hourTd) {
    return hourTd == "" || hourTd == "null";
}

function createArrayOfTime(hoursRow, minutesRow) {
    var arrayOfTimes = [];
    for (var i = 1; i < hoursRow.length; i++) {
        var hourTd = hoursRow[i];
        var minuteString = minutesRow[i].innerHTML;
        var hourString = hourTd.innerHTML;
        if (!isEmptyCell(hourString) && !isEmptyCell(minuteString)) {
            var hour = hourString;
            var minutes = minuteString.split(" ");
            for (var j = 0; j < minutes.length; j++) {
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
        if (currentHour == 23 && $.inArray(timeObject.hour, [0, 1, 2, 3, 4]) == 1) {
            nextTimeDate = new Date();
            nextTimeDate.setDate(nextTimeDate.getDate() + 1);
            nextTimeDate.setHours(timeObject.hour, timeObject.minute, 0, 0);
            return nextTimeDate;
        } else if (timeObject.hour > currentHour || (timeObject.hour == currentHour && timeObject.minute > currentMinutes)) {
            nextTimeDate = new Date();
            nextTimeDate.setHours(timeObject.hour, timeObject.minute, 0, 0);
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
        // nextTimeDate = defineNextTime(secondTimeArray, currentHour, currentMinutes);
        // if (nextTimeDate === undefined) {
            nextTimeDate = new Date();
            nextTimeDate.setHours(secondTimeArray[0].hour, secondTimeArray[0].minute, 0, 0);
        // }
        nextTimeDate.setDate(nextTimeDate.getDate() + 1);
    }

    return nextTimeDate;
}

function computeNextTime(arrayOfWeekdayTime, arrayOfWeekendTime, onlyWeekDay) {
    var nextTime = undefined;
    if (isWeekDay()) {
        if (isFriday()) {
            nextTime = findNextTimeInArrays(arrayOfWeekdayTime, arrayOfWeekendTime);
        } else {
            nextTime = findNextTimeInArrays(arrayOfWeekdayTime, arrayOfWeekdayTime);
        }
    } else {
        if (isSunday()) {
            nextTime = findNextTimeInArrays(arrayOfWeekendTime, arrayOfWeekdayTime);
        } else {
            nextTime = findNextTimeInArrays(arrayOfWeekendTime, arrayOfWeekendTime);
        }
    }

    if (onlyWeekDay) {
        while (nextTime.getDay() == 0 || nextTime.getDay() == 6) {
            nextTime.setDate(nextTime.getDate() + 1);
        }
    }

    return nextTime;
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

