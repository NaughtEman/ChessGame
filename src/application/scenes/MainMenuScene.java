/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.scenes;


/**
 *
 * @author dosum
 */
// MainMenuScene.java

import application.navigation.NavManager;
import application.scenes.utility.SceneAnnotation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

@SceneAnnotation(SceneType.MAIN_MENU)
public class MainMenuScene extends BaseScene {
    private SceneManager sceneManager;
    
    public MainMenuScene(SceneManager sceneManager, NavManager navManager) {
    	super(sceneManager, navManager);
        this.sceneManager = sceneManager;
    }
    
    @Override
    protected void createContent() {
        // Title
        Label titleLabel = new Label("MAIN MENU");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.DARKBLUE);
        
        // Menu buttons
        Button playMode = createMenuButton("Play", () -> sceneManager.showScene(SceneType.PLAY));
        Button settingsButton = createMenuButton("Settings", () -> System.out.println("Settings clicked"));
        Button exitButton = createMenuButton("Exit", () -> System.exit(0));
        
        // Layout
        VBox menuBox = new VBox(15);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.getChildren().addAll(
            titleLabel,
            playMode,
            settingsButton,
            exitButton
        );
        
        //BorderPane root = new BorderPane();
        root.setCenter(menuBox);
        root.setPadding(new Insets(40));
        
        //scene = new Scene(root, 800, 600);
        //applyStyles();
    }
    
    private Button createMenuButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 16px; -fx-padding: 15px 40px; -fx-min-width: 200px;");
        button.setOnAction(e -> action.run());
        return button;
    }
    
    @Override
    protected void applyStyles() {
        scene.getStylesheets().add(getClass().getResource("/resources/css/scenes/menu.css").toExternalForm());
    }
    
    public Scene getScene() {
        return scene;
    }
}