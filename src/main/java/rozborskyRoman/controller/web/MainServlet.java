package rozborskyRoman.controller.web;

import rozborskyRoman.controller.Connector;
import rozborskyRoman.model.DBManager;
import rozborskyRoman.service.Service;
import rozborskyRoman.service.ServiseImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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
        service = new ServiseImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);

        request.setAttribute("navigationLinks", service.commandsList());
        if (action.equals("/")) {
            request.getRequestDispatcher("start.jsp").forward(request, response);
        }
        if (isConnect) {
            workWithDatabase(request, response, action);
        } else {
            request.getRequestDispatcher("start.jsp").forward(request, response);
        }
    }

    private void workWithDatabase(HttpServletRequest request, HttpServletResponse response, String action)
            throws ServletException, IOException {
        if (action.startsWith("/mainPage")) {
            initMainPage(request, response);
        } else if (action.startsWith("/insert")) {
            request.getRequestDispatcher("insert.jsp").forward(request, response);
        } else if (action.startsWith("/exit")) {
            exit(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


    private void initMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        table = manager.getTable();
        String help = "SQL cmd - is a program that allows you to work with the database. " +
                "Implemented using the technology of JSP.";
        String[] rows;
        if (table == null) {
            request.setAttribute("tableName", help);
        } else {
            request.setAttribute("tableName", table);
            showTableData(request);
        }
        setNavigation(request, response);
        request.getRequestDispatcher("mainPage.jsp").forward(request, response);
    }

    private void setNavigation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        tableList(request, response);
        request.setAttribute("commands", service.commands());
        request.setAttribute("navigationLinks", service.commandsList());
    }

    private void showTableData(HttpServletRequest request) {
        String[] rows;
        try {
            rows = manager.getRows();
        } catch (SQLException e) {
            rows = new String[]{"table hasn't rows"};
        }
        request.setAttribute("content", rows);
    }

    private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        manager.destroyConnection();
        isConnect = false;
        table = null;
        request.getRequestDispatcher("start.jsp").forward(request, response);
    }

    private void tableList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            createConnection(request, response);
        } else if (action.startsWith("/selectTable")) {
            chooseTable(request, response);
        } else if (action.startsWith("/selectAction")) {
            chooseCommand(request, response);
        } else if (action.startsWith("/insertValues")) {
            insert(request, response);
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idUser = request.getParameter("idUser");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try {
            int id = Integer.parseInt(idUser);
            String values = "" + id + "', '" + name + "', '" + password;
            String columns = "id, name, password";
            manager.insert(values, columns);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        request.setAttribute("tableName", table);
        showTableData(request);
        setNavigation(request, response);
        request.getRequestDispatcher("insert.jsp").forward(request, response);
    }

    private void chooseCommand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("action");
        request.setAttribute("command", command);
        if (command.equals("insert")) {
            response.sendRedirect(response.encodeRedirectURL("insert"));
        } else if (command.equals("update")) {
            response.sendRedirect(response.encodeRedirectURL("update"));
        } else if (command.equals("delete")) {
            response.sendRedirect(response.encodeRedirectURL("delete"));
        } else if (command.equals("clear")) {
            response.sendRedirect(response.encodeRedirectURL("clear"));
        }
    }

    private void chooseTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.table = request.getParameter("table");
        manager.setTable(table);
        response.sendRedirect(response.encodeRedirectURL("mainPage"));
    }

    private void createConnection(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String databaseName = request.getParameter("dbname");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connector connector = new Connector();
            connector.createConnection(manager, databaseName, userName, password);
            isConnect = true;
            response.sendRedirect(response.encodeRedirectURL("mainPage"));
        } catch (SQLException e) {
            error(request, response, e.getMessage());
        }
    }

    private void error(HttpServletRequest request, HttpServletResponse response, String message2) throws ServletException, IOException {
        request.setAttribute("navigationLinks", service.commandsList());
        request.setAttribute("message", message2);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
