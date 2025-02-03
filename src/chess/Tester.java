/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess;

import chess.pieces.*;


/**
 *
 * @author dosum
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        CoOrdinates cd = new CoOrdinates();
        
        cd.setCordnts('A', 3);
        
        System.out.println(cd.getCordnts());
        
        Pawn p = new Pawn(cd, true);
        
        p.movementLogic();
        
        p.showAllowedMoves();
        p.toString();
        
        cd.setCordnts(4, 4);
        System.out.println(cd.getCordnts());
        Rook r = new Rook(cd, true);
        r.movementLogic();
        r.showAllowedMoves();
        r.toString();
        
        
    }
    
}
