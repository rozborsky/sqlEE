package rozborskyRoman.controller;

import rozborskyRoman.controller.command.Insert;
import rozborskyRoman.model.DBManager;
import rozborskyRoman.view.Console;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by roman on 09.05.2016.
 */
public class PrepareTable {
    private DBManager manager;
    private Console view;

    public PrepareTable() {
        createDBManager();
        createConsole();
    }

    public void clearTable() throws SQLException {
        manager.clear();
        String confirmation = "y";
        InputStream iStream = new ByteArrayInputStream(confirmation.getBytes());
        System.setIn(iStream);

    }

    public void insertValues(String insertedValue) throws SQLException {
        Insert insert = new Insert(manager, view);
        InputStream inputStream = new ByteArrayInputStream(insertedValue.getBytes());

        System.setIn(inputStream);
        insert.process();
    }

    private void createDBManager() {
        manager = new DBManager();
        try {
            manager.createConnection("public", "postgres", "mainuser", "jdbc:postgresql://localhost:5432/");
        } catch (SQLException e) {
            //do noting
        }
    }

    private void createConsole() {
        view = new Console();
    }

    public DBManager getManager() {
        return manager;
    }

    public Console getView() {
        return view;
    }
}
