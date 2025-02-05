/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import chess.pieces.CoOrdinates;
import chess.pieces.ChessPiece;
import java.util.*;
import java.util.concurrent.*;

/**
 *
 * @author dosum
 */
public class ChessBoard {
    
    // Holds all chess pieces name (color and name of chess piece) and thier coordinates
    private Map<CoOrdinates, String> cbCordnts = new ConcurrentHashMap();
    
    private static ChessBoard instance;
    
    public static synchronized ChessBoard getInstance() {
        if (instance == null) {
            instance = new ChessBoard();
        }
        return instance;
    }
    
    /**
     * Checks if a coordinate is free on the board.
     * @param cd The coordinate to check.
     * @return True if free, false if occupied.
     */
    public boolean isFree(CoOrdinates cd) {
        return !cbCordnts.containsKey(cd);
    }
    
    // automatically fill cbCordnts with co-ordinates
    public void populateCBCordnts(){}
    
    /**
     * Adds a piece to the board if the space is free.
     * @param piece The chess piece to place.
     * @param cd The coordinate to place it at.
     * @return True if placement is successful, false if space is occupied.
     */
    public synchronized boolean placePiece(ChessPiece piece, CoOrdinates cd) {
        if (isFree(cd)) {
            cbCordnts.put(cd, piece.getFullName());
            return true;
        }
        return false; // Space is occupied
    }
    
    /**
     * Removes a piece from a given coordinate.
     * @param cd The coordinate to clear.
     */
    public synchronized void removePiece(CoOrdinates cd) {
        cbCordnts.remove(cd);
    }
    
    // Displays the board
    public void displayBoard(){
    // Display board logic
    }
    
}
