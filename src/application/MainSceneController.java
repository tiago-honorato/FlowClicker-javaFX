package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class MainSceneController implements Initializable{
	
	public boolean maxUpgradeFlow = false;
	
	public int score = 0;
	
	public int required = 3;
	
	public double upgradePoint = 0.1;
	
	public double value = 0;
	
	@FXML
	public Label scoreLabel;
	
	@FXML
	public Label costUpgradeLabel;
	
	@FXML
	public Label reqLabel;
	
	@FXML
	public ProgressBar progresso;
	
	@FXML
	public Button btnUpgrade;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//
		
	}
	
	public void botaoFlow() {
		
		System.out.println("botaoFlow");
		
		value = value + upgradePoint;
		
		if (value > 1) {
			
			value = 0;
			score++;
			
		}
		
		if (!maxUpgradeFlow) {
			
			progresso.setProgress(value);
			
		}
		
		
		scoreLabel.setText(Integer.toString(score));
		
		System.out.println("value: " + value);
		
		if (score > 9) {
			
			scoreLabel.setLayoutX(390);
			
			scoreLabel.setLayoutX(score > 9999 ? 360 : (score > 999 ? 370 : (score > 99 ? 380 : 390)));
		}
		
	}
	
	public void botaoUpgrade() {
		
		if (upgradePoint >= 1) {
			
			maxUpgradeFlow = true;
			
			costUpgradeLabel.setText("MAX");
			costUpgradeLabel.setLayoutX(705);
			btnUpgrade.setDisable(true);
			
			progresso.setProgress(-1);
			
			System.out.println("nope");
			
		}else {
			
			reqLabel.setText("");
			
			if (score >= required) {
				
				score = score - required;
				
				if (required >= 500) {
					
					required = (int) (required*1.1);
					
				}else {
					
					required = (int) (required*1.4);
					
				}
				
				
				upgradePoint = upgradePoint + 0.02;
				
				costUpgradeLabel.setText("Cost: " + Integer.toString(required));
				
			} else {
				
				reqLabel.setLayoutX(670);
				reqLabel.setText("need at least: " + Integer.toString(required));
				
			}
			
		}
		
	}
	
}
