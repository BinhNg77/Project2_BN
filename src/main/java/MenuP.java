
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.util.Duration;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

public class MenuP {
    ImageView imageView;

    public MenuBar MenuP(Stage primaryStage){

        MenuBar menuBar = new MenuBar();
        Menu menu = this.menu(primaryStage);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    public Menu menu(Stage primaryStage){

        Menu menu= new Menu("Menu");
        MenuItem rule = new MenuItem("show rule");
        MenuItem odd = new MenuItem("show odd");
        MenuItem play = new MenuItem("play");
//        MenuItem newLook = new MenuItem("new Look");
        MenuItem quit = new MenuItem("exit");
        menu.getItems().add(rule);
        menu.getItems().add(odd);

        if (primaryStage == null){

        }else{
            menu.getItems().add(play);
        }
        menu.getItems().add(quit);


        menu.setOnAction(e -> {
            AtomicReference<TextArea> menuText = new AtomicReference<>(new TextArea());
            if (e.getTarget() == play){
                PlayingP gameScene = new PlayingP();
                primaryStage.setScene(gameScene.PlayingP());
            }else{
                Stage stage = new Stage();
                if (e.getTarget() == rule) {
                    System.out.println("RULE");
                    menuText.set(new TextArea("Keno is a popular gambling game offered in many modern casinos and also offered as a\n" +
                            "game in many state lotteries.\n" +
                            "Players wager by choosing a set amount of numbers( pick 2 numbers, pick 10 numbers,\n" +
                            "etc.) ranging from 1 to 80. After all players have made their wagers and picked their\n" +
                            "numbers, twenty numbers are drawn at random, between 1 and 80 with no duplicates.\n" +
                            "Players win by matching a set amount of their numbers to the numbers that are\n" +
                            "randomly drawn.\n" +
                            "The amount of numbers drawn and the amount of numbers matched that players are\n" +
                            "allowed to wager on will differ from casino to casino and state lottery to state lottery.\n" +
                            "Here are a couple of links to the North Carolina State Lottery and the Connecticut State\n" +
                            "Lottery versions of the game:"));
                    Scene menuScene = new Scene(menuText.get(), 500, 300);
                    stage.setScene(menuScene);
                    stage.show();

                }
                if (e.getTarget() == odd){
                    try {
                        Image oddImg = new Image(new FileInputStream("src/main/resources/KenoOdd.jpg"));
                        imageView = new ImageView(oddImg);
                        imageView.setFitHeight(600);
                        imageView.setFitWidth(500);
                        Group img = new Group(imageView);
                        Scene menuScene = new Scene(img, 500, 600);
                        stage.setScene(menuScene);
                        stage.show();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }


                }
                if(e.getTarget() == quit){
                    menuText.set(new TextArea(" Thank you for playing"));
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
                        Platform.exit();
                    }));
                    timeline.setCycleCount(1);
                    timeline.play();
                    Scene menuScene = new Scene(menuText.get(), 500, 300);
                    stage.setScene(menuScene);
                    stage.show();
                }
                menuText.get().setWrapText(true);
                menuText.get().setMaxWidth(200);
                menuText.get().setEditable(false);
            }
        });
        return menu;
    }


}
