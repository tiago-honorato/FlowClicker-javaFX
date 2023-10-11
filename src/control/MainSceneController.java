package control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

public class MainSceneController implements Initializable{
	
	public boolean maxUpgradeFlow = false;
	
	public int score = 999;
	public int clickerPoint = 0;
	public int scorePerClick = 1;
	public int requiredToUnlockUpgrade = 10;
	public int requiredToUpgradeFlow = 3;
	public int requiredToUpgradeScore = 10;
	public int requiredToUpgradeClicker = 20;
	
	public double progressBarPoint = 0.1;
	public double valueProgressBar = 0;
	
	@FXML
	public Label dissapearLabel;
	@FXML
	public Label scoreLabel;
	@FXML
	public Label ClickersCountLabel;
	@FXML
	public Label costUnlockUpgradesLabel;
	@FXML
	public Label costUpgradeFlowLabel;
	@FXML
	public Label costUpgradeScoreLabel;
	@FXML
	public Label costAddClickerLabel;
	
	public Label costAddFactoryLabel;
	@FXML
	public Label reqLabelFlowUpgrade;
	@FXML
	public Label reqLabelScoreUpgrade;
	@FXML
	public Label reqLabelClickerUpgrade;
	@FXML
	public Label statsLabel;
	
	@FXML
	public ProgressBar progressBar;
	
	@FXML
	public Button btnFlow;
	@FXML
	public Button btnUnlockUpgrade;
	@FXML
	public Button btnUpgradeFlow;
	@FXML
	public Button btnUpgradeScore;
	@FXML
	public Button btnAddClicker;
	@FXML
	public Button btnAddFactory;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		timer();
		
		//encerra o aplicativo quando fecha a janela
		Platform.runLater(() -> {
			
			btnUpgradeFlow.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					
					System.exit(0);
					
				}
			});
			
		});
		
	}
	
	int a = 0;
	public void actbotaoFlow() {
		
		valueProgressBar = valueProgressBar + progressBarPoint;
		
		dissapearLabel.setText("");
		
		String[] cores = {"BLUE", "RED", "GREEN", "PURPLE", "BLACK"};
		
		btnFlow.setTextFill(Color.web(cores[a]));
		scoreLabel.setTextFill(Color.web(cores[a]));
		ClickersCountLabel.setTextFill(Color.web(cores[a]));
		a++;
		if (a == 4) a = 0;
		
		if (valueProgressBar > 1) {
			
			valueProgressBar = 0;
			score = score + scorePerClick ;
			
		}
		
		if (!maxUpgradeFlow) {
			
			progressBar.setProgress(valueProgressBar);
			
		}
		
		
		scoreLabel.setText(Integer.toString(score));
		
		if (score > 9) {
			
			scoreLabel.setLayoutX(390);
			
			scoreLabel.setLayoutX(score > 9999 ? 365 : (score > 999 ? 375 : (score > 99 ? 385 : 390)));
		}
		
	}
	
	int unlockLevel = 0;
	public void actBtnUnlockUpgrade() {
		
		if (score >= requiredToUnlockUpgrade) {
			
			score = score - requiredToUnlockUpgrade;
			
			requiredToUnlockUpgrade = requiredToUnlockUpgrade*3;
			
			costUnlockUpgradesLabel.setText("Cost: " + Integer.toString(requiredToUnlockUpgrade));
			
			switch (unlockLevel) {
			case 0:
				btnUpgradeFlow.setDisable(false);
				break;
			case 1:
				btnUpgradeScore.setDisable(false);
				break;
			case 2:
				btnAddClicker.setDisable(false);
				break;
			case 3:
				btnAddFactory.setDisable(false);
				break;
			}
			
			unlockLevel++;
			
			//verifica se já desbloqueou tudo
			if (unlockLevel >= 4) {
				
				btnUnlockUpgrade.setDisable(true);
				costUnlockUpgradesLabel.setText("");
			}

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
			
			progressBar.setProgress(-1);
						
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
			
			reqLabelScoreUpgrade.setText("");
			
			costUpgradeScoreLabel.setText("MAX");
			costUpgradeScoreLabel.setLayoutX(705);
			
			btnUpgradeScore.setDisable(true);
			
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
	
	public void timer() {
		
		Timer myTimer = new Timer();
		
		myTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				Platform .runLater(() -> {
					
					scoreLabel.setText(Integer.toString(score));
					
					statsLabel.setText("stats: ");
					
					score = score + clickerPoint;
					
					timer();
					
					//run();
					
				});
				
			}
		}, 1000);
		
	}
	
	public void actBtnAddClicker() {
		
		//verifica se o upgrade tá no máximo
		if (clickerPoint >= 50) {
			
			reqLabelClickerUpgrade.setText("");
			
			costAddClickerLabel.setText("MAX");
			costAddClickerLabel.setLayoutX(705);
			
			btnAddClicker.setDisable(true);
			
		} else {
			
			reqLabelClickerUpgrade.setTextFill(javafx.scene.paint.Color.GREEN);
			reqLabelClickerUpgrade.setLayoutX(685);
			reqLabelClickerUpgrade.setText("upgraded!");
				
			//verifica se tem pontos suficientes
			if (score >= requiredToUpgradeClicker) {
				
				//desconta os pontos a serem pagos
				score = score - requiredToUpgradeClicker;
				
				if (requiredToUpgradeClicker >= 50) {
					
					requiredToUpgradeClicker = (int) (requiredToUpgradeClicker*1.1);
					
				}else {
					
					requiredToUpgradeClicker = (int) (requiredToUpgradeClicker*1.4);
					
				}
				//upgrade
				clickerPoint++;
				
				
				//atualiza os labels
				costAddClickerLabel.setText("Cost: " + Integer.toString(requiredToUpgradeClicker));
				ClickersCountLabel.setText("Clickers: " + Integer.toString(clickerPoint));
				
			} else {
				
				reqLabelClickerUpgrade.setLayoutX(670);
				reqLabelClickerUpgrade.setTextFill(javafx.scene.paint.Color.RED);
				reqLabelClickerUpgrade.setText("need at least: " + Integer.toString(requiredToUpgradeClicker));
				
			}
			
		}
		
	}
	
	public void actBtnAddFactory() {
		
		System.out.println("actBtnAddFactory()");
		
	}
	
}
