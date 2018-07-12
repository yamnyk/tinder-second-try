package ua.danit.servlet;

import ua.danit.dao.UsersDAO;
import ua.danit.entity.User;
import ua.danit.utils.FreeMarkerConfig;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginServlet extends HttpServlet{
    private UsersDAO userDAO;

    public LoginServlet(UsersDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        FreeMarkerConfig.proccesTemplate(writer, new HashMap<>(),"login.html", this.getClass());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        User user = userDAO.getUserByLoginAndPassword(login, pass);

        if(user == null){
            resp.getWriter().write("Wrong credentials");
        } else {
            Cookie cookie = new Cookie("userId", String.valueOf(user.getId()));
            resp.addCookie(cookie);
            resp.sendRedirect("/users");
        }

    }
}
