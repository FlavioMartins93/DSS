package APP;

import DB.MusicDAO;
import DB.UserDAO;
import DB.VideoDAO;
import Exceptions.*;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Facade {
    private boolean isGuest;
    private UserDAO userDao;
    private MusicDAO musicDao;
    private VideoDAO videoDao;
    private String log;
    //private PlaylistDAO playlistDao;
    private MediaPlayer mediaPlayer;


    public Facade(){
        this.userDao = new UserDAO();
        this.musicDao = new MusicDAO();
        this.videoDao = new VideoDAO();
        this.mediaPlayer = new MediaPlayer();
        this.log="";
        this.isGuest = false;
    }

    public boolean existUser(String email) {
        return this.userDao.containsKey(email);
    }

    public boolean authenticateUser(String email, String password) throws UtilizadorInexistenteException, PasswordInvalidaException {
        if(!this.userDao.containsKey(email)) throw new UtilizadorInexistenteException();
        User u = userDao.get(email);
        if(!u.authenticate(password)) throw new PasswordInvalidaException();
        this.isGuest = false;
        this.log = email;
        return true;
    }

    public void logout() {
        this.log = "";
        this.isGuest = false;
        this.mediaPlayer.stopMusicPlay();
    }

    public void addUser(User u){
        this.userDao.put(u);
    }

    public void addMusic(File file,String album, String author, String title, String genre) throws IOException, FicheiroDuplicadoException, FicheiroInvalidoException {

        String ext = file.getName().substring(file.getName().indexOf(".") + 1);
        if(!(ext.equals("wma")||ext.equals("mp3")||ext.equals("wav")||ext.equals("mp2"))) throw new FicheiroInvalidoException();

        /* Criação de novo ficheiro e cópia de música para o mesmo*/
        Path sourcePath = file.toPath();
        File destFile = new File( System.getProperty("user.dir") + "/src/MUSIC_FILES/" + file.getName());
        Path destPath = destFile.toPath();
        try {
            Files.copy(sourcePath, destPath);
        } catch (FileAlreadyExistsException e) {
            throw  new FicheiroDuplicadoException();
        }

        /*Criação e adição de nova música na base de dados*/
        Music music = new Music(0,album,author,title,genre,this.log,destPath.toString());
        this.musicDao.put(music);
    }

    public void editMusic(Integer id, String title, String author, String album, String genre) throws MusicaInexistenteException {
        if(musicDao.containsKey(id)) {
            Music m = musicDao.get(id, this.log);
            m.setTitle(title);
            m.setAuthor(author);
            m.setAlbum(album);
            m.setGenre(genre);
            if (m.getOwner().equals(this.log)) {
                musicDao.update(m);
            } else {
                musicDao.updateNotOwner(m, this.log);
            }
        } else throw new MusicaInexistenteException();
    }

    public List<Music> getAllMusic(){
        if(isGuest) return this.musicDao.list("");
        return this.musicDao.list(this.log);
    }

    public List<Video> getAllVideo(){
        return this.videoDao.list();
    }

    public String getLog(){
        return this.log;
    }

    /* Play Music */
    public void playMusic(Music m) {
        this.mediaPlayer.playMusic(m);
    }

    public void pauseMusic() {
        this.mediaPlayer.pauseMusic();
    }

    public void resumeMusicPlay() {
        this.mediaPlayer.resumeMusicPlay();
    }

    public void playNextMusic() {
        this.mediaPlayer.playNextMusic();
    }

    public void playPreviousMusic() {
        this.mediaPlayer.playPreviousMusic();
    }

    public void addMusicToQueue(Music m) {
        this.mediaPlayer.addToQueue(m);
    }

    public void setMusicSound(Integer sound) {
        this.mediaPlayer.setMusicSound(sound);
    }

}
