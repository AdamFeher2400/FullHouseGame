package boardgame.gui;

import boardgame.engine.CellState;
import boardgame.engine.GameEngine;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

/**
 * GUI for the 2D Board Game.
 *
 * NOTE: Do NOT run this class directly in IntelliJ - run 'RunGame' instead.
 */
public class GameGUI extends Application {
    // TODO: move this to Controller class if you use FXML...
    public GameEngine engine = new GameEngine();
    private GridPane gridPane = new GridPane();
    private GridPane rootPane = new GridPane();
    private GridPane controlPane = new GridPane();
    ComboBox levelList = new ComboBox();


    @Override
    public void start(Stage primaryStage) {

        updateGridPane();


        levelList.getItems().addAll("Easy", "Normal", "Insane");
        levelList.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if(t1.equals("Easy"))
                    engine.init(1, true);
                if(t1.equals("Normal"))
                    engine.init(2, true);
                if(t1.equals("Insane"))
                    engine.init(3, true);
                updateGridPane();
            }
        });

        levelList.setValue("Easy");
        controlPane.add(levelList, 0, 0);

        Button restartBtn = new Button("Restart");
        restartBtn.setOnMouseClicked(new Controller(this, 1));
        controlPane.add(restartBtn, 1, 0);

        Button helpBtn = new Button("Help");
        helpBtn.setOnMouseClicked(new Controller(this, 2));
        controlPane.add(helpBtn, 2, 0);

        controlPane.setHgap(30);

        rootPane.add(gridPane, 0, 0);
        rootPane.add(controlPane, 0, 1);
        rootPane.setVgap(20);
        rootPane.setLayoutX(48);

        Scene scene = new Scene(rootPane, 48*7, 48*7+50, Color.rgb(244, 244, 244));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Board Game");
        primaryStage.show();
    }

    public void updateGridPane() { // update game panel with direction info
        gridPane.getChildren().clear(); /// clear game panel
        rootPane.setLayoutX(48 - 24 * (engine.getLevel() - 1));
        controlPane.setHgap(30 + 25 * (engine.getLevel() - 1));
        for(int i = 0; i < engine.getSize(); i ++) {
            for(int j = 0; j < engine.getSize(); j ++) {
                CellState state = engine.getCellState(i, j); // get state of specific cell
                try {
                    Image image = new Image(new FileInputStream("img/EMPTY_CELL.gif"));
                    switch (state) {
                        case BLOCK_CELL:
                            image = new Image(new FileInputStream("img/BLOCK_CELL.gif"));
                            break;
                        case CURRENT_CELL:
                            image = new Image(new FileInputStream("img/CURRENT_CELL.gif"));
                            break;
                        case LEFT_CELL:
                            image = new Image(new FileInputStream("img/LEFT_CELL.gif"));
                            break;
                        case UP_CELL:
                            image = new Image(new FileInputStream("img/UP_CELL.gif"));
                            break;
                        case RIGHT_CELL:
                            image = new Image(new FileInputStream("img/RIGHT_CELL.gif"));
                            break;
                        case DOWN_CELL:
                            image = new Image(new FileInputStream("img/DOWN_CELL.gif"));
                            break;
                    }
                    ImageView imageView = new ImageView(image);
                    gridPane.add(imageView, j, i); // place image with cell state
                } catch (Exception e) { }
            }
        }

        gridPane.setOnMouseClicked(new Controller(this, 0)); // event handler of game panel
    }

    /** In IntelliJ, do NOT run this method.  Run 'RunGame.main()' instead. */
    public static void main(String[] args) {
        launch(args);
    }
}
