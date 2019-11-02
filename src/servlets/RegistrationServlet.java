package servlets;

import Connect.Render;
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


@WebServlet("/registration")
@MultipartConfig
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerConfigurator.getInstance(this);
        HttpSession session = req.getSession();
        Map<String, Object> root = new HashMap<>();
        root.put("context", req.getContextPath());
        resp.setCharacterEncoding("utf-8");
        User user = (User) session.getAttribute("current_user");
        Cookie[] cookies = req.getCookies();
        if (user == null && cookies != null) {
            for (Cookie cookie: cookies) {
                try {
                    user = new UserRepositoryImpl().validateUser(cookie.getName(), cookie.getValue());
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            resp.sendRedirect("/main");
        }
        if (session.getAttribute("user_curent") == null) {
            resp.setContentType("text/html");
            RequestDispatcher dispatcher = req.getRequestDispatcher("pages/registration.ftl");
            dispatcher.forward(req, resp);
        } else {
            Render.render(req,resp,"registration.ftl", root);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
                User user = (User) session.getAttribute("current_user");
        if (user != null) {
            resp.sendRedirect("/main");
        } else {
            Part filePart = req.getPart("file");
            Path f1 = Paths.get(filePart.getSubmittedFileName());
            String filename = f1.getFileName().toString();
            if (filename.equals("")) {
                try {
                    new UserRepositoryImpl().create(new User(
                            0,
                            req.getParameter("name"),
                            req.getParameter("surname"),
                            req.getParameter("email"),
                            req.getParameter("password"),
                            null
                    ));
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else {
                String[] filenames = filename.split("\\.");
                InputStream fileContent = filePart.getInputStream();
                File uploads = new File("D:\\testing\\cancer\\out\\artifacts\\cancer_war_exploded");
                File file = File.createTempFile("img", "." + filenames[1], uploads);
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String[] help_me = file.getPath().split("/");
                String need_path = "/cancer_war_exploded/" + file.getPath().split("\\\\")[6];
                try {
                    new UserRepositoryImpl().create(new User(
                            0,
                            req.getParameter("name"),
                            req.getParameter("surname"),
                            req.getParameter("email"),
                            req.getParameter("password"),
                            need_path
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
        }
        resp.sendRedirect("/main");
    }
}
