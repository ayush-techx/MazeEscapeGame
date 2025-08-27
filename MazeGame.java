import java.util.Scanner;

public class MazeGame {
    public static void main(String[] args) {
        Maze maze = new Maze();
        Player player = new Player();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            maze.printMaze('P', player.row, player.col);
            System.out.print("Move (W/A/S/D): ");
            char move = sc.next().toUpperCase().charAt(0);

            int newRow = player.row;
            int newCol = player.col;

            switch (move) {
                case 'W' -> newRow--;
                case 'S' -> newRow++;
                case 'A' -> newCol--;
                case 'D' -> newCol++;
                default -> {
                    System.out.println("Invalid input! Use W A S D.");
                    continue;
                }
            }

            char destination = maze.grid[newRow][newCol];

            if (destination == '#') {
                System.out.println("🚫 You hit a wall!");
            } else if (destination == 'T') {
                System.out.println("💥 You stepped on a trap. Game over!");
                isRunning = false;
            } else if (destination == 'E') {
                System.out.println("🎉 You found the exit. You win!");
                isRunning = false;
            } else {
                player.row = newRow;
                player.col = newCol;
            }
        }

        sc.close();
    }
}
