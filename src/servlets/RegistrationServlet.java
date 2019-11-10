package servlets;

import Connect.Connect;
import Connect.Render;
import Connect.Password;
import model.User;
import repository.UserRepositoryImpl;
import Connect.FreemarkerConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


@WebServlet("/registration")
@MultipartConfig
public class RegistrationServlet extends HttpServlet {
    Map<String, Object> root = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerConfigurator.getInstance(this);
        HttpSession session = req.getSession();
        Map<String, Object> root = new HashMap<>();
        root.put("context", req.getContextPath());
        resp.setCharacterEncoding("utf-8");
        User user = (User) session.getAttribute("current_user");
        Render.render(req, resp, "registration.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        Part filePart = req.getPart("file");
        String localdir = "uploads";
        boolean error = false;
        if (!(Pattern.matches("^[A-Za-z]+$", req.getParameter("name")) ||
        Pattern.matches("^[A-Za-z]+$", req.getParameter("surname")))) {
            root.put("Error", "Incorrect name or surname.Only English letters");
            error = true;
        }
        System.out.println(req.getParameter("password"));
        if (error &!(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$" , req.getParameter("password")))) {
            root.put("Error", "Password must contain at least one digit,at least one lower" +
                    "case letter, at least one upper case letter, at eight symbols.No whitespace allowed");
            error = true;
        }
        if (error) {
            resp.sendRedirect("/registration");
        } else {
            String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
            File dir = new File(pathDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (filePart.getSubmittedFileName().equals("")) {
                try {
                    new UserRepositoryImpl().create(new User(
                            0,
                            req.getParameter("name"),
                            req.getParameter("surname"),
                            req.getParameter("email"),
                            Password.hasher(req.getParameter("password")),
                            null
                    ));
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else {
                String[] filename_data = filePart.getSubmittedFileName().split("\\.");
                String filename = Math.random() + "." + filename_data[filename_data.length - 1];
                String fullpath = pathDir + File.separator + filename;
                filePart.write(fullpath);
                String photo_path = "/" + localdir + "/" + filename;
                try {
                    new UserRepositoryImpl().create(new User(
                            0,
                            req.getParameter("name"),
                            req.getParameter("surname"),
                            req.getParameter("email"),
                            Password.hasher(req.getParameter("password")),
                            photo_path
                    ));
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                session.setAttribute("current_user", new UserRepositoryImpl().findUserByName(req.getParameter("name")));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/main");
        }
    }
}
