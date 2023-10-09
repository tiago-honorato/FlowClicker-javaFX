package model;

import control.MainSceneController;
import javafx.application.Platform;

public class MainModel {
	
	public void actBtnAddClicker() {
		
		System.out.println("MainModel Method");
		
		Thread t = new Thread(() ->{
			
			while(true) {
				
				try {
					
					Thread.sleep(3000);
					
					Platform.runLater(() ->{
						
						System.out.println("THREAD!!!!");
						
						MainSceneController.score +=1;
						
					});
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		t.setDaemon(true);
		t.start();
		
	}
	
}
