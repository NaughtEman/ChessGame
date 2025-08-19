/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.pieces.abilities.*;
import chess.pieces.Bishop;
import chess.pieces.ChessPiece;
import chess.pieces.CoOrdinates;
import chess.pieces.Direction;
import chess.pieces.dead.*;
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
    StatusEffect regBlessing = new StatusEffect(-1, EffectType.PROTECTION);
    
    StatusEffect ultBlessing = new StatusEffect(3, EffectType.PROTECTION);

    public SuperBishop(CoOrdinates initialPosition, boolean isWhite) {
        super(initialPosition, isWhite);
    }
    
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }

    @Override
    public void useRegularPower(PowerContext pc) {
        if(reg.charged()){
            for(CoOrdinates coords:getAllowedMoves()){
                ChessPiece receiver = board.getPieceAt(coords.getX(), coords.getY());
                if(!isEnemy(receiver)){
                    receiver.addStatusEffect(regBlessing);
                }
            }
        }   
    }

    @Override
    public void useUltimatePower(PowerContext pc) {
        
        if(ult.charged()){
            pc.getPieces();
       
            for(ChessPiece temp : pc.getPieces()){
                temp.addStatusEffect(ultBlessing);
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
        return reg;
    }
}
