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
        
        int[][] diagonalMoves = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        for (int[] move : diagonalMoves) {
            for (int i = 1; i <= 7; i++) {
                addAllowedMove(getCordnts().moveCustom(move[0] * i, move[1] * i));
            }
        }
    }
}
