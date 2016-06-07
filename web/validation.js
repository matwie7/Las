// var nameInputRegex = /^[A-ZĄĘŻŹŁÓ]{1}[a-zA-Z ąęćżźłó-]{0,29}$/;
// var emailInputRegex = /^[0-9a-zA-Z_.-]+@[0-9a-zA-Z.-]+\.[a-zA-Z]{2,3}$/;
// var postalCodeValidationRegex = /^\d{2}[-]\d{3}$/;
var klientRegex = /^[0-9]{11}[,](.*?)$/;
var wycinkaRegex = /^[0-9]+ -$/;
var numRegex = /^[0-9](.*?)$/;

function validate(regex, value, groupDiv) {
    var bootstrapErrorClass = 'has-error';
    if (!regex.test(value)) {
        if (!groupDiv.hasClass(bootstrapErrorClass))
            groupDiv.addClass(bootstrapErrorClass);
        return false;
    } else if (groupDiv.hasClass(bootstrapErrorClass))
        groupDiv.removeClass(bootstrapErrorClass);
    return true;

}

$('#ilosc_drewna').on('input', function (e) {
    validate(numRegex, $(this).val(), $('#ilosc_drewnaGroup'));
});
$('#cena_za_m3').on('input', function (e) {
    validate(numRegex, $(this).val(), $('#cena_za_m3Group'));
});
//
// $("#klient").on("autocompletechange", function (event, ui) {
//     validate(klientRegex, $(this).val(), $('#klientGroup'));
// });

$('#save').on('click', function (e) {
    var errors = [];
    var errorCount = 0;
    if (!validate(klientRegex, $('#klient').val(), $('#klientGroup')))
        errors[errorCount++] = 'Wybierz klienta z listy.';
    if (!validate(wycinkaRegex, $('#wycinka').val(), $('#wycinkaGroup')))
        errors[errorCount++] = 'Wybierz wycinkę z listy.';
    if (!validate(numRegex, $('#ilosc_drewna').val(), $('#ilosc_drewnaGroup')))
        errors[errorCount++] = 'Podaj ilość sprzedanego drewna.';
    if (!validate(numRegex, $('#cena_za_m3').val(), $('#cena_za_m3Group')))
        errors[errorCount++] = 'Podaj cenę drewna.';

    if (errorCount > 0) {
        var modalBody = '';
        for (var i in errors)
            modalBody += (errors[i] + '<br>');
        $('.modal-title').html('Błąd');
        $('.modal-body').html(modalBody);
        $('#myModal').modal('show');
    }
    else {
        $('.modal-title').html('Sukces');
        $('.modal-body').html('Dodawanie zakończone sukcesem.');
        $('#myModal').modal('show');
    }
});