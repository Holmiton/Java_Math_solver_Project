package com.example.nighttest_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private static final int BOARD_SIZE = 15;
    private static final int TILE_SIZE = 30;
    private static final int BOARD_PADDING = 30;
    private static final int STONE_RADIUS = 13;
    private static final int BUTTON_LOC = BOARD_PADDING + BOARD_SIZE * TILE_SIZE;
    private boolean isBlackTurn = true;
    private int moveCount = 0;
    private Circle[][] stones = new Circle[15][15];
    private Pane boardPane;

    public HelloApplication() {
    }

    public void start(Stage primaryStage) {
        HBox root = new HBox();
        VBox paneOfButtons = new VBox();
        boardPane = new Pane();
        boardPane.setStyle("-fx-background-color: #dcb35c;");


        Button saveButton = new Button("保存游戏");
        Button loadButton = new Button("加载游戏");
        Button newGameItem = new Button("新游戏");
        paneOfButtons.getChildren().addAll(saveButton, loadButton, newGameItem);
        paneOfButtons.setSpacing(10.00);



        this.drawBoard(boardPane);
        boardPane.setOnMouseClicked(this::handleMouseClick);

        root.getChildren().addAll(boardPane, paneOfButtons);
        root.setSpacing(10.00);
        // 设置菜单项事件处理
        saveButton.setOnAction(event -> saveGame(primaryStage));
        loadButton.setOnAction(event -> loadGame(primaryStage));
        newGameItem.setOnAction(event -> newGame());

        Scene scene = new Scene(root, 480.0, 500.0);
        primaryStage.setTitle("简单围棋程序");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawBoard(Pane root) {
        for(int i = 0; i < 15; ++i) {
            Line line = new Line(30.0, (30 + i * 30), 450.0, (30 + i * 30));
            line.setStroke(Color.BLACK);
            root.getChildren().add(line);
        }

        for(int i = 0; i < 15; ++i) {
            Line line = new Line((30 + i * 30), 30.0, (30 + i * 30), 450.0);
            line.setStroke(Color.BLACK);
            root.getChildren().add(line);
        }
    }

    private void handleMouseClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        int col = (int)Math.round((x - 30.0) / 30.0);
        int row = (int)Math.round((y - 30.0) / 30.0);
        if (col >= 0 && col < 15 && row >= 0 && row < 15) {
            double stoneX = (30 + col * 30);
            double stoneY = (30 + row * 30);
            if (this.stones[row][col] != null) {
                boardPane.getChildren().remove(this.stones[row][col]);
                this.stones[row][col] = null;
                ++this.moveCount;
                System.out.println(this.moveCount + "_remove_(" + (col + 1) + ", " + (row + 1) + ")");
            } else {
                Circle stone = new Circle(stoneX, stoneY, 13.0);
                stone.setFill(this.isBlackTurn ? Color.BLACK : Color.WHITE);
                stone.setStroke(Color.BLACK);
                boardPane.getChildren().add(stone);
                this.stones[row][col] = stone;
                ++this.moveCount;
                int var10001 = this.moveCount;
                System.out.println(var10001 + "_" + (this.isBlackTurn ? "black" : "white") + "_(" + (col + 1) + ", " + (row + 1) + ")");
                this.isBlackTurn = !this.isBlackTurn;
            }
        }
    }

    private void saveGame(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存游戏");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("围棋游戏文件", "*.go"));
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                // 保存游戏状态
                List<StoneData> stoneDataList = new ArrayList<>();
                for (int i = 0; i < BOARD_SIZE; i++) {
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        if (stones[i][j] != null) {
                            boolean isBlack = stones[i][j].getFill().equals(Color.BLACK);
                            stoneDataList.add(new StoneData(i, j, isBlack));
                        }
                    }
                }

                GameData gameData = new GameData(
                        stoneDataList,
                        isBlackTurn,
                        moveCount
                );

                oos.writeObject(gameData);
                System.out.println("游戏已保存到: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("保存游戏失败: " + e.getMessage());
            }
        }
    }

    private void loadGame(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("加载游戏");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("围棋游戏文件", "*.go"));
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                // 清除当前棋盘
                newGame();

                // 读取游戏状态
                GameData gameData = (GameData) ois.readObject();

                // 恢复棋子
                for (StoneData stoneData : gameData.getStoneDataList()) {
                    int row = stoneData.getRow();
                    int col = stoneData.getCol();
                    double stoneX = (30 + col * 30);
                    double stoneY = (30 + row * 30);

                    Circle stone = new Circle(stoneX, stoneY, 13.0);
                    stone.setFill(stoneData.isBlack() ? Color.BLACK : Color.WHITE);
                    stone.setStroke(Color.BLACK);
                    boardPane.getChildren().add(stone);
                    stones[row][col] = stone;
                }

                // 恢复游戏状态
                isBlackTurn = gameData.isBlackTurn();
                moveCount = gameData.getMoveCount();

                System.out.println("游戏已从 " + file.getAbsolutePath() + " 加载");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("加载游戏失败: " + e.getMessage());
            }
        }
    }

    private void newGame() {
        // 清除所有棋子
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (stones[i][j] != null) {
                    boardPane.getChildren().remove(stones[i][j]);
                    stones[i][j] = null;
                }
            }
        }

        // 重置游戏状态
        isBlackTurn = true;
        moveCount = 0;

        System.out.println("新游戏已开始");
    }

    public static void main(String[] args) {
        launch(args);
    }

    // 用于序列化游戏数据的内部类
    private static class GameData implements Serializable {
        private static final long serialVersionUID = 1L;
        private final List<StoneData> stoneDataList;
        private final boolean isBlackTurn;
        private final int moveCount;

        public GameData(List<StoneData> stoneDataList, boolean isBlackTurn, int moveCount) {
            this.stoneDataList = stoneDataList;
            this.isBlackTurn = isBlackTurn;
            this.moveCount = moveCount;
        }

        public List<StoneData> getStoneDataList() {
            return stoneDataList;
        }

        public boolean isBlackTurn() {
            return isBlackTurn;
        }

        public int getMoveCount() {
            return moveCount;
        }
    }

    // 用于序列化棋子数据的内部类
    private static class StoneData implements Serializable {
        private static final long serialVersionUID = 1L;
        private final int row;
        private final int col;
        private final boolean isBlack;

        public StoneData(int row, int col, boolean isBlack) {
            this.row = row;
            this.col = col;
            this.isBlack = isBlack;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public boolean isBlack() {
            return isBlack;
        }
    }
}