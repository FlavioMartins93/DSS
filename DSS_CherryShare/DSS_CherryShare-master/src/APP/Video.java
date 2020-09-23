package APP;

import java.util.ArrayList;

public class Video {

    private Integer id;
    private String author;
    private String name;
    private String genre;
    private String owner;
    private String path;
    private Integer classification;

    public Video(){
        this.id = 0;
        this.author = "";
        this.name = "";
        this.genre = "";
        this.owner = "";
        this.path = "";
        this.classification = 0;
    }

    public Video(Integer id, String author, String name, String genre, String owner, String path){
        this.id = id;
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.owner = owner;
        this.path = path;
    }

    public Video(Video v) {
        this.id = v.getId();
        this.author = v.getAuthor();
        this.name = v.getName();
        this.genre = v.getGenre();
        this.owner = v.getOwner();
        this.path = v.getPath();
    }

    public Integer getId() { return id; }
    public String getAuthor() { return author; }
    public String getName() { return name; }
    public String getGenre() { return genre; }
    public String getOwner() { return owner; }
    public String getPath() { return path; }
    public Integer getClassification() { return classification; }

    public void setId(Integer id) { this.id = id; }
    public void setAuthor(String author) { this.author = author; }
    public void setName(String name) { this.name = name; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setOwner(String owner) {  this.owner = owner; }
    public void setPath(String path) { this.path = path; }
    public void setClassification(Integer classification) { this.classification = classification; }

    public void upVote(){
        this.classification++;
    }
    public void downVote(){
        this.classification--;
    }
}
