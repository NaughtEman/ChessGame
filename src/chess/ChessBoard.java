/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import chess.actors.Player;
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
    private Map<CoOrdinates, String> board = new ConcurrentHashMap();
    
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
        return !board.containsKey(cd);
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
            board.put(cd, piece.getFullName());
            return true;
        }
        return false; // Space is occupied
    }
    
    /**
     * Bulk update of the board at the start of the game
     */
    public void updateBoard(List<ChessPiece> soldiers){
        for(ChessPiece temp : soldiers){
            board.put(temp.getCordnts(), temp.getFullName());
        }
    }
    
    /**
     * Removes a piece from a given coordinate.
     * @param cd The coordinate to clear.
     */
    public synchronized void removePiece(CoOrdinates cd) {
        board.remove(cd);
    }
    
    /** 
     * Displays the board
    */
    public void displayBoard() {
        
        System.out.println("    A       B       C       D       E       F       G       H"); // Column labels
        System.out.println("  +-----------------------------------------------------------+");

        for (int row = 8; row >= 1; row--) {
            System.out.print(row + " | "); // Row label
            for (char col = 'A'; col <= 'H'; col++) {
                CoOrdinates coord = new CoOrdinates(col - 'A' + 1, row);
                String piece = board.getOrDefault(coord, " _"); // Get piece or empty
                System.out.printf("%-8s", piece); // Format spacing evenly
            }
            System.out.println("|");
        }

        System.out.println("  +-----------------------------------------------------------+");
}

    public Map<CoOrdinates, String> getBoard() {
        return board;
    }

    
}
