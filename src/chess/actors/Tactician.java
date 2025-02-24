/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.actors;

/**
 *
 * @author dosum
 */
import chess.*;
import chess.pieces.*;

public class Tactician {

    private static final ChessBoard board = ChessBoard.getInstance();

    private Tactician() {} // Private constructor to prevent instantiation

    public static boolean movePiece(ChessPiece piece, CoOrdinates targetCoords) {
        if (piece == null) {
            System.out.println("Error: Attempted to move a null piece.");
            return false;
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
    
    public static boolean check(ChessPiece piece, CoOrdinates targetCoords, Commander cmdr) {
        ChessPiece targetPiece = board.getBoard().get(targetCoords);
        
        if (targetPiece == null) {
            return movePiece(piece, targetCoords);  // If empty, just move
        }

        if (!targetPiece.onSameTeam(cmdr.getIsWhite())) {
            return executeCapture(piece, targetCoords);
        }

        System.out.println("Occupied by you");
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
        board.removePiece(piece.getCordnts());
        piece.updateCordnts(targetCoords);
        board.placePiece(piece, targetCoords);

        return true;
    }

    private static boolean executeCapture(ChessPiece attacker, CoOrdinates targetCoords) {
        ChessPiece targetPiece = board.getBoard().get(targetCoords);
        if (targetPiece == null) return false; // Should never happen, but defensive check

        targetPiece.deathNote(attacker);

        Commander commander = attacker.getPlayer();
        commander.getVanquished().add(targetPiece);
        targetPiece.goToValhalla();
        board.getBoard().remove(targetCoords);

        // Move attacker to captured piece’s position
        board.removePiece(attacker.getCordnts());
        attacker.updateCordnts(targetCoords);
        board.placePiece(attacker, targetCoords);

        return true;
    }
}
