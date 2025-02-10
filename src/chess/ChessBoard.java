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
        System.out.println("  A  B  C  D  E  F  G  H"); // Column labels

        for (int y = 8; y >= 1; y--) { // Looping from 8 to 1 (since chessboards are displayed top-down)
            System.out.print(y + " "); // Row number at the start of each row

            for (int x = 1; x <= 8; x++) {
                CoOrdinates tempCD = new CoOrdinates(x, y);

                if (board.containsKey(tempCD)) {
                    System.out.print(board.get(tempCD) + " "); // Print piece name
                } else {
                    System.out.print("_ "); // Empty space
                }
            }
            System.out.println(); // Move to the next row
        }
    }

    
}
