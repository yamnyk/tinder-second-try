package ua.danit.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.dao.UsersDAO;
import ua.danit.servlet.LikedServlet;
import ua.danit.servlet.MessageServlet;
import ua.danit.servlet.UsersServlet;

public class LocalTinderServer {

    public void start() throws Exception {
        final UsersDAO usersDAO = new UsersDAO();
//        final LikedDAO likedDAO = new LikedDAO();

        new Server(8080){{
            setHandler(new ServletContextHandler(){{
                addServlet(new ServletHolder(new UsersServlet(usersDAO)),
                        "/users/*");
                addServlet(new ServletHolder(new LikedServlet(usersDAO)),
                        "/liked/*");
                addServlet(new ServletHolder(new MessageServlet(usersDAO)),
                        "/message/*");

            }});
            start();
            join();
        }};
    }
}
