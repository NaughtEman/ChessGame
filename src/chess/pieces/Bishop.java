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
public class Bishop extends ChessPiece{
    
    private List<Direction> diagonal = List.of(Direction.BACK_SLASH_DOWN, 
            Direction.BACK_SLASH_UP, Direction.FORWARD_SLASH_DOWN, Direction.FORWARD_SLASH_UP);

    public Bishop(CoOrdinates initialPosition, boolean isWhite) {
        super("Bishop", initialPosition, isWhite, false);
        movementLogic();
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        int x = getCordnts().getX();
        int y = getCordnts().getY();
        
        for (Direction dir : diagonal) {
            for (int i = 1; i <= 7; i++) {
                
                int dx = dir.dx() * i;
                int dy = dir.dy()* i;
                
                CoOrdinates cd = new CoOrdinates(x+dx, y+dy);
                
                if(!cd.isOOB()){
                    addAllowedMove(cd);
                }   
            }
        }
    }
}
