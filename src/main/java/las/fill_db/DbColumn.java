package las.fill_db;

import java.sql.Types;

/**
 * Created by Development on 18-May-16.
 */
public class DbColumn {
    private String name;
    private String type;

    public DbColumn(String name, int type) {
        this.name = name;
        switch (type) {
            case Types.INTEGER:
                this.type = "number";
                break;
            case Types.REAL:
                this.type = "number";
                break;
            case Types.VARCHAR:
                this.type = "text";
                break;

            default:
                this.type = "text";
        }
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
