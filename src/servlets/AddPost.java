package servlets;

import Connect.FreemarkerConfigurator;
import Connect.Render;
import model.Post;
import model.User;
import repository.PostRepositoryImpl;
import repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/addpost")
@MultipartConfig
public class AddPost extends HttpServlet {
    Map<String, Object> root = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        FreemarkerConfigurator.getInstance(this);
        root.put("User", user);
        Render.render(req, resp, "addpost.ftl", root);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        Part filePart = req.getPart("file");
        String localdir = "uploads";
        String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
        File dir = new File(pathDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String[] filename_data = filePart.getSubmittedFileName().split("\\.");
        String filename = Math.random() + "." + filename_data[filename_data.length - 1];
        String fullpath = pathDir + File.separator + filename;
        filePart.write(fullpath);
        String photo_path = "/" + localdir + "/" + filename;

        DateFormat format = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        String date = format.format(Calendar.getInstance().getTime());
        try {
            new PostRepositoryImpl().create(new Post(
                    0,
                    req.getParameter("title"),
                    req.getParameter("text"),
                    date,
                    user,
                    photo_path,
                    req.getParameter("category")
            ));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        String redir = "/post";
        try {
            redir = redir + "?id=" + new PostRepositoryImpl().findByName(req.getParameter("title")).getId();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(redir);
    }
}

