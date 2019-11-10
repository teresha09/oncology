package repository;

import Connect.Connect;
import model.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostRepositoryImpl implements CrudRepository<Post> {
    @Override
    public void save(Post model) {

    }

    @Override
    public void create(Post model) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("INSERT INTO public.post(tittle, text, date, author_id, image, category) VALUES " +
                "(?,?,?,?,?,?)");
        statement.setString(1, model.getTitle());
        statement.setString(2, model.getText());
        statement.setString(3,model.getDate());
        statement.setInt(4,model.getUser().getId());
        statement.setString(5, model.getImagesRef());
        statement.setString(6, model.getCategory());
        statement.executeUpdate();
        conn.close();
    }

    @Override
    public Post findByID(int ID) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM post WHERE  id = ?");
        statement.setInt(1,ID);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            conn.close();
            return new Post(
                    rs.getInt("id"),
                    rs.getString("tittle"),
                    rs.getString("text"),
                    rs.getString("date"),
                    new UserRepositoryImpl().findByID(rs.getInt("author_id")),
                    rs.getString("image"),
                    rs.getString("category")
            );
        } else {
            conn.close();
            return null;
        }
    }

    @Override
    public void delete(Post model) {

    }

    @Override
    public ArrayList<Post> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void update(Post model) throws ClassNotFoundException, SQLException {

    }

    public Post findByName(String name) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM post WHERE  tittle = ?");
        statement.setString(1,name);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            conn.close();
            return new Post(
                    rs.getInt("id"),
                    rs.getString("tittle"),
                    rs.getString("text"),
                    rs.getString("date"),
                    new UserRepositoryImpl().findByID(rs.getInt("author_id")),
                    rs.getString("image"),
                    rs.getString("category")
            );
        } else {
            conn.close();
            return null;
        }
    }

    public ArrayList<Post> findByCategory(String cat) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM post WHERE category = ?");
        statement.setString(1,cat);
        ResultSet rs = statement.executeQuery();
        ArrayList<Post> postList = new ArrayList<>();
        while(rs.next()) {
            postList.add(findByID(rs.getInt("id")));
        }
        conn.close();
        return postList;
    }

    public ArrayList<Post> findByTitleLike(String name) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM post WHERE tittle like ?");
        statement.setString(1,"%" + name + "%");
        ResultSet rs = statement.executeQuery();
        ArrayList<Post> postList = new ArrayList<>();
        while (rs.next()) {
            postList.add(new Post(
                    rs.getInt("id"),
                    rs.getString("tittle"),
                    rs.getString("text"),
                    rs.getString("date"),
                    new UserRepositoryImpl().findByID(rs.getInt("author_id")),
                    rs.getString("image"),
                    rs.getString("category"))
            );
        }
        conn.close();
        return postList;
    }
}
