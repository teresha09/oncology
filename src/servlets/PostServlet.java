package servlets;


import Connect.FreemarkerConfigurator;
import Connect.Render;
import model.Post;
import model.User;
import repository.CommentRepositoryImpl;
import repository.PostRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/post")
@MultipartConfig
public class PostServlet extends HttpServlet {
    Map<String,Object> root = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerConfigurator.getInstance(this);
        resp.setCharacterEncoding("utf-8");

        User user = (User) req.getSession().getAttribute("current_user");
        root.put("User", user);
        if (req.getParameter("id") != null) {
            try {
                Post post = new PostRepositoryImpl().findByID(Integer.parseInt(req.getParameter("id")));
                root.put("Post", post);
                root.put("Comments", new CommentRepositoryImpl().findByPost(Integer.parseInt(req.getParameter("id"))));
            }catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            Render.render(req,resp,"post.ftl", root);
        } else {
            resp.sendRedirect("/main");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
