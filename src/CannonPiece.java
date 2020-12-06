/**
 * @author: Lam Nguyen
 * a class representing all cannon pieces
 */
public class CannonPiece extends SPiece {

    /**
     * CannonPiece constructor with default label "C"
     * @param side the side of the piece
     * @param board the associated board
     */
    public CannonPiece(ChessGame.Side side, ChessBoard board) {
        super("C", side, board);
    }
}
