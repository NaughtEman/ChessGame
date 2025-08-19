/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.pieces.ChessPiece;
import chess.pieces.CoOrdinates;
import chess.pieces.Direction;
import chess.pieces.Queen;
import chess.pieces.abilities.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author dosum
 */
public class SuperQueen extends Queen implements Powerable{

    public SuperQueen(CoOrdinates initialPosition, boolean isWhite) {
        super(initialPosition, isWhite);
        setSpecial(true);
    }
    
    Power reg = new Power("Blessing of Freedom", 5, false);
    StatusEffect effect = new StatusEffect(1, EffectType.MOVEMENT);

    @Override
    public void useRegularPower(PowerContext pc) {
        ChessPiece ally = pc.getTargetPiece();
        
        if(reg.charged()){
            if(ally.getTeamColour() == this.getTeamColour()){
                
                ally.addStatusEffect(effect);
                
                ally.setMovementOverride(this.getAllowedMoves());
            }
        }
        
    }

    @Override
    public void useUltimatePower(PowerContext pc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Power getUPower() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Power getRPower() {
        return reg;
    }
}
