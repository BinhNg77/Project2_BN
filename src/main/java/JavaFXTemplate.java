import javafx.application.Application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.geometry.Pos;
import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class JavaFXTemplate extends Application {


	static String defaultStyle = "-fx-background-color: #e2f0f1; -fx-border-color: grey;-fx-border-radius: 10px;";
	static String alertStyle = "-fx-background-color: #E9967A; -fx-border-color: grey;-fx-border-radius: 10px;";


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
//		stage = primaryStage;
		// TODO Auto-generated method stub
		primaryStage.setTitle("Binh Nguyen Project2-Test");

		MenuP menuBar = new MenuP();
		VBox MenuBox = new VBox();// then set title on top of mid by using Vertical Box
		MenuBox.getChildren().add(menuBar.MenuP(primaryStage));

		try {
			Image oddImg = new Image(new FileInputStream("src/main/resources/KenoFront1.jpg"));
			ImageView imageView = new ImageView(oddImg);
			imageView.setFitHeight(200);
			imageView.setFitWidth(650);
			Group img = new Group(imageView);
			VBox root = new VBox(MenuBox,img);
			Scene scene = new Scene(root, 650, 220);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}

//		************** MENU-END ******************






	}

}
