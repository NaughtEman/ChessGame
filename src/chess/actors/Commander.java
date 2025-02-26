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
import chess.pieces.*;

public class Commander {
    
    /** Indicates whether the player is controlling the white pieces (true)
     * or black (false). */
    private boolean isWhite;
    
    /** The name of the player. */
    String pName;
    
    /** List of currently active chess pieces belonging to this player. */
    private List <ChessPiece> soldiers = new ArrayList<>();
    
    /** List of opponent's pieces that this player has captured. */
    private List <ChessPiece> vanquished = new ArrayList<>();
    
    /** List of this player's fallen pieces that are no longer in play. */
    private List <ChessPiece> valhalla = new ArrayList<>();
    
    /** Reference to the chessboard, following a singleton pattern. */
    private static ChessBoard board = ChessBoard.getInstance();

    /**
     * Constructs a new Player with a specified team color and name.
     * Initializes the player's pieces on the board.
     * 
     * @param isWhite true if the player is playing as white, false for black.
     * @param pName the name of the player.
     */
    public Commander(boolean isWhite) {
        this.isWhite = isWhite;
        setPieces();
    }
    
    /**
     * Initializes and places the player's pieces on the chessboard.
     * This method sets up the initial game configuration.
     */
    private void setPieces(){
        
        int pawnRow = isWhite ? 2 : 7; // Pawns are on row 2 (White) or 7 (Black)
        int mainRow = isWhite ? 1 : 8; // Main pieces are on row 1 (White) or 8 (Black)

        // Place Pawns
        for (int i = 0; i < 8; i++) {
            soldiers.add(new Pawn(new CoOrdinates(i + 1, pawnRow), isWhite));
        }

        // Place Rooks
        soldiers.add(new Rook(new CoOrdinates(1, mainRow), isWhite));
        soldiers.add(new Rook(new CoOrdinates(8, mainRow), isWhite));

        // Place Knights
        soldiers.add(new Knight(new CoOrdinates(2, mainRow), isWhite));
        soldiers.add(new Knight(new CoOrdinates(7, mainRow), isWhite));

        // Place Bishops
        soldiers.add(new Bishop(new CoOrdinates(3, mainRow), isWhite));
        soldiers.add(new Bishop(new CoOrdinates(6, mainRow), isWhite));

        // Place Queen (on the matching color square)
        soldiers.add(new Queen(new CoOrdinates(4, mainRow), isWhite));

        // Place King
        soldiers.add(new King(new CoOrdinates(5, mainRow), isWhite));
    
        board.updateBoard(soldiers);
    }
    
    
    public void movePiece(String name, String crdnts) {
        List<ChessPiece> subList = strToObject(name);
        CoOrdinates targetCoords = new CoOrdinates(crdnts);
        ChessPiece selectedPiece = null;

        for (ChessPiece piece : subList) {
            if (Tactician.movePiece(piece, targetCoords)) {
                selectedPiece = piece;
                break;
            }
        }

        if (selectedPiece == null) {
            System.out.println("Invalid move: No valid move found for " + name + " to " + crdnts);
        }
    }

    public List<ChessPiece> getSoldiers() {
        return soldiers;
    }

    public List<ChessPiece> getVanquished() {
        return vanquished;
    }

    public List<ChessPiece> getValhalla() {
        return valhalla;
    }

    /**
     * Retrieves a list of chess pieces matching the specified name.
     * 
     * @param name the name of the chess piece.
     * @return a list of all matching chess pieces belonging to this player.
     */
    public List<ChessPiece> strToObject(String name) {
        
        List<ChessPiece> subList = new ArrayList<>();
        
        for(ChessPiece temp : soldiers){
            
            if(temp.getName().equalsIgnoreCase(name)){
                subList.add(temp);
            }
        }
        return subList; 
    }
    
    /**
     * Moves a fallen chess piece from the active soldiers list to the valhalla list.
     * This method is called when one of this player's pieces is captured.
     * 
     * @param piece the chess piece that has fallen.
     */
    public void quarterMaster(ChessPiece piece){
        soldiers.remove(piece);
        valhalla.add(piece);
    }
    
    /**
     * Prints all currently active chess pieces of this player.
     */
    public void printSoldiers(){
        for(ChessPiece temp : soldiers){
            
            System.out.println(temp.toString());
        }
        System.out.println();
    }
    
    /**
     * Prints all opponent pieces that this player has captured.
     */
    public void printVanquished(){
        for(ChessPiece temp : vanquished){
            
            System.out.println(temp.toString());
        }
        System.out.println();
    }
    
    /**
     * Prints all pieces that this player has lost (fallen soldiers).
     */
    public void printFallen(){
        for(ChessPiece temp : valhalla){
            
            System.out.println(temp.toString());
        }
        System.out.println();
    }

    /**
     * Checks if a given coordinate is occupied by a piece of the same team.
     * 
     * @param cd the coordinate to check.
     * @return true if a friendly piece occupies the coordinate, false otherwise.
     */
    public boolean comradeOccupied(CoOrdinates cd) {
        for(ChessPiece temp : soldiers){
            if(temp.sameCD(cd)){
                return true;
            }
        }
        return false;    
    }

    public boolean getIsWhite() {
        return isWhite;
    }
}
