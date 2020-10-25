package ca.yorku.servlet;

import ca.yorku.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        displayName = "Authenticated Hello Page",
        value = {"/a/home", "/a/"},
        description = "This endpoint serves as a authenticated endpoint which simply shows a hello page"
)
public class AuthenticatedRouteServlet extends HttpServlet {

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.setAttribute("name", user.getName());
        request.setAttribute("username", user.getUsername());
        request.getRequestDispatcher("/pages/authenticated_page.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.process(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.process(req, res);
    }

}
