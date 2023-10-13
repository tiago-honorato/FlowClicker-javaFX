package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage stage;
	
	private static Scene finalScene;
	
	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
			Scene scene = new Scene(root);
			
			AnchorPane finalScene1 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Endgame.fxml"));
			finalScene = new Scene(finalScene1);
			
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			
			
			
			primaryStage.setResizable(false);
			
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void changeScreen() {
		
		stage.setScene(finalScene);
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
