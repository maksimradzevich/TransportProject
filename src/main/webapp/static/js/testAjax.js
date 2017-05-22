/**
 * Created by maximradevich on 22.05.17.
 */
function searchAjax() {
    let data = {type: "stop", id: 1};
    let dataString = JSON.stringify(data);
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/favorite",
        data : dataString,
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS: ", data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}