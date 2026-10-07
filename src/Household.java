import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Household   implements Serializable {
    String id;
    String name;
    String address;
    ZonedDateTime joiningDate;
    ArrayList<RecyclingEvent> recyclingEvents;

    public Household(String id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
        this.joiningDate = ZonedDateTime.now();
        this.recyclingEvents = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public ZonedDateTime getJoinDate(){
        return this.joiningDate;
    }

    public ArrayList<RecyclingEvent> getEvents(){
        return this.recyclingEvents;
    }

    public double getTotalWeight() {
        double total = 0.0;
        for (RecyclingEvent event : recyclingEvents) {
            total += event.getWeight();
        }
        return total;
    }

    public double getTotalPoints() {
        double total = 0.0;
        for (RecyclingEvent event : recyclingEvents) {
            total += event.getEcoPointsEarned();
        }
        return total;
    }

    public void addEvent(RecyclingEvent event){
        this.recyclingEvents.add(event);
    }

}
