/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

/**
 *
 * @author dosum
 */
public class Power {
    private String name;
    private String description;
    private boolean isUltimate;
    private int cooldown;
    //private int lastUsedTurn;
    private int usesLeft;
    //private boolean active;
    //private BiConsumer<ChessPiece,ChessBoard> effect;

    public Power(String name, int cooldown, int usesLeft) {
        this.name = name;
        this.isUltimate = false;
        this.cooldown = cooldown;
        this.usesLeft = usesLeft;
    }
    
    public Power(String name, int cooldown) {
        this.name = name;
        this.isUltimate = true;
        this.cooldown = cooldown;
        this.usesLeft = 0;
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

    public int getUsesLeft() {
        return usesLeft;
    }
    
    public void updateCooldown(){
        cooldown--;
    }
    
    public void updateUsesLeft(){
        usesLeft--;
    }
    
}
