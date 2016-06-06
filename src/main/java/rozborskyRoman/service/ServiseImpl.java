package rozborskyRoman.service;

import rozborskyRoman.model.DBManager;
import rozborskyRoman.view.InputOutput;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by roman on 31.05.2016.
 */
public class ServiseImpl implements Service {

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "exit");
    }

    @Override
    public List<String> commands() {
        return Arrays.asList("insert", "update", "delete", "clear");
    }
}
