package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Board board=new Board();
        board.drawBoard();
        VBox vBox=new VBox();
        vBox.getChildren().addAll(board.getGridPane(),board.getStackPane());
        primaryStage.setTitle("Graphing Calculator");
        Scene scene=new Scene(vBox, 800, 700);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("sample/style.css");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
