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
public class Bomber extends ChessPiece implements Powerable{
    
    private List<CoOrdinates> allowedBomberCapture;
    
    private List<Direction> direction = new ArrayList<>();
    
    private Power ult = new Power("Kamikaze",6, true);
    private Power reg = new Power("Bomb Toss",0, false);
    
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
    
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }

    @Override
    public void useRegularPower(PowerContext pc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void useUltimatePower(PowerContext pc) {
        
        if(ult.charged()){
            int x = getCordnts().getX(), y = getCordnts().getY();
            for(Direction dir : Direction.values()){
                x += dir.dx();
                y += dir.dy();

                int x2 =  x + dir.dx() *2;
                int y2 = y + dir.dy()*2;

                Psychopomp psychopomp = new Psychopomp(board.getPieceAt(new CoOrdinates(x, y)),this);
                Psychopomp psychopomp2 = new Psychopomp(board.getPieceAt(new CoOrdinates(x2, y2)),this);
            }
            Psychopomp psychopomp = new Psychopomp(this);
        }
    }    

    @Override
    public Power getUPower() {
        return ult;
    }

    @Override
    public Power getRPower() {
        return ult;
    }
}
