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
    private Player player;

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


        Font.loadFont(getClass().getResourceAsStream("/sample/rec/gf.ttf"),50);


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
        player = new Player();

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

        storyline.setTextFill(Color.web("#181818"));
        storyline.setTextAlignment(TextAlignment.CENTER);
        storyline.setText("You and your close friends are on the annual camping trip program provided by your high school.");
        layout.setCenter(storyline);
        primaryStage.setTitle("Summer Camp");

        //health
        player.checkHealth(layout);

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
        storyline.setText("On the bus ride there, you find a chocolate bar in your backpack. Cookies and cream...");
        layout.setCenter(storyline);

        //create and add S1 buttons
        Button button1 = new Button("Save it for later");
        button1.setPrefWidth(250);
        button1.setOnAction(e -> {
            clickSound.play();
            buildScene1(primaryStage);
        });
        Button button2 = new Button("Eat it");
        button2.setPrefWidth(250);
        button2.setOnAction(e -> {
            player.healthReduce();
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
        player.checkHealth(layout);

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
        player.checkHealth(layout);

        scene3A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene3A);
    }

    public void buildScene1(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene1 = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("morningSky");

        //create and add S1 storyline0
        storyline.setWrapText(true);
        storyline.setText("The bus arrives and you meet Andy, your camping director. He welcomes you but tells the group that this year they have to split everyone into two groups: one to stay in the tents and one to stay in the cabin.");
        layout.setCenter(storyline);

        //create and add S1 buttons
        Button button1 = new Button("It's gonna be too hot in the tents. You choose to stay in the cabin.");
        button1.setPrefWidth(250);
        button1.setOnAction(e -> {
            clickSound.play();
            buildScene2A(primaryStage);
        });
        Button button2 = new Button("Your friends think the tents will be more fun. You volunteer to join them.");
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
        player.checkHealth(layout);

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
        storyline.setText("You decide to stay in the cabin after all. You'll miss out on some fun but that's okay. Nighttime hits and everyone parts ways. But suddenly 2 hours later... the cabin jerks awake to the sounds of screams. What happened...");

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
        player.checkHealth(layout);

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
        player.checkHealth(layout);

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
        storyline.setText(" You hear your friends bang on the cabin door begging you to let them in. \"Someone's trying to kill us!!\"Opening it you find them covered in blood and tears. You quickly realize one is missing.");

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
        player.checkHealth(layout);

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
        storyline.setText("You fish your pocket knife out of your belongings as quick as possible. It's small but sharp. Suddenly a hatchet rips through the cheap tent fabric burying itself in someones head. All hell breaks loose as everyone tries to flee the tent. Everyone bolts in the direction of the cabin (except your dead friend, of course). You can't see anything in the dark so you trip and die from an axe to the chest.");

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
        player.checkHealth(layout);

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
        storyline.setText("You quickly snatch the flashlight next to you. Suddenly a hatchet rips through the cheap tent fabric burying itself in your friend head. All hell breaks loose as everyone tries to flee the tent. Everyone bolts in the direction of the cabin (except your dead friend, of course). Using your flashlight you easily find your way to the cabin, screaming for help.");

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
        player.checkHealth(layout);

        scene3C.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene3C);
    }

    public void buildScene4A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene4A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("The lights suddenly go out. Someone has to go check the fuse box.");

        //create and position buttons
        Button button5A = new Button("Be the hero and go alone");
        button5A.setPrefWidth(250);
        button5A.setOnAction(e -> {
            clickSound.play();
            buildScene5A(primaryStage);
        });
        Button button5B = new Button("Make everyone come with you to check");
        button5B.setPrefWidth(250);
        button5B.setOnAction(e -> {
            clickSound.play();
            buildScene5B(primaryStage);
        });
        HBox hbox = new HBox(30, button5A, button5B);
        HBox.setMargin(button5A,new Insets(0, 20,50,20));
        HBox.setMargin(button5B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene4A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene4A);
    }

    public void buildScene5A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene5A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You manage to find the fuse box. But it looks like someones here to give you company. And they brought their axe.");

        //create and position buttons
        Button button6A = new Button("Run to the cabin with your tail between your legs.");
        button6A.setOnAction(e -> {
            clickSound.play();
            buildScene6A(primaryStage);
        });
        Button button6B = new Button("Tussle");
        button6B.setOnAction(e -> {
            player.healthReduce();
            hurtSound.play();
            clickSound.play();
            buildScene6B(primaryStage);
        });
        HBox hbox = new HBox(30, button6A, button6B);
        HBox.setMargin(button6A,new Insets(0, 20,50,20));
        HBox.setMargin(button6B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene5A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene5A);
    }

    public void buildScene5B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene5B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You all manage to find the fuse box. But it looks like he also found the fuse box. Time to make a choice.");

        //create and position buttons
        Button button6C = new Button("Tussle as a community.");
        button6C.setOnAction(e -> {
            player.healthReduce();
            hurtSound.play();
            clickSound.play();
            buildScene6C(primaryStage);
        });
        Button button6D = new Button("\"Everybody run for your lives!\"");
        button6D.setOnAction(e -> {
            clickSound.play();
            buildScene6D(primaryStage);
        });
        HBox hbox = new HBox(30, button6C, button6D);
        HBox.setMargin(button6C,new Insets(0, 20,50,20));
        HBox.setMargin(button6D,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene5B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene5B);
    }

    public void buildScene6A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene6A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You quickly make it back to the cabin. It seems everyone abandoned it and you.");

        //create and position buttons
        Button button7A = new Button("Hide.");
        button7A.setOnAction(e -> {
            clickSound.play();
            buildScene6C(primaryStage);
        });
        Button button7B = new Button("Look for car keys.");
        button7B.setOnAction(e -> {
            clickSound.play();
            buildScene7B(primaryStage);
        });
        HBox hbox = new HBox(30, button7A, button7B);
        HBox.setMargin(button7A,new Insets(0, 20,50,20));
        HBox.setMargin(button7B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene6A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene6A);
    }

    public void buildScene6B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene6B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You decided to fight him. You take some damage, and flee to the woods.");

        //create and position buttons
        Button button7C = new Button("Stop and yell for help.");
        button7C.setOnAction(e -> {
            clickSound.play();
            buildScene7A(primaryStage);
        });
        Button button7D = new Button("Keep running");
        button7D.setOnAction(e -> {
            clickSound.play();
            buildScene7A(primaryStage);
        });
        HBox hbox = new HBox(30, button7C, button7D);
        HBox.setMargin(button7C,new Insets(0, 20,50,20));
        HBox.setMargin(button7D,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene6B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene6B);
    }

    public void buildScene6C(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene6C = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You decided to fight him. You take some damage, and flee to the woods.");

        //create and position buttons
        Button button7C = new Button("Stop and yell for help.");
        button7C.setOnAction(e -> {
            clickSound.play();
            buildScene7A(primaryStage);
        });
        Button button7D = new Button("Keep running");
        button7D.setOnAction(e -> {
            clickSound.play();
            buildScene7A(primaryStage);
        });
        HBox hbox = new HBox(30, button7C, button7D);
        HBox.setMargin(button7C,new Insets(0, 20,50,20));
        HBox.setMargin(button7D,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene6C.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene6C);
    }

    public void buildScene6D(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene6D = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Everyone has run off in different directions. You're now left alone in the woods.");

        //create and position buttons
        Button button7C = new Button("Stop and yell for help");
        button7C.setOnAction(e -> {
            clickSound.play();
            buildScene7A(primaryStage);
        });
        Button button7D = new Button("Keep running");
        button7D.setOnAction(e -> {
            clickSound.play();
            buildScene7A(primaryStage);
        });
        HBox hbox = new HBox(30, button7C, button7D);
        HBox.setMargin(button7C,new Insets(0, 20,50,20));
        HBox.setMargin(button7D,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene6D.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene6D);
    }

    public void buildScene7A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene7A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("filler.");

        //create and position buttons
        Button button8A = new Button("Hiding filler");
        button8A.setPrefWidth(250);
        button8A.setOnAction(e -> {
            clickSound.play();
            buildScene7A(primaryStage);
        });
        HBox hbox = new HBox(button8A);
        HBox.setMargin(button8A,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene7A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene7A);
    }

    public void buildScene7B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene7B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Key filler.");

        //create and position buttons
        Button button8C = new Button("Be the hero and go alone");
        button8C.setPrefWidth(250);
        button8C.setOnAction(e -> {
            clickSound.play();
            buildScene7B(primaryStage);
        });
        HBox hbox = new HBox(button8C);
        HBox.setMargin(button8C,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene7B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene7B);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
