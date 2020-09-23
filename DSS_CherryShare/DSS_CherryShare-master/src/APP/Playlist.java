package APP;
import java.util.ArrayList;

public class Playlist {
    private boolean mv; //musica ou video
    private ArrayList<String> mvlist; //lista de media
    private String sort; //palavra chave de pesquisa

    public Playlist(boolean mv) {
        this.mv = mv;
        this.mvlist = new ArrayList<String>();
    }

    public void addMedia(String mv){
        mvlist.add(mv);
    }

    public boolean getMorV(){
        return this.mv;
    }

    public ArrayList<String> getPlist(){
        return new ArrayList<String>(this.mvlist);
    }
}
