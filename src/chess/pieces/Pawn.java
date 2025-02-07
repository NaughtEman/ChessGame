/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */
public class Pawn extends ChessPiece{
    
    boolean isFirstMove = true;

     public Pawn(CoOrdinates initialPosition, boolean isWhite) {
        super("Pawn", initialPosition, isWhite);
    }
     
     @Override
     // Update the chess piece co-ordinates
    public void updateCordnts(CoOrdinates cordnts) {
        super.updateCordnts(cordnts);
        isFirstMove = false;
        
    }

     /** Calculates the movement logic for the Rook
      * 
      */
    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        int d = getTeamColour() ? 1 : -1; // White moves up (+1), Black moves down (-1) 
        
        // Normal move: Move 1 square forward
        addAllowedMove(getCordnts().moveCustom(0, d));

        // First move: Can move 2 squares forward
        if (isFirstMove) {
            addAllowedMove(getCordnts().moveCustom(0, 2* d));
        }

        // Capture moves: Diagonal left and right
        //addAllowedMove(current.moveVertical(direction, -1));
        //addAllowedMove(current.moveVertical(direction, 1));
    }
    
}
