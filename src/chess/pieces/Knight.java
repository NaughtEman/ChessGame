/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

/**
 *
 * @author dosum
 */
public class Knight extends ChessPiece{

    public Knight(CoOrdinates initialPosition, boolean isWhite) {
        super("Knight", initialPosition, isWhite);
    }

    @Override
    public void movementLogic() {
        
        CoOrdinates kA = this.getCordnts().moveVertical(2, 1);
        CoOrdinates A = kA.moveHorizontal(1, 1);
        
        CoOrdinates B = A.moveHorizontal(3, -1);
        CoOrdinates H = A.moveDiagonalBS(1, -1);
        CoOrdinates D = A.moveVertical(4, -1);
        
        CoOrdinates C = D.moveDiagonalFS(1, 1);
        CoOrdinates E = D.moveHorizontal(3, -1);
        
        CoOrdinates G = B.moveDiagonalFS(1, -1);
        
        CoOrdinates F = G.moveVertical(2, -1);
        
        addAllowedMove(A);
        addAllowedMove(B);
        addAllowedMove(C);
        addAllowedMove(D);
        addAllowedMove(E);
        addAllowedMove(F);
        addAllowedMove(G);
        addAllowedMove(H);
        
    }
    
}
