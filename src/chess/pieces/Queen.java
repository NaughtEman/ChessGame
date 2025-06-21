/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */
public class Queen extends ChessPiece{

    public Queen(CoOrdinates initialPosition, boolean isWhite) {
        super("Queen", initialPosition, isWhite);
        movementLogic();
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        CPMovements.diagonalMoves(this);
        CPMovements.straightMoves(this);
    }
}
