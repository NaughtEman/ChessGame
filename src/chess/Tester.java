/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess;

import chess.actors.Commander;
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
        
        /*
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
        
        System.out.println("For the King");
        cd.setCordnts(4, 4);
        System.out.println(cd.getCordnts());
        
        King kg = new King(cd, true);
        kg.showAllowedMoves();
        //b.toString
        */
        
        ChessBoard board = ChessBoard.getInstance();
        
        Commander pW = new Commander(true, "White");
                
        Commander pB = new Commander(false, "Black");
        /*
        board.displayBoard(); // Before move
        //board.getBoard().clear();
        pW.movePiece("Pawn", "B3"); // Make move
        //pW.printSoldiers(); // After move
        board.displayBoard();
        
        pB.movePiece("Rook", "A3");
        board.displayBoard();
        
        pB.movePiece("Rook", "B3");
        board.displayBoard();
        
        System.out.print("Black player vanquished ");
        pB.printVanquished();
        System.out.println();
        
        System.out.print("White player: We shall avenge ");
        pW.printFallen();
        System.out.println(); */
        
       // pW.strToObject("Pawn");
        //board.displayBoard();
        
        pW.movePiece("Pawn", "B3"); // Make move
        
        pB.movePiece("Rook", "A3");
        
        pB.movePiece("Rook", "B3");
        board.displayBoard();
        
        CoOrdinates current = new CoOrdinates("A2");
        CoOrdinates dest = new CoOrdinates("B3");
        
        board.movePiece(current, dest);
       // pW.movePiece("Pawn", "B3");
        board.displayBoard();
        
       
        System.out.print("White player vanquished ");
        pW.printVanquished();
        System.out.println();
        
        System.out.print("Black player: We shall avenge ");
        pB.printFallen();
        System.out.println();
        
    }
    
}
