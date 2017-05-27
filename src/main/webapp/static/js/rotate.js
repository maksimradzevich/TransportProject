

function func() {
    if ($(window).width() < 769) {
        var bodies = document.getElementsByTagName('tbody');
        for (var j = 0; j < bodies.length; j++) {
            var t= bodies[j],
                r= t.getElementsByTagName('tr'),
                cols= r.length, rows= r[0].getElementsByTagName('td').length,
                cell, next, tem, i= 0, tbod= document.createElement('tbody');

            while(i<rows){
                cell= 0;
                tem= document.createElement('tr');
                while(cell<cols){
                    next= r[cell++].getElementsByTagName('td')[0];
                    tem.appendChild(next);
                }
                tbod.appendChild(tem);
                ++i;
            }
            t.parentNode.replaceChild(tbod, t);
        }
    }
}
/**
 * Created by maximradevich on 27.05.17.
 */

document.addEventListener('DOMContentLoaded', function() {
    func();
}, false);
