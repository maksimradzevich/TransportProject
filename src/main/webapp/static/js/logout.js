/**
 * Created by maximradevich on 23.05.17.
 */
function logout(){
    $.ajax({
        url : "logout",
        method : "POST",
        data : "" ,
        success : function(data) {
            location.reload();
        },
        error : function(data) {
            console.log(data);
        }
    });
}