package lll;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class Test extends Application{
	private int i = 0;
	private int scene_width = 500;
	private int scene_height = 400;
	
	
    @Override
    public void start(Stage ps){
    	
    	Pane root = new Pane();
    	HBox hbox = new HBox();
    	hbox.setPadding(new Insets(15, 15, 15, 15));
    	root.getChildren().add(hbox);
    	
    	OKHanderClass handler1 = new OKHanderClass();
    	Btn1HandlerClass handler2 
    			= new Btn1HandlerClass();
    	
    	Button btn1 = new Button("1111");
		
    	btn1.setOnAction(e->{
    				System.out.println(
    				"button 1 clicked: " + i);
    				i++;
    			});
    	
    	btn1.setOnMouseEntered(handler2);
    	
    	Board board = new Board(scene_width, scene_height);
    	hbox.getChildren().add(board);
    	
    	VBox vbox = new VBox();
    	hbox.getChildren().add(vbox);
    	vbox.getChildren().add(btn1);
    	vbox.getChildren().add(new Button("2222"));
    	vbox.getChildren().add(new Button("3333"));
    	vbox.getChildren().add(new Button("4444"));
    	vbox.getChildren().add(new Button("5555"));
    	vbox.setPadding(new Insets(15, 15, 15, 15));
    	vbox.setSpacing(20);
    	
        Scene scene = new Scene(root, 
        		scene_width, scene_height);
        ps.setScene(scene);
        ps.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}