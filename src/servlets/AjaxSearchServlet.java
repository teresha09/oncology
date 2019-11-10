package servlets;

import model.Post;
import org.json.JSONObject;
import repository.PostRepositoryImpl;
import org.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/dosearch")
public class AjaxSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String title = req.getParameter("query");
        try {
            ArrayList<Post> posts = new PostRepositoryImpl().findByTitleLike(title);
            JSONArray jsonArray = new JSONArray();
            for (Post post : posts) {
                jsonArray.put(new JSONObject(post));
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objects", jsonArray);
            resp.setContentType("text/json");
            resp.getWriter().write(jsonObject.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
