package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cookie cookieMail = new Cookie("emailcookie", null);
        Cookie cookiePassword = new Cookie("passwordcookie", null);
        cookieMail.setMaxAge(0);
        cookiePassword.setMaxAge(0);
        resp.addCookie(cookieMail);
        resp.addCookie(cookiePassword);
        session.removeAttribute("current_user");
        resp.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
