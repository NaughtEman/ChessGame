/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.actors;

/**
 *
 * @author dosum
 */
import java.util.*;
import chess.*;
import chess.pieces.ChessPiece;

public class Player {
    
    boolean isWhite;
    
    String pName;
    
    List <ChessPiece> soldiers = new ArrayList<>();
    
    List <ChessPiece> vanquished = new ArrayList<>();

    public Player(boolean isWhite, String pName) {
        this.isWhite = isWhite;
        this.pName = pName;
    }
    
    
    
    
    
}
