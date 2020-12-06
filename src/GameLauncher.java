import java.util.Scanner;

/**
 * @author: Lam Nguyen
 * a class to run the chess game
 */

public class GameLauncher {

    public static void main(String[] args) {
        // handle command line
        Scanner scanner = new Scanner(System.in);

        // string storing the value of the first line in terminal
        String input = scanner.next();

        // initialize the game according to the input
        if (input.toUpperCase().equals("CHESS")) {
            GameMain.initEuropeanChess(ChessGame.Side.SOUTH);
        }
        else if (input.toUpperCase().equals("XIANQI")) {
            GameMain.initXianqi(ChessGame.Side.SOUTH);
        }
    }
}
