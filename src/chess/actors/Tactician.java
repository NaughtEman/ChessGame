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
        pawn.printAllowedMoves();

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
        board.removePiece(piece.getCordnts());  // Ensure this step is necessary
        piece.updateCordnts(targetCoords);
        board.placePiece(piece, targetCoords);

        return true;
    }


    private static boolean executeCapture(ChessPiece attacker, CoOrdinates targetCoords) {
        ChessPiece targetPiece = board.getBoard().get(targetCoords);
        targetPiece.deathNote(attacker);

        Commander commander = attacker.getPlayer();
        commander.getVanquished().add(targetPiece);
        targetPiece.goToValhalla();
        board.getBoard().remove(targetCoords);

        return executeMove(attacker, targetCoords);
    }
}
