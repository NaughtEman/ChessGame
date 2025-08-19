/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */
public class Knight extends ChessPiece{

    public Knight(CoOrdinates initialPosition, boolean isWhite) {
        super("Knight", initialPosition, isWhite, false);
        movementLogic();
    }

    @Override
    public void movementLogic() {
         int[][] moves = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] move : moves) {
            addAllowedMove(getCordnts().moveCustom(move[0], move[1]));
        }
        
    }
    
}
