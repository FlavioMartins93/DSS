package APP;

public class Music {
    private int id;
    private String title;
    private String album;
    private String author;
    private String genre;
    private Integer classification;
    private String path;
    private String owner;


    public Music(Integer id, String alb, String aut, String title, String genre, String owner, String path){
        this.id = id;
        this.title = title;
        this.album = alb;
        this.author = aut;
        this.classification = 0;
        this.genre = genre;
        this.owner = owner;
        this.path=path;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAlbum(){
        return this.album;
    }

    public String getAuthor(){
        return this.author;
    }

    public Integer getId(){
        return this.id;
    }

    public String getPath(){return this.path;}

    public Integer getClassification(){
        return this.classification;
    }

    public String getGenre(){return this.genre;}

    public String getOwner() {return  this.owner;}

    public void setId(int i){
        this.id=i;
    }
    public void setTitle(String title) { this.title = title;}
    public void setAlbum(String album) { this.album = album;}
    public void setAuthor(String author) { this.author = author;}
    public void setClassification(Integer classification) { this.classification = classification;}
    public void setGenre(String genre) { this.genre = genre;}
    public void setOwner(String owner) { this.owner = owner;}


    public void upVote(){
        this.classification++;
    }

    public void downVote(){
        this.classification--;
    }

    public boolean equals(Music m) {
        if(this.id == m.getId()) {
            return true;
        }
        return false;
    }
}
