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
//        return true;
        return piece.getChessBoard().hasPiece(row, column) && piece.getSide() == currentSide;
    }

    /**
     * determine whether the king are facing each other
     * @param piece
     * @return
     */
    private static boolean facingKing(ChessPiece piece) {
        ChessPiece northK = new XianqiKingPiece(Side.NORTH, piece.getChessBoard());
        ChessPiece southK = new XianqiKingPiece(Side.SOUTH, piece.getChessBoard());
        ChessBoard board = piece.getChessBoard();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.hasPiece(i, j) && board.getPiece(i, j).getLabel().equals("X")) {
                    ChessPiece king = board.getPiece(i, j);
                    if (king.getSide() == Side.NORTH) {
                        northK = king;
                    }
                    else if (king.getSide() == Side.SOUTH) {
                        southK = king;
                    }
                }
            }
        }

        if (southK.getColumn() == northK.getColumn()) {
            for (int i = Math.min(southK.getRow(), northK.getRow()) + 1; i < Math.max(southK.getRow(), northK.getRow()); i++) {
                if (piece.getChessBoard().hasPiece(i, northK.getColumn())) {
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
        GameMain.initXianqi(Side.SOUTH);
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


    public static void main(String[] args) {
        ChessGame.Side first = Side.SOUTH;
        ChessGame.Side second = Side.NORTH;
        Xianqi xianqi = new Xianqi(first);
        SwingEuropeanChessDisplay swingEuropeanChessDisplay = new SwingEuropeanChessDisplay();
        SwingChessBoard swingChessBoard = new SwingChessBoard(10, 9, swingEuropeanChessDisplay, xianqi);

        // add KingPiece
        swingChessBoard.addPiece(new XianqiKingPiece(second, swingChessBoard), 0, 4);
        swingChessBoard.addPiece(new XianqiKingPiece(first, swingChessBoard), 9, 4);

        // add a piece
        swingChessBoard.addPiece(new HorsePiece(first, swingChessBoard),5, 4);
        swingChessBoard.addPiece(new HorsePiece(first, swingChessBoard),6, 4);

        System.out.println("Facing Kings: " + Xianqi.facingKing(new HorsePiece(first, swingChessBoard)));
    }
}
