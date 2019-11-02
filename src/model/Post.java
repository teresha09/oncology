package model;

public class Post {

    int id;
    String title;
    String text;
    String Date;
    int category;
    int imagesRef;
    User user;


    public Post(int id, String title, String text, String date, int category, int imagesRef, User user) {
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getImagesRef() {
        return imagesRef;
    }

    public void setImagesRef(int imagesRef) {
        this.imagesRef = imagesRef;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
