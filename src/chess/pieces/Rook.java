/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

import java.util.List;

/**
 *
 * @author dosum
 */
public class Rook extends ChessPiece{
    
    private List<Direction> direction = List.of(Direction.DOWN, Direction.UP, 
            Direction.LEFT, Direction.RIGHT);

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
        
        int x = getCordnts().getX();
        int y = getCordnts().getY();
        
        for (Direction dir : direction) {
            for (int i = 1; i <= 7; i++) {
                
                x += dir.dx() * i;
                y += dir.dy()* i;
                
                CoOrdinates cd = new CoOrdinates(x, y);
            
                if(!cd.isOOB()){
                    addAllowedMove(cd);
                }
            }
        } 
    }
}
