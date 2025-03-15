/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess.game;

import chess.ChessBoard;
import chess.actors.*;
import java.util.*;


/**
 *
 * @author dosum
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    
    GameManager gm = GameManager.getInstance();
    GameHelper gH = new GameHelper();
    
    Commander white = gm.getPlayer(true);
    Commander black = gm.getPlayer(false);
    
    ChessBoard board = ChessBoard.getInstance();
    
    Commander player;
    
    
    public static void main(String[] args) {
        Game game = new Game();
        game.gameLogic();
    }
    
    public void gameLogic() {
       int switchPlayer = 0;                      // Tracks turns

       while (gm.isGameRunning()) {
           player = (switchPlayer == 0) ? white : black;
           switchPlayer = (switchPlayer == 0) ? 1 : 0; // Toggle player turns
           
           board.displayBoard();
           
           gH.getUserInput(player.getpName() + "'s turn. Enter your move:");
           

           // Check for surrender
           if (gH.getWords().contains("surrender")) {
               player.surrender();
               System.out.print(player.getpName() + " has surrendered! ");
               break;
           }else
           
           if (gH.getWords().contains("fallen")) {
               player.printFallen();
               break;
           }else
           
           if (gH.getWords().contains("vanquished")) {
               player.printVanquished();
               break;
           }
           
           board.movePiece(gH.getWords().get(0), gH.getWords().get(1));
       }

       System.out.println("Game Over!");
   }
    
}
