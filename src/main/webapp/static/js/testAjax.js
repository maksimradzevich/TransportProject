/**
 * Created by maximradevich on 22.05.17.
 */
function favorite(id, type) {
    let data = {type: type, id: id};
    let dataString = JSON.stringify(data);
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/favorite",
        data : dataString,
        dataType : 'text json',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS 12312312: ", data);
        },
        error : function(e) {
            console.log("ERROR 12312313: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}