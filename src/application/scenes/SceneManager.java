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
    
    /**
     * Show a scene (cached or freshly created)
     */
    public void showScene(SceneType sceneType) {
    	navManager.updateHistory(sceneType);
        BaseScene scene = getOrCreateScene(sceneType);
        primaryStage.setScene(scene.getScene());
        
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
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
