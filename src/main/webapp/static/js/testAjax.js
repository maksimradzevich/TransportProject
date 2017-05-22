/**
 * Created by maximradevich on 22.05.17.
 */
let favoriteFirst = "<button onclick=\"favorite(";
let favoriteSecond = ", '";
let favoriteThird = "');\" type=\"button\" class=\"btn btn-warning fav-button\"><span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>В избранное</button>";

let unfavoriteFirst= "<button onclick=\"unfavorite(";
let unfavoriteSecond= ", '";
let unfavoriteThird = "');\" type=\"button\" class=\"btn btn-danger fav-button\"><span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>Убрать из избранного</button>"

function favorite(id, type) {
    let data = {type: type, id: id};
    let dataString = JSON.stringify(data);
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/favorite",
        data : dataString,
        dataType : 'text',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS 12312312: ", data);
            let stopId = document.getElementById("id").innerHTML;
            let place = document.getElementById("buttonPlace");
            place.innerHTML = unfavoriteFirst + stopId + unfavoriteSecond + type + unfavoriteThird;
        },
        error : function(e) {
            console.log("ERROR 12312313: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function unfavorite(id, type) {
    let data = {type: type, id: id};
    let dataString = JSON.stringify(data);
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/unfavorite",
        data : dataString,
        dataType : 'text',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS 12312312: ", data);
            let stopId = document.getElementById("id").innerHTML;
            let place = document.getElementById("buttonPlace");
            place.innerHTML = favoriteFirst + stopId + favoriteSecond + type + favoriteThird;
        },
        error : function(e) {
            console.log("ERROR 12312313: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}