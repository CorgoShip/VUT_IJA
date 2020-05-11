package classes;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x0, int y0){
        x = x0;
        y = y0;
    }

    public static Coordinate create(int x, int y){
        if(x >= 0 && y >= 0){
            Coordinate temp = new Coordinate(x,y);
            return temp;
        }
        return null;
    }

    public int diffX(Coordinate c)
    {
        return this.x - c.x;
    }

    public int diffY(Coordinate c)
    {
        return this.y - c.y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode()
    {
        int result = 31;
        result = result * this.getX();
        result = result * 31 + this.getY();
        return result;
    }
}
