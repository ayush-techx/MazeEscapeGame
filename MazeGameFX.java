import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class MazeGameFX extends Application {
    private final int rows = 10, cols = 10;
    private char[][] grid;
    private int playerRow = 1, playerCol = 1;
    private GridPane gridPane;
    private int moveCount = 0;


    @Override
    public void start(Stage primaryStage) {
        // Initialize game
        initMaze();
        gridPane = new GridPane();
        renderMaze();

        Scene scene = new Scene(gridPane, 500, 500);
        scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));

        primaryStage.setTitle("Maze Escape Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initMaze() {
        grid = new char[rows][cols];
        // Fill empty space
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = '.';

        // Add walls
        for (int i = 0; i < rows; i++)
            grid[i][0] = grid[i][cols - 1] = '#';
        for (int j = 0; j < cols; j++)
            grid[0][j] = grid[rows - 1][j] = '#';

        // Traps and exit
        grid[2][3] = 'T';
        grid[4][7] = 'T';
        grid[8][8] = 'E';
    }

    private void renderMaze() {
        gridPane.getChildren().clear();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Text tile;
                if (i == playerRow && j == playerCol) {
                    tile = new Text("🧍");
                } else {
                    switch (grid[i][j]) {
                        case '#' -> tile = new Text("⬛");
                        case '.' -> tile = new Text("⬜");
                        case 'T' -> tile = new Text("💣");
                        case 'E' -> tile = new Text("🚪");
                        default -> tile = new Text(" ");
                    }
                }
                tile.setStyle("-fx-font-size: 24;");
                gridPane.add(tile, j, i);
            }
        }
    }

    private void handleKeyPress(KeyCode code) {
        int newRow = playerRow;
        int newCol = playerCol;

        switch (code) {
            case W -> newRow--;
            case S -> newRow++;
            case A -> newCol--;
            case D -> newCol++;
            default -> { return; }
        }

        char destination = grid[newRow][newCol];
        if (destination == '#') {
            return;
        } else if (destination == 'T') {
            showAlert("Game Over", "💥 You stepped on a trap!");
            resetGame();
        } else if (destination == 'E') {
            showAlert("Victory", "🎉 You escaped the maze!");
            resetGame();
        } else {
            playerRow = newRow;
            playerCol = newCol;
            moveCount++; // 👈 add this
            System.out.println("Moves: " + moveCount); // 👈 temporary console output
            renderMaze();
        }
    }

    private void resetGame() {
        playerRow = 1;
        playerCol = 1;
        moveCount = 0; // Reset move count
        initMaze();
        renderMaze();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
