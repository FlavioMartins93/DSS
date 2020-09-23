package Controller;

import APP.Facade;
import APP.Music;
import Exceptions.MusicaInexistenteException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MUSICEDITController {

    @FXML
    private Button logout;

    @FXML
    private Button settings;

    @FXML
    private Button playlists;

    @FXML
    private Button friends;

    @FXML
    private Button videos;

    @FXML
    private Button musicbtn;

    @FXML
    private Button save;

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField album;

    @FXML
    private TextField genre;

    private Facade facade;
    private Music music;

    public void initME(Facade facade, Music m){
        this.facade = facade;
        this.music = m;


        title.setText(this.music.getTitle());
        author.setText(this.music.getAuthor());
        album.setText(this.music.getAlbum());
        genre.setText(this.music.getGenre());

        //Music Button
        musicbtn.setOnMouseClicked(a ->{
            FXMLLoader loaderMc = new FXMLLoader(getClass().getResource("../UI/MUSIC.fxml"));
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderMc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            MUSICController gc = loaderMc.getController();
            gc.initMC(this.facade);
            window.show();
        });
        //videoButton
        videos.setOnMouseClicked(e ->{
            FXMLLoader loaderGc = new FXMLLoader(getClass().getResource("../UI/VIDEO.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderGc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            VIDEOController vc = loaderGc.getController();
            vc.initVC(this.facade);
            window.show();
        });

        //settingsButton
        settings.setOnMouseClicked(e ->{
            FXMLLoader loaderSc = new FXMLLoader(getClass().getResource("../UI/SETTINGS.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderSc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            SETTINGSController sc = loaderSc.getController();
            sc.initSC(this.facade);
            window.show();
        });

        //friendsButton
        friends.setOnMouseClicked(e ->{
            FXMLLoader loaderFc = new FXMLLoader(getClass().getResource("../UI/FRIENDS.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderFc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            FRIENDSController fc = loaderFc.getController();
            fc.initFC(this.facade);
            window.show();
        });

        //playlistButton
        playlists.setOnMouseClicked(e ->{
            FXMLLoader loaderPc = new FXMLLoader(getClass().getResource("../UI/PLAYLISTS.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderPc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            PLAYLISTController pc = loaderPc.getController();
            pc.initPC(this.facade);
            window.show();
        });

        //logoutButton
        logout.setOnMouseClicked(e ->{
            this.facade.logout();
            FXMLLoader loaderLc = new FXMLLoader(getClass().getResource("../UI/LOGIN.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderLc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LOGINController sc = loaderLc.getController();
            sc.initLC(this.facade);
            window.show();
        });

        //SAVE
        save.setOnMouseClicked(e ->{
            try {
                this.facade.editMusic(this.music.getId(), title.getText(), author.getText(), album.getText(), genre.getText());
            } catch (MusicaInexistenteException ex) {
                ex.printStackTrace();
            }
            FXMLLoader loaderMc = new FXMLLoader(getClass().getResource("../UI/MUSIC.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderMc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            MUSICController mc = loaderMc.getController();
            mc.initMC(this.facade);
            window.show();
        });
    }

}

