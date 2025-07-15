/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.pieces.abilities.Powerable;
import chess.pieces.abilities.Power;
import chess.battlefield.ChessBoard;
import chess.pieces.ChessPiece;
import chess.pieces.CoOrdinates;
import chess.pieces.Direction;
import chess.pieces.abilities.PowerContext;
import chess.pieces.dead.*;
import chess.utilities.BoardObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dosum
 */
public class Bomber extends ChessPiece implements BoardObserver,Powerable{
    
    private List<CoOrdinates> allowedBomberCapture;
    
    private List<Direction> direction = new ArrayList<>();
    
    private Power ultimate = new Power("Kamikaze",6, true);
    
     public Bomber(CoOrdinates initialPosition, boolean isWhite) {
        super("Bomber", initialPosition, isWhite, true);
        this.allowedBomberCapture = new ArrayList<>();
        movementLogic();
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        int d = getTeamColour() ? 1 : -1; // White moves up (+1), Black moves down (-1) 
        
        // Normal move: Move 1 square forward
        addAllowedMove(getCordnts().moveCustom(0, d));
    }
    
    /**
     * Checks if the conditions for detonation are true
     * @param board
     * @return true if bounded horizontally by opposing pieces 
     */
    public boolean canDetonateH() {
        int x = getCordnts().getX(), y = getCordnts().getY();
        return isEnemy(board.getPieceAt(x - 1, y)) && isEnemy(board.getPieceAt(x + 1, y));
    }
    
    /**
     * Checks if the conditions for detonation are true
     * @param board
     * @return true if bounded vertically by opposing pieces 
     */
    public boolean canDetonateV() {
        int x = getCordnts().getX(), y = getCordnts().getY();
        return isEnemy(board.getPieceAt(x, y - 1)) && isEnemy(board.getPieceAt(x, y + 1));
    }
    
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }
    
    // TODO implement valhalla and quarter master method when power used.
    
    public void detonateV(){
        if (!canDetonateV()) return;
        int x = getCordnts().getX(), y = getCordnts().getY();
        board.removePieceAt(new CoOrdinates(x, y - 1));
        board.removePieceAt(new CoOrdinates(x, y + 1));
        board.removePieceAt(getCordnts());
    }
    
    /**
     * Detonate bomb horizontally
     */
    public void detonateH(){
        if (!canDetonateH()) return;
        int x = getCordnts().getX(), y = getCordnts().getY();
        board.removePieceAt(new CoOrdinates(x - 1, y));
        board.removePieceAt(new CoOrdinates(x + 1, y));
        board.removePieceAt(getCordnts());
    }

    @Override
    public void onBoardChanged(ChessBoard board) {
        if (canDetonateH() || canDetonateV()) {
            System.out.println("⚠️ Kamikaze at " + getCordnts() + " is ready to detonate.");
        }
    }

    @Override
    public void useRegularPower(PowerContext pc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void useUltimatePower(PowerContext pc) {
        int x = getCordnts().getX(), y = getCordnts().getY();
        for(Direction dir : Direction.values()){
            x += dir.dx();
            y += dir.dy();
            
            int x2 =  x + dir.dx() *2;
            int y2 = y + dir.dy()*2;
            
            board.removePieceAt(new CoOrdinates(x, y));
            board.removePieceAt(new CoOrdinates(x2, y2));
            // TODO update that the piece was killed and is in vallhalla
        }
        Psychopomp psychopomp = new Psychopomp(this, DeathType.SUICIDE);
    }

    
    
}
