import java.util.ArrayList;

/**
 * Created by attila.deak on 8/5/2016.
 */
public class Calendar {


    private ArrayList<Driver> calendarDrivers;

    public Calendar(ArrayList driverList) {
        this.calendarDrivers = driverList;
    }

    public ArrayList<Driver> getCalendarDrivers() {
        return calendarDrivers;
    }

    public void setCalendarDrivers(ArrayList<Driver> calendarDrivers) {
        this.calendarDrivers = calendarDrivers;
    }
}
