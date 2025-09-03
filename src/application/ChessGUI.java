/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import application.scenes.SceneManager;
import application.scenes.SceneType;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChessGUI extends Application{
    private Stage primaryStage;
    private SceneManager sceneManager;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.sceneManager = new SceneManager(primaryStage);
        // 1. Here you will create your GUI (chess board, etc.)
        // 2. You will create an instance of your existing Game class
        //    and connect it to your GUI.
        //ChessGUI myRunningGame = new ChessGUI(); // Instantiate your logic
        // ... (Your GUI setup code will go here, using myRunningGame) ...
        
        Image icon = new Image(getClass().getResourceAsStream("/resources/images/chessIcon.png"));
        
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Super Chess");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        
        sceneManager.showScene(SceneType.LOGIN);
        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // This bootstraps JavaFX
    }
}