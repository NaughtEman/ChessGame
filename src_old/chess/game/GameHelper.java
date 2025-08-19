/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.game;

import chess.pieces.CoOrdinates;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Handles user input and processes valid commands or coordinates.
 * 
 * @author dosum
 */
public class GameHelper {
    
    private  List<String> words = new ArrayList<>();
    private CoOrdinates cd;
    private final BufferedReader is = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Prompts the user for input and returns the entered string.
     * 
     * @param prompt The message displayed to the user.
     * @return The user's input as a string.
     */
    public boolean getUserInput(String prompt, int timeout) {
        String userInput = "";
        System.out.print(prompt + " ");
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        Future<String> futureInput = executor.submit(() -> is.readLine());
            
        try{
            String input = futureInput.get(timeout, TimeUnit.SECONDS);
            words = Arrays.asList(input.trim().split("\\s+"));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Time's up! No input received.");
            words.clear(); // clear input
            futureInput.cancel(true); // stop input thread
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            executor.shutdownNow();
        }

        return false; // no input
    }

    /**
     * 
     * @return the mode
     */
    public String getMode(){
        String mode = "";
        
        System.out.println("Please enter a mode:");
        System.out.println("1.) Beginner 2mins. \n2.) Easy 1min. \n3.) Medium 30secs.\n"
                + "4.) Hard 15secs.\n5.) Blitz 6secs. \n6.) Bullet 3secs.");
        
        try{
            return mode = is.readLine();
        }catch(IOException e){
            System.out.println("Error reading input.");
            return "";
        }
    }
    
    /**
     * Splits and processes the given input string.
     * 
     * @param inputSplit The input string to split and process.
     */
    public boolean validateInput(String input) {
        
        /*if(words.size() > 1){
            if(words.get(0) && words.get(1)
        }*/
        
        if (!input.toLowerCase().contains("surrender")) {
            words.add("surrender");
        }
        else if (input.toLowerCase().contains("fallen")) {
            words.add("fallen");
        }
        else if (input.toLowerCase().contains("vanquished")) {
            words.add("vanquished");
        }
        
        return false;

    }
    
    public List<String> getWords(){
        return words;
    }

    private void movePieceInput(String word) {
        try {
                cd = new CoOrdinates(word);  // Create CoOrdinates object directly
                words.add(word);
            } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
            }
    }
}
