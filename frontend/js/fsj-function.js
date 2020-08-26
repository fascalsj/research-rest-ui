// Set BirthDate Value
let date = "<option disabled selected value>Date</option>";
for (let i = 1; i <= 31; i++) {
    date = date.concat("<option value='" + i + "'>" + i + "</option>")
}

let month = "<option disabled selected value>Month</option>";
for (let i = 1; i <= 12; i++) {
    month = month.concat("<option value='" + i + "'>" + i + "</option>")
}
let today = new Date();
let yyyy = today.getFullYear();

let year = "<option disabled selected value>Year</option>";
for (let i = yyyy - 40; i <= yyyy - 10; i++) {
    year = year.concat("<option value='" + i + "'>" + i + "</option>")
}


$("#date").html(date);
$("#month").html(month);
$("#year").html(year);


//Give Default Tooltip
$('[data-toggle=popover]').popover();
$(document).on('click', '#button', function () {
    alert('Clicked');
});

$(document).ready(function () {
    $('#mobileNumber').tooltip({trigger: 'manual'}).tooltip('show');
});

$(document).ready(function () {
    $('#firstName').tooltip({trigger: 'manual'}).tooltip('show');
});

$(document).ready(function () {
    $('#lastName').tooltip({trigger: 'manual'}).tooltip('show');
});

$(document).ready(function () {
    $('#email').tooltip({trigger: 'manual'}).tooltip('show');
});

$(document).ready(function () {
    $('#email').tooltip({trigger: 'manual'}).tooltip('show');
});

let mobileNumberValidate = false;
let firstNameValidate = false;
let lastNameValidate = false;
let emailValidate = false;

// Validate Mobile Number
$('#mobileNumber').keyup(function () {
    //Indonesian Number 11 to 14 digit
    let mobileNumber = this.value;
    let mobileNumberIdnFormat = true;
    let mobileNumberExist = false;
    let mobileNumberTimeOut = false;
    let validated = validateIndonesianMobileNumberFormat(mobileNumber);
    if (true === validated) {
        $('#mobileNumber').tooltip({trigger: 'manual'}).tooltip('hide');
    } else {
        $('#mobileNumber').attr('data-original-title', 'Please Enter Valid Indonesian Number').tooltip({trigger: 'manual'}).tooltip('show');
        mobileNumberIdnFormat = false;
    }

    $.ajax({
        type: "GET",
        async: false,
        url: "http://localhost:8080/register/?mobileNumber=" + mobileNumber,
        success: function (response) {
            if ('' !== response) {
                mobileNumberExist = true;
                $('#mobileNumber').attr('data-original-title', 'Duplicate Phone Number').tooltip({trigger: 'manual'}).tooltip('show');
            }
        },
        error: function () {
            mobileNumberTimeOut = true;
            $('#mobileNumber').attr('data-original-title', 'Mobile Number Checker Service Timeout').tooltip({trigger: 'manual'}).tooltip('show');
        }

    });
    mobileNumberValidate = mobileNumberIdnFormat === true && mobileNumberExist === false && mobileNumberTimeOut === false;

    checkValidate();
});

//Validate Firstname
$('#firstName').keyup(function () {
    let firstName = this.value;
    if ('' === firstName) {
        $('#firstName').tooltip({trigger: 'manual'}).tooltip('show');
        firstNameValidate = false;
    } else {
        $('#firstName').tooltip({trigger: 'manual'}).tooltip('hide');
        firstNameValidate = true;
    }
    checkValidate();
});


//Validate Lastname
$('#lastName').keyup(function () {
    let lastName = this.value;
    if ('' === lastName) {
        $('#lastName').tooltip({trigger: 'manual'}).tooltip('show');
        lastNameValidate = false;
    } else {
        $('#lastName').tooltip({trigger: 'manual'}).tooltip('hide');
        lastNameValidate = true;
    }
    checkValidate();
});

//Validate Email
$('#email').keyup(function () {
    let email = this.value;
    let emailExist = false;
    let emailTimeOut = false;
    let validated = validateEmailFormat(email);
    let emailFormat = true;

    if (true === validated) {
        $('#email').tooltip({trigger: 'manual'}).tooltip('hide');
    } else {
        $('#email').attr('data-original-title', 'Please Enter Valid Email Format').tooltip({trigger: 'manual'}).tooltip('show');
        emailFormat = false;
    }

    $.ajax({
        type: "GET",
        async: false,
        url: "http://localhost:8080/register/?email=" + email,
        success: function (response) {
            if ('' !== response) {
                emailExist = true;
                $('#email').attr('data-original-title', 'Duplicate Email').tooltip('show');
            }
        },
        error: function () {
            $('#email').attr('data-original-title', 'Email Check Service timeout').tooltip('show');
            emailTimeOut = true;
        }
    });

    emailValidate = true === emailFormat && false === emailExist && emailTimeOut === false;
    checkValidate();
});

function checkValidate() {

    if ((true === mobileNumberValidate) && (true === firstNameValidate) && (true === lastNameValidate) && (true === emailValidate)) {
        $('#buttonSubmit').removeAttr('disabled')
    } else {
        $('#buttonSubmit').attr('disabled', true)
    }
}


function validateIndonesianMobileNumberFormat(phoneNumber) {
    let phoneNumberPattern = /^(^\+628|628|^08)(\d{3,4}-?){2}\d{3,4}$/g;
    return phoneNumberPattern.test(phoneNumber);
}

function validateEmailFormat(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

//Function for Submit Data
$("#formInput").submit(function (e) {

    let selectedGender = "";
    let genderElement = $("input[type='radio'][name='gender']:checked");
    if (genderElement.length > 0) {
        selectedGender = genderElement.val();
    }
    let data = {
        "mobile_number": $('#mobileNumber').val(),
        "first_name": $('#firstName').val(),
        "last_name": $('#lastName').val(),
        "email": $('#email').val()
    }
    let date = $('#date').val();
    let month = $('#month').val();
    let year = $('#year').val();

    if (null != date && null != month && null != year) {
        data.birth_date = date + "/" + month + "/" + year;
    }
    if ("" !== selectedGender) {
        data.gender = selectedGender;
    }

    $.ajax({
        type: "POST",
        async: false,
        data: JSON.stringify(data),
        dataType: 'JSON',
        contentType: 'application/json',
        url: "http://localhost:8080/register",
        success: function () {
            $("#loginModal").modal();
        }
    });

    e.preventDefault();

});







