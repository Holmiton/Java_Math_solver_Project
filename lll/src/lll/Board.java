package lll;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {
	private int lattice_width;
	private int edge_width;
	private static final int lattice_width_min = 11; 
	private static final int board_size = 15;
	private Circle[][] stones;
	public static final int WHITE = 1;
	public static final int BLACK = 0;
	public static final int EMPTY = 2;
	private int cur_color = WHITE; 
	private Rectangle cur_stone_hint;
	
	public Board(int scene_width, int scene_height) {
		draw(scene_width, scene_height);
		stones = new Circle[board_size][board_size];
    	Rectangle cur_stone_hint = new Rectangle();
    	cur_stone_hint.setWidth(lattice_width);
    	cur_stone_hint.setHeight(lattice_width);
    	cur_stone_hint.setStroke(Color.BLACK);
    	cur_stone_hint.setVisible(false);
    	cur_stone_hint.setFill(Color.WHITE);
    	this.getChildren().add(cur_stone_hint);
    	XiaqiHandler handler 
    		= new XiaqiHandler(this);
    	this.setOnMouseClicked(handler);
    	
    	this.setOnMouseMoved(e->{
    		double ex = e.getX();
    		double ey = e.getY();
    		//System.out.printf("%.2f, %.2f\n", ex, ey);
    		int x = (int)Math.round((ex - edge_width) / lattice_width);
    		int y = (int)Math.round((ey - edge_width) / lattice_width);
    		if(!(0 <= x && x < board_size && 
    				0 <= y && y < board_size)) {
    			cur_stone_hint.setVisible(false);
    			return;
    		}
    		cur_stone_hint.setX(edge_width +  (x - 0.5) * lattice_width);
    		cur_stone_hint.setY(edge_width +  (y - 0.5) * lattice_width);
    		cur_stone_hint.setVisible(true);
    	});
	}
	
	private void draw(int scene_width, int scene_height) {
		
		
		double w = scene_width;
		double h = scene_height;
		lattice_width = (int)(Math.min(w, h) / (board_size + 3));
		if (lattice_width % 2 == 0)
			lattice_width -= 1;
		lattice_width = Math.max(lattice_width, 
				lattice_width_min);
		edge_width = lattice_width; 
		int size = edge_width * 2 + board_size * lattice_width;
		this.setWidth(size);
		this.setHeight(size);
		for(int i = 0; i < board_size; i++) {
			Line line = new Line(edge_width,
					edge_width + lattice_width * i,
					edge_width + lattice_width * (board_size - 1),
					edge_width + lattice_width * i
					);
			getChildren().add(line);
			line = new Line(
					edge_width + lattice_width * i,
					edge_width,
					edge_width + lattice_width * i,
					edge_width + lattice_width * (board_size - 1)
					);
			getChildren().add(line);
		}
		Line line = new Line(1,1,
				edge_width + (lattice_width) * board_size,
				1);
		getChildren().add(line);
		line = new Line(1,1,1,
				edge_width + (lattice_width) * board_size
				);
		line = new Line(1,1,1,
				edge_width + (lattice_width) * board_size
				);
		getChildren().add(line);
	}
	
	public void setStoneByMouse(double ex, double ey) {
		int x = (int)Math.round((ex - edge_width) / lattice_width);
		int y = (int)Math.round((ey - edge_width) / lattice_width);
		if(!(0 <= x && x < board_size && 
				0 <= y && y < board_size))
			return;
		//System.out.printf("%d %d", x, y);
		if(stones[y][x] == null) {
			Circle c = new Circle(lattice_width / 2);
			if (cur_color == BLACK)
				c.setFill(Color.BLACK);
			else {
				c.setFill(Color.WHITE);
				c.setStroke(Color.BLACK);
			}
			c.setCenterX(edge_width + x * lattice_width);
			c.setCenterY(edge_width + y * lattice_width);
			this.getChildren().add(c);
			cur_color = 1 - cur_color;
			stones[y][x] = c;
		} else {
			
		}
	}
	
}
