/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.pieces.CoOrdinates;
import chess.pieces.Direction;
import chess.pieces.King;

/**
 *
 * @author dosum
 */
public class SuperKing extends King implements Powerable{

    public SuperKing(CoOrdinates initialPosition, boolean isWhite) {
        super(initialPosition, isWhite);
        setSpecial(true);
    }

    @Override
    public void useRegularPower(Direction d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void useUltimatePower(Direction d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
