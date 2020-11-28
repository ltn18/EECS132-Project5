/**
 * @author: Lam Nguyen
 * an abstract class representing all L-type pieces (or we can say the KnightPiece)
 */

public abstract class LPiece extends ChessPiece {

    // LPiece constructor that takes in label, side, and board associated with the piece
    public LPiece(String label, ChessGame.Side side, ChessBoard board) {
        super(label, side, board);
    }

    // check whether the knight piece has made a legal move
    public boolean isLegalMove(int toRow, int toColumn) {
        System.out.println("L-type");

        // check if there is a piece of the same side at the designated position
        if (getChessBoard().hasPiece(toRow, toColumn)) {
            if (getChessBoard().getPiece(toRow, toColumn).getSide() == this.getSide()) {
                return false;
            }
        }

        if (getColumn() + 1 == toColumn && (getRow() + 2 == toRow || getRow() - 2 == toRow)) {
            return true;
        }

        if (getColumn() + 2 == toColumn && (getRow() + 1 == toRow || getRow() - 1 == toRow)) {
            return true;
        }

        if (getColumn() - 1 == toColumn && (getRow() + 2 == toRow || getRow() - 2 == toRow)) {
            return true;
        }

        if (getColumn() - 2 == toColumn && (getRow() + 1 == toRow || getRow() - 1 == toRow)) {
            return true;
        }

        // Check Xianqi Horse
        if (getChessBoard().getPiece(getRow(), getColumn()).getLabel().equals("H")) {
            for (int i = Math.min(getRow(), toRow); i <= Math.max(getRow(), toRow); i++) {
                for (int j = Math.min(getColumn(), toColumn); j <= Math.max(getColumn(), toColumn); j++) {
                    if (getChessBoard().hasPiece(i, j)) {
                        return false;
                    }
                }
            }
        }

        return false;
    }

    // deal with actions after the move is done
    public void moveDone() {}
}
