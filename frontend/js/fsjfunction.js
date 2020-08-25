
let date = "<option disabled selected value>Date</option>";
for (let i = 1; i <= 31; i++) {
    date = date.concat("<option value='1'>"+i+"</option>")
}

let month = "<option disabled selected value>Month</option>";
for (let i = 1; i <= 12; i++) {
    month = month.concat("<option value='1'>"+i+"</option>")
}
let today = new Date();
let yyyy = today.getFullYear();

let year = "<option disabled selected value>Year</option>";
for (i=yyyy-40;i<=yyyy-10;i++){
    year = year.concat("<option value='1'>"+i+"</option>")
}
//Setting the Input Date
$("#date").html(date);
$("#month").html(month);
$("#year").html(year);

//Form Input

$("#formInput").submit(function (e){
    $("#loginModal").modal({backdrop: 'static', keyboard: false});
    e.preventDefault();
});

$('[data-toggle=popover]').popover();
$(document).on('click', '#button', function(){
    alert('Clicked');
});

$(document).ready(function(){
    $('#mobileNumber').tooltip({trigger:'manual'}).tooltip('show');
});

$(document).ready(function(){
    $('#firstName').tooltip({trigger:'manual'}).tooltip('show');
});

$(document).ready(function(){
    $('#lastName').tooltip({trigger:'manual'}).tooltip('show');
});

$(document).ready(function(){
    $('#lastName').tooltip({trigger:'manual'}).tooltip('show');
});

$(document).ready(function(){
    $('#email').tooltip({trigger:'manual'}).tooltip('show');
});

$(document).ready(function(){
    $('#email').tooltip({trigger:'manual'}).tooltip('show');
});






