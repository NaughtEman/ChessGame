/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */
public class Queen extends ChessPiece{

    public Queen(CoOrdinates initialPosition, boolean isWhite) {
        super("Queen", initialPosition, isWhite);
        movementLogic();
    }

    @Override
public void movementLogic() {
    clearAllowedMoves();
    
    // Reuse Rook movement (horizontal & vertical)
    int[][] straightMoves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    for (int[] move : straightMoves) {
        for (int i = 1; i <= 7; i++) { // Queen can move any number of squares
            addAllowedMove(getCordnts().moveCustom(move[0] * i, move[1] * i));
        }
    }

    // Reuse Bishop movement (diagonals)
    int[][] diagonalMoves = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    for (int[] move : diagonalMoves) {
        for (int i = 1; i <= 7; i++) {
            addAllowedMove(getCordnts().moveCustom(move[0] * i, move[1] * i));
        }
    }
}

    
}
