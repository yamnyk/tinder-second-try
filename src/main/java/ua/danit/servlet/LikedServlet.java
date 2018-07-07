package ua.danit.servlet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ua.danit.dao.UsersDAO;
import ua.danit.entity.User;
import ua.danit.utils.FreeMarkerConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/liked")
public class LikedServlet extends HttpServlet{
    private UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Map<String, Object> var = new HashMap<>();
        List<User> users = usersDAO.returnAllUsers();
        var.put("users", users);
        String tmplName = "liked.html";

        FreeMarkerConfig.proccesTemplate(writer, var, tmplName);
    }
}
