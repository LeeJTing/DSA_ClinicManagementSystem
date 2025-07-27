/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author
 */
public class MessageUI {

    // Define Colors
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void errorMessage() {
        System.out.println(RED + "Invalid Input!" + RESET);
    }

    public static void inputIntegerMessage() {
        System.out.println(RED + "Input must in Integer." + RESET);
    }

    public static void doubleIntegerMessage() {
        System.out.println(RED + "Invalid input. Input must be in Double." + RESET);
    }

    public static void yesNoMessage() {
        System.out.println(RED + "Enter Y or N only." + RESET);
    }
}
