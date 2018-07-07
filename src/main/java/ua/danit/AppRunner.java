package ua.danit;

import ua.danit.utils.LocalTinderServer;

public class AppRunner {
    public static void main(String[] args) throws Exception {
        LocalTinderServer server = new LocalTinderServer();
        server.start();
    }
}
