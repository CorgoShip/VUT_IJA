package classes;

import java.sql.Time;

public class Point {
    private Coordinate coordinate;
    private Boolean zastavka;
    private String streetId;
    private String casOdjezdu;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Boolean getZastavka() {
        return zastavka;
    }

    public String getStreetId() {
        return streetId;
    }

    public void print()
    {
        this.coordinate.printAll();
        System.out.println(this.zastavka);
        System.out.println(this.streetId);
        System.out.println();
    }

    public Point(Coordinate coordinate, Boolean zastavka, String streetId, String casOdjezdu) {
        this.coordinate = coordinate;
        this.zastavka = zastavka;
        this.streetId = streetId;
        this.casOdjezdu = casOdjezdu;
    }
}
