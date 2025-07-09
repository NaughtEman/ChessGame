/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.dead;

import chess.pieces.ChessPiece;

/**
 *Provides more details on piece death
 * @author dosum
 */
public class Psychopomp {
    
    private ChessPiece chesspiece;
    
    private boolean killed;
    
    private String id;

    public Psychopomp(ChessPiece chesspiece, boolean killed) {
        this.chesspiece = chesspiece;
        this.killed = killed;
        this.id = chesspiece.getID();
        
        Mortavia mortavia = Mortavia.getInstance();
        mortavia.addSoul(this);
    }

    public ChessPiece getChessPiece() {
        return chesspiece;
    }

    public boolean isKilled() {
        return killed;
    }

    public String getId() {
        return id;
    }
}
