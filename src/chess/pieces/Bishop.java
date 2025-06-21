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
        movementLogic();
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        CPMovements.diagonalMoves(this);
    }
}
