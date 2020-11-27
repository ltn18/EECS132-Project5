/**
 * @author: Lam Nguyen
 * an abstract class representing all chess pieces
 */

public abstract class ChessPiece {

    // the game board associated with the piece
    private ChessBoard board;

    // the player's side associated with the piece
    private ChessGame.Side side;

    // the label associated with the piece
    private final String label;

    // the icon associated with the piece
    public Object icon = null;

    // the row associated with the piece
    private int row;

    // the column associated with the piece
    private int column;

    // the boolean pawnFirstMove set to false only for pawns making first move
    private boolean pawnFirstMove = true;

    // the boolean kingFirstMove set to false only for kings making first move
    private boolean kingFirstMove = true;

    // the boolean rookFirstMove set to false only for rooks making first move
    private boolean rookFirstMove = true;

    // ChessPiece constructor that takes in label, side, and board associated with the piece
    public ChessPiece(String label, ChessGame.Side side, ChessBoard board) {
        this.side = side;
        this.label = label;
        this.board = board;
    }

    // return the player's side associated with the piece
    public ChessGame.Side getSide() {
        return side;
    }

    // return the label associated with the piece
    public String getLabel() {
        return label;
    }

    // return the icon associated with the piece
    public Object getIcon() {
        return icon;
    }

    // update the location of the piece to a new one
    public void setLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // abstract method checking if the piece's move can be done or not
    public abstract boolean isLegalMove(int toRow, int toColumn);

    // return the game board associated with the piece
    public ChessBoard getChessBoard() {
        return board;
    }

    // return the row associated with the piece
    public int getRow() {
        return row;
    }

    // return the column associated with the piece
    public int getColumn() {
        return column;
    }

    // return whether it is a legal non-capture move
    public boolean isLegalNonCaptureMove(int row, int column) {
        return isLegalMove(row, column) && !getChessBoard().hasPiece(row, column);
    }

    // return whether it is a legal capture move
    public boolean isLegalCaptureMove(int row, int column) {
        return getChessBoard().hasPiece(row, column)
                && isLegalMove(row, column)
                &&  getChessBoard().getPiece(row, column).getSide() != this.getSide();
    }

    // deal with actions after the move is done
    public abstract void moveDone();

    // return the boolean pawnFirstMove associated with the piece
    public boolean getPawnFirstMove() {
        return pawnFirstMove;
    }

    // update the boolean pawnFirstMove associated with the piece
    public void setPawnFirstMove(boolean pawnFirstMove) {
        this.pawnFirstMove = pawnFirstMove;
    }

    // return the boolean kingFirstMove associated with the piece
    public boolean getKingFirstMove() {
        return kingFirstMove;
    }

    // update the boolean pawnFirstMove associated with the piece
    public void setKingFirstMove(boolean kingFirstMove) {
        this.kingFirstMove = kingFirstMove;
    }

    // return the boolean rookFirstMove associated with the piece
    public boolean getRookFirstMove() {
        return rookFirstMove;
    }

    // update the boolean rookFirstMove associated with the piece
    public void setRookFirstMove(boolean rookFirstMove) {
        this.rookFirstMove = rookFirstMove;
    }

    // check if the piece made a legal diagonal path or not
    public boolean legalDiagonalPath(int toRow, int toColumn) {
        if (getLabel() == "R") {
            return false;
        }

        // the condition for a path to be diagonal
        if (Math.abs(toRow - getRow()) == Math.abs(toColumn - getColumn())) {
            System.out.println("Diagonal");

            /**
             * dRow and dColumn used to decide whether the piece
             * has moved in main or sub diagonal and has moved upwards
             * or downwards relating to its position
             */
            int dRow = getRow() > toRow ? -1 : 1;
            int dColumn = getColumn() > toColumn ? -1 : 1;

            // check if there is any piece in the path
            for (int i = 1; i < Math.max(getRow(), toRow) - Math.min(getRow(), toRow); ++i) {
                if (getChessBoard().hasPiece(getRow() + dRow * i, getColumn() + dColumn * i)) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    // check if the piece made a legal horizontal path or not
    public boolean legalHorizontalPath(int toRow, int toColumn) {
        if (getLabel() == "B") {
            return false;
        }

        if (getRow() == toRow) {
            System.out.println("Horizontal");

            // check if there is any piece in the path
            for (int i = Math.min(toColumn, getColumn()) + 1; i < Math.max(toColumn, getColumn()); ++i) {
                if (getChessBoard().hasPiece(getRow(), i)) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    // check if the piece made a legal vertical path or not
    public boolean legalVerticalPath(int toRow, int toColumn) {
        if (getLabel() == "B") {
            return false;
        }

        if (getColumn() == toColumn) {
            System.out.println("Vertical");

            // check if is there a piece vertically on the path of a pawn
            if (getChessBoard().getPiece(getRow(), getColumn()).getLabel() == "P") {
                if (getChessBoard().hasPiece(toRow, toColumn)) {
                    return false;
                }
            }

            // check if there is any piece in the path
            for (int i = Math.min(toRow, getRow()) + 1; i < Math.max(toRow, getRow()); ++i) {
                if (getChessBoard().hasPiece(i, getColumn())) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }
}
