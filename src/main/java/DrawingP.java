
import javafx.application.Application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class DrawingP {
    public VBox drawing;
    public Button playButton, pauseButton;
    public TextField pickNum, drawingNum;
    public ArrayList<Integer> num;
    public VBox drawingP (int spot, int bet, int draw, ArrayList<Integer> num){

        VBox drawing = new VBox();
        System.out.println(num.toString());
        pickNum = new TextField("your Pick Number: " );
        pickNum.setAlignment(Pos.CENTER);
        pickNum.setPrefHeight(50);
        pickNum.setPrefWidth(350);
        pickNum.setEditable(false);


        playButton = new Button("PLAY..");
        playButton.setDisable(true);


        drawing.getChildren().add(pickNum);
        drawing.getChildren().add(playButton);
        return drawing;
    }

    public TextField getPickNum(){
        return pickNum;
    }
    public Button getPlayButton(){
        return playButton;
    }
    public void setNum(ArrayList num){
        this.num = num;
    }
    public ArrayList getNum(){
        return this.num;
    }
}
