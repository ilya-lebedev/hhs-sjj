package resource;

import counter.Counter;
import counter.Result;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/counter")
public class CounterResource {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        Result result = new Result();
        result.setValue(Counter.get());
        result.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return Response.ok(result).build();
    }

    @POST
    @Path("/")
    public Response increment() {
        Counter.increment();

        return Response.ok().build();
    }

    @DELETE
    @Path("/")
    public Response decrement(@HeaderParam("Subtraction-Value") String header) {
        try {
            long value = Long.parseLong(header);
            Counter.decrement(value);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/clear")
    public Response clear(@CookieParam("hh-auth") String cookie) {
        if (cookie != null && cookie.length() > 10) {
            Counter.clear();
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
