package model;

public class PostCategory {

    Post post;
    String category;

    public PostCategory(Post post, String category) {
        this.post = post;
        this.category = category;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
