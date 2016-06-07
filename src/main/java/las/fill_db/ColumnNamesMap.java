package las.fill_db;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Development on 18-May-16.
 */
public class ColumnNamesMap {
    private static Map<String, String> fields;

    static {
        fields = new HashMap<>();
        fields.put("pesel", "Pesel");
        fields.put("klient", "Klient");
        fields.put("imie", "Imię");
        fields.put("nazwisko", "Nazwisko");
        fields.put("adres", "Adres");
        fields.put("nr_telefonu", "Numer tel.");
        fields.put("nr_ewidencyjny_FK2", "Nr ewidencyjny");
        fields.put("id_dostawcy_FK", "Id dostawcy");
        fields.put("data_dostawy", "Data dostawy (rrrr-mm-dd)");
        fields.put("id_dostawy", "Id dostawy");
        fields.put("id_dostawcy", "Id dostawcy");
        fields.put("nazwa_firmy", "Nazwa firmy");
        fields.put("adres_firmy", "Adres firmy");
        fields.put("adres_firmy", "Adres firmy");
        fields.put("nr_dzialki", "Nr działki");
        fields.put("powierzchnia", "Powierzchnia");
        fields.put("nr_pesel_pracownika", "Pesel");
        fields.put("stawka_godzinowa", "Stawka godzinowa");
        fields.put("nr_ewidencyjny_FK", "Nr ewidencyjny");
        fields.put("nr_dzialki_FK", "Nr działki");
        fields.put("nr_dzialki_FK", "Nr działki");
        fields.put("nr_ewidencyjny", "Nr ewidencyjny");
        fields.put("ilosc_drzewek", "Ilość drzewek");
        fields.put("typ_drzewek", "Typ drzewek");
        fields.put("id_wycinki_FK3", "Id wycinki");
        fields.put("pesel_klienta_FK", "Pesel klienta");
        fields.put("ilosc_drewna", "Ilość drewna");
        fields.put("cena_za_m3", "Cena za metr^3");
        fields.put("suma_brutto", "Suma brutto");
        fields.put("id_transakcji", "Id transakcji");
        fields.put("id_wycinki", "Id wycinki");
        fields.put("data_wycinki", "Data wycinki (rrrr-mm-dd)");
        fields.put("ilosc_godzin_roboczych", "Ilość roboczogodzin");
        fields.put("nr_ewidencyjny_FK3", "Nr ewidencyjny");
        fields.put("id_zwierzecia_FK", "Id zwierzęcia");
        fields.put("numer_dzialki_FK", "Numer działki");
        fields.put("id_zerowiska", "Id żerowiska");
        fields.put("id_wycinki_FK4", "Id wycinki");
        fields.put("nr_pesel_FK", "Nr pesel pracownika");
        fields.put("id_zwierzecia", "Id zwierzęcia");
        fields.put("gatunek", "Gatunek");
        fields.put("plec", "Płeć");

    }

    public static String getColumnDisplayName(String dbName) {
        return fields.get(dbName);
    }
}
