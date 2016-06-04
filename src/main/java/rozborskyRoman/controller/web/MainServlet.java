package rozborskyRoman.controller.web;

import rozborskyRoman.model.DBManager;
import rozborskyRoman.service.Service;
import rozborskyRoman.service.ServiseImpl;
import rozborskyRoman.view.InputOutput;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by roman on 30.05.2016.
 */
public class MainServlet extends HttpServlet {

    DBManager manager;
    boolean isConnect = false;
    String table = null;

    static {//todo replase
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            //throw new ExitException();
        }
    }

    private Service service;

    @Override
    public void init() throws ServletException {
        super.init();

        manager = new DBManager();

        service = new ServiseImpl(manager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);

        request.setAttribute("items", service.commandsList());
        if (action.equals("/")) {
            request.getRequestDispatcher("start.jsp").forward(request, response);
        }
        if (isConnect) {
            String[] rows = new String[]{"chose table"};

            if (table != null) {
                try {
                    rows = manager.getRows();
                } catch (SQLException e) {
                    rows = new String[]{"table hasn't rows"};
                }
            }

            request.setAttribute("content", rows);
            if (action.startsWith("/mainPage")) {
                setTable(request, response);
                table = manager.getTable();
                request.setAttribute("tableName", table);
                request.setAttribute("commands", service.commands());
                request.getRequestDispatcher("mainPage.jsp").forward(request, response);
            } else if (action.startsWith("/exit")) {
                manager.destroyConnection();
                isConnect = false;
                table = null;
                request.getRequestDispatcher("start.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("start.jsp").forward(request, response);
        }
    }

    private void setTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] listTables = new String[]{"Database hasn't tables"};
        try {
            listTables = manager.list();
        } catch (SQLException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("tables", listTables);
    }

    private String getAction(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.substring(request.getContextPath().length(), requestURI.length());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.startsWith("/connect")) {
            String databaseName = request.getParameter("dbname");
            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                service.connect(databaseName, userName, password);
                isConnect = true;
                response.sendRedirect(response.encodeRedirectURL("mainPage"));
            } catch (SQLException e) {
                request.setAttribute("items", service.commandsList());
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if (action.startsWith("/tableName")) {
            String table = request.getParameter("table");
            manager.setTable(table);
            response.sendRedirect(response.encodeRedirectURL("mainPage"));
        } else if (action.startsWith("/comman")) {
            String table = request.getParameter("table");
            manager.setTable(table);
            request.setAttribute("table", table);
            response.sendRedirect(response.encodeRedirectURL("mainPage"));
        }
    }


    private String getUrl(InputOutput view) {
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties property = new Properties();

        try {
            property.load(input);
        } catch (Exception e) {
            view.error("Can't find property file ", e);
        }
        return property.getProperty("url");
    }


//    try {
//        id = Integer.parseInt(command);
//    } catch (Exception e) {
//        view.write("Insert correct id");
//        continue;
//    }
//    if (manager.isExists(id)) {
//        if (manager.delete(command)) {
//            view.write("Row was removed");
//            return;
//        }
//    }
}
