package ua.danit.servlet;

import org.eclipse.jetty.servlet.Source;
import ua.danit.dao.UsersDAO;
import ua.danit.utils.FreeMarkerConfig;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageServlet extends HttpServlet {
    private UsersDAO usersDAO;

    public MessageServlet(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        Map<String, Object> variables = new HashMap<>();
        List<String> messages = new ArrayList<>();
        messages.add("Hello");
        messages.add("Hi");
        messages.add("How are you?");
        variables.put("messages", messages);

        FreeMarkerConfig.proccesTemplate(writer, variables, "messages.html", this.getClass());
    }
}
