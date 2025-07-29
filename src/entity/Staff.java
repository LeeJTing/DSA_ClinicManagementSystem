/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Wong Wei Xin
 */
public class Staff {
    private String staff_id;
    private String staff_password;
    private String staff_name;
    private String staff_position;
    private String staff_contact;
    private String staff_email;
    private int education_level;
    private int service_duration;
    private String dutyStatus;
    private LocalDate joined_date;
    private LocalDate pendingLeaveDate;
    
    //Default Constuctor
    public Staff(){
        this.staff_id = "";
        this.staff_password = "";
        this.staff_name = "";
        this.staff_position = "";
        this.staff_contact = "";
        this.staff_email = "";
        this.education_level = 0;
        this.service_duration = 0;
        this.dutyStatus = "";
    }
    
    //parameterized constructor
    public Staff(String staff_id, String staff_password, String staff_name, String staff_position, String staff_contact, String staff_email, int education_level, int service_duration, String dutyStatus, String joined_dateStr, String pendingLeaveDate  ){
        this.staff_id = staff_id;
        this.staff_password = staff_password;
        this.staff_name = staff_name;
        this.staff_position = staff_position;
        this.staff_contact = staff_contact;
        this.staff_email = staff_email;
        this.education_level = education_level;
        this.service_duration = service_duration;
        this.dutyStatus = dutyStatus;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.joined_date = LocalDate.parse(joined_dateStr, formatter);
        if (pendingLeaveDate != null) {
            this.pendingLeaveDate = LocalDate.parse(pendingLeaveDate, formatter);
        } else {
            this.pendingLeaveDate = null;
        }    
    }
    
    //getter
    public String getStaffID(){
        return staff_id; 
    }
    
     public String getStaffPassword(){
        return staff_password; 
    }    
     
     public String getStaffName(){
        return staff_name; 
    }    
     
     public String getStaffPosition(){
        return staff_position; 
    }    
     
     public String getStaffContact(){
        return staff_contact; 
    }    
     
     public String getStaffEmail(){
        return staff_email; 
    }    
     
     public int getEducationalLevel(){
        return education_level; 
    }
          
     public int getServiceDuration(){
        return service_duration; 
    }     
     
     public String getDutyStatus(){
        return dutyStatus; 
    }     
     
     public LocalDate getJoinedDate(){
        return joined_date; 
    }
     
     public LocalDate getPendingLeaveDate(){
         return pendingLeaveDate;
     }
     
     //setter
    public void setStaffID(String staff_id){
        this.staff_id = staff_id;    
    }
    
     public void setStaffPassword(String staff_password){
        this.staff_password = staff_password;
     }    
     
     public void setStaffName(String staff_name){
        this.staff_name = staff_name;
    }    
     
     public void setStaffPosition(String staff_position){
        this.staff_position = staff_position;
    }    
     
     public void setStaffContact(String staff_contact){
        this.staff_contact = staff_contact;
      }    
     
     public void setStaffEmail(String staff_email){
         this.staff_email = staff_email; 
    }    
     
     public void setEducationalLevel(int education_level){
        this.education_level = education_level; 
    }
          
     public void setServiceDuration(int service_duration){
        this.service_duration = service_duration; 
    }     
     
     public void setDutyStatus(String dutyStatus){
        this.dutyStatus = dutyStatus; 
    }     
     
    public void setPendingLeaveDate(LocalDate pendingLeaveDate){
        this.pendingLeaveDate = pendingLeaveDate;
    }
   
}
