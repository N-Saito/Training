package application;
	
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			URL url = getClass().getClassLoader().getResource("\\fxml\\testGridPane.fxml");
			 GridPane root = FXMLLoader.load(url);
			Scene scene = new Scene(root,400,400);
			primaryStage.setTitle("FXMLサンプル");
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
