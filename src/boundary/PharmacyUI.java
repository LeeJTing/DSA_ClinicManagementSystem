/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Medicine;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import utility.Input;
import utility.MessageUI;

/**
 *
 * @author Teh Zhi Qin
 */
public class PharmacyUI {

    Scanner scanner = new Scanner(System.in);
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public int getMainMenuChoice(int alert) {
        int choice;
        System.out.println("\t\t=====================================================");
        System.out.println("\t\t|                 Pharmacy Module                   |");
        System.out.println("\t\t=====================================================");
        System.out.println("\t\t|       1. Add Medicine                             |");
        System.out.println("\t\t|       2. Update Medicine                          |");
        System.out.println("\t\t|       3. Display Medicine                         |");
        System.out.println("\t\t|       4. Delete Medicine                          |");
        System.out.println("\t\t|       5. Delete Expired Medicine                  |");
        System.out.println("\t\t|       6. Medicine Dispensing Summary Report       |");
        System.out.println("\t\t|       7. Medicine Stock Status Report             |");
        System.out.printf(
                "\t\t%s (%s%d%s)                   |\n",
                "|       8. Alert Notification",
                RED, alert, RESET);
        System.out.println("\t\t|       9. Undo Last Action                         |");
        System.out.println("\t\t|       10. Exit                                    |");
        System.out.println("\t\t=====================================================");

        choice = Input.getIntegerInput("\t\tEnter your choice: ");

        while (choice < 1 || choice > 10) {
            MessageUI.errorMessage();
            choice = Input.getIntegerInput("\t\tEnter your choice: ");
        }
        return choice;
    }

    public char promptAddMedicine() {
        char add;
        System.out.print("\t\tDo you want to add medicine? (y/n): ");
        add = scanner.next().charAt(0);
        scanner.nextLine();
        return add;
    }

    public Medicine addMedicineUI(String nextID, String category) {
        String name;
        Date expiryDate;
        int stock;
        double unitPrice;

        System.out.println("");
        System.out.println("\t\t===================================================");
        System.out.println("\t\t                    Add Medicine                   ");
        System.out.println("\t\t===================================================");

        System.out.println("\t\tMedicine ID: " + nextID);
        System.out.println("\t\t---------------------------------------------------");
        name = promptName();
        System.out.println("\t\t---------------------------------------------------");
        System.out.println("\t\tCategory: " + category);
        System.out.println("\t\t---------------------------------------------------");
        expiryDate = promptExpiryDate();
        System.out.println("\t\t---------------------------------------------------");
        stock = promptStock();
        System.out.println("\t\t---------------------------------------------------");
        unitPrice = promptUnitPrice();
        System.out.println("\t\t===================================================");

        return new Medicine(nextID, name, category, expiryDate, stock, unitPrice);
    }

    public void displayMedicineCategory() {
        System.out.println("\n");
        System.out.println("\t\t=================================================");
        System.out.println("\t\t|               Medicine Category               |");
        System.out.println("\t\t=================================================");
        System.out.println("\t\t|       1. Analgesics (Painkillers)             |");
        System.out.println("\t\t|       2. Antibiotics                          |");
        System.out.println("\t\t|       3. Antipyretics (Fever Reducers)        |");
        System.out.println("\t\t|       4. Antiseptics & Disinfectants          |");
        System.out.println("\t\t|       5. Antihistamines (Allergy Relief)      |");
        System.out.println("\t\t|       6. Cough and Cold Remedies              |");
        System.out.println("\t\t|       7. Anti-inflammatory Drugs              |");
        System.out.println("\t\t|       8. Back                                 |");
        System.out.println("\t\t=================================================");
    }

