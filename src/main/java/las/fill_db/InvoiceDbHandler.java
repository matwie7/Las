package las.fill_db;

import las.fill_db.models.DbModel;
import las.fill_db.models.Transakcja;
import las.helpers.RequestHelper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by M on 07-Jun-16.
 */
public class InvoiceDbHandler extends DbHandler {
    public static List<String> getFields() {
        return Arrays.asList("klient", "data_wycinki", "ilosc_drewna", "cena_za_m3", "suma_brutto");
    }

    @Override
    public List<DbModel> select(Map parameterMap) {
        Map map = RequestHelper.getValidMap(parameterMap);
        String tableName = (String) map.get("tabName");
        map.remove("tabName");

        List<DbModel> records = simpleSelect(tableName);
        List<DbModel> output = new ArrayList<>();

        modelsLoop:
        for (DbModel model : records) {
            boolean matches = false;
            for (String key : getFields()) {
                if ((!map.get(key).equals("") && String.valueOf(model.getValue(key)).contains(String.valueOf(map.get(key))))) {
                    matches = true;
                }
            }
            if (matches)
                output.add(model);
        }
        return output;
    }

    @Override
    public <T extends DbModel> List<DbModel> simpleSelect(String tableName) {
        String sql = "select  CONCAT(k.imie, \" \",k.nazwisko) as klient, w.data_wycinki, t.ilosc_drewna, t.cena_za_m3, t.suma_brutto, t.id_transakcji  from (transakcja t, klient k, wycinka w) where k.pesel = t.pesel_klienta_FK AND t.id_wycinki_FK3 = w.id_wycinki;";

        return getSelectedData(tableName, sql);
    }

    public Transakcja getInvoice(int id) {
        List<DbModel> list = super.simpleSelect("transakcja");
        for (DbModel model : list)
            if (model.getValue(Transakcja.ID_TRANSAKCJI).equals(id))
                return (Transakcja) model;
        return null;
    }

    public <T extends DbModel> boolean updateData(DbModel model) {
        String updatePattern = "UPDATE {0} SET {1} WHERE {2}={3}";

        List<String> keys = model.getKeysAndValues(false).getLeft();
        List<String> values = model.getKeysAndValues(false).getRight();

        List<String> sets = values.stream()
                .map(value -> keys.get(values.indexOf(value)) + "=" + String.valueOf(value))
                .collect(Collectors.toList());

        String sql = MessageFormat.format(updatePattern, model.getTableName(), String.join(", ", sets), Transakcja.ID_TRANSAKCJI, String.valueOf(model.getValue(Transakcja.ID_TRANSAKCJI)));

        return updateData(sql);
    }
}
