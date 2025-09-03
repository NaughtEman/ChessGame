package application.scenes;


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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * SignUpScene class to handle user registration interface.
 * @author dosum
 */

@SceneAnnotation(SceneType.SIGN_UP)
public class SignUpScene extends BaseScene {
    private SceneManager sceneManager;
    
    public SignUpScene(SceneManager sceneManager, NavManager navManager) {
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
        
        Button signupButton = new Button("Sign Up");
        signupButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px;");
        signupButton.setOnAction(e -> handleSignUp(usernameField.getText(), passwordField.getText()));
        
        // Layout
        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER);
        formBox.getChildren().addAll(
            usernameLabel, usernameField,
            passwordLabel, passwordField,
            signupButton
        );
        
        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(titleLabel, formBox);
        
        //BorderPane root = new BorderPane();
        root.setCenter(mainBox);
        root.setPadding(new Insets(40));
        
        //scene = new Scene(root, 800, 600);
        //applyStyles();
    }
    
    /*
     * Communicates with database to register a new user.
     */
    private void handleSignUp(String username, String password) {
		// TODO This method should handle user registration logic
		System.out.println("Sign Up attempted with Username: " + username + " Password: " + password);
		// For now, just navigate to main menu
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
