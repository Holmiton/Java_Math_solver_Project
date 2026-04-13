package com.example.nighttest_javafx_1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Board extends Pane {
    public Board() {
        Line line_1 = new Line();
        Line line_2 = new Line();
        line_1.setStartX(0);
        line_1.setStartY(0);
        line_1.setEndY(0);
        line_1.endXProperty().bind(
                widthProperty().divide(2)
        );
        line_2.setStartX(0);
        line_2.setStartY(0);
        line_2.setEndX(0);
        line_2.endXProperty().bind(
                heightProperty().divide(2)
        );

    }
}
