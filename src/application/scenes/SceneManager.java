/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.scenes;



/**
 *
 * @author dosum
 */
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import java.lang.reflect.Constructor;

import application.navigation.NavManager;
import application.scenes.play.*;
import application.scenes.utility.ClassScanner;
import application.scenes.utility.SceneAnnotation;
import javafx.scene.*;
import javafx.stage.Stage;

public class SceneManager {
    private Stage primaryStage;
    private NavManager navManager;
    
    private final Map<SceneType, BaseScene> sceneCache = new EnumMap<>(SceneType.class);
    private final Map<SceneType, Class<? extends BaseScene>> sceneClasses = new EnumMap<>(SceneType.class);
    
    private LoginScene loginScene;
    private SignUpScene signUpScene;
    private MainMenuScene mainMenuScene;
    private GameScene gameScene;
    private DifficultyScene difficultyScene;
    private ArmyFormationScene armyFormationScene;
    private PlayScene playScene;
    private StoryModeScene storyModeScene;
    private Online onlineScene;
    
    public SceneManager(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.navManager = new NavManager(this);
        discoverScenes();
        
        /*
        this.loginScene = new LoginScene(this, navManager);
        this.mainMenuScene = new MainMenuScene(this, navManager);
        this.gameScene = new GameScene(this, navManager);
        this.difficultyScene = new DifficultyScene(this, navManager);
        this.armyFormationScene = new ArmyFormationScene(this, navManager);
        this.signUpScene = new SignUpScene(this, navManager);
        */
    }
    
    /**
     * Scan application.scenes (including subpackages) for @SceneAnnotation
     */
    private void discoverScenes() {
        List<Class<?>> classes = ClassScanner.getClasses("application.scenes");
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(SceneAnnotation.class) && BaseScene.class.isAssignableFrom(clazz)) {
                @SuppressWarnings("unchecked")
                Class<? extends BaseScene> sceneClass = (Class<? extends BaseScene>) clazz;
                SceneAnnotation annotation = clazz.getAnnotation(SceneAnnotation.class);
                SceneType type = annotation.value();

                // 🔒 Safeguard: detect duplicates
                if (sceneClasses.containsKey(type)) {
                    throw new IllegalStateException(
                        "Duplicate scene type detected: " + type +
                        " already mapped to " + sceneClasses.get(type).getName() +
                        ", attempted to map " + clazz.getName()
                    );
                }

                sceneClasses.put(type, sceneClass);
                System.out.println("Discovered scene: " + type + " -> " + clazz.getName());
            }
        }
    }
    /*
	public void showLoginScene() {
        loginScene = getOrCreateScene(loginScene, SceneType.LOGIN);
        primaryStage.setScene(loginScene.getScene());
    }
    
    public void showMainMenuScene() {
        mainMenuScene = getOrCreateScene(mainMenuScene, SceneType.MAIN_MENU);
        primaryStage.setScene(mainMenuScene.getScene());
    }
    
    public void showGameScene() {
        gameScene = getOrCreateScene(gameScene, SceneType.GAME);
		primaryStage.setScene(gameScene.getScene());
	}
    
    public void showDifficultyScene() {
        difficultyScene = getOrCreateScene(difficultyScene, SceneType.DIFFICULTY);
		primaryStage.setScene(difficultyScene.getScene());
    }
    
    public void showArmyFormationScene() {
        armyFormationScene = getOrCreateScene(armyFormationScene, SceneType.ARMY_FORMATION);
		primaryStage.setScene(armyFormationScene.getScene());
    }
    
    public void showSignUpScene() {
        signUpScene = getOrCreateScene(signUpScene, SceneType.SIGN_UP);
		primaryStage.setScene(signUpScene.getScene());
    }
    
    public void showPlayScene() {
		playScene = getOrCreateScene(playScene, SceneType.PLAY);
		primaryStage.setScene(playScene.getScene());
	}
    
    public void showStoryModeScene() {
		storyModeScene = getOrCreateScene(storyModeScene, SceneType.STORY_MODE);
		primaryStage.setScene(storyModeScene.getScene());
    }
    
    public void showOnlineScene() {
		onlineScene = getOrCreateScene(onlineScene, SceneType.ONLINE);
		primaryStage.setScene(onlineScene.getScene());
    }
    */
    /**
     * Show a scene (cached or freshly created)
     */
    public void showScene(SceneType sceneType) {
        BaseScene scene = getOrCreateScene(sceneType);
        primaryStage.setScene(scene.getScene());
        navManager.navigateTo(sceneType);
    }
    
    
    /**
     * Return cached scene or construct a new one
     */
    private BaseScene getOrCreateScene(SceneType sceneType) {
        return sceneCache.computeIfAbsent(sceneType, type -> {
            try {
                Class<? extends BaseScene> sceneClass = sceneClasses.get(type);
                if (sceneClass == null) {
                    throw new IllegalArgumentException("Scene not registered: " + type);
                }
                Constructor<? extends BaseScene> constructor =
                        sceneClass.getConstructor(SceneManager.class, NavManager.class);
                return constructor.newInstance(this, navManager);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create scene: " + type, e);
            }
        });
    }
    
    /*
    private <T extends BaseScene> T getOrCreateScene(T scene, SceneType type) {
        if (scene == null) {
            // Use factory pattern for cleaner creation
            scene = createScene(type);
        }
        navManager.navigateTo(type);
        return scene;
    }*/
    
    /*
    @SuppressWarnings("unchecked")
    public <T extends BaseScene> T createScene(SceneType type) {
        switch (type) {
            case LOGIN: return (T) new LoginScene(this, navManager);
            case MAIN_MENU: return (T) new MainMenuScene(this, navManager);
            case GAME: return (T) new GameScene(this, navManager);
            case DIFFICULTY: return (T) new DifficultyScene(this, navManager);
            case ARMY_FORMATION: return (T) new ArmyFormationScene(this, navManager);
            case SIGN_UP: return (T) new SignUpScene(this, navManager);
            case PLAY: return (T) new PlayScene(this, navManager);
            case STORY_MODE: return (T) new StoryModeScene(this, navManager);
            case ONLINE: return (T) new Online(this, navManager);
			 // Add other scenes as needed
            default: throw new IllegalArgumentException("Unknown scene type: " + type);
        }
    }*/
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
