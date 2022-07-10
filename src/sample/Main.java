package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class Main extends Application {

    private Label storyline;
    private AudioClip clickSound;
    private AudioClip hurtSound;
    private AudioClip powerSound;
    private MediaPlayer morning;
    private MediaPlayer night;
    private MediaPlayer end;
    private Player player;

    @Override
    public void start(Stage primaryStage) throws Exception{
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
        clickSound.setVolume(.2);

        hurtSound = new AudioClip(getClass().getResource("/sample/rec/hurtSound.mp3").toString());
        hurtSound.setVolume(.2);

        powerSound = new AudioClip(getClass().getResource("/sample/rec/powerSound.mp3").toString());
        powerSound.setVolume(.2);

        Media morningMusic = new Media(getClass().getResource("/sample/rec/morningSound.mp3").toString());
        morning = new MediaPlayer(morningMusic);
        morning.setVolume(.1);

        Media nightMusic = new Media(getClass().getResource("/sample/rec/nightSound.mp3").toString());
        night = new MediaPlayer(nightMusic);
        night.setVolume(.1);

        Media endMusic = new Media(getClass().getResource("/sample/rec/endSound.mp3").toString());
        end = new MediaPlayer(endMusic);
        end.setVolume(.3);
        end.stop();

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
        Button button1 = new Button("Continue.");
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
        Button button1 = new Button("Save it for later.");
        button1.setPrefWidth(250);
        button1.setOnAction(e -> {
            clickSound.play();
            buildScene1(primaryStage);
        });
        Button button2 = new Button("Eat it.");
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
        storyline.setText("The bus arrives and you meet Oliver, your camping director. He welcomes you but tells the group that this year they have to split everyone into two groups: one to stay in the tents and one to stay in the cabin.");
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
        storyline.setText("You decide to stay in the comfy cabin. Nighttime hits and everyone parts ways. But suddenly an hour later... the cabin jerks awake to the sounds of distant screams. What happened...");

        //create and position buttons
        Button button2 = new Button("Continue.");
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
        storyline.setText("You don't split from your friends. You'd rather suffer the heat with them than be bored in the cabin. Night falls. Even though there are multiple tents, you all pile into one tent to tell ghost stories. There is a loud snap of a branch outside. You all freeze.");

        //create and position buttons
        Button button3B = new Button("You grab your pocket knife.");
        button3B.setOnAction(e -> {
            clickSound.play();
            player.healthReduce();
            hurtSound.play();
            buildScene3B(primaryStage);
        });
        Button button3C = new Button("You grab the flashlight.");
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
        storyline.setText(" You hear your friends bang on the cabin door begging you to let them in. \"Someone's trying to kill us!!\"Opening it you find them covered in blood and tears. One of them is missing.");

        //create and position buttons
        Button button4A = new Button("Continue.");

        button4A.setOnAction(e-> {
            clickSound.play();
            powerSound.play();
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
        storyline.setText("You fish your pocket knife out of your belongings as quick as possible. It's small but sharp. Suddenly a hatchet rips through the cheap tent fabric burying itself in someones head. Everyone flees in the direction of the cabin (except your dead friend, of course). You can't see anything in the dark so you trip and scrape yourself up, but you make it back to the cabin.You reconvene with the other campers and tell them what happened.");

        //create and position buttons
        Button button4A = new Button("Continue.");
        button4A.setOnAction(e -> {
            clickSound.play();
            powerSound.play();
            buildScene4A(primaryStage);
        });
        HBox hbox = new HBox(button4A);
        HBox.setMargin(button4A,new Insets(0,20,50,20));
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
        storyline.setText("You quickly snatch the flashlight next to you. Suddenly a hatchet rips through the cheap tent fabric burying itself in your friend head. Everyone flees in the direction of the cabin (except your dead friend, of course). Using your flashlight you easily find your way to the cabin, screaming for help. You reconvene with the other campers and tell them what happened.");

        //create and position buttons
        Button button4A = new Button("Continue.");
        button4A.setOnAction(e -> {
            clickSound.play();
            powerSound.play();
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
        storyline.setText("The cabin lights suddenly go out. Someone has to go check the fuse box.");

        //create and position buttons
        Button button5A = new Button("Be the hero and go alone. You don't what any more people getting hurt or killed.");
        button5A.setPrefWidth(250);
        button5A.setOnAction(e -> {
            clickSound.play();
            buildScene5A(primaryStage);
        });
        Button button5B = new Button("Make everyone come with you to check. Safety in numbers.");
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
        storyline.setText("You creep outside of the cabin alone to look for the fuse box. You find it sparking with the metal torn up. One of the sparks illuminates up a human figure for a brief second. It looks like someone's been waiting for you.");

        //create and position buttons
        Button button6A = new Button("Run to the cabin with your tail between your legs.");
        button6A.setOnAction(e -> {
            clickSound.play();
            buildScene6A(primaryStage);
        });
        Button button6B = new Button("Tussle.");
        button6B.setOnAction(e -> {
            player.healthReduce();
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
        storyline.setText("You all stick together to search for the fuse box. You find it sparking with the metal torn up. One of the sparks illuminates up a human figure for a brief second before it plunges a hatchet into another one of your friends. Time to make a choice.");

        //create and position buttons
        Button button6C = new Button("Tussle as a community. Maybe you can overwhelm him.");
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
        storyline.setText("You sprint back to the cabin. It may be dark but you can tell it has been deserted. It seems everyone abandoned it and you.");

        //create and position buttons
        Button button7A = new Button("Hide. He could find you any second now.");
        button7A.setOnAction(e -> {
            clickSound.play();
            buildScene7A(primaryStage);
        });
        Button button7B = new Button("Look for car keys. Maybe you can escape this place.");
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
            buildScene7C(primaryStage);
        });
        Button button7D = new Button("Keep running.");
        button7D.setOnAction(e -> {
            player.healthReduce();
            hurtSound.play();
            clickSound.play();
            if(player.getHealth() ==0){
                buildSceneDeath(primaryStage);
            } else {
                buildScene7D(primaryStage);
            }
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
        storyline.setText("You decided to fight him, but the axe is too dangerous. You take some damage, and flee to the woods.");

        //create and position buttons
        Button button7C = new Button("Stop and yell for help.");
        button7C.setOnAction(e -> {
            clickSound.play();
            buildScene7C(primaryStage);
        });
        Button button7D = new Button("Keep running.");
        button7D.setOnAction(e -> {
            player.healthReduce();
            hurtSound.play();
            clickSound.play();
            if(player.getHealth() ==0){
                buildSceneDeath(primaryStage);
            } else {
                buildScene7D(primaryStage);
            }
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
        storyline.setText("Everyone has run off in different directions. No one wants to end up dead like the other two. In the chaos you end up left alone in the middle of the woods. Seems like you lost the killer.");

        //create and position buttons
        Button button7C = new Button("Stop and yell for help.");
        button7C.setOnAction(e -> {
            clickSound.play();
            buildScene7C(primaryStage);
        });
        Button button7D = new Button("Keep running.");
        button7D.setOnAction(e -> {
            player.healthReduce();
            hurtSound.play();
            clickSound.play();
            if(player.getHealth() ==0){
                buildSceneDeath(primaryStage);
            } else {
                buildScene7D(primaryStage);
            }
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
        storyline.setText("There's a killer on the loose; why put yourself in danger? Now where to hide...");

        //create and position buttons
        Button button8A = new Button("Under the bed.");
        button8A.setOnAction(e -> {
            clickSound.play();
            buildScene8A(primaryStage);
        });
        Button button8B = new Button("Behind the door.");
        button8B.setOnAction(e -> {
            clickSound.play();
            buildScene8B(primaryStage);
        });
        HBox hbox = new HBox(30, button8A, button8B);
        HBox.setMargin(button8A,new Insets(0, 20,50,20));
        HBox.setMargin(button8B,new Insets(0, 20,50,20));
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
        storyline.setText("You need to escape and the fastest way out is by car. You remember Oliver droning on about how nice his car was. It's gotta be around here somewhere. Where do you check for the keys?");

        //create and position buttons
        Button button8C = new Button("Check the nightstand.");
        button8C.setOnAction(e -> {
            player.healthReduce();
            player.healthReduce();
            hurtSound.play();
            clickSound.play();
            buildScene8C(primaryStage);
        });
        Button button8D = new Button("Check the coats on the coat-rack.");
        button8D.setOnAction(e -> {
            clickSound.play();
            buildScene8D(primaryStage);
        });
        HBox hbox = new HBox(30, button8C, button8D);
        HBox.setMargin(button8C,new Insets(0, 20,50,20));
        HBox.setMargin(button8D,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene7B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene7B);
    }

    public void buildScene7C(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene7C = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("The trees begin to rustle. Oliver bursts out covered in dirt and scratches. \"I don't know where the others went but I have a car nearby. We can get out of here.\" What do you do now that you've found help?");

        //create and position buttons
        Button button9D = new Button("Go to the car together to escape.");
        button9D.setOnAction(e -> {
            clickSound.play();
            buildScene9D(primaryStage);
        });
        Button button9E = new Button("Look for your friends.");
        button9E.setOnAction(e -> {
            clickSound.play();
            buildScene9E(primaryStage);
        });
        HBox hbox = new HBox(30, button9D, button9E);
        HBox.setMargin(button9D,new Insets(0, 20,50,20));
        HBox.setMargin(button9E,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene7C.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene7C);
    }

    public void buildScene7D(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene7D = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You run into a bear trap. Luckily you never skip leg day so you manage to break free, but still damaged. You eventually encounter an old fire-tower. Surely there could be something helpful up there, but the ladder rungs look old.");

        //create and position buttons
        Button button9F = new Button("Risk the climb up anyway.");
        button9F.setOnAction(e -> {
            player.healthReduce();
            player.healthReduce();
            hurtSound.play();
            clickSound.play();
            if(player.getHealth() ==0){
                buildSceneDeath(primaryStage);
            } else {
                buildScene9F(primaryStage);
            }
        });
        Button button9G = new Button("The risk is too high. Give up. ");
        button9G.setOnAction(e -> {
            clickSound.play();
            buildScene9G(primaryStage);
        });
        HBox hbox = new HBox(30, button9F, button9G);
        HBox.setMargin(button9F,new Insets(0, 20,50,20));
        HBox.setMargin(button9G,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene7D.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene7D);
    }

    public void buildScene8A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene8A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Heavy thuds fade into the background as the killer walks away.");

        //create and position buttons
        Button button9D = new Button("Continue.");
        button9D.setOnAction(e -> {
            clickSound.play();
            buildScene9A(primaryStage);
        });
        HBox hbox = new HBox(button9D);
        HBox.setMargin(button9D,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene8A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene8A);
    }

    public void buildScene8B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene8B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Heavy thuds grow louder as the killer approaches.");

        //create and position buttons
        Button button9A = new Button("Continue.");
        button9A.setPrefWidth(250);
        button9A.setOnAction(e -> {
            clickSound.play();
            buildScene9A(primaryStage);
        });
        HBox hbox = new HBox(button9A);
        HBox.setMargin(button9A,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene8B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene8B);
    }

    public void buildScene8C(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene8C = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("The nightstand is filled with peanuts. Unfortunately, you're deathly allergic. Good thing you didn't eat any though. Maybe you should check the coats instead.");

        //create and position buttons
        Button button8D = new Button("Check the coats.");
        button8D.setPrefWidth(250);
        button8D.setOnAction(e -> {
            clickSound.play();
            buildScene8D(primaryStage);
        });
        HBox hbox = new HBox(button8D);
        HBox.setMargin(button8D,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene8C.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene8C);
    }

    public void buildScene8D(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene8D = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You frantically toss the jackets around to find the car keys. You found them! Lucky you.");

        //create and position buttons
        Button button9D = new Button("Go to the car.");
        button9D.setPrefWidth(250);
        button9D.setOnAction(e -> {
            clickSound.play();
            buildScene9D(primaryStage);
        });
        HBox hbox = new HBox(button9D);
        HBox.setMargin(button9D,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene8D.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene8D);
    }

    public void buildScene9A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene9A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("The door is a screen door. Not such a great hiding spot. You are found and quickly taken care of.");

        //create and position buttons
        Button button9E = new Button("Try Again.");
        button9E.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button9E);
        HBox.setMargin(button9E,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene9A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene9A);
    }

    public void buildScene9B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene9B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You help Oliver fight off the killer, but not without taking some damage. You bought yourselves enough time to start the car, but the killer won't stay down for long. Suddenly, you see your friends running in from the treeline screaming for you to wait. You can quickly tell there's not enough room for everyone.");

        //create and position buttons
        Button button10A = new Button("You can't leave them. Wait for your friends.");
        button10A.setOnAction(e -> {
            clickSound.play();
            buildScene10A(primaryStage);
        });
        Button button10B = new Button("There's no time to waste. Drive off with only Oliver.");
        button10B.setOnAction(e -> {
            clickSound.play();
            buildScene10B(primaryStage);
        });
        HBox hbox = new HBox(30, button10A, button10B);
        HBox.setMargin(button10A,new Insets(0, 20,50,20));
        HBox.setMargin(button10B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);
        //health
        player.checkHealth(layout);

        scene9B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene9B);
    }

    public void buildScene9C(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene9C = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You leave Oliver to die. He's the perfect distraction for you to make your getaway. You start the engine. Suddenly, your friends running in from the treeline screaming for you to wait. You're relieved to see them alive, but there's no way there's enough room for everyone");

        //create and position buttons
        Button button10C = new Button("You can't leave them. Wait for your friends.");
        button10C.setOnAction(e -> {
            hurtSound.play();
            clickSound.play();
            buildScene10C(primaryStage);
        });
        Button button10D = new Button("There's no time to waste. Drive off alone.");
        button10D.setOnAction(e -> {
            clickSound.play();
            buildScene10D(primaryStage);
        });
        HBox hbox = new HBox(30, button10C, button10D);
        HBox.setMargin(button10C,new Insets(0, 20,50,20));
        HBox.setMargin(button10D,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);
        //health
        player.checkHealth(layout);

        scene9C.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene9C);
    }

    public void buildScene9D(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene9D = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You quickly make your way to the car to escape. You find the car, but so has the killer. Luckily, Oliver managed to find his way here too; the two begin to struggle in a fight.");

        //create and position buttons
        Button button9B= new Button("Help Oliver.");
        button9B.setOnAction(e -> {
            player.healthReduce();
            hurtSound.play();
            clickSound.play();
            if(player.getHealth() ==0){
                buildSceneDeath(primaryStage);
            } else {
                buildScene9B(primaryStage);
            }
        });
        Button button9C = new Button("Leave him. He's the perfect bait.");
        button9C.setOnAction(e -> {
            clickSound.play();
            buildScene9C(primaryStage);
        });
        HBox hbox = new HBox(30, button9B, button9C);
        HBox.setMargin(button9B,new Insets(0, 20,50,20));
        HBox.setMargin(button9C,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene9D.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene9D);
    }

    public void buildScene9E(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene9E = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You decide to go look for your friends. Where should you check?");

        //create and position buttons
        Button button12A = new Button("The campground bathhouse. It seems likely the safest remaining camp structure.");
        button12A.setOnAction(e -> {
            clickSound.play();
            buildScene12A(primaryStage);
        });
        Button button12B = new Button("Tent site. It's a little far from what you can remember of the camp map, but how likely would it be for the killer to go there again?");
        button12B.setOnAction(e -> {
            clickSound.play();
            buildScene12B(primaryStage);
        });
        HBox hbox = new HBox(30, button12A, button12B);
        HBox.setMargin(button12A,new Insets(0, 20,50,20));
        HBox.setMargin(button12B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene9E.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene9E);
    }

    public void buildScene9F(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene9F = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("The old ladder rungs give away below you causing you to plummet to the ground.");

        //create and position buttons
        Button button10E = new Button("Try again.");
        button10E.setOnAction(e -> {
            clickSound.play();
            player.setFlareTrue();
            buildScene10E(primaryStage);
        });
        Button button10F = new Button("Move on.");
        button10F.setOnAction(e -> {
            clickSound.play();
            buildScene10F(primaryStage);
        });
        HBox hbox = new HBox(30, button10E, button10F);
        HBox.setMargin(button10E,new Insets(0, 20,50,20));
        HBox.setMargin(button10F,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene9F.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene9F);
    }

    public void buildScene9G(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene9G = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("No time to stop, the killer could be close.");

        //create and position buttons
        Button button11A = new Button("Go find your friends.");
        button11A.setOnAction(e -> {
            clickSound.play();
            buildScene11A(primaryStage);
        });
        Button button11B = new Button("Find somewhere safe in the area to hide.");
        button11B.setOnAction(e -> {
            clickSound.play();
            buildScene11B(primaryStage);
        });
        HBox hbox = new HBox(30, button11A, button11B);
        HBox.setMargin(button11A,new Insets(0, 20,50,20));
        HBox.setMargin(button11B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene9G.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene9G);
    }

    public void buildScene10A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene10A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("escape");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Your friends run up to the car. Luckily Oliver is able to use his years of leadership to help everyone cram properly. Once everyone is inside, you speed away. You managed to save everyone's life.");

        //create and position buttons
        Button button10A = new Button("Try Again.");
        button10A.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button10A);
        HBox.setMargin(button10A,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });


        //health
        player.checkHealth(layout);

        scene10A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene10A);
    }

    public void buildScene10B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene10B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("end");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You drive off with Oliver. Saving the camp director is enough, the rest of them will figure it out...or not. You witness the killer taking them out one by one in the rear-view.");

        //create and position buttons
        Button button10B = new Button("Try Again.");
        button10B.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button10B);
        HBox.setMargin(button10B,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });


        //health
        player.checkHealth(layout);

        scene10B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene10B);
    }

    public void buildScene10C(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene10C = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("end");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Your friends frantically approach the car. In the chaos you are thrown out by someone stronger to make more room. The car churns up dust as it speeds away, blinding you moments before the killer finishes you off.");

        //create and position buttons
        Button button10C = new Button("Try Again.");
        button10C.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button10C);
        HBox.setMargin(button10C,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });


        //health
        player.setHealth(0);
        player.checkHealth(layout);

        scene10C.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene10C);
    }

    public void buildScene10D(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene10D = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("end");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("It's too dangerous to wait so you drive away. A couple of your friends attempt to get in the car, but you don't stop. Some of them are run over in the process. But at least you made it out.");


        //create and position buttons
        Button button10D = new Button("Try Again.");
        button10D.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button10D);
        HBox.setMargin(button10D,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });


        //health
        player.checkHealth(layout);

        scene10D.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene10D);
    }

    public void buildScene10E(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene10E = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You successfully make it to the top and find a flare gun. The sky flashes bright red as you fire the flare to signal for help. Hopefully, the authorities see it. What's your next step?");

        //create and position buttons
        Button button11A = new Button("Go find your friends.");
        button11A.setOnAction(e -> {
            clickSound.play();
            buildScene11A(primaryStage);
        });
        Button button11B = new Button("Find somewhere safe in the area to hide.");
        button11B.setOnAction(e -> {
            clickSound.play();
            buildScene11B(primaryStage);
        });
        HBox hbox = new HBox(30, button11A, button11B);
        HBox.setMargin(button11A,new Insets(0, 20,50,20));
        HBox.setMargin(button11B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene10E.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene10E);
    }

    public void buildScene10F(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene10F = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You give up. It's too dangerous to risk any more injuries; time to move on.");

        //create and position buttons
        Button button11A = new Button("Go find your friends.");
        button11A.setOnAction(e -> {
            clickSound.play();
            buildScene11A(primaryStage);
        });
        Button button11B = new Button("Find somewhere safe in the area to hide.");
        button11B.setOnAction(e -> {
            clickSound.play();
            buildScene11B(primaryStage);
        });
        HBox hbox = new HBox(30, button11A, button11B);
        HBox.setMargin(button11A,new Insets(0, 20,50,20));
        HBox.setMargin(button11B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene10F.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene10F);
    }

    public void buildScene11A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene11A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You should meet back up with your friends. Where do you look?");

        //create and position buttons
        Button button12A = new Button("The campground bathhouse. It seems likely the safest remaining camp structure.");
        button12A.setOnAction(e -> {
            clickSound.play();
            buildScene12A(primaryStage);
        });
        Button button12B = new Button("The tents. It's a little far from what you can remember of the camp map, but how likely would it be for the killer to go there again?");
        button12B.setOnAction(e -> {
            clickSound.play();
            buildScene12B(primaryStage);
        });
        HBox hbox = new HBox(30, button12A, button12B);
        HBox.setMargin(button12A,new Insets(0, 20,50,20));
        HBox.setMargin(button12B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);


        //health
        player.checkHealth(layout);

        scene11A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene11A);
    }

    public void buildScene11B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene11B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Maybe there's a good place to hide around here. You go a little further and find a worn path. The trail leads to a small town with a sign that says \"Welcome to Starville\".");

        //create and position buttons
        Button button12C = new Button("Enter Starville in search of help.");
        button12C.setOnAction(e -> {
            clickSound.play();
            buildScene12C(primaryStage);
        });
        Button button12D = new Button("Walk away from the creepy town in the middle of the woods.");
        button12D.setOnAction(e -> {
            clickSound.play();
            buildScene12A(primaryStage);
        });
        HBox hbox = new HBox(30, button12C, button12D);
        HBox.setMargin(button12C,new Insets(0, 20,50,20));
        HBox.setMargin(button12D,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene11B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene11B);
    }

    public void buildScene12A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene12A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You find your friends hiding in the bathhouse and join them. Turns out none of them were able to find a way to call for help. They were hoping you had done something.");

        //create and position buttons
        Button button13DE = new Button("Continue.");
        if(player.getFlare()){
            button13DE.setOnAction(e -> {
                clickSound.play();
                buildScene13D(primaryStage);
            });
        } else{
            button13DE.setOnAction(e -> {
                clickSound.play();
                hurtSound.play();
                buildScene13E(primaryStage);
            });
        }
        HBox hbox = new HBox(button13DE);
        HBox.setMargin(button13DE,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene12A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene12A);
    }

    public void buildScene12B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene12B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You head in the direction you thought was right, but you find a path leading to a small town with a sign that says \"Welcome to Starville\".");

        //create and position buttons
        Button button13A = new Button("Enter Starville in search of help.");
        button13A.setOnAction(e -> {
            hurtSound.play();
            clickSound.play();
            buildScene13A(primaryStage);
        });
        Button button13B = new Button("Walk away and and look for your friends elsewhere.");
        button13B.setOnAction(e -> {
            clickSound.play();
            buildScene12A(primaryStage);
        });
        HBox hbox = new HBox(30, button13A, button13B);
        HBox.setMargin(button13A,new Insets(0, 20,50,20));
        HBox.setMargin(button13B,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene12B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene12B);
    }

    public void buildScene12C(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene12C = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You scream for help but no one answers. Maybe the town is abandoned. You approach one of the homes and pick up a rock.");

        //create and position buttons
        Button button13B = new Button("Break your way in.");
        button13B.setOnAction(e -> {
            clickSound.play();
            buildScene13B(primaryStage);
        });
        Button button13C = new Button("Put the rock down.");
        button13C.setOnAction(e -> {
            hurtSound.play();
            clickSound.play();
            buildScene13C(primaryStage);
        });
        HBox hbox = new HBox(30, button13B, button13C);
        HBox.setMargin(button13B,new Insets(0, 20,50,20));
        HBox.setMargin(button13C,new Insets(0, 20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        //health
        player.checkHealth(layout);

        scene12C.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene12C);
    }

    public void buildScene13A(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene13A = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("end");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You enter looking for any help. Unfortunately, it is home to a cult that is not kind to strangers. You are sacrificed immediately.");

        //create and position buttons
        Button button13A = new Button("Try Again.");
        button13A.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button13A);
        HBox.setMargin(button13A,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });

        //health
        player.setHealth(0);
        player.checkHealth(layout);

        scene13A.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene13A);
    }

    public void buildScene13B(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene13B = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("end");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You enter the home unaware of the threat that lies inside. The locals don't appreciate strangers breaking and entering. A few steps in and you are shot.");

        //create and position buttons
        Button button13B = new Button("Try Again.");
        button13B.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button13B);
        HBox.setMargin(button13B,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });

        //health
        player.setHealth(0);
        player.checkHealth(layout);

        scene13B.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene13B);
    }

    public void buildScene13C(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene13C = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("escape");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("You hear a shout and turn around. Your friends have found you. They had managed to find an old radio and call for help. The police soon arrive and you all make it out alive.");

        //create and position buttons
        Button button13C = new Button("Try Again.");
        button13C.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button13C);
        HBox.setMargin(button13C,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });

        //health
        player.checkHealth(layout);

        scene13C.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene13C);
    }

    public void buildScene13D(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene13D = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("escape");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Luckily you were able to set off the flare. The police soon arrive and everyone is able to make it out safely.");

        //create and position buttons
        Button button13D = new Button("Try Again.");
        button13D.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button13D);
        HBox.setMargin(button13D,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });

        //health
        player.checkHealth(layout);

        scene13D.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene13D);
    }

    public void buildScene13E(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene scene13E = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("end");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("However, you were never able to get to the top of that distant fire tower. Maybe you could've found help up there. The killer eventually finds you all hiding. No one makes it out alive.");

        //create and position buttons
        Button button13E = new Button("Try Again.");
        button13E.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(button13E);
        HBox.setMargin(button13E,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });

        //health
        player.setHealth(0);
        player.checkHealth(layout);

        scene13E.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(scene13E);
    }

    public void buildSceneDeath(Stage primaryStage){
        Scene oldScene = primaryStage.getScene();
        BorderPane layout = new BorderPane();
        Scene sceneDeath = new Scene(layout, oldScene.getWidth(), oldScene.getHeight());
        layout.setId("nightSky");

        //change the story
        layout.setCenter(storyline);
        storyline.setText("Bad choice. You took too much damage and are no longer able to continue.");

        //create and position buttons
        Button buttonDeath = new Button("Try Again.");
        buttonDeath.setOnAction(e -> {
            clickSound.play();
            try {
                start(primaryStage);
            }catch(Exception d) {
                d.printStackTrace();
            }
        });
        HBox hbox = new HBox(buttonDeath);
        HBox.setMargin(buttonDeath,new Insets(0,20,50,20));
        hbox.setAlignment(Pos.CENTER);
        layout.setBottom(hbox);

        night.stop();
        end.play();
        end.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                end.seek(Duration.ZERO);
                end.play();
            }
        });

        //health
        player.checkHealth(layout);

        sceneDeath.getStylesheets().add("sample/rec/theme.css");
        primaryStage.setScene(sceneDeath);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
