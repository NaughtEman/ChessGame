/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author dosum
 */
import java.util.*;

public class CoOrdinates {
    
    private int x;
    private int y;
    
    private static final List <Character> ALLOWEDALPHAS = new ArrayList(List.of('A', 'B', 'C', 'D', 'E','F','G','H'));

    public CoOrdinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CoOrdinates() {}
    
    public String getCordnts() {
        return String.format("%s,%d", ALLOWEDALPHAS.get(this.x - 1), this.y);
    } // close getCordnts()


    // takes a char and an int
    public void setCordnts(char x, int y) {
            
            if(ALLOWEDALPHAS.contains(x)){
                this.x = ALLOWEDALPHAS.indexOf(x) +1; // get the num equivalent of the alpha
            }else{
                throw new IllegalArgumentException("Invalid value for X: " + x);
            }
        
            if(!isOOB(y)){
                this.y = y;
            }else {
                throw new IllegalArgumentException("Y must be between 1 and 8.");
            }
         // close try catch block
         
    } // close setCordnts 
    
    // takes 2 ints
    public void setCordnts(int x, int y) {
        
            if(!isOOB(x,y)){
                this.y = y;
                this.x = x;
            }else {
                throw new IllegalArgumentException("Co-ordinates must be between 1 and 8.");
            }
         
    } // close setCordnts
    
    // converts the x coordinate to an int for easy movement logic calculation 
    public int getXInInt(char c){
        
        int index = ALLOWEDALPHAS.indexOf(Character.toUpperCase(c)); // Convert input to lowercase
        return (index != -1) ? index + 1 : -1; // Return index + 1, or -1 if not found
        
    }// close getXInInt(char c)
    
    /* Move vertically 
        d means direction
        up = +1, down = -1
    */ 
    public int moveVertical(int m, int d){ 
        if (d == 0) {
            throw new IllegalArgumentException("Invalid direction: must be +1 (up) or -1 (down)");
        }
        return this.y + (m * d);
    }// close moveVertical(char c)
    
    /* Move horizontally
        d means direction
        right is +1
        left is -1
    */
    public int moveHorizontal(int m, int d){
        if (d == 0) {
            throw new IllegalArgumentException("Invalid direction: must be +1 (right) or -1 (left)");
        }
        return this.x + (m * d);
    }// close moveHorizontal(char c)
    
    /* Move diagonally
        backslash direction
        d means direction
        left and up = +1
        right and down = -1
    */
    public CoOrdinates moveDiagonalBS(int m, int d){
        return new CoOrdinates(moveHorizontal(m, -d), moveVertical(m, d));
    }// close moveDiagonalBS(int m, int d)
    
    /* Move diagonally
        fs: forward slash movement
        d means direction
        right and up +1
        left and down -1
    */
    public CoOrdinates moveDiagonalFS(int m, int d){
        return new CoOrdinates(moveHorizontal(m, d), moveVertical(m, d));
    }// close moveDiagonalFS(int m, int d)

    public int getX() {
        return x;
    }// close getX()

    public int getY() {
        return y;
    }// close getY()
    
     // Takes an int 
    public boolean isOOB(int t){
        return(t < 1 || t > 8);
    }// close checkOutOfBounds(int t)

    // Takes in int x and y coordinates e.g 2,1 and checks for out of bounds (OOB)
    public boolean isOOB(int x, int y){
        return isOOB(x) || isOOB(y);
    }// close checkOutOfBounds(int x, int y)
    
    // Takes the string coordinates 
    public boolean isOOB(String t){
        if (t.length() < 2) return true;
        
        char letter = Character.toUpperCase(t.charAt(0));
        int number;
        
        try {
            number = Integer.parseInt(t.substring(1));
        } catch (NumberFormatException e) {
            return true;
        }
        
        return isOOB(number) || !ALLOWEDALPHAS.contains(letter);
    }// close checkOutOfBounds(int t)
    
} //close class
