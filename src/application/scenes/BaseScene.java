package application.scenes;

//BaseScene.java

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import application.navigation.NavManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public abstract class BaseScene {
 protected Scene scene;
 protected SceneManager sceneManager;
 protected NavManager navManager;
 protected BorderPane root;
 
 public BaseScene(SceneManager sceneManager, NavManager navManager) {
     this.sceneManager = sceneManager;
     this.navManager = navManager;
     this.root = new BorderPane();
     createScene();
 }
 
 protected void createScene() {
     // Create navigation bar with back button
     HBox navBar = createNavigationBar();
     root.setTop(navBar);
     
     // Let subclasses implement their specific content
     createContent();
     //setupKeyboardShortcuts();
     scene = new Scene(root, 800, 600);
     applyStyles();
 }
 
 protected HBox createNavigationBar() {
     HBox navBar = new HBox();
     navBar.setAlignment(Pos.TOP_LEFT);
     navBar.setPadding(new Insets(10));
     navBar.setStyle("-fx-background-color: rgba(0,0,0,0.1);");
     navBar.getStyleClass().add("nav-bar");
     
     // Only add back button if we can go back
     if (navManager.canGoBack()) {
         Button backButton = createBackButton();
         navBar.getChildren().add(backButton);
     }
     
     // Add additional navigation items if needed
     addNavigationItems(navBar);
     
     return navBar;
 }
 
 protected Button createBackButton() {
     Button backButton = new Button("← Back");
     backButton.getStyleClass().add("nav-button");
     backButton.getStyleClass().add("back-button");
     backButton.setOnAction(e -> navManager.goBack());
     return backButton;
 }
 
 protected void setupKeyboardShortcuts() {
	    scene.setOnKeyPressed(event -> {
	        switch (event.getCode()) {
	            case KeyCode.ESCAPE:
	                if (navManager.canGoBack()) {
	                	navManager.goBack();
	                }
	                break;
	            case KeyCode.BACK_SPACE:
	                if (navManager.canGoBack()) {
	                	navManager.goBack();
	                }
	                break;
	        }
	    });
	}
 
 // Override this in subclasses to add additional navigation items
 protected void addNavigationItems(HBox navBar) {
     // Can be overridden by subclasses
 }
 
 // Abstract methods for subclasses to implement
 protected abstract void createContent();
 protected abstract void applyStyles();
 
 public Scene getScene() {
     return scene;
 }
}