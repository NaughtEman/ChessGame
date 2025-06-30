/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.pieces.Direction;
import chess.ChessBoard;
import chess.pieces.CoOrdinates;
import chess.pieces.Rook;

/**
 *
 * @author dosum
 */
public class SuperRook extends Rook implements Powerable{
    
    private Power ultimate = new Power("Path to Valhalla",0);
    private Power regular = new Power("Judgement path", 2, 2);
    private ChessBoard board = ChessBoard.getInstance();

    public SuperRook(CoOrdinates initialPosition, boolean isWhite) {
        super(initialPosition, isWhite);
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
