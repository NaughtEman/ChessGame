package application.navigation;

import java.util.ArrayList;
import java.util.List;

import application.scenes.SceneManager;
import application.scenes.SceneType;
import javafx.scene.Scene;

/**
 * This class will manage navigation between different scenes in the application.
 * @author dosum
 */
public class NavManager {
	private List<SceneType> sceneHistory = new ArrayList<>();
	private SceneManager sceneManager;
	//private SceneType sceneType;
	
	public NavManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public void navigateTo(SceneType sceneType) {
		
		if(!sceneHistory.isEmpty()) {
			
			// Push current scene to history before navigating
	        if (!sceneHistory.isEmpty() && sceneHistory.get(sceneHistory.size() - 1) != sceneType) {
	            sceneHistory.add(sceneType);
	        } else if (sceneHistory.isEmpty()) {
	            sceneHistory.add(sceneType);
	        }
	        showScene(sceneType);
		}
	}

	public boolean goBack() {
		
		if (sceneHistory.size() <= 1) {
            // Can't go back from the first scene
            return false;
        }
		
		
		// Remove current scene
		sceneHistory.remove(sceneHistory.size() - 1);
		// Get previous scene
		SceneType previousScene = sceneHistory.get(sceneHistory.size() - 2);
		showScene(previousScene);
		return true;
	}
	
	private void showScene(SceneType sceneType) {
		sceneManager.showScene(sceneType);
	}
	
	public void clearHistory() {
        sceneHistory.clear();
    }
    
    public boolean canGoBack() {
        return sceneHistory.size() > 1;
    }
}
