/**
 * @author: Lam Nguyen
 * a class implementing ChessGame to simulate EuropeanChess
 */

public class EuropeanChess implements ChessGame {

    // the side of the player who will go first
    public Side currentSide;

    // the first player that will be playing
    public Side firstPlayer;

    // EuropeanChess constructor that takes in side of first player
    public EuropeanChess(Side currentSide) {
        this.currentSide = currentSide;
        this.firstPlayer = currentSide;
    }

    // get the first/next player
    public Side getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public int getNumRows() {
        return 8;
    }

    @Override
    public int getNumColumns() {
        return 8;
    }

    @Override
    public void startGame(ChessBoard board) {
        GameMain.initEuropeanChess(Side.SOUTH);
    }

    // update the next player
    public void setCurrentSide(Side currentSide) {
        this.currentSide = currentSide;
    }

    // return whether a selected piece can be played or not
    public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
        return piece.getChessBoard().hasPiece(row, column) && piece.getSide() == currentSide;
    }

    // return whether a move can be made by a piece or not
    public boolean makeMove(ChessPiece piece, int toRow, int toColumn) {
        if (piece.isLegalMove(toRow, toColumn)) {
            System.out.println("prev: " + piece.getRow() + " " + piece.getColumn() + " " + piece.getLabel());

            // the piece that is temporarily removed from the table
            ChessPiece save = piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());

            piece.getChessBoard().addPiece(save, toRow, toColumn);

            System.out.println("curr: " + piece.getRow() + " " + piece.getColumn() + " " + piece.getLabel());

            if (piece.getLabel() == "K") {
                // return whether the castle is threatened or not
                System.out.println("THREATENED: " + castleThreatened(piece, toRow, toColumn));
                piece.setKingFirstMove(false);
            }
            if (piece.getLabel() == "R") {
                piece.setRookFirstMove(false);
            }
            if (piece.getLabel() == "P") {
                piece.setPawnFirstMove(false);
            }

            setCurrentSide(currentSide == Side.NORTH? Side.SOUTH : Side.NORTH);

            System.out.println("next player: " + currentSide);
            System.out.println("-------------------");

            return true;
        }
        return false;
    }

    // return whether the castle is threatened or not
    public boolean castleThreatened(ChessPiece piece, int toRow, int toColumn) {
        // specific case of Bishop attacking castle move containing location (7, 6)
        if (piece.getChessBoard().hasPiece(3,2)
                && piece.getChessBoard().getPiece(3, 2).getSide() != piece.getSide() &&
                piece.getChessBoard().getPiece(3, 2).isLegalMove(toRow, 6)) {
            return true;
        }

        return false;
    }

    // return whether selection of a piece can be changed or not
    public boolean canChangeSelection(ChessPiece piece, int row, int column) {
        return true;
    }
}
