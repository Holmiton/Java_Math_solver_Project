package lll;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class XiaqiHandler 
		implements EventHandler<MouseEvent>{
	private Board board;
	
	public XiaqiHandler(Board board) {
		this.board = board;
	}
	
	@Override
	public void handle(MouseEvent e) {
		board.setStoneByMouse(e.getX(), e.getY());		
	}
	

}
