/**
 * @author: Lam Nguyen
 * a class representing all soldier pieces
 */
public class SoldierPiece extends SPiece {

    /**
     * SoldierPiece constructor with default label "S"
     * @param side the side of the piece
     * @param board the associated board
     */
    public SoldierPiece(ChessGame.Side side, ChessBoard board) {
        super("S", side, board);
    }
}
