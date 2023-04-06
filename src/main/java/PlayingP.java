
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayingP {
    final int[] k = {0};
    Timeline timeline;
    GridPane spot, betCard, drawing, money, randomD;
    Scene gameScene;
    Button playButton;
    TextField picknum, totalW;
    int finalWin;
    Button confirmSpotButton, confirmDrawingButton, confirmMoneyButton, confirmBCButton, pickRandomButton;
    Stage gameStage = null;
    int selectedSpot = 0;
    int selectedDrawing = 0;
    int selectedMoney = 0;
    AtomicInteger spotFinal, drawingFinal;
    ArrayList<Integer> listBC, templistBC;
    MenuItem newLook;

    int win = 0;

    int match = 0;
    public Scene PlayingP() {

        MenuP menuBar = new MenuP();
        VBox MenuBox = new VBox();// then set title on top of mid by using Vertical Box
        MenuBar menuBarGame = menuBar.MenuP(gameStage);

        System.out.println(menuBarGame.getMenus().get(0).getItems().get(2).getText());
        newLook = new MenuItem("NewLook");
//        newLook = menuBarGame.getMenus().get(0).getItems().get(2);
        menuBarGame.getMenus().get(0).getItems().add(newLook);


        MenuBox.getChildren().add(menuBarGame);



        // SPOT SELECTION ==============
        Label betCardL = new Label("Choose your Cards after spot");
        confirmBCButton = new Button("Start game");
        confirmSpotButton = new Button("confirm your spot");
        confirmSpotButton.setDisable(true);


        Label spotL = new Label("Choose your Spot");
        spot = new GridPane();
        int[] spotNum = {1, 4, 8, 10};
        ToggleGroup tg = new ToggleGroup();
        for (int i = 0; i < spotNum.length; i++) {
            RadioButton c = new RadioButton(Integer.toString(spotNum[i]));
            c.setToggleGroup(tg);
            spot.add(c, i, 0, 1, 1);
        }
        spot.setVgap(5);
        spot.setHgap(20);
        spotFinal = new AtomicInteger(0);
        tg.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton) tg.getSelectedToggle();
            if (rb != null) {
                String s = rb.getText();
                selectedSpot = Integer.parseInt(s);
                spotFinal.set(selectedSpot);
                if (selectedSpot == 1) {
                    confirmSpotButton.setText("Confirm " + selectedSpot + " card");
                } else {
                    confirmSpotButton.setText("Confirm " + selectedSpot + " cards");
                }
            }
            confirmSpotButton.setDisable(false);
        });


        // DRAWING SELECTION ==============
        drawing = new GridPane();
        Label drawingL = new Label("How many drawing");
        ToggleGroup tgd = new ToggleGroup();
        for (int i = 1; i < 5; i++) {
            RadioButton c = new RadioButton(Integer.toString(i));
            c.setToggleGroup(tgd);
            drawing.add(c, i, 0, 1, 1);
        }
        drawing.setVgap(5);
        drawing.setHgap(20);
        confirmDrawingButton = new Button("confirm your drawing");
        confirmDrawingButton.setDisable(true);
        drawingFinal = new AtomicInteger(0);
        tgd.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton) tgd.getSelectedToggle();
            if (rb != null) {
                String s = rb.getText();
                selectedDrawing = Integer.parseInt(s);
                drawingFinal.set(selectedDrawing);
                confirmDrawingButton.setText("Confirm drawing");
            }
            confirmDrawingButton.setDisable(false);
        });

        // MONEY SELECTION ==============
        money = new GridPane();
        Label moneyL = new Label("How much would u like to bet");
        ToggleGroup tgm = new ToggleGroup();
        int[] moneyBet = {1, 5, 8, 10};
        for (int i = 0; i < moneyBet.length; i++) {
            RadioButton c = new RadioButton("$" + Integer.toString(moneyBet[i]));
            c.setToggleGroup(tgm);
            money.add(c, i, 0, 1, 1);
        }
        money.setVgap(5);
        money.setHgap(20);
        confirmMoneyButton = new Button("confirm your bet");
        confirmMoneyButton.setDisable(true);

        tgm.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton) tgm.getSelectedToggle();
            if (rb != null) {
                String s = rb.getText();
                selectedMoney = (Integer.parseInt(s.substring(1)));
                confirmMoneyButton.setText("Confirm your new bet");
            }
            confirmMoneyButton.setDisable(false);
        });


        // BETCARD SELECTION ==============
        confirmBCButton = new Button("confirm your picks");
        pickRandomButton = new Button("choose random ");
        confirmBCButton.setDisable(true);
        pickRandomButton.setDisable(true);

        betCard = new GridPane();
        listBC = new ArrayList<Integer>(selectedSpot);
        templistBC = new ArrayList<Integer>(selectedSpot);
        int row = 0;
        int col = 0;
        AtomicInteger limit = new AtomicInteger(0);
        for (int i = 1; i < 81; i++) {
            CheckBox c = new CheckBox(Integer.toString(i));
            c.selectedProperty().addListener(
                    (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {

                        if (templistBC.size() == selectedSpot) {
//                            System.out.println("FFFFFF " + listBC.size());
//                            templistBC = listBC;
                        }
                        if (c.isSelected() == true) {
                            System.out.println("selected " + c.getText() + "  :  " + limit.get());
                            limit.getAndIncrement();
                            templistBC.add(Integer.valueOf(c.getText()));
                            System.out.println(templistBC.toString());
                        } else {
                            System.out.println("deselected " + c.getText() + "  :  " + limit.get());
                            limit.getAndDecrement();
                            templistBC.remove(Integer.valueOf(c.getText()));
                        }
                        if (limit.get() != selectedSpot) {
                            confirmBCButton.setDisable(true);
                        } else {
                            confirmBCButton.setDisable(false);
                        }
                    });
            if (i % 8 == 1) {
                row++;
                col = 0;
            }
            betCard.add(c, col, row, 1, 1);
            col++;
        }


        betCard.setDisable(true);
        betCard.setHgap(5);
        betCard.setVgap(20);
        // BETCARD SELECTION_END ==============

//        FLOW CONTROL =======================
        confirmSpotButton.setOnAction(e -> {
            if (confirmSpotButton.getText() == "reset spot") {
                confirmSpotButton.setText("pick new spot");
                confirmBCButton.setDisable(true);
                betCardL.setText("Choose " + " cards");
                confirmSpotButton.setDisable(false);
                spot.setDisable(false);
                betCard.setDisable(true);
//                selectedSpot = 0;
            } else {
                confirmSpotButton.setText("reset spot");
                betCardL.setText("Choose " + selectedSpot + " cards");
                confirmSpotButton.setDisable(false);
                spot.setDisable(true);
                if (confirmSpotButton.getText() == "reset spot" && confirmDrawingButton.getText() == "reset drawing" && confirmMoneyButton.getText() == "reset betting") {
                    betCard.setDisable(false);
                    pickRandomButton.setDisable(false);
                }
            }
        });

        confirmDrawingButton.setOnAction(e -> {
            if (confirmDrawingButton.getText() == "reset drawing") {
                confirmDrawingButton.setText("pick new drawing");
                confirmBCButton.setDisable(true);
                confirmDrawingButton.setDisable(false);
                drawing.setDisable(false);
                betCard.setDisable(true);
            } else {
                confirmDrawingButton.setText("reset drawing");
                drawing.setDisable(true);
                if (confirmSpotButton.getText() == "reset spot" && confirmDrawingButton.getText() == "reset drawing" && confirmMoneyButton.getText() == "reset betting") {
                    betCard.setDisable(false);
                    pickRandomButton.setDisable(false);
                }
            }
        });

        confirmMoneyButton.setOnAction(e -> {
            if (confirmMoneyButton.getText() == "reset betting") {
                confirmMoneyButton.setText("pick new betting");
                confirmBCButton.setDisable(true);
                confirmMoneyButton.setDisable(false);
                money.setDisable(false);
                betCard.setDisable(true);
                System.out.println(selectedMoney);
            } else {
                confirmMoneyButton.setText("reset betting");
                money.setDisable(true);
                System.out.println(selectedMoney);
                if (confirmSpotButton.getText() == "reset spot" && confirmDrawingButton.getText() == "reset drawing" && confirmMoneyButton.getText() == "reset betting") {
                    betCard.setDisable(false);
                    pickRandomButton.setDisable(false);
                }
            }
        });

        DrawingP drawingSide = new DrawingP();
        VBox rightBox = drawingSide.drawingP(selectedSpot, selectedMoney,drawingFinal.get(), listBC);

//        FINALIZE PICK AND RANDOM =============================
        confirmBCButton.setOnAction(e -> {
            listBC = templistBC;
            listBC.sort(Comparator.naturalOrder());
            System.out.println(listBC.toString());
            picknum.setText("your picked number: " + listBC.toString());
            playButton.setDisable(false);
            drawingSide.setNum(listBC);
        });

        pickRandomButton.setOnAction(e -> {
//            confirmBCButton.setDisable(true);
            System.out.println(selectedSpot);

//            listBC = new ArrayList<Integer>(selectedSpot);
            UniqueRng uniqueRng = new UniqueRng();
            listBC = uniqueRng.ArrayListUniqueRng(selectedSpot, 80);
            listBC.sort(Comparator.naturalOrder());
            System.out.println(listBC.toString());
            picknum.setText("your picked number: " + listBC.toString());
            playButton.setDisable(false);
//            betCard.setDisable(true);
            drawingSide.setNum(listBC);
        });

        HBox spotSection = new HBox(spot, confirmSpotButton);
        spotSection.setSpacing(10);
        HBox drawingSection = new HBox(drawing, confirmDrawingButton);
        drawingSection.setSpacing(10);
        HBox moneySection = new HBox(money, confirmMoneyButton);
        moneySection.setSpacing(10);

        HBox pickorRandom = new HBox(confirmBCButton, pickRandomButton);
        pickorRandom.setSpacing(10);

        VBox menuB = new VBox(MenuBox);
        VBox leftBox = new VBox(spotL, spotSection, drawingL, drawingSection, moneyL, moneySection, betCardL, betCard, pickorRandom);

        picknum = drawingSide.getPickNum();
        playButton = drawingSide.getPlayButton();
        Button pauseBt = new Button("pause");
        pauseBt.setDisable(true);
        final int[] drawLeft = {0};

        playButton.setOnAction(e->{
            if(!drawingSide.getNum().isEmpty()){
                System.out.println(drawingSide.getNum().toString());
                System.out.println("spot: " + selectedSpot + " -bet " + selectedMoney + "  -draw " + selectedDrawing );
                spotSection.setDisable(true);
                drawingSection.setDisable(true);
                drawingSection.setDisable(true);
                moneySection.setDisable(true);
                betCard.setDisable(true);
                pickorRandom.setDisable(true);
                pauseBt.setDisable(false);
                drawLeft[0]++;
                k[0] =0;

                randomD = new GridPane();
                int rowD = 0;
                int colD = 0;
                UniqueRng uniqueDrawing = new UniqueRng();
                ArrayList listDrawing = new ArrayList<Integer>();
                listDrawing = uniqueDrawing.ArrayListUniqueRng(20, 80);

                for (int j = 0; j < listDrawing.size(); j++) {
                    Button displayN = new Button( "...");
                    displayN.setId(listDrawing.get(j).toString());
                    displayN.setDisable(true);
                    displayN.setStyle(
                            "-fx-border-color: red;" + "-fx-border-radius: 10em;" +
                                    "-fx-text-fill: black;" +
                                    "-fx-background-radius: 10em; " +
                                    "-fx-min-width: 40px; " +
                                    "-fx-min-height: 40px; " +
                                    "-fx-max-width: 40px; " +
                                    "-fx-max-height: 40px;"
                    );
                    if (j % 10 == 0) {
                        rowD++;
                        colD = 0;
                    }
                    int finalColD = colD;
                    int finalRowD = rowD;
                    randomD.add(displayN, finalColD, finalRowD, 1, 1);
                    colD++;

                }

                rightBox.getChildren().add(randomD);


                timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), ev -> {

                    Object bt = randomD.getChildren().get(k[0]);
                    Button btt = (Button) bt;
                    System.out.println(btt.getId());

                    if (listBC.contains(Integer.valueOf(btt.getId().toString()))){
                        match ++;
                        System.out.println("MATCH IC: " + match);
                        btt.setText(btt.getId().toString());
                        btt.setStyle(
                                "-fx-border-color: green;" +
                                        "-fx-border-radius: 10em;" +
                                        "-fx-text-fill: black;" +
                                        "-fx-background-radius: 10em; " +
                                        "-fx-min-width: 40px; " +
                                        "-fx-min-height: 40px; " +
                                        "-fx-max-width: 40px; " +
                                        "-fx-max-height: 40px;"+"-fx-background-color: #008000;"
                        );
                    }else{
                        btt.setText(btt.getId().toString());
                        btt.setStyle(
                                "-fx-border-color: red;" +
                                        "-fx-border-radius: 10em;" +
                                        "-fx-text-fill: black;" +
                                        "-fx-background-radius: 10em; " +
                                        "-fx-min-width: 40px; " +
                                        "-fx-min-height: 40px; " +
                                        "-fx-max-width: 40px; " +
                                        "-fx-max-height: 40px;"+"-fx-background-color: red;"
                        );
                    }
                    k[0]++;
                    if (k[0] == 20){

                        if (selectedSpot == 1){
                            win = match * selectedMoney;
                        }
                        else if (selectedSpot == 4){
                            if (match == 4){
                                win = match * 75 * selectedMoney;
                            }else if (match == 3){
                                win = match * 5 * selectedMoney;
                            } else if (match == 2){
                                win = match * 1 * selectedMoney;
                            }
                        } else if (selectedSpot == 8) {
                            if (match == 8){
                                win = match * 10000 * selectedMoney;
                            }else if (match == 7){
                                win = match * 750 * selectedMoney;
                            } else if (match == 6){
                                win = match * 50 * selectedMoney;
                            }if (match == 5){
                                win = match * 12 * selectedMoney;
                            }else if (match == 4){
                                win = match * 2 * selectedMoney;
                            }
                        }else if (selectedSpot == 10) {
                            if (match == 10){
                                win = match * 100000 * selectedMoney;
                            }else if (match == 9){
                                win = match * 4250 * selectedMoney;
                            } else if (match == 8){
                                win = match * 450 * selectedMoney;
                            }if (match == 7){
                                win = match * 40 * selectedMoney;
                            }else if (match == 6){
                                win = match * 15 * selectedMoney;
                            }else if (match == 5){
                                win = match * 2 * selectedMoney;
                            }else if (match == 0){
                                win = 5;
                            }
                        }
                        System.out.println("Match: "+ match + " Win: " + win + " SPOT: " + selectedSpot);
                        playButton.setText("next Drawing");
                        match = 0;
                        finalWin += win;
                        win = 0;
                        pauseBt.setDisable(true);
                        playButton.setDisable(false);
                        if (drawLeft[0] == selectedDrawing){
                            System.out.println("Final Win " + finalWin);
                            totalW = new TextField("Your total Win: $"+ finalWin +".00");
                            totalW.setEditable(false);
                            totalW.setAlignment(Pos.CENTER);
                            rightBox.getChildren().add(totalW);
                            playButton.setDisable(true);
                        }
                    }else{
                        playButton.setDisable(true);
                    }

                }));
                timeline.setCycleCount(20);
                timeline.play();
            }
        });


        pauseBt.setOnAction(e->{
            if(drawLeft[0] == selectedDrawing && k[0] == 20){
                pauseBt.setDisable(true);
            }
            if(pauseBt.getText() == "resume"){
                timeline.play();
                pauseBt.setText("pause");
            }else{
                pauseBt.setText("resume");
                timeline.pause();
            }

        });
        rightBox.getChildren().add(pauseBt);

        picknum.setText("your picked numbers");



        HBox gamingScene = new HBox(leftBox, rightBox);
        VBox wholeScene = new VBox(menuB, gamingScene);
        leftBox.setSpacing(10);
        rightBox.setSpacing(10);
        gamingScene.setSpacing(10);

        newLook.setOnAction(e->{
            System.out.println("NEWW LOOK");
            menuB.setStyle("-fx-background-color: blue; -fx-padding: 5;");
            gamingScene.setStyle("-fx-background-color: #ffffcc; -fx-padding: 5;");
            drawingL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            betCardL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            moneyL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            spotL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

//            totalW.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            playButton.setStyle("-fx-border-color: blue;"  + "-fx-background-color: #66ff99;" +
                    "-fx-border-radius: 10em;"+ "-fx-text-fill: black;" +
                    "-fx-background-radius: 10em; " +
                    "-fx-min-width: 70px; " +
                    "-fx-min-height: 30px; " +
                    "-fx-max-width: 120 px; " +
                    "-fx-max-height: 30px;");

            pauseBt.setStyle("-fx-border-color: blue;" + "-fx-background-color: #ffcc99;" +
                    "-fx-border-radius: 10em;"+ "-fx-text-fill: black;" +
                    "-fx-background-radius: 10em; " +
                    "-fx-min-width: 70px; " +
                    "-fx-min-height: 30px; " +
                    "-fx-max-width: 170px; " +
                    "-fx-max-height: 30px;");
            confirmBCButton.setStyle("-fx-border-color: blue;" + "-fx-background-color: #ffcc99;" +
                    "-fx-border-radius: 10em;"+ "-fx-text-fill: black;" +
                    "-fx-background-radius: 10em; " +
                    "-fx-min-width: 70px; " +
                    "-fx-min-height: 30px; " +
                    "-fx-max-width: 170px; " +
                    "-fx-max-height: 130px;");
            confirmMoneyButton.setStyle("-fx-border-color: blue;" + "-fx-background-color: #ffcc99;" +
                    "-fx-border-radius: 10em;"+ "-fx-text-fill: black;" +
                    "-fx-background-radius: 10em; " +
                    "-fx-min-width: 70px; " +
                    "-fx-min-height: 30px; " +
                    "-fx-max-width: 170px; " +
                    "-fx-max-height: 130px;");
            confirmDrawingButton.setStyle("-fx-border-color: blue;" + "-fx-background-color: #ffcc99;" +
                    "-fx-border-radius: 10em;"+ "-fx-text-fill: black;" +
                    "-fx-background-radius: 10em; " +
                    "-fx-min-width: 70px; " +
                    "-fx-min-height: 30px; " +
                    "-fx-max-width: 170px; " +
                    "-fx-max-height: 130px;");
            confirmSpotButton.setStyle("-fx-border-color: blue;" + "-fx-background-color: #ffcc99;" +
                    "-fx-border-radius: 10em;"+ "-fx-text-fill: black;" +
                    "-fx-background-radius: 10em; " +
                    "-fx-min-width: 70px; " +
                    "-fx-min-height: 30px; " +
                    "-fx-max-width: 170px; " +
                    "-fx-max-height: 130px;");
            pickorRandom.setStyle("-fx-border-color: blue;" + "-fx-background-color: #ffcc99;" +
                    "-fx-border-radius: 10em;"+ "-fx-text-fill: black;" +
                    "-fx-background-radius: 10em; " +
                    "-fx-min-width: 70px; " +
                    "-fx-min-height: 30px; " +
                    "-fx-max-width: 70px; " +
                    "-fx-max-height: 130px;");
            pickRandomButton.setStyle("-fx-border-color: blue;" + "-fx-background-color: #ffcc99;" +
                    "-fx-border-radius: 10em;"+ "-fx-text-fill: black;" +
                    "-fx-background-radius: 10em; " +
                    "-fx-min-width: 170px; " +
                    "-fx-min-height: 30px; " +
                    "-fx-max-width: 170px; " +
                    "-fx-max-height: 130px;");
        });

        gameScene = new Scene(wholeScene, 700, 700);
        return gameScene;
    }



}
