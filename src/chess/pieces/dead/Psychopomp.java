/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.dead;

import chess.battlefield.ChessBoard;
import chess.pieces.ChessPiece;

/**
 *Provides more details on piece death
 * @author dosum
 */
public class Psychopomp {
    
    private ChessPiece chesspiece;
    
    private ChessPiece killer;
    
    private String id;
    
    private DeathType deathType;
    
    private ChessBoard board = ChessBoard.getInstance();
    
    private int turnOfDeath;
    
    public Psychopomp(ChessPiece chesspiece) {
        this.chesspiece = chesspiece;
        this.deathType = DeathType.SUICIDE;
        this.id = chesspiece.getID();
        
        Mortavia mortavia = Mortavia.getInstance();
        mortavia.addSoul(this);
        board.removePieceAt(chesspiece.getCordnts());
        
    }
    
    public Psychopomp(ChessPiece chesspiece, ChessPiece killer) {
        this.chesspiece = chesspiece;
        this.deathType = DeathType.KILLED;
        this.killer = killer;
        this.id = chesspiece.getID();
        
        killer.incrementKills();
        Mortavia mortavia = Mortavia.getInstance();
        mortavia.addSoul(this);
        board.removePieceAt(chesspiece.getCordnts());  
    }

    public ChessPiece getChessPiece() {
        return chesspiece;
    }
    
    public ChessPiece getKiller() {
        return killer;
    }

    public DeathType getDeathType() {
        return deathType;
    }

    public String getId() {
        return id;
    }
}
