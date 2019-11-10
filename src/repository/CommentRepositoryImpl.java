package repository;

import Connect.Connect;
import model.Comment;
import model.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommentRepositoryImpl implements CrudRepository<Comment> {
    @Override
    public void save(Comment model) {

    }

    @Override
    public void create(Comment model) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("INSERT INTO comment(text, date, user_id, post_id) VALUES " +
                "(?, ?, ?, ?)");
        statement.setString(1,model.getText());
        statement.setString(2,model.getDate());
        statement.setInt(3,model.getUser().getId());
        statement.setInt(4, model.getPost().getId());
        statement.executeUpdate();
        conn.close();
    }

    @Override
    public Comment findByID(int ID) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM comment WHERE id = ?");
        statement.setInt(1, ID);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            conn.close();
            return new Comment(
                    rs.getInt("id"),
                    rs.getString("text"),
                    rs.getString("date"),
                    new UserRepositoryImpl().findByID(rs.getInt("user_id")),
                    new PostRepositoryImpl().findByID(rs.getInt("post_id"))
            );
        } else {
            conn.close();
            return null;
        }
    }

    @Override
    public void delete(Comment model) {

    }

    @Override
    public ArrayList<Comment> findAll() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM comment");
        ResultSet rs = statement.executeQuery();
        ArrayList<Comment> commentList = new ArrayList<>();
        while (rs.next()) {
            commentList.add(new Comment(
                    rs.getInt("id"),
                    rs.getString("text"),
                    rs.getString("date"),
                    new UserRepositoryImpl().findByID(rs.getInt("user_id")),
                    new PostRepositoryImpl().findByID(rs.getInt("post_id"))
            ));
        }
        conn.close();
        return commentList;

    }

    @Override
    public void update(Comment model) throws ClassNotFoundException, SQLException {

    }

    public ArrayList<Comment> findByPost(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM comment WHERE post_id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        ArrayList<Comment> commentList = new ArrayList<>();
        while (rs.next()) {
            commentList.add(new Comment(
                    rs.getInt("id"),
                    rs.getString("text"),
                    rs.getString("date"),
                    new UserRepositoryImpl().findByID(rs.getInt("user_id")),
                    new PostRepositoryImpl().findByID(rs.getInt("post_id"))
            ));
        }
        conn.close();
        return commentList;
    }
}
