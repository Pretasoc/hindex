package realAufgabe1;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFX extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
		Controller controller = new Controller("/publishingsList.txt");
        loader.setController(controller);

		BorderPane root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("H-Index");
		stage.show();
		
	}
	
}
