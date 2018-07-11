package ua.danit.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.dao.UsersDAO;
import ua.danit.filter.LoginFilter;
import ua.danit.servlet.LikedServlet;
import ua.danit.servlet.MessageServlet;
import ua.danit.servlet.UsersServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class LocalTinderServer {

    public void start(String port) throws Exception {
        final UsersDAO usersDAO = new UsersDAO();
//        final LikedDAO likedDAO = new LikedDAO();

        new Server(Integer.parseInt(port)){{
            setHandler(new ServletContextHandler(){{
                addServlet(new ServletHolder(new UsersServlet(usersDAO)),
                        "/users/*");
                addServlet(new ServletHolder(new LikedServlet(usersDAO)),
                        "/liked/*");
                addServlet(new ServletHolder(new MessageServlet(usersDAO)),
                        "/message/*");
                addFilter(new FilterHolder(new LoginFilter()),"/*",
                        EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD));
            }});
            start();
            join();
        }};
    }
}
