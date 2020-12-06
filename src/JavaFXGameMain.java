/**
 * @author: Lam Nguyen
 * a class representing javafx european chess board and xianqi board
 */
public class JavaFXGameMain {

    private final JavaFXChessBoard board = new JavaFXChessBoard();

    private final ChessGame.Side first = ChessGame.Side.SOUTH;

    private final ChessGame.Side second = ChessGame.Side.NORTH;

//    private ChessPiece[][] pieces;

    public ChessPiece[][] initPieces(String input) {
        if (input.toUpperCase().equals("CHESS")) {
            return new ChessPiece[8][8];
        }
        else if (input.toUpperCase().equals("XIANQI")) {
            return new ChessPiece[10][9];
        }
        return null;
    }

    public void initEuropeanChess() {
        board.setGameRules(new EuropeanChess(ChessGame.Side.SOUTH));
        board.setBoardDisplay(new JavaFXEuropeanChessDisplay());

        // add PawnPiece
        for (int i = 0; i < 8; ++i) {
            board.addPiece(new PawnPiece(second, board), 1, i);
            board.addPiece(new PawnPiece(first, board), 6, i);
        }

        // add KingPiece
        board.addPiece(new KingPiece(second, board), 0, 4);
        board.addPiece(new KingPiece(first, board), 7, 4);

        // add QueenPiece
        board.addPiece(new QueenPiece(second, board), 0, 3);
        board.addPiece(new QueenPiece(first, board), 7, 3);

        // add BishopPiece
        board.addPiece(new BishopPiece(second, board), 0, 2);
        board.addPiece(new BishopPiece(second, board), 0, 5);
        board.addPiece(new BishopPiece(first, board), 7, 2);
        board.addPiece(new BishopPiece(first, board), 7, 5);

        // add KnightPiece
        board.addPiece(new KnightPiece(second, board), 0, 1);
        board.addPiece(new KnightPiece(second, board), 0, 6);
        board.addPiece(new KnightPiece(first, board), 7, 1);
        board.addPiece(new KnightPiece(first, board), 7, 6);

        // add RookPiece
        board.addPiece(new RookPiece(second, board), 0, 0);
        board.addPiece(new RookPiece(second, board), 0, 7);
        board.addPiece(new RookPiece(first, board), 7, 0);
        board.addPiece(new RookPiece(first, board), 7, 7);
    }

}
