/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.actors;

/**
 *
 * @author dosum
 */
public class GameManager {
    
    private static final GameManager instance = new GameManager();
    private final Commander whitePlayer = new Commander(true);
    private final Commander blackPlayer = new Commander(false);

    private GameManager() {} // Private constructor to enforce singleton

    public static GameManager getInstance() {
        return instance;
    }

    public Commander getPlayer(boolean isWhite) {
        return isWhite ? whitePlayer : blackPlayer;
    }
    
}
