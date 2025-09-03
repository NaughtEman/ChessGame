/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.scenes;


/**
 * LoginScene.java
 * @author dosum
 */
import application.navigation.NavManager;
import application.scenes.utility.SceneAnnotation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

@SceneAnnotation(SceneType.LOGIN)
public class LoginScene extends BaseScene {
    private SceneManager sceneManager;
    
    public LoginScene(SceneManager sceneManager, NavManager navManager) {
    	super(sceneManager, navManager);
        this.sceneManager = sceneManager;
    }
    
    @Override
    protected void createContent() {
        // Title
        Label titleLabel = new Label("SUPER CHESS");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.DARKBLUE);
        
        // Login form
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setMaxWidth(250);
        
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setMaxWidth(250);
        
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px;");
        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText()));

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px;");
        signUpButton.setOnAction(e -> handleSignUp());

        // Arrange login and signup buttons horizontally
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(loginButton, signUpButton);

        Button guestButton = new Button("Play as Guest");
        guestButton.setStyle("-fx-font-size: 12px; -fx-padding: 5px 15px;");
        guestButton.setOnAction(e -> handleGuestLogin());

        // Layout
        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER);
        formBox.getChildren().addAll(
            usernameLabel, usernameField,
            passwordLabel, passwordField,
            buttonBox, guestButton
        );
        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(titleLabel, formBox);
        root.setCenter(mainBox);
        root.setPadding(new Insets(40));
    }
    
    private void handleSignUp() {

		System.out.println("Sign Up clicked");
		sceneManager.showScene(SceneType.SIGN_UP);
	}

	private void handleLogin(String username, String password) {
        // TODO: Add actual authentication logic
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Please enter both username and password");
            return;
        }
        System.out.println("Login attempted for: " + username);
        sceneManager.showScene(SceneType.MAIN_MENU);
    }
    
    private void handleGuestLogin() {
        System.out.println("Guest login selected");
        sceneManager.showScene(SceneType.MAIN_MENU);
    }
    
    @Override
    protected void applyStyles() {
        scene.getStylesheets().add(getClass().getResource("/resources/css/scenes/login.css").toExternalForm());
    }
    
    public Scene getScene() {
        return scene;
    }

	
    
   
}