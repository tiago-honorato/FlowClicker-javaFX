package control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import application.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

public class MainSceneController implements Initializable{
	
	public boolean maxUpgradeFlow, factoryIsTrue, maxUpgradeFactory, maxUpgradeClicker, maxUpgradeScore = false;	
	public int score = 999999;
	public int clickerPoint = 0;
	public int factoryPointCounter = 0;
	public int factories = 0;
	public int scorePerClick = 1;
	public int requiredToUnlockUpgrade = 10;
	public int requiredToUpgradeFlow = 3;
	public int requiredToUpgradeScore = 10;
	public int requiredToUpgradeClicker = 20;
	public int requiredToUpgradeFactory = 50;
	
	public double progressBarPoint = 0.1;
	public double valueProgressBar = 0;
	
	@FXML
	public Label dissapearLabel;
	@FXML
	public Label scoreLabel;
	@FXML
	public Label reqLabelUnlockUpgrade;
	@FXML
	public Label clickersCountLabel;
	@FXML
	public Label factoriesCountLabel;
	@FXML
	public Label costUnlockUpgradesLabel;
	@FXML
	public Label costUpgradeFlowLabel;
	@FXML
	public Label costUpgradeScoreLabel;
	@FXML
	public Label costAddClickerLabel;
	@FXML
	public Label costAddFactoryLabel;
	@FXML
	public Label reqLabelFlowUpgrade;
	@FXML
	public Label reqLabelScoreUpgrade;
	@FXML
	public Label reqLabelClickerUpgrade;
	@FXML
	public Label reqLabelFactoryAdd;
	@FXML
	public Label statsLabel;
	@FXML
	public Label factoryTimerLabel;
	
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
	@FXML
	public Button btnFinish;

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
		
		String[] cores = {"BLUE", "RED", "GREEN", "PURPLE", "BLACK", "ORANGE"};
		String[] coresProgressBar = {"#0000FF", "#FF0000", "#008000", "#800080", "#000000", "#FFA500"};
		
		btnFlow.setTextFill(Color.web(cores[a]));
		scoreLabel.setTextFill(Color.web(cores[a]));
		clickersCountLabel.setTextFill(Color.web(cores[a]));
		factoriesCountLabel.setTextFill(Color.web(cores[a]));
		
