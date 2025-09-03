package application.scenes.play;


/**
 * This Scene displays the online play options available to the user.
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

@SceneAnnotation(SceneType.ONLINE)
public class Online extends BaseScene {
	private SceneManager sceneManager;
	
	public Online(SceneManager sceneManager, NavManager navManager) {
		super(sceneManager, navManager);
		this.sceneManager = sceneManager;
	}
	
	@Override
	protected void createContent() {
		// Title
		Label titleLabel = new Label("ONLINE MODE");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
		titleLabel.setTextFill(Color.DARKBLUE);
		
		// Menu buttons
		Button joinGame = createMenuButton("Join Game", () -> System.out.println("Join Game selected"));
		Button hostGame = createMenuButton("Host Game", () -> System.out.println("Host Game selected"));
		Button backButton = createMenuButton("Back", () -> sceneManager.showScene(SceneType.PLAY));
		
		// Layout
		VBox menuBox = new VBox(15);
		menuBox.setAlignment(Pos.CENTER);
		menuBox.getChildren().addAll(
			titleLabel,
			joinGame,
			hostGame,
			backButton
		);
		
		root.setCenter(menuBox);
		root.setPadding(new Insets(40));
	}
	
	private Button createMenuButton(String text, Runnable action) {
		Button button = new Button(text);
		button.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
		button.setOnAction(e -> action.run());
		button.setMaxWidth(200);
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
