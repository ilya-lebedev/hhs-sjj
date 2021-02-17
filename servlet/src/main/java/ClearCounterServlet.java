import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearCounterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("hh-auth")) {
                    if (cookie.getValue().length() > 10) {
                        Counter.clear();
                        resp.sendRedirect("/counter");
                        return;
                    }
                }
            }
        }

        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
