package model;

public class Post {

    int id;
    String title;
    String text;
    String Date;
    String category;
    String imagesRef;
    User user;


    public Post(int id, String title, String text, String date, User user, String imagesRef,String category) {
        this.id = id;
        this.title = title;
        this.text = text;
        Date = date;
        this.category = category;
        this.imagesRef = imagesRef;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagesRef() {
        return imagesRef;
    }

    public void setImagesRef(String imagesRef) {
        this.imagesRef = imagesRef;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