		if (!maxUpgradeFlow) {
			
			progressBar.setStyle("-fx-accent:" + coresProgressBar[a] + ";");
			
		}
		
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
		
	}
	
	int unlockLevel = 0;
	public void actBtnUnlockUpgrade() {
		
		verifyMaxUpgrades();
		
		//verifica se tem pontos suficientes
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
				
				costUnlockUpgradesLabel.setText("");
				btnUnlockUpgrade.setDisable(true);
				reqLabelUnlockUpgrade.setText("");
				
			}

		}else {
			
			reqLabelUnlockUpgrade.setTextFill(javafx.scene.paint.Color.RED);
			reqLabelUnlockUpgrade.setText("need at least: " + Integer.toString(requiredToUnlockUpgrade));
			
		}
		
		verifyMaxUpgrades();
		
	}
	
	public void actBtnUpgradeFlow () {
		
			
		verifyMaxUpgrades();
		
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
				
			reqLabelFlowUpgrade.setLayoutX(660);
			reqLabelFlowUpgrade.setTextFill(javafx.scene.paint.Color.RED);
			reqLabelFlowUpgrade.setText("need at least: " + Integer.toString(requiredToUpgradeFlow));
				
		}
		
		verifyMaxUpgrades();
		
	}
	
	public void actBtnUpgradeScore() {
		
		verifyMaxUpgrades();
		
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
				
			reqLabelScoreUpgrade.setLayoutX(660);
			reqLabelScoreUpgrade.setTextFill(javafx.scene.paint.Color.RED);
			reqLabelScoreUpgrade.setText("need at least: " + Integer.toString(requiredToUpgradeScore));
				
		}
		
		verifyMaxUpgrades();
		
	}
	
	public void timer() {
		
		Timer myTimer = new Timer();
		
		Timer atualizar = new Timer();
		
		myTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				
				Platform .runLater(() -> {
					
					score = score + clickerPoint;
					
					if (factoryIsTrue) {
						
						factoryPointCounter++;
						
						factoryTimerLabel.setText("" + factoryPointCounter);
						
						if (factoryPointCounter >= 20) {
							
							score += 50*factories;
							
							factoryPointCounter = 0;
							
						}
						
					}
					
					if (maxUpgradeClicker && maxUpgradeScore && maxUpgradeFlow && maxUpgradeFactory && score >= 50000) {
						
						btnFinish.setDisable(false);
						
					}
					
				});				
				
			}
			
		}, 0, 1000);
		
		atualizar.scheduleAtFixedRate(new TimerTask() {
			int b = 0;
			@Override
			public void run() {
				
				String[] cores = {"BLUE", "RED", "GREEN", "PURPLE", "ORANGE"};

				
				Platform.runLater(() -> {
					
					dissapearLabel.setTextFill(Color.web(cores[b]));
					b++;
					if (b >= 4) {
						b = 0;
					}
					scoreLabel.setText(Integer.toString(score));
					
					statsLabel.setText("STATS: flow points per iteration: " + scorePerClick +
							"| " + "flow points per second: " + clickerPoint + "| " + "flow points per 20 seconds: " + (50*factories));
					
					if (score > 9) {
						
						scoreLabel.setLayoutX(390);
						
						scoreLabel.setLayoutX(score > 9999 ? 365 : (score > 999 ? 375 : (score > 99 ? 385 : 390)));
					}
				});
				
			}
		}, 0, 300);
		
	}
	
	public void actBtnAddClicker() {
		
		verifyMaxUpgrades();
		
		reqLabelClickerUpgrade.setTextFill(javafx.scene.paint.Color.GREEN);
		reqLabelClickerUpgrade.setLayoutX(685);
		reqLabelClickerUpgrade.setText("upgraded!");
				
		//verifica se tem pontos suficientes
		if (score >= requiredToUpgradeClicker) {
				
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
			clickersCountLabel.setText("Clickers: " + Integer.toString(clickerPoint));
				
		} else {
				
			reqLabelClickerUpgrade.setLayoutX(660);
			reqLabelClickerUpgrade.setTextFill(javafx.scene.paint.Color.RED);
			reqLabelClickerUpgrade.setText("need at least: " + Integer.toString(requiredToUpgradeClicker));
				
		}
		
		verifyMaxUpgrades();
		
	}
	
	public void actBtnAddFactory() {
			
		verifyMaxUpgrades();
		
		reqLabelFactoryAdd.setTextFill(javafx.scene.paint.Color.GREEN);
		reqLabelFactoryAdd.setLayoutX(685);
		reqLabelFactoryAdd.setText("upgraded!");
			
		//verifica se tem pontos suficientes
		if (score >= requiredToUpgradeFactory) {
				
			//desconta os pontos a serem pagos
			score = score - requiredToUpgradeFactory;
				
			factoryIsTrue = true;
				
			//upgrade
			factories++;
				
			requiredToUpgradeFactory = (int)(requiredToUpgradeFactory*2.5);
				
			//atualiza os labels
			factoriesCountLabel.setText("Factories: " + Integer.toString(factories));
			costAddFactoryLabel.setText("Cost: " + Integer.toString(requiredToUpgradeFactory));
		} else {
				
			reqLabelFactoryAdd.setLayoutX(660);
			reqLabelFactoryAdd.setTextFill(javafx.scene.paint.Color.RED);
			reqLabelFactoryAdd.setText("need at least: " + Integer.toString(requiredToUpgradeFactory));
				
		}
		
		verifyMaxUpgrades();
		
	}
	
	public void verifyMaxUpgrades() {
		
		//desconta os pontos a serem pagos
		if (factories >= 10) {
			maxUpgradeFactory = true;
			reqLabelFactoryAdd.setText("");
			costAddFactoryLabel.setText("MAX");
			costAddFactoryLabel.setLayoutX(705);
			btnAddFactory.setDisable(true);
		}
		if (progressBarPoint >= 1) {
			maxUpgradeFlow = true;
			reqLabelFlowUpgrade.setText("");
			costUpgradeFlowLabel.setText("MAX");
			costUpgradeFlowLabel.setLayoutX(705);
			btnUpgradeFlow.setDisable(true);
			progressBar.setProgress(-1);	
		}
		if (scorePerClick >= 10) {
			maxUpgradeScore = true;
			reqLabelScoreUpgrade.setText("");
			costUpgradeScoreLabel.setText("MAX");
			costUpgradeScoreLabel.setLayoutX(705);
			btnUpgradeScore.setDisable(true);
		}
		if (clickerPoint >= 50) {
			maxUpgradeClicker = true;
			reqLabelClickerUpgrade.setText("");
			costAddClickerLabel.setText("MAX");
			costAddClickerLabel.setLayoutX(705);
			btnAddClicker.setDisable(true);
		}
		
	}
	
	public void actBtnFinish() {

		Main.changeScreen();
		
	}
	
}
