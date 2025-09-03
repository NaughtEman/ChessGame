package application.scenes.play;

/**
 * This Scene displays the different play modes available to the user.
 * @author dosum
 */
import application.navigation.NavManager;
import application.scenes.BaseScene;
import application.scenes.SceneManager;
import application.scenes.SceneType;
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

@SceneAnnotation(SceneType.PLAY)
public class PlayScene extends BaseScene {
	private SceneManager sceneManager;
	
	public PlayScene(SceneManager sceneManager, NavManager navManager) {
		super(sceneManager, navManager);
		this.sceneManager = sceneManager;
	}
	
	@Override
	protected void createContent() {
		// Title
		Label titleLabel = new Label("SELECT PLAY MODE");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
		titleLabel.setTextFill(Color.DARKBLUE);
		
		// Menu buttons
		Button storyMode = createMenuButton("Story Mode", () -> sceneManager.showScene(SceneType.ARMY_FORMATION));
		Button online = createMenuButton("Online", () -> sceneManager.showScene(SceneType.ONLINE));
		Button againstAI = createMenuButton("AI", () -> sceneManager.showScene(SceneType.DIFFICULTY));
		Button localMultiplayer = createMenuButton("Local Multiplayer", () -> sceneManager.showScene(SceneType.ONLINE));
		
		// Layout
		VBox menuBox = new VBox(15);
		menuBox.setAlignment(Pos.CENTER);
		menuBox.getChildren().addAll(
			titleLabel,
			storyMode,
			againstAI,
			online,
			localMultiplayer
		);
		
		//BorderPane root = new BorderPane();
		root.setCenter(menuBox);
		root.setPadding(new Insets(40));
	}
	
	private Button createMenuButton(String text, Runnable action) {
		Button button = new Button(text);
		button.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
		button.setPrefWidth(250);
		button.setOnAction(e -> action.run());
		button.getStyleClass().add("menu-button");
		return button;
	}

	@Override
	protected void applyStyles() {
		// TODO Auto-generated method stub
		 scene.getStylesheets().add(getClass().getResource("/resources/css/scenes/menu.css").toExternalForm());
		
	}
	
	public Scene getScene() {
		return scene;
	}
	

}
