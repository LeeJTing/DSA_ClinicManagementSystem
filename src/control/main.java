/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.Master;
import boundary.*;
import static boundary.menu.mainMenu;

/**
 *
 * @author Lwin
 */
public class main {

    public static void main(String[] args) {

        Master.initializer();
        PatientManagement patient = new PatientManagement();
        DoctorManagement doctor = new DoctorManagement();
        ConsultationManagement consult = new ConsultationManagement();
        MedicalTreatmentManagement medical = new MedicalTreatmentManagement();
        PharmacyManagementModule pharmacy = new PharmacyManagementModule();

        int option = 0;
        while (option != 3) {

            option = menu.mainMenuUI();
            switch (option) {
                case 1:
                    //patient
                    patient.PatientModule();
                    break;
                case 2:
                    // staff
                    int choice = 0;
                    while (choice != 5) {
                        choice = mainMenu();
                        switch (choice) {
                            case 1:
                                doctor.doctorManagementOuter();
                                break;
                            case 2:
                                consult.consultationMenu();
                                break;
                            case 3:
                                medical.treatmentMenu();
                                break;
                            case 4:
                                pharmacy.pharmacyMenu();
                                break;
                            default:
                                choice = 5;
                                break;
                        }
                    }
                    break;
                case 3:
                    // consultation
                    int selection = 0;
                    while (selection != 3) {
                        selection = menu.consultationMenu();
                        switch (selection) {
                            case 1:
                                String staffId = menu.askStaffID();
                                staffId = staffId.toUpperCase();
                                Master.setCurrentStaffId(staffId);

                                break;
                            case 2:
                                String patientId = menu.askPatientID();
                                patientId = patientId.toUpperCase();
                                Master.setCurrentPatientId(patientId);

                                break;
                            default:
                                selection = 3;
                                break;
                        }

                        break;
                    }
                default:
                    // exit
                    option = 3;
                    break;

            }
        }

    }
}
