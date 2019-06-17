package boardgame.engine;

public class GameData {

    public static CellState[][] load(int level) {
        switch(level) {
            case 1:
                return load1stData();
            case 2:
                return load2ndData();
            case 3:
                return load3rdData();
            default:
                return null;
        }
    }

    public static CellState[][] load1stData() {
        CellState[][] board = new CellState[5][5];
        zerofill(board);
        board[4][0] = CellState.BLOCK_CELL;
        board[2][1] = CellState.BLOCK_CELL;
        board[1][3] = CellState.BLOCK_CELL;
        board[3][4] = CellState.BLOCK_CELL;
        return board;
    }

    public static CellState[][] load2ndData() {
        CellState[][] board = new CellState[6][6];
        zerofill(board);
        board[0][3] = CellState.BLOCK_CELL;
        board[1][3] = CellState.BLOCK_CELL;
        board[3][4] = CellState.BLOCK_CELL;
        board[4][3] = CellState.BLOCK_CELL;
        board[5][5] = CellState.BLOCK_CELL;
        return board;
    }

    public static CellState[][] load3rdData() {
        CellState[][] board = new CellState[7][7];
        zerofill(board);
        board[2][2] = CellState.BLOCK_CELL;
        board[2][1] = CellState.BLOCK_CELL;
        board[3][5] = CellState.BLOCK_CELL;
        board[3][6] = CellState.BLOCK_CELL;
        board[4][5] = CellState.BLOCK_CELL;
        board[4][6] = CellState.BLOCK_CELL;
        return board;
    }

    public static void zerofill(CellState[][] board) {
        for(int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = CellState.EMPTY_CELL;
            }
        }
    }
}
