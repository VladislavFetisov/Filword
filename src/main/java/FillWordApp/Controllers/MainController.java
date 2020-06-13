package FillWordApp.Controllers;

import FillWordApp.Model.Model;
import FillWordApp.Model.Pair;
import FillWordApp.View.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class MainController {


    public TextArea textArea;
    public GridPane gridPane;
    public Button restart;
    public Button cancel;
    public AnchorPane anchorPane;

    private final Model model = new Model();
    private final char[][] panel = model.fillModel(Introduction.getModelSize());
    private final HashSet<String> words = model.words;
    private final HashSet<ArrayList<Pair>> coordinates = model.wordsCoordinates;

    private final HashSet<String> guessedWords = new HashSet<>();
    private final StringBuilder currentWord = new StringBuilder();
    private final ArrayList<Pair> currentCoordinates = new ArrayList<>();


    public void initialize() {
        if (panel.length == 6) anchorPane.setStyle("-fx-background-image: url('/main2.png')");
        for (int i = 0; i < panel.length; i++) {
            for (int j = 0; j < panel.length; j++) {
                Button letter = new Button(String.valueOf(panel[i][j]));

                letter.setFont(Font.font(18));
                letter.setPrefSize(70, 50);

                letter.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
                    try {
                        guessing(letter);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                gridPane.add(letter, j, i);
            }
        }
        textArea.setText("Отгаданные слова:");
    }


    private void guessing(Button button) throws IOException {
        boolean trigger = false;

        currentCoordinates.add(new Pair(GridPane.getRowIndex(button), GridPane.getColumnIndex(button)));
        currentWord.append(button.getText());
        int iteration = currentCoordinates.size();
        if (iteration >= 2) {
            Pair currentLetter = currentCoordinates.get(iteration - 1);
            Pair previousLetter = currentCoordinates.get(iteration - 2);
            if (!Pair.directions.contains(new Pair(currentLetter.row - previousLetter.row,
                    currentLetter.column - previousLetter.column))) {
                trigger = true;

                showAlertWithHeaderText();
                cancelHighlighting();

            }
        }


        if (!trigger) {
            button.setFont(Font.font(18));
            button.setStyle("-fx-background-color: lightblue");
            button.setDisable(true);
        }

        if (coordinates.contains(currentCoordinates)) guessed();

        if (words.equals(guessedWords)) win();
    }

    public void cancelHighlighting() {
        for (Pair coordinate : currentCoordinates) {
            Button letter = (Button) gridPane.getChildren().get(coordinate.row * panel.length + coordinate.column);
            letter.setStyle("-fx-alternative-column-fill-visible: true");
            letter.setFont(Font.font(18));
            letter.setDisable(false);
        }
        currentWord.setLength(0);
        currentCoordinates.clear();
    }

    private void win() throws IOException {
        Group root = new Group();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Victory.fxml"));
        root.getChildren().add(loader.load());

        Scene secondScene = new Scene(root);

        Stage newWindow = new Stage();
        newWindow.setTitle("Победа!");
        newWindow.setScene(secondScene);
        newWindow.getIcons().add(new Image("/fillword.png"));

        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();

        newWindow.show();
    }

    private void guessed() {
        guessedWords.add(currentWord.toString());
        textArea.appendText(System.lineSeparator() + currentWord);
        String color = RandomColorGeneration();
        for (Pair coordinate : currentCoordinates) {
            Button letter = (Button) gridPane.getChildren().get(coordinate.row * panel.length + coordinate.column);
            letter.setStyle("-fx-background-color: " + color);
            letter.setDisable(true);
        }
        currentWord.setLength(0);
        currentCoordinates.clear();
    }

    public void restartAPP() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        View view = new View();
        try {
            view.start(newStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String RandomColorGeneration() {
        String letters = "0123456789ABCDEF";
        StringBuilder color = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            color.append(letters.charAt((int) Math.floor(Math.random() * 16)));
        }
        return color.toString();
    }

    private void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Нельзя выбирать несоседние или лежащие по диагонали буквы!");
        alert.showAndWait();
    }

}
