"use strict";

var favoriteFirst = "<button onclick=\"favorite(";
var favoriteSecond = ", '";
var favoriteThird = "');\" type=\"button\" class=\"btn btn-warning fav-button\"><span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>В избранное</button>";

var unfavoriteFirst = "<button onclick=\"unfavorite(";
var unfavoriteSecond = ", '";
var unfavoriteThird = "');\" type=\"button\" class=\"btn btn-danger fav-button\"><span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>Убрать из избранного</button>";

function favorite(id, type) {
    var data = { type: type, id: id };
    var dataString = JSON.stringify(data);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/favorite",
        data: dataString,
        dataType: 'text',
        timeout: 100000,
        success: function success(data) {
            console.log("SUCCESS 12312312: ", data);
            var stopId = document.getElementById("id").innerHTML;
            var place = document.getElementById("buttonPlace");
            place.innerHTML = unfavoriteFirst + stopId + unfavoriteSecond + type + unfavoriteThird;
        },
        error: function error(e) {
            console.log("ERROR 12312313: ", e);
        },
        done: function done(e) {
            console.log("DONE");
        }
    });
}

function unfavorite(id, type) {
    var data = { type: type, id: id };
    var dataString = JSON.stringify(data);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/unfavorite",
        data: dataString,
        dataType: 'text',
        timeout: 100000,
        success: function success(data) {
            console.log("SUCCESS 12312312: ", data);
            var stopId = document.getElementById("id").innerHTML;
            var place = document.getElementById("buttonPlace");
            place.innerHTML = favoriteFirst + stopId + favoriteSecond + type + favoriteThird;
        },
        error: function error(e) {
            console.log("ERROR 12312313: ", e);
        },
        done: function done(e) {
            console.log("DONE");
        }
    });
}