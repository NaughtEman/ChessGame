/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces.abilities;

import chess.pieces.ChessPiece;
import chess.players.Commander;

/**
 *
 * @author dosum
 */
public class PowerManager {
    
    public void isPowerReady(){
        
    } 
    
    public static void decrementCooldown(Commander commander){
        
        for(ChessPiece piece : commander.getSoldiers()){
            if (piece instanceof Powerable powerUser) {
                
                try{
                    // get the actual Power object
                    Power Upower = powerUser.getUPower();
                    Power Rpower = powerUser.getRPower();

                    // update cooldown
                    Upower.updateCooldown();
                    Rpower.updateCooldown();
                }catch(Exception e){
                    System.out.println("Not yet supported");
                }   
            }
        }
    }
    
    public static void listPowerDetails(Commander commander){
        for(ChessPiece piece : commander.getSoldiers()){
            if (piece instanceof Powerable powerUser) {
                
                try{
                    //Power Upower = powerUser.getUPower();
                    Power Rpower = powerUser.getRPower();
                    
                    System.out.println("Regular Power:");
                    System.out.println(((Powerable) piece).getRPower().PowerDetails());
                    System.out.println();
                    //System.out.println("Ultimate Power:");
                    //System.out.println(((Powerable) piece).getUPower().PowerDetails());
                    System.out.println("_______________________________________________");
                    
                }catch(Exception e){
                    System.out.println("Not yet supported");
                }
            }
        }
    }
    
}
