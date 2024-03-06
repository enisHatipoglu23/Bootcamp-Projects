import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n=== MINESWEEPER ===\n\nDetermine the size of the game.\nFor example: 2x2 , 4x4 , 3x4\n\nRow: ");
        int row = scanner.nextInt();
        System.out.print("Column: ");
        int col = scanner.nextInt();
        MineSweeper mineSweeper = new MineSweeper(row,col);
        mineSweeper.run();
    }
}