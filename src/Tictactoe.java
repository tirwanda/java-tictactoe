import java.util.*;

public class Tictactoe {

    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] board = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };

        showBoard(board);

        while(true) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Input your placement (1 - 9): ");
            int playerPos = scanner.nextInt();

            while(playerPosition.contains(playerPos) || cpuPosition.contains(playerPos)) {
                System.out.println("Position Already Taken! Enter a Correct Position..!");
                playerPos = scanner.nextInt();
            }

            placement(board, playerPos, "player");

            String result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;

            while(playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
                cpuPos = scanner.nextInt();
            }

            placement(board, cpuPos, "cpu");

            showBoard(board);

            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    public static void showBoard(char[][] board) {
        for (char[] row: board) {
            for (char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public static void placement(char[][] board, int position, String user) {
        char symbol = 'X';

        if(user.equals("player")) {
            symbol = 'X';
            playerPosition.add(position);
        } else if(user.equals("cpu")) {
            symbol = 'O';
            cpuPosition.add(position);
        }

        switch (position) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List crossL = Arrays.asList(1, 5, 9);
        List crossR = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();

        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(crossL);
        winning.add(crossR);

        for (List l : winning) {
            if(playerPosition.containsAll(l)) {
                return "Congratulation You Won";
            } else if(cpuPosition.containsAll(l)) {
                return  "You Lose!!";
            } else if(playerPosition.size() + cpuPosition.size() == 9) {
                return "Game Over";
            }
        }

        return "";
    }
}
