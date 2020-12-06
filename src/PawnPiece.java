/**
 * @author: Lam Nguyen
 * a class representing all pawn pieces
 */
public class PawnPiece extends SPiece {

    /**
     * PawnPiece constructor with default label "P"
     * @param side the side of the piece
     * @param board the associated board
     */
    public PawnPiece(ChessGame.Side side, ChessBoard board) {
        super("P", side, board);
    }
}
