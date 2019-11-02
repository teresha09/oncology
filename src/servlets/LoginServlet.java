package servlets;

import Connect.FreemarkerConfigurator;
import Connect.Render;
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

    Map<String, Object> root = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setCharacterEncoding("utf-8");
        User user = (User) session.getAttribute("current_user");
        if (user != null) {
            resp.sendRedirect("/main");
        } else {
            try {
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                User user1 = new UserRepositoryImpl().validateUser(email, password);
                if (user1 != null) {
                    if (req.getParameter("remember") != null) {
                        Cookie cookieMail = new Cookie("emailcookie",email);
                        Cookie cookiePassword = new Cookie("passwordcookie", password);
                        cookieMail.setMaxAge(60 * 60 * 24 * 10000);
                        cookiePassword.setMaxAge(60 * 60 * 24 * 10000);
                        cookieMail.setPath("/");
                        cookiePassword.setPath("/");
                        resp.addCookie(cookieMail);
                        resp.addCookie(cookiePassword);
                    }
                    session.setAttribute("current_user", user1);
                    resp.sendRedirect("/main");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (session.getAttribute("current_user") == null) {
                root.put("Error", "No such user");
                resp.sendRedirect("/login");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerConfigurator.getInstance(this);
        HttpSession session = req.getSession();
        Map<String, Object> root = new HashMap<>();
        root.put("context", req.getContextPath());
        resp.setCharacterEncoding("utf-8");
        User user = (User) session.getAttribute("current_user");
        Cookie[] cookies = req.getCookies();
        String email = "";
        String password = "";
        if (user == null && cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("emailcookie")){
                    email = cookie.getValue();
                }
                if (cookie.getName().equals("passwordcookie")){
                    password = cookie.getValue();
                }
            }
            try {
                user = new UserRepositoryImpl().validateUser(email, password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } if (user != null) {
            session.setAttribute("current_user", user);
            resp.sendRedirect("/profile");
        } else {
            Render.render(req,resp,"login.ftl", root);
        }
    }
}
