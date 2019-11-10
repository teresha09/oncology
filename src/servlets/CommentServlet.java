package servlets;

import model.Comment;
import model.User;
import repository.CommentRepositoryImpl;
import repository.PostRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        User user = (User) req.getSession().getAttribute("current_user");
        System.out.println(req.getParameter("idPost"));
        try {
            new CommentRepositoryImpl().create(new Comment(
                    0,
                    req.getParameter("comment"),
                    date,
                    (User) req.getSession().getAttribute("current_user"),
                    new PostRepositoryImpl().findByID(Integer.parseInt(req.getParameter("idPost")))
            ));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/post?id=" + req.getParameter("idPost"));
    }
}
