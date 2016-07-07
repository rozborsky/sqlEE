package rozborskyRoman.service;

import org.springframework.stereotype.Component;
import rozborskyRoman.interfaces.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by roman on 31.05.2016.
 */
@Component
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
