package ca.yorku.servlet;


import ca.yorku.model.User;
import ca.yorku.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static java.lang.String.format;

@WebServlet(
        displayName = "Home Servlet",
        value = "/",
        description = "This serves our homepage to the users"
)
public class HomeServlet extends HttpServlet {

    private final UserService userService;

    public HomeServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isAuthenticated = req.getSession().getAttribute("authenticated") != null ? (boolean) req.getSession().getAttribute("authenticated") : false;
        if (!isAuthenticated) {
            String users = this.userService.getUsers().parallelStream().map(User::getName)
                    .map(name -> format("%s %s", name.getFirst(), name.getLast()).trim())
                    .collect(Collectors.joining(", "));
            System.out.printf("Loading homepage......[%s]%n", users);
            req.setAttribute("users", users);
            req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/a/home").forward(req, resp);
        }
    }
    
}
