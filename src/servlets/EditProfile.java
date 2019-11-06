package servlets;

import Connect.FreemarkerConfigurator;
import Connect.Render;
import model.User;
import repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/edit")
@MultipartConfig
public class EditProfile extends HttpServlet {
    Map<String, Object> root = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        resp.setCharacterEncoding("utf-8");
        if (user != null) {
            FreemarkerConfigurator.getInstance(this);
            root.put("User", user);
            Render.render(req,resp,"edit.ftl", root);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        if (user == null) {
            resp.sendRedirect("/login");
        } else {
            Part filePart = req.getPart("file");
            String localdir = "uploads";
            String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
            File dir = new File(pathDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String photo_path = user.getProfilePicture();
            if (!filePart.getSubmittedFileName().equals("")) {
                String[] filename_data = filePart.getSubmittedFileName().split("\\.");
                String filename = Math.random() + "." + filename_data[filename_data.length - 1];
                String fullpath = pathDir + File.separator + filename;
                filePart.write(fullpath);
                photo_path = "/" + localdir + "/" + filename;
            }
            String name = req.getParameter("name");
            if (name.equals("")) {
                name = user.getName();
            }
            String surname = req.getParameter("surname");
            if (surname.equals("")) {
                surname = user.getSurname();
            }
            String email = req.getParameter("email");
            if (email.equals("")) {
                email = user.getEmail();
            }
            String password = req.getParameter("password");
            if (password.equals("")) {
                password = user.getPassword();
            }
            try {
                new UserRepositoryImpl().update(new User(
                        user.getId(),
                        name,
                        surname,
                        email,
                        password,
                        photo_path
                ));
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            try {
                session.setAttribute("current_user", new UserRepositoryImpl().findByID(user.getId()));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/profile");
        }
    }
}
