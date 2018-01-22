/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author fishc8155
 */
public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] wordBank = {"quickly", "exponent", "program"};  // Pick random index of words array
        int randomWordNumber = (int) (Math.random() * wordBank.length);// Create an array to store already entered letters
        char[] Letters = new char[wordBank[randomWordNumber].length()];
        int numberOfTries = 0;
        boolean wordIsGuessed = false;
        do {
        // infinitely cycle as long as enterLetter is true
        // if enterLetter returns false that means user guessed all the letters
        switch (enterLetter(wordBank[randomWordNumber], Letters)) {
            case 0:
                numberOfTries++;
                break;
            case 1:
                numberOfTries++;
                break;
            case 2:
                break;
            case 3:
                wordIsGuessed = true;
                break;
        }
        } while (! wordIsGuessed);
        System.out.println("\nThe word is " + wordBank[randomWordNumber] +
            " You missed " + (numberOfTries -findEmptyPosition(Letters)) +
            " time(s)");
    }

    
    //returns 0 if letter entered is not in the word (counts as try),
    //returns 1 if letter were entered 1st time (counts as try),
    // returns 2 if already guessed letter was REentered,
    //returns 3 if all letters were guessed 
    public static int enterLetter(String word, char[] Letters)    {
        System.out.print("(Guess) Enter a letter in word ");
        // word is successfully guessed
        if (! printWord(word, Letters))
            return 3;
        System.out.print(" > ");
        Scanner input = new Scanner(System.in);
        int emptyPosition = findEmptyPosition(Letters);
        char userInput = input.nextLine().charAt(0);
        if (inEnteredLetters(userInput, Letters)) {
            System.out.println(userInput + " is already in the word");
            return 2;
        }
        else if (word.contains(String.valueOf(userInput))) {
            Letters[emptyPosition] = userInput;
            return 1;
        }
        else {
            System.out.println(userInput + " is not in the word");
            return 0;
            }
    }
    public static boolean printWord(String word, char[] Letters) {
        // go through all letters in word
        boolean asteriskPrinted = false;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            // Check if letter already have been entered by the user before
            if (inEnteredLetters(letter, Letters))
                System.out.print(letter); 
            else {
                System.out.print('*');
                asteriskPrinted = true;
            }
        }
        return asteriskPrinted;
    }

    /* Check if letter is in enteredLetters array */
    public static boolean inEnteredLetters(char letter, char[] enteredLetters) {
        return new String(enteredLetters).contains(String.valueOf(letter));
    }

    /* Find first empty position in array of entered letters (one with code \u0000) */
    public static int findEmptyPosition(char[] Letters) {
        int i = 0;
        while (Letters[i] != '\u0000') i++;
        return i;
    }
}
   