 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.battlefield;

import chess.players.General;
import chess.pieces.CoOrdinates;
import chess.pieces.ChessPiece;
import chess.utilities.BoardObserver;
import java.util.*;
import java.util.concurrent.*;

/**
 *
 * @author dosum
 */
public class ChessBoard {
    
    // Holds all chess pieces name (color and name of chess piece) and thier coordinates
    private Map<CoOrdinates, ChessPiece> board = new ConcurrentHashMap();
    private List<BoardObserver> observers = new ArrayList<>();
    
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
            piece.updateCordnts(cd);
            board.put(cd, piece);
            return true;
        }
        return false; // Space is occupied
    }
    
    /**
     * Bulk update of the board at the start of the game
     */
    public void updateBoard(List<ChessPiece> soldiers){
        for(ChessPiece temp : soldiers){
            board.put(temp.getCordnts(), temp);
        }
    }
    
    /**
     * Removes a piece from a given coordinate.
     * @param cd The coordinate to clear.
     */
    public synchronized void removePieceAt(CoOrdinates cd) {
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
                ChessPiece piece = board.get(coord);
                String pieceSymbol = (piece != null) ? piece.getFullName() : " _";
                System.out.printf("%-8s", pieceSymbol);

            }
            System.out.println("|");
        }

        System.out.println("  +-----------------------------------------------------------+");
}

    public Map<CoOrdinates, ChessPiece> getBoard() {
        return board;
    }
    
    // TODO: Implement cordnt movement
    
    /**
    * Moves a piece from one location to another if the move is valid.
    * @param curr The current location of the piece.
    * @param dest The destination to move the selected piece.
    * @return true if the move was successful, false otherwise.
    */
   public void movePiece(String cur, String dest) {
       
       CoOrdinates current = new CoOrdinates(cur);
       CoOrdinates destination = new CoOrdinates(dest);
       
       ChessPiece piece = board.get(current);

       if (piece == null) {
           System.out.println("No piece found at " + current);
       }

       Tactician.movePiece(piece, destination);
       notifyObservers();
   }
   
   public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

   /**
    * Notify observers everytime a change has been made
    */
    private void notifyObservers() {
        for (BoardObserver obs : observers) {
            obs.onBoardChanged(this);
        }
    }
    
    public ChessPiece getPieceAt(int x, int y){
        CoOrdinates cd = new CoOrdinates(x, y);
        return board.get(cd);
    }
    
    public ChessPiece getPieceAt(CoOrdinates cd){
        return board.get(cd);
    }
    
    public void swap(CoOrdinates a, CoOrdinates b) {
        ChessPiece pieceA = board.get(a);
        ChessPiece pieceB = board.get(b);

        if (pieceA == null && pieceB == null) return;

        // Remove current mappings
        board.remove(a);
        board.remove(b);

        // Swap positions in the board map
        if (pieceA != null) {
            pieceA.updateCordnts(b);
            board.put(b, pieceA);
        }

        if (pieceB != null) {
            pieceB.updateCordnts(a);
            board.put(a, pieceB);
        }
    }

     
}
