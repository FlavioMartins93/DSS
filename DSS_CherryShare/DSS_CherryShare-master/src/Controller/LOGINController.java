package Controller;

import APP.Facade;
import Exceptions.PasswordInvalidaException;
import Exceptions.UtilizadorInexistenteException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LOGINController {

    private Facade facade;

    @FXML
    private AnchorPane screen;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginGuest;

    public void initLC(Facade fac){
        this.facade=fac;
    }

    public void guestButton() throws IOException {
        loginGuest.setOnMouseClicked(e ->{
            FXMLLoader loaderGc = new FXMLLoader(getClass().getResource("../UI/GUEST.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                window.setScene(new Scene(loaderGc.load()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            GUESTController gc = loaderGc.getController();
            gc.initGC(this.facade);
            window.show();
        });
    }

   /* public void enter(KeyEvent keyEvent)
        boolean nv = false;
        if (keyEvent.getCode().toString().equals("ENTER")) {
            boolean exist = this.facade.existUser(userName.getText());
            boolean ok = false;
            if(exist) {
                ok = this.facade.authenticateUser(userName.getText(), password.getText());
            }
            if (!exist) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Email Inválido!");
                alert.show();
            } else if (!ok) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password Inválida!");
                alert.show();
            } else {
                FXMLLoader loaderMc = new FXMLLoader(getClass().getResource("../UI/MUSIC.fxml"));
                Stage window = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                try {
                    window.setScene(new Scene(loaderMc.load()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                MUSICController mc = loaderMc.getController();
                mc.initMC(this.facade);
                window.show();
            }
        }
    }*/
   public void enter(KeyEvent keyEvent) {
       if (keyEvent.getCode().toString().equals("ENTER")) {
           try {
               this.facade.authenticateUser(userName.getText(), password.getText());
               FXMLLoader loaderMc = new FXMLLoader(getClass().getResource("../UI/MUSIC.fxml"));
               Stage window = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
               try {
                   window.setScene(new Scene(loaderMc.load()));
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
               MUSICController mc = loaderMc.getController();
               mc.initMC(this.facade);
               window.show();
           } catch (UtilizadorInexistenteException e) {
               Alert alert = new Alert(Alert.AlertType.ERROR, "Email Inválido!");
               alert.show();
           } catch (PasswordInvalidaException e) {
               Alert alert = new Alert(Alert.AlertType.ERROR, "Password Inválida!");
               alert.show();
           }
       }
   }


}
