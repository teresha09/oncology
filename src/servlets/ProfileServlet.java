package servlets;

import Connect.FreemarkerConfigurator;
import Connect.Render;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    Map<String,Object> root = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerConfigurator.getInstance(this);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        if (user == null) {
            resp.sendRedirect("/login");
        } else {
            resp.setCharacterEncoding("utf-8");
            root.put("User",user);
            Render.render(req, resp, "profile.ftl", root);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
