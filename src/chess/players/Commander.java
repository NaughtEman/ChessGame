/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.players;

import chess.battlefield.ChessBoard;
import chess.pieces.*;
import chess.pieces.enhanced.*;

import java.util.*;

/**
 * Responsible for army formation
 * @author dosum
 */
public class Commander {
    private List<ChessPiece> army = new ArrayList<>();
    
    /** Reference to the chessboard, following a singleton pattern. */
    private static ChessBoard board = ChessBoard.getInstance();

    public void addPiece(String piece, CoOrdinates initialPosition, boolean isWhite) {
        army.add(PieceFactory.create(piece, initialPosition, isWhite));
    }

    public void removePiece(ChessPiece piece) {
        army.remove(piece);
    }
    
    public List<ChessPiece> build(String answer, String piece, CoOrdinates initialPosition, boolean isWhite) {
        if(answer.equalsIgnoreCase("T")){
            traditional(isWhite);
        }else{
            int i =0;
            //while(i != 16){
                addPiece(piece, initialPosition, isWhite);
            //}
        }
        board.updateBoard(army);
        return army;// final immutable army object
    }
    
    private void traditional(boolean isWhite){
        
        int pawnRow = isWhite ? 2 : 7; // Pawns are on row 2 (White) or 7 (Black)
        int mainRow = isWhite ? 1 : 8; // Main pieces are on row 1 (White) or 8 (Black)

        // Place Pawns
        for (int i = 0; i < 8; i++) {
            army.add(new Pawn(new CoOrdinates(i + 1, pawnRow), isWhite));
        }

        // Place Rooks
        army.add(new Rook(new CoOrdinates(1, mainRow), isWhite));
        army.add(new Rook(new CoOrdinates(8, mainRow), isWhite));

        // Place Knights
        army.add(new Knight(new CoOrdinates(2, mainRow), isWhite));
        army.add(new Knight(new CoOrdinates(7, mainRow), isWhite));

        // Place Bishops
        army.add(new Bishop(new CoOrdinates(3, mainRow), isWhite));
        army.add(new Bishop(new CoOrdinates(6, mainRow), isWhite));

        // Place Queen (on the matching color square)
        army.add(new Queen(new CoOrdinates(4, mainRow), isWhite));

        // Place King
        army.add(new King(new CoOrdinates(5, mainRow), isWhite));
    }
}
