/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package chess.utilities;

import chess.ChessBoard;

/**
 *
 * @author dosum
 */
public interface BoardObserver {
    void onBoardChanged(ChessBoard board);
}
