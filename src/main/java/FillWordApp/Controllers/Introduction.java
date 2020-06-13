package FillWordApp.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Introduction {
    private static int size = 0;
    public Button button;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        root.getChildren().add(loader.load());

        Scene mainScene = new Scene(root);

        Stage mainWindow = new Stage();
        mainWindow.setTitle("Filwords!");
        mainWindow.setScene(mainScene);
        mainWindow.getIcons().add(new Image("/fillword.png"));

        mainWindow.show();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
