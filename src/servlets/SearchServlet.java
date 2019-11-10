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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    Map<String, Object> root = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerConfigurator.getInstance(this);
        resp.setCharacterEncoding("utf-8");
        User user =(User) req.getSession().getAttribute("current_user");
        root.put("User",user);
        Render.render(req,resp,"search.ftl",null);
    }
}
