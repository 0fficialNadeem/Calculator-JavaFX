package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//load in FXML file
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculatorLayout.fxml"));
			CalculatorController cc = new CalculatorController();
			loader.setController(cc);
			VBox root = loader.load();
			
			//set-up scene
			Scene scene = new Scene(root,300,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//set-up Window/stage
			primaryStage.setTitle("Calculator");
			primaryStage.setScene(scene);
			primaryStage.show();
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
