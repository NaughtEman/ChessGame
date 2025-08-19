/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.battlefield;

import chess.pieces.CoOrdinates;

/**
 *
 * @author dosum
 */
public class Cell {
    
    private int x;
    private int y;
    
    private CoOrdinates cd;
    
    private boolean isLocked;
    
    private boolean isTrap;
    
    private String effectName;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        
        cd.setCordnts(x, y);
    }

    public Cell(CoOrdinates cd) {
        this.cd = cd;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CoOrdinates getCd() {
        return cd;
    }

    public void setCd(CoOrdinates cd) {
        this.cd = cd;
    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean isIsTrap() {
        return isTrap;
    }

    public void setIsTrap(boolean isTrap) {
        this.isTrap = isTrap;
    }

    public String getEffectName() {
        return effectName;
    }

    public void setEffectName(String effectName) {
        this.effectName = effectName;
    }
    
    
    
}
