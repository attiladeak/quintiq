/**
 * Created by attila.deak on 8/5/2016.
 */
public class Shift {
    private Integer bus;
    private boolean taken;

    public Shift() {
        this.bus = 0;
        this.taken = false;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }


    public Integer getBus() {
        return bus;
    }

    public void setBus(Integer bus) {
        this.bus = bus;
    }

}
