package las.fill_db;

import java.util.HashMap;
import java.util.Map;

public class PrimaryKeysMap {
    private static Map<String, String> fields;

    static {
        fields = new HashMap<>();
        fields.put("zwierze", "id_zwierzecia");
        fields.put("zerowisko", "id_zerowiska");
        fields.put("obszar", "nr_dzialki");
        fields.put("szkolka", "nr_ewidencyjny");
        fields.put("dostawa", "id_dostawy");
        fields.put("dostawca", "id_dostawcy");
        fields.put("klient", "pesel");
        fields.put("pracownik", "nr_pesel_pracownika");
        fields.put("transakcja", "id_transakci");
        fields.put("wycinka", "id_wycinki");
    }

    public static String getPrimaryKey(String tableName) {
        return fields.get(tableName);
    }
}
