/**
 * @author: Lam Nguyen
 * a JUnit to test the xianqi game
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Project5XianqiTester {

    // shortcut for calling the north direction of player side
    private ChessGame.Side north = ChessGame.Side.SOUTH;

    // shortcut for calling the south direction of player side
    private ChessGame.Side south = ChessGame.Side.NORTH;

    // shortcut for calling the east direction of player side
    private ChessGame.Side east = ChessGame.Side.EAST;

    // shortcut for calling the west direction of player side
    private ChessGame.Side west = ChessGame.Side.WEST;

    // initialize the complete chess board south->north
    public SwingChessBoard init(String input) {
        if (input.toUpperCase().equals("CHESS")) {
            return GameMain.initEuropeanChess(south);
        }
        else if (input.toUpperCase().equals("XIANQI")) {
            return GameMain.initXianqi(south);
        }
        return null;
    }

    // initialize the complete chess board with chosen first player
    public SwingChessBoard init(String input, ChessGame.Side first) {
        return GameMain.init(input, first);
    }

    /** TEST SIDE */

    // compare the side of all the pawns on the table to the correct side
    @Test
    public void testSoldierSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        // compare the side of the soldier on the table to the correct side
        for (int i = 0; i < 9; ++i) {
            if (i % 2 == 0) {
                assertEquals(north, chessBoard.getPiece(3, i).getSide());
                assertEquals(south, chessBoard.getPiece(6, i).getSide());
            }
        }
    }

    // compare the side of all the horses on the table to the correct side
    @Test
    public void testHorseSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(north, chessBoard.getPiece(0, 1).getSide());
        assertEquals(north, chessBoard.getPiece(0, 7).getSide());
        assertEquals(south, chessBoard.getPiece(9, 1).getSide());
        assertEquals(south, chessBoard.getPiece(9, 7).getSide());
    }

    // compare the side of all the elephants on the table to the correct side
    @Test
    public void testElephantSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(north, chessBoard.getPiece(0, 2).getSide());
        assertEquals(north, chessBoard.getPiece(0, 6).getSide());
        assertEquals(south, chessBoard.getPiece(9, 2).getSide());
        assertEquals(south, chessBoard.getPiece(9, 6).getSide());
    }

    // compare the side of all the rooks on the table to the correct side
    @Test
    public void testRookSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(north, chessBoard.getPiece(0, 0).getSide());
        assertEquals(north, chessBoard.getPiece(0, 8).getSide());
        assertEquals(south, chessBoard.getPiece(9, 0).getSide());
        assertEquals(south, chessBoard.getPiece(9, 8).getSide());
    }

    // compare the side of all the cannons on the table to the correct side
    @Test
    public void testCannonSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(north, chessBoard.getPiece(2, 1).getSide());
        assertEquals(north, chessBoard.getPiece(2, 7).getSide());
        assertEquals(south, chessBoard.getPiece(7, 1).getSide());
        assertEquals(south, chessBoard.getPiece(7, 7).getSide());
    }

    // compare the side of all the guards on the table to the correct side
    @Test
    public void testGuardSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(north, chessBoard.getPiece(0, 3).getSide());
        assertEquals(north, chessBoard.getPiece(0, 5).getSide());
        assertEquals(south, chessBoard.getPiece(9, 3).getSide());
        assertEquals(south, chessBoard.getPiece(9, 5).getSide());
    }

    // compare the side of all the xianqi kings on the table to the correct side
    @Test
    public void testXianqiKingSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(north, chessBoard.getPiece(0, 4).getSide());
        assertEquals(south, chessBoard.getPiece(9, 4).getSide());
    }

    /** TEST LABEL */

    // compare the label of all the soldiers on the table to the correct label
    @Test
    public void testSoldierLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        // compare the side of the soldier on the table to the correct label
        for (int i = 0; i < 9; ++i) {
            if (i % 2 == 0) {
                assertEquals("S", chessBoard.getPiece(3, i).getLabel());
                assertEquals("S", chessBoard.getPiece(6, i).getLabel());
            }
        }
    }

    // compare the side of all the horses on the table to the correct label
    @Test
    public void testHorseLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals("H", chessBoard.getPiece(0, 1).getLabel());
        assertEquals("H", chessBoard.getPiece(0, 7).getLabel());
        assertEquals("H", chessBoard.getPiece(9, 1).getLabel());
        assertEquals("H", chessBoard.getPiece(9, 7).getLabel());
    }

    // compare the side of all the elephants on the table to the correct label
    @Test
    public void testElephantLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals("E", chessBoard.getPiece(0, 2).getLabel());
        assertEquals("E", chessBoard.getPiece(0, 6).getLabel());
        assertEquals("E", chessBoard.getPiece(9, 2).getLabel());
        assertEquals("E", chessBoard.getPiece(9, 6).getLabel());
    }

    // compare the label of all the rooks on the table to the correct label
    @Test
    public void testRookLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals("R", chessBoard.getPiece(0, 0).getLabel());
        assertEquals("R", chessBoard.getPiece(0, 8).getLabel());
        assertEquals("R", chessBoard.getPiece(9, 0).getLabel());
        assertEquals("R", chessBoard.getPiece(9, 8).getLabel());
    }

    // compare the side of all the cannons on the table to the correct label
    @Test
    public void testCannonLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals("C", chessBoard.getPiece(2, 1).getLabel());
        assertEquals("C", chessBoard.getPiece(2, 7).getLabel());
        assertEquals("C", chessBoard.getPiece(7, 1).getLabel());
        assertEquals("C", chessBoard.getPiece(7, 7).getLabel());
    }

    // compare the label of all the guards on the table to the correct label
    @Test
    public void testGuardLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals("G", chessBoard.getPiece(0, 3).getLabel());
        assertEquals("G", chessBoard.getPiece(0, 5).getLabel());
        assertEquals("G", chessBoard.getPiece(9, 3).getLabel());
        assertEquals("G", chessBoard.getPiece(9, 5).getLabel());
    }

    // compare the label of all the xianqi kings on the table to the correct label
    @Test
    public void testXianqiKingLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals("X", chessBoard.getPiece(0, 4).getLabel());
        assertEquals("X", chessBoard.getPiece(9, 4).getLabel());
    }

    /** TEST ICON */

    // compare the icons of all the pieces on the table to the correct icons
    @Test
    public void testNullIcon() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        // compare the icon of the piece on the table to the correct icon
        for (int i = 0; i < 9; ++i) {
            assertEquals(null, chessBoard.getPiece(0, i).getIcon());
            assertEquals(null, chessBoard.getPiece(9, i).getIcon());
            if (i % 2 == 0) {
                assertEquals(null, chessBoard.getPiece(3, i).getIcon());
                assertEquals(null, chessBoard.getPiece(6, i).getIcon());
            }
        }
    }

    /** TEST POSITION */

    // compare the positions of all the soldiers on the table to the correct positions
    @Test
    public void testSoldierPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        // compare the position of the soldier on the table to the correct position
        for (int i = 0; i < 9; ++i) {
            if (i % 2 == 0) {
                ChessPiece northPiece = chessBoard.getPiece(3, i);
                assertEquals(3, northPiece.getRow());
                assertEquals(i, northPiece.getColumn());

                ChessPiece southPiece = chessBoard.getPiece(6, i);
                assertEquals(6, southPiece.getRow());
                assertEquals(i, southPiece.getColumn());
            }
        }
    }

    // compare the side of all the horses on the table to the correct positions
    @Test
    public void testHorsePosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(0, chessBoard.getPiece(0, 1).getRow());
        assertEquals(1, chessBoard.getPiece(0, 1).getColumn());

        assertEquals(0, chessBoard.getPiece(0, 7).getRow());
        assertEquals(7, chessBoard.getPiece(0, 7).getColumn());

        assertEquals(9, chessBoard.getPiece(9, 1).getRow());
        assertEquals(1, chessBoard.getPiece(9, 1).getColumn());

        assertEquals(9, chessBoard.getPiece(9, 7).getRow());
        assertEquals(7, chessBoard.getPiece(9, 7).getColumn());
    }

    // compare the side of all the elephants on the table to the correct positions
    @Test
    public void testElephantPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(0, chessBoard.getPiece(0, 2).getRow());
        assertEquals(2, chessBoard.getPiece(0, 2).getColumn());

        assertEquals(0, chessBoard.getPiece(0, 6).getRow());
        assertEquals(6, chessBoard.getPiece(0, 6).getColumn());

        assertEquals(9, chessBoard.getPiece(9, 2).getRow());
        assertEquals(2, chessBoard.getPiece(9, 2).getColumn());

        assertEquals(9, chessBoard.getPiece(9, 6).getRow());
        assertEquals(6, chessBoard.getPiece(9, 6).getColumn());
    }

    // compare the label of all the rooks on the table to the correct position
    @Test
    public void testRookPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(0, chessBoard.getPiece(0, 0).getRow());
        assertEquals(0, chessBoard.getPiece(0, 0).getColumn());

        assertEquals(0, chessBoard.getPiece(0, 8).getRow());
        assertEquals(8, chessBoard.getPiece(0, 8).getColumn());

        assertEquals(9, chessBoard.getPiece(9, 0).getRow());
        assertEquals(0, chessBoard.getPiece(9, 0).getColumn());

        assertEquals(9, chessBoard.getPiece(9, 8).getRow());
        assertEquals(8, chessBoard.getPiece(9, 8).getColumn());
    }

    // compare the side of all the cannons on the table to the correct position
    @Test
    public void testCannonPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(2, chessBoard.getPiece(2, 1).getRow());
        assertEquals(1, chessBoard.getPiece(2, 1).getColumn());

        assertEquals(2, chessBoard.getPiece(2, 7).getRow());
        assertEquals(7, chessBoard.getPiece(2, 7).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 1).getRow());
        assertEquals(1, chessBoard.getPiece(7, 1).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 7).getRow());
        assertEquals(7, chessBoard.getPiece(7, 7).getColumn());
    }

    // compare the label of all the guards on the table to the correct position
    @Test
    public void testGuardPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals("G", chessBoard.getPiece(0, 3).getLabel());
        assertEquals("G", chessBoard.getPiece(0, 5).getLabel());
        assertEquals("G", chessBoard.getPiece(9, 3).getLabel());
        assertEquals("G", chessBoard.getPiece(9, 5).getLabel());
    }

    // compare the label of all the xianqi kings on the table to the correct position
    @Test
    public void testXianqiKingPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        assertEquals(0, chessBoard.getPiece(0, 4).getRow());
        assertEquals(4, chessBoard.getPiece(0, 4).getColumn());

        assertEquals(9, chessBoard.getPiece(9, 4).getRow());
        assertEquals(4, chessBoard.getPiece(9, 4).getColumn());
    }

    /** TEST CONSTRUCTOR */
    @Test
    public void testChessPieceConstructor() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        // test soldier constructor
        SoldierPiece soldier = new SoldierPiece(north, chessBoard);
        assertEquals(north, soldier.getSide());
        assertEquals("S", soldier.getLabel());

        // test horse constructor
        HorsePiece horse = new HorsePiece(north, chessBoard);
        assertEquals(north, horse.getSide());
        assertEquals("H", horse.getLabel());

        // test elephant constructor
        ElephantPiece elephant = new ElephantPiece(north, chessBoard);
        assertEquals(north, elephant.getSide());
        assertEquals("E", elephant.getLabel());

        // test rook constructor
        RookPiece rook = new RookPiece(south, chessBoard);
        assertEquals(south, rook.getSide());
        assertEquals("R", rook.getLabel());

        // test cannon constructor
        CannonPiece cannon = new CannonPiece(south, chessBoard);
        assertEquals(south, cannon.getSide());
        assertEquals("C", cannon.getLabel());

        // test guard constructor
        GuardPiece guard = new GuardPiece(west, chessBoard);
        assertEquals(west, guard.getSide());
        assertEquals("G", guard.getLabel());

        // test xianqi king constructor
        XianqiKingPiece king = new XianqiKingPiece(east, chessBoard);
        assertEquals(east, king.getSide());
        assertEquals("X", king.getLabel());
    }

    /** TEST BOARD */
    @Test
    public void testBoard() {
        // initialize the complete chess board
        ChessBoard chessBoard = init("XIANQI");

        // test north board
        SoldierPiece northBoard = new SoldierPiece(north, chessBoard);
        assertEquals(north, northBoard.getSide());

        // test south board
        SoldierPiece southBoard = new SoldierPiece(south, chessBoard);
        assertEquals(south, southBoard.getSide());

        // test east board
        SoldierPiece eastBoard = new SoldierPiece(east, chessBoard);
        assertEquals(east, eastBoard.getSide());

        // test west board
        SoldierPiece westBoard = new SoldierPiece(west, chessBoard);
        assertEquals(west, westBoard.getSide());
    }

    /** TEST FIRST PLAYER */
    @Test
    public void testFirstPlayer() {

        // test north being the first player
        ChessBoard northBoard = init("XIANQI", north);
        assertEquals(north, northBoard.getGameRules().getFirstPlayer());

        // test south being the first player
        ChessBoard southBoard = init("XIANQI", south);
        assertEquals(south, southBoard.getGameRules().getFirstPlayer());

        // test east being the first player
        ChessBoard eastBoard = init("XIANQI", east);
        assertEquals(east, eastBoard.getGameRules().getFirstPlayer());

        // test west being the first player
        ChessBoard westBoard = init("XIANQI", west);
        assertEquals(west, westBoard.getGameRules().getFirstPlayer());

    }

}
