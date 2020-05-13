package classes;

public class Point {
    private Coordinate coordinate;
    private Boolean zastavit;
    private String streetId;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Boolean getZastavit() {
        return zastavit;
    }

    public String getStreetId() {
        return streetId;
    }

    public void print()
    {
        this.coordinate.printAll();
        System.out.println(this.zastavit);
        System.out.println(this.streetId);
        System.out.println();
    }

    public Point(Coordinate coordinate, Boolean zastavit, String streetId) {
        this.coordinate = coordinate;
        this.zastavit = zastavit;
        this.streetId = streetId;
    }
}
