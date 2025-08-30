/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.dead;

import chess.pieces.ChessPiece;
import java.util.*;

/**
 * Tracks which pieces are dead 
 * @author dosum
 */
public class Mortavia {
    
    private static Mortavia instance;
    
    private Map<String, Psychopomp> mortavia = new HashMap<>();
    
    public static Mortavia getInstance(){
        
        if(instance == null){
            instance = new Mortavia();
        }
        
        return instance;
    }
    
    protected void addSoul(Psychopomp p){
        mortavia.put(p.getId(), p);
    }
    
    public ChessPiece getSoul(String id){
        Psychopomp p = mortavia.get(id);
        mortavia.remove(id);
        return p != null ? p.getChessPiece() : null;
        
    }
    
    public void damnedSouls(){
        for (Map.Entry<String, Psychopomp> entry : mortavia.entrySet()) {
            System.out.println(entry.getValue().getChessPiece().getFullName() + "\nDeath by " + entry.getValue().getDeathType());
        }
    }
    
}
