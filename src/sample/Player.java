package sample;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Player{

    private int health;
    private boolean flare;

    public Player(){
        health = 5;
        flare = false;
    }
    public void healthReduce(){
        health --;
    }

    public void healthInc(){
        health ++;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health) { this.health = health;}

    //change health visual
    public void checkHealth(BorderPane layout){
        if(health == 5){
            Image hearts = new Image("sample/rec/hearts/fiveHearts.png", true);
            ImageView iv = new ImageView(hearts);
            iv.setImage(hearts);
            iv.setFitHeight(60);
            iv.setPreserveRatio(true);
            HBox heartBox = new HBox(iv);
            heartBox.setAlignment(Pos.CENTER);
            layout.setTop(heartBox);
        }
        if(health == 4){
            Image hearts = new Image("sample/rec/hearts/fourHearts.png", true);
            ImageView iv = new ImageView(hearts);
            iv.setImage(hearts);
            iv.setFitHeight(60);
            iv.setPreserveRatio(true);
            HBox heartBox = new HBox(iv);
            heartBox.setAlignment(Pos.CENTER);
            layout.setTop(heartBox);
        }
        if(health == 3){
            Image hearts = new Image("sample/rec/hearts/threeHearts.png", true);
            ImageView iv = new ImageView(hearts);
            iv.setImage(hearts);
            iv.setFitHeight(60);
            iv.setPreserveRatio(true);
            HBox heartBox = new HBox(iv);
            heartBox.setAlignment(Pos.CENTER);
            layout.setTop(heartBox);
        }
        if(health == 2){
            Image hearts = new Image("sample/rec/hearts/twoHearts.png", true);
            ImageView iv = new ImageView(hearts);
            iv.setImage(hearts);
            iv.setFitHeight(60);
            iv.setPreserveRatio(true);
            HBox heartBox = new HBox(iv);
            heartBox.setAlignment(Pos.CENTER);
            layout.setTop(heartBox);
        }
        if(health == 1){
            Image hearts = new Image("sample/rec/hearts/oneHearts.png", true);
            ImageView iv = new ImageView(hearts);
            iv.setImage(hearts);
            iv.setFitHeight(60);
            iv.setPreserveRatio(true);
            HBox heartBox = new HBox(iv);
            heartBox.setAlignment(Pos.CENTER);
            layout.setTop(heartBox);
        }
        if(health == 0){
            Image hearts = new Image("sample/rec/hearts/zeroHearts.png", true);
            ImageView iv = new ImageView(hearts);
            iv.setImage(hearts);
            iv.setFitHeight(60);
            iv.setPreserveRatio(true);
            HBox heartBox = new HBox(iv);
            heartBox.setAlignment(Pos.CENTER);
            layout.setTop(heartBox);
        }
    }

    public void setFlareTrue(){ flare = true; }

    public boolean getFlare(){ return(flare); }

}
