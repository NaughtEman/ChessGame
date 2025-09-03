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
 * This class represents the Army Formation scene in the application.
 * @author dosum
 */

@SceneAnnotation(SceneType.ARMY_FORMATION)
public class ArmyFormationScene extends BaseScene {
	private SceneManager sceneManager;
	
	public ArmyFormationScene(SceneManager sceneManager, NavManager navManager) {
		super(sceneManager, navManager);
		this.sceneManager = sceneManager;
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

	@Override
	protected void createContent() {
		// Title
        Label titleLabel = new Label("ARMY FORMATION");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.DARKBLUE);
        
        // Army Formation buttons
        Button save = createMenuButton("Save", () -> System.out.println("Save button clicked"));
        Button load = createMenuButton("Load", () -> System.out.println("Load clicked"));
        Button startGame = createMenuButton("Start Game", () -> sceneManager.showScene(SceneType.GAME));
        
        
        // Layout
        VBox menuBox = new VBox(15);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.getChildren().addAll(
            titleLabel,
            save,
        	load,
        	startGame,
			createMenuButton("Back", () -> navManager.goBack())
        );
        
        //BorderPane root = new BorderPane();
        root.setCenter(menuBox);
        root.setPadding(new Insets(40));
        
        //scene = new Scene(root, 800, 600);
        //applyStyles();
	}
}
