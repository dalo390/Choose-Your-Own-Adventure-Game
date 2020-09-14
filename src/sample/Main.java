package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main extends Application {

    private Label storyline;
    private AudioClip clickSound;
    private AudioClip hurtSound;
    private MediaPlayer morning;
    private MediaPlayer night;
    private int health;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane layout = new BorderPane();
        Scene titleScreen = new Scene(layout, 900,600);

        //create and add titleScreen buttons
        Button button1 = new Button("Start");
        button1.setOnAction(e -> {
            clickSound.play();
            buildScene0(primaryStage);
        });
        Button button2 = new Button("Exit");
        button2.setOnAction(e -> {
            clickSound.play();
            System.exit(0);
        });
        HBox hbox = new HBox(button1, button2);
        HBox.setMargin(button1,new Insets(0,100,50,0));
        HBox.setMargin(button2,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //create and add S1 storyline
        Label title = new Label();
        title.setWrapText(true);
        title.setPrefWidth(550);
        title.setId("title");
        try{
            Font.loadFont(new FileInputStream(new File("D:/Java w IntelliJ/src/sample/rec/gf.ttf")),50);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        title.setTextAlignment(TextAlignment.CENTER);
        title.setText("Summer Camp");
        layout.setCenter(title);
        primaryStage.setTitle("Summer Camp");

        //sounds
        clickSound = new AudioClip(getClass().getResource("/sample/rec/clickSound.mp3").toString());
        clickSound.setVolume(.3);

        hurtSound = new AudioClip(getClass().getResource("/sample/rec/hurtSound.mp3").toString());

        Media morningMusic = new Media(getClass().getResource("/sample/rec/morningSound.mp3").toString());
        morning = new MediaPlayer(morningMusic);
        morning.setVolume(.1);

        Media nightMusic = new Media(getClass().getResource("/sample/rec/nightSound.mp3").toString());
        night = new MediaPlayer(nightMusic);
        night.setVolume(.9);

        morning.setAutoPlay(true);
        morning.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                morning.seek(Duration.ZERO);
                morning.play();
            }
        });

        //health
        health = 5;

        titleScreen.getStylesheets().add("sample/rec/menu.css");
        primaryStage.setScene(titleScreen);
        primaryStage.show();
    }

    public void buildScene0(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene0 = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("morningSky");

        //create and add S1 buttons
        Button button1 = new Button("continue");
        button1.setOnAction(e -> {
            clickSound.play();
            granolaScene(primaryStage);
        });
        VBox vbox = new VBox(button1);
        VBox.setMargin(button1,new Insets(0,20,50,20));
        vbox.setAlignment(Pos.CENTER);
        layout.setBottom(vbox);

        //create and add S1 storyline
        storyline = new Label();
        storyline.setWrapText(true);
        storyline.setPrefWidth(700);
        try{
            final Font f = Font.loadFont(new FileInputStream(new File("D:/Java w IntelliJ/src/sample/rec/gf.ttf")), 25);
            storyline.setFont(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        storyline.setTextFill(Color.web("#181818"));
        storyline.setTextAlignment(TextAlignment.CENTER);
        storyline.setText("It was a summer like any other. You and your close friends (Corey, Noel, Jada, and Olivia) are on the annual camping trip program provided by your high school. Only a select few were allowed to go. Luckily everyone in your friend group managed to make it.");
        layout.setCenter(storyline);
        primaryStage.setTitle("Summer Camp");

        //health
        checkHealth(layout);

        scene0.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene0);
    }

    public void granolaScene(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene1 = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("morningSky");

        //create and add S1 storyline
        storyline.setWrapText(true);
        storyline.setText("On the bus ride there, you find an old granola bar in your backpack");
        layout.setCenter(storyline);

        //create and add S1 buttons
        Button button1 = new Button("Save it for later");
        button1.setWrapText(true);
        button1.setPrefWidth(250);
        button1.setOnAction(e -> {
            clickSound.play();
            buildScene1(primaryStage);
        });
        Button button2 = new Button("Eat it");
        button2.setWrapText(true);
        button2.setPrefWidth(250);
        button2.setOnAction(e -> {
            health--;
            hurtSound.play();
            clickSound.play();
            granolaScene2(primaryStage);
        });
        HBox hbox = new HBox(30, button1, button2);
        HBox.setMargin(button1,new Insets(0, 20,50,20));
        HBox.setMargin(button2,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        checkHealth(layout);

        scene1.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene1);
    }

    public void granolaScene2(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene3A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("morningSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Looks like it was a little too old...");

        //create and position buttons
        Button button = new Button("continue");

        button.setOnAction(e-> {
            clickSound.play();
            buildScene1(primaryStage);
        });
        HBox hbox = new HBox(button);
        HBox.setMargin(button,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        checkHealth(layout);

        scene3A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene3A);
    }

    public void buildScene1(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene1 = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("morningSky");

        //create and add S1 storyline
        storyline.setWrapText(true);
        storyline.setText("There are a couple strangers but that's alright, you'll learn to make do. The bus arrives and you meet Andy, your camping director. He welcomes you but tells the group that this year they have to split everyone into two groups: one to stay in the tents and one to stay in the cabin.");
        layout.setCenter(storyline);

        //create and add S1 buttons
        Button button1 = new Button("You don't wanna deal with the lack of air conditioning all night long. You choose to stay in the cabin.");
        button1.setWrapText(true);
        button1.setPrefWidth(250);
        button1.setOnAction(e -> {
            clickSound.play();
            buildScene2A(primaryStage);
        });
        Button button2 = new Button("Your friends think you'll have more fun with more freedom at the tents. You volunteer to join them.");
        button2.setWrapText(true);
        button2.setPrefWidth(250);
        button2.setOnAction(e -> {
            clickSound.play();
            buildScene2B(primaryStage);
        });
        HBox hbox = new HBox(30, button1, button2);
        HBox.setMargin(button1,new Insets(0, 20,50,20));
        HBox.setMargin(button2,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        checkHealth(layout);

        scene1.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene1);
    }

    public void buildScene2A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene2A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You decide to stay in the cabin after all. You'll miss out on some fun but that's okay, not sleeping in a puddle of sweat all night makes it worth it. Nighttime hits and everyone parts ways. But suddenly 2 hours later... the cabin jerks awake to the sounds of screams. What happened...");

        //create and position buttons
        Button button2 = new Button("continue");
        button2.setOnAction(e -> {
            clickSound.play();
            buildScene3A(primaryStage);
        });
        HBox hbox = new HBox(button2);
        HBox.setMargin(button2,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        morning.stop();
        night.play();
        night.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                night.seek(Duration.ZERO);
                night.play();
            }
        });

        //health
        checkHealth(layout);

        scene2A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene2A);
    }

    public void buildScene2B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene2B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You don't split from your friends. You'd rather suffer the heat with them than be bored in the cabin. Night falls. Even though there are multiple tents, you all pile into one tent to tell ghost stories in the sanctity from mosquitoes. But suddenly there's a loud snap of a branch outside. You all freeze.");

        //create and position buttons
        Button button3B = new Button("You grab your pocket knife");
        button3B.setOnAction(e -> {
            clickSound.play();
            buildScene3B(primaryStage);
        });
        Button button3C = new Button("You grab the flashlight");
        button3C.setOnAction(e -> {
            clickSound.play();
            buildScene3C(primaryStage);
        });
        HBox hbox = new HBox(30, button3B, button3C);
        HBox.setMargin(button3B,new Insets(0, 20,50,20));
        HBox.setMargin(button3C,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        morning.stop();
        night.play();
        night.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                night.seek(Duration.ZERO);
                night.play();
            }
        });

        //health
        checkHealth(layout);

        scene2B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene2B);
    }

    public void buildScene3A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene3A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");


        //change the story
        layout.setCenter(storyline);
        storyline.setText(" You hear your friends bang on the cabin door begging you to let them in. \"Someone's trying to kill us!!\"Opening it you find Olivia covered in blood and tears. You quickly realize Corey is missing.");

        //create and position buttons
        Button button4A = new Button("continue");

        button4A.setOnAction(e-> {
            clickSound.play();
            buildScene4A(primaryStage);
        });
        HBox hbox = new HBox(button4A);
        HBox.setMargin(button4A,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        checkHealth(layout);

        scene3A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene3A);
    }

    public void buildScene3B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene3B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You fish your pocket knife out of your belongings as quick as possible. It's small but sharp. Suddenly a hatchet rips through the cheap tent fabric burying itself in Codey's head. All hell breaks loose as everyone tries to flee the tent. Everyone bolts in the direction of the cabin (except Codey of course, he's dead). You can't see anything in the dark so you trip and die from an axe to the chest.");

        //create and position buttons
        BorderPane buttonPos = new BorderPane();
        Button buttonStart = new Button("Try Again");
        buttonStart.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(buttonStart);
        HBox.setMargin(buttonStart,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        checkHealth(layout);

        scene3B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene3B);
    }

    public void buildScene3C(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene3C = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You quickly snatch the flashlight next to you. Suddenly a hatches rips through the cheap tent fabric burying itself in Codey's head. All hell breaks loose as everyone tries to flee the tent. Everyone bolts in the direction of the cabin (except Codey of course, he's dead). Using your flashlight you easily run to the cabin, pleading for help.");

        //create and position buttons
        Button button4A = new Button("continue");
        button4A.setOnAction(e -> {
            clickSound.play();
            buildScene4A(primaryStage);
        });
        HBox hbox = new HBox(button4A);
        HBox.setMargin(button4A,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        checkHealth(layout);

        scene3C.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene3C);
    }

    public void buildScene4A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene4A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());

        //change the story
        layout.setCenter(storyline);
        storyline.setText("To be continued...");

        scene4A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene4A);
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

    public static void main(String[] args) {
        launch(args);
    }
}
