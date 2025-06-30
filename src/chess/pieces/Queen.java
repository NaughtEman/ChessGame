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
        
        int x = getCordnts().getX();
        int y = getCordnts().getY();

        for (Direction dir : Direction.values()) {
            
            for(int i = 1; i < 7; i++){
                
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
