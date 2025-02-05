/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */
public class Bishop extends ChessPiece{

    public Bishop(CoOrdinates initialPosition, boolean isWhite) {
        super("Bishop", initialPosition, isWhite);
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        for(int i = 1; i < 8; i++){
            addAllowedMove(getCordnts().moveDiagonalBS(i, 1));  
            addAllowedMove(getCordnts().moveDiagonalBS(i, -1)); 
           
            addAllowedMove(getCordnts().moveDiagonalFS(i, 1));  
            addAllowedMove(getCordnts().moveDiagonalFS(i, -1)); 
        }
    }
    
}
