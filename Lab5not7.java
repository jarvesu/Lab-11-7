import java.util.Scanner;

public class ConnectFour {
//Comment for lab 11
    public static void main(String[] args){
        int height = 0, length = 0, rounds = 0, row = 0, col = 0;
        char chipType = '-';
        boolean winnerFound = false;
        Scanner scanner = new Scanner(System.in);
        System.out.print("What would you like the height of the board to be? ");
        height = scanner.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        length = scanner.nextInt();
        char[][] board = new char[height][length];
        rounds = height * length; //gives maximum number of rounds possible
        initializeBoard(board);
        System.out.println("\nPlayer 1: x\nPlayer 2: o");

        while(rounds != 0 && !winnerFound){
            //Player 1
            System.out.print("\nPlayer 1: Which column would you like to choose? ");
            col = scanner.nextInt();
            chipType = 'x'; //Player 1's chip type
            row = insertChip(board, col, chipType);
            printBoard(board);
            winnerFound = checkIfWinner(board, col, row, chipType);
            if(winnerFound) {
                System.out.println("\nPlayer 1 won the game!");
                break;
            }
            --rounds;
            //Player 2
            System.out.print("\nPlayer 2: Which column would you like to choose? ");
            col = scanner.nextInt();
            chipType = 'o'; //Player 2's chip type
            row = insertChip(board, col, chipType);
            printBoard(board);
            winnerFound = checkIfWinner(board, col, row, chipType);
            if(winnerFound) {
                System.out.println("\nPlayer 2 won the game!");
                break;
            }
            --rounds;
            if(rounds == 0 && !winnerFound){
                System.out.println("\nDraw. Nobody wins.");
                break;
            }
        }
    }
    public static void printBoard(char[][] array){
        for(int m = 0; m < array.length; ++m){
            for(int k = 0; k < array[m].length; ++k) {
                System.out.print(array[m][k] + "\t");
            }
            System.out.println();
        }
    }

    public static void initializeBoard(char[][] array){
        for(int i = 0; i < array.length; ++i){
            for(int j = 0; j < array[i].length; ++j){
                array[i][j] = '-';
            }
        }
        char[][] board = array;
        printBoard(array);
    }

    public static int insertChip(char[][]array, int col, char chipType){
        for(int i = array.length - 1; i >= 0; --i){
            //System.out.println("I is: " + i + ".");
            if(array[i][col] == '-'){
                array[i][col] = chipType;
                return i;
            }
        }
        return 0;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType){
        int sumLeft = 0, sumAbove = 0, sumBelow = 0, sumRight = 0;
        boolean winnerFound = false;
        //Find sum of consecutive matching chips
        //to the left of newly placed chip
        for(int j = col; j >= 0; --j){
            if(chipType == array[row][j]){
                ++sumLeft;
            } else {
                break;
            }
        }
        //to the right of newly placed chip
        for(int j = col; j < array[row].length - col - 1; ++j){
            if(chipType == array[row][j]){
                ++sumRight;
            } else {
                break;
            }
        }
        //above the newly placed chip
        for(int i = row; i >= 0; --i){
            if(chipType == array[i][col]){
                ++sumAbove;
            } else {
                break;
            }
        }
        //below the newly placed chip
        for(int i = row; i < array.length - row - 1; ++i){
            //check error here
            if(chipType == array[i][col]){
                ++sumBelow;
            } else {
                break;
            }
        }
        System.out.println("SumAbove: " + sumAbove + "\tSumBelow: " + sumBelow);
        if(((sumRight + sumLeft - 2) >= 3) || ((sumAbove + sumBelow - 2) >= 3) || sumLeft == 4 || sumRight == 4 || sumAbove == 4 || sumBelow == 4){
            winnerFound = true;
        }
        return winnerFound;
    }
}
