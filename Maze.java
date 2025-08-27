public class Maze {
    public char[][] grid;
    int rows = 10, cols = 10;

    public Maze() {
        grid = new char[rows][cols];
        generateMaze();
    }

    private void generateMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '.';
            }
        }

        // Add walls around border
        for (int i = 0; i < rows; i++) {
            grid[i][0] = grid[i][cols - 1] = '#';
        }
        for (int j = 0; j < cols; j++) {
            grid[0][j] = grid[rows - 1][j] = '#';
        }

        // Add traps
        grid[2][3] = 'T';
        grid[4][7] = 'T';

        // Add exit
        grid[8][8] = 'E';
    }

    public void printMaze(char playerChar, int playerRow, int playerCol) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == playerRow && j == playerCol) {
                    System.out.print(playerChar + " ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
