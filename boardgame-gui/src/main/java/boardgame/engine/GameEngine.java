package boardgame.engine;

/**
 * The main engine of the game
 */
public class GameEngine {

    /**
     * An example board to store the current game state.
     *
     * Note: depending on your game, you might want to change this from 'int' to String or something?
     */
    public CellState[][] board;

    /**
     * Current point of game
     *
     * Note: when you are at beginning of game or not initialized, cx and cy are -1s.
     */
    public int cx = -1;
    public int cy = -1;

    /**
     * Start time of game
     *
     * Note: it denotes millisecond of start time
     */
    public long startTime = 0;

    /**
     * Creates a square game board.
     *
     */
    public GameEngine() {
        init(1, true);
    }

    /**
     * Initialize game board with level information
     *
     * @param level the level of the game
     */
    public boolean init(int level, boolean newLevel) {
        try {
            if(level < 1)   level = 1;
            if(level > 3)   level = 3;
            board = GameData.load(level);
            cx = -1;
            cy = -1;
            if(newLevel) {
                startTime = System.currentTimeMillis();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param x,y the position of user hits
     * @return if you move your cursor or not
     */
    public boolean hit(int x, int y) {
        if(cx == -1 && cy == -1) {
            if(x >= 0 && y >= 0 && x < getSize() && y < getSize() && board[x][y] == CellState.EMPTY_CELL) {
                cx = x;
                cy = y;
                board[cx][cy] = CellState.CURRENT_CELL;
                return true;
            }
            return false;
        }

        x -= cx;
        y -= cy;

        if(x != 0 && y != 0)
            return false;
        if(x == 0 && y == 0)
            return false;

        if(x == 0) {
            y /= Math.abs(y);
            int nx = cx, ny = cy + y;
            while(nx >= 0 && ny >= 0 && nx < getSize() && ny < getSize() && board[nx][ny] == CellState.EMPTY_CELL) {
                board[cx][cy] = y > 0 ? CellState.RIGHT_CELL : CellState.LEFT_CELL;
                cx = nx;
                cy = ny;
                board[cx][cy] = CellState.CURRENT_CELL;
                ny += y;
            }
        }

        if(y == 0) {
            x /= Math.abs(x);
            int nx = cx + x, ny = cy;
            while(nx >= 0 && ny >= 0 && nx < getSize() && ny < getSize() && board[nx][ny] == CellState.EMPTY_CELL) {
                board[cx][cy] = x > 0 ? CellState.DOWN_CELL : CellState.UP_CELL;
                cx = nx;
                cy = ny;
                board[cx][cy] = CellState.CURRENT_CELL;
                nx += x;
            }
        }
        return true;
    }


    /**
     * Check the state of game
     *
     * @return
     *      WIN : when the game board completely filled
     *      LOSE : user can't move anymore
     *      RUNNING : user can move cursor
     */
    public GameState getState() {
        int i , j;
        for(i = 0; i < getSize(); i ++) {
            for (j = 0; j < getSize(); j++) {
                if(board[i][j] == CellState.EMPTY_CELL)
                    break;
            }
            if(j < getSize())
                break;
        }
        if(i == getSize()) {
            return GameState.WIN;
        }
        if(cx == -1 && cy == -1) {
            return GameState.RUNNING;
        }
        int dx[] = {-1, 0, 0, 1}, dy[] = {0, -1, 1, 0};
        for(int k = 0; k < 4; k ++)
        {
            int nx = cx + dx[k], ny = cy + dy[k];
            if(nx >= 0 && ny >= 0 && nx < getSize() && ny < getSize() && board[nx][ny] == CellState.EMPTY_CELL) {
                return GameState.RUNNING;
            }
        }
        return GameState.LOSE;
    }



    /**
     * The size of the current game.
     *
     * @return this is both the width and the height.
     */
    public int getSize() {
        return board.length;
    }

    /**
     * The level of the current game.
     *
     * @return this is the current level.
     */
    public int getLevel() {
        return board.length - 4;
    }

    /**
     * The state of cell of game board
     *
     * @param x,y position which you wanna know the state
     *
     * @return state of the pointed cell
     */
    public CellState getCellState(int x, int y) {
        return board[x][y];
    }

    /**
     * Time elapsed while playing game
     *
     * @return play time of this level
     */
    public int getPlayTime() {
        return (int)(System.currentTimeMillis() - startTime) / 1000;
    }
}
