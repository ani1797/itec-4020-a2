package ca.yorku.servlet;

import ca.yorku.model.User;
import ca.yorku.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(
        displayName = "Registration Servlet",
        value = "/register",
        description = "This provides a way to add new user accounts so access can be provided"
)
public class RegistrationServlet extends HttpServlet {

    private UserService userService;

    public RegistrationServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Received a request on an endpoint that doesn't support GET method!");
        resp.sendError(405, "Unsupported Method, Please use POST method");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("s-username");
        final String password = req.getParameter("s-password");
        final String first = req.getParameter("s-first");
        final String last = req.getParameter("s-last");

        Collection<User> users = userService.addUser(new User(first, last, username, password));
        System.out.printf("Current user count %d%n", users.size());

        req.setAttribute("users", users);
        resp.sendRedirect("/");
    }


}
