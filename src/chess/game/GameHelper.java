/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.game;

import chess.pieces.CoOrdinates;
import java.io.*;
import java.util.*;

/**
 * Handles user input and processes valid commands or coordinates.
 * 
 * @author dosum
 */
public class GameHelper {
    
    private final List<String> words = new ArrayList<>();
    private CoOrdinates cd;

    /**
     * Prompts the user for input and returns the entered string.
     * 
     * @param prompt The message displayed to the user.
     * @return The user's input as a string.
     */
    public void getUserInput(String prompt) {
        String userInput = "";
        System.out.print(prompt + " ");
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));

        do{
            
            try{
            userInput =  is.readLine();  // Return the trimmed input for easier processing
            
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
            
        }while(userInput == null || userInput.isEmpty());
        
        splitInput(userInput);
    }

    /**
     * Splits and processes the given input string.
     * 
     * @param inputSplit The input string to split and process.
     */
    public void splitInput(String inputSplit) {
        words.clear();
        Scanner scanner = new Scanner(inputSplit);

        while (scanner.hasNext()) {
            String word = scanner.next().trim();  // Store to avoid multiple `.next()` calls
            
            if (word.equalsIgnoreCase("surrender")) {
                words.add("surrender");  // Add "surrender" directly to the list
            } else if (word.length() == 2) {
                try {
                    cd = new CoOrdinates(word);  // Create CoOrdinates object directly
                    words.add(word);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if(word.contains("fallen")){
                words.add("fallen");
            }else if(word.contains("vanquished")){
                words.add("vanquished");
            }else{
                System.out.println("Invalid Input");
            }
        }
        scanner.close();
    }
    
    public List<String> getWords(){
        return words;
    }
}
