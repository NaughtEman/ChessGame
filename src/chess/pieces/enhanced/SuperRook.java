/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.pieces.abilities.Powerable;
import chess.pieces.abilities.Power;
import chess.pieces.Direction;
import chess.battlefield.ChessBoard;
import chess.pieces.CoOrdinates;
import chess.pieces.Rook;

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
    public void useRegularPower(Direction dir) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void useUltimatePower(Direction dir) {
        
        int x = getCordnts().getX();
        int y = getCordnts().getX();
        
        int dx = dir.dx();
        int dy = dir.dy();
        
        while(!new CoOrdinates(x+dx, y+dy).isOOB()){
            
            x += dx;
            y += dy;
            
            board.removePieceAt(new CoOrdinates(x,y));
        }
        
        board.removePieceAt(getCordnts());
    }
    
}
