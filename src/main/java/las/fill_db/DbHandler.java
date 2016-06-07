package las.fill_db;

import las.fill_db.models.DbModel;
import las.helpers.RequestHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DbHandler {
    private Statement stmt;
    private DbConnection dbConnection;

    public DbHandler() {
        if (dbConnection == null)
            dbConnection = new DbConnection();
        if (!dbConnection.isConnected())
            dbConnection.connect();
        stmt = dbConnection.getStatement();
    }

    public void disconnect() {
        dbConnection.disconnect();
    }

    public void insertData(DbModel model) {
        performStatement(SQLHelper.createInsertQuery(model));
    }

    protected boolean updateData(String sqlQuery) {
        return performStatement(sqlQuery);
    }

    public void insertData(List<DbModel> data) {
        data.stream().forEach(record -> {
            try {
                stmt.addBatch(SQLHelper.createInsertQuery(record));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        performStatement();
    }

    private boolean performStatement(String SQLstatement) {
        boolean error = false;
        try {
            stmt.execute(SQLstatement);
        } catch (SQLException e) {
            e.printStackTrace();
            error = true;
        }
        return error;
    }

    public void clearAllData() {
        try {
            stmt.addBatch("DELETE FROM przynaleznosc;");
            stmt.addBatch("DELETE FROM zerowisko;");
            stmt.addBatch("DELETE FROM transakcja;");
            stmt.addBatch("DELETE FROM zlecenie;");
            stmt.addBatch("DELETE FROM dostawa;");
            stmt.addBatch("DELETE FROM dostawca;");
            stmt.addBatch("DELETE FROM klient;");
            stmt.addBatch("DELETE FROM wycinka;");
            stmt.addBatch("DELETE FROM obszar;");
            stmt.addBatch("DELETE FROM pracownik;");
            stmt.addBatch("DELETE FROM szkolka;");
            stmt.addBatch("DELETE FROM zwierze;");
            stmt.executeBatch();
            stmt.clearBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void performStatement() {
        try {
            stmt.executeBatch();
            stmt.clearBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DbColumn> getDbColummnNamesWithTypes(String tableName) {
        String pattern = "SELECT * FROM {0} LIMIT 0;";
        String sql = MessageFormat.format(pattern, tableName);
        List<DbColumn> colNames = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                colNames.add(new DbColumn(resultSetMetaData.getColumnName(i), resultSetMetaData.getColumnType(i)));
            }
        } catch (Exception e) {
        }
        return colNames;
    }


    public String getClassName(String tableName) {
        String namePattern = "las.fill_db.models.{0}";
        char[] string = tableName.toCharArray();
        string[0] = Character.toUpperCase(string[0]);
        return MessageFormat.format(namePattern, new String(string));
    }

    public <T extends DbModel> List<DbModel> select(Map parameterMap) {
        String selectPattern = "select * from {0} where {1}";

        Map map = RequestHelper.getValidMap(parameterMap);
        String tableName = (String) map.get("tabName");
        map.remove("tabName");

        ImmutablePair<List<String>, List<String>> data = null;

        try {
            Class<T> clazz = (Class<T>) Class.forName(getClassName(tableName));
            Constructor<T> constructor = clazz.getConstructor(Map.class);
            data = constructor.newInstance(map).getKeysAndValues(true);
        } catch (Exception e) {
        }

        List<String> keys = data.getLeft();
        List<String> values = data.getRight();

        List<String> wheres = values.stream()
                .filter(value -> !value.equals(""))
                .filter(value -> !value.equals("''"))
                .map(value -> keys.get(values.indexOf(value)) + "='" + value + "';")
                .collect(Collectors.toList());

        String sql = MessageFormat.format(selectPattern, tableName, String.join(" AND ", wheres));

        return getSelectedData(tableName, sql);
    }

    public <T extends DbModel> List<DbModel> simpleSelect(String tableName) {
        String selectPattern = "SELECT * FROM {0};";
        String sql = MessageFormat.format(selectPattern, tableName);

        return getSelectedData(tableName, sql);
    }

    protected <T extends DbModel> List<DbModel> getSelectedData(String tableName, String sql) {
        List<DbModel> results = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Class<T> clazz = (Class<T>) Class.forName(getClassName(tableName));
                Constructor<T> constructor = clazz.getConstructor(ResultSet.class);
                results.add(constructor.newInstance(rs));
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
