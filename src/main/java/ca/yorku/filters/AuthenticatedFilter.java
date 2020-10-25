package ca.yorku.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        description = "This filter restricts access to pages without authentication",
        filterName = "Authentication Filter",
        urlPatterns = {"/a/*", "/pages/*"}
)
public class AuthenticatedFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        boolean isAuthenticated = session.getAttribute("authenticated") != null ? (boolean) session.getAttribute("authenticated") : false;
        if (isAuthenticated) {
            chain.doFilter(req, res);
        } else {
            System.out.println("Unauthenticated request is not allowed to any endpoints in /a/*! Please login first to continue");
            res.sendError(401, "All routes are protected and must authenticate before continuing!");
        }
    }

}
