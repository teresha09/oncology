package model;

public class PostImage {

    Post post;
    String image;

    public PostImage(Post post, String image) {
        this.post = post;
        this.image = image;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
