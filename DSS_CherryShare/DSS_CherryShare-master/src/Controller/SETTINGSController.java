package Controller;
import APP.Facade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SETTINGSController {
    private Facade facade;

    @FXML
    private Button logout;

    @FXML
    private Button playlists;

    @FXML
    private Button friends;

    @FXML
    private Button videos;

    @FXML
    private Button music;

    @FXML
    private Button settings;

    public void initSC(Facade facade){
        this.facade=facade;

        //SIDEBAR
        videos.setOnMouseClicked(e ->{
            FXMLLoader loaderVc = new FXMLLoader(getClass().getResource("../UI/VIDEO.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderVc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            VIDEOController vc = loaderVc.getController();
            vc.initVC(this.facade);
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
            FXMLLoader loaderPc = new FXMLLoader(getClass().getResource("../UI/SETTINGS.fxml"));
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

        //musicMenu
        music.setOnMouseClicked(e ->{
            FXMLLoader loaderMc = new FXMLLoader(getClass().getResource("../UI/MUSIC.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderMc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            MUSICController gc = loaderMc.getController();
            gc.initMC(this.facade);
            window.show();
        });


    }

}
