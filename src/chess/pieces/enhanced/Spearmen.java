/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.pieces.abilities.Powerable;
import chess.pieces.abilities.Power;
import chess.pieces.Direction;
import chess.battlefield.ChessBoard;
import chess.pieces.ChessPiece;
import chess.pieces.CoOrdinates;
import chess.pieces.abilities.PowerContext;
import chess.pieces.dead.DeathType;
import chess.pieces.dead.Psychopomp;
import chess.utilities.BoardObserver;
import java.util.List;

/**A Spearman can capture a piece 2 cells ahead or horizontally
 *
 * @author dosum
 */
public class Spearmen extends ChessPiece implements BoardObserver, Powerable{
    
    private static final List<Direction> STAB_DIRECTIONS = List.of(Direction.UP, Direction.LEFT, Direction.RIGHT);
    
    private Power ult = new Power("Last Lance", 6, true);
    private Power reg = new Power("Spear Stab", 0, true);


    public Spearmen(String name, CoOrdinates initialPosition, boolean isWhite) {
        super(name, initialPosition, isWhite, true);
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        int d = getTeamColour() ? 1 : -1; // White moves up (+1), Black moves down (-1) 
        
        // Normal move: Move 1 square forward
        addAllowedMove(getCordnts().moveCustom(0, d));
    }
    
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }
    
    public boolean canStab(Direction dir){
        int x = getCordnts().getX();
        int y = getCordnts().getY();
        
        int dx = dir.dx()*2;
        int dy = dir.dy()*2;
        
        return isEnemy(board.getPieceAt(x+dx, y+dy));
    }
    
    /**
     * Stabs a piece in a co-ordinate
     * @param position, 1 is left, 2 is up, 3 is right 
     */
    public void stab(Direction dir){
        int x = getCordnts().getX();
        int y = getCordnts().getY();
        
        int dx = dir.dx()*2;
        int dy = dir.dy()*2;
        
        CoOrdinates target = new CoOrdinates(x + dx, y + dy);
        if (!target.isOOB()) {
            Psychopomp psychopomp = new Psychopomp(board.getPieceAt(target),this);
        }
    }

    @Override
    public void onBoardChanged(ChessBoard board) {
        for(Direction dir: STAB_DIRECTIONS){
            if(canStab(dir)){
                System.out.println("⚔️ Spearman at " + getCordnts() + " can stab " + dir + "!");
            }else {
                continue;
            }
        }
    }

    // TODO implement valhalla and quartermaster update
    @Override
    public void useRegularPower(PowerContext pc) {
        Direction dir = pc.getDirection();
        if (canStab(dir)) {
            stab(dir);
            System.out.println("Spearman stabbed " + dir + "!");
        } else {
            System.out.println("❌ No enemy to stab in direction: " + dir);
        }
    }

    @Override
    public void useUltimatePower(PowerContext pc) {
        
        Direction dir = pc.getDirection();
        
        if(ult.charged()){
            System.out.println("⚡ Spearman throws spear to the " + dir + " (ultimate power)");
            int x = getCordnts().getX();
            int y = getCordnts().getY();

            int dx = dir.dx();
            int dy = dir.dy();

            while(! new CoOrdinates(x+dx, y+dy).isOOB()){
                x += dx;
                y += dy;

                if(isEnemy(board.getPieceAt(x, y))){
                    board.removePieceAt(new CoOrdinates(x,y));
                }
            }
            System.out.println("☠️ Spearman at " + getCordnts() + " sacrifices their life.");
            Psychopomp psychopomp = new Psychopomp(this);
        }
        
    }
    
}
