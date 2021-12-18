package main;

import accounts.UserHuuzer;
import services.UserService;


public class Main {

    public static void main(String[] args) throws Exception {


        UserService userService = new UserService();
        userService.saveUser( new UserHuuzer("Some", "One1"));

/*
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlerList =  new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
        System.out.println("Server started");
        server.join();

    */
    }
}