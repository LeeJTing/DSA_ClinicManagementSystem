package entity;

import adt.ChainBucket;
import adt.MapInterface;
import java.time.LocalDate;

/**
 *
 * @author Wong Wei Xin
 */
public class DutySchedule {

    private LocalDate dutyDate;
    private MapInterface<Integer, String> doctorA;
    private MapInterface<Integer, String> doctorB;
    private MapInterface<LocalDate,  MapInterface<Integer, String>> dutySchedule;

    //parameterized contructor
    public DutySchedule(LocalDate dutyDate, MapInterface<Integer, String> doctorA, MapInterface<Integer, String> doctorB) {
        this.dutyDate = dutyDate;
        this.doctorA = doctorA;
        this.doctorB = doctorB;
        this.dutySchedule = new ChainBucket<>();
    }

    //getter
    public MapInterface<Integer, String> getGroupA() {
        return doctorA;
    }

    public MapInterface<Integer, String> getGroupB() {
        return doctorB;
    }

    public MapInterface<LocalDate,  MapInterface<Integer, String>> getDutySchedule() {
        return dutySchedule;
    }

    public LocalDate getDutyDate (){
        return dutyDate;
    }
    //setter
    public void setGroupA(MapInterface<Integer, String> doctorA) {
        this.doctorA = doctorA;
    }

    public void setGroupB(MapInterface<Integer, String> doctorB) {
        this.doctorB = doctorB;
    }

    public void setDutySchedule(MapInterface<LocalDate, MapInterface<Integer, String>> dutySchedule) {
        this.dutySchedule = dutySchedule;
    }
    
    public void setDutyDate(LocalDate dutyDate){
        this.dutyDate = dutyDate; 
    }
}
