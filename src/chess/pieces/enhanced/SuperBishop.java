/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.pieces.abilities.Powerable;
import chess.pieces.abilities.Power;
import chess.pieces.Bishop;
import chess.pieces.ChessPiece;
import chess.pieces.CoOrdinates;
import chess.pieces.Direction;
import chess.pieces.abilities.StatusEffect;
import java.util.List;

/**
 *
 * @author dosum
 */
public class SuperBishop extends Bishop implements Powerable{
    
    private Power ult = new Power("Last Prayer", 7,true);
    private Power reg = new Power("Holy Ground", 3,false);
    
    // Last Prayer receiver
    ChessPiece lpR1,lpR2,lpR3;
    
    // -1 meaning passive
    StatusEffect regBlessing = new StatusEffect("Prayer of Protection", -1, "protection");
    
    StatusEffect ultBlessing = new StatusEffect("Prayer of Protection", 3, "protection");

    public SuperBishop(CoOrdinates initialPosition, boolean isWhite) {
        super(initialPosition, isWhite);
    }
    
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }

    @Override
    public void useRegularPower(Direction d) {
        if(reg.getCooldown() == 0){
            for(CoOrdinates coords:getAllowedMoves()){
                ChessPiece receiver = board.getPieceAt(coords.getX(), coords.getY());
                if(!isEnemy(receiver)){
                    receiver.addStatusEffect(regBlessing);
                }
            }
        }   
    }

    @Override
    public void useUltimatePower(Direction d) {
        lpR1.addStatusEffect(ultBlessing);
        lpR2.addStatusEffect(ultBlessing);
        lpR3.addStatusEffect(ultBlessing);
        
        board.removePieceAt(getCordnts());
    }
    
    public void prepareUlt(CoOrdinates cd1, CoOrdinates cd2, CoOrdinates cd3){
         if (!isEnemy(board.getPieceAt(cd1))) lpR1 = board.getPieceAt(cd1);
         
         if (!isEnemy(board.getPieceAt(cd2))) lpR2 = board.getPieceAt(cd2);

         if (!isEnemy(board.getPieceAt(cd3))) lpR3 = board.getPieceAt(cd3);
         
    }
    
}
