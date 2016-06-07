/**
 * Created by Development on 31-May-16.
 */

clientAutocomplete();
fellingAutocomplete();
$(window).focus(function () {
    clientAutocomplete();
    fellingAutocomplete();
});


$('#add_client').on('click', function (e) {
    window.open('genericInsert.jsp?tabName=klient', '_blank');
});
$('#add_treesFelling').on('click', function (e) {
    window.open('genericInsert.jsp?tabName=wycinka', '_blank');
});

$('#save').on('click', function (e) {
    $.get("insert_record", {
        table: 'transakcja',
        pesel_klienta_FK: parseInt($('#klient').val().split(",")[0]),
        id_wycinki_FK3: parseInt($('#wycinka').val().split("-")[0]),
        ilosc_drewna: parseInt($('#ilosc_drewna').val()),
        cena_za_m3: parseInt($('#cena_za_m3').val()),
        suma_brutto: parseInt($('#suma_brutto').val())
    }).done(function (responseJson) {
        console.log(responseJson);
    });
});

$('#cena_za_m3,#ilosc_drewna').on('input', function (e) {
    calculateFinalPrice();
});

function clientAutocomplete() {
    $.get("autocomplete", {
        table: 'klient'
    }).done(function (responseJson) {
        klienci = responseJson;
        $("#klient").autocomplete({
            source: klienci
        });
    });
}

function fellingAutocomplete() {
    $.get("autocomplete", {
        table: 'wycinka'
    }).done(function (responseJson) {
        wycinka = responseJson;
        $("#wycinka").autocomplete({
            source: wycinka
        });
    });
}

function calculateFinalPrice() {
    var cena = parseFloat($('#cena_za_m3').val());
    var ilosc = parseFloat($('#ilosc_drewna').val());

    if (cena && ilosc)
        $('#suma_brutto').val(cena * ilosc);
    else
        $('#suma_brutto').val("Proszę podać cenę oraz ilość");
}