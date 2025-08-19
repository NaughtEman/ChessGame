/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

import java.util.List;

/**
 *
 * @author dosum
 */
public class CPMovements {
    
    public static void straightMoves(ChessPiece cp){
        int[][] straightMoves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] move : straightMoves) {
            for (int i = 1; i <= 7; i++) { // Queen can move any number of squares
                cp.addAllowedMove(cp.getCordnts().moveCustom(move[0] * i, move[1] * i));
            }
        }
    }
    
    public static void diagonalMoves(ChessPiece cp){
        int[][] diagonalMoves = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        for (int[] move : diagonalMoves) {
            for (int i = 1; i <= 7; i++) {
                cp.addAllowedMove(cp.getCordnts().moveCustom(move[0] * i, move[1] * i));
            }
        }
    }
    
}
