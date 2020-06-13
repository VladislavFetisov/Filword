package FillWordApp.Controllers;

import FillWordApp.View.View;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Victory {
    public Button button;

    public void close() {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public void restart() {
        close();
        Stage stage = new Stage();
        View view = new View();
        try {
            view.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
