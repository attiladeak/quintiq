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

        for(int i = 0; i < 28; i++){
            ArrayList<Integer> missingLines = missingLines(i);
            if(missingLines.size() > 0){
                for (Integer line : missingLines){
                    for(Driver driver : drivers){
                    	if(passPrimaryConstraints(i, driver, line) && (isAM(i) || (!isAM(i) && passFourPMConstraint(driver)))){
	                 
                    		driverHelp.setShift(driver, i, line);
	                        //missingLines.remove(line);
                    	}
                    }
                }
            }
        }

    }
    
    
    public boolean passPrimaryConstraints(int i, Driver driver, Integer line){
    	boolean pass = false;
    	
    	HashMap<Integer, Shift> driverCal = driver.getDriverCalendar();
    	
    	
    	boolean isDayOff = driver.getDayOffs().contains(i+1);
    	Shift currentShift = driverCal.get(i);
    	Shift nextShift = i == 27 ? new Shift() : driverCal.get(i + 1);
    	Shift prevShift = i == 0 ? new Shift() : driverCal.get(i - 1);
        if(driver.getAvailableBuses().contains(line) && !currentShift.isTaken() && !missingLines(i).isEmpty() && !nextShift.isTaken() && !prevShift.isTaken() && !isDayOff){
        	pass = true;
        }
    	
    	return pass;
    }
    
    public boolean passFourPMConstraint(Driver driver){
    	boolean pass = driverHelp.calculatePMShifts(driver) > 4;;
    	
    	return pass;
    }
    
    public boolean isAM(int i){
    	if(i%2 == 0){
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    



    public void drawCalendar(Calendar calendar){
        ArrayList<Driver> drivers = (ArrayList) calendar.getCalendarDrivers();
        
        System.out.print("l: ");
        
        for(int i = 0; i < 28; i++){
        	int nr = i + 1;
        	System.out.print(" (" + nr + ")");
        }
        
        System.out.println("");
        
        for(Driver driver : drivers){
            System.out.print(driver.getName()+ ": ");


            for(Integer shiftNr : driver.getDriverCalendar().keySet()){
                System.out.print(" (" + driver.getDriverCalendar().get(shiftNr).getBus() + ")");
            }
            System.out.println("");
//            System.out.println("===================================================================================================================================================================");

        }
        System.out.println("");
        System.out.println("Score: " + calculateScore());
        System.out.println("Missing shifts: " + nrOfMissingShifts() * 20);
        System.out.println("PM AM violations: " + nrOfPMAMs() * 30);
        System.out.println("Over 3 PM violations: " + nrOfOverThreePMs() * 10);
        System.out.println("Nr of four PM violations: " + nrOfNotFourPMs() * 8);
     
        System.out.println("Shift pref points: " + nrOfShiftPrefsMet() * 3);
        System.out.println("Day off preference points: " + nrOfDOPrefsMet() * 4);
        System.out.println("Long rest points: " + nrOfLongRests() * 5);
        
        
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
    
    public Integer calculateScore(){
    	Integer score = 0;
    	Integer missingShiftPoints = nrOfMissingShifts() * 20;
    	Integer PMAMViolationPoints = nrOfPMAMs() * 30;
    	Integer overThreePMViolationPoints = nrOfOverThreePMs() * 10;
    	Integer notFourPMsViolationPoints = nrOfNotFourPMs() * 8;
    	
    	Integer shiftPreferencePoints = nrOfShiftPrefsMet() * 3;
    	Integer dayOffPreferencePoints = nrOfDOPrefsMet() * 4;
    	Integer longRestPoints = nrOfLongRests() * 5;
    	
    	score -= missingShiftPoints;
    	score -= PMAMViolationPoints;
    	score -= overThreePMViolationPoints;
    	score -= notFourPMsViolationPoints;
    	
    	score += shiftPreferencePoints;
    	score += dayOffPreferencePoints;
    	score += longRestPoints;
    	
    	
    	return score;
    }

    
    public Integer nrOfMissingShifts(){
    	Integer nr = 0;
    	
    	for(int i = 0; i < 28; i++){
    		nr =+ missingLines(i).size();
    	}
    	return nr;
    }
    
    public Integer nrOfPMAMs(){
    	Integer nr = 0;
    	
    	for(Driver driver : calendar.getCalendarDrivers()){
        	for(int i = 0; i < 28; i++){
        		if(i < 27 && !isAM(i) && driver.getDriverCalendar().get(i).isTaken() && driver.getDriverCalendar().get(i+1).isTaken()){
        			nr ++;
        		}
        	}
    	}
    	return nr;
    }
    
    public Integer nrOfSameDayShifts(){
    	Integer nr = 0;
    	
    	for(Driver driver : calendar.getCalendarDrivers()){
        	for(int i = 0; i < 28; i++){
        		if(i < 23 && isAM(i) && driver.getDriverCalendar().get(i+1).isTaken()){
        			nr ++;
        		}
        	}
    	}
    	return nr;
    }
    
    public Integer nrOfOverThreePMs(){
    	Integer nr = 0;
    	
    	for(Driver driver : calendar.getCalendarDrivers()){
        	for(int i = 0; i < 28; i++){
        		boolean nextPMTaken = i < 26 && driver.getDriverCalendar().get(i+2).isTaken();
        		boolean nextPMTaken2 = i < 24 && driver.getDriverCalendar().get(i+4).isTaken();
        		boolean nextPMTaken3 = i < 22 && driver.getDriverCalendar().get(i+6).isTaken();
        		boolean nextPMTaken4 = i < 20 && driver.getDriverCalendar().get(i+8).isTaken();
        		boolean nextPMTaken5 = i < 18 && driver.getDriverCalendar().get(i+10).isTaken();
        		boolean nextPMTaken6 = i < 16 && driver.getDriverCalendar().get(i+12).isTaken();
        		boolean nextPMTaken7 = i < 14 && driver.getDriverCalendar().get(i+14).isTaken();
        		
        		if(i < 22 && !isAM(i) && nextPMTaken && nextPMTaken2 && nextPMTaken3){
        			nr ++;
        			if(nextPMTaken4){
        				nr ++;
        				if(nextPMTaken5){
        					nr ++;
        					if(nextPMTaken6){
        						nr ++;
        						if(nextPMTaken7){
        							nr ++;
        						}
        						
        					}
        				}
        			}
        		}
        	}
    	}
    	return nr;
    }
    
    public Integer nrOfNotFourPMs(){
    	Integer nr = 0;
    	
    	for(Driver driver : calendar.getCalendarDrivers()){
    		if(driverHelp.calculatePMShifts(driver) != 4){
    			nr ++;
    		}
    	}
    	return nr;
    }
    
    public Integer nrOfShiftPrefsMet(){
    	Integer nr = 0;
    	
    	for(Driver driver : calendar.getCalendarDrivers()){
        	for(int i = 0; i < 28; i++){
    			if(driver.getDriverCalendar().get(i).isTaken() && driver.getPreferredShifts().contains(i+1)){
    				nr ++;
    			}
    		}
    	}
    	return nr;
    }
    
    public Integer nrOfDOPrefsMet(){
    	Integer nr = 0;
    	
    	for(Driver driver : calendar.getCalendarDrivers()){
        	for(int i = 0; i < 28; i++){
    			if(!driver.getDriverCalendar().get(i).isTaken() && driver.getPreferredDayOffs().contains(i+1)){
    				nr ++;
    			}
    		}
    	}
    	return nr;
    }
    
    public Integer nrOfLongRests(){
    	Integer nr = 0;
    	
    	for(Driver driver : calendar.getCalendarDrivers()){
        	for(int i = 0; i < 28; i++){
        		
        		
        		boolean previousDayWorking = false;
        		if(i>2){
        			previousDayWorking = driver.getDriverCalendar().get(i-1).isTaken() || driver.getDriverCalendar().get(i-2).isTaken() ? true : false;
        		}
        		
        		boolean isTaken = driver.getDriverCalendar().get(i).isTaken();
        		boolean nextTaken = i < 26 && driver.getDriverCalendar().get(i+1).isTaken();
        		boolean nextTaken2 = i < 25 && driver.getDriverCalendar().get(i+2).isTaken();
        		boolean nextTaken3 = i < 24 && driver.getDriverCalendar().get(i+3).isTaken();
        		boolean nextTaken4 = i < 23 && driver.getDriverCalendar().get(i+4).isTaken();
        		boolean nextTaken5 = i < 22 && driver.getDriverCalendar().get(i+5).isTaken();

        		
        		if(i < 24 && isAM(i) && !isTaken && !nextTaken && !nextTaken2 && !nextTaken3 && !nextTaken4 && !nextTaken5 && (previousDayWorking || i < 2)) {
    				nr ++;
    			}
    		}
    	}
    	return nr;
    }
    
    
    
    
}
