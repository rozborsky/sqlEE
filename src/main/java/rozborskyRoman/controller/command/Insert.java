package rozborskyRoman.controller.command;

import rozborskyRoman.model.DBManager;
import rozborskyRoman.view.InputOutput;

import java.sql.SQLException;

/**
 * Created by roman on 18.03.2016.
 */
public class Insert {
    protected int tableWidth;
    protected InputOutput view;
    protected String message = "Insert";
    protected String[] enteredData;
    protected String table;
    protected DBManager manager;

    public Insert(DBManager manager, InputOutput view) {
        this.view = view;
        this.manager = manager;
    }


    public void process() throws SQLException {
        tableWidth = manager.getTableWidth();
        table = manager.getTable();

        String showColumns = columns(manager.getColumnNames(), "|");


        if (tableWidth == 0) {
            view.write("Table '" + table + "' has't columns. You cant work with table");
        } else {
            insertData(view, showColumns);
        }
    }

    private void insertData(InputOutput view, String showColumns) throws SQLException {
        do {

            try {
                Integer.parseInt(enteredData[0]);
            } catch (Exception e) {
                view.write("Insert correct id");
                continue;
            }

            String columns = columns(manager.getColumnNames(), ", ");
            String enteredValues = columns(enteredData, "', '");

            request(enteredValues, columns);
        } while ((enteredData.length != tableWidth) || enteredData[0].equals("back"));
    }

    protected void request(String enteredValues, String columns) throws SQLException {
        try {
            manager.insert(enteredValues, columns);
            view.write(String.format("\nTable '%s' was updated", table));

        } catch (SQLException e) {
            throw new SQLException((String.format("Cant insert values in to the table '%s'\n", manager.getTable()) + e.getMessage()));
        }
    }

    protected String columns(String[] array, String format) {
        String columns = "";
        for (int i = 0; i < array.length; i++) {
            columns += array[i];
            if (i + 1 != array.length) {
                columns += format;
            }
        }
        return columns;
    }

}