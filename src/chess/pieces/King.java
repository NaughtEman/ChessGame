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
        
        int x = getCordnts().getX();
        int y = getCordnts().getY();

        for (Direction dir : Direction.values()) {
            
            x += dir.dx();
            y += dir.dy();
            
            CoOrdinates cd = new CoOrdinates(x, y);
            
            if(!cd.isOOB()){
                addAllowedMove(cd);
            }
            
        }
    }
    
     @Override
    public void captured() {
        super.captured(); // Call the base method if necessary
        GameManager.getInstance().onGameEnd(this.getTeamColour() ? "Black" : "White");
    }
}
