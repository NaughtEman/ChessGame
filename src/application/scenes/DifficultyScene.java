package application.scenes;

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


/**
 * This class represents the difficulty selection scene in the application.
 * @author dosum
 */

@SceneAnnotation(SceneType.DIFFICULTY)
public class DifficultyScene extends BaseScene {
	private SceneManager sceneManager;
	
	public DifficultyScene(SceneManager sceneManager, NavManager navManager) {
		super(sceneManager, navManager);
		this.sceneManager = sceneManager;
	}
	
	private Button createDifficultyButton(String text, Runnable action) {
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

	@Override
	protected void createContent() {
		// Title
		Label titleLabel = new Label("DIFFICULTY");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.DARKBLUE);
        
        // Menu buttons
        Button easy = createDifficultyButton("Easy", () -> sceneManager.showScene(SceneType.ARMY_FORMATION));
        Button medium = createDifficultyButton("Medium", () -> sceneManager.showScene(SceneType.ARMY_FORMATION));
        Button hard = createDifficultyButton("Hard", () -> sceneManager.showScene(SceneType.ARMY_FORMATION));
        Button exitButton = createDifficultyButton("Exit", () -> System.exit(0));
        
        // Layout
        VBox difficultyBox = new VBox(15);
        difficultyBox.setAlignment(Pos.CENTER);
        difficultyBox.getChildren().addAll(
            titleLabel,
            easy,
            medium,
            hard,
            exitButton
        );
        
        
        root.setCenter(difficultyBox);
        root.setPadding(new Insets(40));
        
        //scene = new Scene(root, 800, 600);
        
	}

}
