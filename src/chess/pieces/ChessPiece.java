/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */

import chess.pieces.abilities.StatusEffect;
import chess.battlefield.ChessBoard;
import chess.actors.Commander;
import chess.pieces.abilities.EffectType;
import java.util.*;
import chess.pieces.dead.*;

public abstract class ChessPiece {
    
    private CoOrdinates cordnts;
    
    protected ChessBoard board = ChessBoard.getInstance();
    
    private int noOfKills;
    
    private boolean isFree = true; // if a piece is captured or not
    private boolean isWhite = true; // team color
    
    // Is this an enhanced piece
    private boolean isSpecial;
    
    // Are there overridden moves.
    private boolean hasMovementAltered = false;

    
    private String name; // piece name
     
    private String capturedBy; // stores the piece that captured this
    
    private String id;
    
    private List<CoOrdinates> allowedMoves = new ArrayList<>(); // stores all allowed moves
    
    private List<CoOrdinates> alteredMoves = new ArrayList<>();
    
    private List<StatusEffect> statusEffects = new ArrayList<>();
    
    /**
     * ChessPiece constructor
     * 
     */
    public ChessPiece(String name, CoOrdinates initialPosition, boolean isWhite, boolean isSpecial) {
        this.name = name;
        this.cordnts = initialPosition; 
        this.isWhite = isWhite;
        this.isSpecial = isSpecial;
        id = String.format("%s",name, initialPosition.toString());
    }
    
    public String getID(){
        return id;
    }
    
    public boolean special(){
        return isSpecial;
    }
    
    public void setSpecial(boolean bool){
        isSpecial = bool;
    }

    /**
     * Get this piece co-ordinates
     * @return cordnts
     */
    public CoOrdinates getCordnts() {
        return cordnts;
    }
    
    /**
     * Get this piece name
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set this piece name
     * @param name String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Returns a copy of the allowedMoves
     * @return allowedMoves
     */
    public List<CoOrdinates> getAllowedMoves() {
        if (hasMovementAltered) {
            return alteredMoves;
        }
        return allowedMoves;
    }
    
    /**
     * Used when another piece needs other than this has to change its movement
     * E.g Queen reg power.
     * @param moves 
     */
    public void setMovementOverride(List<CoOrdinates> moves) {
        alteredMoves.clear();
        this.alteredMoves = new ArrayList<>(moves);
        this.hasMovementAltered = true;
    }
    
    public boolean movementAltered(){
        return hasMovementAltered;
    }
    
    public void setMovementFlag(boolean altered){
        this.hasMovementAltered = altered;
    }

    
    /**
     * Prints allowed moves
     */
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
    
    /**
     * Adds co-ordinates to the allowed moves list
     * @param cd. Co-ordinate to be added
     */
    public void addAllowedMove(CoOrdinates cd) {
        ChessBoard board = ChessBoard.getInstance();

        // Prevent out-of-bounds or duplicate moves
        if (cd.isOOB() || allowedMoves.contains(cd)) {
            return;
        }

        // Check if the square is occupied
        ChessPiece occupyingPiece = board.getBoard().get(cd);
        if (occupyingPiece != null) {
            // Allow capturing enemy pieces but stop movement beyond this point
            if (!this.isAlly(occupyingPiece.getTeamColour())) {
                this.allowedMoves.add(cd);
            }
            return; // Stop adding moves beyond this point
        }

        // If the square is free, add it as a valid move
        this.allowedMoves.add(cd);
    }
    
    public void addAllowedMove(CoOrdinates cd, EffectType type) {
        if (type == EffectType.MOVEMENT) {
            alteredMoves.add(cd);
        } else {
            allowedMoves.add(cd);
        }
    }

    /** 
     * Removes a coordinate from the allowed moves list
     * @param cd 
     */
    public void removeAllowedMove(CoOrdinates cd) {
        allowedMoves.remove(cd);
    }
    
    /** 
     * Reset allowedMoves list
     */
    public void clearAllowedMoves() {
        this.allowedMoves.clear();
    }
    
    public boolean getTeamColour() {
        return isWhite;
    }
    
    /** 
     * Update the chess piece co-ordinates
     * @param cordnts 
     */
    public void updateCordnts(CoOrdinates cordnts) {
        this.cordnts = cordnts;
        movementLogic();
        // Use when incoporating AI or if performance becomes an issue. 
        // new Thread(this::movementLogic).start(); 
    }
    
    /**
     * Gets the piece that killed this 
     * @param cp the capturer 
     */
    public void deathNote(ChessPiece cp) {
        this.capturedBy = cp.getFullName();
        captured();   
    }

    public void captured() {
        this.isFree = false;
    }
    
    public String getCapturedBy() {
        return capturedBy;
    }
    
    public String getFullName(){
        return String.format( isWhite ? "W" + getName(): "B" + getName());
    }
    
    /**
     * Sends this piece to valhalla
     * @param commander 
     */
    public void goToValhalla(Commander commander){
        commander.quarterMaster(this);
    }

    /**
     * Movement logic where each piece implements its own 
     */
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
    
    /**
    * Checks if the piece is on the same team as the given color.
    * @param isWhite the boolean representing the player's team color
    * @return true if the piece is on the same team, false otherwise
    */
    public boolean isAlly(boolean team) {
        return this.getTeamColour() == team;
    }
    
    public void addStatusEffect(StatusEffect se){
        statusEffects.add(se);
    }
    
    public void incrementKills(){
        noOfKills++;
    }
    
    public int getKills(){
        return noOfKills;
    }
}
