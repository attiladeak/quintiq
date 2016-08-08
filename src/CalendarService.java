//New code for testing Eclipse


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by attila.deak on 8/5/2016.
 */
public class CalendarService {
    Calendar calendar;

    public CalendarService(Calendar calendar){
        this.calendar = calendar;
    }

    DriverHelper driverHelp = new DriverHelper();


    public void generateCalendar(Calendar calendar){
        initialize(calendar);
    };

    public void initialize(Calendar calendar){
        ArrayList<Driver> drivers = calendar.getCalendarDrivers();
        for(Driver driver : drivers){
            HashMap<Integer,Shift> shiftMap = driver.getDriverCalendar();
            for(Integer shiftNr : shiftMap.keySet()){
                if(driver.getPreferredShifts().contains(shiftNr + 1)){
                    shiftMap.get(shiftNr).setTaken(true);
                    shiftMap.get(shiftNr).setBus(driver.getAvailableBuses().get(0));
                }
            }
        }

        for(int i = 0; i < 24; i++){
            ArrayList<Integer> missingLines = missingLines(i);
            if(missingLines.size() > 0){
                for (Integer line : missingLines){
                    for(Driver driver : drivers){
                        Shift currentShift = driver.getDriverCalendar().get(i);
                        if(driver.getAvailableBuses().contains(line) && !currentShift.isTaken() && !missingLines(i).isEmpty()){
                            driverHelp.setShift(driver, i, line);
                            //missingLines.remove(line);
                            break;
                        }
                    }
                }
            }
        }

    }



    public void drawCalendar(Calendar calendar){
        ArrayList<Driver> drivers = (ArrayList) calendar.getCalendarDrivers();

        for(Driver driver : drivers){
            System.out.print(driver.getName()+ ": ");


            for(Integer shiftNr : driver.getDriverCalendar().keySet()){
                System.out.print(shiftNr + " (" + driver.getDriverCalendar().get(shiftNr).getBus() + "), ");
            }
            System.out.println("");
            System.out.println("===================================================================================================================================================================");

        }
    }

    public ArrayList<Integer> missingLines(Integer shiftNr){
        ArrayList<Integer> lines = new ArrayList(Arrays.asList(1,2,3));

            for (Driver driver : calendar.getCalendarDrivers()){
                Integer busNr = driver.getDriverCalendar().get(shiftNr).getBus();
                if(busNr == 1 || busNr == 2 || busNr == 3){
                    lines.remove(busNr);                }
            }
        return lines;
    }

}
