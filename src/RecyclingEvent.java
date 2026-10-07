import java.io.Serializable;
import java.time.ZonedDateTime;


public class RecyclingEvent implements Serializable {
    public enum MaterialType {
        PLASTIC, GLASS, METAL, PAPER;

        public static MaterialType fromString(String value) {
            return MaterialType.valueOf(value.trim().toUpperCase());
        }
    }

    MaterialType type;
    double weight;
    ZonedDateTime recyclingDate;
    int ecoPointsEarned;

    public RecyclingEvent(MaterialType type, double weight){
        this.weight = weight;
        this.type = type;
        this.ecoPointsEarned = (int) (10 * weight);
        this.recyclingDate = ZonedDateTime.now();
    }

    public double getWeight() {
        return weight;
    }

    public int getEcoPointsEarned() {
        return ecoPointsEarned;
    }
}
