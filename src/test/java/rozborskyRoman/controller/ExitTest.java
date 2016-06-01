package rozborskyRoman.controller;

import org.junit.Test;
import rozborskyRoman.controller.command.Exit;
import rozborskyRoman.controller.command.ExitException;
import rozborskyRoman.view.Console;

/**
 * Created by roman on 05.05.2016.
 */
public class ExitTest {
    @Test(expected = ExitException.class)
    public void process() {
        Console view = new Console();
        Exit exit = new Exit(view);
        exit.process();
    }
}