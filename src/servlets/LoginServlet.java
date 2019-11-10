package servlets;

import Connect.FreemarkerConfigurator;
import Connect.Render;
import Connect.Password;
import model.User;
import repository.UserRepositoryImpl;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public Map<String, Object> root = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setCharacterEncoding("utf-8");
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = new UserRepositoryImpl().validateUser(email,Password.hasher(password));
            if (user != null) {
                if (req.getParameter("remember") != null) {
                    Cookie cookieMail = new Cookie("emailcookie", email);
                    Cookie cookiePassword = new Cookie("passwordcookie", Password.hasher(password));
                    cookieMail.setMaxAge(60 * 60 * 24 * 10000);
                    cookiePassword.setMaxAge(60 * 60 * 24 * 10000);
                    cookieMail.setPath("/");
                    cookiePassword.setPath("/");
                    resp.addCookie(cookieMail);
                    resp.addCookie(cookiePassword);
                }
                session.setAttribute("current_user", user);
                resp.sendRedirect("/profile");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (session.getAttribute("current_user") == null) {
            root.put("Error", "No such user");
            resp.sendRedirect("/login");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerConfigurator.getInstance(this);
        HttpSession session = req.getSession();
        Render.render(req, resp, "login.ftl", root);
    }
}

