/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.battlefield;

/**
 *
 * @author dosum
 */
import chess.game.GameManager;
import chess.battlefield.ChessBoard;
import chess.*;
import chess.players.General;
import chess.pieces.*;

import chess.pieces.dead.*;

public class Tactician {

    private static final ChessBoard board = ChessBoard.getInstance();

    private Tactician() {} // Private constructor to prevent instantiation

    public static boolean movePiece(ChessPiece piece, CoOrdinates targetCoords) {
        if (piece == null) {
            System.out.println("Error: Attempted to move a null piece.");
            return false;
        }
        
        if (piece.movementAltered()) {
            piece.setMovementFlag(false);
            //overriddenMoves.clear();
            // Optionally remove the status effect too, if needed
        }


        piece.movementLogic();

        if (piece instanceof Pawn) {
            return handlePawnMove((Pawn) piece, targetCoords);
        }

        if (piece.getAllowedMoves().contains(targetCoords)) {
            return executeMove(piece, targetCoords);
        }

        return false;
    }

    private static boolean handlePawnMove(Pawn pawn, CoOrdinates targetCoords) {
        if (pawn == null) return false;

        if (!board.isFree(targetCoords) && pawn.inCaptureRange(targetCoords)) {
            return executeCapture(pawn, targetCoords);
        }

        if (pawn.getAllowedMoves().contains(targetCoords)) {
            return executeMove(pawn, targetCoords);
        }

        return false;
    }

    private static boolean executeMove(ChessPiece piece, CoOrdinates targetCoords) {
        if (piece == null) {
            System.out.println("Error: Attempted to move a null piece.");
            return false;
        }

        if (!board.isFree(targetCoords)) {
            return executeCapture(piece, targetCoords);
        }

        // Move the piece to the new location
        board.removePieceAt(piece.getCordnts());
        piece.updateCordnts(targetCoords);
        board.placePiece(piece, targetCoords);

        return true;
    }

    private static boolean executeCapture(ChessPiece attacker, CoOrdinates targetCoords) {
        // piece getting killed
        ChessPiece targetPiece = board.getBoard().get(targetCoords);
        
        GameManager gm = GameManager.getInstance();

        if (targetPiece == null) { 
            System.err.println("Error: Tried to capture a piece at " + targetCoords + " but no piece was found.");
            return false; // Defensive check
        }

        // Retrieve the commanders
        General attackingCommander = gm.getPlayer(attacker.getTeamColour()); // killer
        General defendingCommander = gm.getPlayer(targetPiece.getTeamColour()); // getting killed
        
        //Increment kill count
        attacker.incrementKills();

        // Mark the captured piece
        new Psychopomp(targetPiece, attacker);

        // Remove captured piece from the board
        //board.getBoard().remove(targetCoords);

        // Move attacker to the captured piece’s position
        board.removePieceAt(attacker.getCordnts());  
        board.placePiece(attacker, targetCoords);

        return true;
    }

}
