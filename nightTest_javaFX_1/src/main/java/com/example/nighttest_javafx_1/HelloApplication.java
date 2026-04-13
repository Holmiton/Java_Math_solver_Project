//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.nighttest_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private static final int BOARD_SIZE = 15;
    private static final int TILE_SIZE = 30;
    private static final int BOARD_PADDING = 30;
    private static final int STONE_RADIUS = 13;
    private boolean isBlackTurn = true;
    private int moveCount = 0;
    private Circle[][] stones = new Circle[15][15];
    private Pane boardPane = new Pane();

    public HelloApplication() {
    }

    public void start(Stage primaryStage) {
        HBox root = new HBox();
        root.setStyle("-fx-background-color: #dcb35c;");

        boardPane.setStyle("-fx-background-color: #dcb35c;");
        this.drawBoard(boardPane);

        boardPane.setOnMouseClicked(this::handleMouseClick);

        VBox buttonPanel = new VBox(10);
        buttonPanel.setStyle("-fx-padding: 20;");

        Button saveButton = new Button("save");
        saveButton.setOnAction(e -> saveGameState());

        Scene scene = new Scene(root, (double)480.0F, (double)480.0F);
        primaryStage.setTitle("简单围棋程序");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawBoard(Pane root) {
        for(int i = 0; i < 15; ++i) {
            Line line = new Line((double)30.0F, (double)(30 + i * 30), (double)450.0F, (double)(30 + i * 30));
            line.setStroke(Color.BLACK);
            root.getChildren().add(line);
        }

        for(int i = 0; i < 15; ++i) {
            Line line = new Line((double)(30 + i * 30), (double)30.0F, (double)(30 + i * 30), (double)450.0F);
            line.setStroke(Color.BLACK);
            root.getChildren().add(line);
        }

    }

    private void handleMouseClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        int col = (int)Math.round((x - (double)30.0F) / (double)30.0F);
        int row = (int)Math.round((y - (double)30.0F) / (double)30.0F);
        if (col >= 0 && col < 15 && row >= 0 && row < 15) {
            double stoneX = (double)(30 + col * 30);
            double stoneY = (double)(30 + row * 30);
            if (this.stones[row][col] != null) {
                ((Pane)event.getSource()).getChildren().remove(this.stones[row][col]);
                this.stones[row][col] = null;
                ++this.moveCount;
                System.out.println(this.moveCount + ": 移除棋子 (" + (col + 1) + ", " + (row + 1) + ")");
            } else {
                Circle stone = new Circle(stoneX, stoneY, (double)13.0F);
                stone.setFill(this.isBlackTurn ? Color.BLACK : Color.WHITE);
                stone.setStroke(Color.BLACK);
                ((Pane)event.getSource()).getChildren().add(stone);
                this.stones[row][col] = stone;
                ++this.moveCount;
                int var10001 = this.moveCount;
                System.out.println(var10001 + ": 下" + (this.isBlackTurn ? "黑" : "白") + "棋 (" + (col + 1) + ", " + (row + 1) + ")");
                this.isBlackTurn = !this.isBlackTurn;
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
