/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess;

import chess.actors.Commander;
import chess.actors.GameManager;
import chess.pieces.CoOrdinates;
import chess.pieces.*;


/**
 *
 * @author dosum
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ChessBoard board = ChessBoard.getInstance();
        
        GameManager gm = GameManager.getInstance();
        
        Commander pW = gm.getPlayer(true);
                
        Commander pB = gm.getPlayer(false);
        board.displayBoard();
        
        board.movePiece("A2", "A4");
        
        board.displayBoard();
        
        //pB.surrender();
        
        
    }
    
}
