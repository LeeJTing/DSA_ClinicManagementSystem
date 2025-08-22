/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.Input;
import entity.*;
import adt.MapInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class MedicalTreatmentUI {

    private final SimpleDateFormat DATEFORM = new SimpleDateFormat("dd-MM-yyyy");

    public int treatmentMenu() {

        System.out.println("        ====================================================");
        System.out.println("        =            Treatment Management Menu             =");
        System.out.println("        ====================================================");
        System.out.println("        =   1. Add treatment                               =");
        System.out.println("        =   2. Display Treatment Record                    =");
        System.out.println("        =   3. Modify Treatment Record                     =");
        System.out.println("        =   4. Delete Treatment Reocrd                     =");
        System.out.println("        =   5. Disease Prediction and Relationship Report  =");
        System.out.println("        =   6. Disease Analysis Report                     =");
        System.out.println("        =   7. Undo                                        =");
        System.out.println("        =   8. Exit                                        =");
        System.out.println("        ====================================================");

        System.out.print("       Selection(1-7): ");
        int option = Input.getIntegerInput();

        return option;
    }

    public int displayMenu() {
        System.out.print("""
                         
                                   ================================================================================
                                   =                                                                              =
                                   ================================================================================
                                   =                                                                              =
                                   =                   1. Search For Treatment ID                                 =
                                   =                   2. View Patient Medical Treatment Record                   =
                                   =                   3. All Medical Treatment Record                            =
                                   =                   0. Exit                                                    =
                                   =                                                                              =
                                   ================================================================================
                         
                         """);

        System.out.print("          Selection(0-3): ");
        return Input.getIntegerInput();
    }

    public void diseaseAnalysisReport(String high, int highCase, double highPercent, String low, int lowCase, double lowPercent,
            String ageRange1, int age1, double agePercent1, String ageRange2, int age2, double agePercent2, String ageRange3, int age3, double agePercent3,
            String disease1, int numDisease1, double diseasePercent1, String disease2, int numDisease2, double diseasePercent2, String disease3, int numDisease3, double diseasePercent3,
            int countMale, int countFemale) {
        System.out.printf("\n          +==============================================================================+\n");
        System.out.printf("          |                           DISEASE ANALYSIS REPORT                            |\n");
        System.out.printf("          +==============================================================================+\n");
        System.out.printf("          | Total Cases           : %-53d|\n", highCase + lowCase);
        System.out.printf("          | %-21s : %-4d (%-4.2f%%)                                        |\n", high, highCase, highPercent);
        System.out.printf("          | %-21s : %-4d (%-4.2f%%)                                        |\n", low, lowCase, lowPercent);
        System.out.printf("          +------------------------------------------------------------------------------+\n");
        System.out.printf("          | Age Distribution (Top 3 Groups)                                              |\n");
        System.out.printf("          |   %-5s : %-3d (%-4.1f%%)                                                        |\n", ageRange1, age1, agePercent1);
        System.out.printf("          |   %-5s : %-3d (%-4.1f%%)                                                        |\n", ageRange2, age2, agePercent2);
        System.out.printf("          |   %-5s : %-3d (%-4.1f%%)                                                        |\n", ageRange3, age3, agePercent3);
        System.out.printf("          +------------------------------------------------------------------------------+\n");
        System.out.printf("          | Top 3 Diseases                                                               |\n");
        System.out.printf("          |   %-17s: %-3d (%-4.1f%%)                                             |\n", disease1, numDisease1, diseasePercent1);
        System.out.printf("          |   %-17s: %-3d (%-4.1f%%)                                             |\n", disease2, numDisease2, diseasePercent2);
        System.out.printf("          |   %-17s: %-3d (%-4.1f%%)                                             |\n", disease3, numDisease3, diseasePercent3);
        System.out.printf("          +------------------------------------------------------------------------------+\n");
        System.out.printf("          | Bar Chart (All Patients by Gender)                                           |\n");
        System.out.printf("          | Male   : %-68s|\n", (("#").repeat(countMale) + " (" + countMale + ")"));
        System.out.printf("          | Female : %-68s|\n", (("#").repeat(countFemale) + " (" + countFemale + ")"));
        System.out.printf("          +==============================================================================+\n");
    }

    public void predictionRelationshipReportHeader() {
        System.out.printf("\n          +==============================================================================+\n");
        System.out.printf("          |                   DISEASE PREDICTION AND RELATIONSHIP REPORT                 |\n");
        System.out.printf("          +==============================================================================+\n");
    }

    public void diseasePatientHeader(String disease) {
        System.out.printf("          | Disease: %-58s\n", disease);
        System.out.printf("          | Patient Detials:                                                             |\n");
        System.out.printf("          +------------------------------------------------------------------------------+\n");
        System.out.printf("          | Patient ID | Name                      | Contact Number | Email Address      |\n");
        System.out.printf("          +------------------------------------------------------------------------------+\n");
    }

    public void diseasePatientDetials(String id, String name, String contact, String email) {
        System.out.printf("          | %-10s | %-25s | %-14s | %-18s |\n", id, name, contact, email);
    }

    public void lineReport() {
        System.out.printf("          +------------------------------------------------------------------------------+\n");
    }

    public void lastLineReport() {
        System.out.printf("          +==============================================================================+\n");

    }

    public void diseaseRelationship(String disease, double percent) {
        System.out.printf("          | %-23s (%-6.2f)                                             |\n", disease, percent);
    }

    public int updateMenu(String id) {
        int option;
        System.out.println("\n          Treatment ID: " + id);
        System.out.println("\n          Update: ");
        System.out.println("          1. Advice");
        System.out.println("          2. Disease");
        System.out.println("          3. Remark");
        System.out.println("          4. Scanner using");
        System.out.println("          5. Date of the treatment");
        System.out.println("          6. Prescription");
        System.out.println("          7. Exit");
        System.out.print("          Selection: ");
        option = Input.getIntegerInput();

        return option;
    }

    public String askTreatmentID() {
        System.out.print("\n          Medical Treatment ID(e.s. T000001): ");
        String id = Input.getStringInput();

        return id;
    }

    public String askConsultationID() {
        System.out.print("\n          Consultation ID(e.s. C000001): ");
        String id = Input.getStringInput();

        return id;
    }

    public String askDisease() {
        String disease;
        System.out.println("\n          Disease: ");
        System.out.println("          1. Hypertension");
        System.out.println("          2. Diabetes Type 2");
        System.out.println("          3. COVID-19");
        System.out.println("          4. Asthma");
        System.out.println("          5. Fracture");
        System.out.print("          Selection(1 - 5): ");
        int input = Input.getIntegerInput();
        switch (input) {
            case 1:
                disease = "Hypertension";
                break;
            case 2:
                disease = "Diabetes Type 2";
                break;
            case 3:
                disease = "COVID-19";
                break;
            case 4:
                disease = "Asthma";
                break;
            case 5:
                disease = "Fracture";
                break;
            default:
                disease = "Hypertension";
                break;
        }

        return disease;
    }

    public String askAdvice() {
        System.out.print("\n          Advice: ");
        String input = Input.getStringInput();

        return input;
    }

    public String askStaffID() {
        System.out.print("\n          Staff ID(e.s. S000001): ");
        String input = Input.getStringInput();

        return input;
    }

    public String askPatientID() {
        System.out.print("\n          Patient ID(e.s. P000001): ");
        String id = Input.getStringInput();

        return id;
    }

    public Date askTreatmentDate() {
        Date date = new Date();
        System.out.print("\n          Treatment Date(dd-mm-yyyy): ");
        String d = Input.getStringInput();
        try {
            date = DATEFORM.parse(d);
        } catch (ParseException ex) {
            Logger.getLogger(MedicalTreatmentUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

    public boolean askIsScan() {
        System.out.println("\n          Using a Scanner: ");
        System.out.println("          1. Yes");
        System.out.println("          2. No");
        System.out.print("          Selection(1-2): ");
        int input = Input.getIntegerInput();

        return input == 1;
    }

    public String askRemark() {
        System.out.print("\n          Remark: ");
        String input = Input.getStringInput();

        return input;
    }

    public Medicine askMedicine(MapInterface<String, Medicine> medicineList) {
        System.out.println("\n          Medicine List: ");
        Object[] obj = medicineList.getAllKeys();
        System.out.printf("\n          No. %-30s %-3s", "Medicine Name", "QTY");
        for (int i = 0; i < obj.length; i++) {
            String key = (String) obj[i];
            System.out.printf("\n          %-3d. %-30s %d", i + 1, key, medicineList.getValue(key).getMedicineStock());
        }

        System.out.printf("\n          Selection(1 - %d): ", obj.length);
        int optionName = Input.getIntegerInput();
        Iterator<Medicine> iter = medicineList.getIterator();
        String name = "";

        for (int i = 1; i <= optionName && iter.hasNext(); i++) {
            name = iter.next().getMedicineName();
        }
        System.out.print("          Quantity: ");
        int quantity = Input.getIntegerInput();

        return new Medicine(name, quantity);
    }

    public String askToContinue() {
        System.out.print("\n          Continue(Y/N): ");
        String option = Input.getStringInput();

        return option;
    }

    public void noRecord(String recordType) {
        System.out.println("\n          Not found the " + recordType + "!!");
    }

    public void tryAgain() {
        System.out.println("          Please Try Again!!");
    }

    public void addNewConsultationNotFoundCompleted() {
        System.out.println("\n          Consultation Record Not Found or already Completed!!\n");
    }

    public void showCurrentTreatmentID(String id) {
        System.out.println("\n          Medical Treatment ID: " + id + "\n");
    }

    public void showAllTreatmentID(String[] treatmentId) {
        System.out.println("");
        for (int i = 0; i < treatmentId.length; i++) {
            System.out.println("          " + (i + 1) + ". " + treatmentId[i]);
        }
    }

    public void showDate(Date date) {
        System.out.println("\n          Date: " + DATEFORM.format(date));
    }

    public void showStaffIDName(Staff staff) {
        System.out.println("\n          Staff ID   : " + staff.getStaffID());
        System.out.println("          Staff Name : " + staff.getStaffName() + "\n");
    }

    public void showPatientIDName(Patient patient) {
        System.out.println("\n          Patient ID   : " + patient.getPatient_id());
        System.out.println("          Patient Name : " + patient.getPatient_name() + "\n");
    }

    public void updatedItem(String what) {
        System.out.println("\n          " + what + " was updated!!");
    }

    public void deleteTreatment() {
        System.out.println("\n          Delete: ");
        System.out.println("          Medical Treatment List:");
    }

    public void updateTreatment() {
        System.out.println("\n          Update Treatment Record: ");
    }

    public void showUpdatedTreatment(Treatment treatment, Prescription prescription, String staffName, String patientName) {
        displaySpecificTreatmentRecord(treatment, prescription, staffName, patientName);
    }

    public void removedTreatment(Treatment treatment, Prescription prescription, String staffName, String patientName) {
        displaySpecificTreatmentRecord(treatment, prescription, staffName, patientName);
        System.out.println("\n          " + treatment.getTreatment_id() + " and " + prescription.getPrescription_id() + " was removed.");
    }

    public void displaySpecificTreatmentRecord(Treatment treatment, Prescription prescription, String staffName, String patientName) {

        String[] remark_parts = treatment.getRemark().split(",");
        String[] treatment_advice = treatment.getTreatment_advice().split(",");

        //    output length = 91
        System.out.printf("\n          +===============================================================================+\n");
        System.out.printf("          |                            Medical Treatment Record                           |\n");
        System.out.printf("          +===============================================================================+\n");
        System.out.printf("          |                                                                               |\n");
        System.out.printf("          |  Treatment ID    : %-59s|\n", treatment.getTreatment_id());
        System.out.printf("          |                                                                               |\n");
        System.out.printf("          |  Treatment Date  : %-59s|\n", DATEFORM.format(treatment.getTreatment_date()));
        System.out.printf("          |                                                                               |\n");
        System.out.printf("          |  Consultation ID : %-59s|\n", treatment.getConsultation_id());
        System.out.printf("          |                                                                               |\n");
        System.out.printf("          |  Consultant ID   : %-7s      Consultant Name : %-28s|\n", treatment.getStaff_id(), staffName);
        System.out.printf("          |                                                                               |\n");
        System.out.printf("          |  Patient ID      : %-7s      Patient Name    : %-28s|\n", treatment.getPatient_id(), patientName);
        System.out.printf("          |                                                                               |\n");
        System.out.printf("          |  Disease         : %-59s|\n", treatment.getDisease());
        System.out.printf("          |                                                                               |\n");
        System.out.printf("          |  Using a Scanner : %-59s|\n", treatment.getIsScan() ? "Yes" : "No");
        System.out.printf("          |                                                                               |\n");
        for (int i = 0; i < remark_parts.length; i++) {
            remark_parts[i] = remark_parts[i] + ((i == remark_parts.length - 1) ? "" : ",");

            if (i == 0) {
                System.out.printf("          |  Remarks         : %-59s|\n", remark_parts[i]);
            } else {
                System.out.printf("          |                   %-60s|\n", remark_parts[i]);
            }
        }
        System.out.printf("          |                                                                               |\n");

        for (int i = 0; i < treatment_advice.length; i++) {
            treatment_advice[i] = treatment_advice[i] + ((i == treatment_advice.length - 1) ? "" : ",");
            if (i == 0) {
                System.out.printf("          |  Advice          : %-59s|\n", treatment_advice[i]);
            } else {
                System.out.printf("          |                   %-60s|\n", treatment_advice[i]);
            }
        }
        System.out.printf("          |                                                                               |\n");
        System.out.printf("          +===============================================================================+\n");
        System.out.printf("          |                                  Prescription                                 |\n");
        System.out.printf("          +===============================================================================+\n");
        System.out.printf("          |  Medicine ID  |  Medicine Name                   |    QTY    |  Expired Date  |\n");
        System.out.printf("          +-------------------------------------------------------------------------------+\n");
        MapInterface<String, Medicine> prescriptionList = prescription.getMedicineList();
        prescriptionList.sorting();
        Iterator<Medicine> iter = prescriptionList.getIterator();

        Medicine medicine;
        while (iter.hasNext()) {
            medicine = iter.next();
            System.out.printf("          |  %-12s |  %-31s |    %3d    |   %-10s   |\n", medicine.getMedicineID(), medicine.getMedicineName(),
                    medicine.getMedicineStock(), DATEFORM.format(medicine.getMedicineExpiryDate()));
        }
        System.out.printf("          +-------------------------------------------------------------------------------+\n");
        System.out.printf("          |  Total Price: RM %-61.2f|\n", prescription.getMedicine_total_cost());
        System.out.printf("          +===============================================================================+\n");
    }

}
