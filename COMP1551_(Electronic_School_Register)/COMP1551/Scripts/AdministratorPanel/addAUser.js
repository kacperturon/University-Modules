$(document).ready(function () {
    var user = $("[id*=userTypeDD]").val();
    hideUserInputFields(user, true); //Initially setup fields

    $("[id*=userTypeDD]").on("change", function () {
        user = $("[id*=userTypeDD]").val();
        hideUserInputFields(user, false);
    });
});

function hideUserInputFields(user, isInitial) {
    if (user == "Child") {
        hideInputFields(true, true, false, false, false, true, true, true, isInitial);
    }
    if (user == "Parent") {
        hideInputFields(true, true, true, true, true, false, false, false, isInitial);
    }
    if (user == "Administrator") {
        hideInputFields(false, false, false, false, false, false, false, false, isInitial);
    }
}

function hideInputFields(firstNameIsVisible, surnameIsVisible, postcodeIsVisible, telephoneIsVisible, emailIsVisible, birthDateIsVisible, genderIsVisible, parentsIsVisible, isInitial) {
    $("[id*=firstNameTxt]").prop("disabled", !firstNameIsVisible);
    $("[id*=firstNameTxt]").closest("tr").toggle(firstNameIsVisible);

    $("[id*=surnameTxt]").prop("disabled", !surnameIsVisible);
    $("[id*=surnameTxt]").closest("tr").toggle(surnameIsVisible);

    $("[id*=postcodeTxt]").prop("disabled", !postcodeIsVisible);
    $("[id*=postcodeTxt]").closest("tr").toggle(postcodeIsVisible);

    $("[id*=telephoneTxt]").prop("disabled", !telephoneIsVisible);
    $("[id*=telephoneTxt]").closest("tr").toggle(telephoneIsVisible);

    $("[id*=emailTxt]").prop("disabled", !emailIsVisible);
    $("[id*=emailTxt]").closest("tr").toggle(emailIsVisible);

    $("[id*=birthDateTxt]").prop("disabled", !birthDateIsVisible);
    $("[id*=birthDateTxt]").closest("tr").toggle(birthDateIsVisible);

    $("[id*=genderRadioBtnList]").prop("disabled", !genderIsVisible);
    $("[id*=genderRadioBtnList]").closest("tr").toggle(genderIsVisible);

    $("[id*=parentsDropdownList]").prop("disabled", !parentsIsVisible);
    $("[id*=parentsDropdownList]").closest("tr").toggle(parentsIsVisible);

    if (!isInitial) {
        ValidatorEnable($("[id*=firstNameValidator]")[0], firstNameIsVisible);
        ValidatorEnable($("[id*=surnameTxtValidator]")[0], surnameIsVisible);
        ValidatorEnable($("[id*=postcodeTxtValidator]")[0], postcodeIsVisible);
        ValidatorEnable($("[id*=telephoneTxtValidator]")[0], telephoneIsVisible);
        ValidatorEnable($("[id*=emailTxtValidator]")[0], emailIsVisible);
        ValidatorEnable($("[id*=birthDateTxtValidator]")[0], birthDateIsVisible);
        ValidatorEnable($("[id*=genderRadioBtnListValidator]")[0], genderIsVisible);
        ValidatorEnable($("[id*=parentDropDownListValidator]")[0], parentsIsVisible);

    }


}