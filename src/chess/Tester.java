/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess;

import chess.actors.Commander;
import chess.actors.GameManager;
import chess.battlefield.ChessBoard;
import chess.game.GameHelper;
import chess.pieces.CoOrdinates;
import chess.pieces.*;
import chess.pieces.abilities.PowerContext;
import chess.pieces.enhanced.SuperKing;


/**
 *
 * @author dosum
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameManager gm = GameManager.getInstance();
        
        GameHelper gH = new GameHelper();

        Commander white = gm.getPlayer(true);
        Commander black = gm.getPlayer(false);
        
        ChessBoard board = ChessBoard.getInstance();

        board.displayBoard();
        
        PowerContext pc = new PowerContext(board.getPieceAt(new CoOrdinates(1,1)));
        //.useRegularPower(pc);
        if(board.getPieceAt(new CoOrdinates(5,1)).special()){
            System.out.println("This is a special piece");
        }
        
        SuperKing king = (SuperKing) board.getPieceAt(new CoOrdinates(5,1));
        
        king.useRegularPower(pc);
        
        board.displayBoard();
        
    }
    
}
