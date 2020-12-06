/**
 * @author: Lam Nguyen
 * a class representing all horse pieces
 */
public class HorsePiece extends LPiece {

    /**
     * HorsePiece constructor with default label "H"
     * @param side the side of the piece
     * @param board the associated board
     */
    public HorsePiece(ChessGame.Side side, ChessBoard board) {
        super("H", side, board);
    }
}
