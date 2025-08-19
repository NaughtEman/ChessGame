/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package chess.pieces.abilities;

import chess.pieces.Direction;

/**
 *
 * @author dosum
 */
public interface Powerable {
    void useRegularPower(PowerContext pc);
    void useUltimatePower(PowerContext pc);
    Power getUPower();
    Power getRPower();
}
