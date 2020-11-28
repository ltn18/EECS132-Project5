public class StartGame {

    // initialize the complete board with south-north direction
    public static SwingChessBoard initEuropeanChess() {
        ChessGame.Side first = ChessGame.Side.SOUTH;
        ChessGame.Side second = ChessGame.Side.NORTH;
        EuropeanChess europeanChess = new EuropeanChess(first);
        SwingEuropeanChessDisplay swingEuropeanChessDisplay = new SwingEuropeanChessDisplay();
        SwingChessBoard swingChessBoard = new SwingChessBoard(8, 8, swingEuropeanChessDisplay, europeanChess);

        // add PawnPiece
        for (int i = 0; i < 8; ++i) {
            swingChessBoard.addPiece(new PawnPiece(second, swingChessBoard), 1, i);
            swingChessBoard.addPiece(new PawnPiece(first, swingChessBoard), 6, i);
        }
        // add KingPiece
        swingChessBoard.addPiece(new KingPiece(second, swingChessBoard), 0, 4);
        swingChessBoard.addPiece(new KingPiece(first, swingChessBoard), 7, 4);

        // add QueenPiece
        swingChessBoard.addPiece(new QueenPiece(second, swingChessBoard), 0, 3);
        swingChessBoard.addPiece(new QueenPiece(first, swingChessBoard), 7, 3);

        // add BishopPiece
        swingChessBoard.addPiece(new BishopPiece(second, swingChessBoard), 0, 2);
        swingChessBoard.addPiece(new BishopPiece(second, swingChessBoard), 0, 5);
        swingChessBoard.addPiece(new BishopPiece(first, swingChessBoard), 7, 2);
        swingChessBoard.addPiece(new BishopPiece(first, swingChessBoard), 7, 5);

        // add KnightPiece
        swingChessBoard.addPiece(new KnightPiece(second, swingChessBoard), 0, 1);
        swingChessBoard.addPiece(new KnightPiece(second, swingChessBoard), 0, 6);
        swingChessBoard.addPiece(new KnightPiece(first, swingChessBoard), 7, 1);
        swingChessBoard.addPiece(new KnightPiece(first, swingChessBoard), 7, 6);

        // add RookPiece
        swingChessBoard.addPiece(new RookPiece(second, swingChessBoard), 0, 0);
        swingChessBoard.addPiece(new RookPiece(second, swingChessBoard), 0, 7);
        swingChessBoard.addPiece(new RookPiece(first, swingChessBoard), 7, 0);
        swingChessBoard.addPiece(new RookPiece(first, swingChessBoard), 7, 7);

        return swingChessBoard;
    }

}
