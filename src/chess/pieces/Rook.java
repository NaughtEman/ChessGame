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
public class Rook extends ChessPiece{

     public Rook(CoOrdinates initialPosition, boolean isWhite) {
        super("Rook", initialPosition, isWhite);
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        for(int i = 1; i < 8; i++){
            addAllowedMove(getCordnts().moveVertical(i, 1));  // Up
            addAllowedMove(getCordnts().moveVertical(i, -1)); // Down
            addAllowedMove(getCordnts().moveHorizontal(i, 1));  // Right
            addAllowedMove(getCordnts().moveHorizontal(i, -1)); // Left
        } 
    }
}
