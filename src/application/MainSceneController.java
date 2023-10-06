package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class MainSceneController {
	
	public int score = 0;
	
	public double value = 0;
	
	@FXML
	public Label scoreLabel;
	
	@FXML
	public ProgressBar progresso;
	
	public void botaoFlow() {
		
		System.out.println("botaoFlow");
		
		value = value + 0.1;
		
		if (value > 1) {
			
			value = 0;
			score++;
			
		}
		
		progresso.setProgress(value);
		
		scoreLabel.setText(Integer.toString(score));
		
		System.out.println("value: " + value);
		
		
	}
	
}
