package las.fill_db.models;

import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by Development on 21.04.2016.
 */
public class Obszar extends DbModel {
    public final static String ID_ZWIERZECIA = "nr_dzialki";
    public final static String GATUNEK = "powierzchnia";

    public Obszar(int nrDzialki, int powierzchnia) {
        super();
        fields.put(ID_ZWIERZECIA, nrDzialki);
        fields.put(GATUNEK, powierzchnia);
    }

    public Obszar(ResultSet resultSet) {
        super(resultSet);
    }

    public Obszar(Map fields) {
        super(fields);
    }

    @Override
    protected void init() {
        tableName = "obszar";
    }
}
