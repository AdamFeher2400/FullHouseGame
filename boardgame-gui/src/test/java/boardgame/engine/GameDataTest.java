package boardgame.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GameDataTest {

    @Test
    public void testLoad() {
        assertNotNull(GameData.load(1));
        assertNotNull(GameData.load(2));
        assertNotNull(GameData.load(3));
    }

    @Test
    public void testLoad1stData() {
        CellState[][] board = GameData.load1stData();
        assertNotNull(board);
        assertEquals(CellState.BLOCK_CELL, board[4][0]);
        assertEquals(CellState.BLOCK_CELL, board[2][1]);
        assertEquals(CellState.BLOCK_CELL, board[1][3]);
        assertEquals(CellState.BLOCK_CELL, board[3][4]);
    }

    @Test
    public void testLoad2ndData() {
        CellState[][] board = GameData.load2ndData();
        assertNotNull(board);
        assertEquals(CellState.BLOCK_CELL, board[0][3]);
        assertEquals(CellState.BLOCK_CELL, board[1][3]);
        assertEquals(CellState.BLOCK_CELL, board[3][4]);
        assertEquals(CellState.BLOCK_CELL, board[4][3]);
        assertEquals(CellState.BLOCK_CELL, board[5][5]);
    }

    @Test
    public void testLoad3rdData() {
        CellState[][] board = GameData.load3rdData();
        assertNotNull(board);
        assertEquals(CellState.BLOCK_CELL, board[2][2]);
        assertEquals(CellState.BLOCK_CELL, board[2][1]);
        assertEquals(CellState.BLOCK_CELL, board[3][5]);
        assertEquals(CellState.BLOCK_CELL, board[3][6]);
        assertEquals(CellState.BLOCK_CELL, board[4][5]);
        assertEquals(CellState.BLOCK_CELL, board[4][6]);
    }

    @Test
    public void testZeroFill() {
        CellState[][] board = new CellState[10][10];
        GameData.zerofill(board);
        for(int i = 0; i < board.length; i ++) {
            for(int j = 0; j < board[i].length; j ++) {
                assertEquals(CellState.EMPTY_CELL, board[i][j]);
            }
        }
    }
}
