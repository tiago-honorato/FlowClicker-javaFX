package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class MainSceneController implements Initializable{
	
	public boolean maxUpgradeFlow, maxUpgradeScore = false;
	
	public int score = 90000;
	
	public int scorePerClick = 1;
	
	public int requiredToUpgradeFlow = 3;
	
	public int requiredToUpgradeScore = 10;
	
	public double progressBarPoint = 0.1;
	
	public double valueProgressBar = 0;
	
	@FXML
	public Label scoreLabel;
	
	@FXML
	public Label costUpgradeFlowLabel;
	
	@FXML
	public Label reqLabelFlowUpgrade;
	
	@FXML
	public Label reqLabelScoreUpgrade;
	
	@FXML
	public ProgressBar progresso;
	
	@FXML
	public Button btnUpgradeFlow;
	
	@FXML
	public Button btnUpgradeScore;
	
	@FXML
	public Label costUpgradeScoreLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//
		
	}
	
	public void botaoFlow() {
		
		System.out.println("botaoFlow");
		
		valueProgressBar = valueProgressBar + progressBarPoint;
		
		if (valueProgressBar > 1) {
			
			valueProgressBar = 0;
			score = score + scorePerClick ;
			
		}
		
		if (!maxUpgradeFlow) {
			
			progresso.setProgress(valueProgressBar);
			
		}
		
		
		scoreLabel.setText(Integer.toString(score));
		
		System.out.println("value: " + String.format("%.2f", valueProgressBar)+
				"\n"+
				" progressBar: " + String.format("%.2f", progressBarPoint));
		
		if (score > 9) {
			
			scoreLabel.setLayoutX(390);
			
			scoreLabel.setLayoutX(score > 9999 ? 360 : (score > 999 ? 370 : (score > 99 ? 380 : 390)));
		}
		
	}
	
	public void actBtnUpgradeFlow () {
		
		//verifica se o upgrade tá no máximo
		if (progressBarPoint >= 1) {
			
			maxUpgradeFlow = true;
			
			reqLabelFlowUpgrade.setText("");
			
			costUpgradeFlowLabel.setText("MAX");
			costUpgradeFlowLabel.setLayoutX(705);
			btnUpgradeFlow.setDisable(true);
			
			progresso.setProgress(-1);
			
			System.out.println("Upgrade Flow MAX");
			
		}else {
			
			reqLabelFlowUpgrade.setTextFill(javafx.scene.paint.Color.GREEN);
			reqLabelFlowUpgrade.setLayoutX(685);
			reqLabelFlowUpgrade.setText("upgraded!");
			
			//verifica se tem pontos suficientes
			if (score >= requiredToUpgradeFlow) {
				
				//desconta os pontos a serem pagos
				score = score - requiredToUpgradeFlow;
				
				if (requiredToUpgradeFlow >= 500) {
					
					requiredToUpgradeFlow = (int) (requiredToUpgradeFlow*1.1);
					
				}else {
					
					requiredToUpgradeFlow = (int) (requiredToUpgradeFlow*1.4);
					
				}
				
				//upgrade
				progressBarPoint = progressBarPoint + 0.02;
				
				//atualiza o label
				costUpgradeFlowLabel.setText("Cost: " + Integer.toString(requiredToUpgradeFlow));
				
			} else {
				
				reqLabelFlowUpgrade.setLayoutX(670);
				reqLabelFlowUpgrade.setTextFill(javafx.scene.paint.Color.RED);
				reqLabelFlowUpgrade.setText("need at least: " + Integer.toString(requiredToUpgradeFlow));
				
			}
			
		}
		
	}
	
	public void actBtnUpgradeScore() {
		
		//verifica se o upgrade tá no máximo
		if (scorePerClick >= 10) {
			
			maxUpgradeScore = true;
			
			reqLabelScoreUpgrade.setText("");
			
			costUpgradeScoreLabel.setText("MAX");
			costUpgradeScoreLabel.setLayoutX(705);
			
			btnUpgradeScore.setDisable(true);
			
			System.out.println("Upgrade Score MAX");
			
		} else {
			
			reqLabelScoreUpgrade.setTextFill(javafx.scene.paint.Color.GREEN);
			reqLabelScoreUpgrade.setLayoutX(685);
			reqLabelScoreUpgrade.setText("upgraded!");
			
			//verifica se tem pontos suficientes
			if (score >= requiredToUpgradeScore) {
				
				//desconta os pontos a serem pagos
				score = score - requiredToUpgradeScore;
				
				if (requiredToUpgradeScore >= 500) {
					
					requiredToUpgradeScore = (int) (requiredToUpgradeScore*1.1);
					
				}else {
					
					requiredToUpgradeScore = (int) (requiredToUpgradeScore*1.4);
					
				}
				//upgrade
				scorePerClick++;
				
				//atualiza o label
				costUpgradeScoreLabel.setText("Cost: " + Integer.toString(requiredToUpgradeScore));
				
			} else {
				
				reqLabelScoreUpgrade.setLayoutX(670);
				reqLabelScoreUpgrade.setTextFill(javafx.scene.paint.Color.RED);
				reqLabelScoreUpgrade.setText("need at least: " + Integer.toString(requiredToUpgradeScore));
				
			}
			
		}
		
	}
	
}
