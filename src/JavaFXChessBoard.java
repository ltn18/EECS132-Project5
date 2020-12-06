/**
 * @author: Lam Nguyen
 * a class representing javafx chess board
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Scanner;

public class JavaFXChessBoard extends Application implements ChessBoard {

    // the game board
    private Scene board;

    // the squares of the board
    private Button[][] squares;

    private int squaresRow;

    private int squaresCol;

    // stores the pieces
    private ChessPiece[][] pieces;

    // global rules for this particular game
    private ChessGame gameRules;

    // rules for how to draw the chess board
    private JavaFXChessBoardDisplay boardDisplay;

    // magnify the board for easier gameplay - Lam Nguyen add on
    private final int MAGNIFY_RATE = 2;

    /**
     * Returns the rules of the game.
     * @return the rules of the game
     */
    public ChessGame getGameRules() {
        return gameRules;
    }

    /**
     * Changes the rules of the game
     * @param newRules the new rules for the game
     */
    public void setGameRules(ChessGame newRules) {
        this.gameRules = newRules;
    }

    /**
     * Returns the number of rows in the board.
     * @return the number of rows
     */
    public final int numRows() {
        return squares.length;
    }

    /**
     * Returns the number of columns in the board.
     * @return the number of columns
     */
    public final int numColumns() {
        return squares[0].length;
    }

    /**
     *  Adds a piece to the board at the desired location.  Any piece currently
     *  at that location is lost.
     *  @param piece   the piece to add
     *  @param row     the row for the piece
     *  @param col     the column for the piece
     */
    public void addPiece(final ChessPiece piece, final int row, final int col) {
        // set the piece on the board, tell the piece where it is, and then use the display rules to display the square
        // run the display code on the event dispatch thread

        pieces[row][col] = piece;
        piece.setLocation(row, col);

        Runnable addPiece = new Runnable() {
            public void run() {
                boardDisplay.displayFilledSquare(squares[row][col], row, col, piece);
            }
        };

        // run the code to change the display on the event dispatch to avoid drawing errors
        if (SwingUtilities.isEventDispatchThread())
            addPiece.run();
        else {
            try {
                SwingUtilities.invokeAndWait(addPiece);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  Removes a piece from the board
     *  @param row  the row of the piece
     *  @param col  the column of the piece
     *  @return  the piece removed of null if there was no piece at that square
     */
    public ChessPiece removePiece(final int row, final int col) {
        // remove the piece from the board, use the display rules to show an empty square,
        // and run the display code on the event dispatch thread
        ChessPiece save = pieces[row][col];
        pieces[row][col] = null;

        Runnable removePiece = new Runnable() {
            public void run() {
                boardDisplay.displayEmptySquare(squares[row][col], row, col);
            }
        };
        if (SwingUtilities.isEventDispatchThread())
            removePiece.run();
        else {
            try {
                SwingUtilities.invokeAndWait(removePiece);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return save;
    }

    /**
     *  Returns true if there is a piece at a specific location of the board.
     *  @param row   the row to examine
     *  @param col   the column to examine
     *  @return   true if there is a piece a this row and column and false
     *            if the square is empty
     */
    public boolean hasPiece(int row, int col) {
        return (pieces[row][col] != null);
    }

    /**
     *  Returns the chess piece at a specific location on the board.
     *  @param row   the row for the piece
     *  @param col   the column for the piece
     *  @return      the piece at the row and column or null if there is no piece there.
     */
    public ChessPiece getPiece(int row, int col) {
        return pieces[row][col];
    }

    public void setBoardDisplay(JavaFXChessBoardDisplay boardDisplay) {
        this.boardDisplay = boardDisplay;
    }

    /** The code the responds when the user clicks on the game board */
    private class ChessAction implements EventHandler<ActionEvent> {
        private boolean firstPick = true;  // if true, we a selecting a piece
        private int pieceRow;              // remember row of selected piece
        private int pieceCol;              // remember column of selected piece

        /**
         * What we do when the user chooses the piece to move.
         * @param row the row of the chosen piece
         * @param col the column of the chosen piece
         */
        private void processFirstSelection(int row, int col) {
            if ((pieces[row][col] != null) &&
                    (getGameRules() == null || getGameRules().legalPieceToPlay(pieces[row][col], row, col))) {
                /*
                 * if this is the first pick and a square with a piece was picked,
                 * remember the piece's location and highlight the square.
                 */
                pieceRow = row;
                pieceCol = col;
                boardDisplay.highlightSquare(true, squares[row][col], row, col, pieces[row][col]);
                firstPick = false;
            }
        }

        /**
         * What we do when the user chooses the square to move the piece to.
         * @param row the row the piece will move to
         * @param col the column the piece will move to
         */
        private void processSecondSelection(int row, int col) {
            if (row == pieceRow && col == pieceCol)
                return;

            boolean moveMade = getGameRules().makeMove(pieces[pieceRow][pieceCol], row, col);

            // if the move was made or if it was not made and the user select a new piece, then reset to choose a new move
            if (moveMade || getGameRules().canChangeSelection(pieces[pieceRow][pieceCol], pieceRow, pieceCol)) {
                boardDisplay.highlightSquare(false, squares[pieceRow][pieceCol], pieceRow, pieceCol, pieces[pieceRow][pieceCol]);
                firstPick = true;
            }
        }

        /**
         *  Handle a button click.  The method alternates between selecting a piece
         *  and selecting any square.  After both are selected, the piece's
         *  legalMove is called, and if the move is legal, the piece is moved.
         *  @param e   the event that triggered the method
         */
        @Override
        public void handle(ActionEvent e) {
            Button b = (Button) e.getSource();
            int col = -1;
            int row = -1;

            // first find which button (board square) was clicked.
            for (int i = 0; i < squares.length; i++) {
                for (int j = 0; j < squares[i].length; j++) {
                    if (squares[i][j].equals(b)) {
                        row = i;
                        col = j;
                    }
                }
            }

            if (firstPick) {
                processFirstSelection(row, col);
            }
            else {
                processSecondSelection(row, col);
            }
        }
    }

    /**
     * Returns true if a particular square is threatened by an opposing piece.
     * @param row     the row of the square
     * @param column  the column of the square
     * @param piece   a piece of the game
     * @return  true if the square can be attacked by a piece of an opposing side as the parameter piece
     */
    public boolean squareThreatened(int row, int column, ChessPiece piece) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (hasPiece(i,j) && getPiece(i, j).getSide() != piece.getSide() &&
                        getPiece(i, j).isLegalMove(row, column))
                    return true;
            }
        }
        return false;
    }

    private Button[][] init(int row, int col, String input) {
        Button[][] board = new Button[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = new Button();
                board[i][j].setOnAction(new ChessAction());
                board[i][j].setPrefSize(60, 60);
            }
        }
        if (input.toUpperCase().equals("CHESS")) {

        }
        else if (input.toUpperCase().equals("XIANQI")) {

        }
        return board;
    }

    /**
     * Initialize the game
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        if (input.toUpperCase().equals("CHESS")) {
            squaresRow = 8;
            squaresCol = 8;
        }
        else if (input.toUpperCase().equals("XIANQI")) {
            squaresRow = 10;
            squaresCol = 9;
        }

        squares = init(squaresRow, squaresCol, input);

        GridPane pane = new GridPane();
        pane.setPrefSize(squaresCol * 60,squaresRow * 60);
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                pane.add(squares[i][j], j+1, i+1);
            }
        }

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
