/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.game;

import java.util.*;

/**
 *
 * @author dosum
 */
public class Difficulty {
    
    private Map<String, Integer> mode = new HashMap<>();

    public Difficulty() {
        mode.put("beginner", 120);
        mode.put("easy", 60);
        mode.put("medium", 30);
        mode.put("hard", 15);
        mode.put("blitz", 6);
        mode.put("bullet", 3);
    }
    
    public int getTime(String choice){
        return mode.get(choice.toLowerCase());
    }
    
}
