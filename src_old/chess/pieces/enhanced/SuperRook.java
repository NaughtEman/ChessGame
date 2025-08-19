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
    
    private Power ultimate = new Power("Path to Valhalla",8, true);
    private Power regular = new Power("Odin's March", 3, false);

    public SuperRook(CoOrdinates initialPosition, boolean isWhite) {
        super(initialPosition, isWhite);
        setSpecial(true);
    }
    
    

    @Override
    public void useRegularPower(PowerContext pc) {
        
        Direction dir = pc.getDirection();
        
        if(regular.getCooldown() == 0){
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
                Psychopomp psychopomp = new Psychopomp(piece, DeathType.KILLED);
            }
        }
        
        Psychopomp psychopomp = new Psychopomp(this, DeathType.SUICIDE);
    }
    
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }
    
}
