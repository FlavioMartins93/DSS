package Controller;

import APP.Facade;
import Exceptions.FicheiroDuplicadoException;
import Exceptions.FicheiroInvalidoException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MUSICUPController {
    @FXML
    private Button logout;

    @FXML
    private Button settings;

    @FXML
    private Button selectPath;

    @FXML
    private AnchorPane anchorUP;

    @FXML
    private Button playlists;

    @FXML
    private Button friends;

    @FXML
    private Button uploadButton;

    @FXML
    private Button videos;

    @FXML
    private Button music;

    @FXML
    private TextField title;

    @FXML
    private TextField album;

    @FXML
    private TextField author;

    @FXML
    private TextField response;

    private Facade facade;

    public void initUC(Facade facade){
        this.facade=facade;

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
        //File para Upload
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(this.anchorUP.getScene().getWindow());

        //BOTAO DE UPLOAD
        uploadButton.setOnMouseClicked(m ->{
            //checkFormat
            try {
                this.facade.addMusic(file,album.getText(),author.getText(),title.getText(),"");
                response.setText("Uploaded "+title.getText());
                initUC(this.facade);
            } catch (FicheiroDuplicadoException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ficheiro duplicado!");
                alert.showAndWait();
                initUC(this.facade);
            } catch (FicheiroInvalidoException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Formato de ficheiro inválido!");
                alert.showAndWait();
                initUC(this.facade);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
