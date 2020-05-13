package classes;

public class Point {
    private Coordinate coordinate;
    private Boolean zastavit;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Boolean getZastavit() {
        return zastavit;
    }

    public void print()
    {
        this.coordinate.printAll();
        System.out.println(this.zastavit);
        System.out.println();
    }
}
