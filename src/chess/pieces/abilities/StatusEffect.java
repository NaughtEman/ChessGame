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
    private String name;
    private int duration;
    private String type;

    public StatusEffect(String name, int duration, String type) {
        this.name = name;
        this.duration = duration;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
    
    public void updateDuration(){
        duration--;
    }

    public String getType() {
        return type;
    }
    
    // Allows for easy duplicate of status
    public StatusEffect copy() {
        return new StatusEffect(this.name, this.duration, this.type);
    }

}
