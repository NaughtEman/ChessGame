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
    boolean isWhite = true;

     public Pawn(boolean isWhite, CoOrdinates initialPosition) {
        super("Pawn", initialPosition);
        this.isWhite = isWhite;
    }

    
    
    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
       
        
        if(isFirstMove){
            
            isFirstMove = false;
        }
        
        
    }
    
}
