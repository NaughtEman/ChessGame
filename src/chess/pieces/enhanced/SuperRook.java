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
import chess.pieces.Rook;
import chess.pieces.abilities.PowerContext;
import chess.pieces.dead.*;

/**
 *
 * @author dosum
 */
public class SuperRook extends Rook implements Powerable{
    
    private Power ult = new Power("Path to Valhalla",8, true);
    private Power reg = new Power("Odin's March", 3, false);

    public SuperRook(CoOrdinates initialPosition, boolean isWhite) {
        super(initialPosition, isWhite);
        setSpecial(true);
    }
    
    
    /**
     * Can kill up to 2 enemies in its path
     * @param pc 
     */
    @Override
    public void useRegularPower(PowerContext pc) {
        
        Direction dir = pc.getDirection();
        
        if(reg.getCooldown() == 0){
            int x = getCordnts().getX();
            int y = getCordnts().getY();
            
            int tempx = x + dir.dx()*2;
            int tempy = x + dir.dy()*2;
            
            for(int i = 1; i <= 2; i++){
                
            }
        }else{
            System.err.println("Power is not ready yet.");
        }
    }
    /**
     * Path to Valhalla, Moves in a straight line till OOB killing all enemies 
     * in its path.
     * @param pc 
     */
    @Override
    public void useUltimatePower(PowerContext pc) {
        
        Direction dir = pc.getDirection();
        
        int x = getCordnts().getX();
        int y = getCordnts().getX();
        
        int dx = dir.dx();
        int dy = dir.dy();
        
        while(!new CoOrdinates(x+dx, y+dy).isOOB()){
            
            x += dx;
            y += dy;
            
            ChessPiece piece = board.getPieceAt(new CoOrdinates(x + dx,y+dy));
            
            if(isEnemy(piece)){
                Psychopomp psychopomp = new Psychopomp(piece, this);
            }
        }
        
        Psychopomp psychopomp = new Psychopomp(this);
    }
    
    /**
     * Checks if the piece passed in is an enemy or ally
     * @param piece
     * @return 
     */
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }

    @Override
    public Power getUPower() {
        return ult;
    }

    @Override
    public Power getRPower() {
        return reg;
    }
    
}
