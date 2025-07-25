/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Teh Zhi Qin
 */
public class Medicine {

    private String medicine_id = "M0001";
    private String medicine_name;
    private String medicine_category;
    private String medicine_expiryDate;
    private int medicine_stock;
    private double unit_price;

    public Medicine() {
        this.medicine_id = "";
        this.medicine_name = "";
        this.medicine_category = "";
        this.medicine_expiryDate = "";
        this.medicine_stock = 0;
        this.unit_price = 0.0;
    }

    public Medicine(String medicineID, String medicineName, String medicineCategory, String expiryDate, int medicineStock, double medicineUnitPrice) {
        this.medicine_id = medicineID;
        this.medicine_name = medicineName;
        this.medicine_category = medicineCategory;
        this.medicine_expiryDate = expiryDate;
        this.medicine_stock = medicineStock;
        this.unit_price = medicineUnitPrice;
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

    public String getMedicineExpiryDate() {
        return medicine_expiryDate;
    }

    public int getMedicineStock() {
        return medicine_stock;
    }

    public double getMedicineUnitPrice() {
        return unit_price;
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

    public void setMedicineExpiryDate(String expiryDate) {
        this.medicine_expiryDate = expiryDate;
    }

    public void setMedicineStock(int medicineStock) {
        this.medicine_stock = medicineStock;
    }

    public void setMedicineUnitPrice(double unitPrice) {
        this.unit_price = unitPrice;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-20s %-10s %-10s %-5d %-6.2f\n", medicine_id, medicine_name, medicine_category, medicine_expiryDate, medicine_stock, unit_price);
    }
}
