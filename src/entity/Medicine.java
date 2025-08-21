/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import utility.MessageUI;

/**
 *
 * @author Teh Zhi Qin
 */
public class Medicine implements Comparable<Medicine> {

    private String medicine_id;
    private String medicine_name;
    private String medicine_category;
    private Date medicine_expiryDate;
    private int medicine_stock;
    private double unit_price;
    private String status;
    private static String compare = "medicine_stock";

    public Medicine() {
        this.medicine_id = "";
        this.medicine_name = "";
        this.medicine_category = "";
        this.medicine_expiryDate = null;
        this.medicine_stock = 0;
        this.unit_price = 0.0;
    }

    public Medicine(String category) {
        this.medicine_category = category;
    }

    public Medicine(String medicineName, int medicineDispensedStock) {
        this.medicine_name = medicineName;
        this.medicine_stock = medicineDispensedStock;
    }

    public Medicine(String medicineName, String medicineCategory, int medicineStock, double medicineUnitPrice) {
        this.medicine_name = medicineName;
        this.medicine_category = medicineCategory;
        this.medicine_stock = medicineStock;
        this.unit_price = medicineUnitPrice;
    }

    public Medicine(String medicineID, String medicineName, String medicineCategory, Date expiryDate, int medicineStock, double medicineUnitPrice) {
        this.medicine_id = medicineID;
        this.medicine_name = medicineName;
        this.medicine_category = medicineCategory;
        this.medicine_expiryDate = expiryDate;
        this.medicine_stock = medicineStock;
        this.unit_price = medicineUnitPrice;
    }
    
    public Medicine(Medicine med) {
        this.medicine_id = med.getMedicineID();
        this.medicine_name = med.getMedicineName();
        this.medicine_category = med.getMedicineCategory();
        this.medicine_expiryDate = med.getMedicineExpiryDate();
        this.medicine_stock = med.getMedicineStock();
        this.unit_price = med.getMedicineUnitPrice();
    }

    public Medicine(Medicine med, String status) {
        this.medicine_id = med.getMedicineID();
        this.medicine_name = med.getMedicineName();
        this.medicine_category = med.getMedicineCategory();
        this.medicine_expiryDate = med.getMedicineExpiryDate();
        this.medicine_stock = med.getMedicineStock();
        this.unit_price = med.getMedicineUnitPrice();
        this.status = status;
    }

    public String getMedicineID() {
        return medicine_id;
    }

    public String getMedicineName() {
        return medicine_name;
    }

    public String getMedicineCategory() {
        return medicine_category;
    }

    public Date getMedicineExpiryDate() {
        return medicine_expiryDate;
    }

    public int getMedicineStock() {
        return medicine_stock;
    }

    public double getMedicineUnitPrice() {
        return unit_price;
    }

    public String getMedicineStatus() {
        return status;
    }

    public void setMedicineID(String medicineID) {
        this.medicine_id = medicineID;
    }

    public void setMedicineName(String medicineName) {
        this.medicine_name = medicineName;
    }

    public void setMedicineCategory(String medicineCategory) {
        this.medicine_category = medicineCategory;
    }

    public void setMedicineExpiryDate(Date expiryDate) {
        this.medicine_expiryDate = expiryDate;
    }

    public void setMedicineStock(int medicineStock) {
        this.medicine_stock = medicineStock;
    }

    public void setMedicineUnitPrice(double unitPrice) {
        this.unit_price = unitPrice;
    }

    public void updateMedicineStock(int stock) {
        this.medicine_stock -= stock;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(medicine_expiryDate);

        return String.format("\t\t|  %-14s | %-20s | %-32s | %-14s | %-8d | %15.2f  |\n", medicine_id, medicine_name, medicine_category, formattedDate, medicine_stock, unit_price);
    }

    public String customizedToString() {
        return String.format("\t\t|  %-20s | %-32s | %-8d | %15.2f  |\n", medicine_name, medicine_category, medicine_stock, unit_price);
    }

    public String statusToString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(medicine_expiryDate);
        if (status.equals("Good")) {
            return String.format("\t\t|  %-14s | %-20s | %-32s | %-14s | %-8d | %15.2f | %-16s  |\n", medicine_id, medicine_name, medicine_category, formattedDate, medicine_stock, unit_price, status);
        } else {
            return String.format("\t\t|  %-14s | %-20s | %-32s | %-14s | %-8d | %15.2f | %s%-16s%s  |\n", medicine_id, medicine_name, medicine_category, formattedDate, medicine_stock, unit_price, MessageUI.RED, status, MessageUI.RESET);
        }
    }

    public String categoryToString() {
        return String.format("\t\t|  %-32s | %-8d  |\n", medicine_name, medicine_stock);
    }

    public String expiryToString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(medicine_expiryDate);

        return String.format("\t\t|  %-14s | %-20s | %-32s | %-14s  |\n", medicine_id, medicine_name, medicine_category, formattedDate);
    }

    @Override
    public int compareTo(Medicine other) {
        if (this.compare.equals("medicine_stock")) {
            return Integer.compare(this.medicine_stock, other.medicine_stock);
        } else if (this.compare.equals("medicine_stock_asc")) {
            return Integer.compare(other.medicine_stock, this.medicine_stock);
        } else if (this.compare.equals("category")) {
            return this.medicine_category.compareTo(other.medicine_category);
        } else {
            return other.medicine_expiryDate.compareTo(this.medicine_expiryDate);
        }
    }

}
