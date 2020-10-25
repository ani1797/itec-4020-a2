package ca.yorku.servlet;

import ca.yorku.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(
        displayName = "Login Servlet",
        value = {"/login"},
        description = "Provides an endpoint to perform login"
)
public class LoginServlet extends HttpServlet {

    private final UserService userService;

    public LoginServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(format("Received a request on an endpoint that doesn't support GET method!"));
        resp.sendError(405, "Unsupported Method, Please use POST method");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("l-username");
        String pass = req.getParameter("l-password");
        System.out.printf("Received a user '%s' and password '%s'%n", user, pass);
        final boolean isAuthenticated = this.userService.isAuthenticated(user, pass);
        if (isAuthenticated) {
            final HttpSession session = req.getSession();
            session.setAttribute("authenticated", true);
            session.setAttribute("user", this.userService.getUser(user));
            resp.sendRedirect("/a/home");
        } else {
            req.setAttribute("error", "Invalid Username or Password!");
            req.getRequestDispatcher("/").include(req, resp);
        }
    }

}
