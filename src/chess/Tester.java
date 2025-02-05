/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess;

import chess.pieces.CoOrdinates;
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
        
        System.out.println("For the Pawn");
        cd.setCordnts('A', 3);
        System.out.println(cd.getCordnts());
        
        Pawn p = new Pawn(cd, true);
        p.updateCordnts(cd);
        p.showAllowedMoves();
        //p.toString();
        
        System.out.println("For the Rook");
        
        cd.setCordnts(4, 4);
        System.out.println(cd.getCordnts());
        
        Rook r = new Rook(cd, true);
        r.showAllowedMoves();
        //r.toString();
        
        System.out.println("For the Bishop");
        cd.setCordnts(4, 4);
        System.out.println(cd.getCordnts());
        
        Bishop b = new Bishop(cd, true);
        b.showAllowedMoves();
        //b.toString();
        
        System.out.println("For the Knight");
        cd.setCordnts(4, 4);
        System.out.println(cd.getCordnts());
        
        Knight k = new Knight(cd, true);
        k.showAllowedMoves();
        //b.toString();
        
        
    }
    
}
