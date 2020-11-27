/**
 * @author: Lam Nguyen
 * a class to run the chess game
 */

public class GameLauncher {

    // initialize the complete board with south-north direction
    public static ChessBoard init() {
        ChessGame.Side first = ChessGame.Side.SOUTH;
        ChessGame.Side second = ChessGame.Side.NORTH;
        EuropeanChess europeanChess = new EuropeanChess(first);
        EuropeanChessDisplay europeanChessDisplay = new EuropeanChessDisplay();
        ChessBoard chessBoard = new ChessBoard(8, 8, europeanChessDisplay, europeanChess);

        // add PawnPiece
        for (int i = 0; i < 8; ++i) {
            chessBoard.addPiece(new PawnPiece(second, chessBoard), 1, i);
            chessBoard.addPiece(new PawnPiece(first, chessBoard), 6, i);
        }
        // add KingPiece
        chessBoard.addPiece(new KingPiece(second, chessBoard), 0, 4);
        chessBoard.addPiece(new KingPiece(first, chessBoard), 7, 4);

        // add QueenPiece
        chessBoard.addPiece(new QueenPiece(second, chessBoard), 0, 3);
        chessBoard.addPiece(new QueenPiece(first, chessBoard), 7, 3);

        // add BishopPiece
        chessBoard.addPiece(new BishopPiece(second, chessBoard), 0, 2);
        chessBoard.addPiece(new BishopPiece(second, chessBoard), 0, 5);
        chessBoard.addPiece(new BishopPiece(first, chessBoard), 7, 2);
        chessBoard.addPiece(new BishopPiece(first, chessBoard), 7, 5);

        // add KnightPiece
        chessBoard.addPiece(new KnightPiece(second, chessBoard), 0, 1);
        chessBoard.addPiece(new KnightPiece(second, chessBoard), 0, 6);
        chessBoard.addPiece(new KnightPiece(first, chessBoard), 7, 1);
        chessBoard.addPiece(new KnightPiece(first, chessBoard), 7, 6);

        // add RookPiece
        chessBoard.addPiece(new RookPiece(second, chessBoard), 0, 0);
        chessBoard.addPiece(new RookPiece(second, chessBoard), 0, 7);
        chessBoard.addPiece(new RookPiece(first, chessBoard), 7, 0);
        chessBoard.addPiece(new RookPiece(first, chessBoard), 7, 7);

        return chessBoard;
    }

    public static void main(String[] args) {
        // store the complete board for run
        ChessBoard fullChessBoard = init();
    }
}
