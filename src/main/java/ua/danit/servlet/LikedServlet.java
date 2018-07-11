package ua.danit.servlet;

import ua.danit.dao.UsersDAO;
import ua.danit.entity.User;
import ua.danit.utils.FreeMarkerConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LikedServlet extends HttpServlet{
    private UsersDAO usersDAO;

    public LikedServlet(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        Map<String, Object> var = new HashMap<>();
        List<User> users = usersDAO.getLikedUsers();

        var.put("users", users);

        FreeMarkerConfig.proccesTemplate(writer, var, "liked.html", this.getClass());
    }
}