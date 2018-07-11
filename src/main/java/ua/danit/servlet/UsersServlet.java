package ua.danit.servlet;

import ua.danit.dao.UsersDAO;
import ua.danit.entity.User;
import ua.danit.utils.FreeMarkerConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UsersServlet extends HttpServlet{
    private UsersDAO usersDAO;

    public UsersServlet(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        Map<String, Object> variables = new HashMap<>();
        User user = usersDAO.getNotLikedUser();

        if(user == null){
            resp.sendRedirect("/liked");
            return;
        }

        variables.put("user", user);
        FreeMarkerConfig.proccesTemplate(pw, variables, "users.html",this.getClass());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userChoice = req.getParameter("choise");
        String userId = req.getParameter("userId");

        usersDAO.saveLike(userId, "yes".equals(userChoice));

        doGet(req, resp);

    }
}
