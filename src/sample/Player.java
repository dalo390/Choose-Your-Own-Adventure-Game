package sample;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Player{

    private int health;
    private int fear;
    private String weapon;


    public Player(){
        health = 5;
        fear = 5;
    }
    public void healthReduce(){
        health --;
    }

    public void fearReduce(){
        fear --;
    }

    public void healthInc(){
        health ++;
    }

    public void fearInc(){
        health ++;
    }

    public int getHealth(){
        return health;
    }

    public int getFear(){
        return fear;
    }

    public void checkHealth(BorderPane layout){
        if(health == 5){
            Image hearts = new Image("sample/rec/fiveHearts.png", true);
            ImageView iv = new ImageView(hearts);
            iv.setImage(hearts);
            iv.setFitHeight(60);
            iv.setPreserveRatio(true);
            HBox heartBox = new HBox(iv);
            heartBox.setAlignment(Pos.CENTER);
            layout.setTop(heartBox);
        }
        if(health == 4){
            Image hearts = new Image("sample/rec/fourHearts.png", true);
            ImageView iv = new ImageView(hearts);
            iv.setImage(hearts);
            iv.setFitHeight(60);
            iv.setPreserveRatio(true);
            HBox heartBox = new HBox(iv);
            heartBox.setAlignment(Pos.CENTER);
            layout.setTop(heartBox);
        }
    }

}
