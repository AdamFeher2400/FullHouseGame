package boardgame.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameEngineTest {
    GameEngine engine = new GameEngine();

    @Test
    void testInit() {
        assertEquals(true, engine.init(0, true));
        assertEquals(true, engine.init(1, true));
        assertEquals(true, engine.init(2, false));
        assertEquals(true, engine.init(3, true));
        assertEquals(true, engine.init(4, false));
    }

    @Test
    void testHit() {
        engine.init(0, true);
        assertEquals(true, engine.hit(1, 1));
        assertEquals(true, engine.hit(1, 2));
        assertEquals(false, engine.hit(2, 3));
    }

    @Test
    void testGetState() {
        engine.init(0, true);
        assertEquals(true, engine.hit(1, 1));
        assertEquals(true, engine.hit(1, 2));
        assertEquals(true, engine.hit(2, 2));
        assertEquals(GameState.RUNNING, engine.getState());
        assertEquals(true, engine.hit(4, 4));
        assertEquals(GameState.LOSE, engine.getState());

        engine.init(0, false);
        assertEquals(true, engine.hit(1, 1));
        assertEquals(true, engine.hit(1, 2));
        assertEquals(true, engine.hit(2, 2));
        assertEquals(true, engine.hit(4, 1));
        assertEquals(GameState.RUNNING, engine.getState());
        assertEquals(true, engine.hit(3, 1));
        assertEquals(true, engine.hit(3, 0));
        assertEquals(true, engine.hit(0, 0));
        assertEquals(GameState.RUNNING, engine.getState());
        assertEquals(true, engine.hit(0, 4));
        assertEquals(true, engine.hit(2, 4));
        assertEquals(true, engine.hit(2, 3));
        assertEquals(GameState.RUNNING, engine.getState());
        assertEquals(true, engine.hit(4, 3));
        assertEquals(true, engine.hit(4, 4));
        assertEquals(GameState.WIN, engine.getState());

    }

    @Test
    void testGetSize() {
        engine.init(1, true);
        assertEquals(5, engine.getSize());

        engine.init(2, true);
        assertEquals(6, engine.getSize());

        engine.init(3, true);
        assertEquals(7, engine.getSize());
    }

    @Test
    void testGetLevel() {
        engine.init(1, true);
        assertEquals(1, engine.getLevel());

        engine.init(2, true);
        assertEquals(2, engine.getLevel());

        engine.init(3, true);
        assertEquals(3, engine.getLevel());
    }

    @Test
    void getCellState() {
        engine.init(1, true);
        assertEquals(CellState.EMPTY_CELL, engine.getCellState(1, 1));
        engine.hit(1, 1);
        assertEquals(CellState.BLOCK_CELL, engine.getCellState(1, 3));
        engine.hit(1, 2);
        assertEquals(CellState.CURRENT_CELL, engine.getCellState(1, 2));
        assertEquals(CellState.RIGHT_CELL, engine.getCellState(1, 1));
    }
}
