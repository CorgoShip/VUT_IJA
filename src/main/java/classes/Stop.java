/**
 * Autori: Zbynek Lamacka xlamac01
 *         Simon  Pomykal xpomyk04
 *
 * Trida reprezentujici zastavku.
 * Ma nazev a souradnice.
 */

package classes;

import com.google.gson.annotations.SerializedName;

public class Stop {
    private String id;

    @SerializedName("coordinates")
    private Coordinate coordinate;

    public Stop(String id, Coordinate coordinate) {
        this.id = id;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getId() {
        return id;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
