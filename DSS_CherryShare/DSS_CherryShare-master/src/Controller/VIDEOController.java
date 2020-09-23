package Controller;

import APP.Facade;
import APP.Video;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class VIDEOController {

    private Facade facade;

    @FXML
    private TreeView<Video> treeList;

    @FXML
    private ListView<Video> video_list;

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
    private Button music;

    @FXML
    private Button sort;

    public void initVC(Facade facad){
        this.facade=facad;

        //listView
        List<Video> lvideo=this.facade.getAllVideo();
        video_list.setCellFactory(lv->{
            ListCell<Video> cell = new ListCell<Video>() {
                @Override
                protected void updateItem(Video v, boolean empty){
                    super.updateItem(v,empty);
                    if(empty){
                        setGraphic(null);
                    }else{
                        HBox hbox = new HBox(5);
                        hbox.setAlignment(Pos.CENTER_LEFT);
                        hbox.getChildren().add(new Label(v.getName()));
                        setGraphic(hbox);
                    }
                }
            };
            cell.setOnMouseClicked(e->{
                ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\VideoLAN\\VLC\\vlc.exe", cell.getItem().getPath());
                try {
                    Process VLCgo = pb.start();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            return cell;
        });
        ObservableList<Video> olvideo= FXCollections.observableArrayList();
        olvideo.addAll(lvideo);
        this.video_list.setItems(olvideo);
        this.video_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //SIDEBAR
        //musicMenu
        music.setOnMouseClicked(a ->{
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
        friends.setOnMouseClicked(b ->{
            FXMLLoader loaderFc = new FXMLLoader(getClass().getResource("../UI/FRIENDS.fxml"));
            Stage window = (Stage) ((Node) b.getSource()).getScene().getWindow();
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
        playlists.setOnMouseClicked(c ->{
            FXMLLoader loaderPc = new FXMLLoader(getClass().getResource("../UI/SETTINGS.fxml"));
            Stage window = (Stage) ((Node) c.getSource()).getScene().getWindow();
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
        logout.setOnMouseClicked(d ->{
            FXMLLoader loaderLc = new FXMLLoader(getClass().getResource("../UI/LOGIN.fxml"));
            Stage window = (Stage) ((Node) d.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderLc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LOGINController sc = loaderLc.getController();
            sc.initLC(this.facade);
            window.show();
        });
    }

}