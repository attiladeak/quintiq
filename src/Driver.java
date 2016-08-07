import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by attila.deak on 8/5/2016.
 */
public class Driver {
    private String name;
    private ArrayList<Integer> availableBuses;
    private ArrayList<Integer> preferredDayOffs;
    private ArrayList<Integer> preferredShifts;
    private ArrayList<Integer> dayOffs;

    private HashMap<Integer,Shift> driverCalendar;

    public Driver(String name, ArrayList<Integer> availableBuses, ArrayList<Integer> preferredDayOffs, ArrayList<Integer> preferredShifts, ArrayList<Integer> dayOffs) {
        this.name = name;
        this.availableBuses = availableBuses;
        this.preferredDayOffs = preferredDayOffs;
        this.preferredShifts = preferredShifts;
        this.dayOffs = dayOffs;

        this.driverCalendar = new HashMap<Integer, Shift>();

        for(int i=0; i<24; i++){
            this.driverCalendar.put(i,new Shift());
        }

    }

    public ArrayList<Integer> getAvailableBuses() {
        return availableBuses;
    }

    public void setAvailableBuses(ArrayList<Integer> availableBuses) {
        this.availableBuses = availableBuses;
    }

    public ArrayList<Integer> getPreferredDayOffs() {
        return preferredDayOffs;
    }

    public void setPreferredDayOffs(ArrayList<Integer> preferredDayOffs) {
        this.preferredDayOffs = preferredDayOffs;
    }

    public ArrayList<Integer> getPreferredShifts() {
        return preferredShifts;
    }

    public void setPreferredShifts(ArrayList<Integer> preferredShifts) {
        this.preferredShifts = preferredShifts;
    }

    public ArrayList<Integer> getDayOffs() {
        return dayOffs;
    }

    public void setDayOffs(ArrayList<Integer> dayOffs) {
        this.dayOffs = dayOffs;
    }

    public HashMap<Integer, Shift> getDriverCalendar() {
        return driverCalendar;
    }

    public void setDriverCalendar(HashMap<Integer, Shift> driverCalendar) {
        this.driverCalendar = driverCalendar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
