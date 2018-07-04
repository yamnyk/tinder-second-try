package ua.danit.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Index</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Joanna</h1>\n" +
                "<img width=\"400\" src=\"data:image/jpeg;base64,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\">\n" +
                "<br><button>Yes</button>\n" +
                "<form><button>No</button></form>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
