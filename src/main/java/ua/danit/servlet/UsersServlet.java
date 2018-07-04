package ua.danit.servlet;
import ua.danit.dao.UsersDAO;
import ua.danit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet{
    private UsersDAO usersDAO = new UsersDAO();
    private static Boolean choice;
    private int currentUserIndex = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        User user = usersDAO.getUserByIndex(currentUserIndex);

        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Index</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>"+user.getName()+"</h1>\n" +
                "<img width=\"400\" src='"+user.getImgURL()+"'>\n" +
                "<form acton='/users' method='POST'>\n" +
                "<br><button name=\"choise\" value='Yes'>Yes</button>\n" +
                "<button name=\"choise\" value='No'>No</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        currentUserIndex++;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userChoice = req.getParameter("choise");

        choice = "Yes".equals(userChoice);

        doGet(req, resp);

    }
}
