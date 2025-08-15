/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author
 */
public class MessageUI {

    // Define Colors
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void errorMessage() {
        System.out.println(RED + "\t\tInvalid Input!" + RESET);
    }

    public static void inputIntegerMessage() {
        System.out.println(RED + "\t\tInput must in Integer." + RESET);
    }

    public static void doubleIntegerMessage() {
        System.out.println(RED + "\t\tInvalid input. Input must be in Double." + RESET);
    }

    public static void yesNoMessage() {
        System.out.println(RED + "Enter Y or N only." + RESET);
    }
    
    public static void clearScreen() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public static void sleep(int ms) {
        try {
            // 0.8 Second
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
