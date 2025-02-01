/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

import chess.CoOrdinates;

/**
 *
 * @author dosum
 */
public class Pawn extends ChessPiece{
    
    boolean isFirstMove = true;
    boolean isWhite;

     public Pawn(CoOrdinates initialPosition, boolean isWhite) {
        super("Pawn", initialPosition, isWhite);
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        int direction = getTeamColour() ? 1 : -1; // White moves up (+1), Black moves down (-1)
        
        // Normal move: Move 1 square forward
        addAllowedMove(getCordnts().moveVertical(direction, 0));

        // First move: Can move 2 squares forward
        if (isFirstMove) {
            addAllowedMove(getCordnts().moveVertical(2 * direction, 0));
        }

        // Capture moves: Diagonal left and right
        //addAllowedMove(current.moveVertical(direction, -1));
        //addAllowedMove(current.moveVertical(direction, 1));

        isFirstMove = false;
    }
    
}
