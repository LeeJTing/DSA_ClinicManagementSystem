/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Consultation;
import entity.Payment;
import entity.Prescription;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Teh Zhi Qin
 */
public class PaymentUI {

    Scanner scanner = new Scanner(System.in);

    public Payment paymentUI(String newID, Consultation consultation, Prescription prescription) {
        char pay;
        double consultationCost = consultation.calConsultationCost();
        double totalCost = consultationCost + prescription.getMedicine_total_cost();
        Date currentDate = new Date();
        System.out.println("");
        System.out.println("\t\t\t\t==================================================================");
        System.out.println("\t\t\t\t|                           Payment                              |");
        System.out.println("\t\t\t\t==================================================================");
        System.out.printf("\t\t\t\t%35s     %-15.2f     |\n", "|   Consultation cost (RM) :        |   ", consultationCost);
        System.out.printf("\t\t\t\t%35s     %-15.2f     |\n", "|   Medicine cost(RM) :             |   ", prescription.getMedicine_total_cost());
        System.out.println("\t\t\t\t==================================================================");
        System.out.printf("\t\t\t\t%35s     %-15.2f     |\n", "|   Total cost(RM) :                |   ", totalCost);
        System.out.println("\t\t\t\t==================================================================");

        do {
            System.out.print("\t\t\t\tDo you want to pay?(y/n): ");
            pay = scanner.nextLine().charAt(0);
        } while (pay != 'y' && pay != 'Y');

        return new Payment(newID, consultation.getPatient_Id(), prescription, consultationCost, totalCost, currentDate);
    }

    public void displaySuccessfulMsg() {
        System.out.println("\n\t\t\t\tPaid successfully, take good care!");
    }

    public void displayPaymentToString(Payment pm) {
        System.out.println(pm.toString());
    }
}
