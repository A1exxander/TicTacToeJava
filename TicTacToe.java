import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    
    public static void main(String[] args) {
            game();
    }
    
    public static void game(){
    
    char repeat = 'Y';
    
    Scanner input = new Scanner(System.in);
    
    while(repeat == 'Y' || repeat == 'y'){
    
    char[][] gameBoard = {                   // Create a new char array called gameboard each loop to clean up the previous game
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
    };
    printGameBoard(gameBoard);
    
        
    while(true) {
    
        System.out.println("Enter a spot (1-9):");
        int playerPos = input.nextInt();
        
        while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
            System.out.println("Position taken! Enter a correct Position");
            playerPos = input.nextInt();
        }

        placePiece(gameBoard, playerPos, "player");
        String result = checkWinner();
        
        if (result.length() > 0) {
            System.out.println(result);
            break;
        }
        
        Random rand = new Random(); // Create an instance of random to have the CPU select a random spot on the board
        
        int cpuPos = rand.nextInt(9) + 1;
        while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
            cpuPos = rand.nextInt(9) + 1;
        }
        placePiece(gameBoard, cpuPos, "cpu");
        printGameBoard(gameBoard);
        
        result = checkWinner();
        
        if (result.length() > 0) {
            System.out.println(result);
            break;
        }

     }
     System.out.println("\nWould you like to play again? Press Y to play again and any other key to exit.");
     repeat = input.next().charAt(0);
     }
   }


    public static void printGameBoard(char[][] gameBoard) {
       
        for(char[] row : gameBoard) {
        
            for(char c : row) {
                System.out.print(c);
            }
            
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } 
        else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {                          // Checks if user or bot won
        
        String winner = "";
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8,9);
        List leftCol = Arrays.asList(1, 2, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winConditions = new ArrayList<List>();
        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(botRow);
        winConditions.add(leftCol);
        winConditions.add(midCol);
        winConditions.add(rightCol);
        winConditions.add(cross1);
        winConditions.add(cross2);

        for(List l : winConditions) { // Incase someone won or the game cannot be won
            if(playerPositions.containsAll(l)) {
                winner = "You won!";
            } else if (cpuPositions.containsAll(l)) {
                winner = "CPU wins!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                winner = "TIE";
            }
        }

        return winner;
    }
 }