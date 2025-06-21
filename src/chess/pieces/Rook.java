/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */
public class Rook extends ChessPiece{

     public Rook(CoOrdinates initialPosition, boolean isWhite) {
        super("Rook", initialPosition, isWhite);
        movementLogic();
    }
     
     /** Calculates the movement logic for the Rook
      * 
      */
    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        CPMovements.straightMoves(this);
        
        /*int[][] straightMoves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] move : straightMoves) {
            for (int i = 1; i <= 7; i++) { // Queen can move any number of squares
                addAllowedMove(getCordnts().moveCustom(move[0] * i, move[1] * i));
            }
        } */
    }
}
