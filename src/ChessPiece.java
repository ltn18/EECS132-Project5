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

    // the boolean that determines whether the soldier has pass middle boundary
    private boolean soldierPassBound = false;

    // ChessPiece constructor that takes in label, side, and board associated with the piece
    public ChessPiece(String label, ChessGame.Side side, ChessBoard board) {
        this.side = side;
        this.label = label;
        this.board = board;
    }

    /**
     * get the player's side associated with the piece
     * @return the player's side associated with the piece
     */
    public ChessGame.Side getSide() {
        return side;
    }

    /**
     * get the label associated with the piece
     * @return the label associated with the piece
     */
    public String getLabel() {
        return label;
    }

    /**
     * get the icon associated with the piece
     * @return the icon associated with the piece
     */
    public Object getIcon() {
        return icon;
    }

    /**
     * update the location of the piece to a new one
     * @param row row to be set to
     * @param column column to be set to
     */
    public void setLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * abstract method checking if the piece's move can be done or not
     * @param toRow destination row
     * @param toColumn destination column
     * @return a boolean value
     */
    public abstract boolean isLegalMove(int toRow, int toColumn);

    /**
     * get the game board associated with the piece
     * @return the game board associated with the piece
     */
    public ChessBoard getChessBoard() {
        return board;
    }

    /**
     * get the row associated with the piece
     * @return the row associated with the piece
     */
    public int getRow() {
        return row;
    }

    /**
     * get the column associated with the piece
     * @return the column associated with the piece
     */
    public int getColumn() {
        return column;
    }

    // return

    /**
     * determine whether it is a legal non-capture move
     * @param row destination row
     * @param column destination column
     * @return whether it is a legal non-capture move
     */
    public boolean isLegalNonCaptureMove(int row, int column) {
        return isLegalMove(row, column) && !getChessBoard().hasPiece(row, column);
    }

    /**
     * determine whether it is a legal capture move
     * @param row destination row
     * @param column destination column
     * @return whether it is a legal capture move
     */
    public boolean isLegalCaptureMove(int row, int column) {
        return getChessBoard().hasPiece(row, column)
                && isLegalMove(row, column)
                &&  getChessBoard().getPiece(row, column).getSide() != this.getSide();
    }

    /**
     * deal with actions after the move is done
     */
    public abstract void moveDone();

    /**
     * get the boolean pawnFirstMove associated with the piece
     * @return the boolean pawnFirstMove associated with the piece
     */
    public boolean getPawnFirstMove() {
        return pawnFirstMove;
    }

    /**
     * update the boolean pawnFirstMove associated with the piece
     * @param pawnFirstMove the pawn's first move
     */
    public void setPawnFirstMove(boolean pawnFirstMove) {
        this.pawnFirstMove = pawnFirstMove;
    }

    /**
     * get the boolean kingFirstMove associated with the piece
     * @return the boolean kingFirstMove associated with the piece
     */
    public boolean getKingFirstMove() {
        return kingFirstMove;
    }

    /**
     * update the boolean pawnFirstMove associated with the piece
     * @param kingFirstMove the king's first move
     */
    public void setKingFirstMove(boolean kingFirstMove) {
        this.kingFirstMove = kingFirstMove;
    }

    /**
     * get the boolean rookFirstMove associated with the piece
     * @return the boolean rookFirstMove associated with the piece
     */
    public boolean getRookFirstMove() {
        return rookFirstMove;
    }

    /**
     * update the boolean rookFirstMove associated with the piece
     * @param rookFirstMove the rook's first move
     */
    public void setRookFirstMove(boolean rookFirstMove) {
        this.rookFirstMove = rookFirstMove;
    }

    /**
     * check if the piece made a legal diagonal path or not
     * @param toRow destination row
     * @param toColumn destination column
     * @return whether a legal diagonal path
     */
    public boolean legalDiagonalPath(int toRow, int toColumn) {
        if (getLabel().equals("C")) {
            return false;
        }

        if (getLabel().equals("R")) {
            return false;
        }

        // handle guard out of palace
        if (getLabel().equals("G")) {
            if (Math.abs(getRow() - toRow) == 1 && Math.abs((getColumn() - toColumn)) == 1) {
                if (toColumn < 3 || toColumn > 5) {
                    return false;
                } else {
                    ChessGame.Side side = getChessBoard().getPiece(getRow(), getColumn()).getSide();
                    if (side == ChessGame.Side.NORTH) {
                        return toRow <= 2;
                    }
                    else if (side == ChessGame.Side.SOUTH) {
                        return toRow >= 7;
                    }
                }
            } else {
                return false;
            }
        }

        // handle elephant crossing river
        if (getLabel().equals("E")) {
            if (Math.abs(getRow() - toRow) == 2 && Math.abs((getColumn() - toColumn)) == 2) {
                ChessGame.Side side = getChessBoard().getPiece(getRow(), getColumn()).getSide();
                if ((side == ChessGame.Side.NORTH && toRow > 4)
                        || (side == ChessGame.Side.SOUTH && toRow < 5)) {
                    return false;
                }
            } else {
                return false;
            }
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

    /**
     * check if the piece made a legal horizontal path or not
     * @param toRow destination row
     * @param toColumn destination column
     * @return whether a legal horizontal path
     */
    public boolean legalHorizontalPath(int toRow, int toColumn) {
        if (getLabel().equals("G")) {
            return false;
        }

        if (getLabel().equals("E")) {
            return false;
        }

        if (getLabel().equals("H")) {
            return false;
        }

        if (getLabel().equals("B")) {
            return false;
        }

        if (getRow() == toRow) {
            System.out.println("Horizontal");

            // check if there is any piece in the path
            if (!getLabel().equals("C")) {
                for (int i = Math.min(toColumn, getColumn()) + 1; i < Math.max(toColumn, getColumn()); ++i) {
                    if (getChessBoard().hasPiece(getRow(), i)) {
                        return false;
                    }
                }
            }
            else {
                if (getChessBoard().hasPiece(toRow, toColumn)) {
                    if (getChessBoard().getPiece(toRow, toColumn).getSide() == getChessBoard().getPiece(getRow(), getColumn()).getSide()) {
                        return false;
                    }
                    else {
                        // total piece in the path
                        int pieceCnt = 0;

                        // iterate all squares in the path
                        if (getRow() == toRow) {
                            for (int j = Math.min(toColumn, getColumn()) + 1; j < Math.max(toColumn, getColumn()); j++) {
                                if (getChessBoard().hasPiece(getRow(), j)) {
                                    pieceCnt++;
                                }
                            }
                        }
                        else if (getColumn() == toColumn) {
                            for (int j = Math.min(toRow, getRow()) + 1; j < Math.max(toRow, getRow()); j++) {
                                if (getChessBoard().hasPiece(j, getColumn())) {
                                    pieceCnt++;
                                }
                            }
                        }

                        System.out.println(pieceCnt);

                        // determine legal capture move
                        if (pieceCnt == 1) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                else {
                    // check if there is any piece in the path
                    for (int i = Math.min(toColumn, getColumn()) + 1; i < Math.max(toColumn, getColumn()); ++i) {
                        if (getChessBoard().hasPiece(getRow(), i)) {
                            return false;
                        }
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * check if the piece made a legal vertical path or not
     * @param toRow destination row
     * @param toColumn destination column
     * @return whether a legal diagonal path
     */
    public boolean legalVerticalPath(int toRow, int toColumn) {
        if (getLabel().equals("G")) {
            return false;
        }

        if (getLabel().equals("E")) {
            return false;
        }

        if (getLabel().equals("H")) {
            return false;
        }

        if (getLabel().equals("B")) {
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
            if (!getLabel().equals("C")) {
                for (int i = Math.min(toRow, getRow()) + 1; i < Math.max(toRow, getRow()); ++i) {
                    if (getChessBoard().hasPiece(i, getColumn())) {
                        return false;
                    }
                }
            }
            else {
                if (getChessBoard().hasPiece(toRow, toColumn)) {
                    if (getChessBoard().getPiece(toRow, toColumn).getSide() == getChessBoard().getPiece(getRow(), getColumn()).getSide()) {
                        return false;
                    }
                    else {
                        // total piece in the path
                        int pieceCnt = 0;

                        // iterate all squares in the path
                        if (getColumn() == toColumn) {
                            for (int j = Math.min(toRow, getRow()) + 1; j < Math.max(toRow, getRow()); j++) {
                                if (getChessBoard().hasPiece(j, getColumn())) {
                                    pieceCnt++;
                                }
                            }
                        }
                        else if (getRow() == toRow) {
                            for (int j = Math.min(toColumn, getColumn()) + 1; j < Math.max(toColumn, getColumn()); j++) {
                                if (getChessBoard().hasPiece(getRow(), j)) {
                                    pieceCnt++;
                                }
                            }
                        }

                        System.out.println(pieceCnt);

                        // determine legal capture move
                        if (pieceCnt == 1) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                else {
                    // check if there is any piece in the path
                    for (int i = Math.min(toRow, getRow()) + 1; i < Math.max(toRow, getRow()); ++i) {
                        if (getChessBoard().hasPiece(i, getColumn())) {
                            return false;
                        }
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * determine whether the soldier has passed the boundary
     * @return whether the soldier has passed the boundary
     */
    public boolean getSoldierPassBound() {
        return soldierPassBound;
    }

    /**
     * update whether the soldier has passed the boundary
     * @param soldierPassBound boolean storing whether the soldier has passed the boundary
     */
    public void setSoldierPassBound(boolean soldierPassBound) {
        this.soldierPassBound = soldierPassBound;
    }
}
