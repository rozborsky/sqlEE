package rozborskyRoman.service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by roman on 31.05.2016.
 */
public interface Service {
    List<String> commands();

    List<String> commandsList();

    void connect(String databaseName, String userName, String password) throws SQLException;
}
