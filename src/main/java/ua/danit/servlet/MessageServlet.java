package ua.danit.servlet;

import org.eclipse.jetty.servlet.Source;
import ua.danit.dao.UsersDAO;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageServlet extends HttpServlet {
    private UsersDAO usersDAO;

    public MessageServlet(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
