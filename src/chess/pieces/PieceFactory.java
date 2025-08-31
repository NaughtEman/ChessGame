/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

import chess.pieces.enhanced.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 *
 * @author dosum
 */
public class PieceFactory {
    private static Map<String, BiFunction<CoOrdinates, Boolean, ChessPiece>> pieceFactory = new HashMap<>();
    
    static{
        pieceFactory.put("Pawn", (pos, color) -> new Pawn( pos, color));
        pieceFactory.put("Knight", (pos, color) -> new Knight( pos, color));
        pieceFactory.put("Rook", (pos, color) -> new Rook( pos, color));
        pieceFactory.put("Bishop", (pos, color) -> new Bishop( pos, color));
        pieceFactory.put("Queen", (pos, color) -> new Queen( pos, color));
        pieceFactory.put("King", (pos, color) -> new King( pos, color));
        pieceFactory.put("Bomber", (pos, color) -> new Bomber( pos, color));
        pieceFactory.put("Spearman", (pos, color) -> new Spearman( pos, color));
        pieceFactory.put("SuperKing", (pos, color) -> new SuperKing( pos, color));
        pieceFactory.put("SuperBishop", (pos, color) -> new SuperBishop( pos, color));
        pieceFactory.put("SuperQueen", (pos, color) -> new SuperQueen( pos, color));
        pieceFactory.put("SuperRook", (pos, color) -> new SuperRook( pos, color));
    }
    
    // The create method
    public static ChessPiece create(String type, CoOrdinates pos, boolean teamColor) {
        BiFunction<CoOrdinates, Boolean, ChessPiece> constructor = pieceFactory.get(type);
        if (constructor != null) {
            return constructor.apply(pos, teamColor);
        }
        throw new IllegalArgumentException("Unknown piece type: " + type);
    }
}
