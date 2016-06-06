package rozborskyRoman.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by roman on 31.05.2016.
 */
public interface Service {
    List<String> commands();

    List<String> commandsList();
}
