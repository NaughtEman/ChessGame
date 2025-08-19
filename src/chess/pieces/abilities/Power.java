/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.abilities;

/**
 *
 * @author dosum
 */
public class Power {
    private String name;
    private String description;
    private boolean isUltimate;
    private int cooldown;
    private int cooldownReset;
    //private int lastUsedTurn;
    //private int usesLeft;
    //private boolean active;
    //private BiConsumer<ChessPiece,ChessBoard> effect;

    public Power(String name, int cooldown, boolean isultimate) {
        this.name = name;
        this.isUltimate = isultimate;
        this.cooldown = cooldown;
        cooldownReset = cooldown;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUltimate() {
        return isUltimate;
    }

    public int getCooldown() {
        return cooldown;
    }
    
    public void updateCooldown(){
        cooldown--;
    }
    
    public void resetCooldown(){
        cooldown = cooldownReset;
    }
    
    public boolean charged(){
        if(cooldown == 0){
            return true;
        }
        return false;
    }
}
