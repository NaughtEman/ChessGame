/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.abilities;

/**
 *
 * @author dosum
 */
import chess.pieces.*;

public class PowerContext {
    private Direction direction;
    private ChessPiece targetPiece;
    private CoOrdinates targetCoordinates;

    public PowerContext() {}

    public PowerContext(ChessPiece targetPiece) {
        this.targetPiece = targetPiece;
    }

    public PowerContext(Direction direction) {
        this.direction = direction;
    }

    public PowerContext(ChessPiece targetPiece, Direction direction) {
        this.targetPiece = targetPiece;
        this.direction = direction;
    }

    public ChessPiece getTargetPiece() {
        return targetPiece;
    }

    public Direction getDirection() {
        return direction;
    }

    public CoOrdinates getTargetCoordinates() {
        return targetCoordinates;
    }

    public void setTargetPiece(ChessPiece targetPiece) {
        this.targetPiece = targetPiece;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setTargetCoordinates(CoOrdinates targetCoordinates) {
        this.targetCoordinates = targetCoordinates;
    }
}

