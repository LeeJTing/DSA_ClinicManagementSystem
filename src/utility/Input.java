/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;

/**
 *
 * @author 
 */
public class Input {
     static Scanner input = new Scanner(System.in);
    
    // Get Integer Input from User Keyboard
    public static int getIntegerInput(){
        int value;
        boolean error;
        
        do{
            error = false;
            try{
                value = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                input.nextLine();
                MessageUI.inputIntegerMessage();
                value = -1;
            }
            if(value == -1){
                error = true;
            }
        } while(error);
        
        return value;
    }
    
    // Get Integer Input from User Keyboard with Defined Question
    public static int getIntegerInput(String question){
        int value;
        boolean error;
        
        do{
            error = false;
            try{
                System.out.println(question);
                value = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                input.nextLine();
                MessageUI.inputIntegerMessage();
                value = -1;
            }
            if(value == -1){
                error = true;
            }
        } while(error);
        
        return value;
    }
    
    // Get Double
    public static double getDoubleInput(String question){
        double value;
        boolean error;
        
        do{
            error = false;
            try{
                System.out.println(question);
                value = input.nextDouble();
            }catch (Exception e) {
                input.nextLine();
                MessageUI.inputIntegerMessage();
                value = -1;
            }
            if(value == -1){
                error = true;
            }
        }while(error);
        
        return value;
    }
    
    // Get String Input from User Keyboard
    public static String getStringInput(){
        String value;
        
        try{
            value = input.nextLine();
        } catch (Exception e){
            input.nextLine();
            return null;
        }
        
        return value;
    }
    
    // Get String Input from User Keyboard with Defined Question
    public static String getStringInput(String question){
        String value;
        
        try{
            System.out.print(question);
            value = input.nextLine();
        } catch (Exception e){
            input.nextLine();
            return null;
        }
        
        return value;
    }
    
    public static boolean getBooleanInput(String question){
        boolean yes = false;
        int choice;
        boolean error;
        do{
            error = false;
            System.out.println("[1] for 'yes', [0] for 'no'");
            choice = getIntegerInput(question);
            switch (choice){
                case 1 -> yes = true;
                case 0 -> yes = false;
                default -> {
                    MessageUI.errorMessage();
                    error = true;
                }
            }
        }while(error);
        return yes;
    }
}