    public String promptCategory() {
        int choice;
        String category = "";

        displayMedicineCategory();
        System.out.println("");
        choice = Input.getIntegerInput("\t\tEnter your choice: ");

        while (choice < 1 || choice > 8) {
            MessageUI.errorMessage();
            choice = Input.getIntegerInput("\t\tEnter your choice: ");
        }
        switch (choice) {
            case 1:
                category = "Analgesics (Painkillers)";
                break;
            case 2:
                category = "Antibiotics";
                break;
            case 3:
                category = "Antipyretics (Fever Reducers)";
                break;
            case 4:
                category = "Antiseptics & Disinfectants";
                break;
            case 5:
                category = "Antihistamines (Allergy Relief)";
                break;
            case 6:
                category = "Cough & Cold Remedies";
                break;
            case 7:
                category = "Anti-inflammatory Drugs";
                break;
            case 8:
                category = "";
                break;
            default:
                displayInvalidChoiceMsg();
                break;
        }
        return category;
    }

    public int getEditOption() {
        int choice;
        System.out.println("");
        System.out.println("\t\t=============================");
        System.out.println("\t\t|        Edit Fields        |");
        System.out.println("\t\t=============================");
        System.out.println("\t\t|       1. Name             |");
        System.out.println("\t\t|       2. Category         |");
        System.out.println("\t\t|       3. Expiry Date      |");
        System.out.println("\t\t|       4. Add Stock        |");
        System.out.println("\t\t|       5. Unit Price       |");
        System.out.println("\t\t|       6. Back             |");
        System.out.println("\t\t=============================");

        choice = Input.getIntegerInput("\t\tEnter your choice: ");

        while (choice < 1 || choice > 6) {
            MessageUI.errorMessage();
            choice = Input.getIntegerInput("\t\tEnter your choice: ");
        }
        return choice;
    }

    public String promptName() {
        System.out.print("\t\tEnter name: ");
        return scanner.nextLine();
    }

