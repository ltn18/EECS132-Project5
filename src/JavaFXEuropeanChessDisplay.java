/**
 * @author: Lam Nguyen
 * a class representing javafx european chess display
 */
import javafx.scene.control.Button;

import java.awt.*;

public class JavaFXEuropeanChessDisplay implements JavaFXChessBoardDisplay {

    /** The primary color of the checkerboard */
    public static Color redColor   = Color.red;

    /** The secondary color of the checkerboard */
    public static Color blackColor = Color.black;

    /* The color of the SOUTH player */
    public static Color southPlayerColor = Color.yellow;

    /* The color of the NORTH player */
    public static Color northPlayerColor = Color.green;

    /* The color of the EAST player */
    public static Color eastPlayerColor = Color.white;

    /* The color of the WEST player */
    public static Color westPlayerColor = Color.gray;

    /** The color used to highlight a square */
    public static Color highlightColor = Color.blue;

    /**
     * Display a square that has no piece on it.  Will produce a red/black checkerboard.
     * @param button the button that is used for the chessboard square
     * @param row    the row of this square on the board
     * @param column the column of this square on the board
     */
    public void displayEmptySquare(Button button, int row, int column) {
        String color = (row + column) % 2 == 0 ? "#000000" : "#ff0000";
        String style = "-fx-background-color: " + color + ";";
        button.setStyle(style);
        button.setText(null);
        button.setGraphic(null);
    }

    /**
     * Display a square that has a piece on it.
     * @param button the button that is used for the chessboard square
     * @param row    the row of this square on the board
     * @param column the column of this square on the board
     * @param piece  the piece that is on this square
     */
    public void displayFilledSquare(Button button, int row, int column, ChessPiece piece) {
        Color pieceColor;

        switch (piece.getSide()) {
            case NORTH:   pieceColor = northPlayerColor;
                break;
            case SOUTH:   pieceColor = southPlayerColor;
                break;
            case EAST:    pieceColor = eastPlayerColor;
                break;
            default:      pieceColor = westPlayerColor;
        }

        String style = "-fx-background-color: " + pieceColor + ";";
        button.setStyle(style);
        button.setText(piece.getLabel());
        button.setGraphic(null);
    }

    /**
     * Highlight a square of the board.
     * @param highlight  do we want the highlight on (true) or off (false)?
     * @param button     the button that is used for the chessboard square
     * @param row        the row of this square on the board
     * @param column     the column of this square on the board
     * @param piece      the piece (if any) that is on this square
     */
    public void highlightSquare(boolean highlight, Button button, int row, int column, ChessPiece piece) {
        if (highlight) {
            String style = "-fx-background-color: " + highlightColor + ";";
            button.setStyle(style);
        }
        else if (piece == null)
            displayEmptySquare(button, row, column);
        else
            displayFilledSquare(button, row, column, piece);
    }
}