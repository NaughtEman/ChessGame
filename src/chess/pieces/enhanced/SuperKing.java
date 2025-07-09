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
import chess.pieces.King;

/**
 *
 * @author dosum
 */
public class SuperKing extends King implements Powerable{
    
    
    private Power regular = new Power("Royal escape",2,false);

    public SuperKing(CoOrdinates initialPosition, boolean isWhite) {
        super(initialPosition, isWhite);
        setSpecial(true);
    }
    
    
    CoOrdinates escapeTo;
    
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }
    
    private void readyPower(CoOrdinates swapTarget){
        
        if(!isEnemy(board.getPieceAt(swapTarget.getX(), swapTarget.getX()))){
            escapeTo = swapTarget;
        }
    }

    @Override
    public void useRegularPower(Direction d) {
        if (escapeTo != null) {
            board.swap(escapeTo, getCordnts());
        } else {
            System.out.println("⚠️ No valid escape target selected!");
        }
    }

    @Override
    public void useUltimatePower(Direction d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
