/**
 * @author: Lam Nguyen
 * a class representing all pawn pieces
 */

public class PawnPiece extends SPiece {

    // PawnPiece constructor with default label "P"
    public PawnPiece(ChessGame.Side side, ChessBoard board) {
        super("P", side, board);
    }
}
