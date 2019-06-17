package boardgame.gui;

import boardgame.engine.GameState;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class Controller implements EventHandler<MouseEvent> {

    GameGUI mainGUI;
    int mode;

    public Controller(GameGUI gui, int type) {
        mainGUI = gui; mode = type;
    }

    @Override
    public void handle(MouseEvent event) {
        switch (mode) {
            case 0: // case you when click on game panel
                int x = (int) event.getY() / 48;
                int y = (int) event.getX() / 48; // get row and column index of clicked position
                if (mainGUI.engine.hit(x, y)) { // can move to clicked position
                    mainGUI.updateGridPane(); // update game panel
                    GameState now = mainGUI.engine.getState(); // get game state
                    if (now == GameState.WIN) { // if you win in game
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Succeed");
                        alert.setHeaderText(null);
                        alert.setContentText("Super, you solved this game in " + mainGUI.engine.getPlayTime() + " seconds!\nTry the next puzzle please.");
                        alert.showAndWait();
                        mainGUI.engine.init(mainGUI.engine.getLevel() + 1, true); // move to next level
                        switch (mainGUI.engine.getLevel()) {
                            case 1:
                                mainGUI.levelList.setValue("Easy");
                                break;
                            case 2:
                                mainGUI.levelList.setValue("Normal");
                                break;
                            case 3:
                                mainGUI.levelList.setValue("Insane");
                                break;
                        }
                        mainGUI.updateGridPane();
                    }
                    if (now == GameState.LOSE) { // i fyou lose in game
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Lose");
                        alert.setHeaderText(null);
                        alert.setContentText("Ooops! Try again?");
                        alert.showAndWait();
                        mainGUI.engine.init(mainGUI.engine.getLevel(), false); // restart
                        mainGUI.updateGridPane();
                    }
                }
                break;
            case 1: // case when you click restart button
                mainGUI.engine.init(mainGUI.engine.getLevel(), false);
                mainGUI.updateGridPane();
                break;
            case 2: // case when you click help button
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Help");
                alert.setHeaderText(null);
                alert.setContentText("This puzzle was invented in 2019 by Ankit (www.stetson.edu/~efriedma).\n" +
                        "First click on an empty square to mark your starting position.\n" +
                        "After that click on a square to move the ball in horizontal or vertical direction.\n" +
                        "A game is solved, when no square on the grid remains empty.\n" +
                        "Good luck!");
                alert.showAndWait();
        }
    }
}
