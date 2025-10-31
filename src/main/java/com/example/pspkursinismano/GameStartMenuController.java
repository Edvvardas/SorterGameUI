package com.example.pspkursinismano;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStartMenuController {

    public void selectEasyDifficulty() throws IOException {
        startGameWithDifficulty(1);
    }

    public void selectMediumDifficulty(ActionEvent actionEvent) throws IOException {
        startGameWithDifficulty(2);
    }

    public void selectHardDifficulty(ActionEvent actionEvent) throws IOException {
        startGameWithDifficulty(3);
    }

    private void startGameWithDifficulty(int difficulty) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GameGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 395);

        // Get the controller and start the game with selected difficulty
        GameGUI controller = fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setTitle("Konteinerių Žaidimas");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Start game after stage is shown so keyboard input works
        controller.startGame(difficulty);
    }
}
