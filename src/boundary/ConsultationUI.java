/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.Input;

/**
 *
 * @author Tan Kok Hong
 */
public class ConsultationUI {

    public static int consultationMenu() {

        int choice;

        System.out.println("        =================================================");
        System.out.println("        =              Consultation Menu                =");
        System.out.println("        =================================================");
        System.out.println("        =         1. View Appointment                   =");
        System.out.println("        =         2. search Appointment                 =");
        System.out.println("        =         3. Add Appointment                    =");
        System.out.println("        =         4. Update Appoinment                  =");
        System.out.println("        =         5. View Consultation schedule         =");
        System.out.println("        =         6. view Consultation Flw Up Report    =");
        System.out.println("        =         7. view Consultation Valume Report    =");
        System.out.println("        =         8. back                               =");
        System.out.println("        =================================================");

        choice = Input.getIntegerInput("Enter your choice > ");
        return choice;
    }

    public static int addAppointmentMenu() {

        int choice = 0;

        System.out.println("        =================================================");
        System.out.println("        =              Add Appointment Menu             =");
        System.out.println("        =================================================");
        System.out.println("        =         1. Online                             =");
        System.out.println("        =         2. Walk In                            =");
        System.out.println("        =         3. back                               =");
        System.out.println("        =================================================");

        choice = Input.getIntegerInput("Enter your choice > ");
        return choice;
    }
}
