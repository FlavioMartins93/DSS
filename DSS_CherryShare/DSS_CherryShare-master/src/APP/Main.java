package APP;

import APP.Facade;
import Controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Facade facade = new Facade();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/LOGIN.fxml"));
        primaryStage.setTitle("CherryShare");
        primaryStage.setScene(new Scene(loader.load()));
        LOGINController controller = loader.getController();
        controller.initLC(facade);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
