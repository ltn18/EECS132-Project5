public class SwingChessBoard extends ChessBoard {

    /**
     * Builds a board of the desired size, the display parameters, and the rules for the chess game.
     *
     * @param boardDisplay an object that determines how the squares on the chessboard should be drawn
     * @param gameRules    an object that determines when player selection is valid and to update the game with the result of a move
     */
    public SwingChessBoard(ChessBoardDisplay boardDisplay, ChessGame gameRules) {
        super(boardDisplay, gameRules);
    }
}
