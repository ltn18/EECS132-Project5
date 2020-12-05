public class StartGame {

    // initialize the complete european board with south-north direction
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

    // initialize the complete xianqi board with south-north direction
    public static SwingChessBoard initXianqi() {
        ChessGame.Side first = ChessGame.Side.SOUTH;
        ChessGame.Side second = ChessGame.Side.NORTH;
        Xianqi xianqi = new Xianqi(first);
        SwingEuropeanChessDisplay swingEuropeanChessDisplay = new SwingEuropeanChessDisplay();
        SwingChessBoard swingChessBoard = new SwingChessBoard(10, 9, swingEuropeanChessDisplay, xianqi);

        // add SoldierPiece
        for (int i = 0; i < 9; ++i) {
            if (i % 2 == 0) {
                swingChessBoard.addPiece(new SoldierPiece(second, swingChessBoard), 3, i);
                swingChessBoard.addPiece(new SoldierPiece(first, swingChessBoard), 6, i);
            }
        }

        // add KingPiece
        swingChessBoard.addPiece(new XianqiKingPiece(second, swingChessBoard), 0, 4);
        swingChessBoard.addPiece(new XianqiKingPiece(first, swingChessBoard), 9, 4);

        // add GuardPiece
        swingChessBoard.addPiece(new GuardPiece(second, swingChessBoard), 0, 3);
        swingChessBoard.addPiece(new GuardPiece(second, swingChessBoard), 0, 5);
        swingChessBoard.addPiece(new GuardPiece(first, swingChessBoard), 9, 3);
        swingChessBoard.addPiece(new GuardPiece(first, swingChessBoard), 9, 5);

        // add ElephantPiece
        swingChessBoard.addPiece(new ElephantPiece(second, swingChessBoard), 0, 2);
        swingChessBoard.addPiece(new ElephantPiece(second, swingChessBoard), 0, 6);
        swingChessBoard.addPiece(new ElephantPiece(first, swingChessBoard), 9, 2);
        swingChessBoard.addPiece(new ElephantPiece(first, swingChessBoard), 9, 6);

        // add HorsePiece
        swingChessBoard.addPiece(new HorsePiece(second, swingChessBoard), 0, 1);
        swingChessBoard.addPiece(new HorsePiece(second, swingChessBoard), 0, 7);
        swingChessBoard.addPiece(new HorsePiece(first, swingChessBoard), 9, 1);
        swingChessBoard.addPiece(new HorsePiece(first, swingChessBoard), 9, 7);

        // add RookPiece
        swingChessBoard.addPiece(new RookPiece(second, swingChessBoard), 0, 0);
        swingChessBoard.addPiece(new RookPiece(second, swingChessBoard), 0, 8);
        swingChessBoard.addPiece(new RookPiece(first, swingChessBoard), 9, 0);
        swingChessBoard.addPiece(new RookPiece(first, swingChessBoard), 9, 8);

        // add CannonPiece
        swingChessBoard.addPiece(new CannonPiece(second, swingChessBoard), 2, 1);
        swingChessBoard.addPiece(new CannonPiece(second, swingChessBoard), 2, 7);
        swingChessBoard.addPiece(new CannonPiece(first, swingChessBoard), 7, 1);
        swingChessBoard.addPiece(new CannonPiece(first, swingChessBoard), 7, 7);

        return swingChessBoard;
    }

}
