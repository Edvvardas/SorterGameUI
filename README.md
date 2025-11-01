# Sorter Game UI

A JavaFX-based box sorting game where players must quickly sort boxes into the correct containers as they move along a conveyor belt.

## About

This is an interactive sorting game built with JavaFX. Boxes with letters (A, B, or C) appear on a conveyor belt and move towards three containers. Players must select the correct container (1, 2, or 3) to match each box's letter before it reaches the end of the belt.

## Features

- **GUI Interface**: Animated conveyor belt with moving boxes
- **Three Difficulty Levels**:
  - Easy: Slower box movement
  - Medium: Moderate speed
  - Hard: Fast-paced gameplay
- **Scoring System**: Earn points for correct matches (10 points × difficulty level)
- **Lives System**: Limited lives - lose a life for incorrect matches
- **Keyboard Controls**: Quick number key input for fast reactions

## Requirements

- Java 21 or higher
- Maven 3.6+
- JavaFX 21.0.6


## How to Play

1. Start the game and select your difficulty level
2. Watch the boxes appear on the conveyor belt with letters (A, B, or C)
3. Press the corresponding number key to sort each box:
   - Press **1** for container 1 (A)
   - Press **2** for container 2 (B)
   - Press **3** for container 3 (C)
4. Match the letter on the box with the correct container before it reaches the end
5. Earn points for correct matches, lose lives for mistakes
6. Game ends when you run out of lives

## Technologies Used

- **JavaFX 21.0.6**: GUI framework
- **Maven**: Build and dependency management
- **Java 21**: Programming language
- **ControlsFX**: Additional UI controls
- **Lombok**: Code generation

## Project Structure

```
src/main/java/com/example/pspkursinismano/
├── HelloApplication.java           # Main application entry point
├── GameStartMenuController.java    # Start menu controller
├── GameGUI.java                    # Main game GUI controller
└── model/
    ├── Box.java                    # Box entity
    ├── Player.java                 # Player entity with score and lives
    ├── Game.java                   # Game logic
    └── Main.java                   # Console version entry point

src/main/resources/
├── Images/                         # Game graphics
└── com/example/pspkursinismano/    # FXML layouts
```

## License

This project is available for educational purposes.
