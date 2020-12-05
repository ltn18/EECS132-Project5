
/**
 * @author: Lam Nguyen
 * a class to run the chess game
 */

public class GameLauncher {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.next();
//
//        if (input.toUpperCase().equals("CHESS")) {
//            GameMain.initEuropeanChess(ChessGame.Side.SOUTH);
//        }
//        else if (input.toUpperCase().equals("XIANQI")) {
//            GameMain.initXianqi(ChessGame.Side.SOUTH);
//        }
        initXianqi(ChessGame.Side.SOUTH);

    }

    // initialize the complete xianqi board with south-north direction
    public static SwingChessBoard initXianqi(ChessGame.Side first) {
        ChessGame.Side second = first == ChessGame.Side.SOUTH ? ChessGame.Side.NORTH : ChessGame.Side.SOUTH;
        Xianqi xianqi = new Xianqi(first);
        SwingEuropeanChessDisplay swingEuropeanChessDisplay = new SwingEuropeanChessDisplay();
        SwingChessBoard swingChessBoard = new SwingChessBoard(10, 9, swingEuropeanChessDisplay, xianqi);

        // add SoldierPiece
        for (int i = 0; i < 9; ++i) {
            if (i % 2 == 0) {
                swingChessBoard.addPiece(new SoldierPiece(second, swingChessBoard), 3, i);
                swingChessBoard.addPiece(new SoldierPiece(first, swingChessBoard), 6, i);
            }
        }

        // add KingPiece
        swingChessBoard.addPiece(new XianqiKingPiece(second, swingChessBoard), 0, 4);
        swingChessBoard.addPiece(new XianqiKingPiece(first, swingChessBoard), 9, 4);

        // add GuardPiece
        swingChessBoard.addPiece(new GuardPiece(second, swingChessBoard), 0, 3);
        swingChessBoard.addPiece(new GuardPiece(second, swingChessBoard), 0, 5);
        swingChessBoard.addPiece(new GuardPiece(first, swingChessBoard), 9, 3);
        swingChessBoard.addPiece(new GuardPiece(first, swingChessBoard), 9, 5);

        // add ElephantPiece
        swingChessBoard.addPiece(new ElephantPiece(second, swingChessBoard), 0, 2);
        swingChessBoard.addPiece(new ElephantPiece(second, swingChessBoard), 0, 6);
        swingChessBoard.addPiece(new ElephantPiece(first, swingChessBoard), 9, 2);
        swingChessBoard.addPiece(new ElephantPiece(first, swingChessBoard), 9, 6);

        // add HorsePiece
        swingChessBoard.addPiece(new HorsePiece(second, swingChessBoard), 0, 1);
        swingChessBoard.addPiece(new HorsePiece(second, swingChessBoard), 0, 7);
        swingChessBoard.addPiece(new HorsePiece(first, swingChessBoard), 9, 1);
        swingChessBoard.addPiece(new HorsePiece(first, swingChessBoard), 9, 7);

        // add RookPiece
        swingChessBoard.addPiece(new RookPiece(second, swingChessBoard), 0, 0);
        swingChessBoard.addPiece(new RookPiece(second, swingChessBoard), 0, 8);
        swingChessBoard.addPiece(new RookPiece(first, swingChessBoard), 9, 0);
        swingChessBoard.addPiece(new RookPiece(first, swingChessBoard), 9, 8);

        // add CannonPiece
        swingChessBoard.addPiece(new CannonPiece(second, swingChessBoard), 2, 1);
        swingChessBoard.addPiece(new CannonPiece(second, swingChessBoard), 2, 7);
        swingChessBoard.addPiece(new CannonPiece(first, swingChessBoard), 7, 1);
        swingChessBoard.addPiece(new CannonPiece(first, swingChessBoard), 7, 7);

        return swingChessBoard;
    }
}
