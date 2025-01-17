/**
 * Autori: Zbynek Lamacka xlamac01
 *         Simon  Pomykal xpomyk04
 *
 * Trida reprezentuje jednu pozici na trase vozu.
 * Ma souradnice, cas odjezdu, nazev ulice a nazev zastavky (pokud existuje).
 * Obaseuje informaci, zda se jedna o zastavku, ci krizovatku.
 */

package classes;

import java.sql.Time;

public class Point {
    private Coordinate coordinate;
    private Boolean zastavka;
    private String streetId;
    private String casOdjezdu;
    private String stopId;

    public String getStopId() {
        return stopId;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Boolean getZastavka() {
        return zastavka;
    }

    public String getStreetId() {
        return streetId;
    }

    public String getCasOdjezdu() {return casOdjezdu; }

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

    public Point(Point point) {
        this.coordinate = new Coordinate(point.getCoordinate());
        this.zastavka = point.zastavka;
        this.streetId = point.streetId;
        this.casOdjezdu = point.casOdjezdu;
    }

}
