package servlets;

import Connect.FreemarkerConfigurator;
import Connect.Render;
import javafx.geometry.Pos;
import model.Post;
import model.User;
import repository.PostRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            root.put("User", user);
        }
        String category = req.getParameter("category");
        try {
            ArrayList<ArrayList<Post>> posts = divByThree(new PostRepositoryImpl().findByCategory(category));
            root.put("Category", category);
            root.put("Posts", posts);
                    } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Render.render(req,resp,"posts_list.ftl",root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public ArrayList<ArrayList<Post>> divByThree(ArrayList<Post> posts) {
        ArrayList<ArrayList<Post>> res = new ArrayList<>();
        for (int i = 0; i < posts.size() - 3; i += 3) {
            ArrayList<Post> buf = new ArrayList<>();
            for (int j = i; j < i + 3; j++) {
                buf.add(posts.get(j));
            }
            res.add(buf);
        }
        ArrayList<Post> buf = new ArrayList<>();
        for (int i = res.size() * 3; i < posts.size(); i++) {
            buf.add(posts.get(i));
        }
        res.add(buf);
        return res;
    }
}
