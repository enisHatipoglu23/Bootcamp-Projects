import java.util.Random;
import java.util.Scanner;
public class MineSweeper {
    int row;
    int col;
    int size;
    Random random = new Random();
    String[][] board;
    String[][] map;
    int mineNumber;
    boolean isGameOn=true;
    Scanner scan = new Scanner(System.in);
    // Constructor method.
    MineSweeper(int row, int col){
        this.row = row;
        this.col = col;
        this.size = col*row;
        this.mineNumber = (row * col) / 4;
        this.board = new String[row][col];
        this.map = new String[row][col];
    }
    //     Determining fields.
    void field(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " - ";
                map[i][j] = " - ";
            }
            System.out.println();
        }
    }
    // Mine placement method.
    void setMines(){
        // Mine's randomly determining to the field.
        int i = 1;
        int rowMine;
        int colMine;
        while(i <= mineNumber){
            rowMine = random.nextInt(row);
            colMine = random.nextInt(col);
            if(map[rowMine][colMine] == " - "){
                map[rowMine][colMine] = " * ";
                i++;
            }
        }
    }
    // Prints field of the game.
    void printField(String[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
    // Main run method for running the game.
    void run(){
        field();
        setMines();
        int sRow;
        int sCol;
        // 'goal' is road to finish the game.
        int goal=0;
        // 'count' is number of mine's around of the spot.
        int count;
//        System.out.println("Mine's position.");
//        printField(minesVisible);
        System.out.println("=== Welcome to the Game ! ===");
        while(isGameOn){
            count = 0;
            printField(board);
            System.out.println("Enter the row you want to open: (Starting from 0 -->) ");
            sRow = scan.nextInt();
            System.out.println("Enter the column you want to open: (Starting from 0 -->) ");
            sCol = scan.nextInt();

            // Checks if user input is valid.
            if((sRow >= this.row) || (sCol >= this.col) || (sRow < 0) || (sCol < 0)) {
                System.out.println("Invalid input. ");
                if(sRow<0) System.out.println(sRow + " is negative number.");
                if(sCol<0) System.out.println(sCol + " is negative number.");
                if(sRow>=this.row) System.out.println(sRow + " is greater than array size(" + this.row + ")");
                if(sCol>=this.col) System.out.println(sCol + " is greater than array size(" + this.col + ")");
                continue;
            }
            // Calculating the number of mines around the point.
            if(map[sRow][sCol] != " * "){

                if(isValid(sRow+1,sCol+1)==true){
                    if (map[sRow + 1][sCol + 1] == " * ") {
                        count++;
                    }
                }
                if(isValid(sRow-1,sCol-1)==true){
                    if (map[sRow - 1][sCol - 1] == " * ") {
                        count++;
                    }
                }
                if(isValid(sRow+1,sCol)==true){
                    if (map[sRow + 1][sCol] == " * ") {
                        count++;
                    }
                }
                if(isValid(sRow,sCol+1)==true) {
                    if (map[sRow][sCol + 1] == " * ") {
                        count++;
                    }
                }
                if(isValid(sRow+1,sCol-1)==true) {
                    if (map[sRow + 1][sCol - 1] == " * ") {
                        count++;
                    }
                }
                if(isValid(sRow-1,sCol+1)==true) {
                    if (map[sRow - 1][sCol + 1] == " * ") {
                        count++;
                    }
                }
                if(isValid(sRow,sCol-1)==true) {
                    if (map[sRow][sCol - 1] == " * ") {
                        count++;
                    }
                }
                if(isValid(sRow-1,sCol)==true) {
                    if (map[sRow - 1][sCol] == " * ") {
                        count++;
                    }
                }
                // printing the number of mines.
                board[sRow][sCol] =" "+String.valueOf(count).toString()+" ";

                // Game winning situation.
                goal++;
                if(goal == (size)-mineNumber){
                    System.out.println("=== ' YOU WON ' ===");
                    printField(map);
                    playAgain();
                }
            }
            // Game losing situation.
            if (map[sRow][sCol] == " * ") {
                System.out.println("=== ' GAME OVER ' ===");
                printField(map);
                playAgain();
            }
        }
    }
    // isValid methods purpose is to prevent exceeding the limits of the array when calculating the mines around the point.
    // To prevent ArrayIndexOutOfBoundException error.
    boolean isValid(int row, int col){
        return ( (row>=0) && (col>=0) && (row < this.row) && (col < this.col) );
    }
    // Game restart method.
    void playAgain(){
        System.out.println("Wanna PLAY again? \nIf you wanna play, press 1\nIf you don't, press 2.");
        int select = scan.nextInt();
        switch (select){
            case 1:
                run();
                break;
            case 2:
                System.out.println("Goodbye ! ");
                isGameOn = false;
        }
    }
}
