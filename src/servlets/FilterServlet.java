package servlets;

import javafx.util.Pair;
import model.User;
import repository.UserRepositoryImpl;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("current_user");
        String path = req.getServletPath();
        if (path.equals("/login") || path.equals("/registration")) {
            if (user == null) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect("/profile");
            }
        } else {

                if (user != null) {
                    filterChain.doFilter(req, servletResponse);
                } else {
                    Cookie[] cookies = req.getCookies();
                    String email = "";
                    String password = "";
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("emailcookie")) {
                                email = cookie.getValue();
                            }
                            if (cookie.getName().equals("passwordcookie")) {
                                password = cookie.getValue();
                            }
                        }
                        try {
                            user = new UserRepositoryImpl().validateUser(email, password);
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if (user != null) {
                        req.getSession().setAttribute("current_user", user);
                    }
                    if (user == null && path.equals("/profile") || path.equals("/add_post") ||
                            path.equals("/edit")) {
                        resp.sendRedirect("/login");
                    } else {
                        filterChain.doFilter(req, servletResponse);
                    }
                }
            }
        }


    @Override
    public void destroy() {

    }
}
