/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess;

import chess.players.General;
import chess.game.GameManager;
import chess.battlefield.ChessBoard;
import chess.game.GameHelper;
import chess.pieces.CoOrdinates;
import chess.pieces.*;
import chess.pieces.abilities.PowerContext;
import chess.pieces.abilities.PowerManager;
import chess.pieces.dead.Mortavia;
import chess.pieces.enhanced.Spearman;
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

        General white = gm.getPlayer(true);
        General black = gm.getPlayer(false);
        
        ChessBoard board = ChessBoard.getInstance();
        
        //PowerManager.listPowerDetails(white);
        
        Mortavia mortavia = Mortavia.getInstance();

        board.displayBoard();
        
        /*
        PowerContext pc = new PowerContext(Direction.UP);
        //.useRegularPower(pc);
        
        ChessPiece piece = board.getPieceAt(new CoOrdinates(6,1));
        if(piece.special()){
            System.out.println(piece.getFullName());
        }
        
        Spearman spearMan = (Spearman) piece;
        
        spearMan.useUltimatePower(pc);
        
        board.displayBoard();
        
        mortavia.damnedSouls();
        */
    }
    
}
