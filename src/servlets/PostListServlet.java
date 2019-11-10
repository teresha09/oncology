package servlets;

import Connect.FreemarkerConfigurator;
import Connect.Render;
import model.User;
import repository.PostRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/posts")
public class PostListServlet extends HttpServlet {
    Map<String, Object> root = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerConfigurator.getInstance(this);
        resp.setCharacterEncoding("utf-8");
        User user = (User) req.getSession().getAttribute("current_user");
        if (user != null) {
            root.put("user", user);
        }
        String category = req.getParameter("category");
        try {
            root.put("Posts", new PostRepositoryImpl().findByCategory(category));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Render.render(req,resp,"posts_list.ftl",root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
