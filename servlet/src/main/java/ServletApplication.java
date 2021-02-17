import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServletApplication {

  private static Server createServer() {
    Server server = new Server(8080);
    ServletHandler servletHandler = new ServletHandler();

    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(ClearCounterServlet.class, "/counter/clear");
    server.setHandler(servletHandler);

    return server;
  }

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    Server server = createServer();
    server.start();
    server.join();
  }

}
