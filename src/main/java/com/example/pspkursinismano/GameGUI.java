package com.example.pspkursinismano;

import com.example.pspkursinismano.model.Box;
import com.example.pspkursinismano.model.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameGUI implements Initializable {

    @FXML
    public ImageView crate;
    @FXML
    public Pane paneBoxAndText;
    @FXML
    public Text displayBoxLetter;
    @FXML
    public Label scoreLabel;
    @FXML
    public Label livesLabel;
    @FXML
    public Label difficultyLabel;
    @FXML
    public Label selectedContainerLabel;

    private Player player;
    private Box box;
    private boolean running;
    private int difficulty;
    private Random random;
    private AnimationTimer gameLoop;
    private long lastUpdate = 0;
    private static final double MAX_POSITION = 400.0; // Max X position for animation

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setup keyboard input handling
        Platform.runLater(() -> {
            paneBoxAndText.getScene().setOnKeyPressed(event -> {
                if (!running) return;

                KeyCode code = event.getCode();
                if (code == KeyCode.DIGIT1 || code == KeyCode.NUMPAD1) {
                    player.changeDiverter('1');
                    updateSelectedContainer();
                } else if (code == KeyCode.DIGIT2 || code == KeyCode.NUMPAD2) {
                    player.changeDiverter('2');
                    updateSelectedContainer();
                } else if (code == KeyCode.DIGIT3 || code == KeyCode.NUMPAD3) {
                    player.changeDiverter('3');
                    updateSelectedContainer();
                }
            });
        });
    }

    public void startGame() {
        startGame(1); // Default difficulty
    }

    public void startGame(int difficulty) {
        this.difficulty = difficulty;
        random = new Random(); // Initialize random BEFORE using it
        player = new Player();
        box = new Box(randomLetter(), 0);
        running = true;

        // Update UI with initial values
        updateUI();
        updateDifficultyLabel();

        // Reset box position
        paneBoxAndText.setTranslateX(0);

        // Start game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame(now);
            }
        };
        gameLoop.start();
    }

    private void updateGame(long now) {
        // Control update rate based on difficulty (50ms base / difficulty)
        long updateInterval = 50_000_000 / difficulty; // nanoseconds

        if (now - lastUpdate >= updateInterval) {
            box.move();
            lastUpdate = now;

            // Update visual position (scale box position 0-50 to 0-400px)
            double visualX = (box.getPosition() / 50.0) * MAX_POSITION;
            paneBoxAndText.setTranslateX(visualX);

            // Check if box reached the end
            if (box.getPosition() > 50) {
                checkMatch();
            }
        }

        // Update UI
        updateUI();

        // Check game over
        if (player.getLives() <= 0) {
            endGame();
        }
    }

    private void checkMatch() {
        char boxLetter = box.getLetter();
        char playerChoice = (char) ('a' + player.getDiverter());

        if (playerChoice == boxLetter) {
            // Correct match
            player.addScore(10 * difficulty);
            // Visual feedback could be added here (e.g., green flash)
        } else {
            // Incorrect match
            player.loseLife();
            // Visual feedback could be added here (e.g., red flash)
        }

        // Reset box with new random letter
        box.reset(randomLetter());
        paneBoxAndText.setTranslateX(0);
    }

    private void updateUI() {
        displayBoxLetter.setText(String.valueOf(box.getLetter()).toUpperCase());
        scoreLabel.setText("Score: " + player.getScore());
        livesLabel.setText("Lives: " + player.getLives());
    }

    private void updateSelectedContainer() {
        int container = player.getDiverter() + 1; // Convert 0-2 to 1-3
        selectedContainerLabel.setText("Selected: " + container);
    }

    private void updateDifficultyLabel() {
        String diffText = switch (difficulty) {
            case 1 -> "Easy";
            case 2 -> "Medium";
            case 3 -> "Hard";
            default -> "Unknown";
        };
        difficultyLabel.setText("Difficulty: " + diffText);
    }

    private void endGame() {
        running = false;
        if (gameLoop != null) {
            gameLoop.stop();
        }

        // Show game over message
        displayBoxLetter.setText("GAME OVER");
        System.out.println("GAME OVER! Final Score: " + player.getScore());
    }

    private char randomLetter() {
        return (char) ('a' + random.nextInt(3));
    }
}
