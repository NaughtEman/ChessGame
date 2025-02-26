/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.actors;

/**
 *
 * @author dosum
 */
public class GameManager implements GameEndListener{
    
    private static final GameManager instance = new GameManager();
    private boolean gameRunning = true;
    
    private final Commander whitePlayer = new Commander(true);
    private final Commander blackPlayer = new Commander(false);

    private GameManager() {} // Private constructor to enforce singleton

    public static GameManager getInstance() {
        return instance;
    }

    public Commander getPlayer(boolean isWhite) {
        return isWhite ? whitePlayer : blackPlayer;
    }
    
    @Override
    public void onGameEnd(String winner) {
        gameRunning = false;
        System.out.println("Game Over! " + winner + " wins!");
        // You could also implement GUI updates or stop the game loop here.
    }

    public boolean isGameRunning() {
        return gameRunning;
    }
    
}
