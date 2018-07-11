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
    private static Boolean choice;
    private int currentUserIndex = 0;

    public UsersServlet(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(currentUserIndex >= usersDAO.returnAllUsers().size()){
            resp.sendRedirect("/liked");
            currentUserIndex = 0;
            return;
        }

        PrintWriter writer = resp.getWriter();
        String tmplName = "htmlFile.html";

        Map<String, Object> var = new HashMap<>();
        User user = usersDAO.getUserByIndex(currentUserIndex);
        var.put("usr", user);

        FreeMarkerConfig.proccesTemplate(writer, var, tmplName);

//        PrintWriter writer = resp.getWriter();

/*        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Index</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>"+user.getName()+"</h1>\n" +
                "<img width=\"400\" src='"+user.getPhoto()+"'>\n" +
                "<form acton='/users' method='POST'>\n" +
                "<br><button name=\"choise\" value='Yes'>Yes</button>\n" +
                "<button name=\"choise\" value='No'>No</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userChoice = req.getParameter("choise");

        usersDAO.getUserByIndex(currentUserIndex).setLiked("yes".equals(userChoice));
        currentUserIndex++;
        doGet(req, resp);

    }
}
