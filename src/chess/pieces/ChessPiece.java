/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */

import chess.CoOrdinates;
import java.util.*;

public abstract class ChessPiece {
    
    private CoOrdinates cordnts;
    
    private boolean canKill = false;
    private boolean isAlive = true;
    
    private String name;
    
    private List<CoOrdinates> allowedMoves = new ArrayList<>();
    
    public ChessPiece(String name, CoOrdinates initialPosition) {
    this.name = name;
    this.cordnts = initialPosition;
    this.isAlive = true; // Assuming the piece starts alive
}


    // get its current co-ordinates
    public CoOrdinates getCordnts() {
        return cordnts;
    }

    // Update the chess piece co-ordinates
    public void updateCordnts(CoOrdinates cordnts) {
        this.cordnts = cordnts;
    }
    
    // get chess piece name (rook,pawn e.t.c)
    public String getName() {
        return name;
    }
    
    // set chess piece name (rook,pawn e.t.c)
    public void setName(String name) {
        this.name = name;
    }

    
    // returns a copy of the allowedMoves list
    public List<CoOrdinates> getAllowedMoves() {
        return new ArrayList<>(allowedMoves);
    }
    
    // Prints allowed moves
    public void showAllowedMoves() {
        
        if (!allowedMoves.isEmpty()) {
            System.out.println("Allowed Moves: " + allowedMoves);
        } else {
            System.out.println("No moves available");
        }
        
    } // close getCanMoveTo()

    // Adds a coordinate to the allowed moves list
    public void addAllowedMove(CoOrdinates cd) {
        this.allowedMoves.add(cd);
    }
    
    // reset allowedMoves list
    public void clearAllowedMoves() {
        this.allowedMoves.clear();
    }

    
    public abstract void movementLogic();
    
}
