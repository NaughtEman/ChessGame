/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.ChessBoard;
import chess.pieces.ChessPiece;
import chess.pieces.CoOrdinates;
import chess.utilities.BoardObserver;

/**A Spearman can capture a piece 2 cells ahead or horizontally
 *
 * @author dosum
 */
public class Spearmen extends ChessPiece implements BoardObserver {
    
    private ChessBoard board = ChessBoard.getInstance();

    public Spearmen(String name, CoOrdinates initialPosition, boolean isWhite) {
        super(name, initialPosition, isWhite);
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        int d = getTeamColour() ? 1 : -1; // White moves up (+1), Black moves down (-1) 
        
        // Normal move: Move 1 square forward
        addAllowedMove(getCordnts().moveCustom(0, d));
    }
    
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }
    
    public boolean canStabU(){
        int x = getCordnts().getX();
        int y = getCordnts().getY();
        
        return isEnemy(board.getPieceAt(x, y +2));
    }
    
    public boolean canStabL(){
        int x = getCordnts().getX();
        int y = getCordnts().getY();
        
        return isEnemy(board.getPieceAt(x-2, y));
    }
    
    public boolean canStabR(){
        int x = getCordnts().getX();
        int y = getCordnts().getY();
        
        return isEnemy(board.getPieceAt(x+2, y));
    }
    
    /**
     * Stabs a piece in a co-ordinate
     * @param position, 1 is left, 2 is up, 3 is right 
     */
    public void stab(int position){
        if(position == 1){
            stabHelper(-2, 0);
        } else if (position == 2){
            stabHelper(0, 2);
        } else if(position == 3){
            stabHelper(2, 0);
        }
    }
    
    // TODO implement valhalla and quartermaster update
    private void stabHelper(int a, int b){
        int x = getCordnts().getX();
        int y = getCordnts().getY();
        board.removePieceAt(new CoOrdinates(x + a, y+b));
        
    }

    @Override
    public void onBoardChanged(ChessBoard board) {
        if (canStabU() || canStabL() || canStabR()) {
            System.out.println("⚠️ Spearman at " + getCordnts() + " is in range.");
        }
    }
    
}
