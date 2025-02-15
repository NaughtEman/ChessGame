/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.actors;

/**
 *
 * @author dosum
 */
import java.util.*;
import chess.*;
import chess.pieces.*;

public class Player {
    
    boolean isWhite;
    
    String pName;
    
    private List <ChessPiece> soldiers = new ArrayList<>();
    
    private List <ChessPiece> vanquished = new ArrayList<>();
    
    private static ChessBoard board = ChessBoard.getInstance();

    public Player(boolean isWhite, String pName) {
        this.isWhite = isWhite;
        this.pName = pName;
        setPieces();
    }
    
    private void setPieces(){
        
        int pawnRow = isWhite ? 2 : 7; // Pawns are on row 2 (White) or 7 (Black)
        int mainRow = isWhite ? 1 : 8; // Main pieces are on row 1 (White) or 8 (Black)

        // Place Pawns
        for (int i = 0; i < 8; i++) {
            soldiers.add(new Pawn(new CoOrdinates(i + 1, pawnRow), isWhite));
        }

        // Place Rooks
        soldiers.add(new Rook(new CoOrdinates(1, mainRow), isWhite));
        soldiers.add(new Rook(new CoOrdinates(8, mainRow), isWhite));

        // Place Knights
        soldiers.add(new Knight(new CoOrdinates(2, mainRow), isWhite));
        soldiers.add(new Knight(new CoOrdinates(7, mainRow), isWhite));

        // Place Bishops
        soldiers.add(new Bishop(new CoOrdinates(3, mainRow), isWhite));
        soldiers.add(new Bishop(new CoOrdinates(6, mainRow), isWhite));

        // Place Queen (on the matching color square)
        soldiers.add(new Queen(new CoOrdinates(4, mainRow), isWhite));

        // Place King
        soldiers.add(new King(new CoOrdinates(5, mainRow), isWhite));
        board.updateBoard(soldiers);
    }
    
    // Add piece to vanquished list when capturing an opponent's piece
    public void capturePiece(ChessPiece piece) {
        vanquished.add(piece);
        board.removePiece(piece.getCordnts()); // Ensures it's no longer on the board
    }
    
    
      
}
