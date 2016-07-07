package rozborskyRoman.interfaces;

import java.sql.*;

/**
 * Created by roman on 07.07.2016.
 */
public interface DataBase {

    public Connection createConnection(String database, String userName, String password, String url) throws SQLException ;

    public boolean clear() throws SQLException;

    public boolean delete(String command) throws SQLException;

    public boolean insert(String enteredValues, String columns) throws SQLException;

    public boolean update(int idColumn, String changedColumns, String[] enteredData) throws SQLException;

    public boolean isExists(int id);

    public String[] list() throws SQLException;

    public int getTableWidth() throws SQLException;

    public int getTableHight() throws SQLException;

    public String[] getColumnNames() throws SQLException;

    public String[] getRows() throws SQLException;

    public void destroyConnection();

    public void setTable(String table);

    public String getDatabase();

    public String getTable();
}
