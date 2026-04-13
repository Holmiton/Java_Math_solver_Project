package lll;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Btn1HandlerClass 
		implements EventHandler<MouseEvent>{
	int i = 0;
	@Override
	public void handle(MouseEvent e) {
		System.out.println("button 1 entered: " + i);
		i++;
		
	}

}
