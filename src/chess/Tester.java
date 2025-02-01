/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess;

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
        
        cd.setCordnts(1, 3);
        System.out.println(cd.getCordnts());
    }
    
}
