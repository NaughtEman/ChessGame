/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package chess.pieces.enhanced;

import chess.pieces.Direction;

/**
 *
 * @author dosum
 */
public interface Powerable {
    void useRegularPower(Direction d);
    void useUltimatePower(Direction d);
}
