package FillWordApp.Controllers;

import FillWordApp.View.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Introduction {
    private static int size=0;

    public static int getModelSize() {
        return size;
    }

    public void startGame6x6() throws IOException {
        size=6;
        startGame();
    }

    public void startGame8x8() throws IOException {
        size=8;
        startGame();
    }

    private void startGame() throws IOException {
        Group root = new Group();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainAppearance.fxml"));
        root.getChildren().add(loader.load());

        Scene mainScene = new Scene(root);

        Stage mainWindow = new Stage();
        mainWindow.setTitle("Filwords!");
        mainWindow.setScene(mainScene);
        mainWindow.getIcons().add(new Image("/fillword.png"));

        mainWindow.initModality(Modality.APPLICATION_MODAL);
        mainWindow.initOwner(View.getStage());

        mainWindow.show();
    }
}
