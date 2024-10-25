/*
ADD ERRORS TO FORM
 */
export function showErrors(form, errors){
    function getInputTR(tbody, inputName){
        let trs = $(tbody).find("tr");
        for (let i= 0; i < trs.length; i++){
            if ($(trs[i]).find("input").attr("name") === inputName){
                return trs[i];
            }
        }
        return null;
    }

    let tbody = form.getElementsByTagName("tbody").item(0);
    $.each(errors, function (key, value){
        let error = "<tr><td colspan='2' class='error-on-1'>" + value + "</td></tr>";
        $(error).insertAfter($(getInputTR(tbody, key)));
    });
}
/*
DELETE ERRORS FROM FORM
 */
export function clearErrors(form){
    let tbody = form.getElementsByTagName("tbody").item(0);
    let trs = $(tbody).find("tr");
    for (let i= 0; i < trs.length; i++){
        if ($(trs[i]).find(".error-on-1").length !== 0){
            trs[i].remove();
        }
    }
}
/*
SET VALUES FOR FORM INPUTS
 */
export function setFormInputs(form, inputs){
    let inputArray = $(form).find("input");
    $.each(inputs, function (key, value){
        for (let i = 0; i < inputArray.length; i++) {
            if ($(inputArray[i]).attr("name") === key){
                $(inputArray[i]).val(value);
            }
        }
    });
}
/*
RESET FORM
 */
export function clearFormInputs(form) {
    form.reset()
}