    public Date promptExpiryDate() {
        boolean valid = false;
        String dateInput;
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        while (!valid) {
            try {
                System.out.print("\t\tEnter expiry date (dd-MM-yyyy): ");
                dateInput = scanner.nextLine();
                date = dateFormat.parse(dateInput);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, 2);
                Date twoMonthsLater = cal.getTime();

                // Check if date is more than 2 months from now
                if (date.after(twoMonthsLater)) {
                    valid = true;
                } else {
                    System.out.println("\t\tExpiry date must be more than 2 months from today.\n");
                }

            } catch (ParseException e) {
                System.out.println("\t\tInvalid date format. Please use dd-MM-yyyy.\n");
            }
        }
        return date;
    }

    public Date promptExpiryDateWithoutLimit() {
        String dateInput;
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            System.out.print("\t\tEnter expiry date (dd-MM-yyyy): ");
            dateInput = scanner.nextLine();
            date = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("\t\tInvalid date format. Please use dd-MM-yyyy.\n");
        }

        return date;
    }

    public int promptStock() {
        int stock;
        do {
            stock = Input.getIntegerInput("\t\tEnter stock (must be at least 30): ");
            if (stock < 30) {
                System.out.println("\t\tStock must be at least 30. Please try again.\n");
            }
        } while (stock < 30);
        return stock;
    }

    public double promptUnitPrice() {
        double up;
        do {
            up = Input.getDoubleInput("\t\tEnter unit price: ");
            if (up <= 0.00) {
                System.out.println("\t\tUnit price must not be 0.00. Please try again.\n");
            }
        } while (up <= 0.00);
        return up;
    }

    public String promptMedicineID() {
        return Input.getStringInput("\n\t\tEnter medicine ID: ");
    }

    public void displayMedicineDetails(Medicine medicineFound) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("");
        System.out.println("\t\t========================================================================");
        System.out.println("\t\t|                        Medicine Details                              |");
        System.out.println("\t\t========================================================================");
        System.out.printf("\t\t%25s %-45s|\n", "|   Medicine ID:       | ", medicineFound.getMedicineID());
        System.out.printf("\t\t%25s %-45s|\n", "|   Medicine name:     | ", medicineFound.getMedicineName());
        System.out.printf("\t\t%25s %-45s|\n", "|   Medicine category: | ", medicineFound.getMedicineCategory());
        System.out.printf("\t\t%25s %-45s|\n", "|   Expiry date:       | ", sdf.format(medicineFound.getMedicineExpiryDate()));
        System.out.printf("\t\t%25s %-45s|\n", "|   Stock :            | ", medicineFound.getMedicineStock());
        System.out.printf("\t\t%25s %-45.2f|\n", "|   Unit Price (RM):   | ", medicineFound.getMedicineUnitPrice());
        System.out.println("\t\t========================================================================");
    }

    public void displayMedicineListHeader() {
        System.out.println("");
        System.out.println("\t\t============================================================================================================================");
        System.out.println("\t\t|                                                          Medicine List                                                   |");
        System.out.println("\t\t============================================================================================================================");
    }

    public void displayMedicineFilterByStockLevelHeader(int value) {
        System.out.println("");
        System.out.println("\t\t============================================================================================================================");
        System.out.printf("\t\t|                                                Medicine List Under %3d threshold                                         |\n", value);
        System.out.println("\t\t============================================================================================================================");
    }

    public void displayMedicineFilterByExpiryDateHeader() {
        System.out.println("");
        System.out.println("\t\t============================================================================================================================");
        System.out.println("\t\t|                                                  Medicine List Filter By Date                                            |");
        System.out.println("\t\t============================================================================================================================");
    }

    public void displayMedicineFilterByCategoryHeader() {
        System.out.println("");
        System.out.println("\t\t============================================================================================================================");
        System.out.println("\t\t|                                                Medicine List Filter By Category                                          |");
        System.out.println("\t\t============================================================================================================================");
    }

    public void displayMedicineHeader() {
        System.out.printf("\t\t|  %-14s | %-20s | %-32s | %-14s | %-8s | %-15s  |\n", "Medicine ID", "Medicine Name", "Medicine Category", "Expiry Date", "Stock", "Unit Price (RM)");
        System.out.println("\t\t============================================================================================================================");
    }

    public void displayMedicineStatusListHeader() {
        System.out.println("");
        System.out.println("\t\t===============================================================================================================================================");
        System.out.println("\t\t|                                                                Medicine Status List                                                         |");
        System.out.println("\t\t===============================================================================================================================================");
    }

    public void displayMedicineStatusHeader() {
        System.out.printf("\t\t|  %-14s | %-20s | %-32s | %-14s | %-8s | %-15s | %-16s  |\n", "Medicine ID", "Medicine Name", "Medicine Category", "Expiry Date", "Stock", "Unit Price (RM)", "Status");
        System.out.println("\t\t===============================================================================================================================================");
    }

    public void displayOperationSuccessfullyMessage(String operation) {
        System.out.println("\t\tMedicine " + operation + " successfully.");
    }

    public void displayOperationErrorMessage(String operation) {
        System.out.println("\t\tFailed to " + operation + " medicine.");
    }

    public void displayRemoveExpiredMedicineSuccessfully() {
        System.out.println("\t\tAll expired items has been deleted successfully.");
    }

    public boolean promptDeleteMsg() {
        char choice = 'n';
        System.out.print("\t\tConfirm to delete (y/n): ");
        String line = scanner.nextLine().trim();

        if (!line.isEmpty()) {
            choice = line.charAt(0);
        }
        return choice == 'y' || choice == 'Y';
    }

    public void displayInvalidChoiceMsg() {
        System.out.println("\t\tInvalid choice, please try again.\n");
    }

    public void displayExceedLimitMsg() {
        System.out.println("\t\tYou have reached a maximum of 3 times.\n");
    }

    public int promptTopMedicineDispensedSummary() {
        return Input.getIntegerInput("\n\t\tPlease enter the top value you like: ");
    }

    public void displayDispensingSummaryReportUI() {
        System.out.println("");
        System.out.println("\t\t==========================================================================================");
        System.out.println("\t\t|                               Medicine Dispensing Summary                              |");
        System.out.println("\t\t==========================================================================================");
        System.out.printf("\t\t|  %-20s | %-32s | %-8s | %15s  |\n", "Medicine Name", "Category", "Quantity", "Unit Price (RM)");
        System.out.println("\t\t==========================================================================================");
    }

    public void displayTopDispensingSummaryReportUI(int top) {
        System.out.println("");
        System.out.println("\t\t==========================================================================================");
        System.out.printf("\t\t|                        Top %2d Medicine Dispensing Summary Report                       |\n", top);
        System.out.println("\t\t==========================================================================================");
        System.out.printf("\t\t|  %-20s | %-32s | %-8s | %15s  |\n", "Medicine Name", "Category", "Quantity", "Unit Price (RM)");
        System.out.println("\t\t==========================================================================================");
    }

    public void displayMedicineCustomizedToString(Medicine med) {
        System.out.print(med.customizedToString());
        System.out.println("\t\t------------------------------------------------------------------------------------------");
    }

    public int promptMedicineDispensingSummaryFilter() {
        int choice = 0;
        System.out.println("\t\tFilter Medicine Dispensed Records By: ");
        System.out.println("\t\t1. Top Medicine Dispensed");
        System.out.println("\t\t2. Group by category");
        System.out.println("\t\t3. Back");

        while (choice < 1 || choice > 3) {
            choice = Input.getIntegerInput("\t\tEnter your choice: ");
        }
        return choice;
    }

    public int promptFilterMedicineStockBy() {
        int choice;
        System.out.println("");
        System.out.println("\t\tFilter Medicine Stock By:");
        System.out.println("\t\t1. Stock Level");
        System.out.println("\t\t2. Expiry Date");
        System.out.println("\t\t3. Category");
        System.out.println("\t\t4. Back");

        choice = Input.getIntegerInput("\t\tEnter your choice: ");

        while (choice < 1 || choice > 4) {
            MessageUI.errorMessage();
            choice = Input.getIntegerInput("\t\tEnter your choice: ");
        }
        return choice;
    }

    public int promptStockLevelTreshold() {
        return Input.getIntegerInput("\n\t\tEnter a threshold value: ");
    }

    public void displayNoMedicineUnderThreshold(int threshold) {
        System.out.println("\n\t\tNo medicine found with stock below " + threshold);
    }

    public void displayNoMedicineBeforeExpiryDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\n\t\tNo medicine found with expiry date before " + sdf.format(date));
    }

    public void displayNoMedicineFoundWithinCategory(String category) {
        System.out.println("\n\t\tNo medicine found with " + category);
    }

    public void displayMedicineToString(Medicine med) {
        System.out.print(med.toString());
        System.out.println("\t\t----------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayAlertHeader() {
        System.out.println("");
        System.out.println("\t\t=================================================");
        System.out.println("\t\t|               Alert Notification              |");
        System.out.println("\t\t=================================================");
    }

    public void displayAlertMsg(int i, String msg) {
        System.out.printf("\t\t|\t%d. %s\t|\n", i, msg);
    }

    public void displayNoAlertMsg() {
        System.out.println("\n\t\tThere are no alert notifications\n");
    }

    public void displayAlertFooter() {
        System.out.println("\t\t=================================================");
        System.out.println("");
    }

    public boolean confirmDeleteExpiredItems() {
        char choice = 'n';
        System.out.print("\n\t\tConfirm to delete expired item(s) (y/n)? ");
        String line = scanner.nextLine().trim();

        if (!line.isEmpty()) {
            choice = line.charAt(0);
        }
        return choice == 'y' || choice == 'Y';
    }

    public boolean promptReturn() {
        char choice = 'n';

        do {
            System.out.println("");
            System.out.print("\t\tReturn back to previous page (y/n)? ");
            String line = scanner.nextLine().trim();

            if (!line.isEmpty()) {
                choice = line.charAt(0);
            }
        } while (choice != 'y' && choice != 'Y');

        return true;
    }

    public boolean promptViewMedicineDetails() {
        char choice = 'n';
        do {
            System.out.println("");
            System.out.print("\t\tView medicine details (y/n)? ");
            String line = scanner.nextLine().trim();

            if (!line.isEmpty()) {
                choice = line.charAt(0);
            }
        } while (choice != 'y' && choice != 'Y' && choice != 'n' && choice != 'N');

        return choice == 'y' || choice == 'Y';
    }

    public int promptSearchField() {
        System.out.println("");
        System.out.println("\t\tSearch By: ");
        System.out.println("\t\t1. ID");
        System.out.println("\t\t2. Name");
        System.out.println("\t\t3. Category");
        System.out.println("\t\t4. Back");
        return Input.getIntegerInput("\t\tEnter your choice: ");
    }

    public void displayMedicineNotFound() {
        System.out.printf("\t\t%s%s%s\n", RED, "No medicine found.", RESET);
    }

    public void displayWarningMedicineStockStatus(Medicine med) {
        System.out.printf("\t\t|  %-20s | %-32s | %-8d | %15.2f  |\n", med.getMedicineName(), med.getMedicineCategory(), med.getMedicineStock(), med.getMedicineUnitPrice());
        System.out.println("\t\t------------------------------------------------------------------------------------------");
    }

    public void displayMedicineStatusToString(Medicine med) {
        System.out.print(med.statusToString());
        System.out.println("\t\t-----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayMedicineCategoryHeader() {
        System.out.println("");
        System.out.println("\t\t=================================================");
        System.out.println("\t\t|  Category Medicine Dispensing Summary Report  |");
        System.out.println("\t\t=================================================");
        System.out.printf("\t\t|  %-32s | %-8s  |\n", "Category", "Quantity");
        System.out.println("\t\t=================================================");
    }

    public void displayMedicineListByCategory(Medicine med) {
        System.out.print(med.categoryToString());
        System.out.println("\t\t-------------------------------------------------");
    }

    public boolean promptUndo() {
        char choice = 'n';
        do {
            System.out.println("");
            System.out.print("\t\tUndo (y/n)? ");
            String line = scanner.nextLine().trim();

            if (!line.isEmpty()) {
                choice = line.charAt(0);
            }
        } while (choice != 'y' && choice != 'Y' && choice != 'n' && choice != 'N');

        return choice == 'y' || choice == 'Y';
    }

//    public int promptUndoSelection() {
//        int choice = 0;
//        System.out.println("\t\tUndo Menu: ");
//        System.out.println("\t\t1. Last created medicine");
//        System.out.println("\t\t2. Last edited medicine");
//        System.out.println("\t\t2. Last deleted medicine");
//        System.out.println("\t\t4. Back\n");
//        while (choice < 1 || choice > 4) {
//            choice = Input.getIntegerInput("\t\tEnter your choice: ");
//        }
//        return choice;
//    }
    public void displayExitMsg() {
        System.out.println("\n\t\tExiting Pharmacy Module.\n");
    }

    public void displayExpiredMedicineHeader() {
        System.out.println("\t\t===============================================================================================");
        System.out.println("\t\t|                                     Expired Medicine                                        |");
        System.out.println("\t\t===============================================================================================");
    }

    public void displayExpiredMedicine(Medicine med) {
        System.out.print(med.expiryToString());
        System.out.println("\t\t----------------------------------------------------------------------------------------------");
    }

    public boolean promptUndoLastAction() {
        char choice = 'n';
        System.out.print("\n\t\tConfirm to undo last action (y/n): ");
        String line = scanner.nextLine().trim();

        if (!line.isEmpty()) {
            choice = line.charAt(0);
        }
        return choice == 'y' || choice == 'Y';
    }

    public void displayNoLastAction() {
        System.out.println("\t\tUndo operation disable currently, does not have any last action found.");
    }
}
