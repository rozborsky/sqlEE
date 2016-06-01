package rozborskyRoman.controller.web;

import rozborskyRoman.controller.command.ExitException;
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
    static {//todo replase
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExitException();
        }
    }

    private Service service;

    @Override
    public void init() throws ServletException {
        super.init();

        service = new ServiseImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);

        request.setAttribute("items", service.commandsList());
        if (action.startsWith("/menu") || action.equals("/")) {
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else if (action.startsWith("/connect")) {
            request.getRequestDispatcher("connect.jsp").forward(request, response);
        } else if (action.startsWith("/help")) {
            request.getRequestDispatcher("help.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
                response.sendRedirect(response.encodeRedirectURL("menu"));
            } catch (SQLException e) {
                request.setAttribute("items", service.commandsList());
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

}
