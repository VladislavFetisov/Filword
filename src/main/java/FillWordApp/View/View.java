package FillWordApp.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {

        Group root = new Group();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Introduction.fxml"));
        root.getChildren().add(loader.load());


        Scene scene = new Scene(root);
        primaryStage.setTitle("Introduction");
        primaryStage.getIcons().add(new Image("/fillword.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
