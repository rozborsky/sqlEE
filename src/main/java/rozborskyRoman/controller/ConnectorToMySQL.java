package rozborskyRoman.controller;

import org.springframework.stereotype.Component;
import rozborskyRoman.interfaces.Connector;
import rozborskyRoman.interfaces.DataBase;
import rozborskyRoman.model.DBManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by roman on 06.06.2016.
 */

@Component
public class ConnectorToMySQL implements Connector{

    public void createConnection(DataBase manager, String databaseName, String userName, String password)
            throws IOException, SQLException {
        manager.createConnection(databaseName, userName, password, getUrl());
    }

    private String getUrl() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties property = new Properties();
        property.load(input);

        return property.getProperty("url");
    }
}
