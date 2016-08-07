import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by attila.deak on 8/5/2016.
 */
public class DriverHelper {

    public void setShift(Driver driver, Integer shiftNr, Integer bus){
        Shift shift = driver.getDriverCalendar().get(shiftNr);
        shift.setBus(bus);
        shift.setTaken(true);

        driver.getDriverCalendar().put(shiftNr, shift);
    }

    public void emptyShift(Driver driver, Integer shiftNr){
        Shift shift = driver.getDriverCalendar().get(shiftNr);
        shift.setBus(0);
        shift.setTaken(false);

        driver.getDriverCalendar().put(shiftNr, shift);
    }


    public Shift getShiftDetails(Driver driver, Integer shiftNr){
        return driver.getDriverCalendar().get(shiftNr);
    }

    public Integer calculatePMShifts(Driver driver){
        Integer pmNr = 0;

        for(Integer shiftNr : driver.getDriverCalendar().keySet()){
            Shift shift = driver.getDriverCalendar().get(shiftNr);
            if(shift.isTaken() && shiftNr%2 != 0){
                pmNr ++;
            }
        }
        return pmNr;
    }

    public ArrayList<Integer> sameDayViolations(Driver driver){
        ArrayList<Integer> amShifts = new ArrayList<Integer>();
        HashMap<Integer, Shift> driverCal = driver.getDriverCalendar();
        for(Integer shiftNr : driverCal.keySet()){
            if(shiftNr%2 == 0 && driverCal.get(shiftNr).isTaken() && driverCal.get(shiftNr + 1).isTaken()){
                amShifts.add(shiftNr);
            }
        }
        return amShifts;
    }

    public ArrayList<Integer> overnightViolations(Driver driver){
        ArrayList<Integer> amShifts = new ArrayList<Integer>();
        HashMap<Integer, Shift> driverCal = driver.getDriverCalendar();
        for(Integer shiftNr : driverCal.keySet()){
            if(shiftNr%2 != 0 && driverCal.get(shiftNr).isTaken() && driverCal.get(shiftNr + 1).isTaken()){
                amShifts.add(shiftNr);
            }
        }
        return amShifts;
    }


}
