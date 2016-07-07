package rozborskyRoman.interfaces;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by roman on 07.07.2016.
 */
public interface Connector {
    public void createConnection(DataBase manager, String databaseName, String userName, String password)
            throws IOException, SQLException;
}
