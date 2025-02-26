/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

import chess.actors.GameEndListener;
import chess.actors.GameManager;

/**
 *
 * @author dosum
 */
public class King extends ChessPiece{

    public King( CoOrdinates initialPosition, boolean isWhite) {
        super("King", initialPosition, isWhite);
        movementLogic();
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        int[][] moves = { {1, 0}, {0, 1}, {1, 1}, 
            {-1, -1},{-1, 0}, {0, -1}, {-1, 1}, {1, -1}};

        for (int[] move : moves) {
            addAllowedMove(getCordnts().moveCustom(move[0], move[1]));
        }
    }
    
     @Override
    public void captured() {
        super.captured(); // Call the base method if necessary
        GameManager.getInstance().onGameEnd(this.getTeamColour() ? "Black" : "White");
    }
}
