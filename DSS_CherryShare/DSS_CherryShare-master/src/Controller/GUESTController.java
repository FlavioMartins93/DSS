package Controller;

import APP.Facade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class GUESTController {

    private Facade facade;

    @FXML
    private ListView<?> music_list;

    @FXML
    private Button logout;

    @FXML
    private Button videos;

    @FXML
    private Button music;

    public void initGC(Facade facade){
        this.facade=facade;
        //SIDEBAR
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

        //logoutButton
        logout.setOnMouseClicked(e ->{
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
    }

}
