package repository;

import Connect.Connect;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserRepositoryImpl implements CrudRepository<User>{


    @Override
    public void save(User model) {

    }

    @Override
    public void create(User model) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("INSERT INTO public.user_table(name, surname, email, password, images) VALUES " +
                "(?, ?, ?, ?, ?)");
        statement.setString(1,model.getName());
        statement.setString(2, model.getSurname());
        statement.setString(3, model.getEmail());
        statement.setString(4, model.getPassword());
        statement.setString(5, model.getProfilePicture());
        statement.executeUpdate();
        conn.close();
    }

    @Override
    public User findByID(int ID) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres", "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM user_table where id = ?");
        statement.setInt(1,ID);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            conn.close();
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("images"));
        }else {
            conn.close();
            return null;
        }
    }

    @Override
    public void delete(User model) {

    }


    @Override
    public ArrayList<User> findAll() throws SQLException, ClassNotFoundException {
        ArrayList<User> result = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres",
                "2109");
        Statement statement = conn.getConn().createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM user_table");
        while(rs.next()) {
            result.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("images")));
        }
        return result;
    }


    @Override
    public void update(User model) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres",
                "2109");
        PreparedStatement statement = conn.getConn().prepareStatement(
                "UPDATE user_table SET name = ?, surname=?, email=?, password=?, images=? WHERE id=?");
        statement.setString(1,model.getName());
        statement.setString(2,model.getSurname());
        statement.setString(3,model.getEmail());
        statement.setString(4,model.getPassword());
        statement.setString(5, model.getProfilePicture());
        statement.setInt(6, model.getId());
        statement.executeUpdate();
    }

    public User findUserByName(String name) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres",
                "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM public.user_table WHERE name = ?");
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("images"));
        }
        else return null;
    }

    public User validateUser(String email, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connect conn = new Connect("jdbc:postgresql://localhost:5432/cancer", "postgres",
                "2109");
        PreparedStatement statement = conn.getConn().prepareStatement("SELECT * FROM public.user_table " +
                "WHERE email = ? AND password = ?");
        statement.setString(1, email);
        statement.setString(2, pass);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("images"));
        }
        else return null;
    }
}
