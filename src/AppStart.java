import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by attila.deak on 8/5/2016.
 */
public class AppStart {
    public static void main(String args[]) {

        Driver a = new Driver("a",new ArrayList(Arrays.asList(2)), new ArrayList(Arrays.asList(15,16,27,28)), new ArrayList(Arrays.asList(14,17,23,25)), new ArrayList(Arrays.asList(5,6,7,8,19,20,21,22)));
        Driver b = new Driver("b",new ArrayList(Arrays.asList(1)), new ArrayList(Arrays.asList(13,14,15,16)), new ArrayList(Arrays.asList(2, 11, 18)), new ArrayList(Arrays.asList(7,8,9,10,21,22,23,24)));
        Driver c = new Driver("c",new ArrayList(Arrays.asList(1)), new ArrayList(Arrays.asList(1,2,19,20)), new ArrayList(Arrays.asList(15,28)), new ArrayList(Arrays.asList(9,10,11,12,23,24,25,26)));
        Driver d = new Driver("d",new ArrayList(Arrays.asList(2,3)), new ArrayList(Arrays.asList(5,6,7,8,21,22)), new ArrayList(Arrays.asList(4,15,19)), new ArrayList(Arrays.asList(11,12,13,14,25,26,27,28)));
        Driver e = new Driver("e",new ArrayList(Arrays.asList(1,2)), new ArrayList(Arrays.asList(19,20,21,22)), new ArrayList(Arrays.asList(23,25)), new ArrayList(Arrays.asList(1,2,13,14,15,16,27,28)));
        Driver f = new Driver("f",new ArrayList(Arrays.asList(2,3)), new ArrayList(Arrays.asList(11,12,23,24)), new ArrayList(Arrays.asList(6,9,28)), new ArrayList(Arrays.asList(1,2,3,4,15,16,17,18)));
        Driver g = new Driver("g",new ArrayList(Arrays.asList(2)), new ArrayList(Arrays.asList(9,10,11,12,27,28)), new ArrayList(Arrays.asList(1,13)), new ArrayList(Arrays.asList(3,4,5,6,17,18,19,20)));
        Driver h = new Driver("h",new ArrayList(Arrays.asList(1,3)), new ArrayList(Arrays.asList(1,2,11,12,13,14)), new ArrayList(Arrays.asList(3,17)), new ArrayList(Arrays.asList(5,6,7,8,19,20,21,22)));
        Driver i = new Driver("i",new ArrayList(Arrays.asList(2)), new ArrayList(Arrays.asList(27,28)), new ArrayList(Arrays.asList(12,16)), new ArrayList(Arrays.asList(7,8,9,10,21,22,23,24)));
        Driver j = new Driver("j",new ArrayList(Arrays.asList(3)), new ArrayList(Arrays.asList(1,2,15,16,17,18)), new ArrayList(Arrays.asList(4,6,21)), new ArrayList(Arrays.asList(9,10,11,12,23,24,25,26)));
        Driver k = new Driver("k",new ArrayList(Arrays.asList(1)), new ArrayList(Arrays.asList(5,6,17,18)), new ArrayList(Arrays.asList(10,15)), new ArrayList(Arrays.asList(11,12,13,14,25,26,27,28)));

        Calendar calendar = new Calendar(new ArrayList(Arrays.asList(a,b,c,d,e,f,g,h,i,j,k)));

        CalendarService calendarService = new CalendarService(calendar);


        calendarService.generateCalendar(calendar);
        calendarService.drawCalendar(calendar);

    }

}
