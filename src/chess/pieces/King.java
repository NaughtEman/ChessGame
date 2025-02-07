/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */
public class King extends ChessPiece{

    public King( CoOrdinates initialPosition, boolean isWhite) {
        super("King", initialPosition, isWhite);
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
}
