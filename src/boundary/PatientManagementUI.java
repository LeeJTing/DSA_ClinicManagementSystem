/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.Input;

/**
 *
 * @author Lwin
 */
public class PatientManagementUI {
    public static int displayPatientManagementMenu(String patientId) {
        System.out.println("        =================================================");
        System.out.println("        =           Patient Management Menu             =");
        System.out.println("        =================================================");
        System.out.println("        =         1. View Patient Info                  =");
        System.out.println("        =         2. Edit Patient Info                  =");
        System.out.println("        =         3. Back                               =");
        System.out.println("        =================================================");

        return Input.getIntegerInput("Enter your choice > ");
    }
}
