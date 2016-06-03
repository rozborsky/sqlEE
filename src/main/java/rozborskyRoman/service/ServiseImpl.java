package rozborskyRoman.service;

import rozborskyRoman.model.DBManager;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by roman on 31.05.2016.
 */
public class ServiseImpl implements Service {

    DBManager manager;

    public ServiseImpl(DBManager manager) {

        this.manager = manager;
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "exit");
    }

    @Override
    public List<String> commands() {
        return Arrays.asList("insert", "update", "delete", "clear");
    }

    @Override
    public void connect(String databaseName, String userName, String password) throws SQLException {
        manager.createConnection(databaseName, userName, password, "jdbc:postgresql://localhost:5432/");
    }
}
