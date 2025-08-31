/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess.game;

import chess.players.General;
import chess.battlefield.ChessBoard;
import java.util.*;
import java.util.concurrent.*;



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
    
    General white = gm.getPlayer(true);
    General black = gm.getPlayer(false);
    
    ChessBoard board = ChessBoard.getInstance();
    
    General player;
    
    private String mode = "";
    Difficulty dif = new Difficulty();
    
    int switchPlayer = 0;
    private volatile boolean playerMoved = false;
    
    public static void main(String[] args) {
        Game game = new Game();
        game.selectMode();
        game.startGame();
    }
    
    public void startGame() {
        gameLogic();
    }
    
    public void gameLogic() {
       while (gm.isGameRunning()) {
            player = (switchPlayer == 0) ? white : black;
            playerMoved = false;  // Reset move status
            
            board.displayBoard();
            
            boolean response = gH.getUserInput(player.getpName() + "'s turn. Enter your move:", dif.getTime(mode));
            
            if (!response) {
                System.out.println(player.getpName() + " missed their turn!");
                switchTurn();
                continue;
            }

            processMove();  // Handle move input
            
            switchTurn();
            
       }

       System.out.println("Game Over!");
       stopGame();
   }
    
    private void processMove() {
        if (gH.getWords().contains("surrender")) {
            player.surrender();
            System.out.println(player.getpName() + " has surrendered! ");
            stopGame();
        } else if (gH.getWords().contains("fallen")) {
            
        } else if (gH.getWords().contains("vanquished")) {
            
        } else if (!gH.getWords().isEmpty()) {
            board.movePiece(gH.getWords().get(0), gH.getWords().get(1));
            player.incrementMoves();
            playerMoved = true;  // Mark as moved
        }
    }
    
    private void switchTurn() {
        switchPlayer = (switchPlayer == 0) ? 1 : 0;
    }

    private void stopGame() {
        //scheduler.shutdown();
    }

    private void selectMode() {
        mode = gH.getMode();
    }
    
}
