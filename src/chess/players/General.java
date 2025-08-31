/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.players;

/**
 *
 * @author dosum
 */
import chess.battlefield.Tactician;
import chess.battlefield.ChessBoard;
import java.util.*;
import chess.*;
import chess.pieces.*;
import chess.pieces.enhanced.*;

public class General { 
    
    /** Indicates whether the player is controlling the white pieces (true)
     * or black (false). */
    private boolean isWhite;
    
    private int movesNo;
    
    /** The name of the player. */
    String pName;
    
    /** List of currently active chess pieces belonging to this player. */
    private List <ChessPiece> army = new ArrayList<>();
    
    /** Reference to the chessboard, following a singleton pattern. */
    private static ChessBoard board = ChessBoard.getInstance();
    
    private Commander commander = new Commander();
    
    private CoOrdinates initialPosition;

    /**
     * Constructs a new Player with a specified team color and name.
     * Initializes the player's pieces on the board.
     * 
     * @param isWhite true if the player is playing as white, false for black.
     * @param pName the name of the player.
     */
    public General(boolean isWhite) {
        this.isWhite = isWhite;
        this.pName = isWhite ? "White" : "Black";
        buildArmy();
    }

    public String getpName() {
        return pName;
    }
    
    /**
     * Initializes and places the player's pieces on the chessboard.
     * This method sets up the initial game configuration.
     */
    private void buildArmy(){
        int mainRow = isWhite ? 1 : 8; // Main pieces are on row 1 (White) or 8 (Black)
        commander.build("C", "SuperKing", new CoOrdinates(1, mainRow), isWhite);
        
    }

    public List<ChessPiece> getSoldiers() {
        return army;
    }

    /**
     * Retrieves a list of chess pieces matching the specified name.
     * 
     * @param name the name of the chess piece.
     * @return a list of all matching chess pieces belonging to this player.
     */
    public List<ChessPiece> strToObject(String name) {
        
        List<ChessPiece> subList = new ArrayList<>();
        
        for(ChessPiece temp : army){
            
            if(temp.getName().equalsIgnoreCase(name)){
                subList.add(temp);
            }
        }
        return subList; 
    }
    
    /**
     * Prints all currently active chess pieces of this player.
     */
    public void printSoldiers(){
        for(ChessPiece temp : army){
            
            System.out.println(temp.toString());
        }
        System.out.println();
    }
    /*
    

    /**
     * Checks if a given coordinate is occupied by a piece of the same team.
     * 
     * @param cd the coordinate to check.
     * @return true if a friendly piece occupies the coordinate, false otherwise.
     */
    public boolean comradeOccupied(CoOrdinates cd) {
        
        for(ChessPiece temp : army){
            if(temp.sameCD(cd)){
                return true;
            }
        }
        return false;    
    }

    public boolean getIsWhite() {
        return isWhite;
    }
    
      /**
     * Sets the King to captured and notifies the GameManager
     */
    public void surrender() {
        if (!army.isEmpty()) {
            ChessPiece king = army.getLast();
            if (king instanceof King) {
                ((King) king).captured(); // Call the captured method on the King
            } else {
                System.out.println("Error: The last soldier is not the King.");
            }
        } else {
            System.out.println("No soldiers remaining to surrender.");
        }
    }
    
    public void incrementMoves(){
        movesNo++;
    }
    
    public int getMovesNo(){
        return movesNo;
    }

}
