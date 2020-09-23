package Controller;

import APP.Facade;
import APP.Music;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MUSICController {
    private Facade facade;
    private static final int INITIAL_VOLUME = 50;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private Music musicPlaying;
    private List<Music> nextMusic;

    @FXML
    private TreeView<Music> treeList;

    @FXML
    private ListView<Music> music_list;

    @FXML
    private Button logout;

    @FXML
    private Button upload;

    @FXML
    private Button settings;

    @FXML
    private Button playlists;

    @FXML
    private Button friends;

    @FXML
    private Button videos;

    @FXML
    private Button random;

    @FXML
    private Button play;

    @FXML
    private Button pause;

    @FXML
    private Button proxima;

    @FXML
    private Button anterior;

    @FXML
    private Slider slide;

    @FXML
    private Button artistSort;

    @FXML
    private Button genreSort;

    @FXML
    private TextField nomeMusicPlay;

    public void initMC(Facade facade){
        this.facade=facade;

        //SIDEBAR
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

        //uploadButton
        upload.setOnMouseClicked(e ->{
            FXMLLoader loaderUc = new FXMLLoader(getClass().getResource("../UI/MUSICUPLOAD.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderUc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            MUSICUPController uc = loaderUc.getController();
            uc.initUC(this.facade);
            window.show();
        });

        //pauseButton
        pause.setOnMouseClicked(e ->{
            this.facade.pauseMusic();
        });

        //playButton
        play.setOnMouseClicked(e ->{
            this.facade.resumeMusicPlay();
        });

        //proximaButton
        proxima.setOnMouseClicked(e ->{
            this.facade.playNextMusic();
        });

        //anteriorButton
        anterior.setOnMouseClicked(e ->{
            this.facade.playPreviousMusic();
        });

        slide.setMax(200);
        slide.setOnMouseClicked(e ->{
            this.facade.setMusicSound((int) slide.getValue());
        });

        //listViewMusicas
        List<Music> lmusic = this.facade.getAllMusic();
        this.music_list.setCellFactory(lv->{
            Button addToQueue = new Button("Play Next");
            Button editMusic = new Button("Edit Music");
            ListCell<Music> cell = new ListCell<>() {
                @Override
                protected void updateItem(Music m, boolean empty){
                    super.updateItem(m,empty);
                    if(empty){
                        setGraphic(null);
                    }else{
                        HBox hbox = new HBox(new Label(m.getTitle()),editMusic,addToQueue);
                        hbox.setAlignment(Pos.CENTER_LEFT);
                        setGraphic(hbox);
                    }
                }
            };
            cell.setOnMouseClicked(e->{
                if(!cell.isEmpty()){
                    this.facade.playMusic(cell.getItem());
                }
            });
            addToQueue.setOnMouseClicked(e ->{
                this.facade.addMusicToQueue(cell.getItem());
            });
            editMusic.setOnMouseClicked(e -> {
                FXMLLoader loaderUc = new FXMLLoader(getClass().getResource("../UI/MUSICEDIT.fxml"));
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                try {
                    window.setScene(new Scene(loaderUc.load()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                MUSICEDITController ue = loaderUc.getController();
                ue.initME(this.facade,cell.getItem());
                window.show();
            });
            return cell;
        });
        ObservableList<Music> olmusic= FXCollections.observableArrayList();
        olmusic.addAll(lmusic);
        this.music_list.setItems(olmusic);
        this.music_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
}

