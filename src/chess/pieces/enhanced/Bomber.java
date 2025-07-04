/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.enhanced;

import chess.ChessBoard;
import chess.pieces.ChessPiece;
import chess.pieces.CoOrdinates;
import chess.utilities.BoardObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dosum
 */
public class Kamikaze extends ChessPiece implements BoardObserver{
    
    private List<CoOrdinates> allowedKamikazeCapture;
    private ChessBoard board = ChessBoard.getInstance();
     public Kamikaze(CoOrdinates initialPosition, boolean isWhite) {
        super("Kamikaze", initialPosition, isWhite, true);
        this.allowedKamikazeCapture = new ArrayList<>();
        movementLogic();
    }

    @Override
    public void movementLogic() {
        clearAllowedMoves();
        
        int d = getTeamColour() ? 1 : -1; // White moves up (+1), Black moves down (-1) 
        
        // Normal move: Move 1 square forward
        addAllowedMove(getCordnts().moveCustom(0, d));
    }
    
    /**
     * Checks if the conditions for detonation are true
     * @param board
     * @return true if bounded horizontally by opposing pieces 
     */
    public boolean canDetonateH() {
        int x = getCordnts().getX(), y = getCordnts().getY();
        return isEnemy(board.getPieceAt(x - 1, y)) && isEnemy(board.getPieceAt(x + 1, y));
    }
    
    /**
     * Checks if the conditions for detonation are true
     * @param board
     * @return true if bounded vertically by opposing pieces 
     */
    public boolean canDetonateV() {
        int x = getCordnts().getX(), y = getCordnts().getY();
        return isEnemy(board.getPieceAt(x, y - 1)) && isEnemy(board.getPieceAt(x, y + 1));
    }
    
    private boolean isEnemy(ChessPiece piece) {
        return piece != null && piece.getTeamColour() != this.getTeamColour();
    }
    
    // TODO implement valhalla and quarter master method when power used.
    
    public void detonateV(){
        if (!canDetonateV()) return;
        int x = getCordnts().getX(), y = getCordnts().getY();
        board.removePieceAt(new CoOrdinates(x, y - 1));
        board.removePieceAt(new CoOrdinates(x, y + 1));
        board.removePieceAt(getCordnts());
    }
    
    /**
     * Detonate bomb horizontally
     */
    public void detonateH(){
        if (!canDetonateH()) return;
        int x = getCordnts().getX(), y = getCordnts().getY();
        board.removePieceAt(new CoOrdinates(x - 1, y));
        board.removePieceAt(new CoOrdinates(x + 1, y));
        board.removePieceAt(getCordnts());
    }

    @Override
    public void onBoardChanged(ChessBoard board) {
        if (canDetonateH() || canDetonateV()) {
            System.out.println("⚠️ Kamikaze at " + getCordnts() + " is ready to detonate.");
        }
    }

    
    
}
