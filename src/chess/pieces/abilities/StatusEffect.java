/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.abilities;

/**
 *Status effect object which shows how long the status should last
 * @author dosum
 */
public class StatusEffect {
    private EffectType type;
    //private String name;
    private int duration;
    private String id;

    public StatusEffect(int duration, EffectType type) {
        //this.name = name;
        this.duration = duration;
        this.type = type;
    }
    
    /*
    public String getName() {
        return name;
    }*/

    public int getDuration() {
        return duration;
    }
    
    public void updateDuration(){
        duration--;
    }

    public EffectType getType() {
        return type;
    }
    
    // Allows for easy duplicate of status
    public StatusEffect copy() {
        return new StatusEffect(this.duration, this.type);
    }

}
