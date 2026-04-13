package lll;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OKHanderClass implements 
EventHandler<ActionEvent> {
	int i = 0;
	@Override
	public void handle(ActionEvent e) {
		System.out.println("button 1 clicked: " + i);
		i++;
	}

}
