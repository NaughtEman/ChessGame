/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

/**
 *
 * @author dosum
 */
public abstract class Power {
    private String name;
    private String description;
    private boolean isUltimate;
    private int cooldown;
    //private int lastUsedTurn;
    private int usesLeft;
    //private boolean active;
    //private BiConsumer<ChessPiece,ChessBoard> effect;

    public Power(String name, boolean isUltimate, int cooldown, int usesLeft) {
        this.name = name;
        this.isUltimate = isUltimate;
        this.cooldown = cooldown;
        this.usesLeft = usesLeft;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIsUltimate() {
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
    
    public abstract void regPowerLogic();
    
    public abstract void ultPowerLogic();
    
}
