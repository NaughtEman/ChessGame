package application.navigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.scenes.SceneManager;
import application.scenes.SceneType;
import javafx.scene.Scene;

/**
 * This class will manage navigation between different scenes in the application.
 * @author dosum
 */
public class NavManager {
	private List<SceneType> sceneHistory = Collections.synchronizedList(new ArrayList<>());
	private SceneManager sceneManager;
	
	public NavManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public void updateHistory(SceneType sceneType) {
		
		if (sceneHistory.isEmpty()) {
            sceneHistory.add(sceneType);
        }
		
		if(!sceneHistory.isEmpty()) {
			
			// Push current scene to history before navigating
	        if (sceneHistory.get(sceneHistory.size() - 1) != sceneType) {
	        	//sceneHistory.remove(sceneHistory.size() - 1);
	            sceneHistory.add(sceneType);
	        }
	        //showScene(sceneType);
		}
	}

	public boolean goBack() {
		
		if (sceneHistory.size() <= 1) {
            // Can't go back from the first scene
			System.out.println("No previous scene to go back to.");
            return false;
        }
		
		
		// Remove current scene
		sceneHistory.remove(sceneHistory.size() - 1);
		// Get previous scene
		SceneType previousScene = sceneHistory.get(sceneHistory.size() - 1);
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