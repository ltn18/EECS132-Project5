/**
 * @author: Lam Nguyen
 * a class implementing ChessGame to simulate Xianqi
 */

public class Xianqi implements ChessGame {

    // the side of the player who will go first
    public Side currentSide;

    // the first player that will be playing
    public Side firstPlayer;

    // Xianqi constructor that takes in side of first player
    public Xianqi(Side currentSide) {
        this.currentSide = currentSide;
        this.firstPlayer = currentSide;
    }

    @Override
    // return whether a selected piece can be played or not
    public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
        return true;
//        return piece.getChessBoard().hasPiece(row, column) && piece.getSide() == currentSide;
    }

    private boolean notFacingKing(ChessPiece piece) {
        ChessPiece northK = null;
        ChessPiece southK = null;

//        for (int i = 3; i <= 5; i++) {
//            for (int j = 0; j <= 2; j++) {
//                if (piece.getChessBoard().hasPiece(i, j)
//                        && piece.getChessBoard().getPiece(i, j).getLabel().equals("K")) {
//                    northK = piece.getChessBoard().getPiece(i, j);
//                }
//            }
//
//            for (int j = 7; j <= 9; j++) {
//                if (piece.getChessBoard().hasPiece(i, j)
//                        && piece.getChessBoard().getPiece(i, j).getLabel().equals("K")) {
//                    southK = piece.getChessBoard().getPiece(i, j);
//                }
//            }
//        }

        boolean blocked = false;
        if (southK != null && northK != null
                && southK.getColumn() == northK.getColumn()) {
            for (int i = 0; i < 10; i++) {
                if (piece.getChessBoard().hasPiece(i, northK.getColumn())) {
                    blocked = true;
                }
            }
        }

        return blocked;
    }

    @Override
    public boolean makeMove(ChessPiece piece, int toRow, int toColumn) {
        if (piece.isLegalMove(toRow, toColumn)) {
            System.out.println("prev: " + piece.getRow() + " " + piece.getColumn() + " " + piece.getLabel());

            // the piece that is temporarily removed from the table
            ChessPiece save = piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());

//            // check after the piece has been removed and make move if legit
//            if (notFacingKing(piece)) {
//                piece.getChessBoard().addPiece(save, toRow, toColumn);
//            } else {
//                piece.getChessBoard().addPiece(save, piece.getRow(), piece.getColumn());
//            }

            piece.getChessBoard().addPiece(save, toRow, toColumn);


            System.out.println("curr: " + piece.getRow() + " " + piece.getColumn() + " " + piece.getLabel());

            setCurrentSide(currentSide == Side.NORTH? Side.SOUTH : Side.NORTH);

            System.out.println("next player: " + currentSide);
            System.out.println("-------------------");

            return true;
        }
        return false;
    }

    @Override
    public Side getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public int getNumRows() {
        return 10;
    }

    @Override
    public int getNumColumns() {
        return 9;
    }

    // update the next player
    public void setCurrentSide(Side currentSide) {
        this.currentSide = currentSide;
    }

    @Override
    public void startGame(ChessBoard board) {
        StartGame.initXianqi();
    }

    // return whether selection of a piece can be changed or not
    public boolean canChangeSelection(ChessPiece piece, int row, int column) {
        return true;
    }
}
