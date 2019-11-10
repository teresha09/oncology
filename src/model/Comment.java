package model;

public class Comment {

    private int id;
    private String text;
    private String date;
    private User user;
    private Post post;

    public Comment(int id, String text,String date, User user, Post post) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.user = user;
        this.post = post;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
