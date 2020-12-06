/**
 * @author: Lam Nguyen
 * a class implementing ChessGame to simulate Xianqi
 */

public class Xianqi implements ChessGame {

    // the side of the player who will go first
    public Side currentSide;

    // the first player that will be playing
    public Side firstPlayer;

    /**
     * Xianqi constructor that takes in side of first player
     * @param currentSide the current player
     */
    public Xianqi(Side currentSide) {
        this.currentSide = currentSide;
        this.firstPlayer = currentSide;
    }

    /**
     * determine whether a selected piece can be played or not
     * @param piece   the piece to be played
     * @param row     the row of the square the piece is on
     * @param column  the column of the square the piece is on
     * @return whether a selected piece can be played or not
     */
    @Override
    public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
        return true;
//        return piece.getChessBoard().hasPiece(row, column) && piece.getSide() == currentSide;
    }

    /**
     * determine whether the king are facing each other
     * @param piece
     * @return
     */
    private static boolean facingKing(ChessPiece piece) {

        // initialize positions of north king
        int northCol = -1;
        int northRow = -1;

        // initialize positions of south king
        int southCol = -1;
        int southRow = -1;

        // get the board
        ChessBoard board = piece.getChessBoard();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.hasPiece(i, j) && board.getPiece(i, j).getLabel().equals("X")) {
                    ChessPiece king = board.getPiece(i, j);

                    if (king.getSide() == Side.NORTH) {
                        northCol = king.getColumn();
                        northRow = king.getRow();
                    }

                    if (king.getSide() == Side.SOUTH) {
                        southCol = king.getColumn();
                        southRow = king.getRow();
                    }
                }
            }
        }

        /**
         * Since the logic happens after we removed a piece on the board,
         * the board now has 1 king only. What we need to do is to add the
         * positions of the king removed to the associated positions for later
         * procedures to happen.
         */
        if (northCol == -1 && northRow == -1) {
            northCol = piece.getColumn();
            northRow = piece.getRow();
        }

        if (southCol== -1 && southRow == -1) {
            southCol = piece.getColumn();
            southRow = piece.getRow();
        }

        // check whether the kings are blocked by a piece
        if (northCol == southCol) {
            for (int i = Math.min(southRow, northRow) + 1; i < Math.max(southRow, northCol); i++) {
                if (piece.getChessBoard().hasPiece(i, northCol)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * determine whether to make a move
     * @param piece     the piece to move
     * @param toRow     the row of the square the piece is moving to
     * @param toColumn  the column of the square the piece is moving to
     * @return whether to make a move
     */
    @Override
    public boolean makeMove(ChessPiece piece, int toRow, int toColumn) {
        if (piece.isLegalMove(toRow, toColumn)) {
            System.out.println("prev: " + piece.getRow() + " " + piece.getColumn() + " " + piece.getLabel());

            // the piece that is temporarily removed from the table
            ChessPiece save = piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());

            // check after the piece has been removed and make move if legit
            if (!facingKing(piece)) {
                piece.getChessBoard().addPiece(save, toRow, toColumn);
            } else {
                piece.getChessBoard().addPiece(save, save.getRow(), save.getColumn());
            }

            System.out.println("curr: " + piece.getRow() + " " + piece.getColumn() + " " + piece.getLabel());

            setCurrentSide(currentSide == Side.NORTH? Side.SOUTH : Side.NORTH);

            System.out.println("next player: " + currentSide);
            System.out.println("-------------------");

            return true;
        }
        return false;
    }

    /**
     * get the first player's side
     * @return the first player's side
     */
    @Override
    public Side getFirstPlayer() {
        return firstPlayer;
    }

    /**
     * get the number of rows
     * @return the number of rows
     */
    @Override
    public int getNumRows() {
        return 10;
    }

    /**
     * get the number of columns
     * @return the number of columns
     */
    @Override
    public int getNumColumns() {
        return 9;
    }

    /**
     * update the next player
     * @param currentSide the current player
     */
    public void setCurrentSide(Side currentSide) {
        this.currentSide = currentSide;
    }

    /**
     * create a south->north game
     * @param board the chess board
     */
    @Override
    public void startGame(ChessBoard board) {
        SwingGameMain.initXianqi(Side.SOUTH);
    }

    /**
     * determine whether selection of a piece can be changed or not
     * @param piece   the piece the user selected
     * @param row     the row of the square the piece is on
     * @param column  the column of the square the piece is on
     * @return whether selection of a piece can be changed or not
     */
    public boolean canChangeSelection(ChessPiece piece, int row, int column) {
        return true;
    }


    /**
     * Main method for testing facing king
     * @param args
     */
    public static void main(String[] args) {

        // first player
        ChessGame.Side first = Side.SOUTH;

        // second player
        ChessGame.Side second = Side.NORTH;

        // initialize xianqi game
        Xianqi xianqi = new Xianqi(first);

        // create a display
        SwingEuropeanChessDisplay swingEuropeanChessDisplay = new SwingEuropeanChessDisplay();

        // create the xianqi board
        SwingChessBoard swingChessBoard = new SwingChessBoard(10, 9, swingEuropeanChessDisplay, xianqi);

        // add KingPiece
        swingChessBoard.addPiece(new XianqiKingPiece(second, swingChessBoard), 0, 4);
        swingChessBoard.addPiece(new XianqiKingPiece(first, swingChessBoard), 9, 4);

        // add HorsePiece
        swingChessBoard.addPiece(new HorsePiece(first, swingChessBoard),5, 4);
        swingChessBoard.addPiece(new HorsePiece(first, swingChessBoard),6, 4);

        System.out.println("Facing Kings: " + Xianqi.facingKing(new HorsePiece(first, swingChessBoard)));
    }
}
