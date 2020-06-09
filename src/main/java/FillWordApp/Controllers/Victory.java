package FillWordApp.Controllers;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Victory {
    public Button startNewGame;

    public void newGame() {
        Stage stage = (Stage) startNewGame.getScene().getWindow();
        stage.close();
    }
}
