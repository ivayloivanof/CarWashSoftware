$(function($) {
    let url = window.location.pathname;
    if(url == "/car/all") {
        ajaxCarAll();
    }
});

function ajaxCarAll() {
    $.ajax({
        type : "GET",
        url : "/json/car/all",
        dataType : 'json',
        success : function(cars) {
            console.log("SUCCESS: ", cars);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}