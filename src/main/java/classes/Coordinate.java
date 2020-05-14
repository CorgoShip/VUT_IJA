package classes;

public class Coordinate {

    private double x;
    private double y;

    public Coordinate(double x0, double y0){
        x = x0;
        y = y0;
    }

    public static Coordinate create(double x, double y){
        if(x >= 0 && y >= 0){
            Coordinate temp = new Coordinate(x,y);
            return temp;
        }
        return null;
    }

    public double diffX(Coordinate c)
    {
        return this.x - c.x;
    }

    public double diffY(Coordinate c)
    {
        return this.y - c.y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) { this.y = y; }

    public void printAll()
    {
        System.out.println("x:" + this.x + " y:" + this.y );
    }
}
