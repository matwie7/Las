package las.helpers;

import las.fill_db.DbHandler;
import las.fill_db.InvoiceDbHandler;
import las.fill_db.models.DbModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Development on 18-May-16.
 */
public class RequestHelper {
    public static <T extends DbModel> boolean addToDatabase(Map map) {
        DbHandler dbHandler = new DbHandler();
        map = getValidMap(map);
        boolean success = false;
        try {
            Class<T> clazz = (Class<T>) Class.forName(dbHandler.getClassName((String) map.get("table")));
            map.remove("table");
            Constructor<T> constructor = clazz.getConstructor(Map.class);
            dbHandler.insertData(constructor.newInstance(map));
            success = true;
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public static <T extends DbModel> boolean update(Map map) {
        InvoiceDbHandler dbHandler = new InvoiceDbHandler();
        map = getValidMap(map);
        boolean success = false;
        try {
            Class<T> clazz = (Class<T>) Class.forName(dbHandler.getClassName((String) map.get("table")));
            map.remove("table");
            Constructor<T> constructor = clazz.getConstructor(Map.class);
            dbHandler.updateData(constructor.newInstance(map));
            success = true;
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public static Map getValidMap(Map<String, Object> map) {
        Map<String, Object> outMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String value = ((String[]) map.get(entry.getKey()))[0];

            Integer number = null;
            Float floating = null;
            try {
                number = Integer.parseInt(value);
            } catch (Exception e) {
                try {
                    floating = Float.parseFloat(value);
                } catch (Exception ee) {
                }
            }

            if (number != null)
                outMap.put(entry.getKey(), number.intValue());
            else if (floating != null && floating < 9999999999f)
                outMap.put(entry.getKey(), floating.floatValue());
            else if (entry.getKey().equals("nr_telefonu"))
                outMap.put(entry.getKey(), value);
            else
                outMap.put(entry.getKey(), value);
        }
        return outMap;
    }
}
