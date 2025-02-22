/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */

import chess.actors.Commander;
import java.util.*;

public abstract class ChessPiece {
    
    private CoOrdinates cordnts;
    
    //private boolean canKill = false;
    private boolean isFree = true;
    boolean isWhite = true;
    
    private String name;
    
    private Commander player;
    
    private String capturedBy;
    
    private List<CoOrdinates> allowedMoves = new ArrayList<>();
    
    public ChessPiece(String name, CoOrdinates initialPosition, boolean isWhite) {
        this.name = name;
        this.cordnts = initialPosition; 
        this.isWhite = isWhite;
    }

    // get its current co-ordinates
    public CoOrdinates getCordnts() {
        return cordnts;
    }

    public Commander getPlayer() {
        return player;
    }

    public void setPlayer(Commander player) {
        this.player = player;
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
        return allowedMoves;
    }
    
    // Prints allowed moves
    public void showAllowedMoves() {
        
        if (!allowedMoves.isEmpty()) {
            System.out.println("Allowed Moves: ");
            for(CoOrdinates temp : allowedMoves){
                System.out.print( "[" + temp.getCordnts() + "]" + ",");
            }
            
            System.out.println();
            
        } else {
            System.out.println("No moves available");
        }
        
    } // close getCanMoveTo()

    // Adds a coordinate to the allowed moves list if its not Out Of Bounds
    public void addAllowedMove(CoOrdinates cd) {
        if (!cd.isOOB() && !allowedMoves.contains(cd)) {
            /*if(!player.comradeOccupied(cd)){
                this.allowedMoves.add(cd);
            }*/
            this.allowedMoves.add(cd);
        }
    }
    
    // Removes a coordinate from the allowed moves list
    public void removeAllowedMove(CoOrdinates cd) {
        allowedMoves.remove(cd);
    }
    
    // reset allowedMoves list
    public void clearAllowedMoves() {
        this.allowedMoves.clear();
    }
    
    public boolean getTeamColour() {
        return isWhite;
    }
    
    // Update the chess piece co-ordinates
    public void updateCordnts(CoOrdinates cordnts) {
        this.cordnts = cordnts;
        movementLogic();
        // Use when incoporating AI or if performance becomes an issue. 
        // new Thread(this::movementLogic).start(); 
    }
    
    public void deathNote(ChessPiece cp) {
        this.capturedBy = cp.getFullName();
        this.isFree = false;   
    }
    
    public String getCapturedBy() {
        return capturedBy;
    }
    
    public String getFullName(){
        return String.format( isWhite ? "W" + getName(): "B" + getName());
    }
    
    public void goToValhalla(){
        player.quarterMaster(this);
    }

    public abstract void movementLogic();
    
    @Override
    public String toString() {
        return name + " at " + cordnts.getCordnts() + " [" + (isWhite ? "White" : "Black") + "]";
    }
    
    public boolean sameCD(CoOrdinates cd){
        boolean same = false;
        
        int v = 2;
        
        if(this.getCordnts().getX() == cd.getX()){
            v -=1;
        }
        
        if(this.getCordnts().getY() == cd.getY()){
            v -=1;
        }
        
        if(v != 2){
            same = true;
        }
        
        return same;
    }
    
    public void printAllowedMoves(){
        System.out.println(getFullName() + " can move to:");
        for(CoOrdinates temp : allowedMoves){
            System.out.print("[" + temp.getCordnts() + "]," + " ");
        }
        System.out.println();
    }
}
