import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import resource.CounterResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class JerseyApplication extends Application {

  public Set<Class<?>> getClasses() {
    return new HashSet<Class<?>>(Arrays.asList(CounterResource.class));
  }

  private static Server createServer() {
    Server server = new Server(8080);
//
    ServletContextHandler ctx = new ServletContextHandler();
//
    server.setHandler(ctx);
//
    ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
//
    servletHolder.setInitParameter("javax.ws.rs.Application", "JerseyApplication");
    servletHolder.setInitParameter("jersey.config.server.provider.classnames",
            "org.glassfish.jersey.jackson.JacksonFeature");
//
    return server;
  }

  public static void main(String[] args) throws Exception {
//    // run, Jetty, run!
    Server server = createServer();
    server.start();
    server.join();
  }

}
