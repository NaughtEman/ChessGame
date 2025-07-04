/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dosum
 */
public class Pawn extends ChessPiece{
    
    private List<CoOrdinates> allowedPawnCapture;
    boolean isFirstMove = true;

     public Pawn(CoOrdinates initialPosition, boolean isWhite) {
        super("Pawn", initialPosition, isWhite, false);
        this.allowedPawnCapture = new ArrayList<>();
        movementLogic();
    }
     
     @Override
     // Update the chess piece co-ordinates
    public void updateCordnts(CoOrdinates cordnts) {
        super.updateCordnts(cordnts);
        isFirstMove = false;
        allowedPawnCapture = new ArrayList<>();
    }

     /** Calculates the movement logic for the Pawn
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

        captureLogic(d);
    }
    
    public void captureLogic(int d){
        
        if (allowedPawnCapture == null) {
            System.out.println("Error: allowedPawnCapture is NULL! Reinitializing...");
            //allowedPawnCapture = new ArrayList<>(); // Avoid null pointer exceptions
        }
        
        //allowedPawnCapture.clear();
        // Capture moves: Diagonal left and right
        allowedPawnCapture.add(getCordnts().moveCustom(1, d));
        allowedPawnCapture.add(getCordnts().moveCustom(-1, d));
    }

    public boolean inCaptureRange(CoOrdinates cd) {
        boolean canCapture = false;
        
        if(allowedPawnCapture.contains(cd)){
            canCapture = true;
        }
        
        return canCapture; 
    }
}
