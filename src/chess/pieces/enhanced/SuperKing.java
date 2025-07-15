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
import chess.pieces.abilities.PowerContext;

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
    public void useRegularPower(PowerContext pc) {
        board.swap(pc.getTargetPiece().getCordnts(), getCordnts());
    }

    @Override
    public void useUltimatePower(PowerContext pc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
